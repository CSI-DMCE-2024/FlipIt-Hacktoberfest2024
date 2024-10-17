package com.example.memorytilegame.levels;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.memorytilegame.MainActivity;
import com.example.memorytilegame.R;

import android.app.AlertDialog;
import android.widget.EditText;

public class LevelsScreen extends AppCompatActivity {

Button easy, medium, hard;
ImageView goBackFromLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_screen);

        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        goBackFromLevels = findViewById(R.id.goBackFromLevels);

        //gird size assigned based on chosen level
        easy.setOnClickListener(v -> nameDialog(3, 12));
        medium.setOnClickListener(v -> nameDialog(4, 20));
        hard.setOnClickListener(v -> nameDialog(5, 30));

        goBackFromLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LevelsScreen.this, MainActivity.class));
                finish();
            }
        });

    }
    //function to call name dialog box
    public void nameDialog(int cols, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name Dialog");

        final EditText input = new EditText(this);
        input.setHint("Enter your Name");
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String playerName = input.getText().toString().trim();
            if (playerName.isEmpty()) {
                playerName = "Player";
            }
            startGame(cols, index, playerName);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

//    public void startGame(View view) {
//        startAnimation(view);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(LevelsScreen.this, GameActivity.class);
//                int cols, index;
//                if(view.equals(easy)) {
//                    cols = 3;
//                    index = 12;
//                }
//                else if(view.equals(medium)){
//                    cols = 4;
//                    index = 20;
//                }
//                else{
//                    cols = 5;
//                    index = 30;
//                }
//                intent.putExtra("noOfColumns", cols);
//                intent.putExtra("index", index);
//                startActivity(intent);
//                finish();
//            }
//        }, 100);
//    }

    public void startGame(int cols, int index, String playerName) {
        Intent intent = new Intent(LevelsScreen.this, GameActivity.class);
        intent.putExtra("noOfColumns", cols);
        intent.putExtra("index", index);
        intent.putExtra("playerName", playerName);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(LevelsScreen.this, MainActivity.class));
        finish();
        super.onBackPressed();
    }

    public void startAnimation(View v){
        v.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(50)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(() -> {
                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(50)
                            .setInterpolator(new DecelerateInterpolator())
                            .start();
                }).start();
    }
}