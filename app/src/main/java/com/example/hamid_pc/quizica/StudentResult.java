package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class StudentResult {
    private Integer mQuizNumber;
    private String mQuizName;
    private Integer mTotalMarks;
    private Integer mTotalObtainedMarks;


    public StudentResult() {
    }

    public StudentResult(Integer mQuizNumber, String mQuizName, Integer mTotalMarks, Integer mTotalObtainedMarks) {
        this.mQuizNumber = mQuizNumber;
        this.mQuizName = mQuizName;
        this.mTotalMarks = mTotalMarks;
        this.mTotalObtainedMarks = mTotalObtainedMarks;
    }

    public Integer getmQuizNumber() {
        return mQuizNumber;
    }

    public void setmQuizNumber(Integer mQuizNumber) {
        this.mQuizNumber = mQuizNumber;
    }

    public String getmQuizName() {
        return mQuizName;
    }

    public void setmQuizName(String mQuizName) {
        this.mQuizName = mQuizName;
    }

    public Integer getmTotalMarks() {
        return mTotalMarks;
    }

    public void setmTotalMarks(Integer mTotalMarks) {
        this.mTotalMarks = mTotalMarks;
    }

    public Integer getmTotalObtainedMarks() {
        return mTotalObtainedMarks;
    }

    public void setmTotalObtainedMarks(Integer mTotalObtainedMarks) {
        this.mTotalObtainedMarks = mTotalObtainedMarks;
    }
}
