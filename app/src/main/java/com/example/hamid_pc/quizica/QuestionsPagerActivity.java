package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

public class QuestionsPagerActivity extends AppCompatActivity {


    FragmentManager fragmentManager;
    Fragment quizFragment;
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
        mQuestionsList = new ArrayList<>();
        setContentView(R.layout.activity_fragment);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("questions");

        fragmentManager = getSupportFragmentManager();
        quizFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    question = snapshot.getValue(Question.class);
                    mQuestionsList.add(question);
                    Log.d("quizacitivty", "" + mQuestionsList.get(mQuestionsList.size() - 1).getQuestion());
                    if (quizFragment == null) {
                        if (!mQuestionsList.isEmpty()) {
                            quizFragment = createFragment();
                            fragmentManager.beginTransaction()
                                    .add(R.id.fragment_container, quizFragment)
                                    .commit();
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public Fragment createFragment() {
        if ((mQuestionsList.size()) > 0) {
            if (mQuestionsList.get(0).getOptionOne() == null) {
                return TextQuizFragment.newInstance(mQuestionsList.get(0).getQuestion());
            } else {

                return MCQQuizFragment.newInstance(mQuestionsList.get(0).getQuestion(), mQuestionsList.get(0).getOptionOne(), mQuestionsList.get(0).getOptionTwo(), mQuestionsList.get(0).getOptionThree(), mQuestionsList.get(0).getOptionFour());
            }

        } else {
            return TextQuizFragment.newInstance("Skip");
        }
    }

    public void replaceFragment() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if ((mQuestionsList.size()) > 0) {

            if (mQuestionsList.get(0).getOptionOne() == null) {

                fragmentTransaction.replace(R.id.fragment_container, TextQuizFragment.newInstance(mQuestionsList.get(0).getQuestion()));
            fragmentTransaction.commit();
            } else {
                fragmentTransaction.replace(R.id.fragment_container, MCQQuizFragment.newInstance(mQuestionsList.get(0).getQuestion(), mQuestionsList.get(0).getOptionOne(), mQuestionsList.get(0).getOptionTwo(), mQuestionsList.get(0).getOptionThree(), mQuestionsList.get(0).getOptionFour()));
                fragmentTransaction.commit();
            }
            mQuestionsList.remove(mQuestionsList.get(0));
        }
    }

}

