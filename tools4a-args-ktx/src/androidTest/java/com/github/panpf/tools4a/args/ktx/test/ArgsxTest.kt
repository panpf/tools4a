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
        launchActivityWithOnUse<TestActivity>(TestActivity.createIntent(context)) { activity ->
            activity.checkParams()
            activity.fragment.checkParams()
        }
    }

    @Test
    fun noExtrasActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(NoExtraActivity.createIntent(context), NoExtraActivity::checkParams)
    }

    @Test
    fun resActivityIntentArgsTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse<ResTestActivity>(ResTestActivity.createIntent(context)) { activity ->
            activity.checkParams()
            activity.fragment.checkParams()
        }
    }

    @Test
    fun uriOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestOnlyUriNoIntentActivity.createIntentWithUri(context), TestOnlyUriNoIntentActivity::checkParams)
    }

    @Test
    fun intentOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestOnlyIntentNoUriActivity.createIntentWithExtras(context), TestOnlyIntentNoUriActivity::checkParams)
    }

    @Test
    fun bothUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestBothIntentUriActivity.createIntentWithUriAndExtras(context), TestBothIntentUriActivity::checkParams)
    }

    @Test
    fun noUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestNoIntentUriActivity.createIntentWithNothing(context), TestNoIntentUriActivity::checkParams)
    }

    @Test
    fun resUriOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestOnlyUriNoIntentActivity.createIntentWithUri(context), ResTestOnlyUriNoIntentActivity::checkParams)
    }

    @Test
    fun resIntentOnlyActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestOnlyIntentNoUriActivity.createIntentWithExtras(context), TestOnlyIntentNoUriActivity::checkParams2)
    }

    @Test
    fun resBothUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestBothIntentUriActivity.createIntentWithUriAndExtras(context), ResTestBothIntentUriActivity::checkParams)
    }

    @Test
    fun resNoUriIntentActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestNoIntentUriActivity.createIntentWithNothing(context), TestNoIntentUriActivity::checkParams2)
    }

    @Test
    fun uriActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(TestUriActivity.createIntent(context), TestUriActivity::checkParams)
    }

    @Test
    fun resUriActivityTest() {
        val context = InstrumentationRegistry.getInstrumentation().context
        launchActivityWithOnUse(ResTestUriActivity.createIntent(context), ResTestUriActivity::checkParams)
    }
}