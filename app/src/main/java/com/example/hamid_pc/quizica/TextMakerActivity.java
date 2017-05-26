package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TextMakerActivity extends SingleFragmentActivity {

    static String mQuizUuid;

    public static Intent newIntent(Context packageContext, String QuizUuid) {
        Intent i = new Intent(packageContext, TextMakerActivity.class);
        mQuizUuid = QuizUuid;
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return TextMakerFragment.newInstance(mQuizUuid);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
