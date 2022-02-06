package com.example.callories;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.Food;
import com.example.callories.helpers.DateHelper;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class AddFoodActivity extends AppCompatActivity {

    protected static AppDatabase db = MainDisplayActivity.db;

    /* @TODO реализировать сохрание всех параметров еды */
    TextView foodName;
    TextView foodCal;
    TextView foodProtein;
    TextView foodFat;
    TextView foodCarb;
    TextView foodQty;
    Button foodBtn;

    private TextView foodDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodName = findViewById(R.id.foodName);
        foodCal = findViewById(R.id.foodCal);
        foodProtein = findViewById(R.id.foodProtein);
        foodFat = findViewById(R.id.foodFat);
        foodCarb = findViewById(R.id.foodCarb);
        foodQty = findViewById(R.id.foodQty);
        foodBtn = findViewById(R.id.addFoodBtn);

        foodDate = (TextView) findViewById(R.id.foodDate);
        foodDate.setText(DateHelper.getDate());
        foodDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(AddFoodActivity.this,
                    android.R.style.Theme_Material_Dialog, mDateSetListener, year, month, day);
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            String date = day + "/" + month + "/" + year;
            foodDate.setText(date);
        };

        foodBtn.setOnClickListener(view -> {
            if (validateFoodData()) {
                saveFoodData();
            } else {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.notice_food_min_validation);
            }
        });
    }


    protected boolean validateFoodData() {
        return !foodName.getText().toString().equals("") && !foodCal.getText().toString().equals("");
    }


    protected void saveFoodData() {
        Food food = new Food();
        food.userId = ((GlobalVariables) getApplication()).getUser().uid;
        food.date = foodDate.getText().toString();
        food.foodName = foodName.getText().toString();
        int cal;
        try {
            cal = Integer.parseInt(foodCal.getText().toString());
        } catch (NumberFormatException e) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.notice_food_cal_validation);
            return;
        }

        food.cal = cal;
        db.foodDao().insert(food);
        goToMainDisplayActivity();
    }

    private void goToMainDisplayActivity() {
        Intent intent = new Intent(AddFoodActivity.this, MainDisplayActivity.class);
        AddFoodActivity.this.startActivity(intent);
    }

}