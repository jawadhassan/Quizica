package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Hamid-PC on 4/21/2017.
 */

public class QuestionsPagerActivity extends SingleFragmentActivity {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Question question;
    private ArrayList<Question> mQuestionsList;


    public static Intent NewIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, QuestionsPagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("questionspageracitivity", "okay");


    }


    @Override
    protected Fragment createFragment() {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("questions");

        mQuestionsList = new ArrayList<>();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    question = snapshot.getValue(Question.class);
                    mQuestionsList.add(question);


                    Log.d("quizacitivty", "" + mQuestionsList.get(mQuestionsList.size() - 1).getQuesiton());

                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if ((mQuestionsList.size() - 1) > 1 && mQuestionsList.get(0).getOptionOne() == null) {
            return new TextQuizFragment();
        } else {

            return new MCQQuizFragment();
        }


    }

    public void replaceFragment() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if ((mQuestionsList.size() - 1) > 1 && mQuestionsList.get(0).getOptionOne() == null) {

            fragmentTransaction.replace(R.id.fragment_container, new MCQQuizFragment());
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.replace(R.id.fragment_container, new TextQuizFragment());
            fragmentTransaction.commit();
        }
    }

}

