package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hamid-PC on 1/17/2017.
 */

public class TextMakerFragment extends Fragment {


    private EditText mQuestion;
    private Boolean mQuestionValidated;

    private Button mButtonAddMore;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text_maker, container, false);

        mQuestion = (EditText) v.findViewById(R.id.textmaker_text_question);
        mButtonAddMore = (Button) v.findViewById(R.id.textmaker_button_addmore);
        mButtonAddMore.setEnabled(false);
        mButtonAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Successful", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
        mQuestion.addTextChangedListener(new TextValidator(mQuestion) {
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
}
