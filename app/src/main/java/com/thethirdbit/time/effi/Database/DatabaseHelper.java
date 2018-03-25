package com.thethirdbit.time.effi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thethirdbit.time.effi.Goals.SubTask;
import com.thethirdbit.time.effi.Goals.Task;
import com.thethirdbit.time.effi.TimeTable.activity;
import com.thethirdbit.time.effi.TodayView.dayInfo;

/**
 * Created by piyush on 5/2/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public  DatabaseHelper(Context context) {

        super(context,"Time.db", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Task.TaskEntry.TABLE  + " ( " + Task.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Task.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
        String CREATE_SUB_TABLE ="CREATE TABLE " + SubTask.SubTaskEntry.TABLE + " ( " + SubTask.SubTaskEntry._ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + SubTask.SubTaskEntry.COL_TASK_TITLE +" TEXT NOT NULL, " + SubTask.SubTaskEntry.COL_GOAL_TITLE + " TEXT NOT NULL);";
        db.execSQL(CREATE_SUB_TABLE);
        String CREATE_ACTIVITY_TABLE = "CREATE TABLE " + activity.TABLE_NAME + " ( " + activity.activityEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + activity.activityEntery.COL_activity_TITLE +" TEXT NOT NULL, " + activity.activityEntery.COL_activity_location + " TEXT, " + activity.activityEntery.COL_duration+ " INTEGER," + activity.activityEntery.COL_day_name +" TEXT NOT NULL, " + activity.activityEntery.COL_from_hour + " INTEGER," + activity.activityEntery.COL_from_min + " INTEGER," +activity.activityEntery.COL_to_hour + " INTEGER,"+ activity.activityEntery.COL_to_min + " INTEGER);";
        db.execSQL(CREATE_ACTIVITY_TABLE);
        String CREATE_DAYINFO_TABLE = "create table " + dayInfo.TABLE_NAME + " ( " + dayInfo.dayInfoEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + dayInfo.dayInfoEntery.COL_date + " TEXT NOT NULL, " + dayInfo.dayInfoEntery.COL_Notes + " TEXT," + dayInfo.dayInfoEntery.COL_freetime + " INTEGER, " + dayInfo.dayInfoEntery.COL_productiveTime + " INTEGER, " + dayInfo.dayInfoEntery.COL_socialTime + " INTEGER);";
        db.execSQL(CREATE_DAYINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Task.TaskEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SubTask.SubTaskEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + activity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + dayInfo.TABLE_NAME);
        onCreate(db);

    }
}
