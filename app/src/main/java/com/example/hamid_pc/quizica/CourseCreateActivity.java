package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 2/9/2017.
 */

public class CourseCreateActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, CourseCreateActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new CourseCreateFragment();
    }
}
