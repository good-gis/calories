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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.callories.AuthActivity;
import com.example.callories.MainDisplayActivity;
import com.example.callories.R;
import com.example.callories.databinding.FragmentHomeBinding;
import com.example.callories.helpers.GlobalVariables;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        context = getActivity();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final Button singOut = binding.singOut;
//        singOut.setOnClickListener(v -> {
//            ((GlobalVariables) context.getApplication()).setIsUserAuth(false);
//            Intent intent = new Intent(context, AuthActivity.class);
//            context.startActivity(intent);
//        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_sign_out) {
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