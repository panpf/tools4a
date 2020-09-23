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
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.activity.Activityx;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.test.Testx;
import com.github.panpf.tools4j.test.Assertx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class ActivityxTest {

    @Test
    public void testIsDestroyedCompat() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            Runx.runOnMainThreadSync(() -> {
                Assert.assertFalse(Activityx.isDestroyedCompat(activity));
            });
            scenario.moveToState(Lifecycle.State.DESTROYED);
            Runx.runOnMainThreadSync(() -> {
                Assert.assertTrue(Activityx.isDestroyedCompat(activity));
            });
        });
    }

    @Test
    public void testConvertTranslucent() {
        Testx.launchActivityWithOnUse(TestActivity.class, activity -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Assert.assertTrue(Activityx.convertToTranslucentCompat(activity));
                Assert.assertTrue(Activityx.convertFromTranslucentCompat(activity));
            } else {
                Assert.assertFalse(Activityx.convertToTranslucentCompat(activity));
                Assert.assertFalse(Activityx.convertFromTranslucentCompat(activity));
            }
        });
    }

    @Test
    public void testGetImplWithParent() {
        Testx.launchActivityWithOnUse(TestActivity.class, activity -> {
            Assert.assertNull(Activityx.getImplFromParent(activity, ImplTestInterface.class));
            Assert.assertEquals(TestActivity.class, Objects.requireNonNull(Activityx.getImplFromParent(activity, ViewModelStoreOwner.class)).getClass());
        });
    }

    @Test
    public void testCanStart() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertFalse(Activityx.canStart(context, new Intent(context, ActivityxTest.class)));
        Assert.assertTrue(Activityx.canStart(context, new Intent(context, TestActivity.class)));
    }

    @Test
    public void testSafeStart() {
        Testx.launchActivityWithOnUse(TestActivity.class, activity -> {
            Assert.assertTrue(Activityx.safeStart(activity, new Intent(activity, TestActivity.class)));
            Assert.assertFalse(Activityx.safeStart(activity.getApplicationContext(), new Intent(activity, ActivityxTest.class)));

            Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, TestActivity.class)));
            Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));

            Assert.assertTrue(Activityx.safeStart(activity.getFragment(), new Intent(activity, TestActivity.class)));
            Assert.assertFalse(Activityx.safeStart(activity.getFragment(), new Intent(activity, ActivityxTest.class)));

            Assert.assertTrue(Activityx.safeStart(activity.getView(), new Intent(activity, TestActivity.class)));
            Assert.assertFalse(Activityx.safeStart(activity.getView(), new Intent(activity, ActivityxTest.class)));
        });
    }

    @Test
    public void testSafeStartByClass() {
        Testx.launchActivityWithOnUse(TestActivity.class, activity -> {
            Assert.assertTrue(Activityx.safeStartByClass(activity, TestActivity.class, null));
            Assert.assertTrue(Activityx.safeStartByClass(activity, TestActivity.class));
            Assert.assertFalse(Activityx.safeStartByClass(activity.getApplicationContext(), NoRegisterTestActivity.class));

            Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestActivity.class, null));
            Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestActivity.class));
            Assert.assertFalse(Activityx.safeStartByClass(activity.getFragment(), NoRegisterTestActivity.class));

            Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestActivity.class, null));
            Assert.assertTrue(Activityx.safeStartByClass(activity.getFragment(), TestActivity.class));
            Assert.assertFalse(Activityx.safeStartByClass(activity.getFragment(), NoRegisterTestActivity.class));

            Assert.assertTrue(Activityx.safeStartByClass(activity.getView(), TestActivity.class, null));
            Assert.assertTrue(Activityx.safeStartByClass(activity.getView(), TestActivity.class));
            Assert.assertFalse(Activityx.safeStartByClass(activity.getView(), NoRegisterTestActivity.class));
        });
    }

    interface ImplTestInterface {

    }

    public static class TestActivity extends FragmentActivity {

        @NonNull
        public Fragment getFragment() {
            return Objects.requireNonNull(getSupportFragmentManager().findFragmentById(android.R.id.content));
        }

        @NonNull
        public View getView() {
            return findViewById(android.R.id.content);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new Fragment()).commit();
        }
    }

    public static class NoRegisterTestActivity extends Activity implements ImplTestInterface {

    }
}