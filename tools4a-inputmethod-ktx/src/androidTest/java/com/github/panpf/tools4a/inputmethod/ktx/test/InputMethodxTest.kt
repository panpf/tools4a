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

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Selection
import android.view.View
import android.widget.EditText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.inputmethod.ktx.*
import com.github.panpf.tools4a.run.ktx.runInUI
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
        val editText = activity.editTxt

        // ensure hide
        if (activity.isSoftInputShowing()) {
            runInUI { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }

        // show
        runInUI { editText.showSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDelayShowSoftInput() {
        val activity = activityRule.activity
        val editText = activity.editTxt

        // ensure hide
        if (activity.isSoftInputShowing()) {
            runInUI { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }

        // show
        runInUI { editText.delayShowSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())

        // hide
        runInUI { activity.hideSoftInput() }
        Thread.sleep(500)
        Assert.assertFalse(activity.isSoftInputShowing())

        // show
        runInUI { editText.delayShowSoftInput(500) }
        Thread.sleep((500 + 500).toLong())
        Assert.assertTrue(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testHideSoftInput() {
        val activity = activityRule.activity
        val editText = activity.editTxt

        // ensure hide
        if (activity.isSoftInputShowing()) {
            runInUI { activity.hideSoftInput() }
            Thread.sleep(500)
            Assert.assertFalse(activity.isSoftInputShowing())
        }

        // show
        runInUI { editText.showSoftInput() }
        Thread.sleep(500)
        Assert.assertTrue(activity.isSoftInputShowing())

        // hide
        runInUI { editText.hideSoftInput() }
        Thread.sleep(500)
        Assert.assertFalse(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testMoveCursor() {
        val activity = activityRule.activity
        val editText = activity.editTxt

        runInUI { editText.moveCursorToEnd() }
        Thread.sleep(100)
        Assert.assertEquals(editText.length().toLong(), Selection.getSelectionEnd(editText.text).toLong())

        runInUI { editText.moveCursorToStart() }
        Thread.sleep(100)
        Assert.assertEquals(0, Selection.getSelectionEnd(editText.text).toLong())

        runInUI { editText.moveCursorTo(editText.length() / 2) }
        Thread.sleep(100)
        Assert.assertEquals((editText.length() / 2).toLong(), Selection.getSelectionEnd(editText.text).toLong())
    }

    class TestActivity : Activity() {

        lateinit var editTxt: EditText

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            editTxt = EditText(this)
            editTxt.setText("0123456789")
            setContentView(editTxt)
        }
    }
}
