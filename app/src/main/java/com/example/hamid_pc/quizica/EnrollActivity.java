package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 1/27/2017.
 */

public class EnrollActivity extends SingleFragmentActivity {

    private static String mCourseName;

    public static Intent newIntent(Context packageContext, String CourseName) {
        Intent i = new Intent(packageContext, EnrollActivity.class);
        mCourseName = CourseName;
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment createFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("coursename", mCourseName);
        EnrollFragment enrollFragment = new EnrollFragment();
        enrollFragment.setArguments(bundle);
        return enrollFragment;
    }
}
