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

package com.github.panpf.tools4a.args.ktx.test.bind

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.panpf.tools4a.args.ktx.*
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestUriActivity : androidx.fragment.app.FragmentActivity() {

    private val byteUriRequired by bindByteUriArgOrThrow(R.string.byte_uri_required)
    private val byteUriOptional by bindByteUriArgOrNull(R.string.byte_uri_optional)
    private val byteUriOrDefault by bindByteUriArgOr(R.string.byte_uri_or_default)
    private val byteUriOrDefaultErrKey by bindByteUriArgOr(R.string.not_exist_key)

    private val shortUriRequired by bindShortUriArgOrThrow(R.string.short_uri_required)
    private val shortUriOptional by bindShortUriArgOrNull(R.string.short_uri_optional)
    private val shortUriOrDefault by bindShortUriArgOr(R.string.short_uri_or_default)
    private val shortUriOrDefaultErrKey by bindShortUriArgOr(R.string.not_exist_key)

    private val intUriRequired by bindIntUriArgOrThrow(R.string.int_uri_required)
    private val intUriOptional by bindIntUriArgOrNull(R.string.int_uri_optional)
    private val intUriOrDefault by bindIntUriArgOr(R.string.int_uri_or_default)
    private val intUriOrDefaultErrKey by bindIntUriArgOr(R.string.not_exist_key)

    private val longUriRequired by bindLongUriArgOrThrow(R.string.long_uri_required)
    private val longUriOptional by bindLongUriArgOrNull(R.string.long_uri_optional)
    private val longUriOrDefault by bindLongUriArgOr(R.string.long_uri_or_default)
    private val longUriOrDefaultErrKey by bindLongUriArgOr(R.string.not_exist_key)

    private val floatUriRequired by bindFloatUriArgOrThrow(R.string.float_uri_required)
    private val floatUriOptional by bindFloatUriArgOrNull(R.string.float_uri_optional)
    private val floatUriOrDefault by bindFloatUriArgOr(R.string.float_uri_or_default, 0f)
    private val floatUriOrDefaultErrKey by bindFloatUriArgOr(R.string.not_exist_key, -1f)

    private val doubleUriRequired by bindDoubleUriArgOrThrow(R.string.double_uri_required)
    private val doubleUriOptional by bindDoubleUriArgOrNull(R.string.double_uri_optional)
    private val doubleUriOrDefault by bindDoubleUriArgOr(R.string.double_uri_or_default, 1.toDouble())
    private val doubleUriOrDefaultErrKey by bindDoubleUriArgOr(R.string.not_exist_key, (-1).toDouble())

    private val booleanUriRequired by bindBooleanUriArgOrThrow(R.string.boolean_uri_required)
    private val booleanUriOptional by bindBooleanUriArgOrNull(R.string.boolean_uri_optional)
    private val booleanUriOrDefault by bindBooleanUriArgOr(R.string.boolean_uri_or_default, true)
    private val booleanUriOrDefaultErrKey by bindBooleanUriArgOr(R.string.not_exist_key, false)

    private val stringUriRequired by bindStringUriArgOrThrow(R.string.string_uri_required)
    private val stringUriOptional by bindStringUriArgOrNull(R.string.string_uri_optional)
    private val stringUriOrDefault by bindStringUriArgOr(R.string.string_uri_or_default, "")
    private val stringUriOrDefaultErrKey by bindStringUriArgOr(R.string.not_exist_key, "stringUriOrDefaultErrKey")

    private val byteUriOptionalErrKey by bindByteUriArgOrNull(R.string.not_exist_key)
    private val shortUriOptionalErrKey by bindShortUriArgOrNull(R.string.not_exist_key)
    private val intUriOptionalErrKey by bindIntUriArgOrNull(R.string.not_exist_key)
    private val longUriOptionalErrKey by bindLongUriArgOrNull(R.string.not_exist_key)
    private val floatUriOptionalErrKey by bindFloatUriArgOrNull(R.string.not_exist_key)
    private val doubleUriOptionalErrKey by bindDoubleUriArgOrNull(R.string.not_exist_key)
    private val booleanUriOptionalErrKey by bindBooleanUriArgOrNull(R.string.not_exist_key)
    private val stringUriOptionalErrKey by bindStringUriArgOrNull(R.string.not_exist_key)

    companion object {
        fun createIntent(context: Context) = Intent(context, ResTestUriActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            val params = StringBuilder()
                    .append("byteUriRequired=").append(1.toByte())
                    .append("&byteUriOptional=").append((-1).toByte())
                    .append("&byteUriOrDefault=").append(2.toByte())

                    .append("&shortUriRequired=").append(3.toShort())
                    .append("&shortUriOptional=").append((-3).toShort())
                    .append("&shortUriOrDefault=").append(4.toShort())

                    .append("&intUriRequired=").append(5)
                    .append("&intUriOptional=").append(-5)
                    .append("&intUriOrDefault=").append(6)

                    .append("&longUriRequired=").append(7L)
                    .append("&longUriOptional=").append((-7L))
                    .append("&longUriOrDefault=").append(8L)

                    .append("&floatUriRequired=").append(9f)
                    .append("&floatUriOptional=").append(-9f)
                    .append("&floatUriOrDefault=").append(10f)

                    .append("&doubleUriRequired=").append(11.toDouble())
                    .append("&doubleUriOptional=").append((-11).toDouble())
                    .append("&doubleUriOrDefault=").append(12.toDouble())

                    .append("&booleanUriRequired=").append(true)
                    .append("&booleanUriOptional=").append(true)
                    .append("&booleanUriOrDefault=").append(false)

                    .append("&stringUriRequired=").append("stringUriRequired")
                    .append("&stringUriOptional=").append("stringUriOptional")
                    .append("&stringUriOrDefault=").append("stringUriOrDefault")
            data = Uri.parse("tools4a://tools4a.com/panpf/tools4a/res?$params")
        }
    }

    fun checkParams() {

        Assert.assertNull(byteUriOptionalErrKey)
        Assert.assertNull(shortUriOptionalErrKey)
        Assert.assertNull(intUriOptionalErrKey)
        Assert.assertNull(longUriOptionalErrKey)
        Assert.assertNull(floatUriOptionalErrKey)
        Assert.assertNull(doubleUriOptionalErrKey)
        Assert.assertNull(booleanUriOptionalErrKey)
        Assert.assertNull(stringUriOptionalErrKey)

        Assert.assertTrue(byteUriRequired == 1.toByte())
        Assert.assertTrue(byteUriOptional?.run { this == (-1).toByte() } ?: false)
        Assert.assertTrue(byteUriOrDefault == 2.toByte())
        Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

        Assert.assertTrue(shortUriRequired == 3.toShort())
        Assert.assertTrue(shortUriOptional?.run { this == (-3).toShort() } ?: false)
        Assert.assertTrue(shortUriOrDefault == 4.toShort())
        Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

        Assert.assertTrue(intUriRequired == 5)
        Assert.assertTrue(intUriOptional?.run { this == -5 } ?: false)
        Assert.assertTrue(intUriOrDefault == 6)
        Assert.assertTrue(intUriOrDefaultErrKey == 0)

        Assert.assertTrue(longUriRequired == 7L)
        Assert.assertTrue(longUriOptional?.run { this == -7L } ?: false)
        Assert.assertTrue(longUriOrDefault == 8L)
        Assert.assertTrue(longUriOrDefaultErrKey == 0L)

        Assert.assertTrue(floatUriRequired == 9f)
        Assert.assertTrue(floatUriOptional?.run { this == -9f } ?: false)
        Assert.assertTrue(floatUriOrDefault == 10f)
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

        Assert.assertTrue(doubleUriRequired == 11.toDouble())
        Assert.assertTrue(doubleUriOptional?.run { this == (-11).toDouble() } ?: false)
        Assert.assertTrue(doubleUriOrDefault == 12.toDouble())
        Assert.assertTrue(doubleUriOrDefaultErrKey == (-1).toDouble())

        Assert.assertTrue(booleanUriRequired)
        Assert.assertTrue(booleanUriOptional ?: false)
        Assert.assertTrue(!booleanUriOrDefault)
        Assert.assertTrue(!booleanUriOrDefaultErrKey)

        Assert.assertTrue(stringUriRequired == "stringUriRequired")
        Assert.assertTrue(stringUriOptional?.run { this == "stringUriOptional" } ?: false)
        Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
        Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")
    }
}