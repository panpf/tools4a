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

package com.github.panpf.tools4a.prefsdelegate.test

import android.content.Context
import android.preference.PreferenceManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.panpf.tools4a.prefsdelegate.*
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrefsDelegateTest {

    @Test
    fun testDefaultValue() {
        val context = androidx.test.InstrumentationRegistry.getInstrumentation().context
        val prefsService = DefaultValueTestPrefsService(context)
        try {
            prefsService.clearDefaultPrefs()

            /* Bean List */
            assertNull(prefsService.beanListNullablePrefs)
            assertEquals(listOf(People("xiaoming", 19), People("xiaohua", 17)), prefsService.beanListPrefs)
            assertEquals(listOf(People("xiaohua", 17), People("xiaoming", 19)), prefsService.beanList1Prefs)

            /* Bean */
            assertNull(prefsService.beanNullablePrefs)
            assertEquals(People("xiaoming", 19), prefsService.beanPrefs)
            assertEquals(People("xiaohua", 17), prefsService.bean1Prefs)

            /* Boolean */
            assertNull(prefsService.booleanNullablePrefs)
            assertFalse(prefsService.booleanPrefs)
            assertTrue(prefsService.boolean1Prefs)

            /* Enum */
            assertNull(prefsService.enumNullablePrefs)
            assertEquals(Type.BEST, prefsService.enumPrefs)
            assertEquals(Type.NEW, prefsService.enum1Prefs)

            /* Float */
            assertNull(prefsService.floatNullablePrefs)
            assertEquals(0f, prefsService.floatPrefs, 0f)
            assertEquals(56f, prefsService.float1Prefs, 56f)

            /* Int */
            assertNull(prefsService.intNullablePrefs)
            assertEquals(0.toLong(), prefsService.intPrefs.toLong())
            assertEquals(47.toLong(), prefsService.int1Prefs.toLong())

            /* Long */
            assertNull(prefsService.longNullablePrefs)
            assertEquals(0L, prefsService.longPrefs)
            assertEquals(93L, prefsService.long1Prefs)

            /* String List */
            assertNull(prefsService.stringListNullablePrefs)
            assertEquals(listOf("zaoshang", "xiawu"), prefsService.stringListPrefs)
            assertEquals(listOf("xiawu", "zaoshang"), prefsService.stringList1Prefs)

            /* String */
            assertNull(prefsService.stringNullablePrefs)
            assertEquals("", prefsService.stringPrefs)
            assertEquals("wangwu", prefsService.string1Prefs)

            /* String Set */
            assertNull(prefsService.stringSetNullablePrefs)
            assertEquals(setOf("wanshang", "banye"), prefsService.stringSetPrefs)
            assertEquals(setOf("banye", "wanshang"), prefsService.stringSet1Prefs)
        } finally {
            prefsService.clearDefaultPrefs()
        }
    }

    @Test
    fun testValue() {
        val context = androidx.test.InstrumentationRegistry.getInstrumentation().context
        val prefsService = ValueTestPrefsService(context)
        try {
            prefsService.clearDefaultPrefs()

            /* Bean List */
            val beanListNullableDefault: List<People>? = null
            val beanListNullableNew = listOf(People("zhangsan", 15), People("lisi", 11))
            assertEquals(beanListNullableDefault, prefsService.beanListNullablePrefs)
            prefsService.beanListNullablePrefs = beanListNullableNew
            assertEquals(beanListNullableNew, prefsService.beanListNullablePrefs)
            prefsService.beanListNullablePrefs = beanListNullableDefault
            assertEquals(beanListNullableDefault, prefsService.beanListNullablePrefs)

            val beanListDefault: List<People> = listOf(People("xiaoming", 19), People("xiaohua", 17))
            val beanListNew = listOf(People("xiaoli", 14), People("xiaolu", 10))
            assertEquals(beanListDefault, prefsService.beanListPrefs)
            prefsService.beanListPrefs = beanListNew
            assertEquals(beanListNew, prefsService.beanListPrefs)
            prefsService.beanListPrefs = beanListDefault
            assertEquals(beanListDefault, prefsService.beanListPrefs)

            /* Bean */
            val beanNullableDefault: People? = null
            val beanNullableNew = People("xiaoli", 14)
            assertEquals(beanNullableDefault, prefsService.beanNullablePrefs)
            prefsService.beanNullablePrefs = beanNullableNew
            assertEquals(beanNullableNew, prefsService.beanNullablePrefs)
            prefsService.beanNullablePrefs = beanNullableDefault
            assertEquals(beanNullableDefault, prefsService.beanNullablePrefs)

            val beanDefault = People("xiaoming", 19)
            val beanNew = People("xiaoli", 14)
            assertEquals(beanDefault, prefsService.beanPrefs)
            prefsService.beanPrefs = beanNew
            assertEquals(beanNew, prefsService.beanPrefs)
            prefsService.beanPrefs = beanDefault
            assertEquals(beanDefault, prefsService.beanPrefs)

            /* Boolean */
            val booleanNullableDefault: Boolean? = null
            val booleanNullableNew = true
            assertEquals(booleanNullableDefault, prefsService.booleanNullablePrefs)
            prefsService.booleanNullablePrefs = booleanNullableNew
            assertEquals(booleanNullableNew, prefsService.booleanNullablePrefs)
            prefsService.booleanNullablePrefs = booleanNullableDefault
            assertEquals(booleanNullableDefault, prefsService.booleanNullablePrefs)

            val booleanDefault = false
            val booleanNew = true
            assertEquals(booleanDefault, prefsService.booleanPrefs)
            prefsService.booleanPrefs = booleanNew
            assertEquals(booleanNew, prefsService.booleanPrefs)
            prefsService.booleanPrefs = booleanDefault
            assertEquals(booleanDefault, prefsService.booleanPrefs)

            /* Enum */
            val enumNullableDefault: Type? = null
            val enumNullableNew = Type.HOT
            assertEquals(enumNullableDefault, prefsService.enumNullablePrefs)
            prefsService.enumNullablePrefs = enumNullableNew
            assertEquals(enumNullableNew, prefsService.enumNullablePrefs)
            prefsService.enumNullablePrefs = enumNullableDefault
            assertEquals(enumNullableDefault, prefsService.enumNullablePrefs)

            val enumDefault = Type.BEST
            val enumNew = Type.NEW
            assertEquals(enumDefault, prefsService.enumPrefs)
            prefsService.enumPrefs = enumNew
            assertEquals(enumNew, prefsService.enumPrefs)
            prefsService.enumPrefs = enumDefault
            assertEquals(enumDefault, prefsService.enumPrefs)

            /* Float */
            val floatNullableDefault: Float? = null
            val floatNullableNew = 42421f
            assertEquals(floatNullableDefault, prefsService.floatNullablePrefs)
            prefsService.floatNullablePrefs = floatNullableNew
            assertEquals(floatNullableNew, prefsService.floatNullablePrefs)
            prefsService.floatNullablePrefs = floatNullableDefault
            assertEquals(floatNullableDefault, prefsService.floatNullablePrefs)

            val floatDefault = 0f
            val floatNew = 646543f
            assertEquals(floatDefault, prefsService.floatPrefs)
            prefsService.floatPrefs = floatNew
            assertEquals(floatNew, prefsService.floatPrefs)
            prefsService.floatPrefs = floatDefault
            assertEquals(floatDefault, prefsService.floatPrefs)

            /* Int */
            val intNullableDefault: Int? = null
            val intNullableNew = 9798
            assertEquals(intNullableDefault, prefsService.intNullablePrefs)
            prefsService.intNullablePrefs = intNullableNew
            assertEquals(intNullableNew, prefsService.intNullablePrefs)
            prefsService.intNullablePrefs = intNullableDefault
            assertEquals(intNullableDefault, prefsService.intNullablePrefs)

            val intDefault = 0
            val intNew = 7869
            assertEquals(intDefault, prefsService.intPrefs)
            prefsService.intPrefs = intNew
            assertEquals(intNew, prefsService.intPrefs)
            prefsService.intPrefs = intDefault
            assertEquals(intDefault, prefsService.intPrefs)

            /* Long */
            val longNullableDefault: Long? = null
            val longNullableNew = 7987L
            assertEquals(longNullableDefault, prefsService.longNullablePrefs)
            prefsService.longNullablePrefs = longNullableNew
            assertEquals(longNullableNew as Any, prefsService.longNullablePrefs as Any)
            prefsService.longNullablePrefs = longNullableDefault
            assertEquals(longNullableDefault, prefsService.longNullablePrefs)

            val longDefault = 0L
            val longNew = 1423L
            assertEquals(longDefault, prefsService.longPrefs)
            prefsService.longPrefs = longNew
            assertEquals(longNew, prefsService.longPrefs)
            prefsService.longPrefs = longDefault
            assertEquals(longDefault, prefsService.longPrefs)

            /* String List */
            val stringListNullableDefault: List<String>? = null
            val stringListNullableNew = listOf("1", "2", "0")
            assertEquals(stringListNullableDefault, prefsService.stringListNullablePrefs)
            prefsService.stringListNullablePrefs = stringListNullableNew
            assertEquals(stringListNullableNew, prefsService.stringListNullablePrefs)
            prefsService.stringListNullablePrefs = stringListNullableDefault
            assertEquals(stringListNullableDefault, prefsService.stringListNullablePrefs)

            val stringListDefault = listOf("zaoshang", "xiawu")
            val stringListNew = listOf("5", "7", "2")
            assertEquals(stringListDefault, prefsService.stringListPrefs)
            prefsService.stringListPrefs = stringListNew
            assertEquals(stringListNew, prefsService.stringListPrefs)
            prefsService.stringListPrefs = stringListDefault
            assertEquals(stringListDefault, prefsService.stringListPrefs)

            /* String */
            val stringNullableDefault: String? = null
            val stringNullableNew = "tggdgv"
            assertEquals(stringNullableDefault, prefsService.stringNullablePrefs)
            prefsService.stringNullablePrefs = stringNullableNew
            assertEquals(stringNullableNew, prefsService.stringNullablePrefs)
            prefsService.stringNullablePrefs = stringNullableDefault
            assertEquals(stringNullableDefault, prefsService.stringNullablePrefs)

            val stringDefault = ""
            val stringNew = "894123cfasdf"
            assertEquals(stringDefault, prefsService.stringPrefs)
            prefsService.stringPrefs = stringNew
            assertEquals(stringNew, prefsService.stringPrefs)
            prefsService.stringPrefs = stringDefault
            assertEquals(stringDefault, prefsService.stringPrefs)

            /* String Set */
            val stringSetNullableDefault: Set<String>? = null
            val stringSetNullableNew = setOf("vxvz", "t23t", "vyqfjk")
            assertEquals(stringSetNullableDefault, prefsService.stringSetNullablePrefs)
            prefsService.stringSetNullablePrefs = stringSetNullableNew
            assertEquals(stringSetNullableNew, prefsService.stringSetNullablePrefs)
            prefsService.stringSetNullablePrefs = stringSetNullableDefault
            assertEquals(stringSetNullableDefault, prefsService.stringSetNullablePrefs)

            val stringSetDefault = setOf("wanshang", "banye")
            val stringSetNew = setOf("53kfmds", "vzx834r9kjs")
            assertEquals(stringSetDefault, prefsService.stringSetPrefs)
            prefsService.stringSetPrefs = stringSetNew
            assertEquals(stringSetNew, prefsService.stringSetPrefs)
            prefsService.stringSetPrefs = stringSetDefault
            assertEquals(stringSetDefault, prefsService.stringSetPrefs)
        } finally {
            prefsService.clearDefaultPrefs()
        }
    }

    @Test
    fun testPrefsName() {
        val context = androidx.test.InstrumentationRegistry.getInstrumentation().context
        val prefsService = PrefsNameTestPrefsService(context)
        try {
            prefsService.clearDefaultPrefs()
            prefsService.clearSecondPrefs()

            /* Bean List */
            val beanListNew = listOf(People("xiaoli", 13), People("xiaocang", 11))
            val beanListNew1 = listOf(People("xiaosi", 17), People("xiaoye", 14))
            prefsService.beanListNullablePrefs = beanListNew
            prefsService.beanListNullablePrefs1 = beanListNew1
            assertEquals(beanListNew, prefsService.beanListNullablePrefs)
            assertEquals(beanListNew1, prefsService.beanListNullablePrefs1)
            prefsService.beanListPrefs = beanListNew
            prefsService.beanListPrefs1 = beanListNew1
            assertEquals(beanListNew, prefsService.beanListPrefs)
            assertEquals(beanListNew1, prefsService.beanListPrefs1)

            /* Bean */
            val beanNew = People("xiaoli", 13)
            val beanNew1 = People("xiaosi", 17)
            prefsService.beanNullablePrefs = beanNew
            prefsService.beanNullablePrefs1 = beanNew1
            assertEquals(beanNew, prefsService.beanNullablePrefs)
            assertEquals(beanNew1, prefsService.beanNullablePrefs1)
            prefsService.beanPrefs = beanNew
            prefsService.beanPrefs1 = beanNew1
            assertEquals(beanNew, prefsService.beanPrefs)
            assertEquals(beanNew1, prefsService.beanPrefs1)

            /* Boolean */
            val booleanNew = true
            val booleanNew1 = false
            prefsService.booleanNullablePrefs = booleanNew
            prefsService.booleanNullablePrefs1 = booleanNew1
            assertEquals(booleanNew, prefsService.booleanNullablePrefs)
            assertEquals(booleanNew1, prefsService.booleanNullablePrefs1)
            prefsService.booleanPrefs = booleanNew
            prefsService.booleanPrefs1 = booleanNew1
            assertEquals(booleanNew, prefsService.booleanPrefs)
            assertEquals(booleanNew1, prefsService.booleanPrefs1)

            /* Enum */
            val enumNew = Type.NEW
            val enumNew1 = Type.HOT
            prefsService.enumNullablePrefs = enumNew
            prefsService.enumNullablePrefs1 = enumNew1
            assertEquals(enumNew, prefsService.enumNullablePrefs)
            assertEquals(enumNew1, prefsService.enumNullablePrefs1)
            prefsService.enumPrefs = enumNew
            prefsService.enumPrefs1 = enumNew1
            assertEquals(enumNew, prefsService.enumPrefs)
            assertEquals(enumNew1, prefsService.enumPrefs1)

            /* Float */
            val floatNew = 325f
            val floatNew1 = 890890f
            prefsService.floatNullablePrefs = floatNew
            prefsService.floatNullablePrefs1 = floatNew1
            assertEquals(floatNew, prefsService.floatNullablePrefs)
            assertEquals(floatNew1, prefsService.floatNullablePrefs1)
            prefsService.floatPrefs = floatNew
            prefsService.floatPrefs1 = floatNew1
            assertEquals(floatNew, prefsService.floatPrefs)
            assertEquals(floatNew1, prefsService.floatPrefs1)

            /* Int */
            val intNew = 89087
            val intNew1 = 41242
            prefsService.intNullablePrefs = intNew
            prefsService.intNullablePrefs1 = intNew1
            assertEquals(intNew, prefsService.intNullablePrefs)
            assertEquals(intNew1, prefsService.intNullablePrefs1)
            prefsService.intPrefs = intNew
            prefsService.intPrefs1 = intNew1
            assertEquals(intNew, prefsService.intPrefs)
            assertEquals(intNew1, prefsService.intPrefs1)

            /* Long */
            val longNew = 890890L
            val longNew1 = 3534L
            prefsService.longNullablePrefs = longNew
            prefsService.longNullablePrefs1 = longNew1
            assertEquals(longNew as Any, prefsService.longNullablePrefs as Any)
            assertEquals(longNew1 as Any, prefsService.longNullablePrefs1 as Any)
            prefsService.longPrefs = longNew
            prefsService.longPrefs1 = longNew1
            assertEquals(longNew, prefsService.longPrefs)
            assertEquals(longNew1, prefsService.longPrefs1)

            /* String List */
            val stringListNew = listOf("xiaoli", "xiaocang")
            val stringListNew1 = listOf("xiaosi", "xiaoye")
            prefsService.stringListNullablePrefs = stringListNew
            prefsService.stringListNullablePrefs1 = stringListNew1
            assertEquals(stringListNew, prefsService.stringListNullablePrefs)
            assertEquals(stringListNew1, prefsService.stringListNullablePrefs1)
            prefsService.stringListPrefs = stringListNew
            prefsService.stringListPrefs1 = stringListNew1
            assertEquals(stringListNew, prefsService.stringListPrefs)
            assertEquals(stringListNew1, prefsService.stringListPrefs1)

            /* String */
            val stringNew = "xiaoli"
            val stringNew1 = "xiaosi"
            prefsService.stringNullablePrefs = stringNew
            prefsService.stringNullablePrefs1 = stringNew1
            assertEquals(stringNew, prefsService.stringNullablePrefs)
            assertEquals(stringNew1, prefsService.stringNullablePrefs1)
            prefsService.stringPrefs = stringNew
            prefsService.stringPrefs1 = stringNew1
            assertEquals(stringNew, prefsService.stringPrefs)
            assertEquals(stringNew1, prefsService.stringPrefs1)

            /* String Set */
            val stringSetNew = setOf("xiaoli", "xiaocang")
            val stringSetNew1 = setOf("xiaosi", "xiaoye")
            prefsService.stringSetNullablePrefs = stringSetNew
            prefsService.stringSetNullablePrefs1 = stringSetNew1
            assertEquals(stringSetNew, prefsService.stringSetNullablePrefs)
            assertEquals(stringSetNew1, prefsService.stringSetNullablePrefs1)
            prefsService.stringSetPrefs = stringSetNew
            prefsService.stringSetPrefs1 = stringSetNew1
            assertEquals(stringSetNew, prefsService.stringSetPrefs)
            assertEquals(stringSetNew1, prefsService.stringSetPrefs1)
        } finally {
            prefsService.clearDefaultPrefs()
            prefsService.clearSecondPrefs()
        }
    }

    class DefaultValueTestPrefsService(context: Context) {
        private val appContext = context.applicationContext

        fun clearDefaultPrefs() {
            PreferenceManager.getDefaultSharedPreferences(appContext).edit().clear().apply()
        }

        val beanListNullablePrefs by BeanListNullablePrefsDelegate(
                appContext, key = "beanListNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        val beanListPrefs by BeanListPrefsDelegate(
                appContext, key = "beanListPrefs",
                defaultValue = listOf(People("xiaoming", 19), People("xiaohua", 17)),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        val beanList1Prefs by BeanListPrefsDelegate(
                appContext, key = "beanListPrefs",
                defaultValue = listOf(People("xiaohua", 17), People("xiaoming", 19)),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        val beanNullablePrefs by BeanNullablePrefsDelegate(
                appContext, key = "beanNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        val beanPrefs by BeanPrefsDelegate(
                appContext, key = "beanPrefs", defaultValue = People("xiaoming", 19),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        val bean1Prefs by BeanPrefsDelegate(
                appContext, key = "beanPrefs", defaultValue = People("xiaohua", 17),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        val booleanNullablePrefs by BooleanNullablePrefsDelegate(appContext, key = "booleanNullablePrefs")
        val booleanPrefs by BooleanPrefsDelegate(appContext, key = "booleanPrefs")
        val boolean1Prefs by BooleanPrefsDelegate(appContext, key = "booleanPrefs", defaultValue = true)

        val enumNullablePrefs by EnumNullablePrefsDelegate(appContext, key = "enumNullablePrefs") { Type.valueOf(it) }
        val enumPrefs by EnumPrefsDelegate(appContext, key = "enumPrefs", defaultValue = Type.BEST) { Type.valueOf(it) }
        val enum1Prefs by EnumPrefsDelegate(appContext, key = "enumPrefs", defaultValue = Type.NEW) { Type.valueOf(it) }

        val floatNullablePrefs by FloatNullablePrefsDelegate(appContext, key = "floatNullablePrefs")
        val floatPrefs by FloatPrefsDelegate(appContext, key = "floatPrefs")
        val float1Prefs by FloatPrefsDelegate(appContext, key = "floatPrefs", defaultValue = 56f)

        val intNullablePrefs by IntNullablePrefsDelegate(appContext, key = "intNullablePrefs")
        val intPrefs by IntPrefsDelegate(appContext, key = "intPrefs")
        val int1Prefs by IntPrefsDelegate(appContext, key = "intPrefs", defaultValue = 47)

        val longNullablePrefs by LongNullablePrefsDelegate(appContext, key = "longNullablePrefs")
        val longPrefs by LongPrefsDelegate(appContext, key = "longPrefs")
        val long1Prefs by LongPrefsDelegate(appContext, key = "longPrefs", defaultValue = 93L)

        val stringListNullablePrefs by StringListNullablePrefsDelegate(appContext, key = "stringListNullablePrefs")
        val stringListPrefs by StringListPrefsDelegate(appContext, key = "stringListPrefs", defaultValue = listOf("zaoshang", "xiawu"))
        val stringList1Prefs by StringListPrefsDelegate(appContext, key = "stringListPrefs", defaultValue = listOf("xiawu", "zaoshang"))

        val stringNullablePrefs by StringNullablePrefsDelegate(appContext, key = "stringNullablePrefs")
        val stringPrefs by StringPrefsDelegate(appContext, key = "stringPrefs")
        val string1Prefs by StringPrefsDelegate(appContext, key = "stringPrefs", defaultValue = "wangwu")

        val stringSetNullablePrefs by StringSetNullablePrefsDelegate(appContext, key = "stringSetNullablePrefs")
        val stringSetPrefs by StringSetPrefsDelegate(appContext, key = "stringSetPrefs", defaultValue = setOf("wanshang", "banye"))
        val stringSet1Prefs by StringSetPrefsDelegate(appContext, key = "stringSetPrefs", defaultValue = setOf("banye", "wanshang"))
    }

    class ValueTestPrefsService(context: Context) {
        private val appContext = context.applicationContext

        fun clearDefaultPrefs() {
            PreferenceManager.getDefaultSharedPreferences(appContext).edit().clear().apply()
        }

        var beanListNullablePrefs by BeanListNullablePrefsDelegate(
                appContext, key = "beanListNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        var beanListPrefs by BeanListPrefsDelegate(
                appContext, key = "beanListPrefs",
                defaultValue = listOf(People("xiaoming", 19), People("xiaohua", 17)),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        var beanNullablePrefs by BeanNullablePrefsDelegate(
                appContext, key = "beanNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        var beanPrefs by BeanPrefsDelegate(
                appContext, key = "beanPrefs", defaultValue = People("xiaoming", 19),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        var booleanNullablePrefs by BooleanNullablePrefsDelegate(appContext, key = "booleanNullablePrefs")
        var booleanPrefs by BooleanPrefsDelegate(appContext, key = "booleanPrefs")

        var enumNullablePrefs by EnumNullablePrefsDelegate(appContext, key = "enumNullablePrefs") { Type.valueOf(it) }
        var enumPrefs by EnumPrefsDelegate(appContext, key = "enumPrefs", defaultValue = Type.BEST) { Type.valueOf(it) }

        var floatNullablePrefs by FloatNullablePrefsDelegate(appContext, key = "floatNullablePrefs")
        var floatPrefs by FloatPrefsDelegate(appContext, key = "floatPrefs")

        var intNullablePrefs by IntNullablePrefsDelegate(appContext, key = "intNullablePrefs")
        var intPrefs by IntPrefsDelegate(appContext, key = "intPrefs")

        var longNullablePrefs by LongNullablePrefsDelegate(appContext, key = "longNullablePrefs")
        var longPrefs by LongPrefsDelegate(appContext, key = "longPrefs")

        var stringListNullablePrefs by StringListNullablePrefsDelegate(appContext, key = "stringListNullablePrefs")
        var stringListPrefs by StringListPrefsDelegate(appContext, key = "stringListPrefs", defaultValue = listOf("zaoshang", "xiawu"))

        var stringNullablePrefs by StringNullablePrefsDelegate(appContext, key = "stringNullablePrefs")
        var stringPrefs by StringPrefsDelegate(appContext, key = "stringPrefs")

        var stringSetNullablePrefs by StringSetNullablePrefsDelegate(appContext, key = "stringSetNullablePrefs")
        var stringSetPrefs by StringSetPrefsDelegate(appContext, key = "stringSetPrefs", defaultValue = setOf("wanshang", "banye"))
    }

    class PrefsNameTestPrefsService(context: Context) {
        private val appContext = context.applicationContext

        fun clearDefaultPrefs() {
            PreferenceManager.getDefaultSharedPreferences(appContext).edit().clear().apply()
        }

        fun clearSecondPrefs() {
            appContext.getSharedPreferences("second_prefs", Context.MODE_PRIVATE).edit().clear().apply()
        }

        var beanListNullablePrefs by BeanListNullablePrefsDelegate(
                appContext, key = "beanListNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        var beanListNullablePrefs1 by BeanListNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "beanListNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        var beanListPrefs by BeanListPrefsDelegate(
                appContext, key = "beanListPrefs",
                defaultValue = listOf(People("xiaoming", 19), People("xiaohua", 17)),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        var beanListPrefs1 by BeanListPrefsDelegate(
                appContext, prefsName = "second_prefs", key = "beanListPrefs",
                defaultValue = listOf(People("xiaoming", 19), People("xiaohua", 17)),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        var beanNullablePrefs by BeanNullablePrefsDelegate(
                appContext, key = "beanNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        var beanNullablePrefs1 by BeanNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "beanNullablePrefs",
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        var beanPrefs by BeanPrefsDelegate(
                appContext, key = "beanPrefs", defaultValue = People("xiaoming", 19),
                converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)
        var beanPrefs1 by BeanPrefsDelegate(
                appContext, prefsName = "second_prefs", key = "beanPrefs",
                defaultValue = People("xiaoming", 19), converter = People.BEAN_TO_JSON_OBJECT_CONVERTER)

        var booleanNullablePrefs by BooleanNullablePrefsDelegate(
                appContext, key = "booleanNullablePrefs")
        var booleanNullablePrefs1 by BooleanNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "booleanNullablePrefs")

        var booleanPrefs by BooleanPrefsDelegate(
                appContext, key = "booleanPrefs")
        var booleanPrefs1 by BooleanPrefsDelegate(
                appContext, prefsName = "second_prefs", key = "booleanPrefs")

        var enumNullablePrefs by EnumNullablePrefsDelegate(
                appContext, key = "enumNullablePrefs") { Type.valueOf(it) }
        var enumNullablePrefs1 by EnumNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "enumNullablePrefs") { Type.valueOf(it) }
        var enumPrefs by EnumPrefsDelegate(
                appContext, key = "enumPrefs", defaultValue = Type.BEST) { Type.valueOf(it) }
        var enumPrefs1 by EnumPrefsDelegate(
                appContext, prefsName = "second_prefs", key = "enumPrefs", defaultValue = Type.BEST) {
            Type.valueOf(it)
        }

        var floatNullablePrefs by FloatNullablePrefsDelegate(
                appContext, key = "floatNullablePrefs")
        var floatNullablePrefs1 by FloatNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "floatNullablePrefs")

        var floatPrefs by FloatPrefsDelegate(appContext, key = "floatPrefs")
        var floatPrefs1 by FloatPrefsDelegate(appContext, prefsName = "second_prefs", key = "floatPrefs")

        var intNullablePrefs by IntNullablePrefsDelegate(
                appContext, key = "intNullablePrefs")
        var intNullablePrefs1 by IntNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "intNullablePrefs")

        var intPrefs by IntPrefsDelegate(appContext, key = "intPrefs")
        var intPrefs1 by IntPrefsDelegate(appContext, prefsName = "second_prefs", key = "intPrefs")

        var longNullablePrefs by LongNullablePrefsDelegate(
                appContext, key = "longNullablePrefs")
        var longNullablePrefs1 by LongNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "longNullablePrefs")

        var longPrefs by LongPrefsDelegate(appContext, key = "longPrefs")
        var longPrefs1 by LongPrefsDelegate(appContext, prefsName = "second_prefs", key = "longPrefs")

        var stringListNullablePrefs by StringListNullablePrefsDelegate(
                appContext, key = "stringListNullablePrefs")
        var stringListNullablePrefs1 by StringListNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "stringListNullablePrefs")

        var stringListPrefs by StringListPrefsDelegate(
                appContext, key = "stringListPrefs", defaultValue = listOf("zaoshang", "xiawu"))
        var stringListPrefs1 by StringListPrefsDelegate(
                appContext, prefsName = "second_prefs",
                key = "stringListPrefs", defaultValue = listOf("zaoshang", "xiawu"))

        var stringNullablePrefs by StringNullablePrefsDelegate(
                appContext, key = "stringNullablePrefs")
        var stringNullablePrefs1 by StringNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "stringNullablePrefs")

        var stringPrefs by StringPrefsDelegate(appContext, key = "stringPrefs")
        var stringPrefs1 by StringPrefsDelegate(appContext, prefsName = "second_prefs", key = "stringPrefs")

        var stringSetNullablePrefs by StringSetNullablePrefsDelegate(
                appContext, key = "stringSetNullablePrefs")
        var stringSetNullablePrefs1 by StringSetNullablePrefsDelegate(
                appContext, prefsName = "second_prefs", key = "stringSetNullablePrefs")

        var stringSetPrefs by StringSetPrefsDelegate(
                appContext, key = "stringSetPrefs", defaultValue = setOf("wanshang", "banye"))
        var stringSetPrefs1 by StringSetPrefsDelegate(
                appContext, prefsName = "second_prefs",
                key = "stringSetPrefs", defaultValue = setOf("wanshang", "banye"))
    }

    enum class Type {
        NEW, HOT, BEST
    }

    data class People(val name: String, val age: Int) {
        companion object {
            val BEAN_TO_JSON_OBJECT_CONVERTER = object : BeanToJSONObjectConverter<People> {
                override fun beanToJSONObject(bean: People) = JSONObject().apply {
                    put("name", bean.name)
                    put("age", bean.age)
                }

                override fun jsonObjectToBean(jsonObject: JSONObject): People {
                    return People(jsonObject.getString("name"), jsonObject.getInt("age"))
                }
            }
        }
    }
}