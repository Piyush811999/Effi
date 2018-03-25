package com.thethirdbit.time.effi.DailyLog;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.thethirdbit.time.effi.R;

/**
 * Created by piyush on 25/3/18.
 */

public class DailyInfo extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.daily_info);
    }
}
