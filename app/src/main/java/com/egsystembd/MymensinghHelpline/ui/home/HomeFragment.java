package com.egsystembd.MymensinghHelpline.ui.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TypefaceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.credential.LoginActivity;
import com.egsystembd.MymensinghHelpline.data.SharedData;
import com.egsystembd.MymensinghHelpline.databinding.FragmentHomeBinding;
import com.egsystembd.MymensinghHelpline.model.AppHomeModel;
import com.egsystembd.MymensinghHelpline.model.HospitalListModel;
import com.egsystembd.MymensinghHelpline.retrofit.Api;
import com.egsystembd.MymensinghHelpline.retrofit.RetrofitApiClient;
import com.egsystembd.MymensinghHelpline.ui.home.adapter.HomeModuleAdapter;
import com.egsystembd.MymensinghHelpline.ui.home.hospital_clinic.HospitalAndClinicActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private HomeModuleAdapter homeModuleAdapter;

    List<String> home_module_name_eng_list;
    List<String> home_module_name_ban_list = new ArrayList<>();
    List<String> home_module_image_list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView(root);

        initComponent();
        loadListData();

        loadRecyclerView();


        topScrollText("");
        topSlider();
        appHomeapi();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        loadListData();
//        loadRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("TAG5566",
                "onAttach: ");
        super.onAttach(context);
    }


    @SuppressLint("CheckResult")
    public void appHomeapi() {

        showProgressDialog();

        String token = SharedData.getTOKEN(getContext());
        Log.d("tag11111", " token: " + token);
        String authorization = "Bearer" + " " + token;
        String accept = "application/json";

        RetrofitApiClient.getApiInterface().appHome(authorization, accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());


                            if (response.code() == 401) {
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                intent.putExtra("SENDER_ACTIVITY_NAME", "");
                                startActivity(intent);
                            }


                            if (response.isSuccessful()) {
                                closeProgressDialog();

                                response.body(); // do something with that
                                Log.d("tag11111", " response.body(): " + response.body());

                                AppHomeModel model = response.body();
                                String status = model.getStatus();

                                String topScrollTextList = "";

                                if (status.equalsIgnoreCase("success")) {


//                                    ময়মনসিংহ হেল্পলাইন এ আপনাকে স্বাগতম !! | ময়মনসিংহ হেল্পলাইন এ আপনাকে স্বাগতম !! ময়মনসিংহ হেল্পলাইন এ আপনাকে স্বাগতম !!

                                    String topScrollText = model.getDataResponse().getTopScrollText().toString();
                                    String topScrollText2 = model.getDataResponse().getTopScrollText2().toString();
                                    String topScrollText3 = model.getDataResponse().getTopScrollText3().toString();
                                    String topScrollText4 = model.getDataResponse().getTopScrollText4().toString();
                                    String topScrollText5 = model.getDataResponse().getTopScrollText5().toString();

                                    if (topScrollText != null) {
                                        topScrollTextList = topScrollTextList  + topScrollText;
                                    }
                                    if (topScrollText2 != null) {
                                        topScrollTextList = topScrollTextList + " | " + topScrollText2;
                                    }
                                    if (topScrollText3 != null) {
                                        topScrollTextList = topScrollTextList + " | " + topScrollText3;
                                    }
                                    if (topScrollText4 != null) {
                                        topScrollTextList = topScrollTextList + " | " + topScrollText4;
                                    }
                                    if (topScrollText5 != null) {
                                        topScrollTextList = topScrollTextList + " | " + topScrollText5;
                                    }

                                    Log.d("tag11111", " topScrollTextList: " + topScrollTextList);
                                    topScrollText(topScrollTextList);

                                    ArrayList<SlideModel> imageList = new ArrayList<>();
                                    String imageUrl1 = Api.BASE_URL_HOME_SLIDER + model.getDataResponse().getTopSliderImg1();
                                    String imageUrl2 = Api.BASE_URL_HOME_SLIDER + model.getDataResponse().getTopSliderImg2();
                                    String imageUrl3 = Api.BASE_URL_HOME_SLIDER + model.getDataResponse().getTopSliderImg3();
                                    imageList.add(new SlideModel(imageUrl1, ScaleTypes.FIT));
                                    imageList.add(new SlideModel(imageUrl2, ScaleTypes.FIT));
                                    imageList.add(new SlideModel(imageUrl3, ScaleTypes.FIT));

                                    binding.imageSlider.setImageList(imageList);

                                    Log.d("tag11111", " imageUrl1: " + imageUrl1);
                                    Log.d("tag11111", " imageUrl2: " + imageUrl2);
                                    Log.d("tag11111", " imageUrl3: " + imageUrl3);


                                } else {
                                    new MaterialDialog.Builder(getContext())
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

    private void topScrollText(String scrollText) {

        binding.tvMarquee.setSelected(true);

        binding.tvMarquee.setText(scrollText);
        Paint textPaint = binding.tvMarquee.getPaint();
        String text = binding.tvMarquee.getText().toString();//get text
        int width = Math.round(textPaint.measureText(text));//measure the text size
        ViewGroup.LayoutParams params = binding.tvMarquee.getLayoutParams();
        params.width = width;
        binding.tvMarquee.setLayoutParams(params); //refine

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;

        //this is optional. do not scroll if text is shorter than screen width
        //remove this won't effect the scroll
        if (width <= screenWidth) {
            //All text can fit in screen.
            return;
        }
        //set the animation
        TranslateAnimation slide = new TranslateAnimation(0, -width, 0, 0);
        slide.setDuration(50000);
        slide.setRepeatCount(Animation.INFINITE);
        slide.setRepeatMode(Animation.RESTART);
        slide.setInterpolator(new LinearInterpolator());
        binding.tvMarquee.startAnimation(slide);
    }


    private void initView(View root) {

//        binding.etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                filter(s.toString());
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
    }

    private void topSlider() {

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.medical, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.admission, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.service, ScaleTypes.FIT));

        binding.imageSlider.setImageList(imageList);
    }


    private void loadListData() {
        home_module_name_eng_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_eng_list));
//        home_module_name_ban_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_ban_list));
        home_module_image_list = Arrays.asList(getResources().getStringArray(R.array.home_module_image_list));
    }

    private void initComponent() {
//        recyclerView = findViewById(R.id.recyclerView);
    }


    private void loadRecyclerView() {

        loadListData();

        home_module_name_eng_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_eng_list));
//        home_module_name_ban_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_ban_list));
        home_module_image_list = Arrays.asList(getResources().getStringArray(R.array.home_module_image_list));

        homeModuleAdapter = new HomeModuleAdapter(getActivity());
        binding.recyclerView.setAdapter(homeModuleAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 4);
        binding.recyclerView.setLayoutManager(mLayoutManager);
//        homeModuleAdapter.setData(home_module_name_eng_list, home_module_name_ban_list, home_module_image_list);
        homeModuleAdapter.setData(home_module_name_eng_list, home_module_image_list);
        homeModuleAdapter.notifyDataSetChanged();
    }


    private void filter(String text) {
        List<String> filteredList = new ArrayList<>();
//        List<String> filteredListBan = new ArrayList<>();
        List<String> filteredListImg = new ArrayList<>();
        List<Integer> filteredPosition = new ArrayList<>();

        for (String item : home_module_name_eng_list) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredPosition.add(home_module_name_eng_list.indexOf(item));
            }
        }

//        for (int position : filteredPosition) {
//            filteredListBan.add(home_module_name_ban_list.get(position));
//            filteredListImg.add(home_module_image_list.get(position));
//        }

        homeModuleAdapter.filterList(filteredList, filteredListImg);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    ProgressDialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


}