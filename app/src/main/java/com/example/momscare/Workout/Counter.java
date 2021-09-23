package com.example.momscare.Workout;



import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momscare.R;

import java.util.Locale;

public class Counter extends AppCompatActivity {

    //navigation bar buttons
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;

    private static long START_TIME_IN_MILLIS;

    TextView mTextViewCountDown;
    Button mButtonStartPause;
    Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftinMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        //buttons and text view of the time down counter
        mTextViewCountDown = findViewById(R.id.counter_tv2);
        mButtonStartPause = findViewById(R.id.StartBtn);
        mButtonReset = findViewById(R.id.StopBtn);

        //navigation buttons
        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);
        navigate_todo =findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("time");

        long duration = Long.parseLong(msg);
        START_TIME_IN_MILLIS = (duration*60000);
        mTimeLeftinMillis = START_TIME_IN_MILLIS;
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                }else{
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning =false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    public void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    public void resetTimer(){
        mTimeLeftinMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    public void updateCountDownText(){
        int minutes = (int) (mTimeLeftinMillis /1000) / 60;
        int seconds = (int) (mTimeLeftinMillis /1000) % 60;


        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }



    //lower navigation bar button page directions
    protected void onResume() {
        super.onResume();



        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Counter.this, WorkOuts.class);
                startActivity(intent);

            }
        });



        navigate_supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Counter.this, WorkoutDashBoard.class);
                startActivity(intent);

            }
        });

    }
}