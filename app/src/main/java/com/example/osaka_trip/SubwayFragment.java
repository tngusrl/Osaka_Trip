package com.example.osaka_trip;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

import androidx.fragment.app.Fragment;

public class SubwayFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_subway, container, false);

        PhotoView photoView = v.findViewById(R.id.subway);

        photoView.setImageResource(R.drawable.subway);

        return v;
    }
}
