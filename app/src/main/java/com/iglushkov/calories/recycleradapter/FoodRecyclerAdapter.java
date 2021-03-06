package com.iglushkov.calories.recycleradapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iglushkov.calories.MainDisplayActivity;
import com.iglushkov.calories.R;
import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.model.Food;

import java.util.ArrayList;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.MyViewHolder> {

    protected static AppDatabase db = MainDisplayActivity.db;


    private final ArrayList<Food> foodList;

    public FoodRecyclerAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerAdapter.MyViewHolder holder, int position) {
        String foodNameFromList = foodList.get(position).getFoodName();
        String foodDateFromList = foodList.get(position).getDate();
        String foodQtyFromList = foodList.get(position).getQty();
        String foodCalFromList = String.valueOf(foodList.get(position).getCal());
        String foodProteinFromList = String.valueOf(foodList.get(position).getProtein());
        String foodFatFromList = String.valueOf(foodList.get(position).getFat());
        String foodCarbFromList = String.valueOf(foodList.get(position).getCarb());
        holder.foodName.setText(foodNameFromList);
        holder.foodDate.setText(foodDateFromList);
        holder.foodQty.setText(foodQtyFromList);
        holder.foodCal.setText(foodCalFromList);
        holder.foodProtein.setText(foodProteinFromList);
        holder.foodFat.setText(foodFatFromList);
        holder.foodCarb.setText(foodCarbFromList);
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(R.color.white);
        } else {
            holder.itemView.setBackgroundColor(R.color.black);
        }

        holder.foodDeleteBtn.setOnClickListener(v -> {
            db.foodDao().deleteByID(foodList.get(position).getUid());
            holder.itemView.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodName;
        private final TextView foodDate;
        private final TextView foodQty;
        private final TextView foodCal;
        private final TextView foodProtein;
        private final TextView foodFat;
        private final TextView foodCarb;
        private final Button foodDeleteBtn;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodNameItemText);
            foodDate = itemView.findViewById(R.id.foodDateItemText);
            foodQty = itemView.findViewById(R.id.foodQtyItemText);
            foodCal = itemView.findViewById(R.id.foodCalItemText);
            foodProtein = itemView.findViewById(R.id.foodProteinItemText);
            foodFat = itemView.findViewById(R.id.foodFatItemText);
            foodCarb = itemView.findViewById(R.id.foodCarbItemText);
            foodDeleteBtn = itemView.findViewById(R.id.foodItemDelBtn);
        }
    }
}
