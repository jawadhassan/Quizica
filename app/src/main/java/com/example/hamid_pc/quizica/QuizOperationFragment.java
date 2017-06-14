package com.example.hamid_pc.quizica;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Hamid-PC on 1/22/2017.
 */

public class QuizOperationFragment extends DialogFragment {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mQuizReference;

    public static QuizOperationFragment newInstance(String CourseName, int QuizNumber, String QuizName, int QuizTotalMarks, String QuizUuid) {
        QuizOperationFragment frag = new QuizOperationFragment();
        Bundle args = new Bundle();
        args.putString("quizname", QuizName);
        args.putInt("quiznumber", QuizNumber);
        args.putString("quizuuid", QuizUuid);
        args.putInt("quiztotalmarks", QuizTotalMarks);
        args.putString("coursename", CourseName);
        frag.setArguments(args);
        return frag;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String QuizName = getArguments().getString("quizname");
        final int QuizNumber = getArguments().getInt("quiznumber");
        final String mQuizUuid = getArguments().getString("quizuuid");
        final int mQuizTotalMarks = getArguments().getInt("quiztotalmarks");
        final String CourseName = getArguments().getString("coursename");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mQuizReference = mFirebaseDatabase.getReference(CourseName + "/quizzes");
        //    View v = LayoutInflater.from(getActivity())
        //          .inflate(R.layout.dialog_operation,null);
        return new AlertDialog.Builder(getActivity())
                //        .setView(v)
                .setTitle(R.string.operation_picker)
                .setItems(R.array.operation_options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent QuizProgressIntent = QuizProgressActivity.NewIntent(getActivity(), CourseName, mQuizUuid);
                                startActivity(QuizProgressIntent);
                                break;
                            case 1:
                                // Intent intent = CheckerActivity.newIntent(getActivity(), QuizNumber, QuizName);
                                Intent intent = StudentsAnswersListActivity.newIntent(getActivity(), mQuizUuid, mQuizTotalMarks);
                                startActivity(intent);
                                break;
                            case 2:

                                Query query = mQuizReference.orderByChild("mQuizUuid").equalTo(mQuizUuid);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot coursSnapshot : dataSnapshot.getChildren()) {
                                            coursSnapshot.getRef().removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e("check", "onCancelled", databaseError.toException());
                                    }
                                });
                                break;
                        }
                    }
                })
                .create();
    }


}
