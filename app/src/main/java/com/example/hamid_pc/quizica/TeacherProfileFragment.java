package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 2/3/2017.
 */

public class TeacherProfileFragment extends Fragment {


    private EditText mEditTextName;
    private EditText mEditTextId;
    private Button mSubmit;
    private String mName;
    private String mId;
    private String mUsername;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("teacher");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);
        mEditTextName = (EditText) view.findViewById(R.id.view_edittext_name);
        mEditTextId = (EditText) view.findViewById(R.id.view_edittext_id);


        mSubmit = (Button) view.findViewById(R.id.button_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = mEditTextName.getText().toString();
                mId = mEditTextId.getText().toString();
                Teacher teacher = new Teacher(mName, mId);
                mDatabaseReference.push().setValue(teacher);

            }
        });
        return view;
    }
}
