package com.blogspot.techtibet.mathq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private Button mNextBtn;
    private Button mBackBtn;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private int mCurrentPage;
    private TextView[] mDot;
    private int active;
    private SharedPreferences preferences;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mNextBtn=(Button)findViewById(R.id.nextbtn);
        mBackBtn=(Button)findViewById(R.id.backbtn);
        mDotLayout=(LinearLayout)findViewById(R.id.linlayout);

        preferences=this.getSharedPreferences("myprefkey", MODE_PRIVATE);
        final SharedPreferences.Editor editor=preferences.edit();
        active=preferences.getInt("key",0);
        if(active==1){
           Intent intent=new Intent(MainActivity.this,HomeActivity.class);
           startActivity(intent);
            finish();
        }


        sliderAdapter=new SliderAdapter(this);
        mViewPager.setAdapter(sliderAdapter);

        addDotIndicator(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                addDotIndicator(position);
                mCurrentPage=position;
                if(position==0){
                    mBackBtn.setEnabled(false);
                    mNextBtn.setEnabled(true);
                    mBackBtn.setText("");
                    mBackBtn.setVisibility(View.INVISIBLE);
                    mNextBtn.setText("Next");
                }
                else if(position==mDot.length-1){
                    mNextBtn.setText("Finish");
                    mBackBtn.setEnabled(true);
                    mNextBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);

                }
                else if(position==mDot.length){

                }
                else{
                    mBackBtn.setEnabled(true);
                    mNextBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);
                    mNextBtn.setText("Next");
                    mBackBtn.setText("Back");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mCurrentPage+1);
                count++;
                if(count>2){
                    active=1;
                    editor.putInt("key",active);
                    editor.commit();
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mCurrentPage-1);
            }
        });

    }

    private void addDotIndicator(int position) {
     mDot=new TextView[3];
     mDotLayout.removeAllViews();
     for(int i=0;i<mDot.length;i++){
         mDot[i]=new TextView(this);
         mDot[i].setText(Html.fromHtml("&#8226"));
         mDot[i].setTextSize(35f);
         mDot[i].setTextColor(getResources().getColor(R.color.colorWhite));
         mDotLayout.addView(mDot[i]);
     }
     if(mDot.length>0){
         mDot[position].setTextColor(getResources().getColor(R.color.colorPrimary));
     }
    }



    @Override
    protected void onStart() {
        super.onStart();
        preferences=this.getSharedPreferences("myprefkey",Context.MODE_PRIVATE);
       int status=preferences.getInt("key",0);

        if(status==1){
           Intent intent=new Intent(MainActivity.this,HomeActivity.class);
           startActivity(intent);
           finish();

       }
    }
}
