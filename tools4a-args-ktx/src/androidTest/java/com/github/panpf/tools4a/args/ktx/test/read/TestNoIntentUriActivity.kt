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

class TestNoIntentUriActivity : androidx.fragment.app.FragmentActivity() {
    companion object {
        fun createIntentWithNothing(context: Context): Intent {
            val starter = Intent(context, TestNoIntentUriActivity::class.java)
            starter.action = Intent.ACTION_VIEW
            starter.data = Uri.parse("tools4a://github.com/panpf")
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
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr("stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull("stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull("stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.0)
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.0)
        Assert.assertTrue(!booleanUriIntentOrDefault)
        Assert.assertNull(stringUriIntentOptional)
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    fun checkParams2() {
        val activity = this

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)
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
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull(R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.0)
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.0)
        Assert.assertTrue(!booleanUriIntentOrDefault)
        Assert.assertNull(stringUriIntentOptional)
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }
}