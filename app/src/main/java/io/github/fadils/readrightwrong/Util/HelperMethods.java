package io.github.fadils.readrightwrong.Util;

/**
 * Created by fadilsutomo on 11/13/14.
 */
public class HelperMethods {
    public static final int EACH_QUESTION_TIME = 3000;
    public static final int ONE_MILLIS = 1000;

    public static Double increaseScore(Double score) {
        score = score.valueOf(score.doubleValue() + 1);
        return score;
    }

    public static Double finalScore(Double finalScore, int questionsAnswered, double timeLeft) {
        double multiplier = questionsAnswered + (timeLeft / (timeLeft * 10) );
        finalScore = finalScore.doubleValue() * 20 * multiplier;
        finalScore = finalScore * 10;
        return finalScore;

    }

    public static boolean isGameOn(int currentIndex, int numberOfQuestions) {
        boolean result = true;

        if (currentIndex == numberOfQuestions)
            result = false;

        return result;
    }

}
