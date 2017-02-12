package com.example.hamid_pc.quizica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 2/9/2017.
 */

public class CourseCreateFragment extends Fragment {

    EditText CourseNameText;
    EditText CourseIdText;
    Button submitButton;

    private String CourseName;
    private String CourseId;
    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFireAuth;
    private Course course;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_create, container, false);
        CourseNameText = (EditText) view.findViewById(R.id.view_edittext_name);
        CourseIdText = (EditText) view.findViewById(R.id.view_edittext_id);
        submitButton = (Button) view.findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CourseName = CourseNameText.getText().toString();
                CourseId = CourseIdText.getText().toString();


                course = new Course(CourseId, CourseName, mUsername);
                mDatabaseReference.push().setValue(course);

                Intent intent = new Intent();
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();

            }
        });
        return view;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("course");
        mFireAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFireAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            mUsername = mFirebaseUser.getDisplayName();
        }


    }
}
