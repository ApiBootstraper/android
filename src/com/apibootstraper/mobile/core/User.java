package com.apibootstraper.mobile.core;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONArray;

import com.apibootstraper.mobile.core.util.DateUtils;
import com.apibootstraper.mobile.core.util.GsonHttpResponseHandler;
import com.apibootstraper.mobile.core.util.HTTPClient;
import com.apibootstraper.mobile.core.util.HTTPResponse;
import com.loopj.android.http.JsonHttpResponseHandler;

public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4670218264175191002L;

    private String uuid;

    private Date createdAt;

    private Date updatedAt;

    /**
     * @return id
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * @param id
     * @return this user
     */
    public User setUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * @return createdAt
     */
    public Date getCreatedAt() {
        return DateUtils.clone(createdAt);
    }

    /**
     * @param createdAt
     * @return this User
     */
    public User setCreatedAt(Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }

    /**
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return DateUtils.clone(updatedAt);
    }

    /**
     * @param updatedAt
     * @return this user
     */
    public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }

    /**
     * Find a user by UUID
     * 
     * @apiRoute /user/{uuid}
     * 
     * @param uuid
     * @param responseHandler
     */
    public static void findByUUID(String uuid, final HTTPResponse<User> response) {

        onStart handler = new GsonHttpResponseHandler(response) {

            @Override
            public void onSuccess(String content) {
                try {
                    User user = new User();

                    response.onSuccess(user);
                } catch(Exception e) {
                    e.printStackTrace();

                    response.onFailure(e, null);
                }
            }
        };

        HTTPClient.getInstance().get("user/" + uuid, null, handler);
    }

    /**
     * Check if a username is available
     * 
     * @apiRoute /user/availability?username={username}
     * 
     * @param username
     * @param response
     */
    public static void userAvailability(String username, final HTTPResponse<Boolean> response) {

        onStart handler = new GsonHttpResponseHandler(response) {

            @Override
            public void onSuccess(String content) {
                try {
                    boolean res = true;

                    response.onSuccess(res);
                } catch(Exception e) {
                    e.printStackTrace();

                    response.onFailure(e, null);
                }
            }
        };

        HTTPClient.getInstance().get("user/availability?=" + username, null, handler);
    }
}
