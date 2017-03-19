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
 * Created by Hamid-PC on 2/17/2017.
 */

public class CourseOperationFragment extends DialogFragment {


    public static CourseOperationFragment newInstance(String CourseName) {
        CourseOperationFragment frag = new CourseOperationFragment();
        Bundle args = new Bundle();
        args.putString("coursename", CourseName);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String CourseName = getArguments().getString("coursename");

        return new AlertDialog.Builder(getActivity())
                //        .setView(v)
                .setTitle(R.string.operation_picker)
                .setItems(R.array.course_dialog_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:

                                break;
                            case 1:
                                Intent intent = EnrollActivity.newIntent(getContext(), CourseName);
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
