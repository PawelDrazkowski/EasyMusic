package com.example.easymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/** EndGame display */
public class EndGame extends AppCompatActivity {

    /**
     * generates GUI,
     * displays player's results read from a file,
     * performes actions triggered by buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Button more = findViewById(R.id.again);
        Button menu = findViewById(R.id.menu);

        // reads player's results 
        SharedPreferences r_score = getSharedPreferences(LevelMenu.DATA, 0);
        FinalLevel.recent_score = r_score.getInt("recent_score", FinalLevel.recent_score);

        SharedPreferences m_score = getSharedPreferences(LevelMenu.DATA, 0);
        FinalLevel.max_score = m_score.getInt("max_score", FinalLevel.max_score);

        // displays player's results 
        TextView score = findViewById(R.id.end2);
        score.setText(String.format("Recent score: %d/16", FinalLevel.recent_score));

        TextView max = findViewById(R.id.end3);
        max.setText(String.format("Max score: %d/16", FinalLevel.max_score));

        // redirects player to a music theory website
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.musictheory.net/lessons"));
                startActivity(link);
            }
        });

        // redirects player to Level Menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent level_menu = new Intent(EndGame.this, LevelMenu.class);
                startActivity(level_menu);
            }
        });
    }
}
