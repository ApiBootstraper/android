/**
 * 
 */
package com.apibootstraper.mobile.repository;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.apibootstraper.core.Todo;
import com.apibootstraper.mobile.http.HTTPClient;
import com.apibootstraper.mobile.http.HTTPResponse;
import com.apibootstraper.mobile.http.JsonHttpResponseHandler;

/**
 * @author nicolas
 *
 */
public class TodoRepository {

    /**
     * Find all todo
     * 
     * @apiRoute /todos/my
     * 
     * @param uuid
     * @param responseHandler
     */
    public static void findAll(final HTTPResponse<ArrayList<Todo>> response) {
        HTTPClient.getInstance().get("todos/my", null, new JsonHttpResponseHandler<ArrayList<Todo>>(response) {

            @Override
            public void onSuccess(int statusCode, JSONObject json) {
                try {
                    ArrayList<Todo> todos = new ArrayList<Todo>();

                    JSONArray array = json.getJSONObject("response").getJSONArray("todos");
                    for(int i = 0 ; i < array.length(); i++) {
                        Todo todo = new Todo(array.getJSONObject(i));
                        todos.add(todo);
                    }

                    response.onSuccess(statusCode, todos, json);

                } catch(Exception e) {
                    onFailure(e, json);
                }
            }
        });
    }

    /**
     * Find a todo by UUID
     * 
     * @apiRoute /todos/{uuid}
     * 
     * @param uuid
     * @param responseHandler
     */
    public static void findByUUID(String uuid, final HTTPResponse<Todo> response) {
        HTTPClient.getInstance().get(String.format("todos/%s", uuid), null, new JsonHttpResponseHandler<Todo>(response) {

            @Override
            public void onSuccess(JSONObject json) {
                try {
                    Todo todo = new Todo(json.getJSONObject("response").getJSONObject("todo"));
                    response.onSuccess(todo);

                } catch(Exception e) {
                    onFailure(e, json);
                }
            }
        });
    }
}
