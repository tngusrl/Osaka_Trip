package com.example.osaka_trip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ConvListActivity extends AppCompatActivity {

    ListView listview;

    ArrayList<Conversation> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv_list);

        listview = findViewById(R.id.listView);

        arrayList = (ArrayList<Conversation>) getIntent().getSerializableExtra("conv");

        String[] kor = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            kor[i] = arrayList.get(i).getKor();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kor);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ConvActivity.class);
                intent.putExtra("conv", arrayList.get(position));
                startActivity(intent);

            }
        });


    }
}
