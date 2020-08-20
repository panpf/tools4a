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
import org.json.JSONArray
import java.util.*
import kotlin.reflect.KProperty

class StringListNullablePrefsDelegate(
        context: Context, prefsName: String? = null, key: String
) : AbsPrefsDelegate<List<String>?>(context, prefsName, key) {

    override fun getValue(thisRef: Any, property: KProperty<*>): List<String>? {
        val stringListJsonString = prefs.getString(key, null)
        if (stringListJsonString.isNullOrEmpty()) return null

        val jsonArray = JSONArray(stringListJsonString)
        if (jsonArray.length() <= 0) return null

        val arrayList = ArrayList<String>(jsonArray.length())
        0.until(jsonArray.length()).forEach { index ->
            arrayList.add(jsonArray.getString(index))
        }
        return arrayList
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<String>?) {
        prefs.edit().apply {
            if (value == null || value.isEmpty()) {
                remove(key)
            } else {
                val jsonArray: JSONArray = JSONArray()
                for (item in value) {
                    jsonArray.put(item)
                }
                putString(key, jsonArray.toString())
            }
        }.apply()
    }
}
