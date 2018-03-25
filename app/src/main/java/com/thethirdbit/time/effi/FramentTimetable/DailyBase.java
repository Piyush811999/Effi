package com.thethirdbit.time.effi.FramentTimetable;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.thethirdbit.time.effi.Goals.ListItems;
import com.thethirdbit.time.effi.R;
import com.thethirdbit.time.effi.views.RecyclerAdapterTimetable;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class DailyBase extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItems> listItems;
    //private NestedScrollView mScrollView;

    //private OnFragmentInteractionListener mListener;

    public DailyBase() {
        // Required empty public constructor
    }
    public static DailyBase newInstance(){
        DailyBase fragment=new DailyBase();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_daily_base, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.timetable_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems = new ArrayList<>();

        for (int i= 0;i<10;i++){
            ListItems item = new ListItems(
                    "Item "+ (i) ,
                    "Description " + i
            );

            listItems.add(item);
        }

        adapter = new RecyclerAdapterTimetable(this.getContext(), listItems);
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

}
