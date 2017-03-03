package com.company.buylist.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler {

//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "buyList";
//
//    private static final String CREATE_PRODUCT_DEFINITION = "CREATE TABLE PRODUCT_DEFINITION(" +
//            "ID INTEGER PRIMARY KEY," +
//            "NAME TEXT NOT NULL," +
//            "CATEGORY_NAME TEXT)";
//    private static final String CREATE_PRODUCT = "CREATE TABLE PRODUCT(" +
//            "ID INTEGER PRIMARY KEY," +
//            "PRODUCT_DEFINITION_ID INTEGER NOT NULL," +
//            "QUANTITY INTEGER NOT NULL DEFAULT 1," +
//            "QUANTITY_UNIT TEXT," +
//            "PRICE REAL," +
//            "PRICE_UNIT TEXT)";
//
//
//    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_PRODUCT_DEFINITION);
//        db.execSQL(CREATE_PRODUCT);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        //TODO: посмотреть
//    }

//    public void addProductDefinition(ProductDefinition productDefinition) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, productDefinition.getCategoryName()); // Contact Name
//        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number
//
//        // Inserting Row
//        db.insert(TABLE_CONTACTS, null, values);
//        db.close(); // Closing database connection
//    }
//
//    public void addContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName()); // Contact Name
//        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number
//
//        // Inserting Row
//        db.insert(TABLE_CONTACTS, null, values);
//        db.close(); // Closing database connection
//    }
}
