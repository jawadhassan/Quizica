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
    private static String mQuizUuid;
    private static String mStudentUuid;
    private static int mQuizTotalMarks;
    FragmentManager fragmentManager;
    Fragment quizFragment;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    private ArrayList<Answer> mAnswersList;
    private Answer mAnswer;
    private int mTotalObtainedMarks = 0;
    private String mQuestionUuid;

    private float mMarksPerQuestion;
    private String mAnswerText;

    public static Intent newIntent(Context packageContext, String QuizUuid, int QuizTotalMarks, String StudentUuid) {
        Intent i = new Intent(packageContext, CheckerActivity.class);
        mQuizUuid = QuizUuid;
        mQuizTotalMarks = QuizTotalMarks;
        mStudentUuid = StudentUuid;
        return i;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("answers/" + mQuizUuid + "/" + mStudentUuid);


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

        mMarksPerQuestion = (float) mQuizTotalMarks / mAnswersList.size();
        mQuestionUuid = mAnswersList.get(0).getQuestionUuid();
        mAnswerText = mAnswersList.get(0).getAnswerText();
        mAnswersList.remove(0);
        return CheckerFragment.newInstance(mQuizUuid, mQuestionUuid, mMarksPerQuestion, mAnswerText);


    }


    public void replaceFragment(int Totalmarks) {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mTotalObtainedMarks = mTotalObtainedMarks + Totalmarks;

        if (!mAnswersList.isEmpty()) {
            mQuestionUuid = mAnswersList.get(0).getQuestionUuid();
            mAnswerText = mAnswersList.get(0).getAnswerText();


            fragmentTransaction.replace(R.id.fragment_container, CheckerFragment.newInstance(mQuizUuid, mQuestionUuid, mMarksPerQuestion, mAnswerText));
            fragmentTransaction.commit();
            mAnswersList.remove(0);
        } else {

            mDatabaseReference = mFirebaseDatabase.getReference("results/" + mStudentUuid);


            StudentResult studentResult = new StudentResult(mQuizUuid, mQuizName, mQuizTotalMarks, mTotalObtainedMarks);
            mDatabaseReference.push().setValue(studentResult);
            finish();

        }

        }


}
