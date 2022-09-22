package com.egsystembd.MymensinghHelpline;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egsystembd.MymensinghHelpline.databinding.ActivityMainWithDrawerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.egsystembd.MymensinghHelpline.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private ActivityMainWithDrawerBinding binding2;
    private AppBarConfiguration mAppBarConfiguration;

    static TextView tv_user_name, tv_user_profile_completion_status;
    View headerView;
    static ImageView iv_profile_image;
    String userName;
    LinearLayout linear1, linear10;
    DrawerLayout drawerLayout;
    ImageView iv_menu1;
    ActionBarDrawerToggle toggle;
    NavigationView navigationViewDrawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_with_drawer);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
////        binding2 = ActivityMainWithDrawerBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_my_history, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(navView, navController);

        initStatusBar();

        initNavigationMenu();


    }


    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            decor.setSystemUiVisibility(decor.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  light
        }
    }

    private void initNavigationMenu() {

        iv_menu1 = findViewById(R.id.iv_menu1);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationViewDrawer = findViewById(R.id.nav_view_drawer);
        navigationViewDrawer.setNavigationItemSelectedListener(this);

        navigationViewDrawer.setItemIconTintList(null);

        iv_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

//        navigationViewDrawer.getMenu().getItem(0).setActionView(R.layout.layout_arrow_right);
//        navigationViewDrawer.getMenu().getItem(1).setActionView(R.layout.layout_arrow_right);
//        navigationViewDrawer.getMenu().getItem(2).setActionView(R.layout.layout_arrow_right);
//        navigationViewDrawer.getMenu().getItem(3).setActionView(R.layout.layout_arrow_right);
//        navigationViewDrawer.getMenu().getItem(4).setActionView(R.layout.layout_arrow_right);
//        navigationViewDrawer.getMenu().getItem(5).setActionView(R.layout.layout_arrow_right);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        headerView = navigationViewDrawer.getHeaderView(0);
        tv_user_profile_completion_status = headerView.findViewById(R.id.tv_user_profile_completion_status);
        tv_user_name = headerView.findViewById(R.id.tv_user_name);
        iv_profile_image = headerView.findViewById(R.id.iv_profile_image);
        linear10 = headerView.findViewById(R.id.linear10);


//        if (SharedData.getUserName(this) == null) {
//            tv_user_profile_completion_status.setText("");
//        } else {
//            tv_user_profile_completion_status.setText(SharedData.getUserProfileCompletionPercent(this));
//        }
//
//        if (SharedData.getUserName(this) == null) {
//            tv_user_name.setText("User Name");
//        } else {
//            tv_user_name.setText(SharedData.getUserName(this));
//        }


//        String pro_pic = SharedData.getUserImage(this);
//        Log.d("tag222", "pro_pic: " + pro_pic);
//        if (pro_pic == null) {
//            iv_profile_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_profile));
//        } else if (pro_pic.equalsIgnoreCase("null")) {
//            iv_profile_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_profile));
//        } else if (pro_pic.equalsIgnoreCase("")) {
//            iv_profile_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_profile));
//        } else {
//            Log.d("tag1000", "pro_pic Glide: " + pro_pic);
//            Glide.with(this).load(pro_pic).placeholder(R.drawable.ic_profile).into(iv_profile_image);
//
//        }


//        linear1 = findViewById(R.id.linear1);
//        linear10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
//
//                if (userName == null) {
//                    intent.putExtra("user_name", "User Name");
//                } else {
//                    intent.putExtra("user_name", userName);
//                }
//
//                startActivityForResult(intent, 201);
//            }
//        });
//

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

//        if (item.getItemId() == R.id.menu1) {
//
//            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
////            String menu_name = "1";
////            intent.putExtra("menuName", menu_name);
//            startActivity(intent);
//
//        }

        if (item.getItemId() == R.id.menu2) {

//            Intent intent = new Intent(MainActivity.this, InviteOthersActivity.class);
//            String menu_name = "1";
//            intent.putExtra("menuName", menu_name);
//            startActivity(intent);

        }


        if (item.getItemId() == R.id.menu3) {
//            rateApp();
        }


        if (item.getItemId() == R.id.nav_logout) {

//            SharedData.saveTOKEN(MainActivity.this, "");
//            finish();
        }

        return false;

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }



}