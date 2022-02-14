package com.example.callories.helpers;

import android.icu.util.Calendar;

public class DateHelper {

    public static String getDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return day + "/" + month + "/" + year;
    }

    public static String getDateAfterDays(int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return day + "/" + month + "/" + year;
    }

}
