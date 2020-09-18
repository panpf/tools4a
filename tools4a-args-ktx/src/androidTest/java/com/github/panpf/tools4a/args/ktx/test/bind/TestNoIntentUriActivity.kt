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
import org.junit.Assert

class TestNoIntentUriActivity : androidx.fragment.app.FragmentActivity() {

    private val byteIntentUriOrDefault by bindByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
    private val shortIntentUriOrDefault by bindShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
    private val intIntentUriOrDefault by bindIntIntentUriArgOr("intIntentUriOrDefault", 0)
    private val longIntentUriOrDefault by bindLongIntentUriArgOr("longIntentUriOrDefault", 0L)
    private val floatIntentUriOrDefault by bindFloatIntentUriArgOr("floatIntentUriOrDefault", 0f)
    private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
    private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)

    //private val stringIntentUriRequired by bindStringIntentUriArgOrThrow("stringIntentUriRequired")
    private val stringIntentUriOptional by bindStringIntentUriArgOrNull("stringIntentUriOptional")
    private val stringIntentUriOrDefault by bindStringIntentUriArgOr("stringIntentUriOrDefault", "default")
    private val stringIntentUriOrDefaultErrKey by bindStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")

    //Activity Uri Intent
    private val byteUriIntentOrDefault by bindByteUriIntentArgOr("byteUriIntentOrDefault", 0.toByte())
    private val shortUriIntentOrDefault by bindShortUriIntentArgOr("shortUriIntentOrDefault", 0.toShort())
    private val intUriIntentOrDefault by bindIntUriIntentArgOr("intUriIntentOrDefault", 0)
    private val longUriIntentOrDefault by bindLongUriIntentArgOr("longUriIntentOrDefault", 0L)
    private val floatUriIntentOrDefault by bindFloatUriIntentArgOr("floatUriIntentOrDefault", 0f)
    private val doubleUriIntentOrDefault by bindDoubleUriIntentArgOr("doubleUriIntentOrDefault", 0.toDouble())
    private val booleanUriIntentOrDefault by bindBooleanUriIntentArgOr("booleanUriIntentOrDefault", false)

    //        private val stringUriIntentRequired by bindStringUriIntentArgOrThrow("stringUriIntentRequired")
    private val stringUriIntentOptional by bindStringUriIntentArgOrNull("stringUriIntentOptional")
    private val stringUriIntentOrDefault by bindStringIntentUriArgOr("stringUriIntentOrDefault", "default")
    private val stringUriIntentOrDefaultErrKey by bindStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")


    companion object {

        fun createIntentWithNothing(context: Context) = Intent(context, TestNoIntentUriActivity::class.java).apply {
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