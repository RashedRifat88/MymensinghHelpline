package com.egsystembd.MymensinghHelpline.ui.my_history.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.model.AppointmentHistoryModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;
import com.egsystembd.MymensinghHelpline.ui.my_history.appointment.adapter.AppointmentHistoryAdapter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppointmentHistoryActivity extends AppCompatActivity {

    LottieAnimationView animationView;
    RecyclerView recyclerView;
    AppointmentHistoryAdapter appointmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        initComponents();
        loadRecyclerView();
        appointmentListApi();
    }

    private void initComponents() {

        animationView = findViewById(R.id.animationView);
        recyclerView = findViewById(R.id.recyclerView);

//        tv_appointments_all.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(), ResourceAllActivity.class);
//            startActivity(intent);
//        });


    }


    private void loadRecyclerView() {
        appointmentsAdapter = new AppointmentHistoryAdapter(this);
        recyclerView.setAdapter(appointmentsAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        appointmentsAdapter.setData(appointmentImageList, appointmentsTitleList, popularTopicPriceList, false);
        appointmentsAdapter.notifyDataSetChanged();
    }



    @SuppressLint("CheckResult")
    public void appointmentListApi() {

        if (animationView.getVisibility() == View.GONE) {
            animationView.setVisibility(View.VISIBLE);
        }

        String token = SharedData.getTOKEN(this);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";
        String phone = "01814220954";


        RetrofitApiClient.getApiInterface().appointment_history(authorization, accept, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());
                            Log.d("tag111444", " response.code(): " + response.code());
//                            progressDialog.dismiss();
                            animationView.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                animationView.setVisibility(View.GONE);

                                AppointmentHistoryModel model = response.body();
                                Log.d("tag11111", " model: " + model);

                                List<AppointmentHistoryModel.AppointmentResponse> appointment_list = model.getAppointmentResponse();
                                Log.d("tag11111", " appointment_list: " + appointment_list);

                                appointmentsAdapter.setData(appointment_list, "appointment_history_activity");
                                appointmentsAdapter.notifyDataSetChanged();


                            } else {

                            }

                        },
                        error -> {

                            Log.d("tag11111", " error: " + error.getMessage());
                        },
                        () -> {
                            Log.d("tag11111", " response.code(): ");
                        }

                );

    }



}