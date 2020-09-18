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
        launchActivityWithOnUse(TestActivity.createIntent(context), TestActivity::checkParams)
    }

    @Test
    fun testBindNoExtrasActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestNoExtrasActivity.createIntent(context), TestNoExtrasActivity::checkParams)
    }


    @Test
    fun testBindResActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestActivity.createIntent(context), ResTestActivity::checkParams)
    }

    @Test
    fun testUriOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestOnlyUriNoIntentActivity.createIntentWithUri(context), TestOnlyUriNoIntentActivity::checkParamsByUri)
    }

    @Test
    fun testIntentOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestOnlyIntentNoUriActivity.createIntentWithExtras(context), TestOnlyIntentNoUriActivity::checkParamsByIntent)
    }

    @Test
    fun testUriIntentActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestBothIntentUriActivity.createIntentWithUriAndExtras(context), TestBothIntentUriActivity::checkParamsByUriIntent)
    }

    @Test
    fun testNothingActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestNoIntentUriActivity.createIntentWithNothing(context), TestNoIntentUriActivity::checkParamsByNothing)
    }

    @Test
    fun testResUriOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestOnlyUriNoIntentActivity.createIntentWithUri(context), ResTestOnlyUriNoIntentActivity::checkParamsByUri)
    }

    @Test
    fun testResIntentOnlyActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestOnlyIntentNoUriActivity.createIntentWithExtras(context), ResTestOnlyIntentNoUriActivity::checkParamsByIntent)
    }

    @Test
    fun testResUriIntentActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestBothIntentUriActivity.createIntentWithUriAndExtras(context), ResTestBothIntentUriActivity::checkParamsByUriIntent)
    }

    @Test
    fun testResNothingActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestNoIntentUriActivity.createIntentWithNothing(context), ResTestNoIntentUriActivity::checkParamsByNothing)
    }

    @Test
    fun testUriActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestUriActivity.createIntent(context), TestUriActivity::checkParams)
    }

    @Test
    fun testResUriActivity() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestUriActivity.createIntent(context), ResTestUriActivity::checkParams)
    }

    @Test
    fun testBindSupportFragment() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse<TestActivity>(TestActivity.createIntent(context)) { activity ->
            activity.fragment.checkParams()
        }
    }

    @Test
    fun testBindResSupportFragment() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse<ResTestActivity>(ResTestActivity.createIntent(context)) { activity ->
            activity.fragment.checkParams()
        }
    }
}