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

public class Level4 extends AppCompatActivity {

    View.OnClickListener level4BtnListener;
    View.OnClickListener level4StartBtnListener;
    View.OnClickListener level4QuitBtnListener;
    TextView totalScore4;
    TextView timerView4;
    TextView level4_startBtn;
    TextView level4_quitBtn;

    private int score;
    int highlightedBtnIndex = -1;
    Random random;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1A237E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intentLevel4 = getIntent();
        score = intentLevel4.getIntExtra("score", score);

        TextView[] level4_button = new TextView[25];

        random = new Random();
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intentLevel5 = new Intent(this, Level5.class);
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);

        level4StartBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < 25; i++) {
                    int resID = getResources().getIdentifier("level4_button"+ (i+1), "id", getPackageName());
                    level4_button[i].setEnabled(true);
                }
                level4_startBtn.setEnabled(false);


                countDownTimer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerView4.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                    }

                    @Override
                    public void onFinish() {
                        timerView4.setText("Time's up!");
                        for(int i = 0; i < 25; i++) {
                            int resID = getResources().getIdentifier("level4_button"+ (i+1), "id", getPackageName());
                            level4_button[i].setEnabled(false);
                        }

                        builder4.setTitle("Next Level");
                        builder4.setMessage("Your score is " + String.valueOf(score) + "\nWould you want to continue?" );
                        builder4.setCancelable(false);

                        builder4.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intentLevel5.putExtra("score", score);
                                startActivity(intentLevel5);
                            }
                        });

                        builder4.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(intentMain);
                            }
                        });

                        AlertDialog alert4 = builder4.create();
                        alert4.show();
                    }
                };
                countDownTimer.start();

                highlightedBtnIndex = random.nextInt(25);
                highlightNextBtn4(highlightedBtnIndex, level4_button);
            }
        };

        level4QuitBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(intentMain); }
        };

        level4BtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = -1;
                for(int i = 0; i < 25; i++) {
                    int resID = getResources().getIdentifier("level4_button"+ (i+1), "id", getPackageName());
                    if (v == level4_button[i]) {
                        clickedBtn = i;
                    }
                }

                if (clickedBtn == highlightedBtnIndex) {
                    score++;
                    int temp = highlightedBtnIndex;
                    do{
                        highlightedBtnIndex = random.nextInt(25);
                    } while (temp == highlightedBtnIndex);
                    highlightNextBtn4(highlightedBtnIndex, level4_button);
                    totalScore4.setText(String.valueOf(score));
                }
            }
        };

        totalScore4 = findViewById(R.id.totalScore4);
        timerView4 = findViewById(R.id.timerView4);
        level4_startBtn = findViewById(R.id.level4_startBtn);
        level4_quitBtn = findViewById(R.id.level4_quitBtn);
        for(int i = 0; i < 25; i++) {
            int resID = getResources().getIdentifier("level4_button"+ (i+1), "id", getPackageName());
            level4_button[i] = findViewById(resID);
        }

        level4_startBtn.setOnClickListener(level4StartBtnListener);
        level4_quitBtn.setOnClickListener(level4QuitBtnListener);
        for(int i = 0; i < 25; i++) {
            int resID = getResources().getIdentifier("level4_button"+ (i+1), "id", getPackageName());
            level4_button[i].setOnClickListener(level4BtnListener);
        }

        for(int i = 0; i < 25; i++) {
            int resID = getResources().getIdentifier("level4_button"+ (i+1), "id", getPackageName());
            level4_button[i].setEnabled(false);
        }

        totalScore4.setText(String.valueOf(score));
    }



    private void highlightNextBtn4(int index, TextView[] level4_button) {

        for (int i = 0; i < 25; i++) {
            int resID = getResources().getIdentifier("level4_button" + (i + 1), "id", getPackageName());
            level4_button[i].setBackgroundColor(Color.TRANSPARENT);
        }


        for (int i = 0; i < 25; i++) {
            int resID = getResources().getIdentifier("level4_button" + (i + 1), "id", getPackageName());
            if (index == i) {
                level4_button[i].setBackgroundColor(Color.YELLOW);
            }
        }

    }
}