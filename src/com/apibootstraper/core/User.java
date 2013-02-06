package com.apibootstraper.core;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.apibootstraper.mobile.http.HTTPClient;
import com.apibootstraper.mobile.http.HTTPResponse;
import com.apibootstraper.mobile.http.JsonHttpResponseHandler;
import com.apibootstraper.mobile.util.DateUtils;

public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4670218264175191002L;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH);

    private String uuid;

    private Date createdAt;

    private Date updatedAt;

    /**
     * 
     * @param o
     * @throws JSONException 
     * @throws ParseException 
     */
    public User(JSONObject o) throws JSONException, ParseException {
        this.uuid = o.getString("uuid");

        this.createdAt = (Date) dateFormat.parse(o.getString("created_at"));
        this.updatedAt = (Date) dateFormat.parse(o.getString("updated_at"));
    }

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
        HTTPClient.getInstance().get(String.format("user/%s", uuid), null, new JsonHttpResponseHandler<User>(response) {

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
