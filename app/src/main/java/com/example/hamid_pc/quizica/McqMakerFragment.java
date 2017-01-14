package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Hamid-PC on 1/11/2017.
 */

public class McqMakerFragment extends Fragment {

    Animation animShake;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText editTextQuestion;
    private EditText edittTextOptionOne;
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
    private Button publishButton;

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
        edittTextOptionOne = (EditText) v.findViewById(R.id.view_edittext_optionOne);
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


        animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);


        publishButton = (Button) v.findViewById(R.id.button_publish);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Question question = new Question(editTextQuestion.getText().toString(), edittTextOptionOne.getText().toString(), editTextOptionTwo.getText().toString(), editTextOptionThree.getText().toString(), editTextOptionFour.getText().toString(), editTextAnswer.getText().toString());
                //Question question = new Question(editTextQuestion.getText().toString());
                //mDatabaseReference.push().setValue(question);
                submitForm();
            }
        });

        return v;
    }


    public void submitForm() {
        // Toast.makeText(getContext(),"Successful",Toast.LENGTH_LONG).show();
       
    }


}
