package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 1/27/2017.
 */

public class EnrollFragment extends Fragment {

    private static final String ENROLL_OPERATION = "EnrollOperationFragment";
    ListView listView;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mStudentReference;
    private FirebaseRecyclerAdapter<Student, StudentViewHolder> mRecyclerAdapter;
    private String mSearchQuery;
    private Student student;
    private String mCourseName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCourseName = getArguments().getString("coursename");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("student");
        mStudentReference = mFirebaseDatabase.getReference().child(mCourseName + "/students");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.student_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return view;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mainmenu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchQuery = query.trim();
                mDatabaseReference.orderByChild("name").startAt(mSearchQuery).addChildEventListener(new ChildEventListener() {


                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        //student = dataSnapshot.getValue(Student.class);
                        //Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();

                        student = dataSnapshot.getValue(Student.class);

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        EnrollOperationFragment enrollOperationFragment = EnrollOperationFragment.newInstance(student, mCourseName);
                        enrollOperationFragment.show(fragmentManager, ENROLL_OPERATION);
//                        Log.d("check",student.getName());


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


                });


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }


        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void UpdateUI() {
        mRecyclerAdapter = new FirebaseRecyclerAdapter<Student, StudentViewHolder>(
                Student.class,
                R.layout.list_item_student,
                StudentViewHolder.class,
                mStudentReference
        ) {
            @Override
            protected void populateViewHolder(StudentViewHolder viewHolder, Student student, int position) {
                viewHolder.textView.setText(student.getName());
            }
        };
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }



    private static class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;

        public StudentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.list_item_student_title_text_view);

        }


        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Okay", Toast.LENGTH_LONG).show();

        }
    }


}

