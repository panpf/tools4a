package com.github.panpf.tools4a.dialog.ktx.test

import android.R
import android.app.Dialog
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.panpf.tools4a.dialog.ktx.setClickButtonClosable
import com.github.panpf.tools4a.dialog.ktx.showProgressDialog
import com.github.panpf.tools4a.run.ktx.runOnUiThreadAndWait
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialogxTest {

    @get:Rule
    val mFragmentActivityTestRule = ActivityTestRule(TestFragmentActivity::class.java)

    @Test
    fun testSetClickButtonClosable() {
        runOnUiThreadAndWait {
            val activity = mFragmentActivityTestRule.activity
            Assert.assertTrue(activity.dialog.setClickButtonClosable(true))
            Assert.assertTrue(activity.dialog.setClickButtonClosable(false))
        }
    }

    @Test
    fun testShowProgressDialog() {
        runOnUiThreadAndWait {
            val activity = mFragmentActivityTestRule.activity
            val supportFragment = mFragmentActivityTestRule.activity.supportFragmentManager.findFragmentById(R.id.content)

            Assert.assertNotNull(activity.showProgressDialog("by activity"))
            Assert.assertNotNull(activity.showProgressDialog(R.string.ok))

            Assert.assertNotNull(supportFragment?.showProgressDialog("by supportFragment"))
            Assert.assertNotNull(supportFragment?.showProgressDialog(R.string.yes))
        }
    }

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            @Suppress("RemoveRedundantQualifierName")
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, androidx.fragment.app.Fragment())
                    .commit()
        }

        val dialog: Dialog
            get() = Dialog(this)
    }
}