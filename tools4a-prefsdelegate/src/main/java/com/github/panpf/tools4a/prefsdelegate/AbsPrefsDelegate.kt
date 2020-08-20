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
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty

abstract class AbsPrefsDelegate<T>(
        context: Context, private val prefsName: String? = null, val key: String
) : ReadWriteProperty<Any, T> {

    private val appContext = context.applicationContext

    protected val prefs: SharedPreferences by lazy {
        if (prefsName == null) {
            PreferenceManager.getDefaultSharedPreferences(appContext)
        } else {
            appContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        }
    }
}
