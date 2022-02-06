package com.example.callories.helpers;

import android.app.Application;

import com.example.callories.database.entity.User;

public class GlobalVariables extends Application {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private boolean isUserAuth = false;

    public boolean getIsUserAuth() {
        return isUserAuth;
    }

    public void setIsUserAuth(boolean isUserAuth) {
        this.isUserAuth = isUserAuth;
    }
}
