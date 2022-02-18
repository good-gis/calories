package com.iglushkov.calories.ui.userdata;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iglushkov.calories.MainDisplayActivity;
import com.iglushkov.calories.R;
import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.databinding.UserDataFragmentBinding;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class UserDataFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    private UserDataFragmentBinding binding;
    private User user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        context = getActivity();

        binding = UserDataFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = db.userDao().findByPhone(((GlobalVariables) context.getApplication()).getUser().phone);

        if (user.gender != null) {
            binding.savedData.setText(getSavedUserData());
            setFieldPreviousData();
        }

        binding.saveUserDataBtn.setOnClickListener(view -> {
            if (validateUserData()) {
                saveUserData();
                binding.savedData.setText(getSavedUserData());
            } else {
                NotifyHelper.showFastToast(context, R.string.user_data_validation);
            }
        });

        return root;

    }

    private void saveUserData() {
        user.gender = genderSwitcherMap();
        user.age = Integer.parseInt(binding.age.getText().toString());
        user.height = Integer.parseInt(binding.height.getText().toString());
        user.weight = Double.parseDouble(binding.weight.getText().toString());
        user.bellyCm = Integer.parseInt(binding.belly.getText().toString());
        user.neckCm = Integer.parseInt(binding.neck.getText().toString());
        user.activityLevel = binding.spinnerActivity.getSelectedItemPosition();
        db.userDao().updateUser(user);
    }

    private void setFieldPreviousData() {
        binding.age.setText(String.valueOf(user.age));
        binding.height.setText(String.valueOf(user.height));
        binding.weight.setText(String.valueOf(user.weight));
        binding.belly.setText(String.valueOf(user.bellyCm));
        binding.neck.setText(String.valueOf(user.neckCm));
        binding.spinnerActivity.setSelection(user.activityLevel);
        if (user.gender.equals("woman")) {
            binding.genderSwitch.setChecked(true);
        }
    }

    private boolean validateUserData() {
        return !binding.age.getText().toString().isEmpty() &&
                !binding.height.getText().toString().isEmpty() &&
                !binding.weight.getText().toString().isEmpty() &&
                !binding.belly.getText().toString().isEmpty() &&
                !binding.neck.getText().toString().isEmpty();
    }

    private String genderSwitcherMap() {
        if (binding.genderSwitch.isChecked()) {
            return "woman";
        }
        return "man";
    }

    private String genderSwitcherMap(String gender) {
        if (gender.equals("woman")) {
            return "Женщина";
        }
        return "Мужчина";
    }

    private String getSavedUserData() {
        return "Имя: " + user.name + "\n" +
                "Возраст: " + user.age + "\n" +
                "Вес: " + user.weight + "\n" +
                "Физическая активность: " + user.activityLevel + "\n" +
                "Окружность брюшной полости: " + user.bellyCm + "\n" +
                "Окружность шеи: " + user.neckCm + "\n" +
                "Пол: " + genderSwitcherMap(user.gender) + "\n" +
                "Телефон: " + user.phone + "\n" +
                "Запомнить меня: " + user.isRememberMe + "\n";
    }
}