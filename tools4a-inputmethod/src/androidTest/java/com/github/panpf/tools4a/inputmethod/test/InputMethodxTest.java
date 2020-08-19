/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.panpf.tools4a.inputmethod.test;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.inputmethod.InputMethodx;
import com.github.panpf.tools4a.run.Runx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class InputMethodxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getActivityRule() {
        return this.activityRule;
    }

    @Test
    public void testShowSoftInput() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText originEditText = activity.getSupportFragmentEditTxt();

        // ensure hide
        if (InputMethodx.isSoftInputShowing(activity)) {
            Runx.runInUI(() -> InputMethodx.hideSoftInput(activity));
            Thread.sleep(500);
            Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        }

        // show
        Runx.runInUI(() -> InputMethodx.showSoftInput(originEditText));
        Thread.sleep(500);
        Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
    }

    @Test
    public void testDelayShowSoftInput() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText supportEditText = activity.getSupportFragmentEditTxt();

        // ensure hide
        if (InputMethodx.isSoftInputShowing(activity)) {
            Runx.runInUI(() -> InputMethodx.hideSoftInput(activity));
            Thread.sleep(500);
            Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        }

        // show
        Runx.runInUI(() -> InputMethodx.delayShowSoftInput(supportEditText));
        Thread.sleep(500);
        Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));

        // hide
        Runx.runInUI(() -> InputMethodx.hideSoftInput(activity));
        Thread.sleep(500);
        Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));

        // show
        Runx.runInUI(() -> InputMethodx.delayShowSoftInput(supportEditText, 500));
        Thread.sleep(500 + 500);
        Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
    }

    @Test
    public void testHideSoftInput() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText supportEditText = activity.getSupportFragmentEditTxt();

        // ensure hide
        if (InputMethodx.isSoftInputShowing(activity)) {
            Runx.runInUI(() -> InputMethodx.hideSoftInput(activity));
            Thread.sleep(500);
            Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        }

        // show
        Runx.runInUI(() -> InputMethodx.showSoftInput(supportEditText));
        Thread.sleep(500);
        Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));

        // hide
        Runx.runInUI(() -> InputMethodx.hideSoftInput(activity.getSupportFragment()));
        Thread.sleep(500);
        Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));

        // show
        Runx.runInUI(() -> InputMethodx.showSoftInput(supportEditText));
        Thread.sleep(500);
        Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));

        // hide
        Runx.runInUI(() -> InputMethodx.hideSoftInput(supportEditText));
        Thread.sleep(500);
        Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
    }

    @Test
    public void testMoveCursor() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText originEditText = activity.getSupportFragmentEditTxt();

        Runx.runInUI(() -> InputMethodx.moveCursorToEnd(originEditText));
        Thread.sleep(100);
        Assert.assertEquals(originEditText.length(), Selection.getSelectionEnd(originEditText.getText()));

        Runx.runInUI(() -> InputMethodx.moveCursorToStart(originEditText));
        Thread.sleep(100);
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));

        Runx.runInUI(() -> InputMethodx.moveCursorTo(originEditText, originEditText.length() / 2));
        Thread.sleep(100);
        Assert.assertEquals(originEditText.length() / 2, Selection.getSelectionEnd(originEditText.getText()));
    }

    public static class TestActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            setContentView(R.layout.at_multi_frame);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new EditSupportFragment())
                    .commit();
        }

        @NonNull
        public Fragment getSupportFragment() {
            return getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2);
        }

        @NonNull
        public EditText getSupportFragmentEditTxt() {
            //noinspection ConstantConditions
            return (EditText) getSupportFragment().getView();
        }

        public View getView() {
            return findViewById(android.R.id.content);
        }
    }

    public static class EditSupportFragment extends Fragment {
        @NonNull
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            EditText editText = new EditText(getActivity());
            editText.setText("0123456789");
            return editText;
        }
    }
}
