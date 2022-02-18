package com.iglushkov.calories;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.Food;
import com.iglushkov.calories.helpers.DateHelper;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class AddFoodActivity extends AppCompatActivity {

    protected static AppDatabase db = MainDisplayActivity.db;

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
        foodDate = findViewById(R.id.foodDate);

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
        try {
            Integer.parseInt(foodCal.getText().toString());
        } catch (NumberFormatException e) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.notice_food_cal_validation);
            return false;
        }

        return !foodName.getText().toString().equals("");
    }


    protected void saveFoodData() {
        Food food = new Food();
        food.userId = ((GlobalVariables) getApplication()).getUser().uid;
        food.date = foodDate.getText().toString();
        food.foodName = foodName.getText().toString();
        food.cal = Integer.parseInt(foodCal.getText().toString());

        if (foodQty.getText().toString().isEmpty()) {
            food.qty = "0 гр";
        } else {
            food.qty = foodQty.getText().toString();
        }

        if (foodProtein.getText().toString().isEmpty()) {
            food.protein = 0;
        } else {
            food.protein = Integer.parseInt(foodProtein.getText().toString());
        }

        if (foodFat.getText().toString().isEmpty()) {
            food.fat = 0;
        } else {
            food.fat = Integer.parseInt(foodFat.getText().toString());
        }

        if (foodCarb.getText().toString().isEmpty()) {
            food.carb = 0;
        } else {
            food.carb = Integer.parseInt(foodCarb.getText().toString());
        }

        db.foodDao().insert(food);
        goToMainDisplayActivity();
    }

    private void goToMainDisplayActivity() {
        Intent intent = new Intent(AddFoodActivity.this, MainDisplayActivity.class);
        AddFoodActivity.this.startActivity(intent);
    }

}