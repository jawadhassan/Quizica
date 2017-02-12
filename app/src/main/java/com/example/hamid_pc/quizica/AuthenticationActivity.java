package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Hamid-PC on 1/30/2017.
 */

public class AuthenticationActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;
    private boolean signed_in;
    private FirebaseAuth mFireAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.pref_file_key), Context.MODE_PRIVATE);

        signed_in = sharedPref.getBoolean(getString(R.string.pref_sign_key), false);
        mFireAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null && signed_in == false) {
                    Intent intent = ProfileDataActivity.newIntent(AuthenticationActivity.this);
                    startActivity(intent);

                } else if (user != null && signed_in == true) {
                    Toast.makeText(AuthenticationActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = CourseListActivity.newIntent(AuthenticationActivity.this);
                    startActivity(intent);
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(AuthUI.GOOGLE_PROVIDER,
                                            AuthUI.EMAIL_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mFireAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAuthListener != null) {
            mFireAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
