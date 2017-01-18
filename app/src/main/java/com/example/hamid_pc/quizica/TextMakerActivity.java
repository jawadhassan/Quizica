package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TextMakerActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, TextMakerActivity.class);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return new TextMakerFragment();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
