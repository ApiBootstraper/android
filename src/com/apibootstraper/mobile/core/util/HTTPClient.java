package com.apibootstraper.mobile.core.util;

import android.util.Log;

import com.loopj.android.http.*;

public final class HTTPClient extends AsyncHttpClient {

    private static final String API_BASE_URL = "http://api.apibootstraper.com/";
    private static final String API_VERSION  = "1.0";

    private static final String APP_ID  = "APP_ID";
    private static final String APP_KEY = "APP_KEY";

    private static volatile HTTPClient instance = null;


    /**
     * 
     * @return a shared HTTPClient
     */
    public final static HTTPClient getInstance() {

        if (HTTPClient.instance == null) {
            synchronized(HTTPClient.class) {
                if (HTTPClient.instance == null) {
                    HTTPClient.instance = new HTTPClient();
                }
            }
        }
        return HTTPClient.instance;
    }

    /**
     * 
     */
    private HTTPClient() {
        super();

        // Default headers
        addHeader("X-Api-Version", API_VERSION);
        addHeader("X-APP_ID", APP_ID);
        addHeader("X-APP_KEY", APP_KEY);

        addHeader("Accept", "application/json");
        
    }


    /**
     * Call a REST GET webservice
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("HTTPClient", "HTTP GET " + getAbsoluteUrl(url));
        super.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Call a REST POST webservice
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("HTTPClient", "HTTP POST " + getAbsoluteUrl(url));
        super.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Call a REST PUT webservice
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("HTTPClient", "HTTP PUT " + getAbsoluteUrl(url));
        super.put(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Call a REST DELETE webservice
     * 
     * @param url
     * @param responseHandler
     */
    public void delete(String url, AsyncHttpResponseHandler responseHandler) {
        Log.d("HTTPClient", "HTTP DELETE " + getAbsoluteUrl(url));
       super.delete(getAbsoluteUrl(url), responseHandler);
    }

    /**
     * @param relativeUrl
     * @return the absolute url
     */
    private String getAbsoluteUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }
}
