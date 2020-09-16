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

package com.github.panpf.tools4a.dimen.test;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.dimen.Dimenx;
import com.github.panpf.tools4a.test.Testx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class DimenxTest {

    @Test
    public void testDp2px() {
        Testx.launchAndOnActivityWithUse(TestActivity.class, activity -> {
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(activity, 10f));
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(activity, 10));
            Assert.assertEquals(0, Dimenx.dp2px(activity, 0f));
            Assert.assertEquals(0, Dimenx.dp2px(activity, 0));

            Fragment fragment = activity.getFragment();
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(fragment, 10f));
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(fragment, 10));
            Assert.assertEquals(0, Dimenx.dp2px(fragment, 0f));
            Assert.assertEquals(0, Dimenx.dp2px(fragment, 0));

            View view = activity.getView();
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(view, 10f));
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().density + 0.5f), Dimenx.dp2px(view, 10));
            Assert.assertEquals(0, Dimenx.dp2px(view, 0f));
            Assert.assertEquals(0, Dimenx.dp2px(view, 0));
        });
    }

    @Test
    public void testPx2dp() {
        Testx.launchAndOnActivityWithUse(TestActivity.class, activity -> {
            Assert.assertEquals(((float) 100) / activity.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(activity, 100), 0f);
            Assert.assertEquals(0f, Dimenx.px2dp(activity, 0), 0f);

            Fragment fragment = activity.getFragment();
            Assert.assertEquals(((float) 100) / activity.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(fragment, 100), 0f);
            Assert.assertEquals(0f, Dimenx.px2dp(fragment, 0), 0f);

            View view = activity.getView();
            Assert.assertEquals(((float) 100) / activity.getResources().getDisplayMetrics().density + 0.5f, Dimenx.px2dp(view, 100), 0f);
            Assert.assertEquals(0f, Dimenx.px2dp(view, 0), 0f);
        });
    }

    @Test
    public void testSp2px() {
        Testx.launchAndOnActivityWithUse(TestActivity.class, activity -> {
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(activity, 10f));
            Assert.assertEquals((int) ((float) 10 * activity.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(activity, 10));
            Assert.assertEquals(0, Dimenx.sp2px(activity, 0f));
            Assert.assertEquals(0, Dimenx.sp2px(activity, 0));

            Fragment fragment = activity.getFragment();
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(fragment, 10f));
            Assert.assertEquals((int) ((float) 10 * activity.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(fragment, 10));
            Assert.assertEquals(0, Dimenx.sp2px(fragment, 0f));
            Assert.assertEquals(0, Dimenx.sp2px(fragment, 0));

            View view = activity.getView();
            Assert.assertEquals((int) (10f * activity.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(view, 10f));
            Assert.assertEquals((int) ((float) 10 * activity.getResources().getDisplayMetrics().scaledDensity + 0.5f), Dimenx.sp2px(view, 10));
            Assert.assertEquals(0, Dimenx.sp2px(view, 0f));
            Assert.assertEquals(0, Dimenx.sp2px(view, 0));
        });
    }

    @Test
    public void testPx2sp() {
        Testx.launchAndOnActivityWithUse(TestActivity.class, activity -> {
            Assert.assertEquals(((float) 100) / activity.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(activity, 100), 0f);
            Assert.assertEquals(0f, Dimenx.px2sp(activity, 0), 0f);

            Fragment fragment = activity.getFragment();
            Assert.assertEquals(((float) 100) / activity.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(fragment, 100), 0f);
            Assert.assertEquals(0f, Dimenx.px2sp(fragment, 0), 0f);

            View view = activity.getView();
            Assert.assertEquals(((float) 100) / activity.getResources().getDisplayMetrics().scaledDensity + 0.5f, Dimenx.px2sp(view, 100), 0f);
            Assert.assertEquals(0f, Dimenx.px2sp(view, 0), 0f);
        });
    }

    @Test
    public void testApplyDimension() {
        Testx.launchAndOnActivityWithUse(TestActivity.class, activity -> {
            Assert.assertEquals(10f * activity.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(activity, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
            Assert.assertEquals(((float) 10) * activity.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(activity, TypedValue.COMPLEX_UNIT_SP, 10), 0f);

            Fragment fragment = activity.getFragment();
            Assert.assertEquals(10f * activity.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(fragment, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
            Assert.assertEquals(((float) 10) * activity.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(fragment, TypedValue.COMPLEX_UNIT_SP, 10), 0f);

            View view = activity.getView();
            Assert.assertEquals(10f * activity.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(view, TypedValue.COMPLEX_UNIT_SP, 10f), 0f);
            Assert.assertEquals(((float) 10) * activity.getResources().getDisplayMetrics().scaledDensity, Dimenx.applyDimension(view, TypedValue.COMPLEX_UNIT_SP, 10), 0f);
        });
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
        public Fragment getFragment() {
            return Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        @NonNull
        public View getView() {
            return findViewById(android.R.id.content);
        }
    }
}
