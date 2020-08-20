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

package com.github.panpf.tools4a.settings.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.settings.Settingsx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class SettingsxTest {

    @NonNull
    private final ActivityTestRule<RequestPermissionTestActivity> requestPermissionActivityRule = new ActivityTestRule<>(RequestPermissionTestActivity.class);
    @NonNull
    private final ActivityTestRule<RequestNotificationPolicyTestActivity> requestNotificationPolicyActivityRule = new ActivityTestRule<>(RequestNotificationPolicyTestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getRequestPermissionActivityRule() {
        return this.requestPermissionActivityRule;
    }

    @Rule
    @NonNull
    public final ActivityTestRule getRequestNotificationPolicyActivityRule() {
        return this.requestNotificationPolicyActivityRule;
    }

    @Test
    public void testScreenBrightnessMode() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        boolean isAutomatic = Settingsx.isScreenBrightnessModeAutomatic(context);
        try {
            boolean newAutomaticValue = !isAutomatic;
            Assert.assertTrue(Settingsx.setScreenBrightnessModeAutomatic(context, newAutomaticValue));
            boolean newAutomaticValueFromSettings = Settingsx.isScreenBrightnessModeAutomatic(context);
            Assert.assertEquals(newAutomaticValue, newAutomaticValueFromSettings);
        } finally {
            Settingsx.setScreenBrightnessModeAutomatic(context, isAutomatic);
        }
    }

    @Test
    public void testScreenBrightness() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        int screenBrightness = Settingsx.getScreenBrightness(context);
        try {
            int newScreenBrightnessValue = 255 - screenBrightness;
            Assert.assertTrue(Settingsx.setScreenBrightness(context, newScreenBrightnessValue));
            int newScreenBrightnessValueFromSettings = Settingsx.getScreenBrightness(context);
            Assert.assertEquals(newScreenBrightnessValue, newScreenBrightnessValueFromSettings);
        } finally {
            Settingsx.setScreenBrightness(context, screenBrightness);
        }
    }

    @Test
    public void testScreenOffTimeout() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        int screenOffTimeout = Settingsx.getScreenOffTimeout(context);
        try {
            int newScreenOffTimeoutValue = screenOffTimeout + 100;
            Assert.assertTrue(Settingsx.setScreenOffTimeout(context, newScreenOffTimeoutValue));
            int newScreenOffTimeoutValueFromSettings = Settingsx.getScreenOffTimeout(context);
            Assert.assertEquals(newScreenOffTimeoutValue, newScreenOffTimeoutValueFromSettings);
        } finally {
            Settingsx.setScreenOffTimeout(context, screenOffTimeout);
        }
    }

    @Test
    public void testAirplaneModeOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) return;

        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        boolean isAirplaneModeOn = Settingsx.isAirplaneModeOn(context);
        try {
            boolean newAirplaneModeOnValue = !isAirplaneModeOn;
            Assert.assertTrue(Settingsx.setAirplaneModeOn(context, newAirplaneModeOnValue));
            boolean newAirplaneModeOnValueFromSettings = Settingsx.isAirplaneModeOn(context);
            Assert.assertEquals(newAirplaneModeOnValue, newAirplaneModeOnValueFromSettings);
        } finally {
            Settingsx.setAirplaneModeOn(context, isAirplaneModeOn);
        }
    }

    @Test
    public void testBluetoothOn() {
        if (!Settingsx.haveBluetooth()) return;

        boolean isBluetoothOn = Settingsx.isBluetoothOn();
        try {
            boolean newBluetoothOnValue = !isBluetoothOn;
            Assert.assertTrue(Settingsx.setBluetoothOn(newBluetoothOnValue));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean newBluetoothOnValueFromSettings = Settingsx.isBluetoothOn();
            Assert.assertEquals(newBluetoothOnValue, newBluetoothOnValueFromSettings);
        } finally {
            Settingsx.setBluetoothOn(isBluetoothOn);
        }
    }

    public static class RequestPermissionTestActivity extends Activity {

        private CountDownLatch countDownLatch = new CountDownLatch(1);

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (!Settingsx.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 101);

                Toast.makeText(this, "请允许修改系统设置并关闭此页面", Toast.LENGTH_LONG).show();
                new Handler(Looper.getMainLooper()).postDelayed(new FinishTask(new WeakReference<>(this)), 10 * 1000);
            } else {
                finish();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            finish();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            countDownLatch.countDown();
        }

        private static class FinishTask implements Runnable {
            private WeakReference<Activity> activityWeakReference;

            FinishTask(WeakReference<Activity> activityWeakReference) {
                this.activityWeakReference = activityWeakReference;
            }

            @Override
            public void run() {
                Activity activity = activityWeakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
    }

    public static class RequestNotificationPolicyTestActivity extends Activity {

        private CountDownLatch countDownLatch = new CountDownLatch(1);

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (!Settingsx.isNotificationPolicyAccessGranted(this)) {
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivityForResult(intent, 1);

                Toast.makeText(this, "请允许修改请勿打扰状态并关闭此页面", Toast.LENGTH_LONG).show();
                new Handler(Looper.getMainLooper()).postDelayed(new FinishTask(new WeakReference<>(this)), 10 * 1000);
            } else {
                finish();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            finish();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            countDownLatch.countDown();
        }

        private static class FinishTask implements Runnable {
            private WeakReference<Activity> activityWeakReference;

            FinishTask(WeakReference<Activity> activityWeakReference) {
                this.activityWeakReference = activityWeakReference;
            }

            @Override
            public void run() {
                Activity activity = activityWeakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
    }
}
