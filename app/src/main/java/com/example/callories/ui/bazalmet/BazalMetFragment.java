package com.example.callories.ui.bazalmet;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.callories.MainDisplayActivity;
import com.example.callories.R;
import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.User;
import com.example.callories.databinding.BazalMetFragmentBinding;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class BazalMetFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    private BazalMetFragmentBinding binding;

    public static BazalMetFragment newInstance() {
        return new BazalMetFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();

        binding = BazalMetFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        if (((GlobalVariables) context.getApplication()).getUser() != null) {
            User user = db.userDao().findByPhone(((GlobalVariables) context.getApplication()).getUser().phone);
            binding.weightOfUserInBazalFragment.setText(String.valueOf(user.weight));
            binding.heightOfUserInBazalFragment.setText(String.valueOf(user.height));
            binding.ageOfUserInBazalFragment.setText(String.valueOf(user.age));
            binding.bazalMetCal.setText(String.valueOf(user.basalMetabolism));

            binding.btnCalculateInBazalFragment.setOnClickListener(v -> {
                if (validateUserData(user.weight, user.height, user.age)) {
                    double activityRatio = getActivityRatio(user.activityLevel);
                    int basalMet = calculateBasalMetabolism(user.gender, user.weight, user.height, user.age, activityRatio);
                    binding.bazalMetCal.setText(String.valueOf(basalMet));
                    user.basalMetabolism = basalMet;
                    db.userDao().updateUser(user);
                }
            });

        }

        return root;
    }

    private int calculateBasalMetabolism(String gender, double weight, int height, int age, double activityRatio) {
        if (gender.equals("woman")) {
            double basalMet = (447.593 + (9.247 * weight) + (3.098 * height) * (4.330 * age)) * activityRatio;
            return (int) basalMet;
        }

        double basalMet = (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age)) * activityRatio;
        return (int) basalMet;
    }

    private double getActivityRatio(int activityLevel) {
        switch (activityLevel) {
            case 1:
                return 1.3;
            case 2:
                return 1.6;
            case 3:
                return 1.7;
            case 4:
                return 1.9;
            default:
                return 1.2;
        }
    }


    private boolean validateUserData(double weight, int height, int age) {
        if (weight == 0.0) {
            NotifyHelper.showFastToast(context, R.string.weight_validation_text);
            return false;
        }

        if (height == 0) {
            NotifyHelper.showFastToast(context, R.string.height_validation_text);
            return false;
        }

        if (age == 0) {
            NotifyHelper.showFastToast(context, R.string.age_validation_text);
            return false;
        }

        return true;
    }

}