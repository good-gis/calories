package com.example.callories.ui.aboutapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.callories.R;
import com.example.callories.databinding.AboutAppFragmentBinding;
import com.example.callories.databinding.FragmentHomeBinding;

public class AboutAppFragment extends Fragment {

    private AboutAppViewModel mViewModel;
    private AboutAppFragmentBinding binding;

    public static AboutAppFragment newInstance() {
        return new AboutAppFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AboutAppFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.aboutAppText.setMovementMethod(new ScrollingMovementMethod());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AboutAppViewModel.class);
        // TODO: Use the ViewModel
    }

}