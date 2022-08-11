package com.egsystembd.MymensinghHelpline.credential;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;

import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.broadcast_receiver.OTP_Receiver;


public class OtpVerificationSecondPageActivity extends AppCompatActivity {


    public EditText et_otp_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification_second_page);

        requestSMSPermission();

        et_otp_number = findViewById(R.id.et_otp_number);
        new OTP_Receiver().setEditText(et_otp_number);
    }

    private void requestSMSPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

}