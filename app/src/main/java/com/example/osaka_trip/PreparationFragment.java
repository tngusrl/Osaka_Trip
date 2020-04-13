package com.example.osaka_trip;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PreparationFragment extends Fragment {

    ListView listView;

    ArrayList<Preparation_Item> items;

    public PreparationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_preparation, container, false);

        ActionBar ab = ((AppCompatActivity) getActivity ()). getSupportActionBar() ;
        ab.setTitle("준비물") ;

        initItems();

        listView = v.findViewById(R.id.listView);

        Preparation_Adapter mAdapter = new Preparation_Adapter(items);
        listView.setAdapter(mAdapter);


        return v;
    }

    private void initItems(){
        items = new ArrayList<Preparation_Item>();

        TypedArray arrayText = getResources().obtainTypedArray(R.array.restext);

        for(int i=0; i<arrayText.length(); i++){
            String s = arrayText.getString(i);
            boolean b = false;
            Preparation_Item item = new Preparation_Item(b, s);
            items.add(item);
        }

        arrayText.recycle();
    }
}
