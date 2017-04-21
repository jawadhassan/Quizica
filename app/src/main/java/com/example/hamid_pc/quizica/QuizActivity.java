package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class QuizActivity extends SingleFragmentActivity {


    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, QuizActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {


        return new MCQQuizFragment();
    }
}
