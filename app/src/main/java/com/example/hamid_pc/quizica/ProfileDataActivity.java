package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Hamid-PC on 2/2/2017.
 */

public class ProfileDataActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, ProfileDataActivity.class);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return new ProfileDataFragment();
    }
}
