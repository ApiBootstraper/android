package com.apibootstraper.core;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.apibootstraper.mobile.util.DateUtils;

public class Todo implements Entity, Serializable {

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @return this todo
     */
    public Todo setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     * @return this todo
     */
    public Todo setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * @return the isAccomplished
     */
    public boolean isAccomplished() {
        return isAccomplished;
    }

    /**
     * @param isAccomplished the isAccomplished to set
     * @return this todo
     */
    public Todo setAccomplished(boolean isAccomplished) {
        this.isAccomplished = isAccomplished;
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

    @Override
    public String toString() {
        return String.format("<Todo %s uuid:\"%s\" name:\"%s\" description:\"%s\" isAccomplished:%s createdAt:\"%s\" updatedAt:\"%s\">",
            hashCode(), getUUID(), getName(), getDescription(), isAccomplished(), getCreatedAt(), getUpdatedAt()
        );
    }
}
