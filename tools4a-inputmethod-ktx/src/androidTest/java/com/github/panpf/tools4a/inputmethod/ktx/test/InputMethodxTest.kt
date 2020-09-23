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

package com.github.panpf.tools4a.inputmethod.ktx.test

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Selection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.tools4a.inputmethod.ktx.*
import com.github.panpf.tools4a.run.ktx.runOnMainThread
import com.github.panpf.tools4a.test.ktx.getActivitySync
import com.github.panpf.tools4a.test.ktx.launchActivityWithUse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputMethodxTest {

    @Test
    fun testShowSoftInput() {
        TestActivity::class.launchActivityWithUse { scenario ->
            val activity = scenario.getActivitySync()
            val originEditText = activity.supportFragmentEditTxt

            // ensure hide
            if (activity.isSoftInputShowing()) {
                runOnMainThread { activity.hideSoftInput() }
                Thread.sleep(500)
                Assert.assertFalse(activity.isSoftInputShowing())
            }

            // show
            runOnMainThread { originEditText.showSoftInput() }
            Thread.sleep(500)
            Assert.assertTrue(activity.isSoftInputShowing())
        }
    }

    @Test
    fun testDelayShowSoftInput() {
        TestActivity::class.launchActivityWithUse { scenario ->
            val activity = scenario.getActivitySync()
            val supportEditText = activity.supportFragmentEditTxt

            // ensure hide
            if (activity.isSoftInputShowing()) {
                runOnMainThread { activity.hideSoftInput() }
                Thread.sleep(500)
                Assert.assertFalse(activity.isSoftInputShowing())
            }

            // show
            runOnMainThread { supportEditText.delayShowSoftInput() }
            Thread.sleep(500)
            Assert.assertTrue(activity.isSoftInputShowing())

            // hide
            runOnMainThread { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())

            // show
            runOnMainThread { supportEditText.delayShowSoftInput(500) }
            Thread.sleep((500 + 500).toLong())
            Assert.assertTrue(activity.isSoftInputShowing())
        }
    }

    @Test
    fun testHideSoftInput() {
        TestActivity::class.launchActivityWithUse { scenario ->
            val activity = scenario.getActivitySync()
            val supportEditText = activity.supportFragmentEditTxt

            // ensure hide
            if (activity.isSoftInputShowing()) {
                runOnMainThread { activity.hideSoftInput() }
                Thread.sleep(500)
                Assert.assertFalse(activity.isSoftInputShowing())
            }

            // show
            runOnMainThread { supportEditText.showSoftInput() }
            Thread.sleep(500)
            Assert.assertTrue(activity.isSoftInputShowing())

            // hide
            runOnMainThread { activity.supportFragment.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())

            // show
            runOnMainThread { supportEditText.showSoftInput() }
            Thread.sleep(500)
            Assert.assertTrue(activity.isSoftInputShowing())

            // hide
            runOnMainThread { supportEditText.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }
    }

    @Test
    fun testMoveCursor() {
        TestActivity::class.launchActivityWithUse { scenario ->
            val activity = scenario.getActivitySync()
            val originEditText = activity.supportFragmentEditTxt

            runOnMainThread { originEditText.moveCursorToEnd() }
            Thread.sleep(100)
            Assert.assertEquals(originEditText.length().toLong(), Selection.getSelectionEnd(originEditText.text).toLong())

            runOnMainThread { originEditText.moveCursorToStart() }
            Thread.sleep(100)
            Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.text).toLong())

            runOnMainThread { originEditText.moveCursorTo(originEditText.length() / 2) }
            Thread.sleep(100)
            Assert.assertEquals((originEditText.length() / 2).toLong(), Selection.getSelectionEnd(originEditText.text).toLong())
        }
    }

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val supportFragment: androidx.fragment.app.Fragment
            get() = supportFragmentManager.findFragmentById(R.id.multiFrameAt_frame2)!!

        val supportFragmentEditTxt: EditText
            get() = supportFragment.view as EditText

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            setContentView(R.layout.at_multi_frame)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, EditSupportFragment())
                    .commit()
        }
    }

    class EditSupportFragment : androidx.fragment.app.Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            val editText = EditText(activity)
            editText.setText("0123456789")
            return editText
        }
    }
}
