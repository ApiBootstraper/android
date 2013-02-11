package com.apibootstraper.core;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.apibootstraper.mobile.util.DateUtils;

public class User implements Entity, Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4670218264175191002L;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH);

    private String uuid;
    
    private String username;
    
    private String email;

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

        this.username = o.getString("username");
        this.email    = o.getString("email");

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     * @return this User
     */
    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     * @return this User
     */
    public User setEmail(String email) {
        this.email = email;
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
}
