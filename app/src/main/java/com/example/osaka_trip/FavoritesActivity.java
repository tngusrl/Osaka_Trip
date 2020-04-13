package com.example.osaka_trip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    ListView listview;
    LinearLayout container;

    ArrayList<TripInfo> arrayList = new ArrayList<>();

    SQLiteDatabase ReadDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("즐겨찾기");

        ReadDB = openOrCreateDatabase("osakaTrip17.db", MODE_PRIVATE, null);

        listview = findViewById(R.id.listView);

        arrayList.clear();

        Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE favorite ='o'", null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    int idx = c.getInt(0);
                    String title = c.getString(1);
                    String area = c.getString(2);
                    String classify = c.getString(3);
                    String image = c.getString(4);
                    String image1 = c.getString(5);
                    String exp = c.getString(6);
                    String link = c.getString(7);
                    String favorite = c.getString(8);

                    TripInfo trip = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                    arrayList.add(trip);

                } while (c.moveToNext());

                String[] kor = new String[arrayList.size()];

                for (int i = 0; i < arrayList.size(); i++) {
                    kor[i] = arrayList.get(i).getTitle();
                }

                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kor);
                listview.setAdapter(adapter);
            } else {

                listview.setVisibility(View.GONE);

                //부모 뷰
                container = findViewById(R.id.linear);

                //TextView 생성
                TextView view1 = new TextView(this);
                view1.setText("즐겨찾기한 항목이 없습니다.");
                view1.setTextSize(20);
                view1.setTextColor(Color.BLACK);

                //layout_width, layout_height, gravity 설정
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER;
                view1.setLayoutParams(lp);

                //부모 뷰에 추가
                container.addView(view1);

            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), TripinfoActivity.class);
                intent.putExtra("tripinfo", arrayList.get(position));
                startActivity(intent);

            }
        });
    }
}
