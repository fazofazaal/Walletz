package com.fazo.walletz;

/**
 * Created by Fazo on 19/11/2017.
 */

public class IncomeModel {

    int id;
    double income_amount;
    String created_at;

    // constructors
    public IncomeModel() {
    }

    public IncomeModel(double income_amount) {
        this.income_amount = income_amount;
    }

    public IncomeModel(double income_amount, String created_at) {
        this.income_amount = income_amount;
        this.created_at = created_at;
    }

    public IncomeModel(int id, double income_amount, String created_at) {
        this.id = id;
        this.income_amount = income_amount;
        this.created_at = created_at;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIncome_amount(double income_amount) {
        this.income_amount = income_amount;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public double getIncome_amount() {
        return this.income_amount;
    }

    public String getCreatedAt() {
        return this.created_at;
    }
}
