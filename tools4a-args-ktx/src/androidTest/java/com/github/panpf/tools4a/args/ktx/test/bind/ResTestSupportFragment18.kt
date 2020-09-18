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
import com.github.panpf.tools4a.args.ktx.bindBinderArgOr
import com.github.panpf.tools4a.args.ktx.bindBinderArgOrNull
import com.github.panpf.tools4a.args.ktx.bindBinderArgOrThrow
import com.github.panpf.tools4a.args.ktx.test.R
import org.junit.Assert

class ResTestSupportFragment18 : androidx.fragment.app.Fragment() {

    private val binderRequired by bindBinderArgOrThrow(R.string.binder_required)
    private val binderOptional by bindBinderArgOrNull(R.string.binder_optional)
    private val binderOrDefault by bindBinderArgOr(R.string.binder_or_default, TestBinder())
    private val binderOrDefaultErrKey by bindBinderArgOr(R.string.not_exist_key, TestBinder("error"))
    private val binderOptionalErrKey by bindBinderArgOrNull(R.string.not_exist_key)

    fun checkParams() {
        Assert.assertNull(binderOptionalErrKey)
        Assert.assertTrue(binderRequired == TestBinder("binderRequired"))
        Assert.assertTrue(binderOptional == TestBinder("binderOptional"))
        Assert.assertTrue(binderOrDefault == TestBinder("binderOrDefault"))
        Assert.assertTrue(binderOrDefaultErrKey == TestBinder("error"))
    }

    companion object {
        fun createArguments(context: Context): Bundle = Bundle().apply {
            putBinder(context.getString(R.string.binder_required), TestBinder("binderRequired"))
            putBinder(context.getString(R.string.binder_optional), TestBinder("binderOptional"))
            putBinder(context.getString(R.string.binder_or_default), TestBinder("binderOrDefault"))
        }
    }
}