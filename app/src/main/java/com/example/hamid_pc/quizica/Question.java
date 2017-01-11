package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 1/12/2017.
 */

public class Question {
    private String quesiton;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String answer;

    public Question(String question, String optoinOne, String optionTwo, String optionThree, String optionFour, String answer) {

        this.quesiton = question;
        this.optionOne = optoinOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.answer = answer;
    }

    public Question(String question, String answer) {
        this.quesiton = question;
        this.answer = answer;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptionFour() {

        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public String getOptionThree() {

        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionTwo() {

        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionOne() {

        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getQuesiton() {

        return quesiton;
    }

    public void setQuesiton(String quesiton) {
        this.quesiton = quesiton;
    }
}
