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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 4/10/2017.
 */

public class TextQuizFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText editTextAnswer;
    private TextView textViewQuestion;
    private Button mSubmitButton;
    private Answer mAnswer;


    private String mQuestionText;
    private String mAnswerText;

    public TextQuizFragment() {
        super();
    }

    public static TextQuizFragment newInstance(String question) {
        TextQuizFragment textQuizFragment = new TextQuizFragment();
        Bundle args = new Bundle();
        args.putString("question", question);
        textQuizFragment.setArguments(args);
        return textQuizFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("answers");
        mQuestionText = getArguments().getString("question");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_text, container, false);
        textViewQuestion = (TextView) v.findViewById(R.id.textView);
        textViewQuestion.setText(mQuestionText);
        editTextAnswer = (EditText) v.findViewById(R.id.editText);
        //textViewQuestion.setText();
        mSubmitButton = (Button) v.findViewById(R.id.button_submit);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionsPagerActivity questionsPagerActivity = (QuestionsPagerActivity) getActivity();
                mAnswerText = editTextAnswer.getText().toString();
                mAnswer = new Answer(mAnswerText);

                mDatabaseReference.push().setValue(mAnswer);
                questionsPagerActivity.replaceFragment();
            }
        });
        return v;
    }
}
