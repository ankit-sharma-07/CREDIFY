package com.example.android.credify;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.credify.data.creditContract;
import com.example.android.credify.data.creditContract.CreditEntry;
import com.example.android.credify.data.userDbHelper;

public class amount extends AppCompatActivity {
    private userDbHelper mDbHelper;
    private int amtEnter;
    private EditText amtText;
    private Button confirm;
    private String amtEntered;
    int userId;
    int creditValue;
    String userName;
    String userEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount);
        amtText = (EditText) findViewById(R.id.creditAmt);

        mDbHelper = new userDbHelper(this);
        confirm = (Button) findViewById(R.id.confirm);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            creditValue = extras.getInt("currentCredit");
            userId = extras.getInt("id");
            userName = extras.getString("name");
            userEmail = extras.getString("email");
        }

        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView credits = (TextView) findViewById(R.id.credits);
        name.setText("NAME:    " + userName);
        email.setText("EMAIL:   " + userEmail);
        credits.setText("CREDIT:  " + creditValue);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amtEntered = amtText.getText().toString().trim();
                if (!TextUtils.isEmpty(amtEntered)) {
                    amtEnter = Integer.parseInt(amtEntered);


                    if (amtEnter > creditValue) {
                        Toast.makeText(getApplicationContext(), "enter within range", Toast.LENGTH_LONG).show();
                    } else {
                        Intent receiverIntent = new Intent(amount.this, receiver.class);
                        receiverIntent.putExtra("sender", userId);
                        receiverIntent.putExtra("value", creditValue);
                        receiverIntent.putExtra("amount", amtEnter);
                        startActivity(receiverIntent);
                    }
                } else
                    Toast.makeText(getApplicationContext(), "enter amount", Toast.LENGTH_LONG).show();
                ;
            }
        });
    }


}
