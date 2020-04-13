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

public class InfolistFragment extends Fragment {

    LinearLayout btn1, btn2, btn3, btn4, btn5, btn6;

    SQLiteDatabase ReadDB;

    ArrayList<TripInfo> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_infolist, container, false);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setTitle("여행정보");

        btn1 = v.findViewById(R.id.btn1); // 추천코스
        btn2 = v.findViewById(R.id.btn2); // 관광지
        btn3 = v.findViewById(R.id.btn3); // 음식점
        btn4 = v.findViewById(R.id.btn4); // 유명음식
        btn5 = v.findViewById(R.id.btn5); // 축제
        btn6 = v.findViewById(R.id.btn6); // 쇼핑리스트

        ReadDB = getActivity().openOrCreateDatabase("osakaTrip17.db", MODE_PRIVATE, null);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='추천코스'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String title = c.getString(1); // 이름
                            String area = c.getString(2); // 지역
                            String classify = c.getString(3); // 분류(음식점, 관광지, 유명음식, 축제)
                            String image = c.getString(4); // 이미지
                            String image1 = c.getString(5); // 이미지
                            String exp = c.getString(6); // 설명
                            String link = c.getString(7); // 구글맵 url
                            String favorite = c.getString(8); // 즐겨찾기

                            TripInfo tripinfo = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                            arrayList.add(tripinfo);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), TripinfolistActivity.class);
                intent.putExtra("tripinfo", arrayList);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='관광지'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String title = c.getString(1); // 이름
                            String area = c.getString(2); // 지역
                            String classify = c.getString(3); // 분류(음식점, 관광지, 유명음식, 축제)
                            String image = c.getString(4); // 이미지
                            String image1 = c.getString(5); // 이미지
                            String exp = c.getString(6); // 설명
                            String link = c.getString(7); // 구글맵 url
                            String favorite = c.getString(8); // 즐겨찾기

                            TripInfo tripinfo = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                            arrayList.add(tripinfo);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), TripinfolistActivity.class);
                intent.putExtra("tripinfo", arrayList);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='음식점'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String title = c.getString(1); // 이름
                            String area = c.getString(2); // 지역
                            String classify = c.getString(3); // 분류(음식점, 관광지, 유명음식, 축제)
                            String image = c.getString(4); // 이미지
                            String image1 = c.getString(5); // 이미지
                            String exp = c.getString(6); // 설명
                            String link = c.getString(7); // 구글맵 url
                            String favorite = c.getString(8); // 즐겨찾기

                            TripInfo tripinfo = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                            arrayList.add(tripinfo);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), TripinfolistActivity.class);
                intent.putExtra("tripinfo", arrayList);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='대표음식'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String title = c.getString(1); // 이름
                            String area = c.getString(2); // 지역
                            String classify = c.getString(3); // 분류(음식점, 관광지, 유명음식, 축제)
                            String image = c.getString(4); // 이미지
                            String image1 = c.getString(5); // 이미지
                            String exp = c.getString(6); // 설명
                            String link = c.getString(7); // 구글맵 url
                            String favorite = c.getString(8); // 즐겨찾기

                            TripInfo tripinfo = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                            arrayList.add(tripinfo);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), TripinfolistActivity.class);
                intent.putExtra("tripinfo", arrayList);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='축제'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String title = c.getString(1); // 이름
                            String area = c.getString(2); // 지역
                            String classify = c.getString(3); // 분류(음식점, 관광지, 유명음식, 축제)
                            String image = c.getString(4); // 이미지
                            String image1 = c.getString(5); // 이미지
                            String exp = c.getString(6); // 설명
                            String link = c.getString(7); // 구글맵 url
                            String favorite = c.getString(8); // 즐겨찾기

                            TripInfo tripinfo = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                            arrayList.add(tripinfo);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), TripinfolistActivity.class);
                intent.putExtra("tripinfo", arrayList);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayList.clear();

                Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='쇼핑리스트'", null);

                if (c != null) {
                    if (c.moveToFirst()) {
                        do {

                            int idx = c.getInt(0);
                            String title = c.getString(1); // 이름
                            String area = c.getString(2); // 지역
                            String classify = c.getString(3); // 분류(음식점, 관광지, 유명음식, 축제)
                            String image = c.getString(4); // 이미지
                            String image1 = c.getString(5); // 이미지
                            String exp = c.getString(6); // 설명
                            String link = c.getString(7); // 구글맵 url
                            String favorite = c.getString(8); // 즐겨찾기

                            TripInfo tripinfo = new TripInfo(idx, title, area, classify, image, image1, exp, link, favorite);

                            arrayList.add(tripinfo);

                        } while (c.moveToNext());
                    }
                }

                Intent intent = new Intent(getContext(), TripinfolistActivity.class);
                intent.putExtra("tripinfo", arrayList);
                startActivity(intent);
            }
        });

        return v;
    }
}
