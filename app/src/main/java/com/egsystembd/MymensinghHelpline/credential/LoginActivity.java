package com.egsystembd.MymensinghHelpline.credential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.hotspot2.pps.Credential;
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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.egsystembd.MymensinghHelpline.MainActivity;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.model.LoginModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    boolean passwordVissible;
    private ConstraintLayout lay_pass;
    private ImageView iv_hi;
    private TextView tv_Terms_conditions, tv_sign_up, tv_sign_in, tv_forgot_password;
    private EditText etFullName, etEmail, etPhoneNumber, etPassword;
    private boolean nameIsEmpty = true;
    private boolean emailIsEmpty = true;
    private boolean phoneIsEmpty = true;
    private boolean passwordIsEmpty = true;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initStatusBar();
        initComponents();
        checkSharedPrefData();

//        Log.d("tag11111",  "response.code(): " + "5555");

    }

    private void checkSharedPrefData() {
        if (SharedData.getMOBILE(this) ==null || SharedData.getMOBILE(this).isEmpty()){

        }else {
            etEmail.setText(SharedData.getMOBILE(this));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initComponents() {

        animationView = findViewById(R.id.animationView);
        lay_pass = findViewById(R.id.lay_pass);

        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_sign_in = findViewById(R.id.tv_sign_in);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_Terms_conditions = findViewById(R.id.tv_Terms_conditions);

        iv_hi = findViewById(R.id.iv_hi);

//        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
//        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);

//        /////terms conditions starts
//        String text = "By continuing you agree to our Terms & Conditions and Privacy Policy";
//        SpannableString ss = new SpannableString(text);
//
//        ClickableSpan clickableSpan1 = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(LoginActivity.this, "Terms & Conditions", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(getResources().getColor(R.color.colorPrimary2));
//                ds.setUnderlineText(false);
//            }
//        };
//
//        ClickableSpan clickableSpan2 = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(LoginActivity.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(getResources().getColor(R.color.colorPrimary2));
//                ds.setUnderlineText(false);
//            }
//        };
//
//        ss.setSpan(clickableSpan1, 31, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.setSpan(clickableSpan2, 54, 68, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        tv_Terms_conditions.setText(ss);
//        tv_Terms_conditions.setMovementMethod(LinkMovementMethod.getInstance());
//        /////terms conditions ends

//        etPassword.setOnTouchListener((view, motionEvent) -> {
//            final int DRAWABLE_RIGHT = 2;
//            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                if (motionEvent.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                    int selection = etPassword.getSelectionEnd();
//                    if (passwordVissible) {
////                        etPassword.setInputType(InputType.TYPE_CLASS_TEXT |
////                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_lock), null, this.getResources().getDrawable(R.drawable.ic_show), null);
////                        etPassword.setSelection(etPassword.getText().length());
//                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                        passwordVissible = false;
//                    } else {
//                        etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                        etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_lock), null, this.getResources().getDrawable(R.drawable.ic_show2), null);
//                        etPassword.setSelection(etPassword.getText().length());
//                        passwordVissible = true;
//                    }
//                    etPassword.setSelection(selection);
//
//                    return true;
//                }
//            }
//            return false;
//        });



        etEmail.addTextChangedListener(new TextWatcher() {
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
                    etEmail.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etEmail.setPadding(45, 40, 45, 40);
                    emailIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etEmail.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etEmail.setPadding(45, 40, 45, 40);
                    emailIsEmpty = true;
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


        tv_sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });


        tv_sign_in.setOnClickListener(view -> {
            hideKeyboard();

            if (!emailIsEmpty && !passwordIsEmpty) {
                loginApi();
            } else {
                Toast.makeText(this, "Please fill up all the fields correctly" + "rrr", Toast.LENGTH_SHORT).show();
            }

        });


        tv_forgot_password.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
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
        if (!emailIsEmpty && !passwordIsEmpty) {
            tv_sign_in.setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            tv_sign_in.setPadding(45, 45, 45, 45);

//            tv_sign_in.setOnClickListener(view -> {
//                Intent intent = new Intent(LoginActivity.this, FavouriteTopicPickActivity.class);
//                startActivity(intent);
//            });

        } else {
            tv_sign_in.setBackground(getResources().getDrawable(R.drawable.rounded_corner5));
            tv_sign_in.setPadding(45, 45, 45, 45);
        }
    }



//    @SuppressLint("CheckResult")
//   private void loginApi() {
//
//        String userName = "";
//        if (SharedData.getMOBILE(this) ==null || SharedData.getMOBILE(this).isEmpty()){
//            userName = etEmail.getText().toString();
//        }else {
//            userName = SharedData.getMOBILE(this);
//        }
////
////        String otpToken = SharedData.getTOKEN_OTP(this);;
//
//        animationView.setVisibility(View.VISIBLE);
//
////        String token = SharedData.getTOKEN(this);
////        String name = etFullName.getText().toString();
//        String password = etPassword.getText().toString();
//        String deviceModel = android.os.Build.MODEL;
//        String deviceManufacturer = android.os.Build.MANUFACTURER;
//        String deviceProduct = Build.PRODUCT;
//        int sdkVersion = android.os.Build.VERSION.SDK_INT;
//        String deviceName = "";
////        deviceName = deviceManufacturer + " " + deviceModel + " android_sdk: "+ sdkVersion;
//        String token = "";
//        String authorization = "Bearer" + " " ;
//        String accept = "application/json";
//
////        Log.d("tag11111", " deviceModel: " + deviceModel);
////        Log.d("tag11111", " deviceManufacturer: " + deviceManufacturer);
////        Log.d("tag11111", " deviceProduct: " + deviceProduct);
////
////
//        RetrofitApiClient.getApiInterface().user_login(authorization, accept,  userName, password, deviceName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response -> {
//
//                            Toast.makeText(this, "response.code():", Toast.LENGTH_SHORT).show();
////                            Log.d("tag11111",  "response.code(): " + response.code());
////                            progressDialog.dismiss();
//
//                            if (response.code() == 401){
//                                animationView.setVisibility(View.GONE);
//
//                                new MaterialDialog.Builder(LoginActivity.this)
//                                        .title("Login Status")
//                                        .content("User or password is not correct. Please try again.")
//                                        .positiveText("")
//                                        .negativeText("Ok")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                            }
//                                        })
//                                        .onNegative(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                dialog.cancel();
//                                            }
//                                        })
//                                        .show();
//
//                            }
//
//                            LoginModel itemModel = response.body();
////                            String message = itemModel.getMessage();
//
//
//                            if (response.isSuccessful()) {
//
//                                animationView.setVisibility(View.GONE);
//
////                                LoginModel itemModel = response.body();
////                                Log.d("tag11111", " itemModel: " + itemModel);
//
//
//                                String user_phone = itemModel.getUser().getPhone();
//                                String user_image = itemModel.getUser().getProfilePhotoUrl();
//                                String userToken = itemModel.getToken();
//                                String user_name = itemModel.getUser().getName();
//                                String user_id = itemModel.getUser().getId().toString();
//
//                                SharedData.saveTOKEN(this, userToken);
//                                SharedData.saveMOBILE(this, user_phone);
//                                SharedData.saveUSER_NAME(this, user_name);
//                                SharedData.saveDOCTOR_ID(this, user_id);
//
//                                if (response.code() == 200){
//
//                                    SharedData.saveIS_USER_REGISTERED(this, true);
//                                    SharedData.saveIS_USER_LOGGED_IN(this, true);
//
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//
//
//                            } else {
////                                Log.d("tag11111", " response.errorBody().string(): " + response.errorBody().string());
//
//                                new MaterialDialog.Builder(LoginActivity.this)
//                                        .title("Login Status")
//                                        .content(response.errorBody().string())
//                                        .positiveText("Yes")
//                                        .negativeText("No")
//                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                                            }
//                                        })
//                                        .onNegative(new MaterialDialog.SingleButtonCallback() {
//                                            @Override
//                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                                dialog.cancel();
//                                            }
//                                        })
//                                        .show();
//
//                            }
//
//                        },
//                        error -> {
//
////                            Log.d("tag11111", " error: " + error.getMessage());
//                        },
//                        () -> {
//                            Log.d("tag11111", " response.code(): ");
//                        }
//
//                );
//
//    }
//


   @SuppressLint("CheckResult")
   private void loginApi() {

        String userName = "user@gmail.com";
        String password = "12345678";
        String deviceName = "";
        String token = "";
        String authorization = "Bearer" + " " ;
        String accept = "application/json";


        RetrofitApiClient.getApiInterface().user_login(authorization, accept,  userName, password, deviceName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            Log.d("tag3344", response.toString());
                            Log.d("tag3344", response.message().toString());
                            Log.d("tag3344", String.valueOf(response.code()));

                            if (response.code() == 200) {

                                LoginModel loginModel = response.body();
//                                boolean status = loginModel.getStatus();
//                                boolean status = loginModel.getSuccess();
//                                String status2 = loginModel.getMessage();
                                String userToken = loginModel.getToken().toString();

                                SharedData.saveTOKEN(LoginActivity.this, userToken);

                                SharedData.saveIS_USER_REGISTERED(this, true);
                                SharedData.saveIS_USER_LOGGED_IN(this, true);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
//

                            new MaterialDialog.Builder(LoginActivity.this)
                                    .title("Status")
                                    .content(response.code())
                                    .positiveText("")
                                    .negativeText("Ok")
                                    .show();

                            if (response.isSuccessful()) {

//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                                hideProgress();
//                                customProgress.hideProgress();
                                String responseString = response.message();
                                Log.d("tag3344", "Response String:");

//                                Response<AccountVerificationModel> loginModel1 = response;
                                LoginModel loginModel = response.body();
//                                boolean status = loginModel.getStatus();
//                                boolean status = loginModel.getSuccess();
//                                String status2 = loginModel.getMessage();
                                String userToken = loginModel.getToken().toString();
//                                String message = loginModel.getMessage();
//                                String user_name = loginModel.getData().getName();
//                                String email = loginModel.getData().getEmail();
//                                String address = loginModel.getData().getAddress();
//                                String verification = loginModel.getData().getVerification();
//                                versionNameFromServer = loginModel.getData().getVersion_name();

//                                Log.d("tag3344", "Response Status: " + status);
//                                Log.d("tag20", "Response Status1: " + status);
//                                Log.d("tag20", "message " + message);
//                                Log.d("tag20", "address " + address);
//                                Log.d("tag20", "versionNameFromServer: " + versionNameFromServer);
//                                Log.d("tag20", "versionName: " + versionName);

                                Log.d("tag20", "userToken in onClickVerify: ");



//                                customProgress.showProgress(LoginActivity.this, "Verifying... Please wait...", true);

                                if (response.code() == 200) {


                                    SharedData.saveTOKEN(LoginActivity.this, userToken);

                                    SharedData.saveIS_USER_REGISTERED(this, true);
                                    SharedData.saveIS_USER_LOGGED_IN(this, true);

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
//


                            } else {
                                Log.d("tag20", "else is called ");
                            }
                        },
                        error -> {
//                            customProgress.hideProgress();
//                            new MaterialDialog.Builder(LoginActivity.this)
//                                    .title("Status")
//                                    .content("Login Status: " )
//                                    .positiveText("")
//                                    .negativeText("Ok")
//                                    .show();

                            Log.d("tag20", error.getMessage() );

                        },
                        () -> {
                            Log.d("tag3344", "onComplete");
//                            customProgress.hideProgress();


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