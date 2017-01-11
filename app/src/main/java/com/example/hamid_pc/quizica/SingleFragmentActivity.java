package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hamid-PC on 1/11/2017.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment quizFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (quizFragment == null) {
            quizFragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, quizFragment)
                    .commit();
        }
    }
}
