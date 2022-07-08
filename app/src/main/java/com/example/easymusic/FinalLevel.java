package com.example.easymusic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

// FinalLevel structure
public class FinalLevel extends Level {

    // player's maximum score
    public static int max_score;

    // player's last score 
    public static int recent_score;

    // number of currently achieved points
    int curr_score;

    // current segment
    int segment;

    /* flag informing about correctly played note
    prevents from achieving a few points for playing the same note couple times */
    int flag = 0;

    // timer handler 
    Handler handler = new Handler();

    // timer responsible for synchronic change of cursor's position
    Timer timer = new Timer();

    /**
     * generates GUI,
     * Level initiation,
     * timer initiation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_final_level);

        curr_score =  0;
        segment = 1;
        level_notes = LevelMenu.notes[LevelMenu.wannaplay_level-1];

        init_level();

        // change cursor position every 10ms
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changeLoc();
                    }
                });
            }
        },0,10);
    }

    /**
     * calls control functions
     * @param v 
     */
    @Override
    public void check(View v) {
        // check if the played note is correct
        if (curr_note<level_notes.length&&curr_note>=0) check_sound(v);
    }

    /**
     * correct note control,
     * current score display
     * @param v 
     */
    public void check_sound(View v) {
        Button b = (Button)v;
        TextView score = findViewById(R.id.score);

        // increase point number if the note is played correctly
        if(b.getText().equals(level_notes[curr_note])){           
            if(flag == 0) {
                curr_score++;

                flag = 1;
            }
            // display current score
            score.setText(String.format("%d/16",curr_score));

        }
    }

    /**
     * change cursor placement,
     * define currently played note,
     * cursor range of motion control 
     */
    public void changeLoc(){
        ImageView cursor = findViewById(R.id.cursor);

        // change cursor's position
        location += screen_width/1000;
        cursor.setX(location);

        // define note currently pointed at 
        if (curr_note < level_notes.length) curr_note = Math.round((location/screen_width)*9)-1;

        // release the flag, allow the player to play the note 
        if(curr_note!=(Math.round((location+screen_width/1000)/screen_width*9)-1)) flag = 0;


        // multisegment level control
        if (location>=screen_width) {
            if(segment == 1) {
                ImageView notes = findViewById(R.id.imageView);

                notes.setImageResource(R.drawable.sheet5_2);

                // reset cursor's position
                location = 0;

                curr_note = 0;
                level_notes = LevelMenu.notes[LevelMenu.wannaplay_level];

                segment = 2;
            }
            else if (segment == 2) {

                // define score displayed at EndGame 
                if(curr_score>max_score) max_score=curr_score;
                recent_score=curr_score;

                // unlock EndGame screen
                if (LevelMenu.unlocked_level < 6) LevelMenu.unlocked_level++;

                // saves data to a file:
                // - maximum score, 
                // - last score,
                // - number of unclocked levels.
                SharedPreferences m_score = getSharedPreferences(LevelMenu.DATA, 0);
                SharedPreferences.Editor editor0 = m_score.edit();
                editor0.putInt("max_score", max_score);
                editor0.commit();
                
                SharedPreferences rec_score = getSharedPreferences(LevelMenu.DATA, 0);
                SharedPreferences.Editor editor1 = rec_score.edit();
                editor1.putInt("recent_score", recent_score);
                editor1.commit();

                SharedPreferences unl_level = getSharedPreferences(LevelMenu.DATA, 0);
                SharedPreferences.Editor editor2 = unl_level.edit();
                editor2.putInt("unlocked_level", LevelMenu.unlocked_level);
                editor2.commit();

                // stop the timer and go to EndGame screen
                timer.cancel();
                Intent end = new Intent(FinalLevel.this, EndGame.class);
                startActivity(end);
            }
        }
    }

    /** performs action chosen from the menu
     * @param item 
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent final_l = new Intent(FinalLevel.this, FinalLevel.class);

        // stop the timer
        timer.cancel();

        // reset current score
        curr_score = 0;

        switch (item.getItemId())
        {
            // replay current level 
            case R.id.again:
                level_notes = LevelMenu.notes[LevelMenu.wannaplay_level-1];
                startActivity(final_l);
                return true;
            // go to LevelMenu
            case R.id.menu:
                Intent menu = new Intent(FinalLevel.this, LevelMenu.class);
                startActivity(menu);
                return true;
            default:
                return false;
        }
    }

}
