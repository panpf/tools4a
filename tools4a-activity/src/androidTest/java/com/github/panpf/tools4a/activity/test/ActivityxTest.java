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

package com.github.panpf.tools4a.activity.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.activity.Activityx;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4j.lang.Throwablex;
import com.github.panpf.tools4j.premise.Premisex;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ActivityxTest {

    @NonNull
    private final ActivityTestRule<TestFragmentActivity> fragmentActivityTestRule = new ActivityTestRule<>(TestFragmentActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestFragmentActivity> getFragmentActivityTestRule() {
        return fragmentActivityTestRule;
    }

    @Test
    public void testFragmentActivityDestroyed() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));

        fragmentActivityTestRule.finishActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testFragmentActivityNormal() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(Activityx.isDestroyedCompat(activity));
    }

    @Test
    public void testConvertTranslucent() {
        final TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean result = Runx.runOnUIThreadAndWaitResult(() -> Activityx.convertToTranslucentCompat(activity));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result);
        } else {
            Assert.assertFalse(result);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = Runx.runOnUIThreadAndWaitResult(() -> Activityx.convertFromTranslucentCompat(activity));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result);
        } else {
            Assert.assertFalse(result);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetImplWithParent() {
        TestFragmentActivity activity2 = fragmentActivityTestRule.getActivity();
        Assert.assertNull(Activityx.getImplFromParent(activity2, ImplTestInterface.class));
        Assert.assertEquals(Premisex.requireNotNull(Activityx.getImplFromParent(activity2, ViewModelStoreOwner.class)).getClass(), TestFragmentActivity.class);
    }

    @Test
    public void testCanStart() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertFalse(Activityx.canStart(context, new Intent(context, ActivityxTest.class)));
        Assert.assertTrue(Activityx.canStart(context, new Intent(context, TestFragmentActivity.class)));
    }

    @Test
    public void testStartActivityByIntentActivity() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Activityx.start(activity, new Intent(activity, TestFragmentActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getApplicationContext(), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.startByClass(activity, TestFragmentActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.startByClass(activity, TestFragmentActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.startByClass(activity.getApplicationContext(), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentSupportFragment() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, TestFragmentActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getFragment(), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.startByClass(activity.getFragment(), TestFragmentActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.startByClass(activity.getFragment(), TestFragmentActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.startByClass(activity.getFragment(), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartActivityByIntentView() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        try {
            Activityx.start(activity.getView(), new Intent(activity, TestFragmentActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.start(activity.getView(), new Intent(activity, ActivityxTest.class));
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Activityx.startByClass(activity.getView(), TestFragmentActivity.class, null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.startByClass(activity.getView(), TestFragmentActivity.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(Throwablex.stackTraceToString(e));
        }

        try {
            Activityx.startByClass(activity.getView(), NoRegisterTestActivity.class);
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSafeStartActivityByIntentActivity() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity, new Intent(activity, TestFragmentActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getApplicationContext(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStartByClass(activity, TestFragmentActivity.class, null));
        Assert.assertTrue(Activityx.safeStartByClass(activity, TestFragmentActivity.class));
        Assert.assertFalse(Activityx.safeStartByClass(activity.getApplicationContext(), NoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentSupportFragment() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, TestFragmentActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestFragmentActivity.class, null));
        Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestFragmentActivity.class));
        Assert.assertFalse(Activityx.safeStartByClass(activity.getFragment(), NoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentOriginFragment() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, TestFragmentActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestFragmentActivity.class, null));
        Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestFragmentActivity.class));
        Assert.assertFalse(Activityx.safeStartByClass(activity.getFragment(), NoRegisterTestActivity.class));
    }

    @Test
    public void testSafeStartActivityByIntentView() {
        TestFragmentActivity activity = fragmentActivityTestRule.getActivity();

        Assert.assertTrue(Activityx.safeStart(activity.getView(), new Intent(activity, TestFragmentActivity.class)));
        Assert.assertFalse(Activityx.safeStart(activity.getView(), new Intent(activity, ActivityxTest.class)));
        Assert.assertTrue(Activityx.safeStartByClass(activity.getView(), TestFragmentActivity.class, null));
        Assert.assertTrue(Activityx.safeStartByClass(activity.getView(), TestFragmentActivity.class));
        Assert.assertFalse(Activityx.safeStartByClass(activity.getView(), NoRegisterTestActivity.class));
    }

    public interface ImplTestInterface {
    }

    public static class TestFragmentActivity extends FragmentActivity {
        public boolean finished;
        public boolean finishedActivity;
        public boolean finishedActivityFromChild;
        public boolean destoryed;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new Fragment()).commit();
        }

        @Override
        public void finish() {
            super.finish();
            finished = true;
        }

        @Override
        public void finishActivity(int requestCode) {
            super.finishActivity(requestCode);
            finishedActivity = true;
        }

        @Override
        public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
            super.finishActivityFromChild(child, requestCode);
            finishedActivityFromChild = true;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            destoryed = true;
        }

        @NonNull
        public Fragment getFragment() {
            return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(android.R.id.content));
        }

        @NonNull
        public View getView() {
            return findViewById(android.R.id.content);
        }
    }

    public static class NoRegisterTestActivity extends Activity implements ImplTestInterface {
        public boolean finished;
        public boolean finishedActivity;
        public boolean finishedActivityFromChild;
        public boolean destoryed;

        @Override
        public void finish() {
            super.finish();
            finished = true;
        }

        @Override
        public void finishActivity(int requestCode) {
            super.finishActivity(requestCode);
            finishedActivity = true;
        }

        @Override
        public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
            super.finishActivityFromChild(child, requestCode);
            finishedActivityFromChild = true;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            destoryed = true;
        }
    }
}
