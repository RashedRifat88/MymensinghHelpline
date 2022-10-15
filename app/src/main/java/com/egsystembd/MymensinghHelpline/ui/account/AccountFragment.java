package com.egsystembd.MymensinghHelpline.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.egsystembd.MymensinghHelpline.databinding.FragmentAccountBinding;
import com.egsystembd.MymensinghHelpline.ui.account.edit_profile.EditProfileActivity;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initComponents();

//        final TextView textView = binding.textDashboard;

        return root;
    }

    private void initComponents() {
        binding.tvEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditProfileActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}