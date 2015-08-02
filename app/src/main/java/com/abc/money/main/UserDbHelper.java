package com.abc.money.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.xml.transform.sax.SAXResult;

/**
 * Created by shrishty on 7/25/15.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MONEYAPP.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " +
                    UserContract.NewUserInfo.TABLE_NAME + "(" +
                    UserContract.NewUserInfo.USER_ID + " INTEGER PRIMARY KEY," +
                    UserContract.NewUserInfo.USER_NAME + " TEXT," +
                    UserContract.NewUserInfo.USER_NUMBER + " TEXT," +
                    UserContract.NewUserInfo.USER_PASSWORD + " TEXT," +
                    UserContract.NewUserInfo.USER_EMAIL + " TEXT);";

    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("Database operations: ", "Database created or opened");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("Database operations: ", "Query Created");
    }

    public void addInfo(String name, String phno, String email,String password, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL, email);
        contentValues.put(UserContract.NewUserInfo.USER_NUMBER, phno);
        contentValues.put(UserContract.NewUserInfo.USER_PASSWORD, password);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("Database operations: ", "User Info inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
