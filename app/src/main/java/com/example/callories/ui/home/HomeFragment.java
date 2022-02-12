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
import com.example.callories.R;
import com.example.callories.databinding.FragmentHomeBinding;
import com.example.callories.helpers.GlobalVariables;

public class HomeFragment extends Fragment {

    Activity context;
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
}