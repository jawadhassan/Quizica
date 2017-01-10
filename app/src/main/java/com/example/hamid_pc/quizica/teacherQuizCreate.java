package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class TeacherQuizCreate extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_teacher_quiz);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment questionTypeFragment = fragmentManager.findFragmentById(R.id.create_quiz_container);

        if(questionTypeFragment == null){
                 questionTypeFragment = new QuestionTypeFragment();
                 fragmentManager.beginTransaction()
                         .add(R.id.create_quiz_container,questionTypeFragment)
                         .commit();

        }
    }
}
