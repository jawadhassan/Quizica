package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

    private final String QUESTION_FRAG = "QuestionCreateFragment";
    private EditText QuizTitleText;
    private EditText QuizNumberText;
    private Button submitButton;
    private String mQuizName;
    private int mQuizNumber;
    private int mQuizTotalMarks;
    private EditText QuizTotalMarks;
    private String mCourseName;
    private Quiz mQuiz;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_create, container, false);
        QuizTitleText = (EditText) view.findViewById(R.id.view_edittext_title);
        QuizNumberText = (EditText) view.findViewById(R.id.view_edittext_number);
        QuizTotalMarks = (EditText) view.findViewById(R.id.view_edittext_totalmarks_numbers);

        submitButton = (Button) view.findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mQuizName = QuizTitleText.getText().toString();
                if (mQuizName.length() == 0) {
                    QuizTitleText.setError("Enter Quiz Title");
                }


                try {
                    mQuizNumber = Integer.parseInt(QuizNumberText.getText().toString());


                } catch (Exception ex) {
                    ex.printStackTrace();
                    QuizNumberText.setError("Enter Number");

                }

                try {
                    mQuizTotalMarks = Integer.parseInt(QuizTotalMarks.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    QuizTotalMarks.setError("Enter Number");
                }

                if (!(mQuizName.length() == 0) && !(mQuizNumber == 0) && !(mQuizTotalMarks == 0)) {
                    mQuiz = new Quiz(mQuizName, mQuizNumber, mQuizTotalMarks);
                    mDatabaseReference.push().setValue(mQuiz);

                    // Replacing QuizCreateFragment with QuestionCreateFragment
                    final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, QuestionTypeFragment.newInstance(mQuiz.getmQuizUuid()), QUESTION_FRAG);
                    ft.commit();
                }



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
