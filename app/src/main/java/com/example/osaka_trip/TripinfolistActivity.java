package com.example.osaka_trip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class TripinfolistActivity extends AppCompatActivity {

    ListView listview;

    ArrayList<TripInfo> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripinfolist);

        listview = findViewById(R.id.listView);

        arrayList = (ArrayList<TripInfo>) getIntent().getSerializableExtra("tripinfo");

        String[] title = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            title[i] = arrayList.get(i).getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, title);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), TipActivity.class);
                intent.putExtra("tripinfo", arrayList.get(position));
                startActivity(intent);

            }
        });
    }
}
