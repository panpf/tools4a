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

package com.github.panpf.tools4a.utils.test;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.utils.WeakAsyncTask;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WeakAsyncTaskTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Test
    public void testError() {
        String result;
        try {
            new WeakAsyncTask<WeakAsyncTaskTest, Integer, Integer, Integer>(this) {
                @Override
                protected Integer doInBackground(@NonNull WeakAsyncTaskTest weakAsyncTaskTest, @NonNull Integer[] integers) {
                    return null;
                }
            };
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        Assert.assertEquals(result, "failed");

        try {
            new TestWeakAsyncTask(this);
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        Assert.assertEquals(result, "success");

        try {
            new TestWeakAsyncTask2(this);
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        Assert.assertEquals(result, "success");
    }

    @Test
    public void testDestroyed() {
        TestActivity activity = activityTestRule.getActivity();

        activityTestRule.finishActivity();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(activity.result, "None");
    }

    @Test
    public void testNormal() {
        TestActivity activity = activityTestRule.getActivity();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(activity.result, "onPostExecute");
    }

    private static class TestWeakAsyncTask extends WeakAsyncTask<WeakAsyncTaskTest, Integer, Integer, Integer> {

        TestWeakAsyncTask(@NonNull WeakAsyncTaskTest weakAsyncTaskTest) {
            super(weakAsyncTaskTest);
        }

        @Override
        protected Integer doInBackground(@NonNull WeakAsyncTaskTest weakAsyncTaskTest, @NonNull Integer[] integers) {
            return null;
        }
    }

    public static class TestActivity extends Activity {

        public String result = "None";

        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            new LoadDataTask(this).execute(0);
        }

        private static class LoadDataTask extends WeakAsyncTask<TestActivity, Integer, Integer, Integer> {

            LoadDataTask(@NonNull TestActivity testActivity) {
                super(testActivity);
            }

            @Override
            protected Integer doInBackground(@NonNull TestActivity testActivity, @NonNull Integer[] integers) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(@NonNull TestActivity testActivity, @Nullable Integer integer) {
                super.onPostExecute(testActivity, integer);
                testActivity.result = "onPostExecute";
            }
        }
    }

    public static class TestWeakAsyncTask2 extends WeakAsyncTask<WeakAsyncTaskTest, Integer, Integer, Integer> {

        TestWeakAsyncTask2(@NonNull WeakAsyncTaskTest weakAsyncTaskTest) {
            super(weakAsyncTaskTest);
        }

        @Override
        @Nullable
        protected Integer doInBackground(@NonNull WeakAsyncTaskTest weakAsyncTaskTest, @NonNull Integer[] integers) {
            return null;
        }
    }
}
