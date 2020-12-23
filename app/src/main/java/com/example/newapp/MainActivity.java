package com.example.newapp;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start);
        Button pause = findViewById(R.id.pause);
        Button reset = findViewById(R.id.reset);
        Button skipr = findViewById(R.id.skipr);
        Button skipl = findViewById(R.id.skipl);
        SeekBar seekBar = findViewById(R.id.seekBar2);
        TextView textView = findViewById(R.id.textView);
        Button curr = findViewById(R.id.show);
        Button tt = findViewById(R.id.show2);


        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ad1);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setProgress(0);


        Handler mHandler = new Handler();
//Make sure you update Seekbar on UI thread
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int value = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                value = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

//                textView.setText(value+" ");
                mediaPlayer.seekTo(value);

            }
        });



        curr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int tt = mediaPlayer.getCurrentPosition();
                Log.i("infod",tt+" ");
                textView.setText(" " + tt);
            }
        });

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tts = mediaPlayer.getDuration();
//                Log.i("infod",tt+" ");
                textView.setText(" " + tts);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mediaPlayer.start();

                int tt = mediaPlayer.getCurrentPosition();
                Log.i("infod",tt+" ");
                textView.setText(" " + tt);
            }
        });

        skipr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tts = mediaPlayer.getCurrentPosition();

                if(tts>5000)
                {
                    int cal = tts - 5000;

                    mediaPlayer.seekTo(cal);

                }

            }
        });

        skipl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tts = mediaPlayer.getCurrentPosition();

                int cal = tts + 5000;

                mediaPlayer.seekTo(cal);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
            }
        });


        
    }
}