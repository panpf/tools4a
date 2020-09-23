/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http:www.apache.org/licenses/LICENSE-2.0
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
import com.github.panpf.tools4a.args.ktx.test.read.*
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArgsxTest {

    @Test
    fun activityIntentArgsTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestActivity.createIntent(context).launchActivityWithOnUse<TestActivity> { activity ->
            activity.checkParams()
            activity.fragment.checkParams()
        }
    }

    @Test
    fun noExtrasActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        NoExtraActivity.createIntent(context).launchActivityWithOnUse(NoExtraActivity::checkParams)
    }

    @Test
    fun resActivityIntentArgsTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestActivity.createIntent(context).launchActivityWithOnUse<ResTestActivity> { activity ->
            activity.checkParams()
            activity.fragment.checkParams()
        }
    }

    @Test
    fun uriOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestOnlyUriNoIntentActivity.createIntentWithUri(context).launchActivityWithOnUse(TestOnlyUriNoIntentActivity::checkParams)
    }

    @Test
    fun intentOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestOnlyIntentNoUriActivity.createIntentWithExtras(context).launchActivityWithOnUse(TestOnlyIntentNoUriActivity::checkParams)
    }

    @Test
    fun bothUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestBothIntentUriActivity.createIntentWithUriAndExtras(context).launchActivityWithOnUse(TestBothIntentUriActivity::checkParams)
    }

    @Test
    fun noUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestNoIntentUriActivity.createIntentWithNothing(context).launchActivityWithOnUse(TestNoIntentUriActivity::checkParams)
    }

    @Test
    fun resUriOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestOnlyUriNoIntentActivity.createIntentWithUri(context).launchActivityWithOnUse(ResTestOnlyUriNoIntentActivity::checkParams)
    }

    @Test
    fun resIntentOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestOnlyIntentNoUriActivity.createIntentWithExtras(context).launchActivityWithOnUse(TestOnlyIntentNoUriActivity::checkParams2)
    }

    @Test
    fun resBothUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestBothIntentUriActivity.createIntentWithUriAndExtras(context).launchActivityWithOnUse(ResTestBothIntentUriActivity::checkParams)
    }

    @Test
    fun resNoUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestNoIntentUriActivity.createIntentWithNothing(context).launchActivityWithOnUse(TestNoIntentUriActivity::checkParams2)
    }

    @Test
    fun uriActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        TestUriActivity.createIntent(context).launchActivityWithOnUse(TestUriActivity::checkParams)
    }

    @Test
    fun resUriActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestUriActivity.createIntent(context).launchActivityWithOnUse(ResTestUriActivity::checkParams)
    }
}