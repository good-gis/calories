package com.iglushkov.calories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class ChangeImtActivity extends AppCompatActivity {

    protected static AppDatabase db = MainDisplayActivity.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_imt);

        TextView newImt = findViewById(R.id.newImt);
        Button cancelBtn = findViewById(R.id.cancelImtBtn);
        Button changeBtn = findViewById(R.id.okImtBtn);


        cancelBtn.setOnClickListener(v -> onBackPressed());

        changeBtn.setOnClickListener(v -> {
            if (newImt.getText().toString().equals("")) {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.imt_change_validation);
            } else {
                User user = db.userDao().findByPhone(((GlobalVariables) getApplication()).getUser().phone);
                user.imt = Double.parseDouble(newImt.getText().toString());
                db.userDao().updateUser(user);
                goToImtActivity();
            }
        });
    }


    private void goToImtActivity() {
        Intent intent = new Intent(ChangeImtActivity.this, MainDisplayActivity.class);
        ChangeImtActivity.this.startActivity(intent);
    }
}