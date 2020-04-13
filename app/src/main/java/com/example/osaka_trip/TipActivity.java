package com.example.osaka_trip;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TipActivity extends AppCompatActivity {

    TextView tv1, tv2;

    TripInfo tripInfo;

    CheckBox btn_selector;

    SQLiteDatabase ReadDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        ReadDB = openOrCreateDatabase("osakaTrip17.db", MODE_PRIVATE, null);

        tripInfo = (TripInfo) getIntent().getSerializableExtra("tripinfo");

        ImageView imageView = findViewById(R.id.imageView);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        btn_selector = findViewById(R.id.btn_selector);

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

        int lid = this.getResources().getIdentifier(tripInfo.getImage(), "drawable", this.getPackageName());

        imageView.setImageResource(lid);
        tv1.setText(tripInfo.getTitle());
        tv2.setText(tripInfo.getExp());
    }
}
