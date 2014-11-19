package io.github.fadils.readrightwrong.test;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import io.github.fadils.readrightwrong.MainActivity;
import io.github.fadils.readrightwrong.R;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by fadilsutomo on 11/12/14.
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    Activity activity;
    Fragment fragment;
    View rootView;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
        fragment = activity.getFragmentManager().findFragmentById(R.id.main_container);
        ViewGroup container = (ViewGroup) activity.findViewById(R.id.main_container);
        LayoutInflater inflater = fragment.getActivity().getLayoutInflater();
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Test
    public void fragment_shoudBeCreated() throws Exception {
        assertNotNull(fragment);
    }

    @Test
    public void rootView_shouldBeCreated() throws Exception {
        assertNotNull(rootView);
    }

    @Test
    /**
     * Sanity check for layout
     */
    public void fragment_shouldHaveQuestion() throws Exception {
        //have right and wrong button
        Button rightButton = (Button)rootView.findViewById(R.id.right_button);
        assertNotNull(rightButton);
        assertThat((String)rightButton.getText(), equalTo("RIGHT") );

        Button wrongButton = (Button)rootView.findViewById(R.id.wrong_button);
        assertNotNull(wrongButton);
        assertThat((String)wrongButton.getText(), equalTo("WRONG") );

        //have a question
        String question = (String)((TextView)rootView.findViewById(
                                R.id.question_text_view)).getText();

        assertNotNull(question);
    }
}