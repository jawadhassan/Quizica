package com.example.hamid_pc.quizica;

import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 3/21/2017.
 */

public class QuizCreateActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new QuizCreateFragment();
    }
}
