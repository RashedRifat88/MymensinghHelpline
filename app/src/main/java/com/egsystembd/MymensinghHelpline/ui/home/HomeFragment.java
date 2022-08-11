package com.egsystembd.MymensinghHelpline.ui.home;

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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.databinding.FragmentHomeBinding;
import com.egsystembd.MymensinghHelpline.ui.home.adapter.HomeModuleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private HomeModuleAdapter homeModuleAdapter;

    List<String> home_module_name_eng_list;
    List<String> home_module_name_ban_list;
    List<String> home_module_image_list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initView(root);

        loadListData();
        initComponent();

        loadRecyclerView();


        topScrollText();
        topSlider();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

//    private void topScrollText() {
//        binding.tvMarquee.setSelected(true);
//    }

    private void topScrollText() {

        binding.tvMarquee.setSelected(true);

        binding.tvMarquee.setText("    ময়মনসিংহ হেল্পলাইন এ আপনাকে স্বাগতম !! | ময়মনসিংহ হেল্পলাইন এ আপনাকে স্বাগতম !! ময়মনসিংহ হেল্পলাইন এ আপনাকে স্বাগতম !! " );
        Paint textPaint = binding.tvMarquee.getPaint();
        String text = binding.tvMarquee.getText().toString();//get text
        int width = Math.round(textPaint.measureText(text));//measure the text size
        ViewGroup.LayoutParams params =  binding.tvMarquee.getLayoutParams();
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

    private void topSlider() {

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.medical, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.admission, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.service, ScaleTypes.FIT));

        binding.imageSlider.setImageList(imageList);
    }



    private void loadListData() {
        home_module_name_eng_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_eng_list));
        home_module_name_ban_list = Arrays.asList(getResources().getStringArray(R.array.home_module_name_ban_list));
        home_module_image_list = Arrays.asList(getResources().getStringArray(R.array.home_module_image_list));
    }

    private void initComponent() {
//        recyclerView = findViewById(R.id.recyclerView);
    }



    private void loadRecyclerView() {
        homeModuleAdapter = new HomeModuleAdapter(getActivity());
        binding.recyclerView.setAdapter(homeModuleAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        homeModuleAdapter.setData(home_module_name_eng_list, home_module_name_ban_list, home_module_image_list);
        homeModuleAdapter.notifyDataSetChanged();
    }


    private void filter(String text) {
        List<String> filteredList = new ArrayList<>();
        List<String> filteredListBan = new ArrayList<>();
        List<String> filteredListImg = new ArrayList<>();
        List<Integer> filteredPosition = new ArrayList<>();

        for (String item : home_module_name_eng_list) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredPosition.add(home_module_name_eng_list.indexOf(item));
            }
        }

        for (int position : filteredPosition) {
            filteredListBan.add(home_module_name_ban_list.get(position));
            filteredListImg.add(home_module_image_list.get(position));
        }

        homeModuleAdapter.filterList(filteredList, filteredListBan, filteredListImg);
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}