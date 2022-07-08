package com.example.easymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// option menu after finishing a level
public class NextLevelMenu extends AppCompatActivity {

    /**
     * generates GUI
     * performes actions triggered by buttons
     * @param savedInstanceState 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level_menu);

        Button menu = findViewById(R.id.menu);
        Button again = findViewById(R.id.again);
        Button next = findViewById(R.id.next);

        // go to Level Menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(NextLevelMenu.this, LevelMenu.class);
                startActivity(menu);
            }
        });

        final Intent play = new Intent(NextLevelMenu.this, PlayLevel.class);
        final Intent final_l = new Intent(NextLevelMenu.this, FinalLevel.class);

        // repeat current level
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(play);
            }
        });

        // go to the next level
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelMenu.wannaplay_level++;

                // wybór rodzaju poziomu w zależności od jego numeru
                if (LevelMenu.wannaplay_level<5) startActivity(play);
                else startActivity(final_l);
            }
        });
    }
}
