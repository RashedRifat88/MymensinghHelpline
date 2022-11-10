package com.egsystembd.MymensinghHelpline.ui.home.home_pathology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.model.AllTestModel;
import com.egsystembd.MymensinghHelpline.model.UploadTestOrderModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HomePathologyCheckoutActivity extends AppCompatActivity {

    ArrayList<AllTestModel.Test> selectedTests;
    ArrayList<String> testList = new ArrayList<>();
    ArrayList<String> testPriceList = new ArrayList<>();
    ArrayList<String> hospitalList = new ArrayList<>();
    private String subTotalPrice;
    private Double sumOfPrice = 0.0;
    private EditText etName, etPhone;
    private Button btn_order_now;

    String pat_name = "";
    String pat_mobile = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pathology_checkout);

        selectedTests = (ArrayList<AllTestModel.Test>) getIntent().getSerializableExtra("selected_tests");

        initComponents();
        addTable();
        calculateData();
    }

    private void initComponents() {

        btn_order_now = findViewById(R.id.btn_order_now);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);

        pat_name = SharedData.getUSER_NAME(this);
        pat_mobile = SharedData.getUSER_MOBILE(this);

        etName.setText(pat_name);
        etPhone.setText(pat_mobile);


        btn_order_now.setOnClickListener(v -> {

            pat_name = etName.getText().toString();
            pat_mobile = etPhone.getText().toString();

            if (selectedTests != null) {

                if (pat_name.isEmpty() || pat_mobile.isEmpty()) {
                    Toast.makeText(this, "Please fill up necessary fields ", Toast.LENGTH_SHORT).show();
                } else {
                    uploadTestOrder(pat_name, pat_mobile);
                }

            } else {
                Toast.makeText(this, "Please select at least one ", Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void calculateData() {

        testList = new ArrayList<>();
        testPriceList = new ArrayList<>();
        hospitalList = new ArrayList<>();

        for (AllTestModel.Test m : selectedTests) {
            testList.add(m.getTestName());
            testPriceList.add(m.getTestPrice());
            hospitalList.add(m.getHospitalName());
        }


    }


    private void addTable() {
        calculateTotalPrice();
        addHeaders();
        addData();
        addFooters();
//        addFooters2();
    }


    private TextView getTextView(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextSize(12);
        tv.setTextColor(color);
        tv.setPadding(10, 25, 10, 25);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setGravity(Gravity.CENTER);
//        tv.setOnClickListener(CheckoutActivity.this);
        return tv;
    }

    private TextView getTextView2(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextSize(14);
        tv.setTextColor(color);
        tv.setPadding(10, 25, 10, 25);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParams());
        tv.setGravity(Gravity.CENTER);
//        tv.setOnClickListener(CheckoutActivity.this);
        return tv;
    }

    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }


    public void addHeaders() {
        TableLayout tl = findViewById(R.id.table1);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Test", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_100)));
        tr.addView(getTextView(0, "Price", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_100)));
//        tr.addView(getTextView(0, "Quantity", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_100)));
//        tr.addView(getTextView(0, "Total", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_100)));
        tl.addView(tr, getTblLayoutParams());
    }


    public void addFooters() {
        TableLayout tl = findViewById(R.id.table1);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Total Price", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.light_green_100)));
//        tr.addView(getTextView(0, "", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.light_green_100)));
//        tr.addView(getTextView(0, "", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.light_green_100)));
        tr.addView(getTextView(0, subTotalPrice, Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.light_green_100)));
        tl.addView(tr, getTblLayoutParams());
    }

    public void addFooters2() {
        TableLayout tl = findViewById(R.id.table1);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Total Payable Price", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_50)));
//        tr.addView(getTextView(0, "", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_50)));
//        tr.addView(getTextView(0, "", Color.BLACK, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_50)));
//        tr.addView(getTextView2(0, String.valueOf(totalPayableNetPrice), Color.RED, Typeface.BOLD, ContextCompat.getColor(this, R.color.deep_orange_50)));
        tl.addView(tr, getTblLayoutParams());
    }


    private void calculateTotalPrice() {
        for (AllTestModel.Test m : selectedTests) {
            sumOfPrice = sumOfPrice + Double.parseDouble(m.getTestPrice());
        }

        DecimalFormat df = new DecimalFormat("####0.00");

        sumOfPrice = Double.parseDouble(new DecimalFormat("####0.00").format(sumOfPrice));
        subTotalPrice = "\u09F3 " + String.valueOf(sumOfPrice);
    }


    /**
     * add the data to the table
     **/
    public void addData() {
        int numProducts = selectedTests.size();
        TableLayout tl = findViewById(R.id.table1);
        for (int i = 0; i < numProducts; i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());

            tr.addView(getTextView(i, selectedTests.get(i).getTestName(), Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorWhite)));
            tr.addView(getTextView(i, selectedTests.get(i).getTestPrice(), Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorWhite)));
//            tr.addView(getTextView(i, quantities.get(i), Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorWhite)));
//            tr.addView(getTextView(i, selectedTests.get(i).getTestPrice(), Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorWhite)));

            tl.addView(tr, getTblLayoutParams());
        }


    }


    @SuppressLint("CheckResult")
    public void uploadTestOrder(String pat_name, String pat_mobile) {

        String token = SharedData.getTOKEN(this);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";

        String pat_id = SharedData.getUSER_ID(this);
//        String hospital_name = "";
//        String test_list = "hospital_namee";
//        String test_price_list = "test_price_liste";
        String has_prescription = "no";

//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("test_prescription", "image.jpg", requestFile);
        MultipartBody.Part body = null;

        Log.d("tag111666", " authorization: " + authorization);
        Log.d("tag111666", " body: " + body);

        RetrofitApiClient.getApiInterface().upload_prescription(authorization, accept, body, pat_id, pat_name, pat_mobile, hospitalList,
                        testList, testPriceList, has_prescription)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag111666", " response.code(): " + response.code());
                            Log.d("tag111666", " response.message()(): " + response.message());
                            Log.d("tag111666", " response.body()()(): " + response.body());

                            if (response.code() == 404) {

                            }

//                            progressDialog.dismiss();

                            if (response.isSuccessful()) {

                                UploadTestOrderModel model = response.body();

                                String message = model.getMessage();
                                String status = model.getStatus();

//                                Log.d("tag111666", " success: " + success);
//                                Log.d("tag111666", " model: " + model);
//
//                                String imageUrl = model.getData().getPhoto().toString();


                                if (status.equalsIgnoreCase("success")) {

//                                    Glide.with(this).load(imageUrl).into(iv_user_image);

                                    new MaterialDialog.Builder(this)
                                            .title("Status")
                                            .content(message)
                                            .positiveText("")
                                            .negativeText("Ok")
                                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();

                                } else {
                                    new MaterialDialog.Builder(this)
                                            .title("Status")
                                            .content(message)
                                            .positiveText("")
                                            .negativeText("Ok")
                                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();
                                }

                            } else {
//                                response.errorBody().string(); // do something with that
//                                Gson gson = new Gson();
//                                ErrorResponseGeneric errorResponse = gson.fromJson(
//                                        response.errorBody().string(),
//                                        ErrorResponseGeneric.class);
                            }
                        },
                        error -> {
                            Log.d("tag111666", " error: " + error.getMessage());
                        },
                        () -> {
                            Log.d("tag111666", " response.code(): ");
                        }
                );

    }


}