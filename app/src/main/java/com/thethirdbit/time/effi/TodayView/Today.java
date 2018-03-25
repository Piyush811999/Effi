package com.thethirdbit.time.effi.TodayView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.thethirdbit.time.effi.Database.DatabaseHelper;
import com.thethirdbit.time.effi.FragmentBase.BaseFragment;
import com.thethirdbit.time.effi.R;
import com.thethirdbit.time.effi.TimeTable.activity;

import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;

public class Today extends BaseFragment {
    private TextView day;
    private TextView date;
    private DatabaseHelper mHelper;
    private TextView productiveHours;
    private TextView socialHours;
    private ImageButton plusProductive;
    private ImageButton minusProductive;
    private ImageButton plusSocial;
    private ImageButton minusSocial;
    private TextView freeHours;
    private Boolean exit = false;
    private EditText notes;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Today() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Today.
     */
    // TODO: Rename and change types and number of parameters
    public static Today newInstance(String param1, String param2) {
        Today fragment = new Today();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_today, container, false);

        View view = inflater.inflate(R.layout.fragment_today, container, false);




        productiveHours = view.findViewById(R.id.productiveTimeView);
        socialHours = view.findViewById(R.id.socialTimeView);
        plusProductive = view.findViewById(R.id.plusProductiveTime);
        minusProductive = view.findViewById(R.id.minusProuctiveTime);
        plusSocial = view.findViewById(R.id.plusSocialTime);
        minusSocial = view.findViewById(R.id.minusSocialTime);
        freeHours = view.findViewById(R.id.freeHourView);
        notes = view.findViewById(R.id.notes);


        date = view.findViewById(R.id.date);
        day = view.findViewById(R.id.dayView);
        mHelper = new DatabaseHelper(this.getContext());

        Calendar calendar = Calendar.getInstance();
        int day1 = calendar.get(Calendar.DAY_OF_WEEK);
        int dateNumber = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        final String formatedDate = dateFormat.format(new Date(java.lang.System.currentTimeMillis()).getTime());

        date.setText(formatedDate);
        day.setText(dayName(day1));
        freeHours.setText(String.valueOf((double) (1440 - BusyMinutes(dayName(day1))) / 60));


        if (!CheckIfDataAlreadyInDBorNot(dayInfo.TABLE_NAME, dayInfo.dayInfoEntery.COL_date, formatedDate)) {
            SQLiteDatabase db = mHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(dayInfo.dayInfoEntery.COL_date, formatedDate);
            values.put(dayInfo.dayInfoEntery.COL_freetime, 1440 - BusyMinutes(dayName(day1)));
            values.put(dayInfo.dayInfoEntery.COL_productiveTime, 0);
            values.put(dayInfo.dayInfoEntery.COL_socialTime, 0);
            db.insert(dayInfo.TABLE_NAME, null, values);
            productiveHours.setText("0");
            socialHours.setText("0");
        }

        //update UI
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_productiveTime}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_productiveTime);

            int minute = cursor.getInt(index);
            cursor.close();

            int newMinute = minute;
            double hours = (double) newMinute / 60;
            productiveHours.setText(String.valueOf(hours));

        }
        //SQLiteDatabase db = mHelper.getReadableDatabase();
        cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_socialTime}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_socialTime);

            int minute = cursor.getInt(index);
            cursor.close();

            int newMinute = minute;
            double hours = (double) newMinute / 60;
            socialHours.setText(String.valueOf(hours));
        }


        plusProductive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_productiveTime}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_productiveTime);

                    int minute = cursor.getInt(index);
                    cursor.close();

                    int newMinute = minute + 15;
                    double hours = (double) newMinute / 60;


                    SQLiteDatabase db1 = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(dayInfo.dayInfoEntery.COL_productiveTime, newMinute);
                    db1.update(dayInfo.TABLE_NAME, values, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null);
                    productiveHours.setText(String.valueOf(hours));
                }


            }
        });

        minusProductive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_productiveTime}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_productiveTime);

                    int minute = cursor.getInt(index);
                    cursor.close();

                    int newMinute = minute - 15;
                    double hours = (double) newMinute / 60;


                    SQLiteDatabase db1 = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(dayInfo.dayInfoEntery.COL_productiveTime, newMinute);
                    db1.update(dayInfo.TABLE_NAME, values, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null);
                    productiveHours.setText(String.valueOf(hours));
                }


            }
        });

        plusSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_socialTime}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_socialTime);

                    int minute = cursor.getInt(index);
                    cursor.close();

                    int newMinute = minute + 15;
                    double hours = (double) newMinute / 60;


                    SQLiteDatabase db1 = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(dayInfo.dayInfoEntery.COL_socialTime, newMinute);
                    db1.update(dayInfo.TABLE_NAME, values, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null);
                    socialHours.setText(String.valueOf(hours));
                }

            }
        });

        minusSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_socialTime}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_socialTime);

                    int minute = cursor.getInt(index);
                    cursor.close();

                    int newMinute = minute - 15;
                    double hours = (double) newMinute / 60;


                    SQLiteDatabase db1 = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(dayInfo.dayInfoEntery.COL_socialTime, newMinute);
                    db1.update(dayInfo.TABLE_NAME, values, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null);
                    socialHours.setText(String.valueOf(hours));
                }

            }
        });



        return view;





    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // finish activity
        } else {
            Toast.makeText(this.getContext(), "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        final String formatedDate = dateFormat.format(new Date(java.lang.System.currentTimeMillis()).getTime());

        String newNotes = String.valueOf(notes.getText());


        SQLiteDatabase db1 = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dayInfo.dayInfoEntery.COL_Notes, newNotes);
        db1.update(dayInfo.TABLE_NAME, values, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null);



        super.onPause();
    }

    @Override
    public void onResume() {
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        final String formatedDate = dateFormat.format(new Date(java.lang.System.currentTimeMillis()).getTime());
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_Notes}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_Notes);

            String Notes = cursor.getString(index);
            cursor.close();


            String newNotes = String.valueOf(notes.getText());
            notes.setText(Notes);
            notes.setSelection(notes.getText().length());
            super.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        final String formatedDate = dateFormat.format(new Date(java.lang.System.currentTimeMillis()).getTime());
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(dayInfo.TABLE_NAME, new String[]{dayInfo.dayInfoEntery.COL_Notes}, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_Notes);

            String Notes = cursor.getString(index);
            cursor.close();


            String newNotes = String.valueOf(notes.getText());


            SQLiteDatabase db1 = mHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(dayInfo.dayInfoEntery.COL_Notes, newNotes);
            db1.update(dayInfo.TABLE_NAME, values, dayInfo.dayInfoEntery.COL_date + "=" + sant(formatedDate), null);
            notes.setText(Notes);

            //Toast.makeText(this.getContext(), Notes, Toast.LENGTH_SHORT).show();
        }


        super.onDestroyView();
    }

    private String dayName(int dayInt) {
        switch (dayInt) {

            case Calendar.SUNDAY:
                return "Sunday";


            case Calendar.MONDAY:
                return "Monday";


            case Calendar.TUESDAY:
                return "Tuesday";


            case Calendar.WEDNESDAY:
                return "Wednesday";


            case Calendar.THURSDAY:
                return "Thursday";


            case Calendar.FRIDAY:
                return "Friday";


            case Calendar.SATURDAY:
                return "Saturday";


            default:
                return "error";

        }
    }

    private String sant(String str) {
        return DatabaseUtils.sqlEscapeString(str);
    }


    public int BusyMinutes(String thisday) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + activity.activityEntery.COL_duration + ") as Total FROM " + activity.TABLE_NAME + " WHERE " + activity.activityEntery.COL_day_name + " = " + sant(thisday), null);

        if (cursor.moveToFirst()) {

            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            cursor.close();
            return total;
        } else return 0;


    }

    public boolean CheckIfDataAlreadyInDBorNot(String TableName,
                                               String dbfield, String fieldValue) {
        SQLiteDatabase sqldb = mHelper.getReadableDatabase();
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + sant(fieldValue);
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


}


