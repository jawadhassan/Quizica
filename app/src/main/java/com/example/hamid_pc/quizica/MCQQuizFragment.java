package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MCQQuizFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private TextView mQuestionTextView;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private Button mSubmitButton;

    private String mQuesionText;
    private String mOptionOneText;
    private String mOptionTwoText;
    private String mOptionThreeText;
    private String mOptionFourText;

    public MCQQuizFragment() {
        super();
        // Required empty public constructor
    }

    public static MCQQuizFragment newInstance(String question, String OptionOne, String OptionTwo, String OptionThree, String OptionFour) {
        MCQQuizFragment mcqQuizFragment = new MCQQuizFragment();
        Bundle args = new Bundle();
        args.putString("question", question);
        args.putString("optionOne", OptionOne);
        args.putString("optionTwo", OptionTwo);
        args.putString("optionThree", OptionThree);
        args.putString("optionFour", OptionFour);
        mcqQuizFragment.setArguments(args);
        return mcqQuizFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("questions");
        mQuesionText = getArguments().getString("quesion");
        mOptionOneText = getArguments().getString("optionOne");
        mOptionTwoText = getArguments().getString("optionTwo");
        mOptionThreeText = getArguments().getString("optionThree");
        mOptionFourText = getArguments().getString("optionFour");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_mcq, container, false);
        mQuestionTextView = (TextView) v.findViewById(R.id.question_text_view);

        mQuestionTextView.setText(mQuesionText);

        mRadioButton1 = (RadioButton) v.findViewById(R.id.radioButton1);
        mRadioButton1.setText(mOptionOneText);

        mRadioButton2 = (RadioButton) v.findViewById(R.id.radioButton2);
        mRadioButton2.setText(mOptionTwoText);

        mRadioButton3 = (RadioButton) v.findViewById(R.id.radioButton3);
        mRadioButton3.setText(mOptionThreeText);

        mRadioButton4 = (RadioButton) v.findViewById(R.id.radioButton4);
        mRadioButton4.setText(mOptionFourText);

        mSubmitButton = (Button) v.findViewById(R.id.next_question_button);


        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Question question = dataSnapshot.getValue(Question.class)
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


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionsPagerActivity questionsPagerActivity = (QuestionsPagerActivity) getActivity();
                questionsPagerActivity.replaceFragment();
            }
        });

        return v;
    }


}
