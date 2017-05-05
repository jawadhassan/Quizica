package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Hamid-PC on 4/10/2017.
 */

public class CheckerFragment extends Fragment {

    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private EditText mEditTextView;

    private String mQuestionText;
    private String mAnswerText;


    public static CheckerFragment newInstance(String questionuuid, String answer) {
        CheckerFragment checkerFragment = new CheckerFragment();
        Bundle args = new Bundle();
        args.putString("questionuuid", questionuuid);
        args.putString("answer", answer);
        checkerFragment.setArguments(args);
        return checkerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionText = getArguments().getString("questionuuid");
        mAnswerText = getArguments().getString("answer");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checker, container, false);
        mQuestionTextView = (TextView) view.findViewById(R.id.question_text_view);
        mAnswerTextView = (TextView) view.findViewById(R.id.answer_text_view);
        mEditTextView = (EditText) view.findViewById(R.id.number_edit_text);

        mQuestionTextView.setText(mQuestionText);
        mAnswerTextView.setText(mAnswerText);

        mEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mEditTextView.getText().toString();
                Log.d("checkerfragment", "" + text);
            }
        });

        return view;

    }




}
