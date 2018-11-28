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
    protected Button btn;
    protected boolean isPlay;
    protected Bola bola;
    protected BolaHitam bolaHitam;
    protected float x,speedX;
    protected float y,speedY;


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
//        if(randomBola==1 || randomBolaHitam==1){
//            x=50+rand.nextInt(this.imageView.getWidth()/2);
//            y=50+rand.nextInt(this.imageView.getHeight()/2);
//        }
//        else if(randomBola==2 || randomBolaHitam==2){
//            x=this.imageView.getWidth()/2+rand.nextInt(this.imageView.getWidth()/2-100);
//            y=this.imageView.getHeight()/2+rand.nextInt(this.imageView.getHeight()/2-100);
//        }
//        else if(randomBola==3 || randomBolaHitam==3){
//            x=50+rand.nextInt(this.imageView.getWidth()/2);
//            y=this.imageView.getHeight()/2-100+rand.nextInt(this.imageView.getHeight()/2);
//        }
//        else if(randomBola==4 || randomBolaHitam==4){
//            x=this.imageView.getWidth()/2-100+rand.nextInt(this.imageView.getWidth()/2);
//            y=this.imageView.getHeight()/2-100+rand.nextInt(this.imageView.getHeight()/2);
//        }
//        //koor x bola hitam
//        x=this.imageView.getWidth()/2+rand.nextInt(this.imageView.getWidth()/2-150);
//        if(x-this.bola.getX()<50){
//            while(x-this.bola.getX()<50){
//                x=this.imageView.getWidth()/2+rand.nextInt(this.imageView.getWidth()/2-150);
//                Log.d("test","koor x : "+x+"\n");
//            }
//        }
//
//
//        //koor y bola hitam
//        y=this.imageView.getHeight()/2+rand.nextInt(this.imageView.getHeight()/2-150);
//        if(y-this.bola.getY()<50){
//            while(y-this.bola.getY()<50){
//                y=this.imageView.getHeight()/2+rand.nextInt(this.imageView.getHeight()/2-150);
//                Log.d("test","koor y : "+y+"\n");
//            }
//        }

        this.bola=new Bola(this.generateX(rand,randomBola,50),this.generateY(rand,randomBola,50),50);

        this.bolaHitam=new BolaHitam(this.generateX(rand,randomBolaHitam,100),this.generateY(rand,randomBolaHitam,100),100);

        this.canvas.drawCircle(this.bola.getX(),this.bola.getY(),this.bola.getR(),this.p);
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
        this.imageView=res.findViewById(R.id.image_view);
        this.btn.setOnClickListener(this);


        return res;

    }

    public void redraw(float [] values){
        if(this.isPlay) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            this.canvas.drawCircle(this.bola.getX(), this.bola.getY(), this.bola.getR(), paint);

            if(bola.getX()-bola.getR()>0 && bola.getX()+bola.getR()<this.imageView.getWidth() && bola.getY()-bola.getR()>0 && bola.getY()+bola.getR()<this.imageView.getHeight()){
                speedX= (values[0]/9.8f*7)*-1;
                speedY= values[1]/9.8f*7;
            }else{
                if(bola.getX()-bola.getR()<0){
                    this.bola.setX(x+25);
                }else if(bola.getX()+bola.getR()>this.imageView.getWidth()){
                    this.bola.setX(x-25);
                }else if(bola.getY()-bola.getR()<0){
                    this.bola.setY(y+25);
                }else if(bola.getY()+bola.getR()>this.imageView.getHeight()){
                    this.bola.setY(y-25);
                }
            }

            x=bola.getX()+speedX;
            y=bola.getY()+speedY;

            this.bola.setX(x);
            this.bola.setY(y);


            Paint paint1=new Paint();
            paint.setColor(Color.BLACK);

            if(Math.abs(this.bolaHitam.getX()-this.bola.getX())<50 && Math.abs(this.bolaHitam.getY()-this.bola.getY())<50){
                System.exit(0);


                Log.d("test","NABRAK");
            }else {
                this.canvas.drawCircle(this.bolaHitam.getX(), this.bolaHitam.getY(), this.bolaHitam.getR(), paint1);

                this.canvas.drawCircle(this.bola.getX(), this.bola.getY(), this.bola.getR(), this.p);
                this.imageView.invalidate();
            }


        }
    }

    @Override
    public void onClick(View view) {
        if(view==this.btn){
            //this.bola=new Bola(50,50,50);
            this.create();
            this.isPlay=true;
            // this.arrayList=new ArrayList<>();
        }
    }
}
