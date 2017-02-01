package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 1/30/2017.
 */

public class AuthenticationActivity extends SingleFragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new AuthenticationFragment();
    }
}
