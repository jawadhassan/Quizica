package com.example.hamid_pc.quizica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Hamid-PC on 2/2/2017.
 */

public class ProfileDataFragment extends Fragment {

    Button teacher;
    Button student;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_data, container, false);
        teacher = (Button) view.findViewById(R.id.button_teacher);
        student = (Button) view.findViewById(R.id.button_student);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = TeacherProfileActivity.newIntent(getContext());
                startActivity(intent);

            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = StudentProfileActivity.newIntent(getContext());
                startActivity(intent);
            }
        });
        return view;
    }
}
