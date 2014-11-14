package io.github.fadils.readrightwrong.model;

/**
 * Created by fadilsutomo on 11/14/14.
 */
public class Score {
    private final float MULTIPLIER = 10;
    private float mValue;

    public Score() {
        mValue = 0;
    }

    public void increaseScore() {
        mValue++;
    }

    public void finalScore(int timeLeft) {
        float decimalizer = timeLeft * 10;
        mValue = mValue * MULTIPLIER * (1 + timeLeft / decimalizer);
    }

    public float getValue() {
        return mValue;
    }

    @Override
    public String toString() {
        return new Float(mValue).toString().replaceAll("\\.0+$", "");
    }
}
