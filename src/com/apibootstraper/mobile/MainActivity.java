package com.apibootstraper.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apibootstraper.mobile.core.User;
import com.apibootstraper.mobile.core.util.HTTPResponse;

public class MainActivity extends Activity
{
    private TodoApplication application;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        application = (TodoApplication)getApplication();

        listView = (ListView)findViewById(R.id.todoListView);
        String[] listeStrings = {"My first application","Update my application"};

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));

        // Try to call WS
        application.showProgressDialog(this);
        User.userAvailability("teste", new HTTPResponse<Boolean>() {

            @Override
            public void onSuccess(Boolean isAvailable) {
                Log.d("WS_CALL", isAvailable ? "YES" : "NO");
            }
            
            @Override
            public void onFailure(Throwable error, String content) {
                Log.d("WS_CALL", error.getMessage());
                Log.d("WS_CALL", content);
            }
            
            @Override
            public void onFinish() {
                application.hideProgressDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
