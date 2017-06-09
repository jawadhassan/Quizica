package com.example.hamid_pc.quizica;

import java.util.UUID;

/**
 * Created by Hamid-PC on 1/20/2017.
 */

public class Quiz {
    private String mTitle;
    private int mQuizNumber;
    private String mQuizUuid;
    private int mQuizTotalMarks;
    private boolean mStarted;


    public Quiz(String mTitle, int mQuizNumber, int mQuizTotalMarks) {
        this.mTitle = mTitle;
        this.mQuizNumber = mQuizNumber;
        this.mQuizTotalMarks = mQuizTotalMarks;
        mQuizUuid = UUID.randomUUID().toString();
        mStarted = false;
    }

    public Quiz() {
    }

    public boolean ismStarted() {
        return mStarted;
    }

    public void setmStarted(boolean mStarted) {
        this.mStarted = mStarted;
    }

    public int getmQuizTotalMarks() {
        return mQuizTotalMarks;
    }

    public void setmQuizTotalMarks(int mQuizTotalMarks) {
        this.mQuizTotalMarks = mQuizTotalMarks;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmQuizNumber() {
        return mQuizNumber;
    }

    public void setmQuizNumber(int mQuizNumber) {
        this.mQuizNumber = mQuizNumber;
    }

    public String getmQuizUuid() {
        return mQuizUuid;
    }

    public void setmQuizUuid(String mQuizUuid) {
        this.mQuizUuid = mQuizUuid;
    }
}
