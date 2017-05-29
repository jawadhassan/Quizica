package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 5/7/2017.
 */

public class StudentResult {
    private String mQuizUuid;
    private String mQuizName;
    private Integer mTotalMarks;
    private Integer mTotalObtainedMarks;


    public StudentResult() {
    }

    public StudentResult(String mQuizUuid, String mQuizName, Integer mTotalMarks, Integer mTotalObtainedMarks) {
        this.mQuizUuid = mQuizUuid;
        this.mQuizName = mQuizName;
        this.mTotalMarks = mTotalMarks;
        this.mTotalObtainedMarks = mTotalObtainedMarks;
    }

    public String getmQuizUuid() {
        return mQuizUuid;
    }

    public void setmQuizUuid(String mQuizUuid) {
        this.mQuizUuid = mQuizUuid;
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
