package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 3/21/2017.
 */

public class QuizCreateFragment extends Fragment {

    private EditText QuizTitleText;
    private EditText QuizIdText;
    private Button submitButton;

    private String mQuizName;
    private int mQuizNumber;
    private String mCourseName;
    private Quiz mQuiz;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_create, container, false);
        QuizTitleText = (EditText) view.findViewById(R.id.view_edittext_title);
        QuizIdText = (EditText) view.findViewById(R.id.view_edittext_number);
        submitButton = (Button) view.findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuizName = QuizTitleText.getText().toString();
                mQuizNumber = Integer.parseInt(QuizIdText.getText().toString());
                mQuiz = new Quiz(mQuizName, mQuizNumber);
                mDatabaseReference.push().setValue(mQuiz);

            }
        });

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourseName = getArguments().getString("coursename");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(mCourseName + "/quizzes");


    }
}
