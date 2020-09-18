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

package com.github.panpf.tools4a.other.ktx.test

import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.panpf.tools4a.other.ktx.*
import com.github.panpf.tools4j.collections.Mapx
import com.github.panpf.tools4j.reflect.ktx.getFieldValue
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.util.*

@RunWith(AndroidJUnit4::class)
class PreferencexTest {

    @Test
    @Throws(NoSuchFieldException::class)
    fun testGetPreferences() {
        val context = InstrumentationRegistry.getInstrumentation().context

        val preferences = context.getSharedPreferences("testGetPreferences")
        Assert.assertEquals("testGetPreferences.xml", (preferences.getFieldValue("mFile") as File?)!!.name)

        val defaultPreferences = context.getDefaultSharedPreferences()
        Assert.assertEquals(context.packageName + "_preferences.xml", (defaultPreferences.getFieldValue("mFile") as File?)!!.name)
    }

    @Test
    fun testPutAndGet() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val name = "testPutAndGet"

        context.clearPreferences()
        context.clearPreferencesFrom(name)
        Assert.assertTrue(context.isEmptyPreferences())
        Assert.assertTrue(context.isEmptyPreferencesFrom(name))

        try {
            Assert.assertEquals(-1, context.getIntPreference("intKey", -1).toLong())
            context.putIntPreference("intKey", 1)
            Assert.assertEquals(1, context.getIntPreference("intKey", -1).toLong())

            Assert.assertEquals(-1, context.getIntPreferenceFrom(name, "intKey", -1).toLong())
            context.putIntPreferenceTo(name, "intKey", 1)
            Assert.assertEquals(1, context.getIntPreferenceFrom(name, "intKey", -1).toLong())

            Assert.assertEquals(-1, context.getIntPreference("intKey1", -1).toLong())
            Assert.assertEquals(-1, context.getIntPreference("intKey2", -1).toLong())
            context.putIntsPreference(Mapx.builder("intKey1", 1).put("intKey2", 2).buildHashMap())
            Assert.assertEquals(1, context.getIntPreference("intKey1", -1).toLong())
            Assert.assertEquals(2, context.getIntPreference("intKey2", -1).toLong())

            Assert.assertEquals(-1, context.getIntPreferenceFrom(name, "intKey1", -1).toLong())
            Assert.assertEquals(-1, context.getIntPreferenceFrom(name, "intKey2", -1).toLong())
            context.putIntsPreferenceTo(name, Mapx.builder("intKey1", 1).put("intKey2", 2).buildHashMap())
            Assert.assertEquals(1, context.getIntPreferenceFrom(name, "intKey1", -1).toLong())
            Assert.assertEquals(2, context.getIntPreferenceFrom(name, "intKey2", -1).toLong())


            Assert.assertEquals(-1L, context.getLongPreference("longKey", -1L))
            context.putLongPreference("longKey", 1L)
            Assert.assertEquals(1L, context.getLongPreference("longKey", -1L))

            Assert.assertEquals(-1L, context.getLongPreferenceFrom(name, "longKey", -1L))
            context.putLongPreferenceTo(name, "longKey", 1L)
            Assert.assertEquals(1L, context.getLongPreferenceFrom(name, "longKey", -1L))

            Assert.assertEquals(-1L, context.getLongPreference("longKey1", -1L))
            Assert.assertEquals(-1L, context.getLongPreference("longKey2", -1L))
            context.putLongsPreference(Mapx.builder("longKey1", 1L).put("longKey2", 2L).buildHashMap())
            Assert.assertEquals(1L, context.getLongPreference("longKey1", -1L))
            Assert.assertEquals(2L, context.getLongPreference("longKey2", -1L))

            Assert.assertEquals(-1L, context.getLongPreferenceFrom(name, "longKey1", -1L))
            Assert.assertEquals(-1L, context.getLongPreferenceFrom(name, "longKey2", -1L))
            context.putLongsPreferenceTo(name, Mapx.builder("longKey1", 1L).put("longKey2", 2L).buildHashMap())
            Assert.assertEquals(1L, context.getLongPreferenceFrom(name, "longKey1", -1L))
            Assert.assertEquals(2L, context.getLongPreferenceFrom(name, "longKey2", -1L))


            Assert.assertTrue(context.getBooleanPreference("booleanKey", true))
            context.putBooleanPreference("booleanKey", false)
            Assert.assertFalse(context.getBooleanPreference("booleanKey", true))

            Assert.assertTrue(context.getBooleanPreferenceFrom(name, "booleanKey", true))
            context.putBooleanPreferenceTo(name, "booleanKey", false)
            Assert.assertFalse(context.getBooleanPreferenceFrom(name, "booleanKey", true))

            Assert.assertTrue(context.getBooleanPreference("booleanKey1", true))
            Assert.assertTrue(context.getBooleanPreference("booleanKey2", true))
            context.putBooleansPreference(Mapx.builder("booleanKey1", false).put("booleanKey2", true).buildHashMap())
            Assert.assertFalse(context.getBooleanPreference("booleanKey1", true))
            Assert.assertTrue(context.getBooleanPreference("booleanKey2", true))

            Assert.assertTrue(context.getBooleanPreferenceFrom(name, "booleanKey1", true))
            Assert.assertTrue(context.getBooleanPreferenceFrom(name, "booleanKey2", true))
            context.putBooleansPreferenceTo(name, Mapx.builder("booleanKey1", false).put("booleanKey2", true).buildHashMap())
            Assert.assertFalse(context.getBooleanPreferenceFrom(name, "booleanKey1", true))
            Assert.assertTrue(context.getBooleanPreferenceFrom(name, "booleanKey2", true))


            Assert.assertEquals(-1f, context.getFloatPreference("floatKey", -1f), 0f)
            context.putFloatPreference("floatKey", 1f)
            Assert.assertEquals(1f, context.getFloatPreference("floatKey", -1f), 0f)

            Assert.assertEquals(-1f, context.getFloatPreferenceFrom(name, "floatKey", -1f), 0f)
            context.putFloatPreferenceTo(name, "floatKey", 1f)
            Assert.assertEquals(1f, context.getFloatPreferenceFrom(name, "floatKey", -1f), 0f)

            Assert.assertEquals(-1f, context.getFloatPreference("floatKey1", -1f), 0f)
            Assert.assertEquals(-1f, context.getFloatPreference("floatKey2", -1f), 0f)
            context.putFloatsPreference(Mapx.builder("floatKey1", 1f).put("floatKey2", 2f).buildHashMap())
            Assert.assertEquals(1f, context.getFloatPreference("floatKey1", -1f), 0f)
            Assert.assertEquals(2f, context.getFloatPreference("floatKey2", -1f), 0f)

            Assert.assertEquals(-1f, context.getFloatPreferenceFrom(name, "floatKey1", -1f), 0f)
            Assert.assertEquals(-1f, context.getFloatPreferenceFrom(name, "floatKey2", -1f), 0f)
            context.putFloatsPreferenceTo(name, Mapx.builder("floatKey1", 1f).put("floatKey2", 2f).buildHashMap())
            Assert.assertEquals(1f, context.getFloatPreferenceFrom(name, "floatKey1", -1f), 0f)
            Assert.assertEquals(2f, context.getFloatPreferenceFrom(name, "floatKey2", -1f), 0f)


            Assert.assertEquals("-1f", context.getStringPreference("stringKey", "-1f"))
            Assert.assertNull(context.getStringPreferenceOrNull("stringKey"))
            context.putStringPreference("stringKey", "1f")
            Assert.assertEquals("1f", context.getStringPreference("stringKey", "-1f"))

            Assert.assertEquals("-1f", context.getStringPreferenceFrom(name, "stringKey", "-1f"))
            Assert.assertNull(context.getStringPreferenceOrNullFrom(name, "stringKey"))
            context.putStringPreferenceTo(name, "stringKey", "1f")
            Assert.assertEquals("1f", context.getStringPreferenceFrom(name, "stringKey", "-1f"))

            Assert.assertEquals("-1f", context.getStringPreference("stringKey1", "-1f"))
            Assert.assertEquals("-1f", context.getStringPreference("stringKey2", "-1f"))
            context.putStringsPreference(Mapx.builder("stringKey1", "1f").put("stringKey2", "2f").buildHashMap())
            Assert.assertEquals("1f", context.getStringPreference("stringKey1", "-1f"))
            Assert.assertEquals("2f", context.getStringPreference("stringKey2", "-1f"))

            Assert.assertEquals("-1f", context.getStringPreferenceFrom(name, "stringKey1", "-1f"))
            Assert.assertEquals("-1f", context.getStringPreferenceFrom(name, "stringKey2", "-1f"))
            context.putStringsPreferenceTo(name, Mapx.builder("stringKey1", "1f").put("stringKey2", "2f").buildHashMap())
            Assert.assertEquals("1f", context.getStringPreferenceFrom(name, "stringKey1", "-1f"))
            Assert.assertEquals("2f", context.getStringPreferenceFrom(name, "stringKey2", "-1f"))


            Assert.assertEquals(listOf("-1", "-2").sorted().toSet().joinToString(), context.getStringSetPreference("stringSetKey", listOf("-1", "-2").toSet()).sorted().joinToString())
            Assert.assertNull(context.getStringSetPreferenceOrNull("stringSetKey"))
            context.putStringSetPreference("stringSetKey", listOf("1", "2").toSet())
            Assert.assertEquals(listOf("1", "2").sorted().toSet().joinToString(), context.getStringSetPreference("stringSetKey", listOf("-1", "-2").toSet()).sorted().joinToString())

            Assert.assertEquals(listOf("-1", "-2").sorted().toSet().joinToString(), context.getStringSetPreferenceFrom(name, "stringSetKey", listOf("-1", "-2").toSet()).sorted().joinToString())
            Assert.assertNull(context.getStringSetPreferenceOrNullFrom(name, "stringSetKey"))
            context.putStringSetPreferenceTo(name, "stringSetKey", listOf("1", "2").toSet())
            Assert.assertEquals(listOf("1", "2").sorted().toSet().joinToString(), context.getStringSetPreferenceFrom(name, "stringSetKey", listOf("-1", "-2").toSet()).sorted().joinToString())


            Assert.assertEquals(listOf("-1", "-2").sorted().toSet().joinToString(), context.getStringSetPreference("stringSetsKey1", listOf("-1", "-2").toSet()).sorted().joinToString())
            Assert.assertEquals(listOf("-1", "-2").sorted().toSet().joinToString(), context.getStringSetPreference("stringSetsKey2", listOf("-1", "-2").toSet()).sorted().joinToString())
            context.putStringSetsPreference(Mapx.builder("stringSetsKey1", listOf("1", "2").toSet()).put("stringSetsKey2", listOf("3", "4").toSet()).buildHashMap())
            Assert.assertEquals(listOf("1", "2").sorted().toSet().joinToString(), context.getStringSetPreference("stringSetsKey1", listOf("-1", "-2").toSet()).sorted().joinToString())
            Assert.assertEquals(listOf("4", "3").sorted().toSet().joinToString(), context.getStringSetPreference("stringSetsKey2", listOf("-1", "-2").toSet()).sorted().joinToString())

            Assert.assertEquals(listOf("-1", "-2").sorted().toSet().joinToString(), context.getStringSetPreferenceFrom(name, "stringSetsKey1", listOf("-1", "-2").toSet()).sorted().joinToString())
            Assert.assertEquals(listOf("-1", "-2").sorted().toSet().joinToString(), context.getStringSetPreferenceFrom(name, "stringSetsKey2", listOf("-1", "-2").toSet()).sorted().joinToString())
            context.putStringSetsPreferenceTo(name, Mapx.builder("stringSetsKey1", listOf("1", "2").toSet()).put("stringSetsKey2", listOf("3", "4").toSet()).buildHashMap())
            Assert.assertEquals(listOf("1", "2").sorted().toSet().joinToString(), context.getStringSetPreferenceFrom(name, "stringSetsKey1", listOf("-1", "-2").toSet()).sorted().joinToString())
            Assert.assertEquals(listOf("4", "3").sorted().toSet().joinToString(), context.getStringSetPreferenceFrom(name, "stringSetsKey2", listOf("-1", "-2").toSet()).sorted().joinToString())
        } finally {
            context.clearPreferences()
            context.clearPreferencesFrom(name)
        }
    }

    @Test
    fun testGetAll() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val name = "testGetAll"

        context.clearPreferences()
        context.clearPreferencesFrom(name)
        Assert.assertTrue(context.isEmptyPreferences())
        Assert.assertTrue(context.isEmptyPreferencesFrom(name))

        try {
            context.putStringPreference("removeKey1", "removeValue1")
            context.putStringPreference("removeKey2", "removeValue2")
            context.putStringPreference("removeKey3", "removeValue3")

            val allValues = context.getAllPreference()
            Assert.assertEquals("removeValue1", allValues["removeKey1"])
            Assert.assertEquals("removeValue2", allValues["removeKey2"])
            Assert.assertEquals("removeValue3", allValues["removeKey3"])

            context.putStringPreferenceTo(name, "removeKey1", "removeValue1")
            context.putStringPreferenceTo(name, "removeKey2", "removeValue2")
            context.putStringPreferenceTo(name, "removeKey3", "removeValue3")

            val allValues2 = context.getAllPreferenceFrom(name)
            Assert.assertEquals("removeValue1", allValues2["removeKey1"])
            Assert.assertEquals("removeValue2", allValues2["removeKey2"])
            Assert.assertEquals("removeValue3", allValues2["removeKey3"])
        } finally {
            context.clearPreferences()
            context.clearPreferencesFrom(name)
        }
    }

    @Test
    fun testRemoveAndContains() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val name = "testRemoveAndContains"

        context.clearPreferences()
        context.clearPreferencesFrom(name)
        Assert.assertTrue(context.isEmptyPreferences())
        Assert.assertTrue(context.isEmptyPreferencesFrom(name))

        try {
            context.putStringPreference("removeKey1", "removeValue1")
            context.putStringPreference("removeKey2", "removeValue2")
            context.putStringPreference("removeKey3", "removeValue3")

            Assert.assertTrue(context.containsPreference("removeKey1"))
            Assert.assertTrue(context.containsPreference("removeKey2"))
            Assert.assertTrue(context.containsPreference("removeKey3"))

            Assert.assertTrue(context.containsPreferenceAll("removeKey1", "removeKey2", "removeKey3"))
            Assert.assertFalse(context.containsPreferenceAll("removeKey1", "removeKey2", "removeKey4"))

            Assert.assertTrue(context.containsPreferenceAny("removeKey1", "removeKey2", "removeKey3"))
            Assert.assertFalse(context.containsPreferenceAny("removeKey4", "removeKey5", "removeKey6"))

            context.removePreference("removeKey1", "removeKey2", "removeKey3")
            Assert.assertFalse(context.containsPreferenceAll("removeKey1", "removeKey2", "removeKey3"))


            context.putStringPreferenceTo(name, "removeKey1", "removeValue1")
            context.putStringPreferenceTo(name, "removeKey2", "removeValue2")
            context.putStringPreferenceTo(name, "removeKey3", "removeValue3")

            Assert.assertTrue(context.containsPreferenceFrom(name, "removeKey1"))
            Assert.assertTrue(context.containsPreferenceFrom(name, "removeKey2"))
            Assert.assertTrue(context.containsPreferenceFrom(name, "removeKey3"))

            Assert.assertTrue(context.containsPreferenceAllFrom(name, "removeKey1", "removeKey2", "removeKey3"))
            Assert.assertFalse(context.containsPreferenceAllFrom(name, "removeKey1", "removeKey2", "removeKey4"))

            Assert.assertTrue(context.containsPreferenceAnyFrom(name, "removeKey1", "removeKey2", "removeKey3"))
            Assert.assertFalse(context.containsPreferenceAnyFrom(name, "removeKey4", "removeKey5", "removeKey6"))

            context.removePreferenceFrom(name, "removeKey1", "removeKey2", "removeKey3")
            Assert.assertFalse(context.containsPreferenceAllFrom(name, "removeKey1", "removeKey2", "removeKey3"))
        } finally {
            context.clearPreferences()
            context.clearPreferencesFrom(name)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testListener() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val name = "testListener"

        context.clearPreferences()
        context.clearPreferencesFrom(name)
        Assert.assertTrue(context.isEmptyPreferences())
        Assert.assertTrue(context.isEmptyPreferencesFrom(name))

        val backupValues = HashMap<String, Any>()
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            val value = sharedPreferences.all[key]
            if (value != null) {
                backupValues[key] = value
            } else {
                backupValues.remove(key)
            }
        }

        val backupValues2 = HashMap<String, Any>()
        val listener2 = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            val value = sharedPreferences.all[key]
            if (value != null) {
                backupValues2[key] = value
            } else {
                backupValues2.remove(key)
            }
        }

        try {
            context.registerOnPreferenceChangeListener(listener)

            context.putStringPreference("removeKey1", "removeValue1")
            context.putStringPreference("removeKey2", "removeValue2")
            context.putStringPreference("removeKey3", "removeValue3")
            Thread.sleep(100)

            Assert.assertEquals("removeValue1", backupValues["removeKey1"])
            Assert.assertEquals("removeValue2", backupValues["removeKey2"])
            Assert.assertEquals("removeValue3", backupValues["removeKey3"])

            context.unregisterOnPreferenceChangeListener(listener)

            context.putStringPreference("removeKey4", "removeValue4")
            Thread.sleep(100)

            Assert.assertNull(backupValues["removeKey4"])


            context.registerOnPreferenceChangeListenerTo(name, listener2)

            context.putStringPreferenceTo(name, "removeKey1", "removeValue1")
            context.putStringPreferenceTo(name, "removeKey2", "removeValue2")
            context.putStringPreferenceTo(name, "removeKey3", "removeValue3")
            Thread.sleep(100)

            Assert.assertEquals("removeValue1", backupValues2["removeKey1"])
            Assert.assertEquals("removeValue2", backupValues2["removeKey2"])
            Assert.assertEquals("removeValue3", backupValues2["removeKey3"])

            context.unregisterOnPreferenceChangeListenerFrom(name, listener2)

            context.putStringPreferenceTo(name, "removeKey4", "removeValue4")
            Thread.sleep(100)

            Assert.assertNull(backupValues2["removeKey4"])
        } finally {
            context.unregisterOnPreferenceChangeListener(listener)
            context.unregisterOnPreferenceChangeListenerFrom(name, listener2)
            context.clearPreferences()
            context.clearPreferencesFrom(name)
        }
    }
}
