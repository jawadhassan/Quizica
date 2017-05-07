package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class QuizResultActivity extends SingleFragmentActivity {


    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, QuizResultActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new QuizResultFragment();
    }
}
