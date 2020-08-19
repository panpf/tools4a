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

package com.github.panpf.tools4a.display.test;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.display.Displayx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DisplayxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getActivityRule() {
        return this.activityRule;
    }

    @Test
    public void testGetScreenSize() {
        Context context = InstrumentationRegistry.getContext();
        Point point = Displayx.getScreenSize(context);

        Assert.assertTrue(point.x > 0);
        Assert.assertTrue(point.y > 0);
        if (Displayx.isOrientationPortrait(context)) {
            Assert.assertTrue(point.y > point.x);
        } else {
            Assert.assertTrue(point.x > point.y);
        }

        Assert.assertEquals(point.x, Displayx.getScreenWidth(context));
        Assert.assertEquals(point.y, Displayx.getScreenHeight(context));
    }

    @Test
    public void testGetActionBarSize() {
        Context context = InstrumentationRegistry.getContext();
        TypedValue tv = new TypedValue();
        int actonBarSize;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actonBarSize = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } else {
            actonBarSize = 0;
        }

        Assert.assertEquals(actonBarSize, Displayx.getActionBarSize(context));
    }

    @Test
    public void testGetMetrics() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(Displayx.getMetrics(context));
    }

    @Test
    public void testGetDensity() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertEquals(context.getResources().getDisplayMetrics().density, Displayx.getDensity(context), 0f);
        Assert.assertEquals(context.getResources().getDisplayMetrics().densityDpi, Displayx.getDensityDpi(context), 0f);
    }

    @Test
    public void testGetRotation() {
        TestActivity activity = activityRule.getActivity();
        WindowManager windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);

        switch (windowManager.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity));
                break;
        }

        switch (windowManager.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity.getSupportFragment()));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity.getSupportFragment()));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity.getSupportFragment()));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity.getSupportFragment()));
                break;
        }

        switch (windowManager.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                Assert.assertEquals(0, Displayx.getRotation(activity.getView()));
                break;
            case Surface.ROTATION_90:
                Assert.assertEquals(90, Displayx.getRotation(activity.getView()));
                break;
            case Surface.ROTATION_180:
                Assert.assertEquals(180, Displayx.getRotation(activity.getView()));
                break;
            case Surface.ROTATION_270:
                Assert.assertEquals(270, Displayx.getRotation(activity.getView()));
                break;
        }
    }

    @Test
    public void testGetOrientation() {
        TestActivity activity = activityRule.getActivity();

        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Assert.assertEquals(Configuration.ORIENTATION_PORTRAIT, Displayx.getOrientation(activity));
            Assert.assertEquals(Configuration.ORIENTATION_PORTRAIT, Displayx.getOrientation(activity.getSupportFragment()));
            Assert.assertEquals(Configuration.ORIENTATION_PORTRAIT, Displayx.getOrientation(activity.getView()));
            Assert.assertTrue(Displayx.isOrientationPortrait(activity));
            Assert.assertTrue(Displayx.isOrientationPortrait(activity.getSupportFragment()));
            Assert.assertTrue(Displayx.isOrientationPortrait(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getView()));
        } else if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Assert.assertEquals(Configuration.ORIENTATION_LANDSCAPE, Displayx.getOrientation(activity));
            Assert.assertEquals(Configuration.ORIENTATION_LANDSCAPE, Displayx.getOrientation(activity.getSupportFragment()));
            Assert.assertEquals(Configuration.ORIENTATION_LANDSCAPE, Displayx.getOrientation(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getView()));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity.getSupportFragment()));
            Assert.assertTrue(Displayx.isOrientationLandscape(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationUndefined(activity.getView()));
        } else {
            Assert.assertEquals(Configuration.ORIENTATION_UNDEFINED, Displayx.getOrientation(activity));
            Assert.assertEquals(Configuration.ORIENTATION_UNDEFINED, Displayx.getOrientation(activity.getSupportFragment()));
            Assert.assertEquals(Configuration.ORIENTATION_UNDEFINED, Displayx.getOrientation(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationPortrait(activity.getView()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getSupportFragment()));
            Assert.assertFalse(Displayx.isOrientationLandscape(activity.getView()));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity.getSupportFragment()));
            Assert.assertTrue(Displayx.isOrientationUndefined(activity.getView()));
        }
    }

    @Test
    public void testStatusBar() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertTrue(Displayx.getStatusBarHeight(context) > 0);
    }

    @Test
    public void testNavigationBar() {
        Context context = InstrumentationRegistry.getContext();
        if (Displayx.hasNavigationBar(context)) {
            if (Displayx.isOrientationPortrait(context)) {
                Assert.assertTrue(Displayx.getNavigationBarHeight(context) > 0);
            } else {
                Assert.assertTrue(Displayx.getNavigationBarWidth(context) > 0);
            }
        } else {
            Assert.assertEquals(0, Displayx.getNavigationBarWidth(context));
            Assert.assertEquals(0, Displayx.getNavigationBarHeight(context));
        }
    }

    public static class TestActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_multi_frame);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new Fragment())
                    .commit();
        }

        @NonNull
        public Fragment getSupportFragment() {
            return getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2);
        }

        public View getView() {
            return findViewById(android.R.id.content);
        }
    }
}
