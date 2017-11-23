package com.fazo.walletz;

/**
 * Created by Fazo on 19/11/2017.
 */

public class BalanceModel {

    int id;
    double balance_amount;
    double income_amount;
    double expense_amount;
    String created_at;

    // constructors
    public BalanceModel() {
    }

    public BalanceModel(double income_amount) {
        this.income_amount = income_amount;
    }

    public BalanceModel(String expense_amt) {
        this.expense_amount = Double.parseDouble(expense_amt);
    }


    public BalanceModel(int id, double balance_amount, String created_at) {
        this.id = id;
        this.balance_amount = balance_amount;
        this.created_at = created_at;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setBalance_amount(double balance_amount) {
        this.balance_amount = balance_amount;
    }

    public void setIncome_amount(double income_amount) { this.income_amount = income_amount; }

    public void setExpense_amount(double expense_amount) { this.expense_amount = expense_amount; }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }


    //calculate balance
    public double calcBalance(){
        double incDef = 0;
        double expDef = 0;

        this.income_amount = incDef + this.income_amount;
        incDef = this.income_amount;

        this.expense_amount = expDef + this.expense_amount;
        expDef = this.expense_amount;

        return incDef - expDef;
    }


    // getters
    public long getId() {
        return this.id;
    }

    public double getBalance_amount() {
        return calcBalance();
    }

    public String getCreatedAt() {
        return this.created_at;
    }



}
