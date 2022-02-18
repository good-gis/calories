package com.iglushkov.calories.ui.dailyfreecal;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iglushkov.calories.MainDisplayActivity;
import com.iglushkov.calories.R;
import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.databinding.DailyFreeCalFragmentBinding;
import com.iglushkov.calories.helpers.DateHelper;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class DailyFreeCalFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    private DailyFreeCalFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();

        binding = DailyFreeCalFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (((GlobalVariables) context.getApplication()).getUser() != null) {
            User user = db.userDao().findByPhone(((GlobalVariables) context.getApplication()).getUser().phone);
            binding.calToGoal.setText(String.valueOf(user.dailyBurnCalForAGoal));
            binding.basalMetabolism.setText(String.valueOf(user.basalMetabolism));
            if (user.dateGoalEnd == null) {
                binding.dateFinishGoal.setText("цели нет");
            }

            binding.calculateGoal.setOnClickListener(v -> {
                if (validateUserData(binding.loseKg, binding.dayToGoal, user.basalMetabolism)) {
                    int calGoal = calculateCalGoalPerDay(Double.parseDouble(binding.loseKg.getText().toString()), Integer.parseInt(binding.dayToGoal.getText().toString()), user.basalMetabolism);
                    binding.calToGoal.setText(String.valueOf(calGoal));
                    user.dailyBurnCalForAGoal = calGoal;
                    user.dateGoalEnd = DateHelper.getDateAfterDays(Integer.parseInt(binding.dayToGoal.getText().toString()));
                    binding.dateFinishGoal.setText(user.dateGoalEnd);
                    db.userDao().updateUser(user);
                }
            });
        }

        return root;
    }

    private boolean validateUserData(TextView kg, TextView day, int basalMet) {
        if (kg.getText().toString().equals("")) {
            NotifyHelper.showFastToast(context, R.string.kg_validation_text);
            return false;
        }

        if (day.getText().toString().equals("")) {
            NotifyHelper.showFastToast(context, R.string.kg_validation_text);
            return false;
        }

        if (basalMet == 0) {
            NotifyHelper.showFastToast(context, R.string.basal_met_validation_text);
            return false;
        }

        return true;
    }

    private int calculateCalGoalPerDay(double kg, int days, int basalMetabolism) {
        return (int) ((kg * 7716) / days) - basalMetabolism;
    }

}