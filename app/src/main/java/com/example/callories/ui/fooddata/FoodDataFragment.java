package com.example.callories.ui.fooddata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.callories.MainDisplayActivity;
import com.example.callories.database.AppDatabase;
import com.example.callories.databinding.FoodDataFragmentBinding;
import com.example.callories.model.Food;
import com.example.callories.recycleradapter.FoodRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodDataFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    private final ArrayList<Food> foodList = new ArrayList<Food>();
    private FoodDataViewModel mViewModel;
    private RecyclerView foodRecyclerView;
    private FoodDataFragmentBinding binding;

    public static FoodDataFragment newInstance() {
        return new FoodDataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FoodDataFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        foodRecyclerView = binding.foodDataRecyclerView;
        setFoodInfo();
        setAdapter();

        return root;
    }

    private void setFoodInfo() {
        List<com.example.callories.database.entity.Food> dbFoods = db.foodDao().getAll();
        for (int i = 0; i < dbFoods.size(); i++) {
            Food food = new Food(dbFoods.get(i).uid, dbFoods.get(i).foodName, dbFoods.get(i).qty, dbFoods.get(i).cal, dbFoods.get(i).protein, dbFoods.get(i).fat, dbFoods.get(i).carb, dbFoods.get(i).date, dbFoods.get(i).userId);
            foodList.add(food);
        }
    }

    private void setAdapter() {
        FoodRecyclerAdapter foodRecyclerAdapter = new FoodRecyclerAdapter(foodList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        foodRecyclerView.setLayoutManager(layoutManager);
        foodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        foodRecyclerView.setAdapter(foodRecyclerAdapter);
    }

}