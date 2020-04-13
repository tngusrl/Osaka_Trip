package com.example.osaka_trip;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static android.speech.tts.TextToSpeech.ERROR;

public class ConvActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    Button ttsBtn;

    TextToSpeech tts;

    Conversation conv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        ttsBtn = findViewById(R.id.ttsBtn);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {

                    if (!isPackageInstalled(getPackageManager(), "com.google.android.tts"))
                        confirmDialog();
                    tts.setLanguage(Locale.JAPANESE);
                }
            }
        }, "com.google.android.tts");

        conv = (Conversation) getIntent().getSerializableExtra("conv");

        tv1.setText(conv.getKor());
        tv2.setText(conv.getJap());
        tv3.setText(conv.getPronun());

        if (conv.getExpect() != null)
            tv4.setText(conv.getExpect());

        ttsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tts.speak(conv.getJap(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

    public static boolean isPackageInstalled(PackageManager pm, String packageName) {
        try {
            pm.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    private void confirmDialog() {
        AlertDialog.Builder d = new AlertDialog.Builder(ConvActivity.this);
        final AlertDialog ad = d.create();
        d.setTitle("음성 파일 없음!");
        d.setMessage("음성 기능을 사용하기 위한 음성 파일이 휴대폰에 없습니다. 음성파일을 다운로드 하시겠어요?");
        d.setPositiveButton("네", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                Intent installVoice = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installVoice);
            }
        });
        d.setNegativeButton("아니요, 음성기능을 사용하지 않습니다", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {

                ad.dismiss();

            }
        });
        d.show();
    }
}
