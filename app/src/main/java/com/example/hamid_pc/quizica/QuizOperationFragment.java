package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Hamid-PC on 1/22/2017.
 */

public class QuizOperationFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
                                Log.v("check", "You have pressed Start Quiz");
                                break;
                            case 1:
                                Toast.makeText(getActivity(), "You have pressed Edit Quiz", Toast.LENGTH_LONG).show();
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
