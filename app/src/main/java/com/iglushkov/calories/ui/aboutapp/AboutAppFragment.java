package com.iglushkov.calories.ui.aboutapp;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iglushkov.calories.databinding.AboutAppFragmentBinding;

public class AboutAppFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        com.iglushkov.calories.databinding.AboutAppFragmentBinding binding = AboutAppFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.aboutAppText.setMovementMethod(new ScrollingMovementMethod());

        return root;
    }

}