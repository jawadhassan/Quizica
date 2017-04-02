package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 4/2/2017.
 */

public class CourseOperationStudentFragment extends DialogFragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCourseReference;

    public static CourseOperationStudentFragment newInstance(String CourseName) {
        CourseOperationStudentFragment frag = new CourseOperationStudentFragment();
        Bundle args = new Bundle();
        args.putString("coursename", CourseName);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String CourseName = getArguments().getString("coursename");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCourseReference = mFirebaseDatabase.getReference("course");
        return new AlertDialog.Builder(getActivity())
                //        .setView(v)
                .setTitle(R.string.operation_picker)
                .setItems(R.array.student_course_dialog_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent quizIntent = QuizListActivity.newIntent(getContext(), CourseName);
                                startActivity(quizIntent);
                                break;
                            case 1:

                                break;

                        }
                    }
                })
                .create();
    }


}
