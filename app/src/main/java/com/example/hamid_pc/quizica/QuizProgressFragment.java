package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class QuizProgressFragment extends Fragment {

    private RecyclerView mQuizRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("student");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_progress, container, false);
        mQuizRecyclerView = (RecyclerView) view.findViewById(R.id.student_recycler_view);
        mQuizRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public void updateUI() {
        mAdapter = new FirebaseRecyclerAdapter<Student, StudentHolder>(
                Student.class,
                R.layout.list_item_student,
                StudentHolder.class,
                mDatabaseReference) {
            @Override
            protected void populateViewHolder(StudentHolder viewHolder, Student model, int position) {
                viewHolder.mTitleTextView.setText(model.getName());
                Student student = getItem(position);
                viewHolder.bindCourse(student);
            }
        };
        mQuizRecyclerView.setAdapter(mAdapter);
    }


    private static class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        public Student mStudent;

        public StudentHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_student_title_text_view);
        }

        public void bindCourse(Student student) {
            mStudent = student;
        }


        @Override
        public void onClick(View v) {

        }
    }

}


