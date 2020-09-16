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

package com.github.panpf.tools4a.context.ktx.test

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.context.ktx.*
import com.github.panpf.tools4a.device.Devicex
import com.github.panpf.tools4a.run.Runx
import com.github.panpf.tools4a.test.ktx.getActivitySync
import com.github.panpf.tools4a.test.ktx.launchActivityWithUse
import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContextxTest {

    @Test
    fun testAppContext() {
        launchActivityWithUse(TestActivity::class.java) { scenario ->
            val activity = scenario.getActivitySync()
            val fragment = activity.supportFragment
            val view = activity.view

            Runx.runOnUiThreadAndWait {
                Assert.assertTrue(activity.appContext() is Application)
                Assert.assertFalse(activity.appContext() is Activity)

                Assert.assertNotNull(fragment.requireContext())
                Assert.assertFalse(fragment.requireContext() is Application)
                Assert.assertTrue(fragment.requireContext() is Activity)

                Assert.assertNotNull(fragment.requireAppContext())
                Assert.assertTrue(fragment.requireAppContext() is Application)
                Assert.assertFalse(fragment.requireAppContext() is Activity)

                Assert.assertTrue(view.appContext() is Application)
                Assert.assertFalse(view.appContext() is Activity)
            }

            scenario.moveToState(Lifecycle.State.DESTROYED)

            assertThrow(IllegalStateException::class) {
                fragment.requireContext()
            }

            assertThrow(IllegalStateException::class) {
                fragment.requireAppContext()
            }
        }
    }

    @Test
    fun testSystemService() {
        val context = InstrumentationRegistry.getInstrumentation().context

        Assert.assertNotNull(context.systemService<AccessibilityManager>(Context.ACCESSIBILITY_SERVICE))
        context.systemServiceOrNull<AccessibilityManager>(Context.ACCESSIBILITY_SERVICE)

        Assert.assertNotNull(context.packageManager())
        Assert.assertNotNull(context.powerManager())
        Assert.assertNotNull(context.windowManager())
        Assert.assertNotNull(context.accountManager())
        Assert.assertNotNull(context.activityManager())
        Assert.assertNotNull(context.alarmManager())
        Assert.assertNotNull(context.layoutInflater())
        Assert.assertNotNull(context.notificationManager())
        Assert.assertNotNull(context.accessibilityManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(context.captioningManager())
        }
        Assert.assertNotNull(context.keyguardManager())
        Assert.assertNotNull(context.locationManager())
        Assert.assertNotNull(context.searchManager())
        Assert.assertNotNull(context.sensorManager())
        Assert.assertNotNull(context.storageManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(context.storageStatsManager())
        }
        Assert.assertNotNull(context.wallpaperManager())
        Assert.assertNotNull(context.vibrator())
        Assert.assertNotNull(context.connectivityManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Assert.assertNotNull(context.ipSecManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertNotNull(context.networkStatsManager())
        }
        try {
            context.wifiManager()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        context.wifiManagerOrNull()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                context.wifiAwareManager()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            context.wifiAwareManagerOrNull()
        }
        Assert.assertNotNull(context.wifiP2pManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Devicex.isEmulator()) {
                Assert.assertNull(context.wifiRttManagerOrNull())
            } else {
                Assert.assertNotNull(context.wifiRttManagerOrNull())
            }
        }
        Assert.assertNotNull(context.nsdManager())
        Assert.assertNotNull(context.audioManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                context.fingerprintManager()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            context.fingerprintManagerOrNull()
        }
        Assert.assertNotNull(context.mediaRouter())
        try {
            context.telephonyManager()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        context.telephonyManagerOrNull()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Assert.assertNotNull(context.telephonySubscriptionManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertNotNull(context.carrierConfigManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.telecomManager())
        }
        Assert.assertNotNull(context.clipboardManager())
        Assert.assertNotNull(context.inputMethodManager())
        Assert.assertNotNull(context.textServicesManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(context.textClassificationManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.appWidgetManager())
        }
        Assert.assertNotNull(context.dropBoxManager())
        Assert.assertNotNull(context.devicePolicyManager())
        Assert.assertNotNull(context.uiModeManager())
        Assert.assertNotNull(context.downloadManager())
        Assert.assertNotNull(context.nfcManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Assert.assertNotNull(context.bluetoothManager())
        }
        Assert.assertNotNull(context.usbManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.launcherApps())
        }
        Assert.assertNotNull(context.inputManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertNotNull(context.displayManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertNotNull(context.userManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.restrictionsManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(context.appOpsManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.cameraManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(context.printManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(context.consumerIrManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                context.tvInputManager()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            context.tvInputManagerOrNull()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Assert.assertNotNull(context.usageStatsManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.mediaSessionManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.batteryManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.jobScheduler())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.mediaProjectionManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                context.midiManager()
            } catch (e: Exception) {
            }
            context.midiManagerOrNull()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Assert.assertNotNull(context.hardwarePropertiesManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            Assert.assertNotNull(context.shortcutManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Assert.assertNotNull(context.systemHealthManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(context.companionDeviceManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Assert.assertNotNull(context.crossProfileApps())
        }
    }

    class TestActivity : FragmentActivity() {

        val supportFragment: Fragment
            get() =
                supportFragmentManager.findFragmentById(R.id.multiFrameAt_frame2)!!

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_multi_frame)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, Fragment())
                    .commit()
        }
    }
}
