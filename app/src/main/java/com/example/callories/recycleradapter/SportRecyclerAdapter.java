package com.example.callories.recycleradapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callories.MainDisplayActivity;
import com.example.callories.R;
import com.example.callories.database.AppDatabase;
import com.example.callories.model.Activity;

import java.util.ArrayList;

public class SportRecyclerAdapter extends RecyclerView.Adapter<SportRecyclerAdapter.MyViewHolder> {

    protected static AppDatabase db = MainDisplayActivity.db;


    private final ArrayList<Activity> activityList;

    public SportRecyclerAdapter(ArrayList<Activity> activityList) {
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public SportRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SportRecyclerAdapter.MyViewHolder holder, int position) {
        String sportNameFromList = activityList.get(position).getActivityName();
        String sportDateFromList = activityList.get(position).getDate();
        String sportCalFromList = String.valueOf(activityList.get(position).getCalBurned());
        String sportTimeFromList = String.valueOf(activityList.get(position).getTimeOfExecution());

        holder.sportName.setText(sportNameFromList);
        holder.sportDate.setText(sportDateFromList);
        holder.sportTime.setText(sportTimeFromList);
        holder.sportCal.setText(sportCalFromList);
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(R.color.white);
        } else {
            holder.itemView.setBackgroundColor(R.color.black);
        }

        holder.sportDeleteBtn.setOnClickListener(v -> {
            db.activityDao().deleteByID(activityList.get(position).getUid());
            holder.itemView.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView sportName;
        private final TextView sportDate;
        private final TextView sportCal;
        private final TextView sportTime;
        private final Button sportDeleteBtn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            sportName = itemView.findViewById(R.id.sportNameItemText);
            sportDate = itemView.findViewById(R.id.sportDateItemText);
            sportCal = itemView.findViewById(R.id.sportCalItemText);
            sportTime = itemView.findViewById(R.id.sportTimeItemText);
            sportDeleteBtn = itemView.findViewById(R.id.sportItemDelBtn);
        }
    }
}
