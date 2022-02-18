package com.iglushkov.calories.helpers;

import android.widget.Toast;

public class NotifyHelper {

    public static void showFastToast(android.content.Context context, int textResource) {
        Toast toast = Toast.makeText(context,
                textResource, Toast.LENGTH_SHORT);
        toast.show();
    }

}
