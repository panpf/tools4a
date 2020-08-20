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

package com.github.panpf.tools4a.prefsdelegate

import android.content.Context
import kotlin.reflect.KProperty

class IntNullablePrefsDelegate(
        context: Context, prefsName: String? = null, key: String
) : AbsPrefsDelegate<Int?>(context, prefsName, key) {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int? {
        val result = prefs.getInt(key, Integer.MIN_VALUE)
        return if (result == Integer.MIN_VALUE && prefs.getInt(key, Integer.MAX_VALUE) == Integer.MAX_VALUE) {
            null
        } else {
            result
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int?) {
        prefs.edit().apply {
            if (value == null) remove(key) else putInt(key, value)
        }.apply()
    }
}
