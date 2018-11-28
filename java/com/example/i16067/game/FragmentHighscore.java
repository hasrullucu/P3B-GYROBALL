package com.example.i16067.game;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHighscore extends Fragment implements View.OnClickListener{
    protected TextView tv_Highscore;
    protected ListView lst_Highscore ;
    protected Button btn_Back;
//    tes add button buat add ke listview
    protected Button btn_Add;
    protected AdapterHighscore adapterHighscore;
    protected MainActivity activity;


    public FragmentHighscore() {
        // Required empty public constructor
    }

    public static FragmentHighscore newInstance(MainActivity activity){
        FragmentHighscore fragmentHighscore = new FragmentHighscore();
        fragmentHighscore.activity = activity;
        fragmentHighscore.adapterHighscore = new AdapterHighscore(fragmentHighscore);



        return fragmentHighscore;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View res = inflater.inflate(R.layout.fragment_fragment_highscore, container, false);
        this.tv_Highscore = res.findViewById(R.id.tv_Highscore);
        this.btn_Back = res.findViewById(R.id.btn_Back);
        this.lst_Highscore = res.findViewById(R.id.lst_Highscore);
        this.lst_Highscore.setAdapter(adapterHighscore);

//        btnadd
        this.btn_Add = res.findViewById(R.id.btn_Add);
        this.btn_Add.setOnClickListener(this);

        this.btn_Back.setOnClickListener(this);

        return res;

    }

    @Override
    public void onClick(View view) {
        if(view == this.btn_Back){
            this.activity.switchToHome();
        }else if(view == this.btn_Add){
            Log.d("d" , "mantap");
            this.adapterHighscore.addPlayer("basrul" , 10);
        }
    }
}
