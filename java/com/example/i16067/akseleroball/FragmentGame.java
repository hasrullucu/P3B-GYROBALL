package com.example.i16067.akseleroball;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGame extends Fragment implements View.OnClickListener{
    protected MainActivity activity ;
    protected Button btn_Finish;
    protected ImageView iv_Game;


    public FragmentGame() {
        // Required empty public constructor
    }

    public static FragmentGame newInstance(MainActivity activity){
        FragmentGame fragmentGame = new FragmentGame();
        fragmentGame.activity = activity;
        return fragmentGame;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View res = inflater.inflate(R.layout.fragment_fragment_game, container, false);
        this.btn_Finish = res.findViewById(R.id.btn_Finish);
        this.btn_Finish.setOnClickListener(this);

        return res;

    }

    @Override
    public void onClick(View view) {
        if(view == this.btn_Finish){
            this.activity.switchToHighscore();
        }
    }
}
