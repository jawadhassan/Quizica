package com.example.hamid_pc.quizica;

import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 1/21/2017.
 */

public class QuizListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new QuizListFragment();
    }
}
