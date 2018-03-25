package com.thethirdbit.time.effi.FramentTimetable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thethirdbit.time.effi.R;


public class Monday extends DailyBase {

    public Monday() {

//        Toast.makeText(this.getContext(),"this",Toast.LENGTH_SHORT).show();
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(this.getContext(),"monday",Toast.LENGTH_SHORT).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
