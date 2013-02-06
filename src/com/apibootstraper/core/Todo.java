package com.apibootstraper.core;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apibootstraper.mobile.http.HTTPClient;
import com.apibootstraper.mobile.http.HTTPResponse;
import com.apibootstraper.mobile.http.JsonHttpResponseHandler;
import com.apibootstraper.mobile.util.DateUtils;

public class Todo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -2851331358231023655L;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH);

    private String uuid;

    private String name;

    private String description;
    
    private boolean isAccomplished;

    private Date createdAt;

    private Date updatedAt;

    private User user;

    /**
     * 
     * @param o
     * @throws JSONException 
     * @throws ParseException 
     */
    public Todo(JSONObject o) throws JSONException, ParseException {
        this.uuid = o.getString("uuid");

        this.name        = o.has("name") ? o.getString("name") : null;
        this.description = o.has("description") ? o.getString("description") : null;

        this.isAccomplished = o.has("is_accomplished") ? o.getBoolean("is_accomplished") : false;
        
        this.user = o.has("user") ? new User(o.getJSONObject("user")) : null;

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
     * @return the isAccomplished
     */
    public boolean isAccomplished() {
        return isAccomplished;
    }

    /**
     * @param isAccomplished the isAccomplished to set
     */
    public void setAccomplished(boolean isAccomplished) {
        this.isAccomplished = isAccomplished;
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

    @Override
    public String toString() {
        return String.format("<Todo %s uuid:\"%s\" name:\"%s\" description:\"%s\" isAccomplished:%s createdAt:\"%s\" updatedAt:\"%s\">",
            hashCode(), getUUID(), getName(), getDescription(), isAccomplished(), getCreatedAt(), getUpdatedAt()
        );
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
        HTTPClient.getInstance().get("todo/my", null, new JsonHttpResponseHandler<ArrayList<Todo>>(response) {

            @Override
            public void onSuccess(int statusCode, JSONObject json) {
                try {
                    ArrayList<Todo> todos = new ArrayList<Todo>();

                    JSONArray array = json.getJSONObject("response").getJSONArray("todos");
                    for(int i = 0 ; i < array.length(); i++){
                        Todo todo = new Todo(array.getJSONObject(i));
                        todos.add(todo);
                    }

                    response.onSuccess(statusCode, todos);

                } catch(Exception e) {
                    onFailure(e, json);
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
        HTTPClient.getInstance().get(String.format("todo/%s", uuid), null, new JsonHttpResponseHandler<Todo>(response) {

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
