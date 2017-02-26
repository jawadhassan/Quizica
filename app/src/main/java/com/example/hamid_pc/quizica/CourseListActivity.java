package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;



/**
 * Created by Hamid-PC on 1/26/2017.
 */


public class CourseListActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, CourseListActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new CourseListFragment();
    }


}
