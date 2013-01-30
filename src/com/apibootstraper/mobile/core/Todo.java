package com.apibootstraper.mobile.core;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apibootstraper.mobile.core.util.DateUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
	 * @param uuid
	 * @param responseHandler
	 */
	public static void findAll(AsyncHttpResponseHandler responseHandler) {
		HTTPClient.get("todo", null, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray timeline) {
				try {
					JSONObject firstEvent = (JSONObject)timeline.get(0);
					String tweetText = firstEvent.getString("text");

					// Do something with the response
					System.out.println(tweetText);
				} catch(JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Find a todo by UUID
	 * 
	 * @param uuid
	 * @param responseHandler
	 */
	public static void findByUUID(String uuid, AsyncHttpResponseHandler responseHandler) {
		HTTPClient.get("todo/" + uuid, null, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray timeline) {
				try {
					JSONObject firstEvent = (JSONObject)timeline.get(0);
					String tweetText = firstEvent.getString("text");

					// Do something with the response
					System.out.println(tweetText);
				} catch(JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
