package com.thethirdbit.time.effi.Analysis;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.thethirdbit.time.effi.Database.DatabaseHelper;
import com.thethirdbit.time.effi.FragmentBase.BaseFragment;
import com.thethirdbit.time.effi.R;
import com.thethirdbit.time.effi.TodayView.dayInfo;

import java.util.ArrayList;


public class Analysis extends BaseFragment {

    private Boolean exit = false;
    private DatabaseHelper mHelper;
    PieChart pieChartYesterday;
    PieChart pieChartWeekly;
    PieChart pieChartMonthly;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public Analysis() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Analysis newInstance(String param1, String param2) {
        Analysis fragment = new Analysis();
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
        //return inflater.inflate(R.layout.fragment_analysis, container, false);

        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        mHelper = new DatabaseHelper(this.getContext());
        pieChartYesterday = (PieChart)view.findViewById(R.id.chartYesterday);
        pieChartYesterday.setRotationEnabled(true);
        pieChartWeekly=(PieChart)view.findViewById(R.id.chartLastWeek);
        pieChartWeekly.setRotationEnabled(true);
        pieChartMonthly=(PieChart)view.findViewById(R.id.chartLastMonth);
        pieChartMonthly.setRotationEnabled(true);


        Description data = new Description();
        data.setText("");

        pieChartYesterday.setDescription(data);
        pieChartWeekly.setDescription(data);
        pieChartMonthly.setDescription(data);

        //adding todays data
        float[] yData = {sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,1),sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,1),(sumOFcolumn(dayInfo.dayInfoEntery.COL_freetime,1)-sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,1)-sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,1))};
        String[] xData = {"Productive", "Social" , "Others" };

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }


        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }



        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Statics");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(0);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#42a5f5"));
        colors.add(Color.parseColor("#9ccc65"));
        colors.add(Color.parseColor("#bbbbbb"));


        pieDataSet.setColors(colors);
        Legend legend = pieChartYesterday.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChartYesterday.setData(pieData);
        pieChartYesterday.invalidate();


        float[] yData1 = {(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,7),(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,7),(float)(sumOFcolumn(dayInfo.dayInfoEntery.COL_freetime,7)-sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,7)-sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,7))};
        String[] xData1 = {"Productive", "Social" , "Others" };

        ArrayList<PieEntry> yEntrys1 = new ArrayList<>();
        ArrayList<String> xEntrys1 = new ArrayList<>();

        for(int i = 0; i < yData1.length; i++){
            yEntrys1.add(new PieEntry(yData1[i] , i));
        }


        for(int i = 1; i < xData1.length; i++){
            xEntrys1.add(xData1[i]);
        }



        //create the data set
        PieDataSet pieDataSet1 = new PieDataSet(yEntrys1, "Statics");
        pieDataSet1.setSliceSpace(2);
        pieDataSet1.setValueTextSize(0);

        //add colors to dataset

        colors.add(Color.parseColor("#1976d2"));
        colors.add(Color.parseColor("#2E7D32"));
        colors.add(Color.parseColor("#EEEEEE"));


        pieDataSet1.setColors(colors);
        Legend legend1 = pieChartWeekly.getLegend();
        legend1.setForm(Legend.LegendForm.CIRCLE);

        legend1.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


        //create pie data object
        PieData pieData1 = new PieData(pieDataSet1);
        pieChartWeekly.setData(pieData1);
        pieChartWeekly.invalidate();

        float[] yData2 = {(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,30),(float)sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,30),(float)(sumOFcolumn(dayInfo.dayInfoEntery.COL_freetime,30)-sumOFcolumn(dayInfo.dayInfoEntery.COL_productiveTime,30)-sumOFcolumn(dayInfo.dayInfoEntery.COL_socialTime,30))};
        String[] xData2 = {"Productive", "Social" , "Others" };

        ArrayList<PieEntry> yEntrys2 = new ArrayList<>();
        ArrayList<String> xEntrys2 = new ArrayList<>();

        for(int i = 0; i < yData2.length; i++){
            yEntrys2.add(new PieEntry(yData2[i] , i));
        }


        for(int i = 1; i < xData2.length; i++){
            xEntrys2.add(xData2[i]);
        }



        //create the data set
        PieDataSet pieDataSet2 = new PieDataSet(yEntrys2, "Statics");
        pieDataSet2.setSliceSpace(2);
        pieDataSet2.setValueTextSize(0);

        //add colors to dataset

        colors.add(Color.parseColor("#1976d2"));
        colors.add(Color.parseColor("#2E7D32"));
        colors.add(Color.parseColor("#EEEEEE"));


        pieDataSet2.setColors(colors);
        Legend legen2 = pieChartMonthly.getLegend();
        legen2.setForm(Legend.LegendForm.CIRCLE);

        legen2.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);


        //create pie data object
        PieData pieData2 = new PieData(pieDataSet2);
        pieChartMonthly.setData(pieData2);
        pieChartMonthly.invalidate();




        return view;

    }


    private int sumOFcolumn(String col_name,int limit){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + col_name + ") as Total FROM ( select * from "+ dayInfo.TABLE_NAME+" order by "+dayInfo.dayInfoEntery._ID+ " desc limit " +String.valueOf(limit) +")",null);

        if (cursor.moveToFirst()) {

            int total = cursor.getInt(cursor.getColumnIndex("Total"));
            cursor.close();
            return total;
        }
        else return 0;

    }
    private String sant(String str) {
        return DatabaseUtils.sqlEscapeString(str);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
