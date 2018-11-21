package com.example.i16067.akseleroball;


import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements View.OnClickListener{
    protected Button btn_Start;
    protected Button btn_HighScore ;
    protected Button btn_Exit ;
    protected MainActivity activity;

    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(MainActivity activity){
        FragmentHome fragmentHome = new FragmentHome();
        fragmentHome.activity = activity;
        return fragmentHome;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View res = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        this.btn_Start = res.findViewById(R.id.btn_Start);
        this.btn_HighScore = res.findViewById(R.id.btn_HighScore);
        this.btn_Exit = res.findViewById(R.id.btn_Exit);


        this.btn_Start.setOnClickListener(this);
        this.btn_HighScore.setOnClickListener(this);
        this.btn_Exit.setOnClickListener(this);
        return res;
    }

    @Override
    public void onClick(View view) {
        if(view == this.btn_Start){
            this.activity.switchToGame();
        }else if(view == this.btn_HighScore){
            this.activity.switchToHighscore();
        }else{
            System.exit(0);
        }

    }
}
