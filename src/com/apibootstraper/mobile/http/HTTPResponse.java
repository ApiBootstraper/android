package com.apibootstraper.mobile.http;

import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPResponse<T> {

    /**
     * Fired when the request is started, override to handle in your own code
     */
    public void onStart() {}

    /**
     * Fired in all cases when the request is finished, after both success and failure, override to handle in your own code
     */
    public void onFinish() {}

    /**
     * 
     * @param object
     */
    public void onSuccess(T object) {}

    /**
     * 
     * @param object
     * @param response
     */
    public void onSuccess(T object, JSONObject response) {}

    /**
     * 
     * @param object
     * @param response
     */
    public void onSuccess(T object, JSONArray response) {}

    /**
     * 
     * @param statusCode
     * @param object
     */
    public void onSuccess(int statusCode, T object) {
        onSuccess(object);
    }

    /**
     * 
     * @param statusCode
     * @param object
     * @param response
     */
    public void onSuccess(int statusCode, T object, JSONObject response) {
        onSuccess(object, response);
    }

    /**
     * 
     * @param statusCode
     * @param object
     * @param response
     */
    public void onSuccess(int statusCode, T object, JSONArray response) {
        onSuccess(object, response);
    }

    /**
     * Fired when a request fails to complete, override to handle in your own code
     * 
     * @param error the underlying cause of the failure
     * @deprecated use {@link #onFailure(Throwable, JSONObject)} or {@link #onFailure(Throwable, JSONArray)}
     */
    public void onFailure(Throwable e) {}

    /**
     * Fired when a request fails to complete, override to handle in your own code
     * 
     * @param error the underlying cause of the failure
     * @param content the response body, if any
     */
    @SuppressWarnings("deprecation")
    public void onFailure(Throwable e, JSONObject errorResponse) {
        // By default, call the deprecated onFailure(Throwable) for compatibility
        onFailure(e);
    }

    /**
     * 
     * @param e
     * @param errorResponse
     */
    @SuppressWarnings("deprecation")
    public void onFailure(Throwable e, JSONArray errorResponse) {
        // By default, call the deprecated onFailure(Throwable) for compatibility
        onFailure(e);
    }
}
