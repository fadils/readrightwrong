
/**
 * Created by fadilsutomo on 11/12/14.
 */

package io.github.fadils.readrightwrong.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.github.fadils.readrightwrong.R;
import io.github.fadils.readrightwrong.Util.HelperMethods;
import io.github.fadils.readrightwrong.model.Question;
import io.github.fadils.readrightwrong.model.Score;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements OnClickListener {
    private Score mScore = new Score();
    private String mResult;
    private Button mRightButton;
    private Button mWrongButton;
    private Button mRestartButton;
    private TextView mQuestionTextView;
    private TextView mTimerTextView;

    private boolean isTimeOut = false;

    private CountDownTimer mTimer;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    //for traversing question bank
    private int mCurrentIndex = 0;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mQuestionTextView = (TextView)rootView.findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());

        mRestartButton = (Button)rootView.findViewById(R.id.restart_button);
        mRestartButton.setOnClickListener(this);
        mRightButton = (Button)rootView.findViewById(R.id.right_button);
        mRightButton.setOnClickListener(this);
        mWrongButton = (Button)rootView.findViewById(R.id.wrong_button);
        mWrongButton.setOnClickListener(this);

        mTimerTextView = (TextView)rootView.findViewById(R.id.timer);

        int totalTime = HelperMethods.EACH_QUESTION_TIME * mQuestionBank.length;

        mTimer = new CountDownTimer(totalTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTimerTextView.setText(new Long(millisUntilFinished/1000).toString());
            }

            @Override
            public void onFinish() {
                isTimeOut = true;
                showResult();
            }
        }.start();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.right_button:
            //is the game still on
            if( HelperMethods.isGameOn(mCurrentIndex, mQuestionBank.length) ) {
                //User answer is correct
                if(mQuestionBank[mCurrentIndex].getQuestionAnswer()) {
                    mScore.increaseScore();
                    mCurrentIndex = mCurrentIndex + 1;

                    if(mCurrentIndex == mQuestionBank.length) {
                        showResult();
                    } else {
                        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
                    }
                } else { //answer is incorrect
                    Toast.makeText(this.getActivity(), R.string.incorrect_toast,
                            Toast.LENGTH_SHORT).show();
                    showResult();
                }
            } else {
                showResult();
            }

                break;

            case R.id.wrong_button:
            //is the game still on
            if( HelperMethods.isGameOn(mCurrentIndex, mQuestionBank.length) ) {
                //User answer is correct
                if (!mQuestionBank[mCurrentIndex].getQuestionAnswer()) {
                    mScore.increaseScore();
                    mCurrentIndex++;

                    if (mCurrentIndex == mQuestionBank.length) {
                        showResult();
                    } else {
                        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
                    }

                } else { //answer is incorrect
                    Toast.makeText(this.getActivity(), R.string.incorrect_toast,
                            Toast.LENGTH_SHORT).show();
                    showResult();
                }
            } else {
                showResult();
            }

                break;

            case R.id.restart_button:
                mTimer.start();
                isTimeOut = false;
                mCurrentIndex = 0;
                mScore = new Score();
                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
                mRestartButton.setVisibility(View.GONE);
                mRightButton.setVisibility(View.VISIBLE);
                mWrongButton.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void showResult() {
        mScore.finalScore(Integer.parseInt((String)mTimerTextView.getText()));
        mResult = "Your total score: " + mScore;

        mQuestionTextView.setText(mResult);

        if(isTimeOut) {
            mTimerTextView.setText(R.string.zero_second);
            mRestartButton.setVisibility(View.VISIBLE);
            mRightButton.setVisibility(View.GONE);
            mWrongButton.setVisibility(View.GONE);
        } else {
            mRestartButton.setVisibility(View.VISIBLE);
            mRightButton.setVisibility(View.GONE);
            mWrongButton.setVisibility(View.GONE);
        }

        mTimer.cancel();
    }
}
