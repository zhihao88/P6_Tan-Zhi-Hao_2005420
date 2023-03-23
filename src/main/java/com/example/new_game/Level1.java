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

public class Level1 extends AppCompatActivity {

    View.OnClickListener level1BtnListener;
    View.OnClickListener level1StartBtnListener;
    View.OnClickListener level1QuitBtnListener;
    TextView totalScore;
    TextView timerView;
    TextView level1_button1;
    TextView level1_button2;
    TextView level1_button3;
    TextView level1_button4;
    TextView level1_startBtn;
    TextView level1_quitBtn;


    int highlightedBtnIndex = -1;
    int score = 0;
    Random random;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1A237E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        random = new Random();
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intentLevel2 = new Intent(this, Level2.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        level1StartBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                level1_startBtn.setEnabled(false);
                level1_button1.setEnabled(true);
                level1_button2.setEnabled(true);
                level1_button3.setEnabled(true);
                level1_button4.setEnabled(true);

                countDownTimer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerView.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                    }

                    @Override
                    public void onFinish() {
                        timerView.setText("Time's up!");
                        level1_button1.setEnabled(false);
                        level1_button2.setEnabled(false);
                        level1_button3.setEnabled(false);
                        level1_button4.setEnabled(false);


                        builder.setTitle("Next Level");
                        builder.setMessage("Your score is " + String.valueOf(score) + "\nWould you want to continue?" );
                        builder.setCancelable(false);

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intentLevel2.putExtra("score", score);
                                startActivity(intentLevel2);
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(intentMain);
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                };
                countDownTimer.start();

                highlightedBtnIndex = random.nextInt(4);
                highlightNextBtn(highlightedBtnIndex);
            }
        };

        level1QuitBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(intentMain); }
        };

        level1BtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = -1;
                if (v == level1_button1) {
                    clickedBtn = 0;
                } else if (v == level1_button2) {
                    clickedBtn = 1;
                } else if (v == level1_button3) {
                    clickedBtn = 2;
                } else if (v == level1_button4) {
                    clickedBtn = 3;
                }
                if (clickedBtn == highlightedBtnIndex) {
                    score++;
                    int temp = highlightedBtnIndex;
                    do{
                        highlightedBtnIndex = random.nextInt(4);
                    } while (temp == highlightedBtnIndex);
                    highlightNextBtn(highlightedBtnIndex);
                    totalScore.setText(String.valueOf(score));
                }
            }
        };

        totalScore = findViewById(R.id.totalScore);
        timerView = findViewById(R.id.timerView1);
        level1_button1 = findViewById(R.id.level1_button1);
        level1_button2 = findViewById(R.id.level1_button2);
        level1_button3 = findViewById(R.id.level1_button3);
        level1_button4 = findViewById(R.id.level1_button4);
        level1_startBtn = findViewById(R.id.level1_startBtn);
        level1_quitBtn = findViewById(R.id.level1_quitBtn);

        level1_button1.setOnClickListener(level1BtnListener);
        level1_button2.setOnClickListener(level1BtnListener);
        level1_button3.setOnClickListener(level1BtnListener);
        level1_button4.setOnClickListener(level1BtnListener);
        level1_startBtn.setOnClickListener(level1StartBtnListener);
        level1_quitBtn.setOnClickListener(level1QuitBtnListener);

        level1_button1.setEnabled(false);
        level1_button2.setEnabled(false);
        level1_button3.setEnabled(false);
        level1_button4.setEnabled(false);

    }

    private void highlightNextBtn(int index) {
        level1_button1.setBackgroundColor(Color.TRANSPARENT);
        level1_button2.setBackgroundColor(Color.TRANSPARENT);
        level1_button3.setBackgroundColor(Color.TRANSPARENT);
        level1_button4.setBackgroundColor(Color.TRANSPARENT);
        switch (index) {
            case 0:
                level1_button1.setBackgroundColor(Color.YELLOW);
                break;
            case 1:
                level1_button2.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                level1_button3.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                level1_button4.setBackgroundColor(Color.YELLOW);
                break;
        }
    }

}