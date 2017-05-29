package com.example.hamid_pc.quizica;

import java.util.UUID;

/**
 * Created by Hamid-PC on 1/12/2017.
 */

public class Question {
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private String answer;
    private String QuestionUuid;

    public Question(String question, String optionOne, String optionTwo, String optionThree, String optionFour, String answer) {

        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.answer = answer;
        QuestionUuid = UUID.randomUUID().toString();
    }

    public Question(String question) {
        this.question = question;

    }

    public Question() {

    }

    public String getQuestionUuid() {
        return QuestionUuid;
    }

    public void setQuestionUuid(String questionUuid) {
        QuestionUuid = questionUuid;
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

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
