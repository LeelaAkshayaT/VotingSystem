package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Voting extends AppCompatActivity {
    Button andBtn;
    Button iosBtn;
    Button donebtn;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        db = new DatabaseHelper(this);

        andBtn = findViewById(R.id.button_and);
        iosBtn = findViewById(R.id.button_ios);
        donebtn = findViewById(R.id.button_done);




        andBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                final String username = bundle.getString("username");

                Toast.makeText(getApplicationContext(), "You voted for Android", Toast.LENGTH_SHORT).show();
                db.getvotes(username, "android");
                donebtn.setEnabled(true);
                iosBtn.setEnabled(false);
                andBtn.setEnabled(false);

            }
        });
        iosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i= "ios";
                Bundle bundle = getIntent().getExtras();
                final String username = bundle.getString("username");

                Toast.makeText(getApplicationContext(), "You voted for iOS", Toast.LENGTH_SHORT).show();
                db.getvotes(username, i);
                donebtn.setEnabled(true);
                andBtn.setEnabled(false);
                iosBtn.setEnabled(false);
            }
        });
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent winner = new Intent(Voting.this,Winner.class);
                startActivity(winner);
            }
        });
    }
}