/**
 * 
 */
package com.apibootstraper.mobile.core.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author nicolas
 *
 */
public class GsonHttpResponseHandler<T> extends AsyncHttpResponseHandler {

    protected final HTTPResponse<T> response;
    
    protected final Gson gson;

    public GsonHttpResponseHandler(HTTPResponse<T> response) {
        this.response = response;
        this.gson = new Gson();
    }

    //
    // Callbacks to be overridden, typically anonymously
    //

    /**
     * Fired when the request is started, override to handle in your own code
     */
    public void onStart() {
        this.response.onStart();
    }

    /**
     * Fired in all cases when the request is finished, after both success and failure, override to handle in your own code
     */
    public void onFinish() {
        this.response.onFinish();
    }

    /**
     * Fired when a request returns successfully, override to handle in your own code
     * @param content the body of the HTTP response from the server
     */
    public void onSuccess(String json) {
    	T res = gson.fromJson(json, new TypeToken<T>(){}.getType());
        this.response.onSuccess(res);
    }

    /**
     * Fired when a request returns successfully, override to handle in your own code
     * @param statusCode the status code of the response
     * @param content the body of the HTTP response from the server
     */
    public void onSuccess(int statusCode, String json) {
    	T res = gson.fromJson(json, new TypeToken<T>(){}.getType());
        this.response.onSuccess(statusCode, res);
    }

    /**
     * Fired when a request fails to complete, override to handle in your own code
     * @param error the underlying cause of the failure
     * @deprecated use {@link #onFailure(Throwable, String)}
     */
    public void onFailure(Throwable error) {
        this.response.onFailure(error);
    }

    /**
     * Fired when a request fails to complete, override to handle in your own code
     * @param error the underlying cause of the failure
     * @param content the response body, if any
     */
    public void onFailure(Throwable error, String content) {
        // By default, call the deprecated onFailure(Throwable) for compatibility
        this.response.onFailure(error, content);
    }
}
