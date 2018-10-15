package com.logicshore.simpleuserloginapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Registration";
    private static final String TABLE_CONTACTS = "Logindetails";

    private static final String KEY_ID = "_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_MOBILENUMBER = "mobilenumber";
    private static final String KEY_MAILID = "mailid";
    private static final String KEY_DOB = "dob";
    private static final String KEY_QUALIFICATION = "qualification";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_USER_PIC = "profilepic";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER," + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT," + KEY_MOBILENUMBER + " TEXT," + KEY_MAILID +
                " TEXT," + KEY_DOB + " TEXT," + KEY_QUALIFICATION + " TEXT," + KEY_GENDER + " TEXT," +  KEY_USER_PIC + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void insertintodb(int i,String username, String password, String mobilenumber, String mailid, String dob, String selectedstring, String gender,String photo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,i);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_MOBILENUMBER, mobilenumber);
        values.put(KEY_MAILID, mailid);
        values.put(KEY_DOB, dob);
        values.put(KEY_QUALIFICATION, selectedstring);
        values.put(KEY_GENDER, gender);
        values.put(KEY_USER_PIC,photo);

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        Log.d("sravan1", String.valueOf(values));
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }
    // Getting contacts Count
    public Cursor getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor;

    }

    public Cursor getContactInfo(String empNo) {
        Log.d("gettingvalue",empNo);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor  cursor = db.rawQuery("SELECT * FROM Logindetails WHERE username=?", new String[] {empNo + ""});
//            if(cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                empName = cursor.getString(cursor.getColumnIndex("EmployeeName"));
//
            return cursor;

    }

}
