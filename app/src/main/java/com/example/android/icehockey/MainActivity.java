package com.example.android.icehockey;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA=0;
    int scoreTeamB=0;

    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapterTeamA;
    private CountryAdapter mAdapterTeamB;

    private static final long START_TIME_IN_MILLIS_TeamA=120000;
    private static final long START_TIME_IN_MILLIS_TeamB=120000;
    private TextView textViewCountdownTeamA;
    private Button buttonStartTeamA;
    private TextView textViewCountdownTeamB;
    private Button buttonStartTeamB;
    private Button buttonResetTimer;

    boolean timerRunningTeamA;
    boolean timerRunningTeamB;


    private CountDownTimer countDownTimerTeamA;
    private CountDownTimer countDownTimerTeamB;
    private long timeLeftInMillisTeamA = START_TIME_IN_MILLIS_TeamA;
    private long timeLeftInMillisTeamB = START_TIME_IN_MILLIS_TeamB;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code for spinner below
        initList();

        Spinner spinnerCountriesTeamA=findViewById(R.id.spinner_counries_teamA);
        Spinner spinnerCountriesTeamB=findViewById(R.id.spinner_counries_teamB);

        mAdapterTeamA=new CountryAdapter(this,mCountryList);
        spinnerCountriesTeamA.setAdapter(mAdapterTeamA);
        mAdapterTeamB=new CountryAdapter(this,mCountryList);
        spinnerCountriesTeamB.setAdapter(mAdapterTeamB);


        //code for Score below
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);

        //Code for Timer Below
        textViewCountdownTeamA = findViewById(R.id.text_teamA_countdown);
        buttonStartTeamA = findViewById(R.id.button_start_teamA);
        textViewCountdownTeamB = findViewById(R.id.text_teamB_countdown);
        buttonStartTeamB = findViewById(R.id.button_start_teamB);
        buttonResetTimer=findViewById(R.id.button_reset_timer);


        buttonStartTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimerTeamA();
            }
        });

        buttonStartTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimerTeamB();
            }
        });

        buttonResetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                scoreTeamA = 0;
                scoreTeamB = 0;
                displayForTeamA(scoreTeamA);
                displayForTeamB(scoreTeamB);
                timerRunningTeamA=false;
                countDownTimerTeamA.cancel();
                textViewCountdownTeamA.setText("00:00");
                timerRunningTeamB=false;
                countDownTimerTeamB.cancel();
                textViewCountdownTeamB.setText("00:00");
            }
        });

    }

    private void initList(){
        mCountryList= new ArrayList<>();
        mCountryList.add(new CountryItem("CAN",R.drawable.canada));
        mCountryList.add(new CountryItem("CZE",R.drawable.czech));
        mCountryList.add(new CountryItem("FIN",R.drawable.finland));
        mCountryList.add(new CountryItem("GER",R.drawable.germany));
        mCountryList.add(new CountryItem("NOR",R.drawable.norway));
        mCountryList.add(new CountryItem("RUS",R.drawable.russia));
        mCountryList.add(new CountryItem("KOR",R.drawable.soukorea));
        mCountryList.add(new CountryItem("SVK",R.drawable.slovakia));
        mCountryList.add(new CountryItem("SLV",R.drawable.slovenia));
        mCountryList.add(new CountryItem("SWE",R.drawable.sweden));
        mCountryList.add(new CountryItem("SUI",R.drawable.switzerland));
        mCountryList.add(new CountryItem("USA",R.drawable.usa));
    }

    public void stopTimerTeamA(){

    }




    public void startTimerTeamA(){
        countDownTimerTeamA=new CountDownTimer(timeLeftInMillisTeamA,1000) {
            @Override

            public void onTick(long millisUntilFinished) {
                timeLeftInMillisTeamA=millisUntilFinished;

                updateCountDownTextTeamA();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }



    public void startTimerTeamB(){
        countDownTimerTeamB=new CountDownTimer(timeLeftInMillisTeamB,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisTeamB=millisUntilFinished;
                updateCountDownTextTeamB();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void stopTimer(){

    }

    public void updateCountDownTextTeamA(){
        int minutes=(int) (timeLeftInMillisTeamA / 1000)/60;
        int seconds=(int) (timeLeftInMillisTeamA / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountdownTeamA.setText(timeLeftFormatted);

    }

    public void updateCountDownTextTeamB() {
        int minutes=(int) (timeLeftInMillisTeamB / 1000)/60;
        int seconds=(int) (timeLeftInMillisTeamB / 1000) % 60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        textViewCountdownTeamB.setText(timeLeftFormatted);
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
