package com.iglushkov.calories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class RegistrationActivity extends AppCompatActivity {

    protected static AppDatabase db = MainDisplayActivity.db;

    TextView regName;
    TextView regPhone;
    TextView regPassword;
    TextView regPassword2;
    Button regBtn;
    CheckBox isPolicyAccepted;
    TextView authLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        findMainElements();


        authLink.setOnClickListener(v -> {
            goToAuthScreen();
        });

        regBtn.setOnClickListener(v -> {
            if (!validationUserInput()) {
                return;
            }

            User userCreated = saveNewUserInDB();
            ((GlobalVariables) getApplication()).createUserAuthSession(userCreated);
            goToMainScreen();
        });

    }

    private User saveNewUserInDB() {
        User userNew = new User();
        userNew.name = regName.getText() + "";
        userNew.password = regPassword.getText() + "";
        userNew.phone = regPhone.getText() + "";
        userNew.isRememberMe = false;

        db.userDao().insertAll(userNew);
        return db.userDao().findByPhone(userNew.phone);
    }

    private boolean validationUserInput() {
        if (!isPolicyAccepted.isChecked()) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.policy_must_be_accepted);
            return false;
        }

        if (regName.getText().toString().isEmpty() || regPhone.getText().toString().isEmpty() || regPassword.getText().toString().isEmpty() || regPassword2.getText().toString().isEmpty()) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.fields_have_to_be_filled);
            return false;
        }

        User user = db.userDao().findByPhone(regPhone.getText().toString());
        if (user != null) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.user_was_found);
            return false;
        }

        if (!regPassword.getText().toString().equals(regPassword2.getText().toString())) {
            NotifyHelper.showFastToast(getApplicationContext(), R.string.passwords_are_not_equal);
            return false;
        }

        return true;
    }

    private void goToAuthScreen() {
        Intent intent = new Intent(RegistrationActivity.this, AuthActivity.class);
        RegistrationActivity.this.startActivity(intent);
    }

    private void goToMainScreen() {
        Intent intent = new Intent(RegistrationActivity.this, MainDisplayActivity.class);
        RegistrationActivity.this.startActivity(intent);
    }

    private void findMainElements() {
        regName = findViewById(R.id.personName);
        regPhone = findViewById(R.id.regPhone);
        regPassword = findViewById(R.id.regPassword);
        regPassword2 = findViewById(R.id.regPassword2);
        regBtn = findViewById(R.id.regBtn);
        isPolicyAccepted = findViewById(R.id.privateData);
        authLink = findViewById(R.id.authLink);
    }

}
