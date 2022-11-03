package com.egsystembd.MymensinghHelpline.ui.home.home_pathology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.credential.LoginActivity;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.databinding.ActivityHomePathologyBinding;
import com.egsystembd.MymensinghHelpline.model.AllTestModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;
import com.egsystembd.MymensinghHelpline.ui.home.home_pathology.adapter.HomePathologyAdapter;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePathologyActivity extends AppCompatActivity {

    private ActivityHomePathologyBinding binding;
    private HomePathologyAdapter adapter;

    List<String> mymensingh_div_service_name_list;
    List<String> home_module_name_ban_list = new ArrayList<>();
    List<String> mymensingh_div_service_image_list;

    private String title = "";
    List<AllTestModel.Test> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePathologyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title = getIntent().getStringExtra("title");
//        binding.tvTitle.setText(title);

        initStatusBar();
//        loadListData();
        initComponent();
        initView();

        loadRecyclerView();

        Log.d("tag11111", " token: " + "dfdfdf");
        loadDoctorList("");
        Log.d("tag11111", " token after: " + "dfdfdf");

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


//    private void loadListData() {
//        mymensingh_div_service_name_list = Arrays.asList(getResources().getStringArray(R.array.mymensingh_div_service_name_list));
////        home_module_name_ban_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_ban_list));
//        mymensingh_div_service_image_list = Arrays.asList(getResources().getStringArray(R.array.mymensingh_div_service_image_list));
//    }

    private void initComponent() {
//        recyclerView = findViewById(R.id.recyclerView);
        binding.linearBack.setOnClickListener(v -> {
            finish();
        });

        binding.tvCheckout.setOnClickListener(view -> {
            ArrayList<String> selectedItemIds = adapter.getSelectedIds();
            ArrayList<AllTestModel.Test> selectedTests = adapter.getSelectedTests();
            Log.d("tag20", "selectedItemIds from fragment: " + selectedItemIds);

            if (selectedItemIds.size() > 0) {

                Intent intent = new Intent(HomePathologyActivity.this, HomePathologyCheckoutActivity.class);
                intent.putExtra("selected_ids", selectedItemIds);
                intent.putExtra("selected_tests", selectedTests);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "You should select all at least one", Toast.LENGTH_LONG).show();
            }


        });

    }


    private void loadRecyclerView() {
        adapter = new HomePathologyAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        binding.recyclerView.setLayoutManager(mLayoutManager);
//        adapter.setData(mymensingh_div_service_name_list, mymensingh_div_service_image_list);
        adapter.notifyDataSetChanged();
    }


    private void filter(String text) {
        List<AllTestModel.Test> filteredList = new ArrayList<>();
        List<String> filteredListImg = new ArrayList<>();
        List<Integer> filteredPosition = new ArrayList<>();

        for (AllTestModel.Test item : testList) {
            if (item.getTestName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredPosition.add(testList.indexOf(item));
            }
        }

        adapter.filterList(filteredList);
    }


    @SuppressLint("CheckResult")
    public void loadDoctorList(String speciality_division) {

        showProgressDialog();

        String token = SharedData.getTOKEN(this);
        Log.d("tag11111", " token: " + token);
        Log.d("tag11111", " speciality_division: " + speciality_division);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";

        RetrofitApiClient.getApiInterface().show_all_test(authorization, accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());


                            if (response.code() == 401) {
                                Intent intent = new Intent(HomePathologyActivity.this, LoginActivity.class);
                                intent.putExtra("SENDER_ACTIVITY_NAME", "");
                                startActivity(intent);
                            }



                            if (response.isSuccessful()) {
                                closeProgressDialog();

                                response.body(); // do something with that
                                Log.d("tag11111", " response.body(): " + response.body());

                                AllTestModel specialistDoctor = response.body();

                                if (response.code() == 200) {

                                    AllTestModel model = response.body();

                                    String responseString = response.message();

                                    testList = model.getTestList();

                                    adapter.setData(testList);
                                    adapter.notifyDataSetChanged();


                                } else {
                                    new MaterialDialog.Builder(HomePathologyActivity.this)
                                            .title("Doctor Status")
                                            .content("List is empty....")
                                            .positiveText("")
                                            .negativeText("Ok")
                                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                                }
                                            })
                                            .show();
                                }

                            } else {

                            }


                        },
                        error -> {

                            Log.d("tag11111", " response.code(): " + error.toString());

                        },
                        () -> {

                        }
                );


    }


    ProgressDialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


}