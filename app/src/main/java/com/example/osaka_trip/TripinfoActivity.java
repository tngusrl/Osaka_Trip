package com.example.osaka_trip;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TripinfoActivity extends AppCompatActivity {

    TextView tv1, tv2;

    TripInfo tripInfo;

    CheckBox btn_selector;

    Button btn_map;

    SQLiteDatabase ReadDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripinfo);

        ReadDB = openOrCreateDatabase("osakaTrip17.db", MODE_PRIVATE, null);

        tripInfo = (TripInfo) getIntent().getSerializableExtra("tripinfo");

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView1 = findViewById(R.id.imageView1);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        btn_selector = findViewById(R.id.btn_selector);

        btn_map = findViewById(R.id.btn_map);

        if (tripInfo.getFavorite().equals("o")) {

            btn_selector.setChecked(true);

        } else {
            btn_selector.setChecked(false);
        }

        btn_selector.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btn_selector.isChecked()) {

                    String sqlCreateTbl = "UPDATE Info SET favorite = 'o' WHERE idx =" + tripInfo.getIdx();
                    Toast.makeText(getApplicationContext(), "즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show();
                    ReadDB.execSQL(sqlCreateTbl);

                } else {

                    String sqlCreateTbl = "UPDATE Info SET favorite = 'x' WHERE idx =" + tripInfo.getIdx();
                    Toast.makeText(getApplicationContext(), "즐겨찾기에서 삭제되었습니다.", Toast.LENGTH_LONG).show();
                    ReadDB.execSQL(sqlCreateTbl);

                }
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("geo:34.673344, 135.494313?q=" + Uri.encode(tripInfo.getTitle().substring(0, tripInfo.getTitle().lastIndexOf("/"))));
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        gmmIntentUri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);

            }
        });

        if (tripInfo.getImage().equals("x")) {

        } else {
            int lid = this.getResources().getIdentifier(tripInfo.getImage(), "drawable", this.getPackageName());
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(lid);
        }


        if (tripInfo.getImage1().equals("x")) {

        } else {
            int lid1 = this.getResources().getIdentifier(tripInfo.getImage1(), "drawable", this.getPackageName());
            imageView1.setVisibility(View.VISIBLE);
            imageView1.setImageResource(lid1);
        }

        tv1.setText(tripInfo.getTitle());
        tv2.setText(tripInfo.getExp());

    }
}
