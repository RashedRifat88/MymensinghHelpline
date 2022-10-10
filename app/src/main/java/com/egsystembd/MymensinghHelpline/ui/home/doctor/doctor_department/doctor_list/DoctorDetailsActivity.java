package com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department.doctor_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.credential.LoginActivity;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.model.DoctorDetailsModel;
import com.egsystembd.MymensinghHelpline.model.DoctorListModel;
import com.egsystembd.MymensinghHelpline.retrofit.Api;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;
import com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department.doctor_list.adapter.AvailableDaysAdapter;
import com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department.doctor_list.adapter.DoctorListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DoctorDetailsActivity extends AppCompatActivity {

    static String doctor_name, doctor_id, doctorSpecialities, doctorExperiencesYear, bmdc_no, doctorDegrees,
            doctor_img_url, doctor_desig, doctor_note, doctorFee, doctorAvailabilityTime;
    String roomId = "";
    String fromWhere = "";
    ImageView imageView;
    TextView tv_doctor_name, tv_doctor_speciality, tv_doctor_experience, tv_doctor_experience2, tv_bmdc_no, tv_doctor_degree, tv_doctor_designation,
            tv_doctor_fee, tv_note, tv_doctor_availability_day, tv_calling_text, tv_view_more, tv_appointment;
    static TextView tv_selected_date, tv_selected_date2;
    LinearLayout linearCall, linearCallStop, linearDesig, linearNote, linearVideoConsultation, linearDoctorTiming, linear_my_doctor, linear_all_doctor;
    static LinearLayout linearSubTimeSlot, linearMicroTimeSlot;

    private List<String> doctorDateList;
    private List<String> doctorTimeSlotNumberList;
    private List<String> divisionWiseDoctorList;
    private List<String> divisionWiseDoctorList2;

    //    List<SpecialistDoctor.ProcessedtimeSlot> listOfTimeSlots;
    public static RecyclerView recyclerView1;
    public static RecyclerView recyclerView2;
    public static RecyclerView recyclerView3;

    private AvailableDaysAdapter adapter;
    List<String> availableDayList = new ArrayList<>();

    public String video_call_initiate = "";
    public String video_call_check_status_url = "";
    private String call_status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);


        doctor_name = getIntent().getStringExtra("doctor_name");
        doctor_id = getIntent().getStringExtra("doctor_id");
        doctorSpecialities = getIntent().getStringExtra("doctorSpecialities");
        doctorExperiencesYear = getIntent().getStringExtra("doctorExperiencesYear");
        bmdc_no = getIntent().getStringExtra("bmdc_no");
        doctorDegrees = getIntent().getStringExtra("doctorDegrees");
        doctorFee = getIntent().getStringExtra("doctorFee");
        doctorAvailabilityTime = getIntent().getStringExtra("doctorAvailability");
        doctor_img_url = getIntent().getStringExtra("doctor_img_url");
        doctor_desig = getIntent().getStringExtra("doctor_desig");
        doctor_note = getIntent().getStringExtra("doctor_note");

        fromWhere = getIntent().getStringExtra("fromWhere");

//        listOfTimeSlots = (List<SpecialistDoctor.ProcessedtimeSlot>) getIntent().getSerializableExtra("listOfTimeSlots");

        initStatusBar();
        initComponemt();
        loadRecyclerView();
        getDoctorDetailsById(doctor_id);
//        setDoctorData();


//        VidChat.INSTANCE.requestVideoChatPermissions(this, PERMISSION_REQUEST_CODE);
    }

    private void setDoctorData() {
        if (doctor_desig == null) {
            linearDesig.setVisibility(View.GONE);
        } else {
            tv_doctor_designation.setText(doctor_desig);
        }


        if (doctor_note == null) {
            linearNote.setVisibility(View.GONE);
        } else {
            String myReallyLongText = "Bacon ipsum dolor amet porchetta venison ham fatback alcatra tri-tip, turducken strip steak sausage rump burgdoggen pork loin. Spare ribs filet mignon salami, strip steak ball tip shank frankfurter corned beef venison. Pig pork belly pork chop andouille. Porchetta pork belly ground round, filet mignon bresaola chuck swine shoulder leberkas jerky boudin. Landjaeger pork chop corned beef, tri-tip brisket rump pastrami flank.";
            tv_note.setText(myReallyLongText);
//            tv_note.setText(doctor_note);

        }

        tv_doctor_name.setText("Dr. " + doctor_name);
        tv_doctor_speciality.setText(doctorSpecialities);
        tv_bmdc_no.setText("BMDC: " + bmdc_no);

        tv_doctor_experience2.setVisibility(View.GONE);
//        tv_doctor_degree.setText(doctorDegrees);
        tv_doctor_degree.setText(doctorDegrees);


        tv_doctor_degree.setText(doctorDegrees);

        tv_doctor_fee.setText("BDT " + doctorFee);
//            tv_doctor_availability_day.setText(doctorAvailabilityTime);

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


    ////


    @SuppressLint("CheckResult")
    private void getDoctorDetailsById(String doctorId) {

        showProgressDialog();

        String token = SharedData.getTOKEN(this);
        Log.d("tag11111", " token: " + token);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";
        String url = Api.BASE_URL + Api.doctors_details + "/" + doctorId;

        RetrofitApiClient.getApiInterface().doctorDetailsById(url, authorization, accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());
                            Log.d("tag11111", " doctorId: " + doctorId);


                            if (response.code() == 401) {
                                Intent intent = new Intent(DoctorDetailsActivity.this, LoginActivity.class);
                                intent.putExtra("SENDER_ACTIVITY_NAME", "");
                                startActivity(intent);
                            }


                            if (response.isSuccessful()) {
                                closeProgressDialog();

                                DoctorDetailsModel specialistDoctor = response.body();

                                if (response.code() == 200) {

                                    DoctorDetailsModel model = response.body();

                                    String responseString = response.message();

                                    doctor_name = model.getName();
                                    doctorSpecialities = model.getSpeciality();
//                                    doctorExperiencesYear = model;
//                                    bmdc_no = model.getBmdcNo().toString();
                                    doctorDegrees = model.getDegree();
                                    doctorFee = model.getFee();

                                    String imageUrl = Api.BASE_URL_IMAGE_ASSET + model.getImage();
                                    if (model.getImage() == null || model.getImage().isEmpty()){
                                        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_doctor1));
                                    }else {
                                        Glide.with(this).load(imageUrl).into(imageView);
                                    }

//                                    Glide.with(this).load(imageUrl).into(imageView);

                                    Log.d("tag11111", " responseString: " + responseString);
                                    Log.d("tag11111", " doctor_name: " + doctor_name);
                                    Log.d("tag11111", " doctorSpecialities: " + doctorSpecialities);
                                    Log.d("tag11111", " bmdc_no: " + bmdc_no);
                                    Log.d("tag11111", " doctorDegrees: " + doctorDegrees);
                                    Log.d("tag11111", " doctor_name: " + doctor_name);

                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            boolean wrapInScrollView = true;
                                            MaterialDialog dialog1 = new MaterialDialog.Builder(DoctorDetailsActivity.this)
                                                    .customView(R.layout.material_dialog_photo, wrapInScrollView)
                                                    .build();

                                            ImageView iv_dialog_fullscreen = dialog1.getCustomView().findViewById(R.id.iv_dialog_fullscreen);
                                            Glide.with(DoctorDetailsActivity.this).load(imageUrl).into(iv_dialog_fullscreen);

                                            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                            WindowManager.LayoutParams wmlp = dialog1.getWindow()
                                                    .getAttributes();
                                            wmlp.width = android.view.WindowManager.LayoutParams.MATCH_PARENT;
                                            wmlp.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
                                            dialog1.show();

                                            dialog1.setCancelable(true);
                                            dialog1.setCanceledOnTouchOutside(true);
                                        }
                                    });


                                    setDoctorData();


                                    availableDayList = model.getAvailableDays();

                                    adapter.setData(availableDayList);
                                    adapter.notifyDataSetChanged();


                                } else {
                                    new MaterialDialog.Builder(DoctorDetailsActivity.this)
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


//    public static void setTodayAdapterPosition(int position) {
//        todayAdapterPosition = position;
//        recyclerView1.scrollToPosition(position);
//    }


//    public static void setInvisibleMicroTimeSlot() {
//        linearMicroTimeSlot.setVisibility(View.GONE);
//    }


    private void initComponemt() {
        recyclerView1 = findViewById(R.id.recyclerView1);
//        recyclerView4 = findViewById(R.id.recyclerView4);
        imageView = findViewById(R.id.imageView);
        tv_doctor_name = findViewById(R.id.tv_doctor_name);
        tv_doctor_speciality = findViewById(R.id.tv_doctor_speciality);
        tv_doctor_experience = findViewById(R.id.tv_doctor_experience);
        tv_doctor_experience2 = findViewById(R.id.tv_doctor_experience2);
        tv_bmdc_no = findViewById(R.id.tv_bmdc_no);
        tv_doctor_degree = findViewById(R.id.tv_doctor_degree);
        tv_doctor_designation = findViewById(R.id.tv_doctor_designation);
        tv_doctor_fee = findViewById(R.id.tv_doctor_fee);
        tv_note = findViewById(R.id.tv_note);
        tv_doctor_availability_day = findViewById(R.id.tv_doctor_availability_day);
        tv_calling_text = findViewById(R.id.tv_calling_text);
        tv_appointment = findViewById(R.id.tv_appointment);
//        tv_selected_date = findViewById(R.id.tv_selected_date);
//        tv_selected_date2 = findViewById(R.id.tv_selected_date2);
        tv_view_more = findViewById(R.id.tv_view_more);
//        linearCall = findViewById(R.id.linearCall);
//        linearCallStop = findViewById(R.id.linearCallStop);
        linearDesig = findViewById(R.id.linearDesig);
        linearNote = findViewById(R.id.linearNote);
//        linearVideoConsultation = findViewById(R.id.linearVideoConsultation);
        linearDoctorTiming = findViewById(R.id.linearDoctorTiming);
//        linear_my_doctor = findViewById(R.id.linear_my_doctor);
//        linear_all_doctor = findViewById(R.id.linear_all_doctor);
//        linearSubTimeSlot = findViewById(R.id.linearSubTimeSlot);
//        linearMicroTimeSlot = findViewById(R.id.linearMicroTimeSlot);

        tv_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }


    private void loadRecyclerView() {
        adapter = new AvailableDaysAdapter(this);
        recyclerView1.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false); // Set Horizontal Layout Manager for Recycler view
        recyclerView1.setLayoutManager(mLayoutManager);
//        adapter.setData2(doctorDateList, doctorTimeSlotNumberList, doctor_name);
        adapter.notifyDataSetChanged();
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