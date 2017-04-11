package com.example.hamid_pc.quizica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by Hamid-PC on 2/3/2017.
 */

public class TeacherProfileFragment extends Fragment {


    private EditText mEditTextName;
    private EditText mEditTextId;
    private Button mSubmit;
    private String mName;
    private String mId;
    private String mUsername;
    private User mUser;
    private Teacher mTeacher;
    private FirebaseAuth mFirebaseAuth;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseTeacherReference;
    private DatabaseReference mDatabaseUserReference;
    private FirebaseUser mFirebaseUser;
    private SharedPreferences mPref;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseTeacherReference = mFirebaseDatabase.getReference().child("teacher");
        mDatabaseUserReference = mFirebaseDatabase.getReference().child("user");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);
        mEditTextName = (EditText) view.findViewById(R.id.view_edittext_name);
        mEditTextId = (EditText) view.findViewById(R.id.view_edittext_id);


        mSubmit = (Button) view.findViewById(R.id.button_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = mEditTextName.getText().toString();
                mId = mEditTextId.getText().toString();
                mTeacher = new Teacher(mName, mId);
                mDatabaseTeacherReference.push().setValue(mTeacher);

                mUser = new User(FirebaseAuth.getInstance().getCurrentUser().getUid(), mName, "Teacher");
                mDatabaseUserReference.push().setValue(mUser);

                context = getActivity();
                mPref = context.getSharedPreferences(getString(R.string.pref_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPref.edit();
                editor.putBoolean(getString(R.string.pref_sign_key), true);
                editor.commit();
                Intent intent = CourseListActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        return view;
    }
}
