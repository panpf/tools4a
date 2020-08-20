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

class StringPrefsDelegate(
        context: Context, prefsName: String? = null, key: String, private val defaultValue: String = ""
) : AbsPrefsDelegate<String>(context, prefsName, key) {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        prefs.edit().apply {
            if (value == defaultValue) remove(key) else putString(key, value)
        }.apply()
    }
}
