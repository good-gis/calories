package com.example.callories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.User;
import com.example.callories.helpers.GlobalVariables;
import com.example.callories.helpers.NotifyHelper;

public class RegistrationActivity extends AppCompatActivity {

    protected static AppDatabase db = MainDisplayActivity.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        TextView regName = findViewById(R.id.personName);
        TextView regPhone = findViewById(R.id.regPhone);
        TextView regPassword = findViewById(R.id.regPassword);
        TextView regPassword2 = findViewById(R.id.regPassword2);
        Button regBtn = findViewById(R.id.regBtn);
        CheckBox isPolicyAccepted = findViewById(R.id.privateData);
        TextView authLink = findViewById(R.id.authLink);

        authLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, AuthActivity.class);
            RegistrationActivity.this.startActivity(intent);
        });

        regBtn.setOnClickListener(v -> {
            if (!isPolicyAccepted.isChecked()) {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.policy_must_be_accepted);
                return;
            }

            if (regName.getText().toString().isEmpty() || regPhone.getText().toString().isEmpty() || regPassword.getText().toString().isEmpty() || regPassword2.getText().toString().isEmpty()) {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.fields_have_to_be_filled);
                return;
            }

            User user = db.userDao().findByPhone(regPhone.getText().toString());
            if (user != null) {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.user_was_found);
                return;
            }

            if (regPassword.getText().toString().equals(regPassword2.getText().toString())) {
                User userNew = new User();
                userNew.name = regName.getText() + "";
                userNew.password = regPassword.getText() + "";
                userNew.phone = regPhone.getText() + "";
                userNew.isRememberMe = false;

                db.userDao().insertAll(userNew);
                ((GlobalVariables) getApplication()).setIsUserAuth(true);
                ((GlobalVariables) getApplication()).setUser(db.userDao().findByPhone(userNew.phone));
                Intent intent = new Intent(RegistrationActivity.this, MainDisplayActivity.class);
                RegistrationActivity.this.startActivity(intent);
            } else {
                NotifyHelper.showFastToast(getApplicationContext(), R.string.passwords_are_not_equal);
            }
        });

    }

}
