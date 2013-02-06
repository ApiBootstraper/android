/**
 * 
 */
package com.apibootstraper.mobile.http;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author nicolas
 *
 */
public class JsonHttpResponseHandler<T> extends com.loopj.android.http.JsonHttpResponseHandler {

    protected final HTTPResponse<T> response;

    public JsonHttpResponseHandler(HTTPResponse<T> response) {
        this.response = response;
    }

    //
    // Callbacks to be overridden, typically anonymously
    //

    @Override
    public void onStart() {
        this.response.onStart();
    }

    @Override
    public void onFinish() {
        this.response.onFinish();
    }


    @Override
    public void onSuccess(JSONObject object) {}

    @Override
    public void onSuccess(JSONArray array) {}

    @Override
    public void onSuccess(int statusCode, JSONObject response) {
        onSuccess(response);
    }

    @Override
    public void onSuccess(int statusCode, JSONArray response) {
        onSuccess(response);
    }


    @Override
    public void onFailure(Throwable e, JSONObject errorResponse) {
        e.printStackTrace();
        Log.e("HTTPResponse", e.getMessage());
        Log.e("HTTPResponse", errorResponse.toString());
        this.response.onFailure(e, errorResponse);
    }

    @Override
    public void onFailure(Throwable e, JSONArray errorResponse) {
        e.printStackTrace();
        Log.e("HTTPResponse", e.getMessage());
        Log.e("HTTPResponse", errorResponse.toString());
        this.response.onFailure(e, errorResponse);
    }
}
