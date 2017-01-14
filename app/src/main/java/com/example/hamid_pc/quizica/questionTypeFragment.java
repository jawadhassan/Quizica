package com.example.hamid_pc.quizica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hamid-PC on 1/11/2017.
 */

public class QuestionTypeFragment extends Fragment {


    static final int ADD_QUESTION_REQUEST = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question_type, container, false);
        Button button = (Button) v.findViewById(R.id.button_mcq);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = McqMakerActivity.newIntent(getContext());
                startActivityForResult(i, ADD_QUESTION_REQUEST);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_QUESTION_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getContext(), "Question Added", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
