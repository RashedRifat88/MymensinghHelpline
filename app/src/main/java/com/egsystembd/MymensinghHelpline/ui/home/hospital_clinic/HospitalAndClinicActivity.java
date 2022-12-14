package com.egsystembd.MymensinghHelpline.ui.home.hospital_clinic;

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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.credential.LoginActivity;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.databinding.ActivityHospitalAndClinicBinding;
import com.egsystembd.MymensinghHelpline.model.DoctorListModel;
import com.egsystembd.MymensinghHelpline.model.HospitalListModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;
import com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department.doctor_list.adapter.DoctorListAdapter;
import com.egsystembd.MymensinghHelpline.ui.home.hospital_clinic.adapter.HospitalListAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HospitalAndClinicActivity extends AppCompatActivity {

    private ActivityHospitalAndClinicBinding binding;
    private HospitalListAdapter adapter;

    List<String> mymensingh_div_service_name_list;
    List<String> home_module_name_ban_list = new ArrayList<>();
    List<String> mymensingh_div_service_image_list;

    private String title = "";
    List<HospitalListModel.Hospital> doctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHospitalAndClinicBinding.inflate(getLayoutInflater());
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
    }


    private void loadRecyclerView() {
        adapter = new HospitalListAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        binding.recyclerView.setLayoutManager(mLayoutManager);
//        adapter.setData(mymensingh_div_service_name_list, mymensingh_div_service_image_list);
        adapter.notifyDataSetChanged();
    }


    private void filter(String text) {
        List<HospitalListModel.Hospital> filteredList = new ArrayList<>();
//        List<String> filteredListBan = new ArrayList<>();
        List<String> filteredListImg = new ArrayList<>();
        List<Integer> filteredPosition = new ArrayList<>();

        for (HospitalListModel.Hospital item : doctorList) {
            if (item.getHospital().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredPosition.add(doctorList.indexOf(item));
            }
        }

        for (int position : filteredPosition) {
//            filteredListBan.add(home_module_name_ban_list.get(position));
            filteredListImg.add(mymensingh_div_service_image_list.get(position));
        }

//        adapter.filterList(filteredList, filteredListBan, filteredListImg);
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

        RetrofitApiClient.getApiInterface().hospitals(authorization, accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());


                            if (response.code() == 401) {
                                Intent intent = new Intent(HospitalAndClinicActivity.this, LoginActivity.class);
                                intent.putExtra("SENDER_ACTIVITY_NAME", "");
                                startActivity(intent);
                            }



                            if (response.isSuccessful()) {
                                closeProgressDialog();

                                response.body(); // do something with that
                                Log.d("tag11111", " response.body(): " + response.body());

                                HospitalListModel specialistDoctor = response.body();

                                if (response.code() == 200) {

                                    HospitalListModel model = response.body();

                                    String responseString = response.message();

                                    doctorList = model.getHospitalList();

                                    adapter.setData(doctorList);
                                    adapter.notifyDataSetChanged();


                                } else {
                                    new MaterialDialog.Builder(HospitalAndClinicActivity.this)
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