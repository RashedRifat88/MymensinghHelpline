package com.egsystembd.MymensinghHelpline.ui.home.mymensingh_division;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.databinding.ActivityMainBinding;
import com.egsystembd.MymensinghHelpline.databinding.ActivityMymensinghDivisionBinding;
import com.egsystembd.MymensinghHelpline.databinding.FragmentHomeBinding;
import com.egsystembd.MymensinghHelpline.ui.home.HomeViewModel;
import com.egsystembd.MymensinghHelpline.ui.home.mymensingh_division.adapter.MymensinghDivAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MymensinghDivisionActivity extends AppCompatActivity {

    private ActivityMymensinghDivisionBinding binding;
    private MymensinghDivAdapter adapter;

    List<String> mymensingh_div_service_name_list;
    List<String> home_module_name_ban_list = new ArrayList<>();
    List<String> mymensingh_div_service_image_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMymensinghDivisionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initStatusBar();
        loadListData();
        initComponent();
        initView();

        loadRecyclerView();
    }


    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary, this.getTheme()));
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
        }

    }



    private void initView() {

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                filter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }



    private void loadListData() {
        mymensingh_div_service_name_list = Arrays.asList(getResources().getStringArray(R.array.mymensingh_div_service_name_list));
//        home_module_name_ban_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_ban_list));
        mymensingh_div_service_image_list = Arrays.asList(getResources().getStringArray(R.array.mymensingh_div_service_image_list));
    }

    private void initComponent() {
//        recyclerView = findViewById(R.id.recyclerView);
        binding.linearBack.setOnClickListener(v -> {
            finish();
        });
    }



    private void loadRecyclerView() {
        adapter = new MymensinghDivAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        binding.recyclerView.setLayoutManager(mLayoutManager);
//        adapter.setData(mymensingh_div_service_name_list, home_module_name_ban_list, mymensingh_div_service_image_list);
        adapter.setData(mymensingh_div_service_name_list, mymensingh_div_service_image_list);
        adapter.notifyDataSetChanged();
    }


    private void filter(String text) {
        List<String> filteredList = new ArrayList<>();
//        List<String> filteredListBan = new ArrayList<>();
        List<String> filteredListImg = new ArrayList<>();
        List<Integer> filteredPosition = new ArrayList<>();

        for (String item : mymensingh_div_service_name_list) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredPosition.add(mymensingh_div_service_name_list.indexOf(item));
            }
        }

        for (int position : filteredPosition) {
//            filteredListBan.add(home_module_name_ban_list.get(position));
            filteredListImg.add(mymensingh_div_service_image_list.get(position));
        }

//        adapter.filterList(filteredList, filteredListBan, filteredListImg);
        adapter.filterList(filteredList, filteredListImg);
    }




}