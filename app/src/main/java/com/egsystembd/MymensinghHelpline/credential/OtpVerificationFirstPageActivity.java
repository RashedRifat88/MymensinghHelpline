package com.egsystembd.MymensinghHelpline.credential;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.egsystembd.MymensinghHelpline.R;

public class OtpVerificationFirstPageActivity extends AppCompatActivity {

    boolean passwordVissible;
    private TextView tv_send;
    private EditText etFullName, etEmail, etPhoneNumber, etPassword;
    private boolean nameIsEmpty = true;
    private boolean emailIsEmpty = true;
    private boolean phoneIsEmpty = true;
    private boolean passwordIsEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_first_page);

        initStatusBar();
        initComponents();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initComponents() {

        tv_send = findViewById(R.id.tv_send);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
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
                    etPhoneNumber.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etPhoneNumber.setPadding(45, 45, 45, 45);
                    phoneIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etPhoneNumber.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etPhoneNumber.setPadding(45, 45, 45, 45);
                    phoneIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });

        tv_send.setOnClickListener(view -> {

            hideKeyboard();

            if (!phoneIsEmpty && etPhoneNumber.length() == 11) {
                otpSendApi();
            } else {
                Toast.makeText(this, "Please correctly write your mobile number.", Toast.LENGTH_SHORT).show();
            }


        });


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



    private void checkSignupButtonActive() {
        if (!phoneIsEmpty && etPhoneNumber.length() == 11) {
            tv_send.setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            tv_send.setPadding(45, 40, 45, 40);
        } else {
            tv_send.setBackground(getResources().getDrawable(R.drawable.rounded_corner5));
            tv_send.setPadding(45, 40, 45, 40);
        }
    }


    private void otpSendApi() {
        //            Intent intent = new Intent(OtpVerificationFirstPageActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
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