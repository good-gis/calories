package com.example.callories.ui.support;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.callories.R;
import com.example.callories.databinding.FragmentSlideshowBinding;
import com.example.callories.databinding.FragmentSupportBinding;
import com.example.callories.ui.slideshow.SlideshowViewModel;

public class SupportFragment extends Fragment {

    private SupportViewModel mViewModel;
    private FragmentSupportBinding binding;
//
//    public static SupportFragment newInstance() {
//        return new SupportFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_support, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(SupportViewModel.class);
//        // TODO: Use the ViewModel
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       SupportViewModel supportViewModel =
                new ViewModelProvider(this).get(SupportViewModel.class);

        binding = FragmentSupportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSupport;
        supportViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}