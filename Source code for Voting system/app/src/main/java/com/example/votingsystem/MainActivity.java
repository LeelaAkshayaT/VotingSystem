package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Username;
    EditText Password;
    Button Login;
    TextView Register;
    CheckBox show;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHelper(this);
        Username =  findViewById(R.id.username);
        Password =  findViewById(R.id.assword);
        Login =  findViewById(R.id.login);
        Register =  findViewById(R.id.register);
        show = findViewById(R.id.spass);
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString().trim();
                String pwd = Password.getText().toString().trim();
                if(user.equals("") && pwd.equals("")){
                    Toast.makeText(MainActivity.this,"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if (user.equals("")) {
                    Toast.makeText(MainActivity.this, "username required", Toast.LENGTH_SHORT).show();
                } else if (pwd.equals("")) {
                    Toast.makeText(MainActivity.this, "password required", Toast.LENGTH_SHORT).show();
                } else if (pwd.length() <= 6) {
                    Toast.makeText(MainActivity.this, "password must have more than 6 characters", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean res = db.checkUser(user, pwd);
                    if (res == true) {
                        Toast.makeText(MainActivity.this, "LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                        Intent voted = new Intent(MainActivity.this, AlreadyVoted.class);
                        startActivity(voted);

                    } else {
                        Toast.makeText(MainActivity.this, "LOGIN ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

}