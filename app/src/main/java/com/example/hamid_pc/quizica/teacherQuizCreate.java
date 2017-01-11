package com.example.hamid_pc.quizica;

import android.support.v4.app.Fragment;

public class TeacherQuizCreate extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new QuestionTypeFragment();
    }
}
