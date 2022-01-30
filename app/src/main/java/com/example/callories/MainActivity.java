package com.example.callories;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.callories.database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-database").allowMainThreadQueries().build();
        auth();
        super.onCreate(savedInstanceState);
    }

    public void auth() {
        Auth auth = new Auth(db);
        boolean isAuth = auth.isRememberedUser();
        if (isAuth) {
            setContentView(R.layout.main_activity);
        } else {
            Intent intent = new Intent(MainActivity.this, AuthActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }


}