package com.example.hamid_pc.quizica;

import android.content.Intent;
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

import com.firebase.ui.auth.ResultCodes;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 4/1/2017.
 */

public class CourseListStudentPanelFragment extends Fragment {

    private static final String DIALOG_OPERATION = "CourseDialogOperation";
    public final int RC_COURSE = 2;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<Course, CourseViewHolder> mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.course_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        UpdateUI();

        return view;
    }

    public void UpdateUI() {
        mAdapter = new FirebaseRecyclerAdapter<Course, CourseViewHolder>(
                Course.class,
                R.layout.list_item_course,
                CourseViewHolder.class,
                mDatabaseReference
        ) {


            @Override
            protected void populateViewHolder(CourseViewHolder viewHolder, Course model, int position) {
                viewHolder.textView.setText(model.getCourseName());
                Course course = getItem(position);
                viewHolder.bindCourse(course);


            }


        };
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_COURSE && resultCode == ResultCodes.OK) {
            mAdapter.notifyDataSetChanged();
        }
    }

    private static class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        Course mCourse;

        public CourseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.list_item_course_title_text_view);


        }


        public void bindCourse(Course course) {
            mCourse = course;

        }


        @Override
        public void onClick(View v) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            if (appCompatActivity instanceof CourseListActivity) {

                CourseListActivity activityCourseList = (CourseListActivity) appCompatActivity;
                FragmentManager manager = activityCourseList.getSupportFragmentManager();
                CourseOperationStudentFragment courseOperationStudentFragment = CourseOperationStudentFragment.newInstance(mCourse.getCourseName());
                courseOperationStudentFragment.show(manager, DIALOG_OPERATION);


            }
        }

    }
}