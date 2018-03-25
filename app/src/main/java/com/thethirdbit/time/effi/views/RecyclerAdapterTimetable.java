package com.thethirdbit.time.effi.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thethirdbit.time.effi.Goals.ListItems;
import com.thethirdbit.time.effi.R;

import java.util.List;

/**
 * Created by Saurabh on 05-02-2018.
 */

public class RecyclerAdapterTimetable extends RecyclerView.Adapter<RecyclerAdapterTimetable.ViewHolder> {
    private Context context;
    private String[] itemsData;
    private List<ListItems> listItems;



    public RecyclerAdapterTimetable(Context context , List listitem ){
        this.context = context;
        listItems = listitem;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);

        // create ViewHolder

       // ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return new ViewHolder(itemLayoutView);


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
        ListItems item = listItems.get(position);

        //viewHolder.txtViewTitle.setText(item.getName());
        viewHolder.name.setText(item.getName());

    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

       // public TextView txtViewTitle;
        public TextView name;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
           // txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.class_name);
            name = (TextView) itemView.findViewById(R.id.title);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listItems.size();
    }
}

