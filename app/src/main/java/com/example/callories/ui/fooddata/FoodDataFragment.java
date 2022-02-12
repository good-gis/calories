package com.example.callories.ui.fooddata;

import android.app.Activity;
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
import com.example.callories.helpers.DateHelper;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.model.Food;
import com.example.callories.recycleradapter.FoodRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodDataFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    private final ArrayList<Food> foodList = new ArrayList<>();
    Activity context;
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
        context = getActivity();

        foodRecyclerView = binding.foodDataRecyclerView;
        setFoodInfo();
        setAdapter();

        setSumFoodInfoForADay(DateHelper.getDate());

        return root;
    }

    private void setFoodInfo() {
        List<com.example.callories.database.entity.Food> dbFoods = db.foodDao().getAllForAUser(((GlobalVariables) context.getApplication()).getUser().uid);
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

    private void setSumFoodInfoForADay(String date) {
        List<com.example.callories.database.entity.Food> listFoodForADayForUser = db.foodDao().getFoodForADay(date, ((GlobalVariables) context.getApplication()).getUser().uid);

        int sumCalForADay = 0;
        int sumFatForADay = 0;
        int sumProteinForADay = 0;
        int sumCarbForADay = 0;
        for (int i = 0; i < listFoodForADayForUser.size(); i++) {
            sumCalForADay += listFoodForADayForUser.get(i).cal;
            sumFatForADay += listFoodForADayForUser.get(i).fat;
            sumProteinForADay += listFoodForADayForUser.get(i).protein;
            sumCarbForADay += listFoodForADayForUser.get(i).carb;
        }

        binding.sumCurrentDateText.setText(date);
        binding.sumCalText.setText(String.valueOf(sumCalForADay));
        binding.sumFatText.setText(String.valueOf(sumFatForADay));
        binding.sumProteinText.setText(String.valueOf(sumProteinForADay));
        binding.sumCarbText.setText(String.valueOf(sumCarbForADay));
    }

}