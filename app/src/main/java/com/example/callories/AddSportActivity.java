package com.example.callories;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.Activity;
import com.example.callories.helpers.DateHelper;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class AddSportActivity extends AppCompatActivity {

    protected static AppDatabase db = MainDisplayActivity.db;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView activityDate;
    private TextView activityName;
    private TextView activityMin;
    private TextView activityCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sport);

        activityDate = (TextView) findViewById(R.id.addActivityDate);
        activityName = (TextView) findViewById(R.id.addSportName);
        activityMin = (TextView) findViewById(R.id.addTimeOfSport);
        activityCal = (TextView) findViewById(R.id.addCalSportBurned);
        Button addActivityBtn = findViewById(R.id.addSportBtn);


        activityDate.setText(DateHelper.getDate());
        activityDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(AddSportActivity.this,
                    android.R.style.Theme_Material_Dialog, mDateSetListener, year, month, day);
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = day + "/" + month + "/" + year;
            activityDate.setText(date);

        };

        addActivityBtn.setOnClickListener(view -> {
            if (validateFoodData()) {
                saveFoodData();
            } else {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.notice_sport_min_validation);
            }
        });
    }


    protected boolean validateFoodData() {
        return !activityDate.getText().toString().equals("")
                && !activityName.getText().toString().equals("")
                && !activityMin.getText().toString().equals("")
                && !activityCal.getText().toString().equals("");
    }


    protected void saveFoodData() {
        Activity activity = new Activity();
        activity.userId = ((GlobalVariables) getApplication()).getUser().uid;
        activity.date = activityDate.getText().toString();
        activity.activityName = activityName.getText().toString();
        try {
            activity.calBurned = Integer.parseInt(activityCal.getText().toString());
            activity.timeOfExecution = Integer.parseInt(activityMin.getText().toString());
        } catch (NumberFormatException e) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.notice_sport_min_validation);
            return;
        }
        db.activityDao().insert(activity);
        goToMainDisplayActivity();
    }

    private void goToMainDisplayActivity() {
        Intent intent = new Intent(AddSportActivity.this, MainDisplayActivity.class);
        AddSportActivity.this.startActivity(intent);
    }
}