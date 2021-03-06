package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class QuizResultActivity extends SingleFragmentActivity {

    private static String mQuizUuid;

    public static Intent NewIntent(Context packageContext, String QuizUuid) {
        Intent intent = new Intent(packageContext, QuizResultActivity.class);
        mQuizUuid = QuizUuid;
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return QuizResultFragment.newInstance(mQuizUuid);
    }
}
