package com.abc.money.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shrishty on 7/26/15.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListDataAdapter(Context context, int resource){
        super(context, resource);
    }

    static class LayoutHandler {
        TextView tvname;
        TextView tvAmount;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        Log.e("money123", "in GetView");

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.tvname = (TextView) row.findViewById(R.id.tvName);
            layoutHandler.tvAmount = (TextView) row.findViewById(R.id.tvAmount);
            row.setTag(layoutHandler);
        } else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.tvname.setText(dataProvider.getName());
        layoutHandler.tvAmount.setText(""+ dataProvider.getAmount());


        return row;
    }
}
