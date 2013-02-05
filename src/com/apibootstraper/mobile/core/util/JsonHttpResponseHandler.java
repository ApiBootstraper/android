/**
 * 
 */
package com.apibootstraper.mobile.core.util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author nicolas
 *
 */
public class JsonHttpResponseHandler extends com.loopj.android.http.JsonHttpResponseHandler {

    protected final HTTPResponse response;

    public JsonHttpResponseHandler(HTTPResponse response) {
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
     * Fired when a request returns successfully and contains a json object
     * at the base of the response string. Override to handle in your
     * own code.
     * @param response the parsed json object found in the server response (if any)
     */
    public void onSuccess(JSONObject response) {
        this.response.onSuccess(response);
    }


    /**
     * Fired when a request returns successfully and contains a json array
     * at the base of the response string. Override to handle in your
     * own code.
     * @param response the parsed json array found in the server response (if any)
     */
    public void onSuccess(JSONArray response) {
        this.response.onSuccess(response);
    }

    /**
     * Fired when a request returns successfully and contains a json object
     * at the base of the response string. Override to handle in your
     * own code.
     * @param statusCode the status code of the response
     * @param response the parsed json object found in the server response (if any)
     */
    public void onSuccess(int statusCode, JSONObject response) {
        onSuccess(response);
    }


    /**
     * Fired when a request returns successfully and contains a json array
     * at the base of the response string. Override to handle in your
     * own code.
     * @param statusCode the status code of the response
     * @param response the parsed json array found in the server response (if any)
     */
    public void onSuccess(int statusCode, JSONArray response) {
        onSuccess(response);
    }

    public void onFailure(Throwable e, JSONObject errorResponse) {
        this.response.onFailure(e, errorResponse);
    }

    public void onFailure(Throwable e, JSONArray errorResponse) {
        this.response.onFailure(e, errorResponse);
    }
}
