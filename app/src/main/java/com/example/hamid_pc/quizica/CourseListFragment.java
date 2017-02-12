package com.example.hamid_pc.quizica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid-PC on 1/26/2017.
 */

public class CourseListFragment extends Fragment {

    private final int RC_COURSE = 2;
    private RecyclerView mCourseRecyclerView;
    private CourseAdapter mAdapter;
    private List<Course> mCourses;
    private FloatingActionButton mfloatingActionButton;
    private ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Course course;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("course");


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
                Intent intent = CourseCreateActivity.newIntent(getActivity());
                startActivityForResult(intent, RC_COURSE);
            }
        });

        return view;
    }

    public void updateUI() {

//        for (int i = 0; i < 20; i++) {
//            mCourses.add(new Course("CS101", "Introduction To computer Science", "Dr.Smith"));
//        }

        mCourses = new ArrayList<>();
        mAdapter = new CourseAdapter(mCourses);
        attachDatabaseListener();
        mCourseRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_COURSE) {
            if (resultCode == Activity.RESULT_OK) {
                updateUI();
            }
        }
    }

    public void attachDatabaseListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    course = dataSnapshot.getValue(Course.class);
                    Log.d("check", "okay" + "" + dataSnapshot.getValue(Course.class).getCourseName());
                    mAdapter.CourseList.add(course);


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }

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

        private List<Course> CourseList;

        public CourseAdapter(List<Course> CourseList) {

            this.CourseList = CourseList;
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            Course course = CourseList.get(position);
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
            return CourseList.size();
        }
    }
}
