package com.egsystembd.MymensinghHelpline.ui.home.home_pathology;

import static android.Manifest.permission.CAMERA;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.exifinterface.media.ExifInterface;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.Manifest.permission.CAMERA;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.databinding.ActivityUploadPrescriptionBinding;
import com.egsystembd.MymensinghHelpline.model.UploadPrescriptionModel;
import com.egsystembd.MymensinghHelpline.model.UploadTestOrderModel;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class UploadPrescriptionActivity extends AppCompatActivity {

    private ActivityUploadPrescriptionBinding binding;


    Bitmap myBitmap;
    Uri picUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadPrescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponents();

    }

    private void initComponents() {
        binding.btOpenGallery.setOnClickListener(v -> {
            chooseProfilePicture();
        });

        binding.btUpload.setOnClickListener(v -> {
            chooseProfilePicture();
        });
    }


    ////image part starts
    private void chooseProfilePicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_picture_upload, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView imageViewADPPCamera = dialogView.findViewById(R.id.imageViewADPPCamera);
        ImageView imageViewADPPGallery = dialogView.findViewById(R.id.imageViewADPPGallery);

        final AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();

        imageViewADPPCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions()) {
                    takePictureFromCamera();
                    alertDialogProfilePicture.dismiss();
                }
            }
        });

        imageViewADPPGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialogProfilePicture.dismiss();
            }
        });
    }

    private void takePictureFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void takePictureFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, 2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    binding.ivImage.setImageURI(selectedImageUri);

                    try {
                        InputStream is = getContentResolver().openInputStream(data.getData());
                        uploadImage(getBytes(is));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    binding.ivImage.setImageBitmap(bitmapImage);


                    try {
                        InputStream is = getContentResolver().openInputStream(data.getData());
                        uploadImage(getBytes(is));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            int cameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (cameraPermission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePictureFromCamera();
        } else
            Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show();
    }
    ////image part ends


    ///image upload api call


    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }


    @SuppressLint("CheckResult")
    public void uploadImage(byte[] imageBytes) {

        String token = SharedData.getTOKEN(this);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";

        String pat_id = "4";
        String pat_name = "pat_namee";
        String pat_mobile = "pat_mobilee";
        String hospital_name = "";
        String test_list = "hospital_namee";
        String test_price_list = "test_price_liste";
        String has_prescription = "has_prescriptione";

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
        MultipartBody.Part body = MultipartBody.Part.createFormData("test_prescription", "image.jpg", requestFile);

        Log.d("tag111666", " authorization: " + authorization);
        Log.d("tag111666", " body: " + body);

//        RetrofitApiClient.getApiInterface().upload_prescription(authorization, accept, body)
        RetrofitApiClient.getApiInterface().upload_prescription(authorization, accept, body, pat_id, pat_name, pat_mobile, hospital_name,
                        test_list, test_price_list, has_prescription)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag111666", " response.code(): " + response.code());
                            Log.d("tag111666", " response.message()(): " + response.message());
                            Log.d("tag111666", " response.body()()(): " + response.body());

                            if (response.code() == 404){

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