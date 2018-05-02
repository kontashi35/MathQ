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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class LevelOneActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    private CircleImageView mAnswerImg;
    private RelativeLayout relativeLayout1;
    Button startButton;
    TextView resultTextView;
    CircleImageView resultImageView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    public  SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        relativeLayout1=(RelativeLayout)findViewById(R.id.levelonelayout);
        Intent intent=getIntent();
        int color=intent.getIntExtra("color",0);
        relativeLayout1.setBackgroundColor(color);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        mAnswerImg=(CircleImageView) findViewById(R.id.rightresult);
        preferences=this.getSharedPreferences("level1", MODE_PRIVATE);
        editor=preferences.edit();

        

    }
    public void playAgain(final View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                int oldscore=preferences.getInt("key1",0);
                if(score>oldscore){

                    editor.putInt("key1",score);
                    editor.commit();
                }
                else{
                    oldscore=oldscore;
                }
                builder=new AlertDialog.Builder(LevelOneActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Time Up!!!");
                builder.setIcon(R.drawable.smile);
                builder.setMessage("Your score:"+score);
                builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playAgain(view);

                    }
                });
                builder.setNegativeButton("No,Go to home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(LevelOneActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.create();
                builder.show();


            }
        }.start();


    }
    public void generateQuestion() {


        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answers.add(incorrectAnswer);

            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }
    public void chooseAnswer(View view) {
        mAnswerImg.setVisibility(View.VISIBLE);
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.right);

            score++;
            resultTextView.setText("Correct!");
            mediaPlayer.start();
            mAnswerImg.setImageResource(R.drawable.correct);

        } else {
            MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.wrong);
            mAnswerImg.setImageResource(R.drawable.wrong);
            resultTextView.setText("Wrong!");
            mediaPlayer.start();

        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();


    }
    public void start(View view) {

        SharedPreferences preferences=this.getSharedPreferences("level1", Context.MODE_PRIVATE);
        int level1score=preferences.getInt("key1",0);
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }
}
