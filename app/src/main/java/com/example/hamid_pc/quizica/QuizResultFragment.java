package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class QuizResultFragment extends Fragment {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private TextView mMarksObtained;
    private TextView mTotalMarks;
    private TextView mTitle;
    private TextView mMessage;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("results");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        mTitle = (TextView) view.findViewById(R.id.result_title);
        mMessage = (TextView) view.findViewById(R.id.message);
        updateUI();
        mMarksObtained = (TextView) view.findViewById(R.id.marks_obtained);
        mTotalMarks = (TextView) view.findViewById(R.id.total_marks);
        return view;
    }

    public void updateUI() {

        Log.d("check", "ok");

        mDatabaseReference.orderByChild("mQuizName").equalTo("DiodeQuiz").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                StudentResult studentResult = dataSnapshot.getValue(StudentResult.class);
                Log.d("check", "" + studentResult.getmQuizName());
                mTotalMarks.setText("" + studentResult.getmTotalMarks());
                mMarksObtained.setText("" + studentResult.getmTotalObtainedMarks());


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


    }

}
