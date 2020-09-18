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

class ResTestNoIntentUriActivity : androidx.fragment.app.FragmentActivity() {

    private val byteIntentUriOrDefault by bindByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
    private val shortIntentUriOrDefault by bindShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
    private val intIntentUriOrDefault by bindIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
    private val longIntentUriOrDefault by bindLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
    private val floatIntentUriOrDefault by bindFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0f)
    private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
    private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)

    //                private val stringIntentUriRequired by bindStringIntentUriArgOrThrow(R.string.string_intent_uri_required)
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

    //        private val stringUriIntentRequired by bindStringIntentUriArgOrThrow(R.string.string_uri_intent_required)
    private val stringUriIntentOptional by bindStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
    private val stringUriIntentOrDefault by bindStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
    private val stringUriIntentOrDefaultErrKey by bindStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")

    companion object {

        fun createIntentWithNothing(context: Context) = Intent(context, ResTestNoIntentUriActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("tools4a://tools4a.com/panpf")
        }
    }

    fun checkParamsByNothing() {
        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.toDouble())
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.toDouble())
        Assert.assertTrue(!booleanUriIntentOrDefault)

        Assert.assertNull(stringUriIntentOptional)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }
}