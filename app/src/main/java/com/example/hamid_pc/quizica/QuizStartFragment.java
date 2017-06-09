package com.example.hamid_pc.quizica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hamid-PC on 6/6/2017.
 */

public class QuizStartFragment extends Fragment {


    private TextView mQuizTitle;
    private TextView mQuizNumber;
    private TextView mQuizTotalMarks;
    private Button mViewResult;
    private Button mStartQuiz;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mQuizReference;
    private Quiz mQuiz;
    private String mTotalMarksString;
    private String mQuizNumberString;

    private String mCourseName;
    private String mQuizUuid;

    public static QuizStartFragment newInstance(String CourseName, String QuizUuid) {
        QuizStartFragment frag = new QuizStartFragment();
        Bundle args = new Bundle();
        args.putString("quizuuid", QuizUuid);
        args.putString("coursename", CourseName);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCourseName = getArguments().getString("coursename");
        mQuizUuid = getArguments().getString("quizuuid");
        mDatabaseReference = mFirebaseDatabase.getReference(mCourseName + "/quizzes");
        mQuizReference = mFirebaseDatabase.getReference(mQuizUuid);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz_start, container, false);
        mQuizTitle = (TextView) view.findViewById(R.id.quiz_title);
        mQuizNumber = (TextView) view.findViewById(R.id.quiz_number);
        mQuizTotalMarks = (TextView) view.findViewById(R.id.quiz_total_marks);
        mStartQuiz = (Button) view.findViewById(R.id.btn_start);
        mViewResult = (Button) view.findViewById(R.id.btn_result);
        mTotalMarksString = getResources().getString(R.string.quiz_total_marks);
        mQuizNumberString = getResources().getString(R.string.quiz_number);

        SwitchLayout();

        mDatabaseReference.orderByChild("mQuizUuid").equalTo(mQuizUuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mQuiz = dataSnapshot.getValue(Quiz.class);


                mQuizTitle.setText(mQuiz.getmTitle());
                mQuizNumber.setText(mQuizNumberString + mQuiz.getmQuizNumber());
                mQuizTotalMarks.setText(mTotalMarksString + mQuiz.getmQuizTotalMarks());

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


        mViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = QuizResultActivity.NewIntent(getActivity(), mQuizUuid);
                startActivity(intent);

            }
        });

        mStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuiz.ismStarted()) {
                Intent StartQuizIntent = QuestionsPagerActivity.NewIntent(getActivity(), mQuizUuid);
                    startActivity(StartQuizIntent);
                } else {
                    Toast.makeText(getActivity(), "Quiz not started yet by Instructor", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SwitchLayout();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewResult.setVisibility(View.INVISIBLE);
        mStartQuiz.setVisibility(View.INVISIBLE);
    }

    public void SwitchLayout() {
        mQuizReference.orderByChild("uuid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mQuizReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mViewResult.setVisibility(View.VISIBLE);
                } else {
                    mStartQuiz.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
