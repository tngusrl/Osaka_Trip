package com.example.osaka_trip;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class VersionFragment extends Fragment {


    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_version, container, false);

        ActionBar ab = ((AppCompatActivity) getActivity ()). getSupportActionBar() ;
        ab.setTitle("업데이트 버전") ;

        text1 = v.findViewById(R.id.text1);
        text2 = v.findViewById(R.id.text2);
        text3 = v.findViewById(R.id.text3);
        text4 = v.findViewById(R.id.text4);

        text1.setText("Ver 1.0.1  2019-12-28");
        text2.setText(Html.fromHtml(" - 구글 플레이스토어에 첫 출시되었습니다." + "<br />" + " - 이후 개발자가 발견하는 버그, 사용하시면서 불편한 사항 및 오류사항을 알려주시면 빠르게 수정하도록 하겠습니다." + "<br />" + " - 버그, 불편한 사항 및 오류사항은 구글 플레이스토어 답글이나 개발자 이메일 (tngusrl@gmail.com)으로 보내주세요."));

        text3.setText("Ver 1.0.2  2019-12-30");
        text4.setText(Html.fromHtml(" - 여러가지 문제사항을 해결했습니다." + "<br />" + " - UI 편의사항을 수정하였습니다. "));


        return v;
    }


}


