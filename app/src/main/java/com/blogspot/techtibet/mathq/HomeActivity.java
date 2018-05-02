package com.blogspot.techtibet.mathq;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    private Toolbar mToolbar;
    private RelativeLayout relativeLayout;
    int color;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MathQ");
        mBtn1=(Button)findViewById(R.id.btn1);
        mBtn2=(Button)findViewById(R.id.btn2);
        mBtn3=(Button)findViewById(R.id.btn3);
        mBtn4=(Button)findViewById(R.id.btn4);
        mBtn5=(Button)findViewById(R.id.btn5);
        mBtn6=(Button)findViewById(R.id.btn6);
        mBtn7=(Button)findViewById(R.id.btn7);




    }
    public void selectoption(View view){


int tag= Integer.parseInt(view.getTag().toString());
        Intent intent;
        switch (tag){
            case 1:
                intent=new Intent(HomeActivity.this,LevelOneActivity.class);
                intent.putExtra("color",color);
                startActivity(intent);
                break;
            case 2:
                SharedPreferences preferences1=this.getSharedPreferences("level1", Context.MODE_PRIVATE);
                int value1=preferences1.getInt("key1",0);
                if(value1>10){
                    intent=new Intent(HomeActivity.this,LevelTwoActivity.class);
                    intent.putExtra("color",color);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Score more than 10 in Level 1 to play this level!", Toast.LENGTH_SHORT).show();
                }

                break;
            case 3:
                SharedPreferences preferences2=this.getSharedPreferences("level2", Context.MODE_PRIVATE);
                int value2=preferences2.getInt("key2",0);
                if(value2>10){
                    intent=new Intent(HomeActivity.this,LevelThreeActivity.class);
                    intent.putExtra("color",color);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Score more than 10 in Level 2 to play this level!", Toast.LENGTH_SHORT).show();
                }

                break;
            case 4:
                SharedPreferences preferences3=this.getSharedPreferences("level3", Context.MODE_PRIVATE);
                int value3=preferences3.getInt("key3",0);
                if(value3>10){
                    intent=new Intent(HomeActivity.this,LevelFourActivity.class);
                    intent.putExtra("color",color);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "You need to score more than 10 in level 3 to play this level!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
                SharedPreferences preferences4=this.getSharedPreferences("level4", Context.MODE_PRIVATE);
                int value4=preferences4.getInt("key4",0);
                if(value4>10){
                    intent=new Intent(HomeActivity.this,LevelFiveActivity.class);
                    intent.putExtra("color",color);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "You need to score more than 10 in level 4 to play this level!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6:
                SharedPreferences preferences5=this.getSharedPreferences("level5", Context.MODE_PRIVATE);
                int value5=preferences5.getInt("key5",0);
                if(value5>10){
                    intent=new Intent(HomeActivity.this,LevelSixActivity.class);
                    intent.putExtra("color",color);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "You need to score more than 10 in level 5 to play this level!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 7:
                SharedPreferences preferences6=this.getSharedPreferences("level6", Context.MODE_PRIVATE);
                int value6=preferences6.getInt("key6",0);
                if(value6>10){
                    intent=new Intent(HomeActivity.this,LevelSevenActivity.class);
                    intent.putExtra("color",color);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "You need to score more than 10 in level 6 to to unlock level!", Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    Toast.makeText(this, "Hello there", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.bgcolor){
            Random random=new Random();
            color= Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
            relativeLayout.setBackgroundColor(color);
        }
        if(item.getItemId()==R.id.exitbtn){
            callexit();
        }
        if(item.getItemId()==R.id.highscore){
        Intent intent=new Intent(HomeActivity.this,HighScoreActivity.class);
        startActivity(intent);
        }
        return false;
    }

    private void callexit() {
        AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Do you really want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(1);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create();
        builder.show();
    }

    @Override
    public void onBackPressed() {
callexit();
    }
}
