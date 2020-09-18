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

class ResTestOnlyUriNoIntentActivity : androidx.fragment.app.FragmentActivity() {
    companion object {
        fun createIntentWithUri(context: Context): Intent {
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

            return Intent(context, ResTestOnlyUriNoIntentActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("tools4a://tools4a.com/panpf/tools4a/res/uri?$params")
            }
        }
    }

    fun checkParams() {
        val activity = this

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow(R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr(R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr(R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull(R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull(R.string.not_exist_key)

        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr(R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr(R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr(R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr(R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr(R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr(R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr(R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow(R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull(R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == 1.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 2.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 3)
        Assert.assertTrue(longIntentUriOrDefault == 4L)
        Assert.assertTrue(floatIntentUriOrDefault == 5f)
        Assert.assertTrue(doubleIntentUriOrDefault == 6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentUriRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentUriOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentUriOrDefault")
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