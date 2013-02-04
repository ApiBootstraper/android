package com.apibootstraper.mobile.core.util;

import android.util.Log;

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
     * @param response
     */
    public void onSuccess(T object) {}

    /**
     * 
     * @param statusCode
     * @param object
     */
    public void onSuccess(int statusCode, T object) {
        onSuccess(object);
    }

    /**
     * Fired when a request fails to complete, override to handle in your own code
     * @param error the underlying cause of the failure
     */
    public void onFailure(Throwable error) {
        Log.e("HTTPResponse", error.getMessage());
    }

    /**
     * Fired when a request fails to complete, override to handle in your own code
     * @param error the underlying cause of the failure
     * @param content the response body, if any
     */
    public void onFailure(Throwable error, String content) {
        onFailure(error);
        Log.e("HTTPResponse", content);
    }
}
