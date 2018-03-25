package com.thethirdbit.time.effi.Goals;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thethirdbit.time.effi.Database.DatabaseHelper;
import com.thethirdbit.time.effi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piyush on 6/2/18.
 */

public class SubTasksActivity extends AppCompatActivity {
    private static DatabaseHelper mHelper;
    private static String GoalName;
    private static RecyclerView.Adapter SR_adapter;
    private Bundle extras;
    private static RecyclerView mSubTaskListView;
    private static List<SubListItems> sublistItems;
    private TextView goal;
    private CheckBox checkBox;
    private ImageButton deleteButton;
    private static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sub_tasks);


        mHelper = new DatabaseHelper(this);
        extras = getIntent().getExtras();
        if (extras != null){
            GoalName = extras.getString("task");
        }

        goal = findViewById(R.id.goalName);




        SubTasksActivity.context = getApplicationContext();







        extras= getIntent().getExtras();

        if (extras != null){
            goal.setText(extras.getString("task"));
        }


        mSubTaskListView = findViewById(R.id.Sub_list_todo);
        mSubTaskListView.setHasFixedSize(true);
        mSubTaskListView.setLayoutManager(new LinearLayoutManager(this));

        sublistItems = new ArrayList<SubListItems>();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddSubTasks);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText subtaskText = new EditText(SubTasksActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(SubTasksActivity.this)
                        .setTitle("Add Sub-Task")
                        .setMessage("Add a new sub-task")
                        .setView(subtaskText)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String subtask = String.valueOf(subtaskText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(SubTask.SubTaskEntry.COL_TASK_TITLE,subtask);
                                values.put(SubTask.SubTaskEntry.COL_GOAL_TITLE,GoalName);
                                db.insert(SubTask.SubTaskEntry.TABLE,null,values);               //<
                                updateUI();
                                db.close();

                            }
                        })
                        .setNegativeButton("CANCEL",null)
                        .create();
                dialog.show();

            }
        });


//        try {
//            deleteButton = findViewById(R.id.delete_sub_task);
//
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View v) {
//
//
//                    deleteTask(v);
//                    Log.d("bUTTON","WORKS");
//                }
//            });
//
//        }catch (NullPointerException e)
//        {
//
//        }
//


        updateUI();


    }



    public static void updateUI() {
        ArrayList<String> subtaskList = new ArrayList<>();
        mHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor =db.query(SubTask.SubTaskEntry.TABLE,new String[] {SubTask.SubTaskEntry.COL_TASK_TITLE},SubTask.SubTaskEntry.COL_GOAL_TITLE+" = "+sant(GoalName),null,null,null,null);

        sublistItems.clear();


        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(SubTask.SubTaskEntry.COL_TASK_TITLE);
            subtaskList.add(cursor.getString(index));
            SubListItems item = new SubListItems(cursor.getString(index));
            sublistItems.add(item);
        }

        SR_adapter = new RecyclerAdapterSubTasks(context,sublistItems);
        mSubTaskListView.setAdapter(SR_adapter);
        cursor.close();
    }

    private static String sant(String str) {
        return DatabaseUtils.sqlEscapeString(str);
    }

    public  void  deleteTask(View view){
        View parent = (View)view.getParent();

        final TextView taskText = (TextView) parent.findViewById(R.id.title_subtask);

        AlertDialog dialog1 = new AlertDialog.Builder(this)
                .setTitle("DELETE GOAL")
                .setMessage("Are you sure you want to delete this goal")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String task = String.valueOf(taskText.getText());
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        db.delete(SubTask.SubTaskEntry.TABLE,SubTask.SubTaskEntry.COL_TASK_TITLE + " = ?",new String[] {task});
                        db.close();
                        updateUI();





                    }
                })
                .setNegativeButton("NO",null)
                .create();
        dialog1.show();

    }

    public static Context getAppContext() {
        return SubTasksActivity.context;
    }





}
