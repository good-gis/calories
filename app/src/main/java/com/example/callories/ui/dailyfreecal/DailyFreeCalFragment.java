package com.example.callories.ui.dailyfreecal;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.callories.R;

public class DailyFreeCalFragment extends Fragment {

    private DailyFreeCalViewModel mViewModel;

    public static DailyFreeCalFragment newInstance() {
        return new DailyFreeCalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_free_cal_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DailyFreeCalViewModel.class);
        // TODO: Use the ViewModel
    }

}