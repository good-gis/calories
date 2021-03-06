package com.iglushkov.calories.ui.imt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iglushkov.calories.ChangeImtActivity;
import com.iglushkov.calories.MainDisplayActivity;
import com.iglushkov.calories.R;
import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.databinding.ImtFragmentBinding;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class ImtFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    private ImtFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        context = getActivity();

        binding = ImtFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (((GlobalVariables) context.getApplication()).getUser() != null) {
            User user = db.userDao().findByPhone(((GlobalVariables) context.getApplication()).getUser().phone);
            binding.imtNumber.setText(String.valueOf(user.imt));

            binding.calculateImtBtn.setOnClickListener(v -> {
                if (user.height != 0) {
                    double imt = ImtCalculation.imtCalculate(user.height, user.bellyCm, user.height, user.gender);
                    binding.imtNumber.setText(String.valueOf(imt));
                    user.imt = imt;
                    db.userDao().updateUser(user);
                } else {
                    NotifyHelper.showFastToast(context, R.string.imt_validation);
                }
            });

            binding.changeImtBtn.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChangeImtActivity.class);
                context.startActivity(intent);
            });
        }

        return root;
    }

}