package com.iglushkov.calories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iglushkov.calories.database.AppDatabase;
import com.iglushkov.calories.database.entity.User;
import com.iglushkov.calories.helpers.GlobalVariables;
import com.iglushkov.calories.helpers.NotifyHelper;

public class AuthActivity extends AppCompatActivity {
    protected static AppDatabase db = MainDisplayActivity.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);


        CheckBox isRememberMe = findViewById(R.id.isRememberMe);
        TextView regLink = findViewById(R.id.regLink);
        Button submitButton = findViewById(R.id.submitBtn);
        TextView phone = findViewById(R.id.authPhone);
        TextView password = findViewById(R.id.authPassword);

        submitButton.setOnClickListener(v -> {
            String userPhone = phone.getText().toString();
            String userPassword = password.getText().toString();

            boolean isValidUserInput = validateUserData(userPhone, userPassword);

            if (isValidUserInput) {
                User user = db.userDao().findByPhone(userPhone);

                if (user == null) {
                    NotifyHelper.showFastToast(getApplicationContext(), R.string.user_not_found);
                } else {
                    boolean isAuthDataCorrect = authUser(user, userPassword);
                    if (isAuthDataCorrect) {
                        if (isRememberMe.isChecked()) {
                            user.isRememberMe = true;
                            db.userDao().updateUser(user);
                        }
                        ((GlobalVariables) getApplication()).setIsUserAuth(true);
                        ((GlobalVariables) getApplication()).setUser(user);
                        Intent intent = new Intent(AuthActivity.this, MainDisplayActivity.class);
                        AuthActivity.this.startActivity(intent);
                    } else {
                        NotifyHelper.showFastToast(getApplicationContext(), R.string.password_is_wrong);
                    }
                }
            }
        });
        regLink.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this, RegistrationActivity.class);
            AuthActivity.this.startActivity(intent);
        });

    }

    private boolean validateUserData(String phone, String password) {
        if (phone.isEmpty() || password.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.auth_data_not_correct, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        return true;
    }

    private boolean authUser(User user, String password) {
        return user.password.equals(password);
    }

    @Override
    public void onBackPressed() {
        NotifyHelper.showFastToast(getApplicationContext(), R.string.sing_in_help);
    }

}