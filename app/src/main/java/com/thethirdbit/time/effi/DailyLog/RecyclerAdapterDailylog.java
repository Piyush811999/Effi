package com.thethirdbit.time.effi.DailyLog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thethirdbit.time.effi.R;

import java.util.List;

/**
 * Created by piyush on 7/2/18.
 */

public class RecyclerAdapterDailylog extends RecyclerView.Adapter<RecyclerAdapterDailylog.ViewHolder> {
    private Context context;
    private List<dailylogItems> listItems;

    public RecyclerAdapterDailylog(Context context, List<dailylogItems> listItems) {
        this.context = context;
        this.listItems = listItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_dailylog,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dailylogItems items = listItems.get(position);
        holder.date.setText(items.getDate());
        dailylogItems itemsone = listItems.get(position);
        holder.notes.setText(itemsone.getNotes());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        public TextView date;
        public TextView notes;
        public ViewHolder(View itemView) {
            super(itemView);

            //itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.date_dailylog);
            notes=itemView.findViewById(R.id.notes_dailyview);


        }

       /* @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ListItems item = listItems.get(position);

            Intent intent = new Intent(context,SubTasksActivity.class);
            intent.putExtra("task",item.getTask());

            context.startActivity(intent);

        }*/
    }
}
