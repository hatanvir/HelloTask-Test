package com.tvr.easynote.features.view.bottom_nav_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tvr.easynote.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarFragment extends Fragment {
    @BindView(R.id.welcomeTv)
    TextView welcomeTv;

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
}