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
import android.os.Bundle
import com.github.panpf.tools4a.args.ktx.readExtrasArgOr
import com.github.panpf.tools4a.args.ktx.readExtrasArgOrNull
import org.junit.Assert

class NoExtraActivity : androidx.fragment.app.FragmentActivity() {
    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NoExtraActivity::class.java)
        }
    }

    fun checkParams() {
        val activity = this

        val extrasOptional = activity.readExtrasArgOrNull()
        val bundleDefault = Bundle()
        bundleDefault.putString("errDefault", "default")
        val extrasDefault = activity.readExtrasArgOr(bundleDefault)

        Assert.assertNull(extrasOptional)
        Assert.assertTrue(extrasDefault.getString("errDefault") == "default")
    }
}