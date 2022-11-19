package com.example.blooddonationapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="login.db";

    public DatabaseHelper(@Nullable Context context) {super (context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Hello World");
        db.execSQL ("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " " +
                "username TEXT, password TEXT, " +
                "phoneNumber TEXT, email TEXT, BloodType TEXT, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public boolean insert (String username, String password, String phoneNumber, String email, String bloodType, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password",password);
        contentValues.put("phoneNumber",phoneNumber);
        contentValues.put("email",email);
        contentValues.put("BloodType",bloodType);
        contentValues.put("address",address);

        long result = db.insert("user" , null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUsernameAndEmail (String userid, String emailId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND email = ?",
                new String[] {userid, emailId});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkLogin (String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?" ,
//                new String[] {username, password});

        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ye AND password = 123", null);

        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false; }

    }
}
