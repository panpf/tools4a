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

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.args.ktx.test.bind.ResTestSupportFragment18
import com.github.panpf.tools4a.args.ktx.test.bind.TestSupportFragment18
import com.github.panpf.tools4a.test.ktx.launchFragmentInContainerWithOn
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
class ArgsBinderTest18 {

    @Test
    fun testBindSupportFragment18() {
        TestSupportFragment18::class.launchFragmentInContainerWithOn(TestSupportFragment18.createArguments(), TestSupportFragment18::checkParams)
    }

    @Test
    fun testBindResSupportFragment18() {
        val context = InstrumentationRegistry.getInstrumentation().context
        ResTestSupportFragment18::class.launchFragmentInContainerWithOn(ResTestSupportFragment18.createArguments(context), ResTestSupportFragment18::checkParams)
    }
}