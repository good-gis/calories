package com.example.callories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.callories.database.AppDatabase;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.callories.databinding.ActivityMainDisplayBinding;

public class MainDisplayActivity extends AppCompatActivity {

    public static AppDatabase db;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainDisplayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").allowMainThreadQueries().build();
        super.onCreate(savedInstanceState);

        authUserProcess();

        binding = ActivityMainDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainDisplay.toolbar);
        binding.appBarMainDisplay.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_support, R.id.nav_food_data)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_display);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_display, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_display);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void authUserProcess()
    {
        processRememberAuthOfUser();

        if (!((GlobalVariables) this.getApplication()).getIsUserAuth()) {
            goToAuthActivity();
        }
    }

    private void goToAuthActivity() {
        Intent intent = new Intent(MainDisplayActivity.this, AuthActivity.class);
        MainDisplayActivity.this.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        NotifyHelper.showFastToast(getApplicationContext(), R.string.sing_out_help);
    }

    private void processRememberAuthOfUser() {
        if (db.userDao().findRememberMeUser() != null) {
            ((GlobalVariables) this.getApplication()).setIsUserAuth(true);
        }
    }
}