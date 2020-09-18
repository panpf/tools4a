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

package com.github.panpf.tools4a.args.ktx.test.read

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.panpf.tools4a.args.ktx.*
import org.junit.Assert

class TestBothIntentUriActivity : androidx.fragment.app.FragmentActivity() {

    companion object {
        fun createIntentWithUriAndExtras(context: Context): Intent {
            val params = StringBuilder()
                    .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                    .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                    .append("&intIntentUriOrDefault=").append(3)
                    .append("&longIntentUriOrDefault=").append(4L)
                    .append("&floatIntentUriOrDefault=").append(5.toFloat())
                    .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                    .append("&booleanIntentUriOrDefault=").append(true)
                    .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                    .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                    .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")
                    .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                    .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                    .append("&intUriIntentOrDefault=").append(13)
                    .append("&longUriIntentOrDefault=").append(14L)
                    .append("&floatUriIntentOrDefault=").append(15.toFloat())
                    .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                    .append("&booleanUriIntentOrDefault=").append(true)
                    .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                    .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                    .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                    .toString()

            val uri = Uri.parse("tools4a://tools4a.com/panpf/tools4a/uri/intent?$params")

            val starter = Intent(context, TestBothIntentUriActivity::class.java)
            starter.action = Intent.ACTION_VIEW
            starter.data = uri
            starter.putExtra("byteIntentUriOrDefault", (-1).toByte())
            starter.putExtra("shortIntentUriOrDefault", (-2).toShort())
            starter.putExtra("intIntentUriOrDefault", -3)
            starter.putExtra("longIntentUriOrDefault", -4L)
            starter.putExtra("floatIntentUriOrDefault", -5f)
            starter.putExtra("doubleIntentUriOrDefault", -6.0)
            starter.putExtra("booleanIntentUriOrDefault", true)
            starter.putExtra("stringIntentUriRequired", "stringIntentRequired")
            starter.putExtra("stringIntentUriOptional", "stringIntentOptional")
            starter.putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")

            starter.putExtra("byteUriIntentOrDefault", (-11).toByte())
            starter.putExtra("shortUriIntentOrDefault", (-12).toShort())
            starter.putExtra("intUriIntentOrDefault", -13)
            starter.putExtra("longUriIntentOrDefault", -14L)
            starter.putExtra("floatUriIntentOrDefault", -15f)
            starter.putExtra("doubleUriIntentOrDefault", -16.0)
            starter.putExtra("booleanUriIntentOrDefault", true)
            starter.putExtra("stringUriIntentRequired", "stringUriIntentRequired")
            starter.putExtra("stringUriIntentOptional", "stringUriIntentOptional")
            starter.putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault")
            return starter
        }
    }

    fun checkParams() {
        val activity = this

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr("intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr("longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr("floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow("stringIntentUriRequired")
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull("stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull("stringIntentUriOptionalErrKey")

        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr("byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr("shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr("intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr("longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr("floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr("doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr("booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow("stringUriIntentRequired")
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr("stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull("stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull("stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }
}