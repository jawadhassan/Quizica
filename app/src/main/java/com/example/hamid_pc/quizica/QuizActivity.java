package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class QuizActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment quizFragment = fragmentManager.findFragmentById(R.id.quiz_container);

        if (quizFragment == null) {
            quizFragment = new QuizFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.quiz_container, quizFragment)
                    .commit();
        }
    }
}
