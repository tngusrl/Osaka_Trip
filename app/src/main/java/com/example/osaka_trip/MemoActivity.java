package com.example.osaka_trip;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {

    SQLiteDatabase db;

    TextView startTime, endTime;

    EditText title;
    EditText content;

    Button ok, cancel;

    int start_h = 8, start_m = 0; // 시간, 분
    int end_h = 9, end_m = 0; // 시간, 분

    String schedule; // 제목
    String memo; // 내용

    int day, month, year; // 일, 월, 년도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        db = this.openOrCreateDatabase("osakaTrip5.db", MODE_PRIVATE, null);

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);

        Intent intent = getIntent();

        day = intent.getIntExtra("day", 0);
        month = intent.getIntExtra("month", 0);
        year = intent.getIntExtra("year", 0);


        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowStartTime();

            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowEndTime();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                schedule = title.getText().toString();
                memo = content.getText().toString();


                if (schedule.equals(""))
                    schedule = "내 일정";

                if (memo.equals(""))
                    memo = "메모 없음";


                // 스케쥴 db에 메모도 저장할 수 있게 만들고 리스트에서 클릭하면 수정창으로 뜨게
                // 대신 같은 xml 의 수정창을 만들어서 이때는 수정되는걸로 만들기
                db = openOrCreateDatabase("osakaTrip5.db", MODE_PRIVATE, null);
                db.execSQL("INSERT INTO Schedule (year,month,day,content,memo,startHour,startMin,endHour,endMin) Values ('" + year + "','" + month + "','" + day + "','" + schedule + "','" + memo + "','" + start_h + "','" + start_m + "','" + end_h + "','" + end_m + "');");

                db.close();

                // sqlite DB에 저장 => finish로 메모리스트 다이얼로그로 이동 => DB변화 불러와서 리스트 출력
                // DB 저장내용 => 년도, 월, 일, 제목, 메모, 시작시간, 시작분, 종료시간, 종료분
                setResult(2);
                finish();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void ShowStartTime() {
        TimePickerDialog tpd = new TimePickerDialog(this, 3, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                start_h = hourOfDay;
                start_m = minute;

                if (start_h >= 10) {

                    if (start_m < 10) {
                        startTime.setText(start_h + ":0" + start_m);
                    } else
                        startTime.setText(start_h + ":" + start_m);

                } else {

                    if (start_m < 10) {
                        startTime.setText("0" + start_h + ":0" + start_m);
                    } else
                        startTime.setText("0" + start_h + ":" + start_m);
                }

            }

        }, start_h, start_m, false);
        tpd.setMessage("일정 시작 시간");
        tpd.show();

    }

    void ShowEndTime() {
        TimePickerDialog tpd = new TimePickerDialog(this, 3, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                end_h = hourOfDay;
                end_m = minute;

                if (end_h >= 10) {

                    if (end_m < 10) {
                        endTime.setText(end_h + ":0" + end_m);
                    } else
                        endTime.setText(end_h + ":" + end_m);

                } else {

                    if (end_m < 10) {
                        endTime.setText("0" + end_h + ":0" + end_m);
                    } else
                        endTime.setText("0" + end_h + ":" + end_m);
                }

            }

        }, end_h, end_m, false);
        tpd.setMessage("일정 종료 시간");
        tpd.show();

    }
}
