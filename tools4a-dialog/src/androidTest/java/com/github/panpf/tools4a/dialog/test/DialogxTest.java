package com.github.panpf.tools4a.dialog.test;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.github.panpf.tools4a.dialog.Dialogx;
import com.github.panpf.tools4a.run.Runx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DialogxTest {

    @Rule
    public final ActivityTestRule<TestFragmentActivity> mFragmentActivityTestRule =
            new ActivityTestRule<>(TestFragmentActivity.class);

    @Test
    public void testSetClickButtonClosable() {
        Runx.runOnUiThreadAndWait(() -> {
            TestFragmentActivity activity = mFragmentActivityTestRule.getActivity();
            Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), true));
            Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), false));
        });
    }

    @Test
    public void testShowProgressDialog() {
        Runx.runOnUiThreadAndWait(() -> {
            TestFragmentActivity activity = mFragmentActivityTestRule.getActivity();
            androidx.fragment.app.Fragment supportFragment = mFragmentActivityTestRule.getActivity()
                    .getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);

            Assert.assertNotNull(Dialogx.showProgressDialog(activity, "by activity"));
            Assert.assertNotNull(Dialogx.showProgressDialog(activity, android.R.string.ok));

            assert supportFragment != null;
            Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, "by supportFragment"));
            Assert.assertNotNull(Dialogx.showProgressDialog(supportFragment, android.R.string.yes));
        });
    }

    public static class TestFragmentActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new androidx.fragment.app.Fragment())
                    .commit();
        }

        public Dialog getDialog() {
            return new Dialog(this);
        }
    }
}