package com.iglushkov.calories.helpers;

import android.app.Application;

import com.iglushkov.calories.database.entity.User;

public class GlobalVariables extends Application {

    private User user;
    private boolean isUserAuth = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsUserAuth() {
        return isUserAuth;
    }

    public void setIsUserAuth(boolean isUserAuth) {
        this.isUserAuth = isUserAuth;
    }

    public void createUserAuthSession(User user)
    {
        this.setIsUserAuth(true);
        this.setUser(user);
    }
}
