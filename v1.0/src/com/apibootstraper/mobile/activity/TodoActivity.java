package com.apibootstraper.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.apibootstraper.core.Todo;
import com.apibootstraper.mobile.R;
import com.apibootstraper.mobile.TodoApplication;
import com.apibootstraper.mobile.http.HTTPResponse;
import com.apibootstraper.mobile.repository.TodoRepository;

public class TodoActivity extends Activity
{
    private TodoApplication application;

    private TextView todoViewName;
    private TextView todoViewDescription;

    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        application = (TodoApplication)getApplication();
        
        todoViewName        = (TextView)findViewById(R.id.todo_view_name);
        todoViewDescription = (TextView)findViewById(R.id.todo_view_description);

        Intent intent   = this.getIntent();
        Bundle b        = intent.getBundleExtra("bundle");
        Object obj      = b != null ? b.getSerializable("todo") : null;

        if (obj != null && obj instanceof Todo)
        {
            todo = (Todo)obj;
            refreshView();
            loadTodo(todo.getUUID());
        }
        else {
            // TODO: return message because there are no todo specified 
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void refreshView()
    {
        todoViewName.setText(todo.getName());
        todoViewDescription.setText(todo.getDescription());
    }

    private void loadTodo(String uuid)
    {
        application.showProgressDialog(this);
        TodoRepository.findByUUID(uuid, new HTTPResponse<Todo>() {

            @Override
            public void onSuccess(Todo todo)
            {
                TodoActivity.this.todo = todo;
                refreshView();
            }

            @Override
            public void onFailure(Throwable e)
            {
                Toast toast = Toast.makeText(TodoActivity.this.getApplicationContext(), R.string.loading_error, Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onFinish() {
                TodoActivity.this.application.hideProgressDialog();
            }
        });
    }
}
