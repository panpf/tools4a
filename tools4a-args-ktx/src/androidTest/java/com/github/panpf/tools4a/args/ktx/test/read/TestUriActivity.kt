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

class TestUriActivity : androidx.fragment.app.FragmentActivity() {
    companion object {

        fun createIntent(context: Context): Intent {
            val params = StringBuilder()
                    .append("byteUriRequired=").append(1.toByte().toInt())
                    .append("&byteUriOptional=").append((-1).toByte().toInt())
                    .append("&byteUriOrDefault=").append(2.toByte().toInt())

                    .append("&shortUriRequired=").append(3.toShort().toInt())
                    .append("&shortUriOptional=").append((-3).toShort().toInt())
                    .append("&shortUriOrDefault=").append(4.toShort().toInt())

                    .append("&intUriRequired=").append(5)
                    .append("&intUriOptional=").append(-5)
                    .append("&intUriOrDefault=").append(6)

                    .append("&longUriRequired=").append(7L)
                    .append("&longUriOptional=").append(-7L)
                    .append("&longUriOrDefault=").append(8L)

                    .append("&floatUriRequired=").append(9f)
                    .append("&floatUriOptional=").append(-9f)
                    .append("&floatUriOrDefault=").append(10f)

                    .append("&doubleUriRequired=").append(11.0)
                    .append("&doubleUriOptional=").append(-11.0)
                    .append("&doubleUriOrDefault=").append(12.0)

                    .append("&booleanUriRequired=").append(true)
                    .append("&booleanUriOptional=").append(true)
                    .append("&booleanUriOrDefault=").append(false)

                    .append("&stringUriRequired=").append("stringUriRequired")
                    .append("&stringUriOptional=").append("stringUriOptional")
                    .append("&stringUriOrDefault=").append("stringUriOrDefault")
                    .toString()
            return Intent(context, TestUriActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("tools4a://tools4a.com/panpf/tools4a?$params")
            }
        }
    }

    fun checkParams() {
        val activity = this

        val byteUriRequired = activity.readByteUriArgOrThrow("byteUriRequired")
        val byteUriOptional = activity.readByteUriArgOrNull("byteUriOptional")
        val byteUriOptionalErrKey = activity.readByteUriArgOrNull("byteUriOptionalErrKey")
        val byteUriOrDefault = activity.readByteUriArgOr("byteUriOrDefault", 0.toByte())
        val byteUriOrDefaultErrKey = activity.readByteUriArgOr("byteUriOrDefaultErrKey", 0.toByte())

        val shortUriRequired = activity.readShortUriArgOrThrow("shortUriRequired")
        val shortUriOptional = activity.readShortUriArgOrNull("shortUriOptional")
        val shortUriOptionalErrKey = activity.readShortUriArgOrNull("shortUriOptionalErrKey")
        val shortUriOrDefault = activity.readShortUriArgOr("shortUriOrDefault", 0.toShort())
        val shortUriOrDefaultErrKey = activity.readShortUriArgOr("shortUriOrDefaultErrKey", 0.toShort())

        val intUriRequired = activity.readIntUriArgOrThrow("intUriRequired")
        val intUriOptional = activity.readIntUriArgOrNull("intUriOptional")
        val intUriOptionalErrKey = activity.readIntUriArgOrNull("intUriOptionalErrKey")
        val intUriOrDefault = activity.readIntUriArgOr("intUriOrDefault", 0)
        val intUriOrDefaultErrKey = activity.readIntUriArgOr("intUriOrDefaultErrKey", 0)

        val longUriRequired = activity.readLongUriArgOrThrow("longUriRequired")
        val longUriOptional = activity.readLongUriArgOrNull("longUriOptional")
        val longUriOptionalErrKey = activity.readLongUriArgOrNull("longUriOptionalErrKey")
        val longUriOrDefault = activity.readLongUriArgOr("longUriOrDefault", 0L)
        val longUriOrDefaultErrKey = activity.readLongUriArgOr("longUriOrDefaultErrKey", 0L)

        val floatUriRequired = activity.readFloatUriArgOrThrow("floatUriRequired")
        val floatUriOptional = activity.readFloatUriArgOrNull("floatUriOptional")
        val floatUriOptionalErrKey = activity.readFloatUriArgOrNull("floatUriOptionalErrKey")
        val floatUriOrDefault = activity.readFloatUriArgOr("floatUriOrDefault", 0f)
        val floatUriOrDefaultErrKey = activity.readFloatUriArgOr("floatUriOrDefaultErrKey", -1f)

        val doubleUriRequired = activity.readDoubleUriArgOrThrow("doubleUriRequired")
        val doubleUriOptional = activity.readDoubleUriArgOrNull("doubleUriOptional")
        val doubleUriOptionalErrKey = activity.readDoubleUriArgOrNull("doubleUriOptionalErrKey")
        val doubleUriOrDefault = activity.readDoubleUriArgOr("doubleUriOrDefault", 1.0)
        val doubleUriOrDefaultErrKey = activity.readDoubleUriArgOr("doubleUriOrDefaultErrKey", -1.0)

        val booleanUriRequired = activity.readBooleanUriArgOrThrow("booleanUriRequired")
        val booleanUriOptional = activity.readBooleanUriArgOrNull("booleanUriOptional")
        val booleanUriOptionalErrKey = activity.readBooleanUriArgOrNull("booleanUriOptionalErrKey")
        val booleanUriOrDefault = activity.readBooleanUriArgOr("booleanUriOrDefault", true)
        val booleanUriOrDefaultErrKey = activity.readBooleanUriArgOr("booleanUriOrDefaultErrKey", false)

        val stringUriRequired = activity.readStringUriArgOrThrow("stringUriRequired")
        val stringUriOptional = activity.readStringUriArgOrNull("stringUriOptional")
        val stringUriOptionalErrKey = activity.readStringUriArgOrNull("stringUriOptionalErrKey")
        val stringUriOrDefault = activity.readStringUriArgOr("stringUriOrDefault", "")
        val stringUriOrDefaultErrKey = activity.readStringUriArgOr("stringUriOrDefaultErrKey", "stringUriOrDefaultErrKey")

        Assert.assertNull(byteUriOptionalErrKey)
        Assert.assertNull(shortUriOptionalErrKey)
        Assert.assertNull(intUriOptionalErrKey)
        Assert.assertNull(longUriOptionalErrKey)
        Assert.assertNull(floatUriOptionalErrKey)
        Assert.assertNull(doubleUriOptionalErrKey)
        Assert.assertNull(booleanUriOptionalErrKey)
        Assert.assertNull(stringUriOptionalErrKey)

        Assert.assertTrue(byteUriRequired == 1.toByte())
        Assert.assertTrue(byteUriOptional == (-1).toByte())
        Assert.assertTrue(byteUriOrDefault == 2.toByte())
        Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

        Assert.assertTrue(shortUriRequired == 3.toShort())
        Assert.assertTrue(shortUriOptional == (-3).toShort())
        Assert.assertTrue(shortUriOrDefault == 4.toShort())
        Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

        Assert.assertTrue(intUriRequired == 5)
        Assert.assertTrue(intUriOptional == -5)
        Assert.assertTrue(intUriOrDefault == 6)
        Assert.assertTrue(intUriOrDefaultErrKey == 0)

        Assert.assertTrue(longUriRequired == 7L)
        Assert.assertTrue(longUriOptional == -7L)
        Assert.assertTrue(longUriOrDefault == 8L)
        Assert.assertTrue(longUriOrDefaultErrKey == 0L)

        Assert.assertTrue(floatUriRequired == 9f)
        Assert.assertTrue(floatUriOptional == -9f)
        Assert.assertTrue(floatUriOrDefault == 10f)
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

        Assert.assertTrue(doubleUriRequired == 11.0)
        Assert.assertTrue(doubleUriOptional == -11.0)
        Assert.assertTrue(doubleUriOrDefault == 12.0)
        Assert.assertTrue(doubleUriOrDefaultErrKey == -1.0)

        Assert.assertTrue(booleanUriRequired)
        Assert.assertTrue(booleanUriOptional!!)
        Assert.assertTrue(!booleanUriOrDefault)
        Assert.assertTrue(!booleanUriOrDefaultErrKey)

        Assert.assertTrue(stringUriRequired == "stringUriRequired")
        Assert.assertTrue(stringUriOptional == "stringUriOptional")
        Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
        Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")
    }
}