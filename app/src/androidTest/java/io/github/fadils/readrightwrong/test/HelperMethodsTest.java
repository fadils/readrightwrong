package io.github.fadils.readrightwrong.test;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import io.github.fadils.readrightwrong.Util.HelperMethods;

/**
 * Created by fadilsutomo on 11/13/14.
 */
public class HelperMethodsTest extends TestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    /**
     * Given question is shown
     * When user answered correctly
     * Then score increases
     */
    public void testIncreaseScore() {
        Double zero = new Double(0);
        Double currentScore = new Double(0);
        currentScore = HelperMethods.increaseScore(currentScore);

        assertTrue(currentScore.doubleValue() > zero.doubleValue());
    }

    @SmallTest
    /**
     * Given a question is shown
     * When user answers and it's a final question
     * Then make sure the score of one answered question is less than two answered questions
     * That is, two questions slow is better than one question fast
     */
    public void testCompareFinalScore() {
        int oneQuestion = 1;
        int twoQuestions = 2;
        double maxTimeLeft = (HelperMethods.EACH_QUESTION_TIME / HelperMethods.ONE_MILLIS) * twoQuestions;
        double minTimeLeft = 1;


        Double answeredOne = new Double(0);
        answeredOne = HelperMethods.increaseScore(answeredOne);
        answeredOne = HelperMethods.finalScore(answeredOne, oneQuestion, maxTimeLeft);

        Double answeredTwo = new Double(0);
        answeredTwo = HelperMethods.increaseScore(answeredTwo);
        answeredTwo = HelperMethods.increaseScore(answeredTwo);
        answeredTwo = HelperMethods.finalScore(answeredTwo, twoQuestions, minTimeLeft);


        assertTrue(answeredOne.doubleValue() < answeredTwo.doubleValue());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
