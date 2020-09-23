package com.github.panpf.tools4a.dialog.ktx.test

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.tools4a.dialog.ktx.setClickButtonClosable
import com.github.panpf.tools4a.dialog.ktx.showProgressDialog
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialogxTest {

    @Test
    fun testSetClickButtonClosable() {
        TestFragmentActivity::class.launchActivityWithOnUse { activity ->
            Assert.assertTrue(activity.dialog.setClickButtonClosable(true))
            Assert.assertTrue(activity.dialog.setClickButtonClosable(false))
        }
    }

    @Test
    fun testShowProgressDialog() {
        TestFragmentActivity::class.launchActivityWithOnUse { activity ->
            Assert.assertNotNull(activity.showProgressDialog("by activity"))
            Assert.assertNotNull(activity.showProgressDialog(android.R.string.ok))

            val fragment = activity.fragment
            Assert.assertNotNull(fragment.showProgressDialog("by supportFragment"))
            @Suppress("DEPRECATION")
            Assert.assertNotNull(fragment.showProgressDialog(android.R.string.yes))
        }
    }

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {

        val fragment: Fragment
            get() = supportFragmentManager.findFragmentById(android.R.id.content)!!

        val dialog: Dialog
            get() = Dialog(this)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            @Suppress("RemoveRedundantQualifierName")
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, androidx.fragment.app.Fragment())
                    .commit()
        }
    }
}