package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Hamid-PC on 3/11/2017.
 */

public class EnrollOperationFragment extends DialogFragment {


    public EnrollOperationFragment() {
        super();
    }

    public static EnrollOperationFragment newInstance(String UserName, String ID) {
        EnrollOperationFragment frag = new EnrollOperationFragment();
        Bundle args = new Bundle();
        args.putString("username", UserName);
        args.putString("id", ID);
        frag.setArguments(args);
        return frag;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String UserName = getArguments().getString("username");
        String ID = getArguments().getString("id");
        String Message = getString(R.string.enroll_operation_dialog_msg) + " " + UserName + " " + ID;
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.enroll_operation_dialog_title)
                .setMessage(Message)
                .setPositiveButton(R.string.enroll_operation_dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EnrollFragment enrollFragment = getActivity().getSupportFragmentManager();
                        enrollFragment.ReturnValue(true);

                    }
                })
                .setNegativeButton(R.string.enroll_operation_dialog_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }
}
