package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 3/11/2017.
 */

public class EnrollOperationFragment extends DialogFragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mStudentReference;
    private DatabaseReference mCourseReference;
    private Student mStudent;


    public static EnrollOperationFragment newInstance(Student student, String CourseName) {
        EnrollOperationFragment frag = new EnrollOperationFragment();
        Bundle args = new Bundle();
        args.putString("username", student.getName());
        args.putString("id", student.getId());
        args.putString("uuid", student.getUuid());
        args.putString("coursename", CourseName);
        frag.setStudent(student);
        frag.setArguments(args);
        return frag;
    }

    public void setStudent(Student student) {

        mStudent = student;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String UserName = getArguments().getString("username");
        final String ID = getArguments().getString("id");
        final String CourseName = getArguments().getString("coursename");
        final String uuid = getArguments().getString("uuid");
        String Message = getString(R.string.enroll_operation_dialog_msg) + " " + UserName + " " + ID;

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.enroll_operation_dialog_title)
                .setMessage(Message)
                .setPositiveButton(R.string.enroll_operation_dialog_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFirebaseDatabase = FirebaseDatabase.getInstance();
                        mDatabaseReference = mFirebaseDatabase.getReference(CourseName + "/students");
                        mDatabaseReference.push().setValue(mStudent);

                        mStudentReference = mFirebaseDatabase.getReference(mStudent.getUuid());

                        mCourseReference = mFirebaseDatabase.getReference().child("course");
                        mCourseReference.orderByChild("courseName").equalTo(CourseName).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Course course = dataSnapshot.getValue(Course.class);
                                mStudentReference.push().setValue(course);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });





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
