package com.example.callories.helpers;

import android.app.Application;

public class GlobalVariables extends Application {

    private boolean isUserAuth = false;

    public boolean getIsUserAuth() {
        return isUserAuth;
    }

    public void setIsUserAuth(boolean isUserAuth) {
        this.isUserAuth = isUserAuth;
    }
}
