package com.apibootstraper.mobile.core;

import com.loopj.android.http.*;

public class HTTPClient {

	private static final String BASE_URL = "http://api.twitter.com/1/";

	private static AsyncHttpClient client = new AsyncHttpClient();

	/**
	 * Call a REST GET webservice
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Call a REST POST webservice
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Call a REST PUT webservice
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.put(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Call a REST DELETE webservice
	 * 
	 * @param url
	 * @param responseHandler
	 */
	public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
		client.delete(getAbsoluteUrl(url), responseHandler);
	}

	/**
	 * @param relativeUrl
	 * @return the absolute url
	 */
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}
