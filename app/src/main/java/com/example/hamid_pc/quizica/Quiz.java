package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 1/20/2017.
 */

public class Quiz {
    private String mTitle;
    private int mQuizNumber;


    public Quiz(String mTitle, int mQuizNumber) {
        this.mTitle = mTitle;
        this.mQuizNumber = mQuizNumber;
    }

    public Quiz() {
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
}
