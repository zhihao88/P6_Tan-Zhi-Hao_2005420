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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Level5 extends AppCompatActivity {

    View.OnClickListener level5BtnListener;
    View.OnClickListener level5StartBtnListener;
    View.OnClickListener level5QuitBtnListener;
    TextView totalScore5;
    TextView timerView5;
    TextView level5_startBtn;
    TextView level5_quitBtn;

    private int score;
    int highlightedBtnIndex = -1;
    Random random;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1A237E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intentLevel5 = getIntent();
        score = intentLevel5.getIntExtra("score", score);

        TextView[] level5_button = new TextView[36];

        random = new Random();
        Intent intentMain = new Intent(this, MainActivity.class);
        AlertDialog.Builder builder5 = new AlertDialog.Builder(this);

        level5StartBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 36; i++) {
                    int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
                    level5_button[i].setEnabled(true);
                }
                level5_startBtn.setEnabled(false);


                countDownTimer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerView5.setText(String.valueOf((millisUntilFinished / 1000) + 1));
                    }

                    @Override
                    public void onFinish() {
                        timerView5.setText("Time's up!");
                        for (int i = 0; i < 36; i++) {
                            int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
                            level5_button[i].setEnabled(false);
                        }

                        builder5.setTitle("Game Over");
                        builder5.setMessage("Your score is " + String.valueOf(score) + "\nWould you want back to Menu?");
                        builder5.setCancelable(false);

                        builder5.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               startActivity(intentMain);
                            }
                        });

                        AlertDialog alert5 = builder5.create();
                        alert5.show();
                    }
                };

                countDownTimer.start();

                highlightedBtnIndex = random.nextInt(36);
                highlightNextBtn5(highlightedBtnIndex, level5_button);
            }
        };


        level5QuitBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentMain);
            }
        };

        level5BtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedBtn = -1;
                for (int i = 0; i < 36; i++) {
                    int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
                    if (v == level5_button[i]) {
                        clickedBtn = i;
                    }
                }

                if (clickedBtn == highlightedBtnIndex) {
                    score++;
                    int temp = highlightedBtnIndex;
                    do {
                        highlightedBtnIndex = random.nextInt(36);
                    } while (temp == highlightedBtnIndex);
                    highlightNextBtn5(highlightedBtnIndex, level5_button);
                    totalScore5.setText(String.valueOf(score));
                }
            }
        };

        totalScore5 = findViewById(R.id.totalScore5);
        timerView5 = findViewById(R.id.timerView5);
        level5_startBtn = findViewById(R.id.level5_startBtn);
        level5_quitBtn = findViewById(R.id.level5_quitBtn);
        for (int i = 0; i < 36; i++) {
            int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
            level5_button[i] = findViewById(resID);
        }

        level5_startBtn.setOnClickListener(level5StartBtnListener);
        level5_quitBtn.setOnClickListener(level5QuitBtnListener);
        for (int i = 0; i < 36; i++) {
            int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
            level5_button[i].setOnClickListener(level5BtnListener);
        }

        for (int i = 0; i < 36; i++) {
            int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
            level5_button[i].setEnabled(false);
        }

        totalScore5.setText(String.valueOf(score));
    }


    private void highlightNextBtn5(int index, TextView[] level5_button) {

        for (int i = 0; i < 36; i++) {
            int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
            level5_button[i].setBackgroundColor(Color.TRANSPARENT);
        }


        for (int i = 0; i < 36; i++) {
            int resID = getResources().getIdentifier("level5_button" + (i + 1), "id", getPackageName());
            if (index == i) {
                level5_button[i].setBackgroundColor(Color.YELLOW);
            }
        }
    }

};