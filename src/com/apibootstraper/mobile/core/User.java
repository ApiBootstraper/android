package com.apibootstraper.mobile.core;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apibootstraper.mobile.core.util.DateUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
	 * @param uuid
	 * @param responseHandler
	 */
	public static void findByUUID(String uuid, AsyncHttpResponseHandler responseHandler) {
		HTTPClient.getInstance().get("user/" + uuid, null, new JsonHttpResponseHandler() {

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
