package com.example.callories.ui.extraweight;

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
import com.example.callories.databinding.ExtraWeightFragmentBinding;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class ExtraWeightFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    private ExtraWeightFragmentBinding binding;

    public static ExtraWeightFragment newInstance() {
        return new ExtraWeightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();

        binding = ExtraWeightFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (((GlobalVariables) context.getApplication()).getUser() != null) {
            User user = db.userDao().findByPhone(((GlobalVariables) context.getApplication()).getUser().phone);
            binding.weightOfUser.setText(String.valueOf(user.weight));
            binding.imtOfUser.setText(String.valueOf(user.imt));
            binding.extraCalOfUser.setText(String.valueOf(user.extraWeightInCal));

            binding.weightToCalBtn.setOnClickListener(v -> {
                if (validateUserData(user.weight, user.imt)) {
                    int extraWeightInCal = extraCalCalculate(user.weight, user.imt);
                    binding.extraCalOfUser.setText(String.valueOf(extraWeightInCal));
                    user.extraWeightInCal = extraWeightInCal;
                    db.userDao().updateUser(user);
                }
            });
        }

        return root;
    }

    private int extraCalCalculate(double weight, double imt) {
        double result = weight * (imt - 20) / 100 * 7716;
        return (int) result;
    }

    private boolean validateUserData(double weight, double imt) {
        if (weight == 0.0) {
            NotifyHelper.showFastToast(context, R.string.weight_validation_text);
            return false;
        }

        if (imt == 0.0) {
            NotifyHelper.showFastToast(context, R.string.imt_validation_text);
            return false;
        }

        return true;
    }


}