package com.apibootstraper.mobile.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.apibootstraper.core.Todo;
import com.apibootstraper.mobile.R;
import com.apibootstraper.mobile.TodoApplication;
import com.apibootstraper.mobile.R.id;
import com.apibootstraper.mobile.R.layout;
import com.apibootstraper.mobile.R.menu;
import com.apibootstraper.mobile.http.HTTPClient;
import com.apibootstraper.mobile.http.HTTPResponse;

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

        // Create an empty list for waiting
        String[] list = {"No datas"};
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));

        // Temporary set credentials
        HTTPClient.getInstance().setBasicAuth("user@example.com", "password");

        // Load the remote data
        refreshTodos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.refresh) {
            refreshTodos();
        }

        return super.onOptionsItemSelected(item);
    }
    
    private void refreshTodos()
    {
        application.showProgressDialog(this);
        Todo.findAll(new HTTPResponse<ArrayList<Todo>>() {

            @Override
            public void onSuccess(ArrayList<Todo> todos) {
                Log.d("HTTPResponse", todos.toString());
                ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(MainActivity.this, android.R.layout.simple_list_item_1, todos);
            
                listView.setAdapter(adapter);
            }
            
            @Override
            public void onFailure(Throwable e) {
                Toast t = new Toast(MainActivity.this.getApplicationContext());
                t.setText(R.string.loading_error);
                t.show();
            }

            @Override
            public void onFinish() {
                MainActivity.this.application.hideProgressDialog();
            }
        });
    }

}
