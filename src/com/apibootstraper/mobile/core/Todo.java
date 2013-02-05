package com.apibootstraper.mobile.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.net.ParseException;

import com.apibootstraper.mobile.core.util.DateUtils;
import com.apibootstraper.mobile.core.util.HTTPClient;
import com.apibootstraper.mobile.core.util.HTTPResponse;
import com.apibootstraper.mobile.core.util.JsonHttpResponseHandler;

public class Todo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -2851331358231023655L;

    private String uuid;

    private String name;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private User user;

    /**
     * 
     * @param t
     * @throws JSONException 
     * @throws java.text.ParseException 
     * @throws java.text.ParseException 
     */
    protected void initFromJsonObject(JSONObject o) throws JSONException, java.text.ParseException {
        this.uuid = o.getString("uuid");

        this.name        = o.getString("name");
//        this.description = o.getString("description");

        this.createdAt = (Date) new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH).parse(o.getString("created_at"));
        this.updatedAt = (Date) new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH).parse(o.getString("updated_at"));
    }

    /**
     * @return id
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * @param id
     * @return this todo
     */
    public Todo setUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return createdAt
     */
    public Date getCreatedAt() {
        return DateUtils.clone(createdAt);
    }

    /**
     * @param createdAt
     * @return this todo
     */
    public Todo setCreatedAt(Date createdAt) {
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
     * @return this todo
     */
    public Todo setUpdatedAt(Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }

    /**
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     * @return this todo
     */
    public Todo setUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Find all todo
     * 
     * @apiRoute /todo
     * 
     * @param uuid
     * @param responseHandler
     */
    public static void findAll(final HTTPResponse<ArrayList<Todo>> response) {
        HTTPClient.getInstance().get("todo/my", null, new JsonHttpResponseHandler(response) {

            @Override
            public void onSuccess(JSONObject json) {
                try {
                    ArrayList<Todo> todos = new ArrayList<Todo>();

                    JSONArray array = json.getJSONObject("response").getJSONArray("todos");
                    for(int i = 0 ; i < array.length(); i++){
                        Todo todo = new Todo();
                        todo.initFromJsonObject(array.getJSONObject(i));
                        todos.add(todo);
                    }

                    this.response.onSuccess(todos);
                    this.response.onSuccess(0, todos);

                } catch(Exception e) {
                    e.printStackTrace();
                    onFailure(e);
                }
            }
        });
    }

    /**
     * Find a todo by UUID
     * 
     * @apiRoute /todo/{uuid}
     * 
     * @param uuid
     * @param responseHandler
     */
    public static void findByUUID(String uuid, final HTTPResponse<Todo> response) {

        JsonHttpResponseHandler handler = new JsonHttpResponseHandler(response) {

            @Override
            public void onSuccess(JSONObject json) {
                try {
                    Todo todo = new Todo();
                    todo.initFromJsonObject(json.getJSONObject("response").getJSONObject("todo"));

                    response.onSuccess(todo);
                } catch(Exception e) {
                    onFailure(e);
                }
            }
        };

        HTTPClient.getInstance().get("todo/" + uuid, null, handler);
    }
}
