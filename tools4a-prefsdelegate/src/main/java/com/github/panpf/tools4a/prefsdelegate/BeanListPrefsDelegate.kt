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
import org.json.JSONObject
import kotlin.reflect.KProperty

class BeanListPrefsDelegate<T>(
        context: Context, prefsName: String? = null, key: String, private val defaultValue: List<T>, private val converter: BeanToJSONObjectConverter<T>
) : AbsPrefsDelegate<List<T>>(context, prefsName, key) {

    override fun getValue(thisRef: Any, property: KProperty<*>): List<T> {
        val jsonString = prefs.getString(key, null)
        if (jsonString.isNullOrEmpty()) return defaultValue

        val jsonArray = JSONArray(jsonString)
        if (jsonArray.length() <= 0) return defaultValue

        val list = ArrayList<T>(jsonArray.length())
        0.until(jsonArray.length()).forEach { index ->
            list.add(converter.jsonObjectToBean(JSONObject(jsonArray.getString(index))))
        }
        return list
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<T>) {
        prefs.edit().apply {
            if (value == defaultValue) {
                remove(key)
            } else {
                val jsonArray: JSONArray = JSONArray()
                for (item in value) {
                    jsonArray.put(converter.beanToJSONObject(item))
                }
                putString(key, jsonArray.toString())
            }
        }.apply()
    }
}
