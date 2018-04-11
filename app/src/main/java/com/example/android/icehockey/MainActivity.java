package com.example.android.icehockey;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA=0;
    int scoreTeamB=0;
    Spinner spinnerTeamA;
    Spinner spinnerTeamB;
    String[] textArray = { "CAN", "CZE", "FIN", "GER", "NOR", "RUS", "SVK", "SLV", "KOR", "SWE", "SUI", "USA" };
    Integer[] imageArray = { R.drawable.canada, R.drawable.czech, R.drawable.finland, R.drawable.germany,R.drawable.norway, R.drawable.russia, R.drawable.slovakia,R.drawable.slovenia, R.drawable.soukorea,R.drawable.sweden, R.drawable.switzerland, R.drawable.usa };
    private static final long START_TIME_IN_MILLIS_TeamA=120000;
    private static final long START_TIME_IN_MILLIS_TeamB=120000;
    private TextView textViewCountDownTeamA;
    private Button buttonStartTeamA;
    private TextView textViewCountDownTeamB;
    private Button buttonStartTeamB;
    private Button buttonReset;
    private CountDownTimer countDownTimerTeamA;
    private CountDownTimer countDownTimerTeamB;
    private boolean timerRunningTeamA;
    private boolean timerRunningTeamB;
    private long timeLeftInMillisTeamA = START_TIME_IN_MILLIS_TeamA;
    private long timeLeftInMillisTeamB = START_TIME_IN_MILLIS_TeamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.text_view_name);
        ImageView imageView =(ImageView)findViewById(R.id.image_view_flag);
        spinnerTeamA = (Spinner) findViewById(R.id.spinner_counries_teamA);
        spinnerTeamB = (Spinner) findViewById(R.id.spinner_counries_teamB);

        ImageArrayAdapter adapter = new ImageArrayAdapter(getApplicationContext(),imageArray,textArray);
        spinnerTeamA.setAdapter(adapter);
        spinnerTeamB.setAdapter(adapter);


        textViewCountDownTeamA = findViewById(R.id.text_teamA_countdown);
        buttonStartTeamA = findViewById(R.id.button_start_teamA);
        textViewCountDownTeamB = findViewById(R.id.text_teamB_countdown);
        buttonStartTeamB = findViewById(R.id.button_start_teamB);
        buttonReset=findViewById(R.id.button_reset);

        buttonStartTeamA.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(timerRunningTeamA) {
                    newStartTimerTeamA();
                }else{
                    startTimerTeamA();
                }

            }
        });

        buttonReset.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                reserTimer();

            }
        });

        buttonStartTeamB.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick(View view) {
                if(timerRunningTeamB) {
                    newStartTimerTeamB();
                }else{
                    startTimerTeamB();
                }
            }
        });

        buttonReset.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                reserTimer ();
            }
        });
    }
    private void startTimerTeamA() {
        countDownTimerTeamA=new CountDownTimer (timeLeftInMillisTeamA, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisTeamA=millisUntilFinished;
                updateCountDownTextTeamA();

            }

            @Override
            public void onFinish() {
                timerRunningTeamA=false;
                buttonStartTeamA.setText("Penalty");

            }
        }.start ();
        timerRunningTeamA=true;
        buttonStartTeamA.setText("New Penalty");
    }
    private void startTimerTeamB() {
        countDownTimerTeamB=new CountDownTimer (timeLeftInMillisTeamB, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisTeamB=millisUntilFinished;
                updateCountDownTextTeamB();

            }

            @Override
            public void onFinish() {
                timerRunningTeamB=false;
                buttonStartTeamB.setText("Penalty");

            }
        }.start ();
        timerRunningTeamB=true;
        buttonStartTeamB.setText("New Penalty");
    }

    private void newStartTimerTeamA() {
        countDownTimerTeamA.cancel();
        timeLeftInMillisTeamA=START_TIME_IN_MILLIS_TeamA;
        startTimerTeamA ();
    }

    private void newStartTimerTeamB() {
        countDownTimerTeamB.cancel();
        timeLeftInMillisTeamB=START_TIME_IN_MILLIS_TeamB;
        startTimerTeamB ();
    }

    private void reserTimer() {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        timerRunningTeamA=false;
        countDownTimerTeamA.cancel();
        textViewCountDownTeamA.setText("00:00");
        timeLeftInMillisTeamA=START_TIME_IN_MILLIS_TeamA;
        buttonStartTeamA.setText("Penalty");
        timerRunningTeamB=false;
        countDownTimerTeamB.cancel();
        textViewCountDownTeamB.setText("00:00");
        timeLeftInMillisTeamB=START_TIME_IN_MILLIS_TeamB;
        buttonStartTeamB.setText("Penalty");
    }

    private void updateCountDownTextTeamA(){
        int minutes=(int) (timeLeftInMillisTeamA / 1000)/60;
        int seconds=(int) (timeLeftInMillisTeamA / 1000) % 60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewCountDownTeamA.setText(timeLeftFormatted);

    }
    private void updateCountDownTextTeamB(){
        int minutes=(int) (timeLeftInMillisTeamB / 1000)/60;
        int seconds=(int) (timeLeftInMillisTeamB / 1000) % 60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewCountDownTeamB.setText(timeLeftFormatted);

    }

    public void goalForTeamA (View view) {
        scoreTeamA=scoreTeamA+1;
        displayForTeamA(scoreTeamA);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamA_score);
        scoreView.setText(String.valueOf(score));
    }


    public void goalForTeamB (View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamB_score);
        scoreView.setText(String.valueOf(score));
    }
}
