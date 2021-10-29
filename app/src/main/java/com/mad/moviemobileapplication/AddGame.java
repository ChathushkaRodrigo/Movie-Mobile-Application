package com.mad.moviemobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddGame extends AppCompatActivity {

    Button addGameBtn;
    EditText gameName,gameYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        addGameBtn =findViewById(R.id.button3);
        gameName=findViewById(R.id.gameName);
        gameYear=findViewById(R.id.gameYear);

        String currentGameName= addGameBtn.getText().toString().trim();
        String currentGameYear = gameYear.getText().toString().trim();

        addGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Boolean isAdded= DBHandler.instance.addGame(currentGameName,currentGameYear);

               if(isAdded == true){
                   Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(getApplicationContext(), "Error adding game", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }

}