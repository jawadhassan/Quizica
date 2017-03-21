package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 3/21/2017.
 */

public class QuizCreateActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, QuizCreateActivity.class);
        return i;
    }
    @Override
    protected Fragment createFragment() {
        return new QuizCreateFragment();
    }
}
