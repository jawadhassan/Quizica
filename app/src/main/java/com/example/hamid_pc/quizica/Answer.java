package com.example.hamid_pc.quizica;

/**
 * Created by Hamid-PC on 4/29/2017.
 */

public class Answer {

    private String AnswerText;
    private String QuestionUuid;

    public Answer() {
    }

    public Answer(String answerText) {
        AnswerText = answerText;
    }

    public String getAnswerText() {
        return AnswerText;
    }

    public void setAnswerText(String answerText) {
        AnswerText = answerText;
    }

    public String getQuestionUuid() {
        return QuestionUuid;
    }

    public void setQuestionUuid(String questionUuid) {
        QuestionUuid = questionUuid;
    }
}
