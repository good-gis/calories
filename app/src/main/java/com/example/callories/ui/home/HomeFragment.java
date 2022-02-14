package com.example.callories.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.callories.AddFoodActivity;
import com.example.callories.AddSportActivity;
import com.example.callories.AuthActivity;
import com.example.callories.MainDisplayActivity;
import com.example.callories.R;
import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.Food;
import com.example.callories.database.entity.User;
import com.example.callories.databinding.FragmentHomeBinding;
import com.example.callories.helpers.DateHelper;
import com.example.callories.helpers.GlobalVariables;

import java.util.List;

public class HomeFragment extends Fragment {

    protected static AppDatabase db = MainDisplayActivity.db;
    Activity context;
    String date = DateHelper.getDate();
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        context = getActivity();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button addActivity = binding.addActivity;
        addActivity.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddSportActivity.class);
            context.startActivity(intent);
        });

        final Button addFood = binding.addFood;
        addFood.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddFoodActivity.class);
            context.startActivity(intent);
        });

        binding.currentDate.setText(date);

        if (((GlobalVariables) context.getApplication()).getUser() != null) {
            calculateFoodAndSportDataForUser();
            User user = ((GlobalVariables) context.getApplication()).getUser();
            binding.basalMetabolismTextHome.setText(String.valueOf(user.basalMetabolism));
            binding.imtTextHome.setText(String.valueOf(user.imt));
            binding.weightTextHome.setText(String.valueOf(user.weight));
        }
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_sign_out) {
            ((GlobalVariables) context.getApplication()).setIsUserAuth(false);
            Intent intent = new Intent(context, AuthActivity.class);
            context.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void calculateFoodAndSportDataForUser() {
        List<com.example.callories.database.entity.Activity> sportForADay = db.activityDao().getSportForADay(date, ((GlobalVariables) context.getApplication()).getUser().uid);
        List<Food> foodForADay = db.foodDao().getFoodForADay(date, ((GlobalVariables) context.getApplication()).getUser().uid);

        int sumBurnedCal = 0;
        for (int i = 0; i < sportForADay.size(); i++) {
            sumBurnedCal += sportForADay.get(i).calBurned;
        }

        int sumEatenCal = 0;
        for (int i = 0; i < foodForADay.size(); i++) {
            sumEatenCal += foodForADay.get(i).cal;
        }

        binding.burnedCaloriesText.setText(String.valueOf(sumBurnedCal));
        binding.eatenCaloriesText.setText(String.valueOf(sumEatenCal));
    }
}