package com.example.android.credify.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.credify.data.creditContract.CreditEntry;


public class userDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = userDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "management.db";


    private static final int DATABASE_VERSION = 1;

    public userDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_USER_TABLE = " CREATE TABLE " + CreditEntry.TABLE_NAME + "( "
                + CreditEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CreditEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
                + CreditEntry.COLUMN_USER_EMAIL + " TEXT NOT NULL ,"
                + CreditEntry.COLUMN_USER_CREDIT + " INTEGER NOT NULL DEFAULT 0 );";

        db.execSQL(SQL_CREATE_USER_TABLE);


        ContentValues values1 = new ContentValues();
        values1.put(CreditEntry.COLUMN_USER_NAME, "ANKIT");
        values1.put(CreditEntry.COLUMN_USER_EMAIL, "ankitself@gmail.com");
        values1.put(CreditEntry.COLUMN_USER_CREDIT, 11000);
        long r1 = db.insert(CreditEntry.TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(CreditEntry.COLUMN_USER_NAME, "KARAN");
        values2.put(CreditEntry.COLUMN_USER_EMAIL, "karanparwani@gmail.com");
        values2.put(CreditEntry.COLUMN_USER_CREDIT, 15000);
        long r2 = db.insert(CreditEntry.TABLE_NAME, null, values2);


        ContentValues values3 = new ContentValues();
        values3.put(CreditEntry.COLUMN_USER_NAME, "ANAND");
        values3.put(CreditEntry.COLUMN_USER_EMAIL, "anandkumar2302@gmail.com");
        values3.put(CreditEntry.COLUMN_USER_CREDIT, 9000);
        long r3 = db.insert(CreditEntry.TABLE_NAME, null, values3);


        ContentValues values4 = new ContentValues();
        values4.put(CreditEntry.COLUMN_USER_NAME, "AKSHAY");
        values4.put(CreditEntry.COLUMN_USER_EMAIL, "akshay1225@yahoo.com");
        values4.put(CreditEntry.COLUMN_USER_CREDIT, 5000);
        long r4 = db.insert(CreditEntry.TABLE_NAME, null, values4);


        ContentValues values5 = new ContentValues();
        values5.put(CreditEntry.COLUMN_USER_NAME, "VINAYAK");
        values5.put(CreditEntry.COLUMN_USER_EMAIL, "kumarvinayak@gmail.com");
        values5.put(CreditEntry.COLUMN_USER_CREDIT, 7000);
        long r5 = db.insert(CreditEntry.TABLE_NAME, null, values5);


        ContentValues values6 = new ContentValues();
        values6.put(CreditEntry.COLUMN_USER_NAME, "AYUSH");
        values6.put(CreditEntry.COLUMN_USER_EMAIL, "ayushsrivastav@gmail.com");
        values6.put(CreditEntry.COLUMN_USER_CREDIT, 9000);
        long r6 = db.insert(CreditEntry.TABLE_NAME, null, values6);


        ContentValues values7 = new ContentValues();
        values7.put(CreditEntry.COLUMN_USER_NAME, "ARCHIT");
        values7.put(CreditEntry.COLUMN_USER_EMAIL, "architshukla@gmail.com");
        values7.put(CreditEntry.COLUMN_USER_CREDIT, 7500);
        long r7 = db.insert(CreditEntry.TABLE_NAME, null, values7);


        ContentValues values8 = new ContentValues();
        values8.put(CreditEntry.COLUMN_USER_NAME, "DHRUV");
        values8.put(CreditEntry.COLUMN_USER_EMAIL, "gargdhruv@gmail.com");
        values8.put(CreditEntry.COLUMN_USER_CREDIT, 6700);
        long r8 = db.insert(CreditEntry.TABLE_NAME, null, values8);


        ContentValues values9 = new ContentValues();
        values9.put(CreditEntry.COLUMN_USER_NAME, "SHIVAM");
        values9.put(CreditEntry.COLUMN_USER_EMAIL, "shivampandey@yahoo.com");
        values9.put(CreditEntry.COLUMN_USER_CREDIT, 6000);
        long r9 = db.insert(CreditEntry.TABLE_NAME, null, values9);

        ContentValues values = new ContentValues();
        values.put(CreditEntry.COLUMN_USER_NAME, "ASHISH");
        values.put(CreditEntry.COLUMN_USER_EMAIL, "ashishkumar@gmail.com");
        values.put(CreditEntry.COLUMN_USER_CREDIT, 1000);
        long r10 = db.insert(CreditEntry.TABLE_NAME, null, values);


        String CREATE_TABLE2 = "CREATE TABLE " + CreditEntry.TABLE2_NAME + "( "
                + "ID " + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + CreditEntry.COLUMN_SENDER + " INTEGER , "
                + CreditEntry.COLUMN_RECEIVER + " INTEGER , "
                + CreditEntry.COLUMN_AMOUNT + " INTEGER );";
        db.execSQL(CREATE_TABLE2);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
