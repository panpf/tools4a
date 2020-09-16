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
package com.github.panpf.tools4a.context.test;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.github.panpf.tools4a.context.Contextx;
import com.github.panpf.tools4a.device.Devicex;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.test.Testx;
import com.github.panpf.tools4j.test.Assertx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class ContextxTest {

    @Test
    public void testAppContext() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            Fragment fragment = activity.getFragment();
            View view = activity.getView();

            Runx.runOnUiThreadAndWait(() -> {
                Assert.assertTrue(Contextx.appContext(activity) instanceof Application);
                Assert.assertFalse(Contextx.appContext(activity) instanceof Activity);
                Assert.assertNotNull(Contextx.requireContext(fragment));
                Assert.assertFalse(Contextx.requireContext(fragment) instanceof Application);
                Assert.assertTrue(Contextx.requireContext(fragment) instanceof Activity);
                Assert.assertNotNull(Contextx.requireAppContext(fragment));
                Assert.assertTrue(Contextx.requireAppContext(fragment) instanceof Application);
                Assert.assertFalse(Contextx.requireAppContext(fragment) instanceof Activity);
                Assert.assertTrue(Contextx.appContext(view) instanceof Application);
                Assert.assertFalse(Contextx.appContext(view) instanceof Activity);
            });

            scenario.moveToState(Lifecycle.State.DESTROYED);

            Assertx.assertThrow(IllegalStateException.class, () -> Contextx.requireContext(fragment));
            Assertx.assertThrow(IllegalStateException.class, () -> Contextx.requireAppContext(fragment));
        });
    }

    @Test
    public void testSystemService() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertNotNull(Contextx.systemService(context, Context.ACCESSIBILITY_SERVICE));
        Contextx.systemServiceOrNull(context, Context.ACCESSIBILITY_SERVICE);
        Assert.assertNotNull(Contextx.packageManager(context));
        Assert.assertNotNull(Contextx.powerManager(context));
        Assert.assertNotNull(Contextx.windowManager(context));
        Assert.assertNotNull(Contextx.accountManager(context));
        Assert.assertNotNull(Contextx.activityManager(context));
        Assert.assertNotNull(Contextx.alarmManager(context));
        Assert.assertNotNull(Contextx.layoutInflater(context));
        Assert.assertNotNull(Contextx.notificationManager(context));
        Assert.assertNotNull(Contextx.accessibilityManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(Contextx.captioningManager(context));
        }
        Assert.assertNotNull(Contextx.keyguardManager(context));
        Assert.assertNotNull(Contextx.locationManager(context));
        Assert.assertNotNull(Contextx.searchManager(context));
        Assert.assertNotNull(Contextx.sensorManager(context));
        Assert.assertNotNull(Contextx.storageManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(Contextx.storageStatsManager(context));
        }
        Assert.assertNotNull(Contextx.wallpaperManager(context));
        Assert.assertNotNull(Contextx.vibrator(context));
        Assert.assertNotNull(Contextx.connectivityManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Assert.assertNotNull(Contextx.ipSecManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertNotNull(Contextx.networkStatsManager(context));
        }
        try {
            Contextx.wifiManager(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Contextx.wifiManagerOrNull(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                Contextx.wifiAwareManager(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Contextx.wifiAwareManagerOrNull(context);
        }
        Assert.assertNotNull(Contextx.wifiP2pManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Devicex.isEmulator()) {
                Assert.assertNull(Contextx.wifiRttManagerOrNull(context));
            } else {
                Assert.assertNotNull(Contextx.wifiRttManagerOrNull(context));
            }
        }
        Assert.assertNotNull(Contextx.nsdManager(context));
        Assert.assertNotNull(Contextx.audioManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                Contextx.fingerprintManager(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Contextx.fingerprintManagerOrNull(context);
        }
        Assert.assertNotNull(Contextx.mediaRouter(context));
        try {
            Contextx.telephonyManager(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Contextx.telephonyManagerOrNull(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Assert.assertNotNull(Contextx.telephonySubscriptionManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertNotNull(Contextx.carrierConfigManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.telecomManager(context));
        }
        Assert.assertNotNull(Contextx.clipboardManager(context));
        Assert.assertNotNull(Contextx.inputMethodManager(context));
        Assert.assertNotNull(Contextx.textServicesManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(Contextx.textClassificationManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.appWidgetManager(context));
        }
        Assert.assertNotNull(Contextx.dropBoxManager(context));
        Assert.assertNotNull(Contextx.devicePolicyManager(context));
        Assert.assertNotNull(Contextx.uiModeManager(context));
        Assert.assertNotNull(Contextx.downloadManager(context));
        Assert.assertNotNull(Contextx.nfcManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Assert.assertNotNull(Contextx.bluetoothManager(context));
        }
        Assert.assertNotNull(Contextx.usbManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.launcherApps(context));
        }
        Assert.assertNotNull(Contextx.inputManager(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertNotNull(Contextx.displayManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertNotNull(Contextx.userManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.restrictionsManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(Contextx.appOpsManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.cameraManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(Contextx.printManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(Contextx.consumerIrManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Contextx.tvInputManager(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Contextx.tvInputManagerOrNull(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Assert.assertNotNull(Contextx.usageStatsManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.mediaSessionManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.batteryManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.jobScheduler(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(Contextx.mediaProjectionManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Contextx.midiManager(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Contextx.midiManagerOrNull(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Assert.assertNotNull(Contextx.hardwarePropertiesManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            Assert.assertNotNull(Contextx.shortcutManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Assert.assertNotNull(Contextx.systemHealthManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(Contextx.companionDeviceManager(context));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Assert.assertNotNull(Contextx.crossProfileApps(context));
        }
    }

    public static class TestActivity extends FragmentActivity {

        @NonNull
        public Fragment getFragment() {
            return Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        @NonNull
        public View getView() {
            return findViewById(android.R.id.content);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_multi_frame);
            getSupportFragmentManager().beginTransaction().replace(R.id.multiFrameAt_frame2, new Fragment()).commit();
        }
    }
}