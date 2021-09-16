package com.power.bunkTech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class NoOfSeatsOccupiedActivity extends AppCompatActivity {

    private Button minus, plus, reset, back, exit;
    private int counter = 0;

    private static final long START_TIME_IN_MILLIS = 18000000;
    private static final long START_TIME_IN_MILLIS1 = 18000000;

    private TextView mTextViewCountDown,mTextViewCountDown1, counterTxt, shift1, shift2;
    private Button mButtonStartPause,mButtonStartPause1;
    private Button mButtonReset,mButtonReset1;
    private CountDownTimer mCountDownTimer,mCountDownTimer1;
    private boolean mTimerRunning,mTimerRunning1;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis1 = START_TIME_IN_MILLIS1;

    private long mEndTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_of_seats_occupied);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shift1 = findViewById(R.id.shift1);
        shift2 = findViewById(R.id.shift2);

        counterTxt = findViewById(R.id.counterTxt);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        reset = findViewById(R.id.reset);
        back = findViewById(R.id.back);
        exit = findViewById(R.id.exit);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mTextViewCountDown1 = findViewById(R.id.text_view_countdown1);
        mButtonStartPause1 = findViewById(R.id.button_start_pause1);
        mButtonReset = findViewById(R.id.button_reset);
        mButtonReset1 = findViewById(R.id.button_reset1);



        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                counterTxt.setText(Integer.toString(counter));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                counterTxt.setText(Integer.toString(counter));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                counterTxt.setText(Integer.toString(counter));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoOfSeatsOccupiedActivity.this, DestinationActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoOfSeatsOccupiedActivity.this, SelectScreenActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
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

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateButtons();
            }
        }.start();
        mTimerRunning = true;
        updateButtons();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateButtons();
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / (1000 * 60 * 60)) % 24;
        int minutes = (int) (mTimeLeftInMillis / (1000 * 60)) % 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours, minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void updateButtons() {
        if (mTimerRunning) {
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis < START_TIME_IN_MILLIS) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

        @Override
        protected void onStop() {
            super.onStop();
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("millisLeft", mTimeLeftInMillis);
            editor.putBoolean("timerRunning", mTimerRunning);
            editor.putLong("millisLeft1", mTimeLeftInMillis1);
            editor.putBoolean("timerRunning1", mTimerRunning1);
            editor.putLong("endTime", mEndTime);
            editor.apply();
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
            }if(mCountDownTimer1 != null) {
                mCountDownTimer1.cancel();
            }
        }
        @Override
        protected void onStart() {
            super.onStart();
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
            mTimerRunning = prefs.getBoolean("timerRunning", false);
            mTimeLeftInMillis1 = prefs.getLong("millisLeft1", START_TIME_IN_MILLIS1);
            mTimerRunning1 = prefs.getBoolean("timerRunning1", false);
            updateCountDownText();
            updateButtons();
            updateCountDownText1();
            updateButtons1();
            if (mTimerRunning) {
                mEndTime = prefs.getLong("endTime", 0);
                mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
                if (mTimeLeftInMillis < 0) {
                    mTimeLeftInMillis = 0;
                    mTimerRunning = false;
                    updateCountDownText();
                    updateButtons();
                } else {
                    startTimer();
                }
            } if (mTimerRunning1) {
                mEndTime = prefs.getLong("endTime", 0);
                mTimeLeftInMillis1 = mEndTime - System.currentTimeMillis();
                if (mTimeLeftInMillis1 < 0) {
                    mTimeLeftInMillis1 = 0;
                    mTimerRunning1 = false;
                    updateCountDownText1();
                    updateButtons1();
                } else {
                    startTimer1();
                }
            }

        mButtonStartPause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning1) {
                    pauseTimer1();
                } else {
                    startTimer1();
                }
            }
        });
        mButtonReset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer1();
            }
        });
        updateCountDownText1();
    }

    private void startTimer1() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis1;
        mCountDownTimer1 = new CountDownTimer(mTimeLeftInMillis1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis1 = millisUntilFinished;
                updateCountDownText1();
            }

            @Override
            public void onFinish() {
                mTimerRunning1 = false;
                updateButtons1();
            }
        }.start();
        mTimerRunning1 = true;
        updateButtons1();
    }

    private void pauseTimer1() {
        mCountDownTimer1.cancel();
        mTimerRunning1 = false;
        updateButtons1();
    }

    private void resetTimer1() {
        mTimeLeftInMillis1 = START_TIME_IN_MILLIS1;
        updateCountDownText1();
        updateButtons1();
    }

    private void updateCountDownText1() {
        int hours1 = (int) (mTimeLeftInMillis1 / (1000 * 60 * 60)) % 24;
        int minutes1 = (int) (mTimeLeftInMillis1 / (1000 * 60)) % 60;
        int seconds1 = (int) (mTimeLeftInMillis1 / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours1, minutes1, seconds1);
        mTextViewCountDown1.setText(timeLeftFormatted);
    }

    private void updateButtons1() {
        if (mTimerRunning1) {
            mButtonReset1.setVisibility(View.INVISIBLE);
            mButtonStartPause1.setText("Pause");
        } else {
            mButtonStartPause1.setText("Start");
            if (mTimeLeftInMillis1 < 1000) {
                mButtonStartPause1.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause1.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis1 < START_TIME_IN_MILLIS1) {
                mButtonReset1.setVisibility(View.VISIBLE);
            } else {
                mButtonReset1.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLougout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));

                break;
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", counter);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endTime", mEndTime);
        outState.putLong("millisLeft1", mTimeLeftInMillis1);
        outState.putBoolean("timerRunning1", mTimerRunning1);
    }

}
