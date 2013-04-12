/**
 * 
 */
package com.apibootstraper.mobile.view;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apibootstraper.core.Todo;
import com.apibootstraper.mobile.R;

/**
 * @author nicolas
 *
 */
public class TodoArrayAdapter extends ArrayAdapter<Todo> {

    private final List<Todo> list;
    private final Activity context;

    /**
     * 
     */
    public TodoArrayAdapter(Activity context, List<Todo> list) {

        super(context, R.layout.todo_list_item, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null) {

            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.todo_list_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.todo_list_item_name);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        Todo entity = (Todo) list.get(position);
        holder.text.setText(entity.getName());
        return view;
    }

}
