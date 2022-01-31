package com.example.callories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.callories.database.AppDatabase;
import com.example.callories.helpers.GlobalVariables;

public class MainActivity extends AppCompatActivity {

    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").allowMainThreadQueries().build();
        super.onCreate(savedInstanceState);

        if (((GlobalVariables) this.getApplication()).getIsUserAuth()) {

            setContentView(R.layout.main_activity);
            Button singOut = findViewById(R.id.singOut);

            singOut.setOnClickListener(v -> {
                ((GlobalVariables) getApplication()).setIsUserAuth(false);
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                MainActivity.this.startActivity(intent);
            });

        } else {
            goToAuthActivity();
        }
    }

    public void goToAuthActivity() {
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        MainActivity.this.startActivity(intent);
    }

}