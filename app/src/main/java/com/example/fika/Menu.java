package com.example.fika;

import java.util.ArrayList;

public class Menu {
    private String foodName, foodDesc;
    private double foodPrice;

    public Menu() {
    }

    public Menu(String foodName, String foodDesc, double foodPrice) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
