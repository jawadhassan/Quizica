package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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


    private static String mQuizUuid;
    FragmentManager fragmentManager;
    Fragment quizFragment;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Question question;
    private ArrayList<Question> mQuestionsList;
    private DatabaseReference mQuizReference;
    private DatabaseReference mStudentReference;
    private Student mStudent;

    private String mStudentUuid;
    private String mQuestionUuid;
    private String mQuestionText;
    private String mQuestionOptionOne;
    private String mQuestionOptionTwo;
    private String mQuestionOptionThree;
    private String mQuestionOptionFour;

    public static Intent NewIntent(Context packageContext, String QuizUuid) {
        Intent intent = new Intent(packageContext, QuestionsPagerActivity.class);
        mQuizUuid = QuizUuid;
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionsList = new ArrayList<>();
        setContentView(R.layout.activity_fragment);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("questions/" + mQuizUuid);

        mQuizReference = mFirebaseDatabase.getReference(mQuizUuid);

        mStudentReference = mFirebaseDatabase.getReference("student");
        mStudentUuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mStudentReference.orderByChild("uuid").equalTo(mStudentUuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mStudent = dataSnapshot.getValue(Student.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fragmentManager = getSupportFragmentManager();
        quizFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    question = snapshot.getValue(Question.class);
                    mQuestionsList.add(question);
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
        mQuizReference.push().setValue(mStudent);
        if ((mQuestionsList.size()) > 0) {

            if (mQuestionsList.get(0).getOptionOne() == null) {
                mQuestionText = mQuestionsList.get(0).getQuestion();
                mQuestionUuid = mQuestionsList.get(0).getQuestionUuid();
                mQuestionsList.remove(0);
                return TextQuizFragment.newInstance(mQuizUuid, mQuestionUuid, mQuestionText);
            } else {
                mQuestionText = mQuestionsList.get(0).getQuestion();
                mQuestionUuid = mQuestionsList.get(0).getQuestionUuid();

                mQuestionText = mQuestionsList.get(0).getQuestion();
                mQuestionOptionOne = mQuestionsList.get(0).getOptionOne();
                mQuestionOptionThree = mQuestionsList.get(0).getOptionThree();
                mQuestionOptionTwo = mQuestionsList.get(0).getOptionTwo();
                mQuestionOptionFour = mQuestionsList.get(0).getOptionFour();
                mQuestionsList.remove(0);
                return MCQQuizFragment.newInstance(mQuizUuid, mQuestionUuid, mQuestionText, mQuestionOptionOne, mQuestionOptionTwo, mQuestionOptionThree, mQuestionOptionFour);
            }

        } else {
            return TextQuizFragment.newInstance(mQuizUuid, mQuestionUuid, "Skip");
        }
    }

    public void replaceFragment() {


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if ((mQuestionsList.size()) > 0) {

            if (mQuestionsList.get(0).getOptionOne() == null) {
                mQuestionText = mQuestionsList.get(0).getQuestion();
                mQuestionUuid = mQuestionsList.get(0).getQuestionUuid();

                fragmentTransaction.replace(R.id.fragment_container, TextQuizFragment.newInstance(mQuizUuid, mQuestionUuid, mQuestionsList.get(0).getQuestion()));
            fragmentTransaction.commit();
            } else {
                mQuestionText = mQuestionsList.get(0).getQuestion();
                mQuestionUuid = mQuestionsList.get(0).getQuestionUuid();
                fragmentTransaction.replace(R.id.fragment_container, MCQQuizFragment.newInstance(mQuizUuid, mQuestionUuid, mQuestionsList.get(0).getQuestion(), mQuestionsList.get(0).getOptionOne(), mQuestionsList.get(0).getOptionTwo(), mQuestionsList.get(0).getOptionThree(), mQuestionsList.get(0).getOptionFour()));
                fragmentTransaction.commit();
            }
            mQuestionsList.remove(mQuestionsList.get(0));
        } else {
            finish();
        }
    }

}

