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

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class FavoritesFragment extends Fragment {

    ListView listview;

    ArrayList<TripInfo> arrayList = new ArrayList<>();

    SQLiteDatabase ReadDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        ReadDB = getActivity().openOrCreateDatabase("osakaTrip17.db", MODE_PRIVATE, null);

        listview = v.findViewById(R.id.listView);

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

                Intent intent = new Intent(getActivity(), TripinfoActivity.class);
                intent.putExtra("tripinfo", arrayList.get(position));
                startActivity(intent);

            }
        });


        return v;
    }

}
