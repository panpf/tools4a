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

class TestBothIntentUriActivity : androidx.fragment.app.FragmentActivity() {

    private val byteIntentUriOrDefault by bindByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
    private val shortIntentUriOrDefault by bindShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
    private val intIntentUriOrDefault by bindIntIntentUriArgOr("intIntentUriOrDefault", 0)
    private val longIntentUriOrDefault by bindLongIntentUriArgOr("longIntentUriOrDefault", 0L)
    private val floatIntentUriOrDefault by bindFloatIntentUriArgOr("floatIntentUriOrDefault", 0f)
    private val doubleIntentUriOrDefault by bindDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
    private val booleanIntentUriOrDefault by bindBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)

    private val stringIntentUriRequired by bindStringIntentUriArgOrThrow("stringIntentUriRequired")
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

    private val stringUriIntentRequired by bindStringUriIntentArgOrThrow("stringUriIntentRequired")
    private val stringUriIntentOptional by bindStringUriIntentArgOrNull("stringUriIntentOptional")
    private val stringUriIntentOrDefault by bindStringIntentUriArgOr("stringUriIntentOrDefault", "default")
    private val stringUriIntentOrDefaultErrKey by bindStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")

    private val stringUriIntentOptionalErrKey by bindStringUriIntentArgOrNull("keyNotExist")
    private val stringIntentUriOptionalErrKey by bindStringIntentUriArgOrNull("keyNotExist")

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

        private val uri = Uri.parse("tools4a://tools4a.com/panpf/tools4a/uri/intent?$params")

        fun createIntentWithUriAndExtras(context: Context) = Intent(context, TestBothIntentUriActivity::class.java).apply {

            action = Intent.ACTION_VIEW
            data = uri

            putExtra("byteIntentUriOrDefault", (-1).toByte())
            putExtra("shortIntentUriOrDefault", (-2).toShort())
            putExtra("intIntentUriOrDefault", -3)
            putExtra("longIntentUriOrDefault", -4L)
            putExtra("floatIntentUriOrDefault", -5f)
            putExtra("doubleIntentUriOrDefault", (-6).toDouble())
            putExtra("booleanIntentUriOrDefault", true)

            putExtra("stringIntentUriRequired", "stringIntentRequired")
            putExtra("stringIntentUriOptional", "stringIntentOptional")
            putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")

            //Activity Uri Intent
            putExtra("byteUriIntentOrDefault", (-11).toByte())
            putExtra("shortUriIntentOrDefault", (-12).toShort())
            putExtra("intUriIntentOrDefault", -13)
            putExtra("longUriIntentOrDefault", -14L)
            putExtra("floatUriIntentOrDefault", -15f)
            putExtra("doubleUriIntentOrDefault", (-16).toDouble())
            putExtra("booleanUriIntentOrDefault", true)
            putExtra("stringUriIntentRequired", "stringUriIntentRequired")
            putExtra("stringUriIntentOptional", "stringUriIntentOptional")
            putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault")
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