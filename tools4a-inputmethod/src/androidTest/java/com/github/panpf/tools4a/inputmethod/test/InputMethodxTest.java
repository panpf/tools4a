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
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.inputmethod.InputMethodx;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.test.Testx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class InputMethodxTest {

    @Test
    public void testShowSoftInput() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final EditText originEditText = activity.getSupportFragmentEditTxt();

            // ensure hide
            if (InputMethodx.isSoftInputShowing(activity)) {
                Runx.runOnMainThread(() -> InputMethodx.hideSoftInput(activity));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
            }

            // show
            Runx.runOnMainThread(() -> InputMethodx.showSoftInput(originEditText));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        });
    }

    @Test
    public void testDelayShowSoftInput() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final EditText supportEditText = activity.getSupportFragmentEditTxt();

            // ensure hide
            if (InputMethodx.isSoftInputShowing(activity)) {
                Runx.runOnMainThread(() -> InputMethodx.hideSoftInput(activity));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
            }

            // show
            Runx.runOnMainThread(() -> InputMethodx.delayShowSoftInput(supportEditText));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));

            // hide
            Runx.runOnMainThread(() -> InputMethodx.hideSoftInput(activity));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));

            // show
            Runx.runOnMainThread(() -> InputMethodx.delayShowSoftInput(supportEditText, 500));
            try {
                Thread.sleep(500 + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        });
    }

    @Test
    public void testHideSoftInput() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final EditText supportEditText = activity.getSupportFragmentEditTxt();

            // ensure hide
            if (InputMethodx.isSoftInputShowing(activity)) {
                Runx.runOnMainThread(() -> InputMethodx.hideSoftInput(activity));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
            }

            // show
            Runx.runOnMainThread(() -> InputMethodx.showSoftInput(supportEditText));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));

            // hide
            Runx.runOnMainThread(() -> InputMethodx.hideSoftInput(activity.getSupportFragment()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));

            // show
            Runx.runOnMainThread(() -> InputMethodx.showSoftInput(supportEditText));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));

            // hide
            Runx.runOnMainThread(() -> InputMethodx.hideSoftInput(supportEditText));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        });
    }

    @Test
    public void testMoveCursor() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final EditText originEditText = activity.getSupportFragmentEditTxt();

            Runx.runOnMainThread(() -> InputMethodx.moveCursorToEnd(originEditText));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(originEditText.length(), Selection.getSelectionEnd(originEditText.getText()));

            Runx.runOnMainThread(() -> InputMethodx.moveCursorToStart(originEditText));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));

            Runx.runOnMainThread(() -> InputMethodx.moveCursorTo(originEditText, originEditText.length() / 2));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(originEditText.length() / 2, Selection.getSelectionEnd(originEditText.getText()));
        });
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
            return Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        @NonNull
        public EditText getSupportFragmentEditTxt() {
            return (EditText) Objects.requireNonNull(getSupportFragment().getView());
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
