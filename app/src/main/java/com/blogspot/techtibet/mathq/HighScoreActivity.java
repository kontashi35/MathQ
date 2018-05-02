package com.blogspot.techtibet.mathq;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by WS7 on 4/7/2018.
 */

public class HighScoreActivity extends Activity {
    private TextView mLevelScore1,mLevelScore2,mLevelScore3,mLevelScore4,mLevelScore5,mLevelScore6,mLevelScore7;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        setTitle("High Score");
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));
        mLevelScore1=(TextView)findViewById(R.id.levelscore1);
        mLevelScore2=(TextView)findViewById(R.id.levelscore2);
        mLevelScore3=(TextView)findViewById(R.id.levelscore3);
        mLevelScore4=(TextView)findViewById(R.id.levelscore4);
        mLevelScore5=(TextView)findViewById(R.id.levelscore5);
        mLevelScore6=(TextView)findViewById(R.id.levelscore6);
        mLevelScore7=(TextView)findViewById(R.id.levelscore7);
        SharedPreferences preferences1=this.getSharedPreferences("level1", Context.MODE_PRIVATE);
        int level1score=preferences1.getInt("key1",0);
        mLevelScore1.setText(Integer.toString(level1score));
        SharedPreferences preferences2=this.getSharedPreferences("level2", Context.MODE_PRIVATE);
        int level2score=preferences2.getInt("key2",0);
        mLevelScore2.setText(Integer.toString(level2score));

        SharedPreferences preferences3=this.getSharedPreferences("level3", Context.MODE_PRIVATE);
        int level3score=preferences3.getInt("key3",0);
        mLevelScore3.setText(Integer.toString(level3score));

        SharedPreferences preferences4=this.getSharedPreferences("level4", Context.MODE_PRIVATE);
        int level4score=preferences4.getInt("key4",0);
        mLevelScore4.setText(Integer.toString(level4score));

        SharedPreferences preferences5=this.getSharedPreferences("level5", Context.MODE_PRIVATE);
        int level5score=preferences5.getInt("key5",0);
        mLevelScore5.setText(Integer.toString(level5score));

        SharedPreferences preferences6=this.getSharedPreferences("level6", Context.MODE_PRIVATE);
        int level6score=preferences6.getInt("key6",0);
        mLevelScore6.setText(Integer.toString(level6score));
        SharedPreferences preferences7=this.getSharedPreferences("level7", Context.MODE_PRIVATE);
        int level7score=preferences7.getInt("key7",0);
        mLevelScore7.setText(Integer.toString(level7score));




    }
}
