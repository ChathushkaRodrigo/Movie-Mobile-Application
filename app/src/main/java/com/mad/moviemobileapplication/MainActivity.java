package com.mad.moviemobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register,login;
    EditText username,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         register =findViewById(R.id.button2);
         login=findViewById(R.id.button);
         username =findViewById(R.id.username);
         password=findViewById(R.id.password);


         String currentUsername = username.getText().toString().trim();
         String currentPassword = password.getText().toString().trim();



        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBHandler.instance.RegisterUser(currentUsername,currentPassword);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginChecker=   DBHandler.instance.loginUser(currentUsername,currentPassword);

                if(loginChecker.equals("admin")){
                    Intent myIntent = new Intent(MainActivity.this, AddGame.class);
                    startActivity(myIntent);

                }
                else if(loginChecker.equals("normalUser")){
                    Intent myIntent = new Intent(MainActivity.this, GameList.class);
                    startActivity(myIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error login in", Toast.LENGTH_LONG).show();
                }



            }
        });




    }




}