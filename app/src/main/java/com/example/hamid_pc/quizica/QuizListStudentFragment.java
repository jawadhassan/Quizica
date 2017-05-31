package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 5/1/2017.
 */

public class QuizListStudentFragment extends Fragment {

    private static final String QUIZ_OPERATION = "DialogOperation";
    private RecyclerView mQuizRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private String mCourseName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mCourseName = getArguments().getString("coursename");
        mDatabaseReference = mFirebaseDatabase.getReference(mCourseName + "/quizzes");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quizlist_student, container, false);
        mQuizRecyclerView = (RecyclerView) view.findViewById(R.id.quiz_recycler_view);
        mQuizRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();


        return view;
    }

    public void updateUI() {
        mAdapter = new FirebaseRecyclerAdapter<Quiz, QuizHolder>(
                Quiz.class,
                R.layout.list_item_quiz,
                QuizHolder.class,
                mDatabaseReference) {
            @Override
            protected void populateViewHolder(QuizHolder viewHolder, Quiz model, int position) {
                viewHolder.mTitleTextView.setText(model.getmTitle());
                Quiz quiz = getItem(position);
                viewHolder.bindCourse(quiz);
            }
        };
        mQuizRecyclerView.setAdapter(mAdapter);
        RecyclerViewDivider.with(getContext()).build().addTo(mQuizRecyclerView);
    }

    private static class QuizHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;
        public Quiz mQuiz;

        public QuizHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_quiz_title_text_view);
        }

        public void bindCourse(Quiz quiz) {
            mQuiz = quiz;
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof QuizListStudentActivity) {
                QuizListStudentActivity activityStudentQuizList = (QuizListStudentActivity) appCompatActivity;
                FragmentManager manager = activityStudentQuizList.getSupportFragmentManager();

                QuizStudentOperationFragment quizStudentOperationFragment = QuizStudentOperationFragment.newInstance(mQuiz.getmQuizUuid());
                quizStudentOperationFragment.show(manager, QUIZ_OPERATION);


            }

        }

    }
}