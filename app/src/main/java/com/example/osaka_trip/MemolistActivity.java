package com.example.osaka_trip;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MemolistActivity extends Activity {

    int day, month, year;

    ArrayList<HashMap<String, String>> 일정List;

    ListAdapter adapter;

    SQLiteDatabase ReadDB;


    FloatingActionButton floatingActionButton;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_memolist);

        floatingActionButton = findViewById(R.id.fab_main);

        listView = findViewById(R.id.listView);

        ReadDB = this.openOrCreateDatabase("osakaTrip5.db", MODE_PRIVATE, null);
        ReadDB.execSQL("CREATE TABLE IF NOT EXISTS Schedule (year VARCHAR(20),month VARCHAR(20),day VARCHAR(20),content VARCHAR(20),memo VARCHAR(20),startHour VARCHAR(20),startMin VARCHAR(20),endHour VARCHAR(20),endMin VARCHAR(20))");


        일정List = new ArrayList<HashMap<String, String>>();

        final Intent intent = getIntent();
        day = intent.getIntExtra("day", 0);
        month = intent.getIntExtra("month", 0) + 1;
        year = intent.getIntExtra("year", 0);

        showList();

        adapter = new SimpleAdapter(this, 일정List, R.layout.listview_memoitem, new String[]{"content", "time"}, new int[]{R.id.name, R.id.phone});

        listView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), MemoActivity.class);
                intent1.putExtra("day", day);
                intent1.putExtra("month", month);
                intent1.putExtra("year", year);
                startActivityForResult(intent1, 1);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> 일정 = 일정List.get(position);
                Intent intent1 = new Intent(getApplicationContext(), MemoUpdateActivity.class);
                intent1.putExtra("hashmap", 일정);
                startActivityForResult(intent1, 1);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> 일정 = 일정List.get(position);

                String year = 일정.get("year");
                String month = 일정.get("month");
                String day = 일정.get("day");
                String content = 일정.get("content");
                String memo = 일정.get("memo");
                String startHour = 일정.get("startHour");
                String startMin = 일정.get("startMin");
                String endHour = 일정.get("endHour");
                String endMin = 일정.get("endMin");

                ReadDB.execSQL("DELETE FROM Schedule WHERE year = " + year + " AND month = " + month + " AND day = " + day + " AND content = '" + content + "' AND memo = '" + memo + "' AND startHour = " + startHour + " AND startMin = " + startMin + " AND endHour = " + endHour + " AND endMin = " + endMin);
                일정List.clear();
                showList();
                ((BaseAdapter) adapter).notifyDataSetChanged();

                return false;
            }
        });


    }

    protected void showList() {

        Cursor c = ReadDB.rawQuery("SELECT * FROM Schedule WHERE year = " + year + " AND month = " + month + " AND day = " + day, null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {

                    String year = c.getString(0);
                    String month = c.getString(1);
                    String day = c.getString(2);
                    String content = c.getString(3);
                    String memo = c.getString(4);
                    String startHour = c.getString(5);
                    String startMin = c.getString(6);
                    String endHour = c.getString(7);
                    String endMin = c.getString(8);

                    String time = startHour + ":" + startMin + "~" + endHour + ":" + endMin;

                    if (Integer.parseInt(startMin) < 10 && Integer.parseInt(endMin) < 10) {
                        time = startHour + ":0" + startMin + "~" + endHour + ":0" + endMin;
                    } else if (Integer.parseInt(startMin) < 10 && Integer.parseInt(endMin) >= 10) {
                        time = startHour + ":0" + startMin + "~" + endHour + ":" + endMin;
                    } else if (Integer.parseInt(startMin) >= 10 && Integer.parseInt(endMin) < 10) {
                        time = startHour + ":" + startMin + "~" + endHour + ":0" + endMin;
                    }

                    HashMap<String, String> 일정 = new HashMap<>();

                    일정.put("year", year);
                    일정.put("month", month);
                    일정.put("day", day);
                    일정.put("content", content);
                    일정.put("memo", memo);
                    일정.put("startHour", startHour);
                    일정.put("startMin", startMin);
                    일정.put("endHour", endHour);
                    일정.put("endMin", endMin);
                    일정.put("time", time);

                    일정List.add(일정);

                } while (c.moveToNext());
            }
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {

            일정List.clear();
            showList();
            ((BaseAdapter) adapter).notifyDataSetChanged();

        }

        if (resultCode == 3) {

            일정List.clear();
            showList();
            ((BaseAdapter) adapter).notifyDataSetChanged();

        }

    }

}

