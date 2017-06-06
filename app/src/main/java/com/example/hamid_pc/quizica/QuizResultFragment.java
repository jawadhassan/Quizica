package com.example.hamid_pc.quizica;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class QuizResultFragment extends Fragment {


    String mQuizUuid;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private TextView mMarksObtained;
    private TextView mTotalMarks;
    private TextView mTitle;
    private TextView mMessage;
    private TextView mNotFound;
    private TextView mOutOFSign;

    public static QuizResultFragment newInstance(String QuizUuid) {
        QuizResultFragment frag = new QuizResultFragment();
        Bundle args = new Bundle();
        args.putString("quizuuid", QuizUuid);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizUuid = getArguments().getString("quizuuid");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("results/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_result, container, false);
        mTitle = (TextView) view.findViewById(R.id.result_title);
        mMessage = (TextView) view.findViewById(R.id.message);
        updateUI();
        mMarksObtained = (TextView) view.findViewById(R.id.marks_obtained);
        mTotalMarks = (TextView) view.findViewById(R.id.total_marks);
        mNotFound = (TextView) view.findViewById(R.id.not_found);
        mOutOFSign = (TextView) view.findViewById(R.id.out_of_sign);
        mNotFound.setVisibility(View.VISIBLE);
        return view;
    }

    public void updateUI() {
        mDatabaseReference.orderByChild("mQuizUuid").equalTo(mQuizUuid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                StudentResult studentResult = dataSnapshot.getValue(StudentResult.class);
                mNotFound.setVisibility(View.INVISIBLE);
                mOutOFSign.setVisibility(View.VISIBLE);
                mTitle.setVisibility(View.VISIBLE);
                mMarksObtained.setVisibility(View.VISIBLE);
                mTotalMarks.setVisibility(View.VISIBLE);
                mMessage.setVisibility(View.VISIBLE);
                mTotalMarks.setText("" + studentResult.getmTotalMarks());
                mMarksObtained.setText("" + studentResult.getmTotalObtainedMarks());


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


    }

}
