package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Hamid-PC on 1/11/2017.
 */

public class McqMakerFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText editTextQuestion;
    private EditText editTextOptionOne;
    private EditText editTextOptionTwo;
    private EditText editTextOptionThree;
    private EditText editTextOptionFour;
    private EditText editTextAnswer;
    private TextInputLayout layoutQuestion;
    private TextInputLayout layoutOptionOne;
    private TextInputLayout layoutOptionTwo;
    private TextInputLayout layoutOptionThree;
    private TextInputLayout layoutOptioFour;
    private TextInputLayout layoutAnswer;
    private Button doneButton;
    private Button addMoreButton;

    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;

    private Boolean isQuestionTextValidated = false;
    private Boolean isOptionOneTextValidated = false;
    private Boolean isOptionTwoTextValidated = false;
    private Boolean isOptionThreeTextValidated = false;
    private Boolean isOptionFourTextValidated = false;
    private Boolean isAnswerValidated = false;


    private Boolean isOptionTwoValidated = false;
    private Boolean isOptionThreeValidated = false;
    private Boolean isOptionFourValidated = false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("questions");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mcq_maker, container, false);
        editTextQuestion = (EditText) v.findViewById(R.id.view_edittext_question);
        editTextOptionOne = (EditText) v.findViewById(R.id.view_edittext_optionOne);
        editTextOptionTwo = (EditText) v.findViewById(R.id.view_edittext_optionTwo);
        editTextOptionThree = (EditText) v.findViewById(R.id.view_edittext_optionThree);
        editTextOptionFour = (EditText) v.findViewById(R.id.view_edittext_optionFour);
        editTextAnswer = (EditText) v.findViewById(R.id.view_edittext_answer);


        layoutQuestion = (TextInputLayout) v.findViewById(R.id.view_edittext_layout_question);
        layoutQuestion = (TextInputLayout) v.findViewById(R.id.view_edittext_layout_optionOne);
        layoutQuestion = (TextInputLayout) v.findViewById(R.id.view_edittext_layout_optionTwo);
        layoutQuestion = (TextInputLayout) v.findViewById(R.id.view_edittext_layout_optionThree);
        layoutQuestion = (TextInputLayout) v.findViewById(R.id.view_edittext_layout_optionFour);
        layoutQuestion = (TextInputLayout) v.findViewById(R.id.view_edittext_layout_optionAnswer);


        doneButton = (Button) v.findViewById(R.id.mcqmaker_buttondone);
        doneButton.setEnabled(false);

        addMoreButton = (Button) v.findViewById(R.id.mcqmaker_buttonaddmore);
        addMoreButton.setEnabled(false);

        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Question question = new Question(editTextQuestion.getText().toString(), editTextOptionOne.getText().toString(), editTextOptionTwo.getText().toString(), editTextOptionThree.getText().toString(), editTextOptionFour.getText().toString(), editTextAnswer.getText().toString());
                //Question question = new Question(editTextQuestion.getText().toString());
                //mDatabaseReference.push().setValue(question);
                submitForm();
            }
        });


        editTextQuestion.addTextChangedListener(new TextValidator(editTextQuestion) {
            @Override
            public void validate(EditText editText, String text) {
                isQuestionTextValidated = textValidate(text);
                enableButton();

            }
        });
        editTextOptionOne.addTextChangedListener(new TextValidator(editTextOptionOne) {
            @Override
            public void validate(EditText editText, String text) {
                isOptionOneTextValidated = textValidate(text);
                enableButton();
            }
        });
        editTextOptionTwo.addTextChangedListener(new TextValidator(editTextOptionTwo) {
            @Override
            public void validate(EditText editText, String text) {
                isOptionTwoTextValidated = textValidate(text);

                optionOne = editTextOptionOne.getText().toString();
                optionThree = editTextOptionThree.getText().toString();
                optionFour = editTextOptionFour.getText().toString();
                isOptionTwoValidated = optionValidate(text, optionOne, optionThree, optionFour);
                enableButton();

            }
        });
        editTextOptionFour.addTextChangedListener(new TextValidator(editTextOptionFour) {
            @Override
            public void validate(EditText editText, String text) {
                isOptionFourTextValidated = textValidate(text);

                optionOne = editTextOptionOne.getText().toString();
                optionTwo = editTextOptionTwo.getText().toString();
                optionThree = editTextOptionThree.getText().toString();
                isOptionFourValidated = optionValidate(text, optionOne, optionTwo, optionThree);
                enableButton();
            }
        });
        editTextOptionThree.addTextChangedListener(new TextValidator(editTextOptionThree) {
            @Override
            public void validate(EditText editText, String text) {
                isOptionThreeTextValidated = textValidate(text);

                optionOne = editTextOptionOne.getText().toString();
                optionTwo = editTextOptionTwo.getText().toString();
                optionFour = editTextOptionFour.getText().toString();

                isOptionThreeValidated = optionValidate(text, optionOne, optionTwo, optionFour);
                enableButton();

            }
        });
        editTextAnswer.addTextChangedListener(new TextValidator(editTextAnswer) {


            @Override
            public void validate(EditText editText, String text) {
                optionOne = editTextOptionOne.getText().toString();
                optionTwo = editTextOptionTwo.getText().toString();
                optionThree = editTextOptionThree.getText().toString();
                optionFour = editTextOptionFour.getText().toString();

                isAnswerValidated = answerValidate(text, optionOne, optionTwo, optionThree, optionFour);
                enableButton();
            }
        });



        return v;
    }


    public void submitForm() {
        Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
        getActivity().finish();

    }


    public void enableButton() {
        if (isQuestionTextValidated && isOptionOneTextValidated && isOptionTwoTextValidated && isOptionThreeTextValidated && isOptionFourTextValidated && isAnswerValidated && isOptionTwoValidated && isOptionThreeValidated && isOptionFourValidated) {
            doneButton.setEnabled(true);
            addMoreButton.setEnabled(true);

        } else {
            doneButton.setEnabled(false);
            addMoreButton.setEnabled(false);
        }

    }
}
