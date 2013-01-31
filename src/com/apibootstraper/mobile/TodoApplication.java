package com.apibootstraper.mobile;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

public class TodoApplication extends Application {

    public ProgressDialog progressDialog;

    /**
     * 
     * @param context
     */
    public void showProgressDialog(Context context) {
        progressDialog = ProgressDialog.show(context, getString(R.string.loading), getString(R.string.loading), true);
    }

    /**
     * 
     */
    public void hideProgressDialog() {

        if (progressDialog != null) {
            progressDialog.hide();
        }
    }
}
