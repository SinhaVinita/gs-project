package com.mkyong.hashing;
public class TradePosition {

    private String name;
    private double amount;

    public TradePosition(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getAssetName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }

    public void addAsset(double amountToAdd) {
        this.amount = this.amount + amountToAdd;
    }



}
