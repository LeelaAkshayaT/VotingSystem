package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText Username;
    EditText Password;
    EditText Cnfpassword;
    Button Register;
    TextView Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        Cnfpassword= findViewById(R.id.cpassword);
        Login =  findViewById(R.id.tvlogin);
        Register = findViewById(R.id.register);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString().trim();
                String pwd = Password.getText().toString().trim();
                String cnf_pwd = Cnfpassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (res == true) {
                    Toast.makeText(RegisterActivity.this, "U HAVE REGISTERED ALREADY PLS LOGIN", Toast.LENGTH_SHORT).show();

                    Intent LOGIN = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(LOGIN);

                }
                else if(user.equals("") && pwd.equals("") && cnf_pwd.equals("")){
                    Toast.makeText(RegisterActivity.this,"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if((user.equals("") && pwd.equals("") )|| (pwd.equals("") && cnf_pwd.equals("")) || (user.equals("") && cnf_pwd.equals("")) ){
                    Toast.makeText(RegisterActivity.this,"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(user.equals("")){
                    Toast.makeText(RegisterActivity.this,"username required",Toast.LENGTH_SHORT).show();
                }
                else if(pwd.equals("")){
                    Toast.makeText(RegisterActivity.this,"password required",Toast.LENGTH_SHORT).show();
                }
                else if(cnf_pwd.equals("")){
                    Toast.makeText(RegisterActivity.this,"confirm password required",Toast.LENGTH_SHORT).show();
                }
                else if(!cnf_pwd.equals(pwd)){
                    Toast.makeText(RegisterActivity.this,"password and confirm password didn't match",Toast.LENGTH_SHORT).show();
                }
                else if(pwd.length()<=6){
                    Toast.makeText(RegisterActivity.this,"password must have more than 6 characters",Toast.LENGTH_SHORT).show();
                }
                else{
                    long val = db.addUser(user,pwd);
                    if(val>0)
                    {
                        Toast.makeText(RegisterActivity.this, "REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Bundle bundle= new Bundle();
                        bundle.putString("username", user);
                        Intent moveToVote = new Intent(RegisterActivity.this,Voting.class);
                        moveToVote.putExtras(bundle);
                        startActivity(moveToVote);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "REGISTRATION ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}