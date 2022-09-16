package com.ishak.hareketiyakala;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.view.Display;///araştır
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    TextView textViewTime, textViewScore;
    int score;
    int screenX, screenY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;
        imageView1= findViewById(R.id.imageView1);
        textViewTime = findViewById(R.id.textViewTime);
        textViewScore = findViewById(R.id.textViewScore);

        playGame();

    }

    private void playGame(){
        textViewScore.setText("Score:0");
        score = 0;
        Random random=new Random();
        CountDownTimer countDownTimer=new CountDownTimer(10000,500) {
            @Override
            public void onTick(long l) {
                textViewTime.setText("Time: " + l / 1000);
                ///getMeasuredWidth() image'in genişlik boyutu getirir.

                int locationX=random.nextInt(screenX - 2*imageView1.getMeasuredWidth());
                int locationY=random.nextInt(screenY - 2*imageView1.getMeasuredHeight());
                imageView1.setX(locationX);
                imageView1.setY(locationY);
            }

            @Override
            public void onFinish() {

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Finished");
                alert.setMessage("Do you want to try again?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {/// i'ne demek araştır.
                        Toast.makeText(MainActivity.this,"Game is starting",Toast.LENGTH_SHORT).show();
                        playGame();

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game is finished,",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                alert.show();

            }
        };
        countDownTimer.start();
    }


    public void clickImage(View view){///private yaptığımda çalışmıyor

        score++;
        textViewScore.setText("Score:" + score);
    }





}