package com.example.android.credify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.credify.data.creditContract;
import com.example.android.credify.data.creditContract.CreditEntry;
import com.example.android.credify.data.userDbHelper;

import java.util.ArrayList;

public class allUsers extends AppCompatActivity {

    private userDbHelper mDbHelper;
    ListView displayView;
    ArrayList<user> mlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alluser);
        mDbHelper = new userDbHelper(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
        displayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user currentUser = mlist.get(position);
                Intent amountIntent = new Intent(allUsers.this, amount.class);
                amountIntent.putExtra("currentCredit", currentUser.getCredit());
                amountIntent.putExtra("id", position + 1);
                amountIntent.putExtra("email", currentUser.getEmail());
                amountIntent.putExtra("name", currentUser.getName());
                startActivity(amountIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CreditEntry._ID,
                CreditEntry.COLUMN_USER_NAME,
                CreditEntry.COLUMN_USER_EMAIL,
                CreditEntry.COLUMN_USER_CREDIT
        };


        // Perform a query on the user table
        Cursor cursor = db.query(
                creditContract.CreditEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        displayView = (ListView) findViewById(R.id.list);

        mlist = new ArrayList<user>();
        userAdapter mCursorAdapter;

        try {


            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String mName = cursor.getString(cursor.getColumnIndex(CreditEntry.COLUMN_USER_NAME));
                String mEmail = cursor.getString(cursor.getColumnIndex(CreditEntry.COLUMN_USER_EMAIL));
                int mCredit = cursor.getInt((cursor.getColumnIndex(CreditEntry.COLUMN_USER_CREDIT)));
                int mId = cursor.getInt(cursor.getColumnIndex(CreditEntry._ID));

                mlist.add(new user(mName, mEmail, mCredit, mId));

            }

            mCursorAdapter = new userAdapter(this, mlist);
            displayView.setAdapter(mCursorAdapter);

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }


    }
}
