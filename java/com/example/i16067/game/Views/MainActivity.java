package com.example.i16067.game.Views;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.i16067.game.R;

/**
 * -------------------------HOW TO CREATE FRAGMENT
 * siapin fragment container di main activity
 * bikin fragment baru jangan ceklist interface call backs
 *
 * bikin layout buat fragment
 *
 * atur oncreate view ( bikin view res , ambil id view , return)
 *
 * bikin static newInstance (new Fragment objek , ambil activity)
 *
 * di main activity (bikin objek fragment , bikin fragment manager)
 *
 * bikin fragment transaction (hati hati package)
 * replace
 * commit
 *
 * --------------------------HOW TO CREATE LISTVIEW
 * isi tipe listView di layout utama
 * create listview di activity nya / fragment nya
 *
 * bikin layout per item beserta elemen
 *
 * bikin kelas Adapter extends BaseAdapter
 * buat constructor
 * implements semua method
 * bikin data struktur simpen data
 *
 * ganti getsize ( data.getsize)
 * ganti get item (data.get(i))
 * ganti getview (layout inflater , ambil id element per item , dan set data)
 *
 * bikin method add item di adapter (add ke data , notify data setchanged)
 *
 * di activity / fragment bikin adapter
 * listview setadapter(adapter)
 *
 *
 *
 *
 *
 */

public class MainActivity extends AppCompatActivity {
    // Fragment untuk home page
    protected FragmentHome fragmentHome;

    // Fragment saat bermain bola
    protected FragmentGame fragmentGame;

    // Fragment untuk highscore
    protected FragmentHighscore fragmentHighscore;

    //Fragment manager buat atur perpindahan fragment
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fragmentHome = FragmentHome.newInstance(this);
        this.fragmentGame = FragmentGame.newInstance(this);
        this.fragmentHighscore = FragmentHighscore.newInstance(this);




        this.fragmentManager = this.getSupportFragmentManager();

        // inisialisasi awal tampilkan fragment home
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.fragContainer , fragmentHome);
        transaction.commit();


    }

    /**
     * Method wrapper untuk pindah ke fragment Game
     */
    public void switchToGame(){
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.fragContainer , fragmentGame);
        transaction.commit();
    }

    /**
     * Method wrapper untuk pindah ke fragment Home
     */
    public void switchToHome(){
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.fragContainer , fragmentHome);
        transaction.commit();
    }

    /**
     * Method wrapper untuk pindah ke fragment Highscore
     */
    public void switchToHighscore(){
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.fragContainer , fragmentHighscore);
        transaction.commit();
    }
}
