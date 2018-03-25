package com.thethirdbit.time.effi.Goals;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.thethirdbit.time.effi.Database.DatabaseHelper;
import com.thethirdbit.time.effi.R;

import java.util.List;

/**
 * Created by piyush on 6/2/18.
 */

public class RecyclerAdapterSubTasks extends RecyclerView.Adapter<RecyclerAdapterSubTasks.ViewHolder> {
    private Context context;
    private List<SubListItems> SublistItems;
    private DatabaseHelper mHelper;

    public RecyclerAdapterSubTasks(Context context, List<SubListItems> sublistItems) {
        this.context = context;
        SublistItems = sublistItems;
    }

    @Override
    public RecyclerAdapterSubTasks.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_subtask,parent,false);
        return new RecyclerAdapterSubTasks.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterSubTasks.ViewHolder holder, final int position) {
        final SubListItems items = SublistItems.get(position);
        holder.title.setText(items.getSubtask());

        mHelper = new DatabaseHelper(context);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                AlertDialog dialog1 = new AlertDialog.Builder(v.getContext())
                        .setTitle("DELETE SUB-TASK")
                        .setMessage("Are you sure you want to delete this sub-task")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                String task = items.getSubtask();
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                db.delete(SubTask.SubTaskEntry.TABLE,SubTask.SubTaskEntry.COL_TASK_TITLE + " = ?",new String[] {task});
                                SubTasksActivity.updateUI();


                            }
                        })
                        .setNegativeButton("NO",null)
                        .create();
                dialog1.show();



                            }

        });

    }

    @Override
    public int getItemCount() {

        return SublistItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageButton button;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_subtask);
            button = itemView.findViewById(R.id.delete_sub_task);
        }
    }
}
