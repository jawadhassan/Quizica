package com.example.hamid_pc.quizica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class QuizListFragment extends Fragment {

    private static final String DIALOG_OPERATION = "DialogOperation";
    private RecyclerView mQuizRecyclerView;
    private QuizAdapter mAdapter;
    private List<Quiz> mQuizes;
    private FloatingActionButton mfloatingActionButton;

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
                Intent intent = QuestionCreateActivity.newIntent(getContext());
                startActivity(intent);
            }
        });

        return view;
    }

    public void updateUI() {
        //  page no 184 of Big nerd ranch
        //Temporary Code For Checking

        mQuizes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mQuizes.add(new Quiz("Database Introduction", 1));
        }

        mAdapter = new QuizAdapter(mQuizes);
        mQuizRecyclerView.setAdapter(mAdapter);
    }

    private class QuizHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;

        public QuizHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_quiz_title_text_view);
        }

        @Override
        public void onClick(View v) {
            FragmentManager manager = getFragmentManager();
            QuizOperationFragment quizOperationFragment = new QuizOperationFragment();
            quizOperationFragment.show(manager, DIALOG_OPERATION);

        }
    }

    private class QuizAdapter extends RecyclerView.Adapter<QuizHolder> {
        private List<Quiz> mQuiz;


        public QuizAdapter(List<Quiz> mQuiz) {
            this.mQuiz = mQuiz;
        }

        @Override
        public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_quiz, parent, false);

            return new QuizHolder(view);
        }

        @Override
        public void onBindViewHolder(QuizHolder holder, int position) {
            Quiz quiz = mQuiz.get(position);
            holder.mTitleTextView.setText(quiz.getmTitle());
        }

        @Override
        public int getItemCount() {
            return mQuiz.size();
        }
    }
}
