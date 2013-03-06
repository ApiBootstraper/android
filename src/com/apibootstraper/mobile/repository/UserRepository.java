/**
 * 
 */
package com.apibootstraper.mobile.repository;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.apibootstraper.core.User;
import com.apibootstraper.mobile.http.HTTPClient;
import com.apibootstraper.mobile.http.HTTPResponse;
import com.apibootstraper.mobile.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author nicolas
 *
 */
public class UserRepository {

    /**
     * Search a user
     * 
     * @apiRoute /users/search?q={query}
     * 
     * @param query
     * @param responseHandler
     */
    public static void search(String query, final HTTPResponse<ArrayList<User>> response) {

        RequestParams params = new RequestParams();
        params.put("q", query);

        HTTPClient.getInstance().get("users/my", params, new JsonHttpResponseHandler<ArrayList<User>>(response) {

            @Override
            public void onSuccess(int statusCode, JSONObject json) {
                try {
                    ArrayList<User> users = new ArrayList<User>();

                    JSONArray array = json.getJSONObject("response").getJSONArray("users");
                    for(int i = 0 ; i < array.length(); i++) {
                        User user = new User(array.getJSONObject(i));
                        users.add(user);
                    }

                    response.onSuccess(statusCode, users, json);

                } catch(Exception e) {
                    onFailure(e, json);
                }
            }
        });
    }

    /**
     * Find a user by UUID
     * 
     * @apiRoute /users/{uuid}
     * 
     * @param uuid
     * @param responseHandler
     */
    public static void findByUUID(String uuid, final HTTPResponse<User> response) {
        HTTPClient.getInstance().get(String.format("users/%s", uuid), null, new JsonHttpResponseHandler<User>(response) {

            @Override
            public void onSuccess(JSONObject json) {
                try {
                    User user = new User(json.getJSONObject("response").getJSONObject("user"));
                    response.onSuccess(user);

                } catch(Exception e) {
                    onFailure(e, json);
                }
            }
        });
    }
}
