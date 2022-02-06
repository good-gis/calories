package com.example.callories.recycleradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callories.R;
import com.example.callories.model.Food;

import java.util.ArrayList;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.MyViewHolder> {


    private ArrayList<Food> foodList;

    public FoodRecyclerAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView foodName;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodNameItem);
        }
    }

    @NonNull
    @Override
    public FoodRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerAdapter.MyViewHolder holder, int position) {
        String foodNameFromList = foodList.get(position).getFoodName();
        holder.foodName.setText(foodNameFromList);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
