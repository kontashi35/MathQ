package com.blogspot.techtibet.mathq;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class LevelSixActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    private RelativeLayout relativeLayout1;
    private RelativeLayout mRelativeLevel2;
    private TextView mTimer;
    private TextView mExpression;
    private TextView mMarks;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private int score;
    private TextView mMsgText;
    private CircleImageView mMsgImg;
    private Button mGoBtn;
    private int locationofcorrect;
    int correctanswer;
    int question=0;
    private ArrayList<Integer> arrayList=new ArrayList<>();
    private AlertDialog.Builder builder6;
    public SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void startgame(View view){
        mGoBtn.setVisibility(View.INVISIBLE);
        relativeLayout1.setVisibility(View.VISIBLE);
        startnewgame();
    }

    void startnewgame() {
        mMsgText.setText("");
        mMsgImg.setVisibility(View.INVISIBLE);
        mMarks.setText("0/0");
        score=0;
        question=0;


        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                mTimer.setText(String.valueOf(l/1000)+"s");
                Log.d(TAG, "onTick: "+String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                mTimer.setText("0");
                mMsgImg.setVisibility(View.INVISIBLE);
                mMsgText.setText("Time UP!");
               builder6=new AlertDialog.Builder(LevelSixActivity.this);
                builder6.setCancelable(false);
                builder6.setTitle("Time Up!!!");
                builder6.setIcon(R.drawable.smile);
                builder6.setMessage("Your score:"+score+"\nQuestion Atempted:"+question);
                int oldscore=preferences.getInt("key6",0);
                if(score>oldscore){
                    editor.putInt("key6",score);
                    editor.commit();
                }
                builder6.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startnewgame();

                    }
                });
                builder6.setNegativeButton("No,Go to home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(LevelSixActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder6.create();
                builder6.show();

            }
        }.start();
        GenerateQuestion();
    }


    private void GenerateQuestion() {
        Random rand=new Random();
        int a=-10+rand.nextInt(21);
        int b=-10+rand.nextInt(21);
        int c=-10+rand.nextInt(21);
        mExpression.setText(a+"X"+b+"+("+c+")"+"=?");
        locationofcorrect=rand.nextInt(4);
        arrayList.clear();
        for(int i=0;i<4;i++){
            if(i==locationofcorrect){
                arrayList.add(a*b+c);
            }
            else{
                int no=-20+rand.nextInt(100);
                while (no==(a*b+c)){
                    no=-20+rand.nextInt(100);
                }
                arrayList.add(no);
            }

        }
        button0.setText(Integer.toString(arrayList.get(0)));
        button1.setText(Integer.toString(arrayList.get(1)));
        button2.setText(Integer.toString(arrayList.get(2)));
        button3.setText(Integer.toString(arrayList.get(3)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_six);
        relativeLayout1=(RelativeLayout)findViewById(R.id.relativelvl6);

        mTimer=(TextView)findViewById(R.id.timerlvl6);
        mExpression=(TextView)findViewById(R.id.explvl6);
        mMarks=(TextView)findViewById(R.id.marklvl6);
        button0=(Button)findViewById(R.id.button0lvl6);
        button1=(Button)findViewById(R.id.button1lvl6);
        button2=(Button)findViewById(R.id.button2lvl6);
        button3=(Button)findViewById(R.id.button3lvl6);
        mGoBtn=(Button)findViewById(R.id.gobtnlvl6);
        mMsgText=(TextView)findViewById(R.id.messagetextlvl6);
        mMsgImg=(CircleImageView)findViewById(R.id.messageimglvl6);
        mRelativeLevel2=(RelativeLayout)findViewById(R.id.relativelvl6main);
        preferences=this.getSharedPreferences("level6", Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    public void choosenoption(View view){
        question++;
        if(view.getTag().toString().equals(Integer.toString(locationofcorrect))){
            mMsgImg.setVisibility(View.VISIBLE);
            score++;
            MediaPlayer mediaPlayer=MediaPlayer.create(LevelSixActivity.this,R.raw.right);
            mediaPlayer.start();
            mMsgImg.setImageResource(R.drawable.correct);
            mMsgText.setText("Did it!!! Nice Man");



        }
        else{
            mMsgImg.setVisibility(View.VISIBLE);
            MediaPlayer mediaPlayer=MediaPlayer.create(LevelSixActivity.this,R.raw.wrong);
            mediaPlayer.start();
            mMsgImg.setImageResource(R.drawable.wrong);
            mMsgText.setText("Lol,It's Wrong");

        }
        mMarks.setText(score+"/"+question);

        GenerateQuestion();


    }

    @Override
    protected void onStart() {
        int color=getIntent().getIntExtra("color",0);
        mRelativeLevel2.setBackgroundColor(color);
        super.onStart();
    }
}
