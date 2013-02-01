/**
 * 
 */
package com.apibootstraper.mobile.core.util;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author nicolas
 *
 */
public class GsonHttpResponseHandler extends AsyncHttpResponseHandler {

    protected final HTTPResponse response;

    public GsonHttpResponseHandler(HTTPResponse response) {
        this.response = response;
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
    public void onSuccess(String content) {
        this.response.onSuccess(content);
    }

    /**
     * Fired when a request returns successfully, override to handle in your own code
     * @param statusCode the status code of the response
     * @param content the body of the HTTP response from the server
     */
    public void onSuccess(int statusCode, String content) {
        this.response.onSuccess(statusCode, content);
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
