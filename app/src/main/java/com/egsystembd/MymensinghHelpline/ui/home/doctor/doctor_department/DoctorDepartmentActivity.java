package com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.databinding.ActivityDoctorDepartmentBinding;
import com.egsystembd.MymensinghHelpline.databinding.ActivityMymensinghDivisionBinding;
import com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department.adapter.DoctorDepartmentAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorDepartmentActivity extends AppCompatActivity {

    private ActivityDoctorDepartmentBinding binding;
    private DoctorDepartmentAdapter adapter;

    List<String> name_list;
    List<String> home_module_name_ban_list = new ArrayList<>();
    List<String> image_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDoctorDepartmentBinding.inflate(getLayoutInflater());
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
        name_list = Arrays.asList(getResources().getStringArray(R.array.division_wise_doctor_title_code_array));
//        home_module_name_ban_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_ban_list));
        image_list = Arrays.asList(getResources().getStringArray(R.array.division_wise_doctor_image_array));
    }

    private void initComponent() {
//        recyclerView = findViewById(R.id.recyclerView);
        binding.linearBack.setOnClickListener(v -> {
            finish();
        });
    }



    private void loadRecyclerView() {
        adapter = new DoctorDepartmentAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        binding.recyclerView.setLayoutManager(mLayoutManager);
//        adapter.setData(name_list, home_module_name_ban_list, image_list);
        adapter.setData(name_list, image_list);
        adapter.notifyDataSetChanged();
    }


    private void filter(String text) {
        List<String> filteredList = new ArrayList<>();
//        List<String> filteredListBan = new ArrayList<>();
        List<String> filteredListImg = new ArrayList<>();
        List<Integer> filteredPosition = new ArrayList<>();

        for (String item : name_list) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredPosition.add(name_list.indexOf(item));
            }
        }

        for (int position : filteredPosition) {
//            filteredListBan.add(home_module_name_ban_list.get(position));
            filteredListImg.add(image_list.get(position));
        }

//        adapter.filterList(filteredList, filteredListBan, filteredListImg);
        adapter.filterList(filteredList, filteredListImg);
    }


}