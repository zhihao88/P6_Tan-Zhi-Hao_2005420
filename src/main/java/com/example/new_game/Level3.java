package com.example.new_game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Level3 extends AppCompatActivity {
    View.OnClickListener level3BtnListener;
    View.OnClickListener level3StartBtnListener;
    View.OnClickListener level3QuitBtnListener;
    TextView totalScore3;
    TextView timerView3;
    TextView level3_startBtn;
    TextView level3_quitBtn;

    private int score;
    int highlightedBtnIndex = -1;
    Random random;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1A237E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intentLevel3 = getIntent();
        score = intentLevel3.getIntExtra("score", score);

        TextView[] level3_button = new TextView[16];

        random = new Random();
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intentLevel4 = new Intent(this, Level4.class);
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

        level3StartBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < 16; i++) {
                    int resID = getResources().getIdentifier("level3_button"+ (i+1), "id", getPackageName());
                    level3_button[i].setEnabled(true);
                }
                level3_startBtn.setEnabled(false);


                countDownTimer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerView3.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                    }

                    @Override
                    public void onFinish() {
                        timerView3.setText("Time's up!");
                        for(int i = 0; i < 16; i++) {
                            int resID = getResources().getIdentifier("level3_button"+ (i+1), "id", getPackageName());
                            level3_button[i].setEnabled(false);
                        }

                        builder3.setTitle("Next Level");
                        builder3.setMessage("Your score is " + String.valueOf(score) + "\nWould you want to continue?" );
                        builder3.setCancelable(false);

                        builder3.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intentLevel4.putExtra("score", score);
                                startActivity(intentLevel4);
                            }
                        });

                        builder3.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(intentMain);
                            }
                        });

                        AlertDialog alert3 = builder3.create();
                        alert3.show();
                    }
                };
                countDownTimer.start();

                highlightedBtnIndex = random.nextInt(16);
                highlightNextBtn3(highlightedBtnIndex, level3_button);
            }
        };

        level3QuitBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(intentMain); }
        };

        level3BtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = -1;
                for(int i = 0; i < 16; i++) {
                    int resID = getResources().getIdentifier("level3_button"+ (i+1), "id", getPackageName());
                    if (v == level3_button[i]) {
                        clickedBtn = i;
                    }
                }

                if (clickedBtn == highlightedBtnIndex) {
                    score++;
                    int temp = highlightedBtnIndex;
                    do{
                        highlightedBtnIndex = random.nextInt(16);
                    } while (temp == highlightedBtnIndex);
                    highlightNextBtn3(highlightedBtnIndex, level3_button);
                    totalScore3.setText(String.valueOf(score));
                }
            }
        };

        totalScore3 = findViewById(R.id.totalScore3);
        timerView3 = findViewById(R.id.timerView3);
        level3_startBtn = findViewById(R.id.level3_startBtn);
        level3_quitBtn = findViewById(R.id.level3_quitBtn);
        for(int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("level3_button"+ (i+1), "id", getPackageName());
            level3_button[i] = findViewById(resID);
        }

        level3_startBtn.setOnClickListener(level3StartBtnListener);
        level3_quitBtn.setOnClickListener(level3QuitBtnListener);
        for(int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("level3_button"+ (i+1), "id", getPackageName());
            level3_button[i].setOnClickListener(level3BtnListener);
        }

        for(int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("level3_button"+ (i+1), "id", getPackageName());
            level3_button[i].setEnabled(false);
        }

        totalScore3.setText(String.valueOf(score));
    }



    private void highlightNextBtn3(int index, TextView[] level3_button) {

        for (int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("level3_button" + (i + 1), "id", getPackageName());
            level3_button[i].setBackgroundColor(Color.TRANSPARENT);
        }


        for (int i = 0; i < 16; i++) {
            int resID = getResources().getIdentifier("level3_button" + (i + 1), "id", getPackageName());
            if (index == i) {
                level3_button[i].setBackgroundColor(Color.YELLOW);
            }
        }

    }
}