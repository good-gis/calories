package com.iglushkov.calories.ui.activitydata;

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

import com.iglushkov.calories.MainDisplayActivity;
import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.databinding.ActivityDataFragmentBinding;
import com.iglushkov.calories.helpers.DateHelper;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.recycleradapter.SportRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityDataFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    private final ArrayList<com.iglushkov.calories.model.Activity> activityList = new ArrayList<>();
    Activity context;
    private ActivityDataFragmentBinding binding;
    private RecyclerView activityRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ActivityDataFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getActivity();

        activityRecyclerView = binding.activityDataRecyclerView;
        setSportInfo();
        setAdapter();

        setSumSportInfoForADay(DateHelper.getDate());

        return root;
    }

    private void setSportInfo() {
        List<com.iglushkov.calories.database.entity.Activity> dbActivities = db.activityDao().getAllForAUser(((GlobalVariables) context.getApplication()).getUser().uid);
        for (int i = 0; i < dbActivities.size(); i++) {
            com.iglushkov.calories.model.Activity activity = new com.iglushkov.calories.model.Activity(dbActivities.get(i).uid, dbActivities.get(i).date, dbActivities.get(i).userId, dbActivities.get(i).activityName, dbActivities.get(i).timeOfExecution, dbActivities.get(i).calBurned);
            activityList.add(activity);
        }
    }

    private void setAdapter() {
        SportRecyclerAdapter sportRecyclerAdapter = new SportRecyclerAdapter(activityList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        activityRecyclerView.setLayoutManager(layoutManager);
        activityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        activityRecyclerView.setAdapter(sportRecyclerAdapter);
    }

    private void setSumSportInfoForADay(String date) {
        List<com.iglushkov.calories.database.entity.Activity> listSportForADayForUser = db.activityDao().getSportForADay(date, ((GlobalVariables) context.getApplication()).getUser().uid);

        int sumCalForADay = 0;
        for (int i = 0; i < listSportForADayForUser.size(); i++) {
            sumCalForADay += listSportForADayForUser.get(i).calBurned;
        }

        binding.sumCurrentDateSportText.setText(date);
        binding.sumCalSportForADay.setText(String.valueOf(sumCalForADay));
    }


}