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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class LevelSevenActivity extends AppCompatActivity {
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
    AlertDialog.Builder builder7;
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
                builder7=new AlertDialog.Builder(LevelSevenActivity.this);
                builder7.setCancelable(false);
                builder7.setTitle("Time Up!!!");
                builder7.setIcon(R.drawable.smile);
                builder7.setMessage("Your score:"+score+"\nQuestion Atempted:"+question);
                int oldscore=preferences.getInt("key7",0);
                if(score>oldscore){
                    editor.putInt("key7",score);
                    editor.commit();
                }
                builder7.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startnewgame();

                    }
                });
                builder7.setNegativeButton("No,Go to home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(LevelSevenActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder7.create();
                builder7.show();

            }
        }.start();
        GenerateQuestion();
    }


    private void GenerateQuestion() {

        Random rand=new Random();
        int a=rand.nextInt(31);
        int b=rand.nextInt(31);
        int operator=rand.nextInt(4);
        locationofcorrect=rand.nextInt(4);
        arrayList.clear();
        switch (operator){
            case 0:
                mExpression.setText(a+"+"+b+"=?");
                for(int i=0;i<4;i++){
                    if(i==locationofcorrect){
                        arrayList.add(a+b);
                    }
                    else{
                        int no=20+rand.nextInt(100);
                        while (no==(a+b)){
                            no=20+rand.nextInt(100);
                        }
                        arrayList.add(no);
                    }

                }
                break;
            case 1:
                mExpression.setText(a+"-"+b+"=?");

                for(int i=0;i<4;i++){
                    if(i==locationofcorrect){
                        arrayList.add(a-b);
                    }
                    else{
                        int no=20+rand.nextInt(100);
                        while (no==(a-b)){
                            no=20+rand.nextInt(100);
                        }
                        arrayList.add(no);
                    }

                }
                break;
            case 2:

                mExpression.setText(a+"X"+b+"=?");
                for(int i=0;i<4;i++){
                    if(i==locationofcorrect){
                        arrayList.add(a*b);
                    }
                    else{
                        int no=20+rand.nextInt(100);
                        while (no==(a*b)){
                            no=20+rand.nextInt(100);
                        }
                        arrayList.add(no);
                    }

                }
                break;
            case 3:

                mExpression.setText(a+"%"+b+"=?");
                for(int i=0;i<4;i++){
                    if(i==locationofcorrect){
                        arrayList.add(a%b);
                    }
                    else{
                        int no=20+rand.nextInt(100);
                        while (no==(a%b)){
                            no=20+rand.nextInt(100);
                        }
                        arrayList.add(no);
                    }

                }
                break;
                default:
                    break;
        }





        button0.setText(Integer.toString(arrayList.get(0)));
        button1.setText(Integer.toString(arrayList.get(1)));
        button2.setText(Integer.toString(arrayList.get(2)));
        button3.setText(Integer.toString(arrayList.get(3)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_seven);
        relativeLayout1=(RelativeLayout)findViewById(R.id.relativelvl7);

        mTimer=(TextView)findViewById(R.id.timerlvl7);
        mExpression=(TextView)findViewById(R.id.explvl7);
        mMarks=(TextView)findViewById(R.id.marklvl7);
        button0=(Button)findViewById(R.id.button0lvl7);
        button1=(Button)findViewById(R.id.button1lvl7);
        button2=(Button)findViewById(R.id.button2lvl7);
        button3=(Button)findViewById(R.id.button3lvl7);
        mGoBtn=(Button)findViewById(R.id.gobtnlvl7);
        mMsgText=(TextView)findViewById(R.id.messagetextlvl7);
        mMsgImg=(CircleImageView)findViewById(R.id.messageimglvl7);
        mRelativeLevel2=(RelativeLayout)findViewById(R.id.relativelvl7main);
        preferences=this.getSharedPreferences("level7", Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    public void choosenoption(View view){
        question++;
        if(view.getTag().toString().equals(Integer.toString(locationofcorrect))){
            mMsgImg.setVisibility(View.VISIBLE);
            score++;
            MediaPlayer mediaPlayer=MediaPlayer.create(LevelSevenActivity.this,R.raw.right);
            mediaPlayer.start();
            mMsgImg.setImageResource(R.drawable.correct);
            mMsgText.setText("Nice,Its is right");



        }
        else{
            mMsgImg.setVisibility(View.VISIBLE);
            MediaPlayer mediaPlayer=MediaPlayer.create(LevelSevenActivity.this,R.raw.wrong);
            mediaPlayer.start();
            mMsgImg.setImageResource(R.drawable.wrong);
            mMsgText.setText("Sorry,False");

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
