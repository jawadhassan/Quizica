package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Hamid-PC on 2/17/2017.
 */

public class CourseOperationFragment extends DialogFragment {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCourseReference;

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
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCourseReference = mFirebaseDatabase.getReference("course");
        return new AlertDialog.Builder(getActivity())
                //        .setView(v)
                .setTitle(R.string.operation_picker)
                .setItems(R.array.course_dialog_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent quizIntent = QuizListActivity.newIntent(getContext());
                                startActivity(quizIntent);
                                break;
                            case 1:
                                Intent intent = EnrollActivity.newIntent(getContext(), CourseName);
                                startActivity(intent);
                                break;
                            case 2:
                                Query query = mCourseReference.orderByChild("courseName").equalTo(CourseName);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot coursSnapshot : dataSnapshot.getChildren()) {
                                            coursSnapshot.getRef().removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e("check", "onCancelled", databaseError.toException());
                                    }
                                });
                                Toast.makeText(getActivity(), "You have pressed Update Quiz", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                })
                .create();
    }
}
