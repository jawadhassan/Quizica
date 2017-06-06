package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 6/6/2017.
 */

public class QuizStartActivity extends SingleFragmentActivity {

    private static String mQuizUuid;
    private static String mCourseName;

    public static Intent NewIntent(Context packageContext, String CourseName, String QuizUuid) {
        Intent intent = new Intent(packageContext, QuizStartActivity.class);
        mQuizUuid = QuizUuid;
        mCourseName = CourseName;
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return QuizStartFragment.newInstance(mCourseName, mQuizUuid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
