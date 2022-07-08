package com.example.easymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

// main structure of every level
public class Level extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    // piano sounds player
    MediaPlayer player;

    // current note to be played 
    int curr_note = 0;

    // smartphone screen width in pixels
    int screen_width;

    // notes in current level 
    String[] level_notes = LevelMenu.notes[LevelMenu.wannaplay_level-1];

    //current cursors location
    float location = 0;

    /**
     * level initiation 
     * piano keys layout and function definition,
     * display correct sheetmusic for current level,
     * smarphone screen parameters detection. 
     */
    public void init_level() {
        Button c = findViewById(R.id.c);
        Button cis = findViewById(R.id.cis);
        Button d = findViewById(R.id.d);
        Button dis = findViewById(R.id.dis);
        Button e = findViewById(R.id.e);
        Button f = findViewById(R.id.f);
        Button fis = findViewById(R.id.fis);
        Button g = findViewById(R.id.g);
        Button gis = findViewById(R.id.gis);
        Button a = findViewById(R.id.a);
        Button ais = findViewById(R.id.ais);
        Button b = findViewById(R.id.b);

        c.setOnClickListener(this);
        cis.setOnClickListener(this);
        d.setOnClickListener(this);
        dis.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        fis.setOnClickListener(this);
        g.setOnClickListener(this);
        gis.setOnClickListener(this);
        a.setOnClickListener(this);
        ais.setOnClickListener(this);
        b.setOnClickListener(this);

        // sheet music for particular levels
        ImageView notes = findViewById(R.id.imageView);

        switch(LevelMenu.wannaplay_level) {
            case 1:
                notes.setImageResource(R.drawable.sheet0);
                break;
            case 2:
                notes.setImageResource(R.drawable.sheet01);
                break;
            case 3:
                notes.setImageResource(R.drawable.sheet3);
                break;
            case 4:
                notes.setImageResource(R.drawable.sheet4);
                break;
            case 5:
                notes.setImageResource(R.drawable.sheet5_1);
                break;
        }

        // smarphone display width detection
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        screen_width = display.widthPixels;
    }

    /**
     * play the sound of a pressed key,
     * control functions definition
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (player != null) {
            player.release();
            player = null;
        }

        switch(v.getId()){
            case R.id.c:
                player = MediaPlayer.create(this,R.raw.c);
                break;
            case R.id.cis:
                player = MediaPlayer.create(this,R.raw.cis);
                break;
            case R.id.d:
                player = MediaPlayer.create(this,R.raw.d);
                break;
            case R.id.dis:
                player = MediaPlayer.create(this,R.raw.dis);
                break;
            case R.id.e:
                player = MediaPlayer.create(this,R.raw.e);
                break;
            case R.id.f:
                player = MediaPlayer.create(this,R.raw.f);
                break;
            case R.id.fis:
                player = MediaPlayer.create(this,R.raw.fis);
                break;
            case R.id.g:
                player = MediaPlayer.create(this,R.raw.g);
                break;
            case R.id.gis:
                player = MediaPlayer.create(this,R.raw.gis);
                break;
            case R.id.a:
                player = MediaPlayer.create(this,R.raw.a);
                break;
            case R.id.ais:
                player = MediaPlayer.create(this,R.raw.ais);
                break;
            case R.id.b:
                player = MediaPlayer.create(this,R.raw.b);
                break;
        }
        player.start();

        check(v);
    }

    /**
     * call control function
     * @param v 
     */
    public void check(View v){}

    /**
     * create and launch pause menu
     * @param v 
     */
    public void pause_menu(View v){
        PopupMenu pause = new PopupMenu(this, v);
        pause.setOnMenuItemClickListener(this);
        pause.inflate((R.menu.pause_menu));
        pause.show();
    }

    /**
     * performes actions triggered by buttons
     * @param item option chosen from the menu
     * @return true if option from the menu was chosen
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent play = new Intent(Level.this, PlayLevel.class);
        switch (item.getItemId())
        {
            // launch current level again
            case R.id.again:
                startActivity(play);
                return true;
            // go to LevelMenu
            case R.id.menu:
                Intent menu = new Intent(Level.this, LevelMenu.class);
                startActivity(menu);
                return true;
            default:
                return false;
        }
    }
}
