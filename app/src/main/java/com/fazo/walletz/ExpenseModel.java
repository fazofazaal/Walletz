package com.fazo.walletz;

/**
 * Created by Fazo on 19/11/2017.
 */

public class ExpenseModel {
    int id;
    double expense_amount;
    String category;
    String created_at;

    // constructors
    public ExpenseModel() {
    }

    public ExpenseModel(double expense_amount) {
        this.expense_amount = expense_amount;
    }

    public ExpenseModel(double expense_amount, String category) {
        this.expense_amount = expense_amount;
        this.category = category;
    }

    public ExpenseModel(double expense_amount, String category, String created_at) {
        this.expense_amount = expense_amount;
        this.category = category;
        this.created_at = created_at;
    }

    public ExpenseModel(int id, double expense_amount, String category, String created_at) {
        this.id = id;
        this.expense_amount = expense_amount;
        this.category = category;
        this.created_at = created_at;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setExpense_amount(double expense_amount) {
        this.expense_amount= expense_amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public double getExpense_amount() { return this.expense_amount;}

    public String getCategory() {
        return this.category;
    }

    public String getCreatedAt() {
        return this.created_at;
    }
}
