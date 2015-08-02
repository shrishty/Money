package com.abc.money.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shrishty on 7/25/15.
 */
public class UserMoneyDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MONEYAPP.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " +
                    UserMoneyContract.UserMoney.TABLE_NAME + "(" +
                    UserMoneyContract.UserMoney.USER_MONEY_ID + " INTEGER PRIMARY KEY," +
                    UserMoneyContract.UserMoney.USER_MONEY_NAME + " TEXT," +
                    UserMoneyContract.UserMoney.USER_MONEY_TOTAL + " INTEGER," +
                    UserMoneyContract.UserMoney.USER_ID + " INTEGER);";

    public UserMoneyDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("Database operations: ", "Database created or opened");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("Database operations: ", "Query Created");
    }

    public void addUserMoney(String name, int total,int user_id, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserMoneyContract.UserMoney.USER_MONEY_NAME, name);
        contentValues.put(UserMoneyContract.UserMoney.USER_MONEY_TOTAL, total);
        contentValues.put(UserMoneyContract.UserMoney.USER_ID, user_id);
        db.insert(UserMoneyContract.UserMoney.TABLE_NAME, null, contentValues);
        Log.e("Database operations: ", "User Info inserted");
    }

    public Cursor getUserMoneyInformation(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {UserMoneyContract.UserMoney.USER_MONEY_NAME, UserMoneyContract.UserMoney.USER_MONEY_TOTAL};
        Log.e("money123", "in getUserMoenyInfo");
        try {
            cursor = db.query(UserMoneyContract.UserMoney.TABLE_NAME, projections, null, null, null, null,null);
            return cursor;
        } catch (Exception e){
            Log.e("money123", "error");
        }
        Log.e("money123", "in getUserMoenyInfo");
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
