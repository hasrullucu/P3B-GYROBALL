package com.example.i16067.game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGame extends Fragment implements View.OnClickListener{
    protected MainActivity activity ;
    protected TextView tvAccX, tvAccY, tvAccZ;
    protected SensorReader sensorReader;
    protected ImageView imageView;
    protected Bitmap bitmap;
    protected Canvas canvas;
    protected Paint p;
    protected Button btn ,add;
    protected boolean isPlay;
//    protected Bola bola;
    protected BolaHitam bolaHitam;
    protected float x,speedX;
    protected float y,speedY;


    protected ArrayList<Bola> arrBola;

    public FragmentGame() {
        // Required empty public constructor
    }

    public static FragmentGame newInstance(MainActivity activity){
        FragmentGame fragmentGame = new FragmentGame();
        fragmentGame.activity = activity;
        fragmentGame.sensorReader = new SensorReader(fragmentGame.activity , fragmentGame);
        fragmentGame.p = new Paint();
        fragmentGame.p.setColor(Color.RED);
        fragmentGame.isPlay = false;


        return fragmentGame;
    }

    @Override
    public void onPause(){
        super.onPause();
        this.sensorReader.stop();
    }

    @Override
    public void onResume(){
        super.onResume();
        this.sensorReader.start();
    }

    public void updateAccXYZ(float [] values){ // done
        this.tvAccX.setText("X = "+(int)values[0]);
        this.tvAccY.setText("Y = "+(int)values[1]);
        this.tvAccZ.setText("Z = "+(int)values[2]);
    }

    public void create(){
        this.bitmap=Bitmap.createBitmap(this.imageView.getWidth(),this.imageView.getHeight(), Bitmap.Config.ARGB_8888);
        this.imageView.setImageBitmap(this.bitmap);
        this.canvas=new Canvas(this.bitmap);
        this.canvas.drawColor(Color.WHITE);

        Random rand=new Random();

        Log.d("test",this.imageView.getWidth()+" "+this.imageView.getHeight());

        int randomBola= rand.nextInt(4)+1;
        int randomBolaHitam=rand.nextInt(4)+1;

        while(randomBolaHitam==randomBola){
            randomBolaHitam=rand.nextInt(4)+1;
        }
        float x=0;
        float y=0;


        Bola bola=new Bola(this.generateX(rand,randomBola,50),this.generateY(rand,randomBola,50),50);
        this.arrBola.add(bola);

        this.bolaHitam=new BolaHitam(this.generateX(rand,randomBolaHitam,100),this.generateY(rand,randomBolaHitam,100),100);

//        this.canvas.drawCircle(this.bola.getX(),this.bola.getY(),this.bola.getR(),this.p);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        this.canvas.drawCircle(this.bolaHitam.getX(),this.bolaHitam.getY(),this.bolaHitam.getR(),paint);

        this.imageView.invalidate();
    }

    public float generateX(Random rand, int random, int r){
        float x=0;
        if(random==1 || random==3){
            x=r+rand.nextInt(this.imageView.getWidth()/2-r);
        }
        else if(random==2 || random==4) {
            x = this.imageView.getWidth() / 2 + rand.nextInt(this.imageView.getWidth() / 2 - r);
        }
        Log.d("test","koor x : "+x+"\n");
        return x;
    }

    public float generateY(Random rand,int random,int r){
        float y=0;
        if(random==1 ||random==2){
            y=r+rand.nextInt(this.imageView.getHeight()/2-r);
        }
        else if(random==3 || random==4){
            y=this.imageView.getHeight()/2+rand.nextInt(this.imageView.getHeight()/2)-r;
        }
        Log.d("test","koor y : "+y+"\n");
        return y;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View res = inflater.inflate(R.layout.fragment_fragment_game, container, false);
        this.tvAccX =res.findViewById(R.id.tvAccX);
        this.tvAccY =res.findViewById(R.id.tvAccY);
        this.tvAccZ =res.findViewById(R.id.tvAccZ);

        this.btn=res.findViewById(R.id.btn);
        this.add = res.findViewById(R.id.add);
        this.imageView=res.findViewById(R.id.image_view);
        this.btn.setOnClickListener(this);
        this.add.setOnClickListener(this);


        return res;

    }

    public void redraw(float [] values){
        if(this.isPlay) {
            for(int i=0;i<this.arrBola.size();i++) {
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                Bola bola=this.arrBola.get(i);
                this.canvas.drawCircle(bola.getX(), bola.getY(), bola.getR(), paint);

                if (bola.getX() - bola.getR() > 0 && bola.getX() + bola.getR() < this.imageView.getWidth() && bola.getY() - bola.getR() > 0 && bola.getY() + bola.getR() < this.imageView.getHeight()) {
                    //kalau ga nabrak
                    Log.d("cek",bola.getX()+" "+bola.getY()+" ga nabrak");
                    speedX = (values[0] / 20f)*-1;
                    speedY = (values[1] / 20.8f);
                    bola.setKecepatanX(bola.getKecepatanX()+speedX);
                    bola.setKecepatanY(bola.getKecepatanY()+speedY);
                } else {
                    Log.d("cek",bola.getX()+" "+bola.getY()+" nabrak");
                    if (bola.getX() - bola.getR() < 0) {
                        bola.setX(75);
                        //bola.setKecepatanX(0);
                        bola.setKecepatanX(bola.getKecepatanX()*-0.5f);
                    } else if (bola.getX() + bola.getR() > this.imageView.getWidth()) {
                        bola.setX(this.imageView.getWidth()-75);
                        bola.setKecepatanX(bola.getKecepatanX()*-0.5f);
                    } else if (bola.getY() - bola.getR() < 0) {
                        bola.setY(75);
                        bola.setKecepatanY(bola.getKecepatanY()*-0.5f);
                        //bola.setKecepatanY(0);
                    } else if (bola.getY() + bola.getR() > this.imageView.getHeight()) {
                        bola.setY(this.imageView.getHeight()-75);
                        bola.setKecepatanY(bola.getKecepatanY()*-0.5f);
                        //bola.setKecepatanY(0);
                    }
                }

                x = bola.getX() + (bola.getKecepatanX()/bola.getBerat()*1.0f);
                y = bola.getY() + (bola.getKecepatanY()/bola.getBerat()*1.0f);

                bola.setX(x);
                bola.setY(y);


                Paint paint1 = new Paint();
                paint1.setColor(Color.BLACK);
                //this.canvas.drawColor(Color.WHITE);
                this.canvas.drawCircle(bola.getX(), bola.getY(), bola.getR(), paint);
                if (Math.abs(this.bolaHitam.getX() - bola.getX()) < 50 && Math.abs(this.bolaHitam.getY() - bola.getY()) < 50) {
                    //System.exit(0);
                    //stop game dan waktu
                    //hitung score
                    //pindah fragment

                    Log.d("test", "NABRAK");
                } else {
                    this.canvas.drawCircle(this.bolaHitam.getX(), this.bolaHitam.getY(), this.bolaHitam.getR(), paint1);

                    this.canvas.drawCircle(bola.getX(), bola.getY(), bola.getR(), this.p);
                    this.imageView.invalidate();
                }
            }




        }
    }

    @Override
    public void onClick(View view) {
        if(view==this.btn){
            //this.bola=new Bola(50,50,50);
            this.arrBola=new ArrayList<>();
            this.create();
            this.isPlay=true;
            // this.arrayList=new ArrayList<>();
        }else if(view==this.add){
            Random rand=new Random();
            int randomBola= rand.nextInt(4)+1;
            Bola bola=new Bola(this.generateX(rand,randomBola,50),this.generateY(rand,randomBola,50),50);
            this.arrBola.add(bola);
        }
    }
}
