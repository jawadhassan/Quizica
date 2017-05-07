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
 * Created by Hamid-PC on 1/22/2017.
 */

public class QuizOperationFragment extends DialogFragment {


    public static QuizOperationFragment newInstance(int QuizNumber, String QuizName) {
        QuizOperationFragment frag = new QuizOperationFragment();
        Bundle args = new Bundle();
        args.putString("quizname", QuizName);
        args.putInt("quiznumber", QuizNumber);
        frag.setArguments(args);
        return frag;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String QuizName = getArguments().getString("quizname");
        final int QuizNumber = getArguments().getInt("quiznumber");
        //    View v = LayoutInflater.from(getActivity())
        //          .inflate(R.layout.dialog_operation,null);
        return new AlertDialog.Builder(getActivity())
                //        .setView(v)
                .setTitle(R.string.operation_picker)
                .setItems(R.array.operation_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent QuizProgressIntent = QuizProgressActivity.NewIntent(getActivity());
                                startActivity(QuizProgressIntent);
                                break;
                            case 1:
                                Intent intent = CheckerActivity.newIntent(getActivity(), QuizNumber, QuizName);
                                startActivity(intent);
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
