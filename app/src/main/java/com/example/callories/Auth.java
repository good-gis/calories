package com.example.callories;

import com.example.callories.database.AppDatabase;
import com.example.callories.database.entity.User;

public class Auth {

    protected AppDatabase db;

    public Auth(AppDatabase dbInstance) {
        db = dbInstance;
    }

    public boolean isRememberedUser() {
        User user = db.userDao().findRememberMeUser();
        return user != null;
    }
}
