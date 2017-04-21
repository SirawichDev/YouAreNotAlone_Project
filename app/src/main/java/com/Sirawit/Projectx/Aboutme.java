package com.Sirawit.Projectx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;

public class Aboutme extends AppCompatActivity  {


    private IconRoundCornerProgressBar progressOne;
    private RoundCornerProgressBar progressTwo;
    private TextRoundCornerProgressBar progressThree;
    private TextView tvProgressOne;
    private TextView tvProgressTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);


        updateSecondaryProgressOne();



    }

    private void increaseProgressOne() {
        progressOne.setProgress(progressOne.getProgress() + 1);
        updateSecondaryProgressOne();
        updateTextProgressOne();
    }

    private void decreaseProgressOne() {
        progressOne.setProgress(progressOne.getProgress() - 1);
        updateSecondaryProgressOne();
        updateTextProgressOne();
    }

    private void increaseProgressTwo() {
        progressTwo.setProgress(progressTwo.getProgress() + 1);
        updateProgressTwoColor();
        updateTextProgressTwo();
    }

    private void decreaseProgressTwo() {
        progressTwo.setProgress(progressTwo.getProgress() - 1);
        updateProgressTwoColor();
        updateTextProgressTwo();
    }

    private void increaseProgressThree() {
        progressThree.setProgress(progressThree.getProgress() + 1);

    }

    private void decreaseProgressThree() {
        progressThree.setProgress(progressThree.getProgress() - 1);

    }

    private void updateSecondaryProgressOne() {
        progressOne.setSecondaryProgress(progressOne.getProgress() + 2);
    }

    private void updateProgressTwoColor() {
        float progress = progressTwo.getProgress();
        if(progress <= 3) {

        }
        }


    private void updateTextProgressOne() {
        tvProgressOne.setText(String.valueOf((int) progressOne.getProgress()));
    }

    private void updateTextProgressTwo() {
        tvProgressTwo.setText(String.valueOf((int) progressTwo.getProgress()));
    }
}

