package com.example.hamid_pc.quizica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class QuizListFragment extends Fragment {

    private static final String DIALOG_OPERATION = "DialogOperation";
    private RecyclerView mQuizRecyclerView;
    private FirebaseRecyclerAdapter mAdapter;
    private FloatingActionButton mfloatingActionButton;
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
        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);
        mQuizRecyclerView = (RecyclerView) view.findViewById(R.id.quiz_recycler_view);
        mQuizRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mfloatingActionButton = (FloatingActionButton) view.findViewById(R.id.quiz_floating_button);
        updateUI();


        mfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = QuestionCreateActivity.newIntent(getContext());
                //  startActivity(intent);
                Intent intent = QuizCreateActivity.newIntent(getContext(), mCourseName);
                startActivity(intent);
            }
        });

        return view;
    }

    public void updateUI() {
        //  page no 184 of Big nerd ranch
        //Temporary Code For Checking

        //  mQuizes = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            mQuizes.add(new Quiz("Database Introduction", 1));
//        }

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
            if (appCompatActivity instanceof CourseListActivity) {
                QuizListActivity activityQuizList = (QuizListActivity) appCompatActivity;
                FragmentManager manager = activityQuizList.getSupportFragmentManager();
                QuizOperationFragment quizOperationFragment = new QuizOperationFragment();
                quizOperationFragment.show(manager, DIALOG_OPERATION);


            }

        }
    }





}
