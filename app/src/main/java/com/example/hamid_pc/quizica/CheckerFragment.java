package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 4/10/2017.
 */

public class CheckerFragment extends Fragment {

    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private EditText mEditTextView;

    private Button mSubmitButton;

    private String mQuestionText;
    private String mAnswerText;
    private String mQuestionUuid;


    private DatabaseReference mQuestionReference;
    private FirebaseDatabase mFirebaseDatabase;
    private Question mQuestion;


    private String mQuizUuid;
    private int mTotalMarks;
    private int mQuizNumber;
    private String mQuizName;

    public static CheckerFragment newInstance(String quizuuid, String questionuuid, String answer) {
        CheckerFragment checkerFragment = new CheckerFragment();
        Bundle args = new Bundle();
        args.putString("questionuuid", questionuuid);
        args.putString("answer", answer);
        args.putString("quizuuid", quizuuid);
        checkerFragment.setArguments(args);
        return checkerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mQuestionUuid = getArguments().getString("questionuuid");
        mQuizUuid = getArguments().getString("quizuuid");
        mAnswerText = getArguments().getString("answer");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checker, container, false);
        mQuestionTextView = (TextView) view.findViewById(R.id.question_text_view);
        mAnswerTextView = (TextView) view.findViewById(R.id.answer_text_view);
        mEditTextView = (EditText) view.findViewById(R.id.number_edit_text);
        mSubmitButton = (Button) view.findViewById(R.id.button_submit);


        mAnswerTextView.setText(mAnswerText);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckerActivity checkerActivity = (CheckerActivity) getActivity();
                mTotalMarks = Integer.parseInt(mEditTextView.getText().toString());
                checkerActivity.replaceFragment(mTotalMarks);
            }
        });

        mQuestionReference = mFirebaseDatabase.getReference().child("questions/" + mQuizUuid);
        mQuestionReference.orderByChild("questionUuid").equalTo(mQuestionUuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mQuestion = dataSnapshot.getValue(Question.class);
                mQuestionTextView.setText(mQuestion.getQuestion());
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


        return view;

    }




}
