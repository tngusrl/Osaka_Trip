package com.example.osaka_trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class Preparation_Adapter extends BaseAdapter {

    private ArrayList<Preparation_Item> list;

    Preparation_Adapter(ArrayList<Preparation_Item> i){
        list = i;
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
    public long getItemId(int position) {
        return 0;
    }

    public boolean isChecked(int position) {
        return list.get(position).checked;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.preparation_listview_item, parent, false);
        }

        TextView tv_preparation = convertView.findViewById(R.id.item_text);
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);

        checkBox.setChecked(list.get(position).checked);

        tv_preparation.setText(list.get(position).ItemString);

        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                boolean newState = !list.get(position).isChecked();
                list.get(position).checked = newState;

            }
        });

        checkBox.setChecked(isChecked(position));
        return convertView;
    }
}
