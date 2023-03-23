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

public class Level2 extends AppCompatActivity {

    View.OnClickListener level2BtnListener;
    View.OnClickListener level2StartBtnListener;
    View.OnClickListener level2QuitBtnListener;
    TextView totalScore2;
    TextView timerView2;
    TextView level2_startBtn;
    TextView level2_quitBtn;

    private int score;
    int highlightedBtnIndex = -1;
    Random random;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1A237E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intentLevel2 = getIntent();
        score = intentLevel2.getIntExtra("score", score);

        TextView[] level2_button = new TextView[9];

        random = new Random();
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intentLevel3 = new Intent(this, Level3.class);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

        level2StartBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < 9; i++) {
                    int resID = getResources().getIdentifier("level2_button"+ (i+1), "id", getPackageName());
                    level2_button[i].setEnabled(true);
                }
                level2_startBtn.setEnabled(false);


                countDownTimer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerView2.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                    }

                    @Override
                    public void onFinish() {
                        timerView2.setText("Time's up!");
                        for(int i = 0; i < 9; i++) {
                            int resID = getResources().getIdentifier("level2_button"+ (i+1), "id", getPackageName());
                            level2_button[i].setEnabled(false);
                        }

                        builder2.setTitle("Next Level");
                        builder2.setMessage("Your score is " + String.valueOf(score) + "\nWould you want to continue?" );
                        builder2.setCancelable(false);

                        builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intentLevel3.putExtra("score", score);
                                startActivity(intentLevel3);
                            }
                        });

                        builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(intentMain);
                            }
                        });

                        AlertDialog alert2 = builder2.create();
                        alert2.show();
                    }
                };
                countDownTimer.start();

                highlightedBtnIndex = random.nextInt(9);
                highlightNextBtn2(highlightedBtnIndex, level2_button);
            }
        };

        level2QuitBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(intentMain); }
        };

        level2BtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = -1;
                for(int i = 0; i < 9; i++) {
                    int resID = getResources().getIdentifier("level2_button"+ (i+1), "id", getPackageName());
                    if (v == level2_button[i]) {
                        clickedBtn = i;
                    }
                }

                if (clickedBtn == highlightedBtnIndex) {
                    score++;
                    int temp = highlightedBtnIndex;
                    do{
                        highlightedBtnIndex = random.nextInt(9);
                    } while (temp == highlightedBtnIndex);
                    highlightNextBtn2(highlightedBtnIndex, level2_button);
                    totalScore2.setText(String.valueOf(score));
                }
            }
        };

        totalScore2 = findViewById(R.id.totalScore2);
        timerView2 = findViewById(R.id.timerView2);
        level2_startBtn = findViewById(R.id.level2_startBtn);
        level2_quitBtn = findViewById(R.id.level2_quitBtn);
        for(int i = 0; i < 9; i++) {
            int resID = getResources().getIdentifier("level2_button"+ (i+1), "id", getPackageName());
            level2_button[i] = findViewById(resID);
        }

        level2_startBtn.setOnClickListener(level2StartBtnListener);
        level2_quitBtn.setOnClickListener(level2QuitBtnListener);
        for(int i = 0; i < 9; i++) {
            int resID = getResources().getIdentifier("level2_button"+ (i+1), "id", getPackageName());
            level2_button[i].setOnClickListener(level2BtnListener);
        }

        for(int i = 0; i < 9; i++) {
            int resID = getResources().getIdentifier("level2_button"+ (i+1), "id", getPackageName());
            level2_button[i].setEnabled(false);
        }

        totalScore2.setText(String.valueOf(score));
    }

    private void highlightNextBtn2(int index, TextView[] level2_button) {

        for (int i = 0; i < 9; i++) {
            int resID = getResources().getIdentifier("level2_button" + (i + 1), "id", getPackageName());
            level2_button[i].setBackgroundColor(Color.TRANSPARENT);
        }


        for (int i = 0; i < 9; i++) {
            int resID = getResources().getIdentifier("level2_button" + (i + 1), "id", getPackageName());
            if (index == i) {
                level2_button[i].setBackgroundColor(Color.YELLOW);
            }
        }

    }
}