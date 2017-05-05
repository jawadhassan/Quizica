package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

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


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("answers");
    }

    @Override
    protected Fragment createFragment() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    mAnswer = dataSnapshot1.getValue(Answer.class);
                    mAnswersList.add(mAnswer);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        for (Answer answer : mAnswersList) {
            return CheckerFragment.newInstance(answer.getQuestionUuid(), answer.getAnswerText());
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
