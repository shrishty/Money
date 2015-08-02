package com.abc.money.main;

/**
 * Created by shrishty on 7/26/15.
 */
public class DataProvider {
    private String name;
    private int amount;

    public DataProvider(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
