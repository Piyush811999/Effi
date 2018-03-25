package com.thethirdbit.time.effi.TimeTable;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.thethirdbit.time.effi.FragmentBase.BaseFragment;
import com.thethirdbit.time.effi.FramentTimetable.DailyBase;
import com.thethirdbit.time.effi.FramentTimetable.Friday;
import com.thethirdbit.time.effi.FramentTimetable.Monday;
import com.thethirdbit.time.effi.FramentTimetable.Saturday;
import com.thethirdbit.time.effi.FramentTimetable.Sunday;
import com.thethirdbit.time.effi.FramentTimetable.Thursday;
import com.thethirdbit.time.effi.FramentTimetable.Tuesday;
import com.thethirdbit.time.effi.FramentTimetable.Wednesday;
import com.thethirdbit.time.effi.Goals.ListItems;
import com.thethirdbit.time.effi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class Timetable extends BaseFragment {
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;*/

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DayItems> dayItems;
    private Boolean exit = false;



    // TODO: Rename and change types of parameters



    public Timetable() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);


        //toolbar
       /*Toolbar toolbar=mViewPager.getToolbar();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
*/

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toptimetable);
        //setSupportActionBar(toolbar);


        recyclerView = view.findViewById(R.id.listDays);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        dayItems = new ArrayList<>();

        dayItems.add(new DayItems("Monday", "1", "2"));
        dayItems.add(new DayItems("Tuesday", null, null));
        dayItems.add(new DayItems("Wednesday", null, null));
        dayItems.add(new DayItems("Thursday", null, null));
        dayItems.add(new DayItems("Friday", null, null));
        dayItems.add(new DayItems("Saturday", null, null));
        dayItems.add(new DayItems("Sunday", null, null));

        adapter = new RecyclerAdapterDaysTimeTable(this.getContext(), dayItems);
        recyclerView.setAdapter(adapter);


        // listItems = new ArrayList<>();




       //yourAdapter = new RecyclerAdapterGoals(this.getActivity(),listItems);
    //   mRecyclerView.setAdapter(yourAdapter);

   //mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        //mRecyclerView.setAdapter(yourAdapter);





      /*  mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 7) {
                    case 0:
                       // Toast.makeText(getActivity(),"monday",Toast.LENGTH_SHORT).show();

                        return new Monday();


                    case 1:
                        //Toast.makeText(getActivity(),"tuesday",Toast.LENGTH_SHORT).show();
                        return new Tuesday();


                    case 2:
                       // Toast.makeText(getActivity(),"wednesday",Toast.LENGTH_SHORT).show();
                        return new Wednesday();

                    case 3:
                        //.makeText(getActivity(),"thursday",Toast.LENGTH_SHORT).show();
                        return new Thursday();

                    case 4:
                      //  Toast.makeText(getActivity(),"friday",Toast.LENGTH_SHORT).show();
                        return new Friday();

                    case 5:
                        //Toast.makeText(getActivity(),"saturday",Toast.LENGTH_SHORT).show();
                        return new Saturday();

                    case 6:
                       // Toast.makeText(getActivity(),"sunday",Toast.LENGTH_SHORT).show();
                        return new Sunday();

                    default:
                        return new DailyBase();
                }
            }
*/

        /*    @Override
            public int getCount() {
                return 7;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 7) {
                    case 0:
                        return "Monday";
                    case 1:
                        return "Tuesday";
                    case 2:
                        return "Wednesday";
                    case 3:
                        return "Thursday";
                    case 4:
                        return "Friday";
                    case 5:
                        return "Saturday";
                    case 6:
                        return "Sunday";
                }
                return "";
            }
        });


        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryColor,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryDarkColor,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryColor,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryDarkColor,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");

                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryColor,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 5:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryDarkColor,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 6:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.primaryColor,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getPagerTitleStrip().setTextColor(Color.GRAY);
        mViewPager.setColor(Color.BLACK,0);
        mViewPager.setBackgroundColor(Color.DKGRAY);

        */


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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        //super.onCreateOptionsMenu(menu, menuInflater);
        //return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int i=item.getItemId();
        switch (i){
            case R.id.add_time:

              //  Toast.makeText(this.getContext(),"this"+getVisibleFragment().toString(),Toast.LENGTH_SHORT).show();

                //Intent intent=new Intent(this.getContext(), PopupAddActivity.class);
                //intent.putExtra("day",)

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = Timetable.this.getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }




}
