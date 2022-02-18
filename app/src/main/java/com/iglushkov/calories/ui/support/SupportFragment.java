package com.iglushkov.calories.ui.support;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iglushkov.calories.R;
import com.iglushkov.calories.databinding.FragmentSupportBinding;
import com.iglushkov.calories.helpers.NotifyHelper;

public class SupportFragment extends Fragment {
    private FragmentSupportBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSupportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.buttonSentMail.setOnClickListener(v -> {
            if (binding.textOfMail.getText().toString().isEmpty() || binding.textOfMail.getText().toString().isEmpty()) {
                NotifyHelper.showFastToast(getContext(), R.string.email_support_validation);
            } else {
                composeEmail(new String[]{getString(R.string.my_email)}, binding.themeOfMail.getText().toString(), binding.textOfMail.getText().toString());
            }
        });

        binding.vk.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.vk_link))));
        });

        binding.telegram.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.telegram_link))));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void composeEmail(String[] addresses, String subject, String textOfEmail) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, textOfEmail);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}