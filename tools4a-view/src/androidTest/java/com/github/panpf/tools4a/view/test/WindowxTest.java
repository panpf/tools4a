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

package com.github.panpf.tools4a.view.test;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.view.Windowx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WindowxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getActivityRule() {
        return this.activityRule;
    }

    @Test
    public void testBrightness() {
        final TestActivity activity = activityRule.getActivity();

        final float windowBrightness = Windowx.getBrightness(activity.getWindow());

        if (windowBrightness < 0) {
            Assert.assertTrue(Windowx.isBrightnessFlowSystem(activity.getWindow()));
        } else {
            Assert.assertFalse(Windowx.isBrightnessFlowSystem(activity.getWindow()));
        }

        try {
            final float newWindowBrightnessValue = windowBrightness * -1;
            Runx.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    Windowx.setBrightness(activity.getWindow(), newWindowBrightnessValue);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            float newWindowBrightnessValueFromSettings = Windowx.getBrightness(activity.getWindow());

            if (newWindowBrightnessValueFromSettings < 0) {
                Assert.assertTrue(Windowx.isBrightnessFlowSystem(activity.getWindow()));
            } else {
                Assert.assertFalse(Windowx.isBrightnessFlowSystem(activity.getWindow()));
            }

            Assert.assertEquals(newWindowBrightnessValue, newWindowBrightnessValueFromSettings, 0f);
        } finally {
            Runx.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    Windowx.setBrightness(activity.getWindow(), windowBrightness);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TestActivity extends Activity {


    }
}
