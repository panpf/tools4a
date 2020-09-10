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

@file:Suppress("DEPRECATION")

package com.github.panpf.tools4a.inputmethod.ktx.test

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Selection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.inputmethod.ktx.*
import com.github.panpf.tools4a.run.ktx.runOnUiThread
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputMethodxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    @Throws(InterruptedException::class)
    fun testShowSoftInput() {
        val activity = activityRule.activity
        val originEditText = activity.supportFragmentEditTxt

        // ensure hide
        if (activity.isSoftInputShowing()) {
            runOnUiThread { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }

        // show
        runOnUiThread { originEditText.showSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDelayShowSoftInput() {
        val activity = activityRule.activity
        val supportEditText = activity.supportFragmentEditTxt

        // ensure hide
        if (activity.isSoftInputShowing()) {
            runOnUiThread { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }

        // show
        runOnUiThread { supportEditText.delayShowSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())

        // hide
        runOnUiThread { activity.hideSoftInput() }
        Thread.sleep(500)
        Assert.assertFalse(activity.isSoftInputShowing())

        // show
        runOnUiThread { supportEditText.delayShowSoftInput(500) }
        Thread.sleep((500 + 500).toLong())
        Assert.assertTrue(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testHideSoftInput() {
        val activity = activityRule.activity
        val supportEditText = activity.supportFragmentEditTxt

        // ensure hide
        if (activity.isSoftInputShowing()) {
            runOnUiThread { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }

        // show
        runOnUiThread { supportEditText.showSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())

        // hide
        runOnUiThread { activity.supportFragment.hideSoftInput() }
        Thread.sleep(500)
        Assert.assertFalse(activity.isSoftInputShowing())

        // show
        runOnUiThread { supportEditText.showSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())

        // hide
        runOnUiThread { supportEditText.hideSoftInput() }
        Thread.sleep(500)
        Assert.assertFalse(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testMoveCursor() {
        val activity = activityRule.activity
        val originEditText = activity.supportFragmentEditTxt

        runOnUiThread { originEditText.moveCursorToEnd() }
        Thread.sleep(100)
        Assert.assertEquals(originEditText.length().toLong(), Selection.getSelectionEnd(originEditText.text).toLong())

        runOnUiThread { originEditText.moveCursorToStart() }
        Thread.sleep(100)
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.text).toLong())

        runOnUiThread { originEditText.moveCursorTo(originEditText.length() / 2) }
        Thread.sleep(100)
        Assert.assertEquals((originEditText.length() / 2).toLong(), Selection.getSelectionEnd(originEditText.text).toLong())
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
