package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 5/26/2017.
 */


public class StudentsAnswersListActivity extends SingleFragmentActivity {

    private static String mQuizUuid;

    public static Intent newIntent(Context packageContext, String QuizUuid) {
        Intent i = new Intent(packageContext, StudentsAnswersListActivity.class);
        mQuizUuid = QuizUuid;
        return i;
    }


    @Override
    protected Fragment createFragment() {
        return StudentAnswersListFragment.newInstance(mQuizUuid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
