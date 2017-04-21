package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

public class QuestionsPagerActivity extends FragmentActivity {


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
        setContentView(R.layout.activity_question_pager);
        Log.d("questionspageracitivity", "okay");


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("questions");

        mQuestionsList = new ArrayList<Question>();
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

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


    }

    private class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return mQuestionsList.size();
        }

        @Override
        public Fragment getItem(int position) {
            Question question = mQuestionsList.get(position);
            if (question.getOptionOne() != null) {
                return new MCQQuizFragment();
            } else {
                return new TextQuizFragment();
            }

        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            getCount();
        }
    }
}

