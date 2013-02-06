package com.apibootstraper.mobile.http;

import android.util.Log;

import com.apibootstraper.mobile.util.AppConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public final class HTTPClient extends AsyncHttpClient {

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
        addHeader("X-Api-Version", AppConfig.HTTP_API_VERSION);
        addHeader("X-APP-ID", AppConfig.HTTP_API_APP_ID);
        addHeader("X-APP-KEY", AppConfig.HTTP_API_APP_KEY);

        addHeader("Accept", "application/json");
        addHeader("Content-Type", "application/json");
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
        return AppConfig.HTTP_API_BASE_URL + relativeUrl;
    }
}
