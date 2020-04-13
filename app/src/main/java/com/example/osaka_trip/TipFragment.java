package com.example.osaka_trip;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class TipFragment extends Fragment {

    SQLiteDatabase ReadDB;

    ListView listview;

    ArrayList<TripInfo> arrayList = new ArrayList<>(); // 팁객체

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tip, container, false);

        listview = v.findViewById(R.id.listView);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setTitle("여행 팁");

        ReadDB = getActivity().openOrCreateDatabase("osakaTrip17.db", MODE_PRIVATE, null);

        arrayList.clear();

        Cursor c = ReadDB.rawQuery("SELECT * FROM Info WHERE classify ='팁'", null);

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

        String[] kor = new String[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            kor[i] = arrayList.get(i).getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, kor);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), TipActivity.class);
                intent.putExtra("tripinfo", arrayList.get(position));
                startActivity(intent);

            }
        });

        return v;
    }
}
