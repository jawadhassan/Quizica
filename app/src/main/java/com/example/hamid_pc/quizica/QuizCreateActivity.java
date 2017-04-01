package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 3/21/2017.
 */

public class QuizCreateActivity extends SingleFragmentActivity {

    public static String mCourseName;

    public static Intent newIntent(Context packageContext, String CourseName) {
        Intent i = new Intent(packageContext, QuizCreateActivity.class);
        mCourseName = CourseName;
        return i;
    }
    @Override
    protected Fragment createFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("coursename", mCourseName);
        QuizCreateFragment quizCreateFragment = new QuizCreateFragment();
        quizCreateFragment.setArguments(bundle);
        return quizCreateFragment;
    }


}
