package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class QuizCreateActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, QuizCreateActivity.class);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return new QuestionTypeFragment();
    }
}
