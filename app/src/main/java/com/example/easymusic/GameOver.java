package com.example.easymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// options menu after lost game
public class GameOver extends AppCompatActivity {

    /**
     * generates GUI,
     * performes actions triggered by buttons
     * @param savedInstanceState 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Button menu = findViewById(R.id.menu);
        Button again = findViewById(R.id.again);

        // go to LevelMenu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(GameOver.this, LevelMenu.class);
                startActivity(menu);
            }
        });

        // repeat current level 
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play = new Intent(GameOver.this, PlayLevel.class);
                startActivity(play);
            }
        });
    }
}
