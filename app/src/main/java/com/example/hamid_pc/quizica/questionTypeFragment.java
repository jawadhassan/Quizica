package com.example.hamid_pc.quizica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hamid-PC on 1/11/2017.
 */

public class QuestionTypeFragment extends Fragment {

    private Button mMcqButton;
    private Button mTextButton;
    private String mQuizUuid;

    public static QuestionTypeFragment newInstance(String QuizUuid) {
        QuestionTypeFragment frag = new QuestionTypeFragment();
        Bundle args = new Bundle();
        args.putString("quizuuid", QuizUuid);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizUuid = getArguments().getString("quizuuid");
        Log.d("Check", mQuizUuid);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question_type, container, false);
        mMcqButton = (Button) v.findViewById(R.id.button_mcq);
        mTextButton = (Button) v.findViewById(R.id.button_text);

        mMcqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = McqMakerActivity.newIntent(getContext(), mQuizUuid);
                startActivity(i);
            }
        });

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = TextMakerActivity.newIntent(getContext(), mQuizUuid);
                startActivity(i);
            }
        });

        return v;
    }


}
