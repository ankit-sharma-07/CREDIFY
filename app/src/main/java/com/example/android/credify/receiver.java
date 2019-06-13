package com.example.android.credify;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.content.DialogInterface;
import android.widget.ListView;
import android.app.AlertDialog;

import com.example.android.credify.data.creditContract;
import com.example.android.credify.data.creditContract.CreditEntry;
import com.example.android.credify.data.userDbHelper;

import java.util.ArrayList;

public class receiver extends AppCompatActivity {
    private userDbHelper mDbHelper;
    ListView displayView;
    ArrayList<user> mlist;
    int senderId;
    int updateValue;
    int senderCredit;
    int receiverCredit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiveruser);
        mDbHelper = new userDbHelper(this);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            senderId = extra.getInt("sender");
            updateValue = extra.getInt("amount");
            senderCredit = extra.getInt("value");
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
        displayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user currentUser = mlist.get(position);
                receiverCredit = currentUser.getCredit();
                update(currentUser.getId());
                Intent amountIntent = new Intent(receiver.this, allUsers.class);
                startActivity(amountIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("CANCEL TRANSACTION ?");
        builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                receiver.this.finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
        String selection = CreditEntry._ID + "!=?";
        String[] selectionArgs = {Integer.toString(senderId)};

        // Perform a query on the user table
        Cursor cursor = db.query(
                creditContract.CreditEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                selection,                  // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
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

    private void update(int receiveId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CreditEntry.COLUMN_USER_CREDIT, senderCredit - updateValue);
        String selection = CreditEntry._ID + "=?";
        String[] selectionArgs = {Integer.toString(senderId)};
        db.update(CreditEntry.TABLE_NAME, values, selection, selectionArgs);

        ContentValues values2 = new ContentValues();
        values2.put(CreditEntry.COLUMN_USER_CREDIT, receiverCredit + updateValue);
        String[] selectionArgs2 = {Integer.toString(receiveId)};
        db.update(CreditEntry.TABLE_NAME, values2, selection, selectionArgs2);

        ContentValues value3 = new ContentValues();
        value3.put(CreditEntry.COLUMN_SENDER, senderId);
        value3.put(CreditEntry.COLUMN_RECEIVER, receiveId);
        value3.put(CreditEntry.COLUMN_AMOUNT, updateValue);
        db.insert(CreditEntry.TABLE2_NAME, null, value3);

    }
}
