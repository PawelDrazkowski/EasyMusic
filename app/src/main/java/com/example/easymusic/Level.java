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

/** główna struktura każdego poziomu */
public class Level extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    /** odtwarzacz dźwięków pianina */
    MediaPlayer player;

    /** aktualny dźwięk */
    int curr_note = 0;

    /** szerokość ekranu smartfona */
    int screen_width;

    /** nuty w aktualnym poziomie */
    String[] level_notes = LevelMenu.notes[LevelMenu.wannaplay_level-1];

    /** aktualna lokalizacja kursora */
    float location = 0;

    /**
     * inicjacja poziomu:
     * definicja klawiszy pianina,
     * ustalenie obrazu z zapisem nutowym w zależności od wybranego poziomu,
     * odczytanie szerokości ekranu
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

        // ustal obraz z zapisem nutowym w zależności od wybranego poziomu
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

        // pobierz szerokość ekranu smartfona
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        screen_width = display.widthPixels;
    }

    /**
     * odtworzenie dźwięku naciśniętego klawisza,
     * wywołanie funkcji sprawdzających
     * @param v naciśnięty klawisz
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
     * wywołaj funkcje sprawdzające
     * @param v naciśnięty klawisz
     */
    public void check(View v){}

    /**
     * utworzenie oraz uruchomienie menu pauzy
     * @param v naciśnięty przycisk
     */
    public void pause_menu(View v){
        PopupMenu pause = new PopupMenu(this, v);
        pause.setOnMenuItemClickListener(this);
        pause.inflate((R.menu.pause_menu));
        pause.show();
    }

    /**
     * uruchomienie dopowiedniej aktywności w zależności od wybranej opcji
     * @param item opcja wybrana w menu
     * @return true jeżeli wybrano opcję z menu
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent play = new Intent(Level.this, PlayLevel.class);
        switch (item.getItemId())
        {
            // uruchom poziom ponownie
            case R.id.again:
                startActivity(play);
                return true;
            // przejdź do menu głównego
            case R.id.menu:
                Intent menu = new Intent(Level.this, LevelMenu.class);
                startActivity(menu);
                return true;
            default:
                return false;
        }
    }
}
