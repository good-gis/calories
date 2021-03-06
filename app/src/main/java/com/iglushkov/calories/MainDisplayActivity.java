package com.iglushkov.calories;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.databinding.ActivityMainDisplayBinding;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;
import com.google.android.material.navigation.NavigationView;

public class MainDisplayActivity extends AppCompatActivity {

    public static AppDatabase db;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").allowMainThreadQueries().build();
        super.onCreate(savedInstanceState);

        authUserProcess();

        ActivityMainDisplayBinding binding = ActivityMainDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainDisplay.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_support,
                R.id.nav_food_data,
                R.id.nav_about_app,
                R.id.nav_activity_data,
                R.id.nav_bazal_met,
                R.id.nav_day_has_to_free_cal,
                R.id.nav_extra_weight,
                R.id.nav_imt,
                R.id.nav_user_data,
                R.id.nav_weight_data)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_display);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_display, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_display);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void authUserProcess() {
        if (((GlobalVariables) this.getApplication()).getIsUserAuth()) {
            return;
        }
        goToAuthScreen();
    }

    private void goToAuthScreen() {
        Intent intent = new Intent(MainDisplayActivity.this, AuthActivity.class);
        MainDisplayActivity.this.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        NotifyHelper.showFastToast(getApplicationContext(), R.string.sing_out_help);
    }
}