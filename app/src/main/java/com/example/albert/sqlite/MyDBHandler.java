package com.example.albert.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.os.StrictMode;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    //Constructor
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //context & factory = Background Information
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(Products product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, product.getName());
        contentValues.put(COLUMN_DESCRIPTION, product.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, contentValues);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_NAME + " =\"" + name + "\";");
    }

    //Printout the database to the String
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("name"))!=null){
                dbString += c.getString(c.getColumnIndex("name"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
