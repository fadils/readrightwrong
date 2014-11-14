package io.github.fadils.readrightwrong.model;

/**
 * Created by fadilsutomo on 11/12/14.
 */
public class Question {
    public static final int EACH_QUESTION_TIME = 3000;
    public static final int ONE_MILLIS = 1000;

    private int mQuestion;
    private boolean mQuestionAnswer;

    public Question(int questionID, boolean questionAnswer) {
        mQuestion = questionID;
        mQuestionAnswer = questionAnswer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int questionID) {
        mQuestion = questionID;
    }

    public boolean getQuestionAnswer() {
        return mQuestionAnswer;
    }

    public void setQuestionAnswer(boolean questionAnswer) {
        mQuestionAnswer = questionAnswer;
    }
}
