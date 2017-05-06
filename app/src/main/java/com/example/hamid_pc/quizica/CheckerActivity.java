package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Hamid-PC on 4/10/2017.
 */

public class CheckerActivity extends SingleFragmentActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ArrayList<Answer> mAnswersList;
    private Answer mAnswer;

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, CheckerActivity.class);
        return i;
    }


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected Fragment createFragment() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("answers");

        mAnswersList = new ArrayList<>();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mAnswer = snapshot.getValue(Answer.class);
                    mAnswersList.add(mAnswer);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return CheckerFragment.newInstance("Okay", "Right");
    }


    public void replaceFragment() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if ((mAnswersList.size()) > 0) {


            fragmentTransaction.replace(R.id.fragment_container, CheckerFragment.newInstance(mAnswersList.get(0).getQuestionUuid(), mAnswersList.get(0).getAnswerText()));
            fragmentTransaction.commit();
            mAnswersList.remove(0);
        }

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
