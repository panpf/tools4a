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

class ResTestBothIntentUriActivity : androidx.fragment.app.FragmentActivity() {

    private val byteIntentUriOrDefault by bindByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
    private val shortIntentUriOrDefault by bindShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
    private val intIntentUriOrDefault by bindIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
    private val longIntentUriOrDefault by bindLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
    private val floatIntentUriOrDefault by bindFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0f)
    private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
    private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)

    private val stringIntentUriRequired by bindStringIntentUriArgOrThrow(R.string.string_intent_uri_required)
    private val stringIntentUriOptional by bindStringIntentUriArgOrNull(R.string.string_intent_uri_optional)
    private val stringIntentUriOrDefault by bindStringIntentUriArgOr(R.string.string_intent_uri_or_default, "default")
    private val stringIntentUriOrDefaultErrKey by bindStringIntentUriArgOr(R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")

    //Activity Uri Intent
    private val byteUriIntentOrDefault by bindByteUriIntentArgOr(R.string.byte_uri_intent_or_default, 0.toByte())
    private val shortUriIntentOrDefault by bindShortUriIntentArgOr(R.string.short_uri_intent_or_default, 0.toShort())
    private val intUriIntentOrDefault by bindIntUriIntentArgOr(R.string.int_uri_intent_or_default, 0)
    private val longUriIntentOrDefault by bindLongUriIntentArgOr(R.string.long_uri_intent_or_default, 0L)
    private val floatUriIntentOrDefault by bindFloatUriIntentArgOr(R.string.float_uri_intent_or_default, 0f)
    private val doubleUriIntentOrDefault by bindDoubleUriIntentArgOr(R.string.double_uri_intent_or_default, 0.toDouble())
    private val booleanUriIntentOrDefault by bindBooleanUriIntentArgOr(R.string.boolean_uri_intent_or_default, false)

    private val stringUriIntentRequired by bindStringUriIntentArgOrThrow(R.string.string_uri_intent_required)
    private val stringUriIntentOptional by bindStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
    private val stringUriIntentOrDefault by bindStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
    private val stringUriIntentOrDefaultErrKey by bindStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")

    private val stringUriIntentOptionalErrKey by bindStringUriIntentArgOrNull(R.string.not_exist_key)
    private val stringIntentUriOptionalErrKey by bindStringIntentUriArgOrNull(R.string.not_exist_key)

    companion object {

        private val params = StringBuilder()
                .append("byteIntentUriOrDefault=").append(1.toByte())
                .append("&shortIntentUriOrDefault=").append(2.toShort())
                .append("&intIntentUriOrDefault=").append(3)
                .append("&longIntentUriOrDefault=").append(4L)
                .append("&floatIntentUriOrDefault=").append(5.toFloat())
                .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                .append("&booleanIntentUriOrDefault=").append(true)
                .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                //Activity Uri Intent
                .append("&byteUriIntentOrDefault=").append(11.toByte())
                .append("&shortUriIntentOrDefault=").append(12.toShort())
                .append("&intUriIntentOrDefault=").append(13)
                .append("&longUriIntentOrDefault=").append(14L)
                .append("&floatUriIntentOrDefault=").append(15.toFloat())
                .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                .append("&booleanUriIntentOrDefault=").append(true)
                .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")

        private val uri = Uri.parse("tools4a://tools4a.com/panpf/tools4a/res/uri/intent?$params")

        fun createIntentWithUriAndExtras(context: Context) = Intent(context, ResTestBothIntentUriActivity::class.java).apply {

            action = Intent.ACTION_VIEW
            data = uri

            putExtra(context.getString(R.string.byte_intent_uri_or_default), (-1).toByte())
            putExtra(context.getString(R.string.short_intent_uri_or_default), (-2).toShort())
            putExtra(context.getString(R.string.int_intent_uri_or_default), -3)
            putExtra(context.getString(R.string.long_intent_uri_or_default), -4L)
            putExtra(context.getString(R.string.float_intent_uri_or_default), -5f)
            putExtra(context.getString(R.string.double_intent_uri_or_default), (-6).toDouble())
            putExtra(context.getString(R.string.boolean_intent_uri_or_default), true)
            putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired")
            putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional")
            putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault")

            putExtra(context.getString(R.string.byte_uri_intent_or_default), (-11).toByte())
            putExtra(context.getString(R.string.short_uri_intent_or_default), (-12).toShort())
            putExtra(context.getString(R.string.int_uri_intent_or_default), -13)
            putExtra(context.getString(R.string.long_uri_intent_or_default), -14L)
            putExtra(context.getString(R.string.float_uri_intent_or_default), -15f)
            putExtra(context.getString(R.string.double_uri_intent_or_default), (-16).toDouble())
            putExtra(context.getString(R.string.boolean_uri_intent_or_default), true)
            putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired")
            putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional")
            putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault")
        }

    }

    fun checkParamsByUriIntent() {
        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == (-5).toFloat())
        Assert.assertTrue(doubleIntentUriOrDefault == (-6).toDouble())
        Assert.assertTrue(booleanIntentUriOrDefault)

        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional?.run { this == "stringIntentOptional" }
                ?: false)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15.toFloat())
        Assert.assertTrue(doubleUriIntentOrDefault == 16.toDouble())
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional?.run { this == "stringUriIntentOptional" }
                ?: false)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")

        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertNull(stringIntentUriOptionalErrKey)
    }

}