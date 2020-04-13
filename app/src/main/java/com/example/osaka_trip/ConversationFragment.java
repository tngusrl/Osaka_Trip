package com.example.osaka_trip;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ConversationFragment extends Fragment {

    LinearLayout btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    SQLiteDatabase ReadDB;

    ArrayList<Conversation> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_conversation, container, false);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setTitle("회화");

        ReadDB = getActivity().openOrCreateDatabase("osakaTrip10.db", MODE_PRIVATE, null);

        btn1 = v.findViewById(R.id.btn1); // 공항
        btn2 = v.findViewById(R.id.btn2); // 교통
        btn3 = v.findViewById(R.id.btn3); // 관광
        btn4 = v.findViewById(R.id.btn4); // 식당
        btn5 = v.findViewById(R.id.btn5); // 쇼핑
        btn6 = v.findViewById(R.id.btn6); // 호텔
        btn7 = v.findViewById(R.id.btn7); // 병원/약국
        btn8 = v.findViewById(R.id.btn8); // 긴급상황

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='공항'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='교통'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='관광'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='음식점'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='쇼핑'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='호텔'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='약국/병원'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Conv WHERE class ='긴급상황'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String classification = c.getString(1);
                            String kor = c.getString(2);
                            String jap = c.getString(3);
                            String pronun = c.getString(4);
                            String expect = c.getString(5);

                            Conversation conv = new Conversation(idx, classification, kor, jap, pronun, expect);

                            arrayList.add(conv);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), ConvListActivity.class);
                intent.putExtra("conv", arrayList);
                startActivity(intent);
            }
        });


        return v;
    }

}
