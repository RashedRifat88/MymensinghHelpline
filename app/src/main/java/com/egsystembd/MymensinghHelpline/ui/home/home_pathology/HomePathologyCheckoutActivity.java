package com.egsystembd.MymensinghHelpline.ui.home.home_pathology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.model.AllTestModel;

import java.util.ArrayList;

public class HomePathologyCheckoutActivity extends AppCompatActivity {

    ArrayList<AllTestModel.Test> selectedTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pathology_checkout);

        selectedTests = (ArrayList<AllTestModel.Test>) getIntent().getSerializableExtra("selected_tests");
    }
}