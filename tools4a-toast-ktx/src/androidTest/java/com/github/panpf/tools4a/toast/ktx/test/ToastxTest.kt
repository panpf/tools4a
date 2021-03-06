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

package com.github.panpf.tools4a.toast.ktx.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
import com.github.panpf.tools4a.toast.ktx.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastxTest {

    @Test
    fun testContextToast() {
        TestFragmentActivity::class.launchActivityWithOnUse { activity ->
            activity.showLongToast("今天是2018年10月18号")
            activity.showLongToastWithFormat("今天是%d年%d月%d号", 2018, 10, 18)
            activity.showLongToast(R.string.toast_test)
            activity.showLongToastWithFormat(R.string.toast_test_tp, 2018, 10, 18)

            activity.showShortToast("今天是2018年10月18号")
            activity.showShortToastWithFormat("今天是%d年%d月%d号", 2018, 10, 18)
            activity.showShortToast(R.string.toast_test)
            activity.showShortToastWithFormat(R.string.toast_test_tp, 2018, 10, 18)
        }
    }

    @Test
    fun testSupportFragmentToast() {
        TestFragmentActivity::class.launchActivityWithOnUse { activity ->
            val fragment = activity.fragment

            fragment.showLongToast("今天是2018年10月18号")
            fragment.showLongToastWithFormat("今天是%d年%d月%d号", 2018, 10, 18)
            fragment.showLongToast(R.string.toast_test)
            fragment.showLongToastWithFormat(R.string.toast_test_tp, 2018, 10, 18)

            fragment.showShortToast("今天是2018年10月18号")
            fragment.showShortToastWithFormat("今天是%d年%d月%d号", 2018, 10, 18)
            fragment.showShortToast(R.string.toast_test)
            fragment.showShortToastWithFormat(R.string.toast_test_tp, 2018, 10, 18)
        }
    }

    @Test
    fun testViewToast() {
        TestFragmentActivity::class.launchActivityWithOnUse { activity ->
            val view = activity.view

            view.showLongToast("今天是2018年10月18号")
            view.showLongToastWithFormat("今天是%d年%d月%d号", 2018, 10, 18)
            view.showLongToast(R.string.toast_test)
            view.showLongToastWithFormat(R.string.toast_test_tp, 2018, 10, 18)

            view.showShortToast("今天是2018年10月18号")
            view.showShortToastWithFormat("今天是%d年%d月%d号", 2018, 10, 18)
            view.showShortToast(R.string.toast_test)
            view.showShortToastWithFormat(R.string.toast_test_tp, 2018, 10, 18)
        }
    }

    @Test
    fun testWithViewToast() {
        @Suppress("DEPRECATION")
        LayoutInflater.from(InstrumentationRegistry.getInstrumentation().context).inflate(R.layout.view_toast, null, false).showLongToastWithSelf()
        @Suppress("DEPRECATION")
        LayoutInflater.from(InstrumentationRegistry.getInstrumentation().context).inflate(R.layout.view_toast, null, false).showShortToastWithSelf()
    }

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {

        val fragment: androidx.fragment.app.Fragment
            get() = supportFragmentManager.findFragmentById(android.R.id.content)!!

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            this.supportFragmentManager.beginTransaction().replace(android.R.id.content, androidx.fragment.app.Fragment()).commit()
        }
    }
}