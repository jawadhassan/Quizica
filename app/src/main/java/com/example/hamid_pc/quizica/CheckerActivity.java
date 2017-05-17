package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Hamid-PC on 4/10/2017.
 */

public class CheckerActivity extends AppCompatActivity {

    private static String mQuizName;
    private static int mQuizNumber;
    FragmentManager fragmentManager;
    Fragment quizFragment;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ArrayList<Answer> mAnswersList;
    private Answer mAnswer;
    private int mTotalObtainedMarks = 0;

    public static Intent newIntent(Context packageContext, int QuizNumber, String QuizName) {
        Intent i = new Intent(packageContext, CheckerActivity.class);
        mQuizName = QuizName;
        mQuizNumber = QuizNumber;
        return i;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("answers");

        fragmentManager = getSupportFragmentManager();
        quizFragment = fragmentManager.findFragmentById(R.id.fragment_container);


        mAnswersList = new ArrayList<>();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mAnswer = snapshot.getValue(Answer.class);
                    mAnswersList.add(mAnswer);
                }
                if (quizFragment == null) {
                    if (!mAnswersList.isEmpty()) {
                        quizFragment = createFragment();
                        fragmentManager.beginTransaction()
                                .add(R.id.fragment_container, quizFragment)
                                .commit();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public Fragment createFragment() {
        return CheckerFragment.newInstance(mAnswersList.get(0).getQuestionUuid(), mAnswersList.get(0).getAnswerText());

    }


    public void replaceFragment(int Totalmarks) {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (!mAnswersList.isEmpty()) {
            mTotalObtainedMarks = mTotalObtainedMarks + 1;

            fragmentTransaction.replace(R.id.fragment_container, CheckerFragment.newInstance(mAnswersList.get(0).getQuestionUuid(), mAnswersList.get(0).getAnswerText()));
            fragmentTransaction.commit();
            mAnswersList.remove(0);
        } else {

            mDatabaseReference = mFirebaseDatabase.getReference("results");
            StudentResult studentResult = new StudentResult(mQuizNumber, mQuizName, 20, mTotalObtainedMarks);
            mDatabaseReference.push().setValue(studentResult);
            finish();

        }

        }


}
