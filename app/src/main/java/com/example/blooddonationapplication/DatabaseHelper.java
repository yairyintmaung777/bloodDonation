package com.example.blooddonationapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="login.db";

    public DatabaseHelper(@Nullable Context context) {super (context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Hello World");
        db.execSQL ("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " " +
                "username TEXT, password TEXT, " +
                "phoneNumber TEXT, email TEXT, BloodType TEXT, address TEXT)");

        db.execSQL ("CREATE TABLE appointment (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT, state TEXT, centre TEXT,"+
                "date TEXT, time TEXT, BloodType TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS appointment");
        onCreate(db);
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
    public boolean insertAppointment (String username, String state, String centre, String date, String time, String BloodType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("state",state);
        cv.put("centre",centre);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("BloodType",BloodType);

        long result = db.insert("appointment" , null, cv);

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
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?" ,
                new String[] {username, password});


        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false; }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
//            list.add(res.getString(res.getColumnIndex("CONTACTS_COLUMN_NAME")));
            list.add(new User(
                    res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)));
            res.moveToNext();
        }
        return list;
    }

    public List<Appointment> getAllAppointment() {
        List<Appointment> list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM appointment", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
//            list.add(res.getString(res.getColumnIndex("CONTACTS_COLUMN_NAME")));
            list.add(new Appointment(
                    res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)));
            res.moveToNext();
        }
        return list;
    }

    public List<Appointment> getSpecificAppointment(String username) {
        List<Appointment> list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM appointment WHERE username = ?", new String[]{username} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
//            list.add(res.getString(res.getColumnIndex("CONTACTS_COLUMN_NAME")));
            list.add(new Appointment(
                    res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)));
            res.moveToNext();
        }
        return list;
    }

    boolean deleteUser(String username){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("user","username" + "=?", new String[]{String.valueOf(username)}) == 1;
    }

    boolean deleteAppointment(String ID){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("appointment","ID" + "=?", new String[]{String.valueOf(ID)}) == 1;
    }

    boolean updateUser(String id,String name, String phone, String address){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UpdateUserActivity.USERNAME,name);
        contentValues.put(UpdateUserActivity.PHONE,phone);
        contentValues.put(UpdateUserActivity.ADDRESS,address);
        return db.update("user",contentValues,"id" + "=?", new String[]{String.valueOf(id)}) == 1;

    }

    boolean updateAppointment(String id, String centre, String date,String time){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UpdateUserAppointment.CENTRE,centre);
        contentValues.put(UpdateUserAppointment.DATE,date);
        contentValues.put(UpdateUserAppointment.TIME,time);
        return db.update("appointment",contentValues,"id" + "=?", new String[]{String.valueOf(id)}) == 1;

    }

    boolean checkAppointment(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM appointment WHERE username = ?" ,
                new String[] {name});
        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false; }
    }

    boolean searchAppointment(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM appointment WHERE username = ?" ,
                new String[] {name});
        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false; }
    }
}
