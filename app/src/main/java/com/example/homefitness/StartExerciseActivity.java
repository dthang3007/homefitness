package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StartExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonStartPause;
    private TextView mTextViewCountDown;
    CountDownTimer Timer;
    private static final long START_TIME_IN_MILLIS = 10000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean mTimerRunning;
    private Pack pack;
    private Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
         pack=(Pack) bundle.get("pack");

        mTimeLeftInMillis=pack.getTimePack()*1000;
        setContentView(R.layout.activity_start_exercise);
        mButtonStartPause = findViewById(R.id.startStop);
        mTextViewCountDown = findViewById(R.id.timeCountDown);
        mButtonStartPause.setOnClickListener(this);

        buttonBack=findViewById(R.id.button3);
        buttonBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startTimer();

        switch (view.getId()){
            case R.id.button3:
                Intent i = new Intent(StartExerciseActivity.this,Exercise_Set.class);
                startActivity(i);
                break;
        }
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Finish");
            }
        }.start();

        mTimerRunning = true;

    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}