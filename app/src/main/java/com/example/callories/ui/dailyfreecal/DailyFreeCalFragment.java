package com.example.callories.ui.dailyfreecal;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.callories.MainDisplayActivity;
import com.example.callories.R;
import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.User;
import com.example.callories.databinding.DailyFreeCalFragmentBinding;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class DailyFreeCalFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    private DailyFreeCalFragmentBinding binding;

    public static DailyFreeCalFragment newInstance() {
        return new DailyFreeCalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();

        binding = DailyFreeCalFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (((GlobalVariables) context.getApplication()).getUser() != null) {
            User user = db.userDao().findByPhone(((GlobalVariables) context.getApplication()).getUser().phone);
            binding.dateToGoal.setText(String.valueOf(user.dateGoalEnd));
            binding.calToGoal.setText(String.valueOf(user.dailyBurnCalForAGoal));
            binding.calToGoalText.setText(String.valueOf(user.imt));
            binding.basalMetabolism.setText(String.valueOf(user.extraWeightInCal));

            binding.calculateGoal.setOnClickListener(v -> {
                if (validateUserData(binding.loseKg, binding.dayToGoal, user.basalMetabolism)) {

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

}