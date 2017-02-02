package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid-PC on 1/26/2017.
 */

public class CourseListFragment extends Fragment {
    public static final int RC_SIGN_IN = 1;
    private RecyclerView mCourseRecyclerView;
    private CourseAdapter mAdapter;
    private List<Course> mCourses;
    private FloatingActionButton mfloatingActionButton;
    private FirebaseAuth mFireAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public CourseListFragment() {
        super();
        mFireAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Check", "OK");
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(AuthUI.GOOGLE_PROVIDER,
                                            AuthUI.EMAIL_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        mCourseRecyclerView = (RecyclerView) view.findViewById(R.id.course_recycler_view);
        mCourseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mfloatingActionButton = (FloatingActionButton) view.findViewById(R.id.course_floating_button);
        updateUI();
        mfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void updateUI() {
        mCourses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mCourses.add(new Course("CS101", "Introduction To computer Science", "Dr.Smith"));
        }

        mAdapter = new CourseAdapter(mCourses);
        mCourseRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mFireAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mFireAuth.addAuthStateListener(mAuthListener);
    }

    private class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;

        public CourseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mTextView = (TextView)
                    itemView.findViewById(R.id.list_item_course_title_text_view);
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

        private List<Course> mCourse;

        public CourseAdapter(List<Course> mCourse) {

            this.mCourse = mCourse;
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            Course course = mCourse.get(position);
            holder.mTextView.setText(course.getCourseName());

        }

        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_course, parent, false);

            return new CourseHolder(view);
        }

        @Override
        public int getItemCount() {
            return mCourse.size();
        }
    }
}
