package com.example.easymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

// Start screen
public class Start extends AppCompatActivity {

    /**
     * generates GUI,
     * goes to LevelMenu after the logo is pressed
     * @param savedInstanceState 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView btn_start = findViewById(R.id.start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(Start.this, LevelMenu.class);
                startActivity(menu);
            }
        });
    }
}
