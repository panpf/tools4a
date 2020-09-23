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

package com.github.panpf.tools4a.args.ktx.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.args.ktx.test.bind.*
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArgsBinderTest {

    @Test
    fun testBindSupportActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestActivity.createIntent(context).launchActivityWithOnUse(TestActivity::checkParams)
    }

    @Test
    fun testBindNoExtrasActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestNoExtrasActivity.createIntent(context).launchActivityWithOnUse(TestNoExtrasActivity::checkParams)
    }


    @Test
    fun testBindResActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestActivity.createIntent(context).launchActivityWithOnUse(ResTestActivity::checkParams)
    }

    @Test
    fun testUriOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestOnlyUriNoIntentActivity.createIntentWithUri(context).launchActivityWithOnUse(TestOnlyUriNoIntentActivity::checkParamsByUri)
    }

    @Test
    fun testIntentOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestOnlyIntentNoUriActivity.createIntentWithExtras(context).launchActivityWithOnUse(TestOnlyIntentNoUriActivity::checkParamsByIntent)
    }

    @Test
    fun testUriIntentActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestBothIntentUriActivity.createIntentWithUriAndExtras(context).launchActivityWithOnUse(TestBothIntentUriActivity::checkParamsByUriIntent)
    }

    @Test
    fun testNothingActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestNoIntentUriActivity.createIntentWithNothing(context).launchActivityWithOnUse(TestNoIntentUriActivity::checkParamsByNothing)
    }

    @Test
    fun testResUriOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestOnlyUriNoIntentActivity.createIntentWithUri(context).launchActivityWithOnUse(ResTestOnlyUriNoIntentActivity::checkParamsByUri)
    }

    @Test
    fun testResIntentOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestOnlyIntentNoUriActivity.createIntentWithExtras(context).launchActivityWithOnUse(ResTestOnlyIntentNoUriActivity::checkParamsByIntent)
    }

    @Test
    fun testResUriIntentActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestBothIntentUriActivity.createIntentWithUriAndExtras(context).launchActivityWithOnUse(ResTestBothIntentUriActivity::checkParamsByUriIntent)
    }

    @Test
    fun testResNothingActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestNoIntentUriActivity.createIntentWithNothing(context).launchActivityWithOnUse(ResTestNoIntentUriActivity::checkParamsByNothing)
    }

    @Test
    fun testUriActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestUriActivity.createIntent(context).launchActivityWithOnUse(TestUriActivity::checkParams)
    }

    @Test
    fun testResUriActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestUriActivity.createIntent(context).launchActivityWithOnUse(ResTestUriActivity::checkParams)
    }

    @Test
    fun testBindSupportFragment() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestActivity.createIntent(context).launchActivityWithOnUse<TestActivity> { activity ->
            activity.fragment.checkParams()
        }
    }

    @Test
    fun testBindResSupportFragment() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestActivity.createIntent(context).launchActivityWithOnUse<ResTestActivity> { activity ->
            activity.fragment.checkParams()
        }
    }
}