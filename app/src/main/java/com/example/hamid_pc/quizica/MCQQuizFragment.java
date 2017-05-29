package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;

    private Answer mAnswer;


    private String mQuesionText;
    private String mOptionOneText;
    private String mOptionTwoText;
    private String mOptionThreeText;
    private String mOptionFourText;
    private String mQuizUuid;
    private String mQuestionUuid;

    private String mAnswerText;

    public MCQQuizFragment() {
        super();
        // Required empty public constructor
    }

    public static MCQQuizFragment newInstance(String QuizUuid, String QuestionUuid, String question, String OptionOne, String OptionTwo, String OptionThree, String OptionFour) {
        MCQQuizFragment mcqQuizFragment = new MCQQuizFragment();
        Bundle args = new Bundle();
        args.putString("question", question);
        args.putString("optionOne", OptionOne);
        args.putString("optionTwo", OptionTwo);
        args.putString("optionThree", OptionThree);
        args.putString("optionFour", OptionFour);
        args.putString("quizuuid", QuizUuid);
        args.putString("questionuuid", QuestionUuid);
        mcqQuizFragment.setArguments(args);
        return mcqQuizFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mQuesionText = getArguments().getString("question");
        mOptionOneText = getArguments().getString("optionOne");
        mOptionTwoText = getArguments().getString("optionTwo");
        mOptionThreeText = getArguments().getString("optionThree");
        mOptionFourText = getArguments().getString("optionFour");
        mQuizUuid = getArguments().getString("quizuuid");
        mQuestionUuid = getArguments().getString("questionuuid");
        mDatabaseReference = mFirebaseDatabase.getReference().child("answers/" + mQuizUuid + "/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
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

        mRadioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);


        mSubmitButton = (Button) v.findViewById(R.id.next_question_button);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int SelectedValue = mRadioGroup.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) v.getRootView().findViewById(SelectedValue);
                mAnswerText = mRadioButton.getText().toString();


                Log.d("MCQFragment", "" + mAnswerText);

                mAnswerText = mRadioButton.getText().toString();
                mAnswer = new Answer(mQuestionUuid, mAnswerText);
                mDatabaseReference.push().setValue(mAnswer);
                QuestionsPagerActivity questionsPagerActivity = (QuestionsPagerActivity) getActivity();
                questionsPagerActivity.replaceFragment();
            }
        });

        return v;
    }


}
