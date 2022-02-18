package com.example.callories.ui.activitydata;

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
import com.example.callories.databinding.ActivityDataFragmentBinding;
import com.example.callories.helpers.DateHelper;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.recycleradapter.SportRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityDataFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    private final ArrayList<com.example.callories.model.Activity> activityList = new ArrayList<>();
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
        List<com.example.callories.database.entity.Activity> dbActivities = db.activityDao().getAllForAUser(((GlobalVariables) context.getApplication()).getUser().uid);
        for (int i = 0; i < dbActivities.size(); i++) {
            com.example.callories.model.Activity activity = new com.example.callories.model.Activity(dbActivities.get(i).uid, dbActivities.get(i).date, dbActivities.get(i).userId, dbActivities.get(i).activityName, dbActivities.get(i).timeOfExecution, dbActivities.get(i).calBurned);
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
        List<com.example.callories.database.entity.Activity> listSportForADayForUser = db.activityDao().getSportForADay(date, ((GlobalVariables) context.getApplication()).getUser().uid);

        int sumCalForADay = 0;
        for (int i = 0; i < listSportForADayForUser.size(); i++) {
            sumCalForADay += listSportForADayForUser.get(i).calBurned;
        }

        binding.sumCurrentDateSportText.setText(date);
        binding.sumCalSportForADay.setText(String.valueOf(sumCalForADay));
    }


}