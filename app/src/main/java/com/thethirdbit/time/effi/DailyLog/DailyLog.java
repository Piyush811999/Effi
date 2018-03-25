package com.thethirdbit.time.effi.DailyLog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.thethirdbit.time.effi.Database.DatabaseHelper;
import com.thethirdbit.time.effi.FragmentBase.BaseFragment;
import com.thethirdbit.time.effi.R;
import com.thethirdbit.time.effi.TodayView.dayInfo;

import java.util.ArrayList;
import java.util.List;


public class DailyLog extends BaseFragment {
    private DatabaseHelper mHelper;
    private RecyclerView mTaskListView;
    private RecyclerView.Adapter R_adapter;
    private List<dailylogItems> listItems;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DailyLog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyLog.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyLog newInstance(String param1, String param2) {
        DailyLog fragment = new DailyLog();
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


        View view = inflater.inflate(R.layout.fragment_daily_log, container, false);



        mTaskListView= view.findViewById(R.id.dailylog);
        mTaskListView.setHasFixedSize(true);
        mTaskListView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listItems = new ArrayList<>();

        mHelper = new DatabaseHelper(this.getContext());

        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(dayInfo.TABLE_NAME ,new String[] {dayInfo.dayInfoEntery.COL_date ,dayInfo.dayInfoEntery.COL_Notes},null,null,null,null,null);
        listItems.clear();

        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_date);
            int index1 = cursor.getColumnIndex(dayInfo.dayInfoEntery.COL_Notes);

            dailylogItems item = new dailylogItems(cursor.getString(index),cursor.getString(index1));
            listItems.add(item);
        }


        R_adapter = new RecyclerAdapterDailylog(this.getContext(), listItems);
        mTaskListView.setAdapter(R_adapter);
        cursor.close();




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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
