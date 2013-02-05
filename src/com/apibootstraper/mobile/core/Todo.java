package com.apibootstraper.mobile.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;

import com.apibootstraper.mobile.core.util.DateUtils;
import com.apibootstraper.mobile.core.util.GsonHttpResponseHandler;
import com.apibootstraper.mobile.core.util.HTTPClient;
import com.apibootstraper.mobile.core.util.HTTPResponse;
import com.loopj.android.http.JsonHttpResponseHandler;

public class Todo implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -2851331358231023655L;

    private String uuid;

    private Date createdAt;

    private Date updatedAt;

    private User user;

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
        HTTPClient.getInstance().get("todo/my", null, new GsonHttpResponseHandler<ArrayList<Todo>>(response) {

            @Override
            public void onSuccess(String content) {
                try {
                    ArrayList<Todo> todos = new ArrayList<Todo>();

//                    for (JSONObject t : todos) {
//                        Todo todo = new Todo();
//                        todoList.add(todo);
//                    }

                    response.onSuccess(todos);

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

        GsonHttpResponseHandler handler = new GsonHttpResponseHandler<Todo>(response) {

            @Override
            public void onSuccess(String content) {
                try {
                    Todo todo = new Todo();

                    response.onSuccess(todo);
                } catch(Exception e) {
                    onFailure(e);
                }
            }
        };

        HTTPClient.getInstance().get("todo/" + uuid, null, handler);
    }
}
