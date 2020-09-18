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
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestUriActivity : androidx.fragment.app.FragmentActivity() {

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
            return Intent(context, ResTestUriActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("tools4a://tools4a.com/panpf/tools4a/res?$params")
            }
        }
    }

    fun checkParams() {
        val activity = this

        val byteUriRequired = activity.readByteUriArgOrThrow(R.string.byte_uri_required)
        val byteUriOptional = activity.readByteUriArgOrNull(R.string.byte_uri_optional)
        val byteUriOptionalErrKey = activity.readByteUriArgOrNull(R.string.not_exist_key)
        val byteUriOrDefault = activity.readByteUriArgOr(R.string.byte_uri_or_default, 0.toByte())
        val byteUriOrDefaultErrKey = activity.readByteUriArgOr(R.string.not_exist_key, 0.toByte())

        val shortUriRequired = activity.readShortUriArgOrThrow(R.string.short_uri_required)
        val shortUriOptional = activity.readShortUriArgOrNull(R.string.short_uri_optional)
        val shortUriOptionalErrKey = activity.readShortUriArgOrNull(R.string.not_exist_key)
        val shortUriOrDefault = activity.readShortUriArgOr(R.string.short_uri_or_default, 0.toShort())
        val shortUriOrDefaultErrKey = activity.readShortUriArgOr(R.string.not_exist_key, 0.toShort())

        val intUriRequired = activity.readIntUriArgOrThrow(R.string.int_uri_required)
        val intUriOptional = activity.readIntUriArgOrNull(R.string.int_uri_optional)
        val intUriOptionalErrKey = activity.readIntUriArgOrNull(R.string.not_exist_key)
        val intUriOrDefault = activity.readIntUriArgOr(R.string.int_uri_or_default, 0)
        val intUriOrDefaultErrKey = activity.readIntUriArgOr(R.string.not_exist_key, 0)

        val longUriRequired = activity.readLongUriArgOrThrow(R.string.long_uri_required)
        val longUriOptional = activity.readLongUriArgOrNull(R.string.long_uri_optional)
        val longUriOptionalErrKey = activity.readLongUriArgOrNull(R.string.not_exist_key)
        val longUriOrDefault = activity.readLongUriArgOr(R.string.long_uri_or_default, 0L)
        val longUriOrDefaultErrKey = activity.readLongUriArgOr(R.string.not_exist_key, 0L)

        val floatUriRequired = activity.readFloatUriArgOrThrow(R.string.float_uri_required)
        val floatUriOptional = activity.readFloatUriArgOrNull(R.string.float_uri_optional)
        val floatUriOptionalErrKey = activity.readFloatUriArgOrNull(R.string.not_exist_key)
        val floatUriOrDefault = activity.readFloatUriArgOr(R.string.float_uri_or_default, 0f)
        val floatUriOrDefaultErrKey = activity.readFloatUriArgOr(R.string.not_exist_key, -1f)

        val doubleUriRequired = activity.readDoubleUriArgOrThrow(R.string.double_uri_required)
        val doubleUriOptional = activity.readDoubleUriArgOrNull(R.string.double_uri_optional)
        val doubleUriOptionalErrKey = activity.readDoubleUriArgOrNull(R.string.not_exist_key)
        val doubleUriOrDefault = activity.readDoubleUriArgOr(R.string.double_uri_or_default, 1.0)
        val doubleUriOrDefaultErrKey = activity.readDoubleUriArgOr(R.string.not_exist_key, -1.0)

        val booleanUriRequired = activity.readBooleanUriArgOrThrow(R.string.boolean_uri_required)
        val booleanUriOptional = activity.readBooleanUriArgOrNull(R.string.boolean_uri_optional)
        val booleanUriOptionalErrKey = activity.readBooleanUriArgOrNull(R.string.not_exist_key)
        val booleanUriOrDefault = activity.readBooleanUriArgOr(R.string.boolean_uri_or_default, true)
        val booleanUriOrDefaultErrKey = activity.readBooleanUriArgOr(R.string.not_exist_key, false)

        val stringUriRequired = activity.readStringUriArgOrThrow(R.string.string_uri_required)
        val stringUriOptional = activity.readStringUriArgOrNull(R.string.string_uri_optional)
        val stringUriOptionalErrKey = activity.readStringUriArgOrNull(R.string.not_exist_key)
        val stringUriOrDefault = activity.readStringUriArgOr(R.string.string_uri_or_default, "")
        val stringUriOrDefaultErrKey = activity.readStringUriArgOr(R.string.not_exist_key, "stringUriOrDefaultErrKey")

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