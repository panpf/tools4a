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

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.github.panpf.tools4a.utils.LifecycleBroadcastReceiver;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LifecycleBroadcastReceiverTest {
    private static String INTENT_ACTION = "com.github.panpf.tools4a.utils.test.LifecycleBroadcastReceiverTest";

    @NonNull
    private final ActivityTestRule<TestFragmentActivity> activityTestRule = new ActivityTestRule<>(TestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestFragmentActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Test
    public void test() throws InterruptedException {
        TestFragmentActivity activity = activityTestRule.getActivity();
        TestFragment fragment = activity.getTestFragment();
        assert fragment != null;

        activity.sendBroadcast(new Intent(INTENT_ACTION).putExtra("event", "send1"));
        Thread.sleep(1000);
        Assert.assertEquals("[send1]", activity.createReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.startReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.resumeReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.resumeReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", fragment.createReceiver.getEventArrayString());

        activityTestRule.finishActivity();
        Thread.sleep(1000);
        activity.sendBroadcast(new Intent(INTENT_ACTION).putExtra("event", "send2"));
        Thread.sleep(1000);
        Assert.assertEquals("[send1]", activity.createReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.startReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", activity.resumeReceiver.getEventArrayString());
        Assert.assertEquals("[send1]", fragment.createReceiver.getEventArrayString());

        activity.sendBroadcast(new Intent(INTENT_ACTION).putExtra("event", "throwCrash"));
        Thread.sleep(1000);
    }

    public static class TestFragmentActivity extends FragmentActivity {

        public TestBroadcastReceiver createReceiver;
        public TestBroadcastReceiver startReceiver;
        public TestBroadcastReceiver resumeReceiver;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            createReceiver = new TestBroadcastReceiver(this);
            createReceiver.registerCreateDestroy(new IntentFilter(INTENT_ACTION));

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new TestFragment())
                    .commit();
        }

        @Override
        protected void onStart() {
            super.onStart();

            startReceiver = new TestBroadcastReceiver(this);
            startReceiver.registerStartStop(new IntentFilter(INTENT_ACTION));
        }

        @Override
        protected void onResume() {
            super.onResume();

            resumeReceiver = new TestBroadcastReceiver(this);
            resumeReceiver.registerResumePause(new IntentFilter(INTENT_ACTION));
        }

        @Nullable
        public TestFragment getTestFragment() {
            return (TestFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }
    }

    public static class TestFragment extends Fragment {

        public TestBroadcastReceiver createReceiver;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            createReceiver = new TestBroadcastReceiver(this);
            createReceiver.registerCreateDestroy(new IntentFilter(INTENT_ACTION));
        }
    }

    public static class TestBroadcastReceiver extends LifecycleBroadcastReceiver {
        private List<String> eventList = new LinkedList<>();

        public TestBroadcastReceiver(@NonNull Fragment fragment) {
            super(fragment.requireContext(), fragment);
        }

        public TestBroadcastReceiver(@NonNull FragmentActivity activity) {
            super(activity, activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String event = intent.getStringExtra("event");
            if (event == null) event = "unknown";
            if (event.equals("throwCrash")) {
                throw new RuntimeException("Received a crash crash broadcast");
            } else {
                eventList.add(event);
            }
        }

        @NonNull
        public String getEventArrayString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (String s : eventList) {
                if (stringBuilder.length() > 1) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(s);
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }
}
