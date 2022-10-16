package com.egsystembd.MymensinghHelpline.ui.my_history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.databinding.FragmentMyHistoryBinding;
import com.egsystembd.MymensinghHelpline.ui.account.edit_profile.EditProfileActivity;
import com.egsystembd.MymensinghHelpline.ui.my_history.appointment.AppointmentHistoryActivity;


public class MyHistoryFragment extends Fragment {

    private FragmentMyHistoryBinding binding;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMyHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initComponents();

//        final TextView textView = binding.textDashboard;

        return root;
    }

    private void initComponents() {

        binding.linearAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(context, AppointmentHistoryActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}