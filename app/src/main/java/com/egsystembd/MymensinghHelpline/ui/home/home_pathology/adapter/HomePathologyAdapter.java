package com.egsystembd.MymensinghHelpline.ui.home.home_pathology.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.egsystembd.MymensinghHelpline.R;
import com.egsystembd.MymensinghHelpline.model.AllTestModel;
import com.egsystembd.MymensinghHelpline.model.DoctorListModel;
import com.egsystembd.MymensinghHelpline.retrofit.Api;
import com.egsystembd.MymensinghHelpline.ui.home.doctor.doctor_department.doctor_list.DoctorDetailsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePathologyAdapter extends RecyclerView.Adapter<HomePathologyAdapter.DoctorListViewHolder> {

    List<AllTestModel.Test> testList = new ArrayList<>();
    List<String> home_module_name_ban_list = new ArrayList<>();
    List<String> home_module_image_list = new ArrayList<>();
    private ArrayList<String> selectedItemIds = new ArrayList<String>();
    private ArrayList<AllTestModel.Test> selectedTests = new ArrayList<>();
    Context context;

    Cursor dataCursor;

    String categoryName = "";

    String title;
    String category_id;
    private List<String> memberListFiltered = new ArrayList<>();

    private static long COUNTDOWN_IN_MILLIS = 0;
    private long timeLeftInMillis;
    private CountDownTimer countDownTimer;


    private AdapterCallback adapterCallback;

    public HomePathologyAdapter(Context context) {
        this.context = context;

//        try {
//            adapterCallback = ((AdapterCallback) context);
//        } catch (ClassCastException e) {
////            throw new ClassCastException("Activity must implement AdapterCallback.", e);
//        }

    }


    public void setData(List<AllTestModel.Test> testList) {
        this.testList = testList;
//        this.home_module_name_ban_list = home_module_name_ban_list;
    }

    public void filterList(List<AllTestModel.Test> testList) {
        this.testList = testList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return testList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public DoctorListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_home_pathology_list, parent, false);
        DoctorListViewHolder myViewHolder = new DoctorListViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listener.onItemClick(v, myViewHolder.getPosition());
            }
        });
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final HomePathologyAdapter.DoctorListViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        TextView txtSlNo = holder.txtSlNo;
        LinearLayout linear1 = holder.linear1;


        ImageView iv_circle = holder.iv_circle;
        ImageView iv_1 = holder.iv_1;

        TextView tv_name = holder.tv_name;
        TextView tv_price = holder.tv_price;
        TextView tv_hospital_name = holder.tv_hospital_name;


        CardView card1 = holder.card1;
        CheckBox check_box = holder.check_box;

//        img_clock.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
////        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
////        cardview.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fall_down_animation));
//
//
        AllTestModel.Test test = testList.get(position);



        String name = test.getTestName();
        String price = test.getTestPrice();
        String hospital_name = test.getHospitalName();


        tv_name.setText(name);
        tv_price.setText("\u09F3 "+price+ " /=");
        tv_hospital_name.setText(hospital_name);


//        String imageUrl = Api.BASE_URL_IMAGE_ASSET + test.getImage();
//        Glide.with(context).load(imageUrl).into(iv_1);


//        tv_doctor_name_ban.setText(title_ban);
//        iv_circle.setColorFilter(ContextCompat.getColor(context, R.color.light_blue_100), android.graphics.PorterDuff.Mode.SRC_IN);
//        Glide.with(context).load(title.getName()).into(iv_1);

//        int aPosition = position + 1;
//
//        if (aPosition % 5 == 1) {
//            linear2.setBackgroundResource(R.color.light_color1);
//            img_clock.setColorFilter(ContextCompat.getColor(context, R.color.light_deep_color1), android.graphics.PorterDuff.Mode.SRC_IN);
//        } else if (aPosition % 5 == 4) {
//            linear2.setBackgroundColor(context.getResources().getColor(R.color.light_color2));
//            img_clock.setColorFilter(ContextCompat.getColor(context, R.color.light_deep_color2), android.graphics.PorterDuff.Mode.SRC_IN);
//        } else if (aPosition % 5 == 3) {
//            linear2.setBackgroundColor(context.getResources().getColor(R.color.light_color3));
//            img_clock.setColorFilter(ContextCompat.getColor(context, R.color.light_deep_color3), android.graphics.PorterDuff.Mode.SRC_IN);
//        } else if (aPosition % 5 == 2) {
//            linear2.setBackgroundColor(context.getResources().getColor(R.color.light_color4));
//            img_clock.setColorFilter(ContextCompat.getColor(context, R.color.light_deep_color4), android.graphics.PorterDuff.Mode.SRC_IN);
//        } else if (aPosition % 5 == 0) {
//            linear2.setBackgroundColor(context.getResources().getColor(R.color.light_color5));
//            img_clock.setColorFilter(ContextCompat.getColor(context, R.color.light_deep_color5), android.graphics.PorterDuff.Mode.SRC_IN);
//        }


        check_box.setChecked(false);

        check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (check_box.isChecked()) {
                    selectedItemIds.add(test.getId().toString());
                    selectedTests.add(test);
                }

                if (!check_box.isChecked()) {
                    selectedItemIds.remove(test.getId().toString());
                    selectedTests.remove(test);
                }

            }
        });


        linear1.setOnClickListener(view -> {


            if (check_box.isChecked()) {
                check_box.setChecked(false);
            }else {
                check_box.setChecked(true);
            }


            if (check_box.isChecked()) {
                selectedItemIds.add(test.getId().toString());
                selectedTests.add(test);
            }

            if (!check_box.isChecked()) {
                selectedItemIds.remove(test.getId().toString());
                selectedTests.remove(test);
            }


        });



    }


    public ArrayList<String> getSelectedIds() {
        return selectedItemIds;
    }

    public ArrayList<AllTestModel.Test> getSelectedTests() {
        return selectedTests;
    }



    public long milliseconds(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            return timeInMilliseconds;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public static String getFormattedDateFromTimestamp(long timestampInMilliSeconds, String dateStyle) {
        Date date = new Date();
        date.setTime(timestampInMilliSeconds);
        String formattedDate = new SimpleDateFormat(dateStyle).format(date);
        return formattedDate;
    }



    class DoctorListViewHolder extends RecyclerView.ViewHolder {

        TextView txtSlNo;
        TextView tv_name;
        TextView tv_price;
        TextView tv_hospital_name;

        LinearLayout linear1;
        LinearLayout linear2;
        RelativeLayout relative1;

        ImageView iv_circle;
        ImageView iv_1;

        CardView card1;
        CheckBox check_box;
        ProgressBar progressBar;

        public DoctorListViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_hospital_name = itemView.findViewById(R.id.tv_hospital_name);

            iv_circle = itemView.findViewById(R.id.iv_circle);
            iv_1 = itemView.findViewById(R.id.iv_1);

            card1 = itemView.findViewById(R.id.card1);

            check_box = itemView.findViewById(R.id.check_box);

            linear1 = itemView.findViewById(R.id.linear1);
//            linear2 = itemView.findViewById(R.id.linear2);
//
//            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }


//    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
//    public void filter(CharSequence charText) {
//
//        List<String> filteredList = new ArrayList<>();
//        String charString = charText.toString();
//
//        if (charString.length() == 0) {
//////         memberListFiltered = dataSet;
////         filteredList.addAll(dataSet);
//            Log.i("tag", String.valueOf("1:  "+charString.length())+dataSet);
//
//
//        }
//
//        if (charString.isEmpty() || charString.equalsIgnoreCase("")) {
//            memberListFiltered = titleList;
//        } else {
////         List<MemberInfoModel.Result> filteredList = new ArrayList<>();
//            for (String row : titleList) {
//                if (
//                        row.toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getMobile().toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getYear().toLowerCase().contains(charString.toLowerCase())
////                                ||
////                                row.getMember_id_String().toLowerCase().contains(charString.toLowerCase())
////                             ||
////                             row.getTakaAmount().toLowerCase().contains(charString.toLowerCase()) ||
////                             row.getPaymentStatus().toLowerCase().contains(charString.toLowerCase())
//                ) {
//                    filteredList.add(row);
//                }
//            }
//
//            Log.i("tag", "2:  "+String.valueOf(charString.length())+filteredList);
//
//            memberListFiltered = filteredList;
//        }
//
////     Filter.FilterResults filterResults = new Filter.FilterResults();
////     filterResults.values = memberListFiltered;
//        this.setData(memberListFiltered, memberListFiltered, memberListFiltered);
//        this.notifyDataSetChanged();
//    }


    public interface AdapterCallback {
        void onMethodCallback(List<String> favTopicList);

        void onMethodCallback();
    }


}

