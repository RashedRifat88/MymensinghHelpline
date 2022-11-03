package com.egsystembd.MymensinghHelpline.credential;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.egsystembd.MymensinghHelpline.MainActivity;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.model.LoginModel;
import com.egsystembd.MymensinghHelpline.model.RegisterModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    boolean passwordVissible;
    private ConstraintLayout lay_pass;
    private ImageView iv_hi;
    private TextView tv_Terms_conditions, tv_sign_in, tv_register, tv_forgot_password;
    private EditText etName, etPhone, etPhoneNumber, etPassword;
    private boolean nameIsEmpty = true;
    private boolean phoneIsEmpty = true;
    private boolean emailIsEmpty = true;
    private boolean passwordIsEmpty = true;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initStatusBar();
        initComponents();
        checkSharedPrefData();

//        Log.d("tag11111",  "response.code(): " + "5555");

    }

    private void checkSharedPrefData() {
        if (SharedData.getMOBILE(this) ==null || SharedData.getMOBILE(this).isEmpty()){

        }else {
            etPhone.setText(SharedData.getMOBILE(this));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initComponents() {

        animationView = findViewById(R.id.animationView);
        lay_pass = findViewById(R.id.lay_pass);

        tv_sign_in = findViewById(R.id.tv_sign_in);
        tv_register = findViewById(R.id.tv_register);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_Terms_conditions = findViewById(R.id.tv_Terms_conditions);

        iv_hi = findViewById(R.id.iv_hi);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
//        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);



        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    etPhone.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etPhone.setPadding(45, 40, 45, 40);
                    phoneIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etPhone.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etPhone.setPadding(45, 40, 45, 40);
                    phoneIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });


        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    lay_pass.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etPassword.setPadding(45, 40, 45, 40);
                    passwordIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    lay_pass.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etPassword.setPadding(45, 40, 45, 40);
                    passwordIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });


        tv_sign_in.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        tv_register.setOnClickListener(view -> {
            hideKeyboard();

            if (!phoneIsEmpty && !passwordIsEmpty) {
                registerApi();
            } else {
                Toast.makeText(this, "Please fill up all the fields correctly", Toast.LENGTH_SHORT).show();
            }

        });


        tv_forgot_password.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        iv_hi.setOnClickListener(view -> {

        });
//        iv_hi.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_shake));


    }




    private void hideKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) this.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    public void showHidePass(View view) {

        if(view.getId()==R.id.show_pass_btn){
            if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_show2);
                //Show Password
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_show);
                //Hide Password
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }



    private void checkSignupButtonActive() {
        if (!phoneIsEmpty && !passwordIsEmpty) {
            tv_register.setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            tv_register.setPadding(45, 45, 45, 45);

//            tv_register.setOnClickListener(view -> {
//                Intent intent = new Intent(RegisterActivity.this, FavouriteTopicPickActivity.class);
//                startActivity(intent);
//            });

        } else {
            tv_register.setBackground(getResources().getDrawable(R.drawable.rounded_corner5));
            tv_register.setPadding(45, 45, 45, 45);
        }
    }





    @SuppressLint("CheckResult")
    private void registerApi() {

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();
        String deviceModel = android.os.Build.MODEL;
        String deviceManufacturer = android.os.Build.MANUFACTURER;
        String deviceProduct = Build.PRODUCT;
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        String deviceName = deviceManufacturer + " " + deviceModel + " android_sdk: "+ sdkVersion;
        String token = "";
        String authorization = "Bearer" + " " ;
        String accept = "application/json";


        RetrofitApiClient.getApiInterface().user_registration(authorization, accept,  name,  phone, password, deviceName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            Log.d("tag3344", response.toString());
                            Log.d("tag3344", response.message().toString());
                            Log.d("tag3344", String.valueOf(response.code()));

                            if (response.code() == 200) {

                                RegisterModel loginModel = response.body();
//                                boolean status = loginModel.getStatus();
//                                boolean status = loginModel.getSuccess();
//                                String status2 = loginModel.getMessage();
                                String userToken = loginModel.getToken().toString();

                                SharedData.saveTOKEN(RegisterActivity.this, userToken);

                                SharedData.saveIS_USER_REGISTERED(this, true);
                                SharedData.saveIS_USER_LOGGED_IN(this, true);

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
//

                            new MaterialDialog.Builder(RegisterActivity.this)
                                    .title("Status")
                                    .content(response.code())
                                    .positiveText("")
                                    .negativeText("Ok")
                                    .show();

                            if (response.isSuccessful()) {

                                String responseString = response.message();
                                Log.d("tag3344", "Response String:");

//                                Response<AccountVerificationModel> loginModel1 = response;
                                RegisterModel loginModel = response.body();

                                String userToken = loginModel.getToken().toString();
//                                String message = loginModel.getMessage();


//                                Log.d("tag3344", "Response Status: " + status);

                                Log.d("tag20", "userToken in onClickVerify: ");


//                                customProgress.showProgress(RegisterActivity.this, "Verifying... Please wait...", true);

                                if (response.code() == 200) {


                                    SharedData.saveTOKEN(RegisterActivity.this, userToken);

                                    SharedData.saveIS_USER_REGISTERED(this, true);
                                    SharedData.saveIS_USER_LOGGED_IN(this, true);

                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
//


                            } else {
                                Log.d("tag20", "else is called ");
                            }
                        },
                        error -> {
                            Log.d("tag20", error.getMessage() );

                        },
                        () -> {
                            Log.d("tag3344", "onComplete");
                        }
                );


    }





    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.background, this.getTheme()));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.background));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }




}