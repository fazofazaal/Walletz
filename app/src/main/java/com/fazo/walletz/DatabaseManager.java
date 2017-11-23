package com.fazo.walletz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Fazo on 19/11/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseManager";

    //Database Name
    private static final String DATABASE_NAME = "walletzDB";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String TABLE_INCOME = "income";
    private static final String TABLE_EXPENSE = "expense";
    private static final String TABLE_BALANCE = "balance";

    //Common Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    //Income Table - Column Names
    private static final String KEY_INCOME_AMOUNT = "income_amount";

    //Expense Table - Column Names
    private static final String KEY_EXPENSE_AMOUNT = "expense_amount";
    private static final String KEY_EXPENSE_CATEGORY = "expense_category";

    //Balance Table - Column Names
    private static final String KEY_BALANCE_AMOUNT = "balance_amount";

    // Table Create Statements
    // Income table create statement
    private static final String CREATE_TABLE_INCOME = "CREATE TABLE "
            + TABLE_INCOME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INCOME_AMOUNT
            + " REAL," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Expense table create statement
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE " + TABLE_EXPENSE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EXPENSE_AMOUNT + " REAL,"
            + KEY_EXPENSE_CATEGORY + " TEXT," + KEY_CREATED_AT + " DATETIME" + ")";

    // Balance table create statement
    private static final String CREATE_TABLE_BALANCE = "CREATE TABLE "
            + TABLE_BALANCE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_BALANCE_AMOUNT + " REAL," + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_INCOME);
        db.execSQL(CREATE_TABLE_EXPENSE);
        db.execSQL(CREATE_TABLE_BALANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BALANCE);

        onCreate(db);
    }

    // ------------------------ "income" table methods ----------------//

    //Creating an Income Entry
    public void createIncome(IncomeModel incomeModel) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();

            //Add values to the set
            values.put(KEY_INCOME_AMOUNT, incomeModel.getIncome_amount());
            values.put(KEY_CREATED_AT, getDateTime());

            // insert row
            db.insert(TABLE_INCOME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    // ------------------------ "expense" table methods ----------------//

    //Creating an Expense Entry
    public void createExpense(ExpenseModel expenseModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();

            //Add values to the set
            values.put(KEY_EXPENSE_AMOUNT, expenseModel.getExpense_amount());
            values.put(KEY_EXPENSE_CATEGORY, expenseModel.getCategory());
            values.put(KEY_CREATED_AT, getDateTime());

            //insert row
            db.insert(TABLE_EXPENSE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }

    }

    // ------------------------ "balance" table methods ----------------//

    public void createBalance(BalanceModel balanceModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();

            //Add values to the set
            values.put(KEY_BALANCE_AMOUNT, balanceModel.calcBalance());
            values.put(KEY_CREATED_AT, getDateTime());

            //insert row
            db.insert(TABLE_BALANCE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }

    } //String selectQuery = "SELECT * FROM " + TABLE_BALANCE + " ORDER BY "+ KEY_ID + "DESC LIMIT 1";

    public IncomeModel getData(){
        IncomeModel obj = new IncomeModel();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BALANCE + " ORDER BY "+ KEY_ID + "DESC LIMIT 1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            obj.income_amount = cursor.getDouble(cursor.getColumnIndex(KEY_INCOME_AMOUNT));
        }
        cursor.close();
        return obj;
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


    // --------------------------other methods--------------------------//

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
