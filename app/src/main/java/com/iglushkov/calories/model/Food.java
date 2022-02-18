package com.iglushkov.calories.model;


public class Food {

    public Food(int uid, String foodName, String qty, int cal, int protein, int fat, int carb, String date, int userId) {
        this.uid = uid;
        this.foodName = foodName;
        this.qty = qty;
        this.cal = cal;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.date = date;
        this.userId = userId;
    }

    private int uid;

    private String foodName;

    private String qty;

    private int cal;

    private int protein;

    private int fat;

    private int carb;

    private String date;

    private int userId;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
