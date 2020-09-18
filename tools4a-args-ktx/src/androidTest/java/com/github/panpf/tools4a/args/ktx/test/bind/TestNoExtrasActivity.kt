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
import android.os.Bundle
import com.github.panpf.tools4a.args.ktx.*
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class TestNoExtrasActivity : androidx.fragment.app.FragmentActivity() {

    private val byteArrayOrDefaultErrKey by bindByteArrayArgOr("byteArrayOrDefaultErrorKey", byteArrayOf(120.toByte()))

    private val shortArrayOrDefaultErrKey by bindShortArrayArgOr("shortArrayOrDefaultErrorKey", shortArrayOf(130.toShort()))

    private val intArrayOrDefaultErrKey by bindIntArrayArgOr("intArrayOrDefaultErrorKey", intArrayOf(140))

    private val intArrayListOrDefaultErrKey by bindIntArrayListArgOr("intArrayListOrDefaultErrorKey", arrayListOf(150))

    private val longArrayOrDefaultErrKey by bindLongArrayArgOr("longArrayOrDefaultErrorKey", longArrayOf(160L))

    private val floatArrayOrDefaultErrKey by bindFloatArrayArgOr("floatArrayOrDefaultErrorKey", floatArrayOf(170f))

    private val doubleArrayOrDefaultErrKey by bindDoubleArrayArgOr("doubleArrayOrDefaultErrorKey", doubleArrayOf(180.toDouble()))

    private val booleanArrayOrDefaultErrKey by bindBooleanArrayArgOr("booleanArrayOrDefaultErrorKey", booleanArrayOf(true))

    private val charArrayOrDefaultErrKey by bindCharArrayArgOr("charArrayOrDefaultErrorKey", charArrayOf('e'))

    private val charSequenceOrDefaultErrKey by bindCharSequenceArgOr("charSequenceOrDefaultErrorKey", "error")

    private val charSequenceArrayOrDefaultErrKey by bindCharSequenceArrayArgOr("charSequenceArrayOrDefaultErrorKey", arrayOf("error" as CharSequence))

    private val charSequenceArrayListOrDefaultErrKey by bindCharSequenceArrayListArgOr("charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "error"))

    private val stringOrDefaultErrKey by bindStringArgOr("stringOrDefaultErrKey", "error")

    private val stringArrayOrDefaultErrKey by bindStringArrayArgOr("stringArrayOrDefaultErrKey", arrayOf("stringArrayOrDefaultErrKey", "error"))

    private val stringArrayListOrDefaultErrKey by bindStringArrayListArgOr("stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "error"))

    private val parcelableOrDefaultErrKey by bindParcelableArgOr("parcelableOrDefaultErrKey", TestParcelable("error"))

    private val parcelableArrayOrDefaultErrKey by bindParcelableArrayArgOr("parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("parcelableArrayOrDefaultErrKey"), TestParcelable("error")))

    private val parcelableArrayListOrDefaultErrKey by bindParcelableArrayListArgOr("parcelableArrayListOrDefaultErrKey", arrayListOf(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("error")))

    private val serializableOrDefaultErrKey by bindSerializableArgOr("serializableOrDefaultErrKey", TestSerializable("error"))

    private val bundleOrDefaultErrKey by bindBundleArgOr("bundleOrDefaultErrKey", Bundle().apply { putString("bundle", "error") })

    private val extrasOrDefaultErrKey by bindExtrasArgOr(Bundle().apply { putString("extrasOrDefaultErrKey", "error") })

    private val extrasOptionalErrKey by bindExtrasArgOrNull()

    companion object {
        fun createIntent(context: Context) = Intent(context, TestNoExtrasActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.at_test)
    }

    fun checkParams() {

        Assert.assertNull(extrasOptionalErrKey)

        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 120.toByte())

        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 130.toShort())

        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 140)

        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 150)

        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 160L)

        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 170f)

        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 180.toDouble())

        Assert.assertTrue(booleanArrayOrDefaultErrKey[0])

        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'e')

        Assert.assertTrue(charSequenceOrDefaultErrKey == "error")

        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error")

        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "error")

        Assert.assertTrue(stringOrDefaultErrKey == "error")

        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "stringArrayOrDefaultErrKey" && stringArrayOrDefaultErrKey[1] == "error")

        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "error")

        Assert.assertTrue(parcelableOrDefaultErrKey == TestParcelable("error"))

        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("parcelableArrayOrDefaultErrKey") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("error"))

        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("error"))

        Assert.assertTrue(serializableOrDefaultErrKey == TestSerializable("error"))

        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "error")

        Assert.assertTrue(extrasOrDefaultErrKey.getString("extrasOrDefaultErrKey") == "error")
    }
}