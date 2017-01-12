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
 * Created by Hamid-PC on 1/11/2017.
 */

public class McqMakerFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText editText;
    private EditText edittTextOne;
    private EditText editTextTwo;
    private EditText editTextThree;
    private EditText editTextFour;
    private EditText editTextAnswer;
    private Button button;

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
        editText = (EditText) v.findViewById(R.id.view_edittext_question);
        edittTextOne = (EditText) v.findViewById(R.id.view_edittext_optionOne);
        editTextTwo = (EditText) v.findViewById(R.id.view_edittext_optionTwo);
        editTextThree = (EditText) v.findViewById(R.id.view_edittext_optionThree);
        editTextFour = (EditText) v.findViewById(R.id.view_edittext_optionFour);
        editTextAnswer = (EditText) v.findViewById(R.id.view_edittext_answer);
        button = (Button) v.findViewById(R.id.button_publish);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = new Question(editText.getText().toString(), edittTextOne.getText().toString(), editTextTwo.getText().toString(), editTextThree.getText().toString(), editTextFour.getText().toString(), editTextAnswer.getText().toString());
                //Question question = new Question(editText.getText().toString());
                mDatabaseReference.push().setValue(question);
            }
        });

        return v;
    }


}
