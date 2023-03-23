package com.example.new_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener startNewGameListener;
    TextView newGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Level1.class);

        startNewGameListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        };

        newGameBtn = findViewById(R.id.newGameBtn);
        newGameBtn.setOnClickListener(startNewGameListener);
    }
}