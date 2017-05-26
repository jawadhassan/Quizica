package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 1/17/2017.
 */

public class TextMakerFragment extends Fragment {


    private EditText mQuestionText;
    private Boolean mQuestionValidated;

    private Question mQuestion;
    private String mQuizUuid;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Button mButtonAddMore;
    private Button mButtonDone;

    public static TextMakerFragment newInstance(String QuizUuid) {
        TextMakerFragment frag = new TextMakerFragment();
        Bundle args = new Bundle();
        Log.d("CheckTextMaker", QuizUuid);
        args.putString("quizuuid", QuizUuid);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mQuizUuid = getArguments().getString("quizuuid");
        mDatabaseReference = mFirebaseDatabase.getReference().child("questions/" + mQuizUuid);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text_maker, container, false);

        mQuestionText = (EditText) v.findViewById(R.id.textmaker_text_question);
        mButtonDone = (Button) v.findViewById(R.id.textmaker_button_done);
        mButtonAddMore = (Button) v.findViewById(R.id.textmaker_button_addmore);
        mButtonAddMore.setEnabled(false);
        mButtonDone.setEnabled(false);
        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        mButtonAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        mQuestionText.addTextChangedListener(new TextValidator(mQuestionText) {
            @Override
            public void validate(EditText editText, String text) {
                mQuestionValidated = textValidate(text);
                enableButton();
            }
        });

        return v;
    }

    public void enableButton() {
        if (mQuestionValidated) {
            mButtonAddMore.setEnabled(true);
        }
    }

    public void submitForm() {
        Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
        mQuestion = new Question(mQuestionText.getText().toString());
        mDatabaseReference.push().setValue(mQuestion);
        getActivity().finish();

    }
}
