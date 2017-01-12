package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 1/11/2017.
 */

public class McqMakerActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, McqMakerActivity.class);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return new McqMakerFragment();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
