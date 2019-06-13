package com.example.android.credify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewUser = (Button) findViewById(R.id.users);
        viewUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent = new Intent(MainActivity.this, allUsers.class);
                startActivity(userIntent);
            }
        });

    }


}
