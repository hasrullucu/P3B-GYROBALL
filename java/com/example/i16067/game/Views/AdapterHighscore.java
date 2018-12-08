package com.example.i16067.game.Views;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.i16067.game.Models.Player;
import com.example.i16067.game.R;
import com.example.i16067.game.Views.FragmentHighscore;

import java.util.ArrayList;

public class AdapterHighscore extends BaseAdapter {
    protected ArrayList<Player> listPlayer;
    protected FragmentHighscore fragmentHighscore;

    public AdapterHighscore(FragmentHighscore fragmentHighscore){
        this.listPlayer = new ArrayList<Player>();
        this.fragmentHighscore = fragmentHighscore;

    }

    public void addPlayer(String name , int score){
        Player newPlayer = new Player(name , score);
        this.listPlayer.add(newPlayer);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return this.listPlayer.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listPlayer.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View res = this.fragmentHighscore.getLayoutInflater().inflate(R.layout.lst_item , null);
        TextView tv_Name= res.findViewById(R.id.tv_Name);
        TextView tv_Score = res.findViewById(R.id.tv_Score);

        Player now = this.listPlayer.get(i);

        tv_Name.setText(now.getNama());
        tv_Score.setText(now.getScore()+"");

        return res;

    }
}
