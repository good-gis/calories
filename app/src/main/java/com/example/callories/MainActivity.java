package com.example.callories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.callories.database.AppDatabase;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class MainActivity extends AppCompatActivity {

    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").allowMainThreadQueries().build();
        super.onCreate(savedInstanceState);

        processRememberAuthOfUser();

        if (((GlobalVariables) this.getApplication()).getIsUserAuth()) {

            setContentView(R.layout.main_activity);
            Button singOut = findViewById(R.id.singOut);

            singOut.setOnClickListener(v -> {
                ((GlobalVariables) getApplication()).setIsUserAuth(false);
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                MainActivity.this.startActivity(intent);
            });

            ProgressBar goalBurnCalories = findViewById(R.id.goalBurnCalories);
        } else {
            goToAuthActivity();
        }
    }

    private void goToAuthActivity() {
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        MainActivity.this.startActivity(intent);
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