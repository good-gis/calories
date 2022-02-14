package com.example.callories.ui.imt;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ImtCalculation {

    public static double imtCalculate(int heightCm, int bellyCm, int neckCm, String gender) {
        double c;
        if (gender.equals("woman")) {
            c = 19.2;
        } else {
            c = 10.1;
        }
        double resultD = Math.abs(c - (0.239 * heightCm) + (0.8 * bellyCm) - (0.5 * neckCm));

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#.##", otherSymbols);
        String result = decimalFormat.format(resultD);

        return Double.parseDouble(result);
    }
}
