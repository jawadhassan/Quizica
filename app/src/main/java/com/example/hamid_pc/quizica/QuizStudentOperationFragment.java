package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Hamid-PC on 5/1/2017.
 */

public class QuizStudentOperationFragment extends DialogFragment {


    public static QuizStudentOperationFragment newInstance(String QuizUuid) {
        QuizStudentOperationFragment frag = new QuizStudentOperationFragment();
        Bundle args = new Bundle();
        args.putString("quizuuid", QuizUuid);
        frag.setArguments(args);
        return frag;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //    View v = LayoutInflater.from(getActivity())
        //          .inflate(R.layout.dialog_operation,null);
        final String mQuizUuid = getArguments().getString("quizuuid");
        return new AlertDialog.Builder(getActivity())
                //        .setView(v)
                .setTitle(R.string.operation_picker)
                .setItems(R.array.operation_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = QuestionsPagerActivity.NewIntent(getActivity(), mQuizUuid);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = QuizResultActivity.NewIntent(getActivity());
                                startActivity(intent1);
                                break;
                            case 2:
                                Toast.makeText(getActivity(), "You have pressed Update Quiz", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                })
                .create();
    }


}
