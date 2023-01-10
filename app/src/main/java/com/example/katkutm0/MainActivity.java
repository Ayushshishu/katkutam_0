package com.example.katkutm0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer =0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning
    // 0 - X
    // 1 - O
    // 2 -null

    int [] [] winPosition ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PlayerTab(View view) {
        ImageView img =(ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.o_turn);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.x_turn);

            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check if any player hae win
        for(int[] winPositon: winPosition){
            if(gameState[winPositon[0]] == gameState[winPositon[1]] &&
                    gameState[winPositon[1]] == gameState[winPositon[2]] &&
                    gameState[winPositon[0]] != 2 ){
                //Some body has won! = Find out who
                String winnerStr;
                gameActive = false;
                if(gameState[winPositon[0]] == 0){
                    winnerStr = "X jita hai! PARTY DO..";
                }else{
                    winnerStr = "0 jita HAI! party do..";
                }
                //Update the status bar for winner
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }
    public void gameReset (View view){
        gameActive = true;
        activePlayer = 0;
        for(int i =0; i<gameState.length ; i++){
            gameState[i] =2;
        }

        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText(R.string.x_turn);


    }
}