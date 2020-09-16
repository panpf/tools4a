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

package com.github.panpf.tools4a.settings.ktx.test

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.github.panpf.tools4a.settings.ktx.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.ref.WeakReference
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class SettingsxTest {

    @get:Rule
    val requestPermissionActivityRule = ActivityTestRule(RequestPermissionTestActivity::class.java)

    @get:Rule
    val requestNotificationPolicyActivityRule = ActivityTestRule(RequestNotificationPolicyTestActivity::class.java)

    @Test
    fun testScreenBrightnessMode() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val isAutomatic = context.isScreenBrightnessModeAutomatic()
        try {
            val newAutomaticValue = !isAutomatic
            Assert.assertTrue(context.setScreenBrightnessModeAutomatic(newAutomaticValue))
            val newAutomaticValueFromSettings = context.isScreenBrightnessModeAutomatic()
            Assert.assertEquals(newAutomaticValue, newAutomaticValueFromSettings)
        } finally {
            context.setScreenBrightnessModeAutomatic(isAutomatic)
        }
    }

    @Test
    fun testScreenBrightness() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val screenBrightness = context.getScreenBrightness()
        try {
            val newScreenBrightnessValue = 255 - screenBrightness
            Assert.assertTrue(context.setScreenBrightness(newScreenBrightnessValue))
            val newScreenBrightnessValueFromSettings = context.getScreenBrightness()
            Assert.assertEquals(newScreenBrightnessValue.toLong(), newScreenBrightnessValueFromSettings.toLong())
        } finally {
            context.setScreenBrightness(screenBrightness)
        }
    }

    @Test
    fun testScreenOffTimeout() {
        val context = InstrumentationRegistry.getInstrumentation().getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val screenOffTimeout = context.getScreenOffTimeout()
        try {
            val newScreenOffTimeoutValue = screenOffTimeout + 100
            Assert.assertTrue(context.setScreenOffTimeout(newScreenOffTimeoutValue))
            val newScreenOffTimeoutValueFromSettings = context.getScreenOffTimeout()
            Assert.assertEquals(newScreenOffTimeoutValue.toLong(), newScreenOffTimeoutValueFromSettings.toLong())
        } finally {
            context.setScreenOffTimeout(screenOffTimeout)
        }
    }

    @Test
    fun testAirplaneModeOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) return

        val context = InstrumentationRegistry.getInstrumentation().getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val isAirplaneModeOn = context.isAirplaneModeOn()
        try {
            val newAirplaneModeOnValue = !isAirplaneModeOn
            Assert.assertTrue(context.setAirplaneModeOn(newAirplaneModeOnValue))
            val newAirplaneModeOnValueFromSettings = context.isAirplaneModeOn()
            Assert.assertEquals(newAirplaneModeOnValue, newAirplaneModeOnValueFromSettings)
        } finally {
            context.setAirplaneModeOn(isAirplaneModeOn)
        }
    }

    class RequestPermissionTestActivity : Activity() {

        val countDownLatch = CountDownLatch(1)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (!canWrite()) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(intent, 101)

                Toast.makeText(this, "请允许修改系统设置并关闭此页面", Toast.LENGTH_LONG).show()
                Handler(Looper.getMainLooper()).postDelayed(FinishTask(WeakReference(this)), (10 * 1000).toLong())
            } else {
                finish()
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }

        override fun onDestroy() {
            super.onDestroy()
            countDownLatch.countDown()
        }

        private class FinishTask internal constructor(private val activityWeakReference: WeakReference<Activity>) : Runnable {

            override fun run() {
                val activity = activityWeakReference.get()
                activity?.finish()
            }
        }
    }

    class RequestNotificationPolicyTestActivity : Activity() {

        private val countDownLatch = CountDownLatch(1)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (!isNotificationPolicyAccessGranted()) {
                val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                startActivityForResult(intent, 1)

                Toast.makeText(this, "请允许修改请勿打扰状态并关闭此页面", Toast.LENGTH_LONG).show()
                Handler(Looper.getMainLooper()).postDelayed(FinishTask(WeakReference(this)), (10 * 1000).toLong())
            } else {
                finish()
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }

        override fun onDestroy() {
            super.onDestroy()
            countDownLatch.countDown()
        }

        private class FinishTask internal constructor(private val activityWeakReference: WeakReference<Activity>) : Runnable {

            override fun run() {
                val activity = activityWeakReference.get()
                activity?.finish()
            }
        }
    }
}
