package io.github.fadils.readrightwrong.test;

import android.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import io.github.fadils.readrightwrong.MainActivity;
import io.github.fadils.readrightwrong.R;

/**
 * Created by fadilsutomo on 11/12/14.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mMainActivity;
    Fragment mMainFragment;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
    }

    @SmallTest
    /**
     * Given mainActivity is created
     * When a new fragment is added to the main_container
     * Then the fragment is not null
     */
    public void testFragmentIsCreated() {
        assertNull(mMainFragment);
        mMainFragment = mMainActivity.getFragmentManager().findFragmentById(R.id.main_container);
        assertNotNull(mMainFragment);
    }

}
