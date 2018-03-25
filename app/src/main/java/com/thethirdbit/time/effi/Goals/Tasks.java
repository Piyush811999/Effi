package com.thethirdbit.time.effi.Goals;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.thethirdbit.time.effi.Database.DatabaseHelper;
import com.thethirdbit.time.effi.FragmentBase.BaseFragment;
import com.thethirdbit.time.effi.R;

import java.util.ArrayList;
import java.util.List;


public class Tasks extends BaseFragment {

    private DatabaseHelper mHelper;
    private RecyclerView mTaskListView;
    private RecyclerView.Adapter R_adapter;
    private List<ListItems> listItems;
    private ImageButton deleteButton;
    private Boolean exit = false;
    Context context = getActivity();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tasks.
     */
    // TODO: Rename and change types and number of parameters
    public static Tasks newInstance(String param1, String param2) {
        Tasks fragment = new Tasks();
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
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

       Toolbar toolbar = (Toolbar) view.findViewById(R.id.topgoals);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        CircularProgressBar circularProgressBar = view.findViewById(R.id.testProgress);

        mHelper = new DatabaseHelper(this.getContext());
        mTaskListView= view.findViewById(R.id.list_todo);
        mTaskListView.setHasFixedSize(true);
        mTaskListView.setLayoutManager(new LinearLayoutManager(this.getContext()));





        listItems = new ArrayList<ListItems>();

        updateUI();

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

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(Task.TaskEntry.TABLE,new String[] {Task.TaskEntry.COL_TASK_TITLE},null,null,null,null,null);
        listItems.clear();

        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(Task.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(index));
            ListItems item = new ListItems(cursor.getString(index));
            listItems.add(item);
        }


        R_adapter = new RecyclerAdapterGoals(this.getContext(), listItems);
        mTaskListView.setAdapter(R_adapter);
        cursor.close();
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_sub_add :
                final EditText taskText = new EditText(this.getContext());
                AlertDialog dialog = new AlertDialog.Builder(this.getContext())
                        .setTitle("NEW GOAL")
                        .setMessage("Add a new goal")
                        .setView(taskText)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(Task.TaskEntry.COL_TASK_TITLE,task);
                                db.insert(Task.TaskEntry.TABLE,null,values);                //<
                                updateUI();
                                db.close();

                            }
                        })
                        .setNegativeButton("CANCLE",null)
                        .create();
                dialog.show();
                return true;
            //  case R.id.action_delete :

            default:
                return super.onOptionsItemSelected(item);
        }
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_goals, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

//    @Override
//    public void onBackPressed() {
//        if (exit) {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            // finish activity
//        } else {
//            Toast.makeText(this, "Press Back again to Exit.",
//                    Toast.LENGTH_SHORT).show();
//            exit = true;
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exit = false;
//                }
//            }, 3 * 1000);
//
//
//
//    }


}
