package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 5/1/2017.
 */

public class QuizListStudentActivity extends SingleFragmentActivity {
    private static String mCourseName;

    public static Intent newIntent(Context packageContext, String CourseName) {
        Intent i = new Intent(packageContext, QuizListStudentActivity.class);
        mCourseName = CourseName;
        return i;
    }

    @Override
    protected Fragment createFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("coursename", mCourseName);
        QuizListStudentFragment quizListStudentFragment = new QuizListStudentFragment();
        quizListStudentFragment.setArguments(bundle);
        return quizListStudentFragment;
    }
}
