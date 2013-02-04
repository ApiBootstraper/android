package com.apibootstraper.mobile;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apibootstraper.mobile.core.Todo;
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

        // Create an empty list for waiting
        String[] list = {"No datas"};
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        
        // Load the remote data
        refreshTodos();

        // Try to call WS
//        application.showProgressDialog(this);
//        User.userAvailability("teste", new HTTPResponse<Boolean>() {
//
//            @Override
//            public void onSuccess(Boolean isAvailable) {
//                Log.d("WS_CALL", isAvailable ? "YES" : "NO");
//            }
//
//            @Override
//            public void onFinish() {
//                MainActivity.this.application.hideProgressDialog();
//            }
//        });
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
                
//                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.todo_list_item, ));
            }

            @Override
            public void onFinish() {
                MainActivity.this.application.hideProgressDialog();
            }
        });
    }

}
