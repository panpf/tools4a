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
import android.os.Bundle
import android.util.Size
import android.util.SizeF
import com.github.panpf.tools4a.args.ktx.*
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestSupportFragment21 : androidx.fragment.app.Fragment() {

    private val sizeRequired by bindSizeArgOrThrow(R.string.size_required)
    private val sizeOptional by bindSizeArgOrNull(R.string.size_optional)
    private val sizeOrDefault by bindSizeArgOr(R.string.size_or_default, Size(0, 0))
    private val sizeOrDefaultErrKey by bindSizeArgOr(R.string.not_exist_key, Size(4, 4))

    private val sizeFRequired by bindSizeFArgOrThrow(R.string.sizeF_required)
    private val sizeFOptional by bindSizeFArgOrNull(R.string.sizeF_optional)
    private val sizeFOrDefault by bindSizeFArgOr(R.string.sizeF_or_default, SizeF(0f, 0f))
    private val sizeFOrDefaultErrKey by bindSizeFArgOr(R.string.not_exist_key, SizeF(4f, 4f))

    private val sizeOptionalErrKey by bindSizeArgOrNull(R.string.not_exist_key)
    private val sizeFOptionalErrKey by bindSizeFArgOrNull(R.string.not_exist_key)

    fun checkParams() {
        Assert.assertNull(sizeOptionalErrKey)
        Assert.assertNull(sizeFOptionalErrKey)
        Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
        Assert.assertTrue(sizeOptional?.width == 2 && sizeOptional?.height == 2)
        Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
        Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)
        Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
        Assert.assertTrue(sizeFOptional?.width == 2f && sizeFOptional?.height == 2f)
        Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
        Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)
    }

    companion object {
        fun createArguments(context: Context): Bundle = Bundle().apply {
            putSize(context.getString(R.string.size_required), Size(1, 1))
            putSize(context.getString(R.string.size_optional), Size(2, 2))
            putSize(context.getString(R.string.size_or_default), Size(3, 3))

            putSizeF(context.getString(R.string.sizeF_required), SizeF(1f, 1f))
            putSizeF(context.getString(R.string.sizeF_optional), SizeF(2f, 2f))
            putSizeF(context.getString(R.string.sizeF_or_default), SizeF(3f, 3f))
        }
    }
}