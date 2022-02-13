package com.example.callories.ui.imt;

public class ImtCalculation {

    public static double imtCalculate(int heightCm, int bellyCm, int neckCm, String gender) {
        double c;
        if (gender.equals("woman")) {
            c = 19.2;
        } else {
            c = 10.1;
        }

        return Math.abs(c - (0.239 * heightCm) + (0.8 * bellyCm) - (0.5 * neckCm));
    }
}
