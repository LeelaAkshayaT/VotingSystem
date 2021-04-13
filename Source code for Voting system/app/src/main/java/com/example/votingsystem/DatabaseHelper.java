package com.example.votingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="registration.db";
    public static final String TABLE_NAME ="votinglist";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 = "voted_for";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE votinglist (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, voted_for TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("votinglist",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }
    public void getvotes (String username, String vote){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE votinglist SET voted_for = "+"'" + vote +"'" +" WHERE username = " +"'"+ username +"'" );
        db.close();

    }

    public int resultsandroid (){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor =db.rawQuery(" SELECT * FROM votinglist WHERE voted_for = 'android'  ", null);
        cursor.moveToFirst();
        int  count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public int resultsios() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor =db.rawQuery(" SELECT * FROM votinglist WHERE voted_for = 'ios'  ", null);
        cursor.moveToFirst();
        int  count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
}
