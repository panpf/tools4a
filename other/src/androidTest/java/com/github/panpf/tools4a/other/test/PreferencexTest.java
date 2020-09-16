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

package com.github.panpf.tools4a.other.test;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.other.Preferencex;
import com.github.panpf.tools4j.collections.Collectionx;
import com.github.panpf.tools4j.collections.Mapx;
import com.github.panpf.tools4j.reflect.Reflectx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class PreferencexTest {

    @Test
    public void testGetPreferences() throws NoSuchFieldException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        SharedPreferences preferences = Preferencex.getPreferences(context, "testGetPreferences");
        Assert.assertEquals("testGetPreferences.xml", ((File) Reflectx.getFieldValue(preferences, "mFile")).getName());

        SharedPreferences preferences2 = Preferencex.getPreferences(context, "testGetPreferences_mod", Context.MODE_PRIVATE);
        Assert.assertEquals("testGetPreferences_mod.xml", ((File) Reflectx.getFieldValue(preferences2, "mFile")).getName());

        SharedPreferences defaultPreferences = Preferencex.getDefaultPreferences(context);
        Assert.assertEquals(context.getPackageName() + "_preferences.xml", ((File) Reflectx.getFieldValue(defaultPreferences, "mFile")).getName());
    }

    @Test
    public void testPutAndGet() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String name = "testPutAndGet";

        Preferencex.clear(context);
        Preferencex.clearFrom(context, name);
        Assert.assertTrue(Preferencex.isEmpty(context));
        Assert.assertTrue(Preferencex.isEmptyFrom(context, name));

        try {
            Assert.assertEquals(-1, Preferencex.getInt(context, "intKey", -1));
            Preferencex.putInt(context, "intKey", 1);
            Assert.assertEquals(1, Preferencex.getInt(context, "intKey", -1));

            Assert.assertEquals(-1, Preferencex.getIntFrom(context, name, "intKey", -1));
            Preferencex.putIntTo(context, name, "intKey", 1);
            Assert.assertEquals(1, Preferencex.getIntFrom(context, name, "intKey", -1));

            Assert.assertEquals(-1, Preferencex.getInt(context, "intKey1", -1));
            Assert.assertEquals(-1, Preferencex.getInt(context, "intKey2", -1));
            Preferencex.putInts(context, Mapx.builder("intKey1", 1).put("intKey2", 2).buildHashMap());
            Assert.assertEquals(1, Preferencex.getInt(context, "intKey1", -1));
            Assert.assertEquals(2, Preferencex.getInt(context, "intKey2", -1));

            Assert.assertEquals(-1, Preferencex.getIntFrom(context, name, "intKey1", -1));
            Assert.assertEquals(-1, Preferencex.getIntFrom(context, name, "intKey2", -1));
            Preferencex.putIntsTo(context, name, Mapx.builder("intKey1", 1).put("intKey2", 2).buildHashMap());
            Assert.assertEquals(1, Preferencex.getIntFrom(context, name, "intKey1", -1));
            Assert.assertEquals(2, Preferencex.getIntFrom(context, name, "intKey2", -1));


            Assert.assertEquals(-1L, Preferencex.getLong(context, "longKey", -1L));
            Preferencex.putLong(context, "longKey", 1L);
            Assert.assertEquals(1L, Preferencex.getLong(context, "longKey", -1L));

            Assert.assertEquals(-1L, Preferencex.getLongFrom(context, name, "longKey", -1L));
            Preferencex.putLongTo(context, name, "longKey", 1L);
            Assert.assertEquals(1L, Preferencex.getLongFrom(context, name, "longKey", -1L));

            Assert.assertEquals(-1L, Preferencex.getLong(context, "longKey1", -1L));
            Assert.assertEquals(-1L, Preferencex.getLong(context, "longKey2", -1L));
            Preferencex.putLongs(context, Mapx.builder("longKey1", 1L).put("longKey2", 2L).buildHashMap());
            Assert.assertEquals(1L, Preferencex.getLong(context, "longKey1", -1L));
            Assert.assertEquals(2L, Preferencex.getLong(context, "longKey2", -1L));

            Assert.assertEquals(-1L, Preferencex.getLongFrom(context, name, "longKey1", -1L));
            Assert.assertEquals(-1L, Preferencex.getLongFrom(context, name, "longKey2", -1L));
            Preferencex.putLongsTo(context, name, Mapx.builder("longKey1", 1L).put("longKey2", 2L).buildHashMap());
            Assert.assertEquals(1L, Preferencex.getLongFrom(context, name, "longKey1", -1L));
            Assert.assertEquals(2L, Preferencex.getLongFrom(context, name, "longKey2", -1L));


            Assert.assertTrue(Preferencex.getBoolean(context, "booleanKey", true));
            Preferencex.putBoolean(context, "booleanKey", false);
            Assert.assertFalse(Preferencex.getBoolean(context, "booleanKey", true));

            Assert.assertTrue(Preferencex.getBooleanFrom(context, name, "booleanKey", true));
            Preferencex.putBooleanTo(context, name, "booleanKey", false);
            Assert.assertFalse(Preferencex.getBooleanFrom(context, name, "booleanKey", true));

            Assert.assertTrue(Preferencex.getBoolean(context, "booleanKey1", true));
            Assert.assertTrue(Preferencex.getBoolean(context, "booleanKey2", true));
            Preferencex.putBooleans(context, Mapx.builder("booleanKey1", false).put("booleanKey2", true).buildHashMap());
            Assert.assertFalse(Preferencex.getBoolean(context, "booleanKey1", true));
            Assert.assertTrue(Preferencex.getBoolean(context, "booleanKey2", true));

            Assert.assertTrue(Preferencex.getBooleanFrom(context, name, "booleanKey1", true));
            Assert.assertTrue(Preferencex.getBooleanFrom(context, name, "booleanKey2", true));
            Preferencex.putBooleansTo(context, name, Mapx.builder("booleanKey1", false).put("booleanKey2", true).buildHashMap());
            Assert.assertFalse(Preferencex.getBooleanFrom(context, name, "booleanKey1", true));
            Assert.assertTrue(Preferencex.getBooleanFrom(context, name, "booleanKey2", true));


            Assert.assertEquals(-1f, Preferencex.getFloat(context, "floatKey", -1f), 0f);
            Preferencex.putFloat(context, "floatKey", 1f);
            Assert.assertEquals(1f, Preferencex.getFloat(context, "floatKey", -1f), 0f);

            Assert.assertEquals(-1f, Preferencex.getFloatFrom(context, name, "floatKey", -1f), 0f);
            Preferencex.putFloatTo(context, name, "floatKey", 1f);
            Assert.assertEquals(1f, Preferencex.getFloatFrom(context, name, "floatKey", -1f), 0f);

            Assert.assertEquals(-1f, Preferencex.getFloat(context, "floatKey1", -1f), 0f);
            Assert.assertEquals(-1f, Preferencex.getFloat(context, "floatKey2", -1f), 0f);
            Preferencex.putFloats(context, Mapx.builder("floatKey1", 1f).put("floatKey2", 2f).buildHashMap());
            Assert.assertEquals(1f, Preferencex.getFloat(context, "floatKey1", -1f), 0f);
            Assert.assertEquals(2f, Preferencex.getFloat(context, "floatKey2", -1f), 0f);

            Assert.assertEquals(-1f, Preferencex.getFloatFrom(context, name, "floatKey1", -1f), 0f);
            Assert.assertEquals(-1f, Preferencex.getFloatFrom(context, name, "floatKey2", -1f), 0f);
            Preferencex.putFloatsTo(context, name, Mapx.builder("floatKey1", 1f).put("floatKey2", 2f).buildHashMap());
            Assert.assertEquals(1f, Preferencex.getFloatFrom(context, name, "floatKey1", -1f), 0f);
            Assert.assertEquals(2f, Preferencex.getFloatFrom(context, name, "floatKey2", -1f), 0f);


            Assert.assertEquals("-1f", Preferencex.getString(context, "stringKey", "-1f"));
            Assert.assertNull(Preferencex.getStringOrNull(context, "stringKey"));
            Preferencex.putString(context, "stringKey", "1f");
            Assert.assertEquals("1f", Preferencex.getString(context, "stringKey", "-1f"));

            Assert.assertEquals("-1f", Preferencex.getStringFrom(context, name, "stringKey", "-1f"));
            Assert.assertNull(Preferencex.getStringOrNullFrom(context, name, "stringKey"));
            Preferencex.putStringTo(context, name, "stringKey", "1f");
            Assert.assertEquals("1f", Preferencex.getStringFrom(context, name, "stringKey", "-1f"));

            Assert.assertEquals("-1f", Preferencex.getString(context, "stringKey1", "-1f"));
            Assert.assertEquals("-1f", Preferencex.getString(context, "stringKey2", "-1f"));
            Preferencex.putStrings(context, Mapx.builder("stringKey1", "1f").put("stringKey2", "2f").buildHashMap());
            Assert.assertEquals("1f", Preferencex.getString(context, "stringKey1", "-1f"));
            Assert.assertEquals("2f", Preferencex.getString(context, "stringKey2", "-1f"));

            Assert.assertEquals("-1f", Preferencex.getStringFrom(context, name, "stringKey1", "-1f"));
            Assert.assertEquals("-1f", Preferencex.getStringFrom(context, name, "stringKey2", "-1f"));
            Preferencex.putStringsTo(context, name, Mapx.builder("stringKey1", "1f").put("stringKey2", "2f").buildHashMap());
            Assert.assertEquals("1f", Preferencex.getStringFrom(context, name, "stringKey1", "-1f"));
            Assert.assertEquals("2f", Preferencex.getStringFrom(context, name, "stringKey2", "-1f"));


            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("-1", "-2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSet(context, "stringSetKey", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Assert.assertNull(Preferencex.getStringSetOrNull(context, "stringSetKey"));
            Preferencex.putStringSet(context, "stringSetKey", Collectionx.toSet(Collectionx.mutableListOf("1", "2")));
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("1", "2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSet(context, "stringSetKey", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));

            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("-1", "-2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSetFrom(context, name, "stringSetKey", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Assert.assertNull(Preferencex.getStringSetOrNullFrom(context, name, "stringSetKey"));
            Preferencex.putStringSetTo(context, name, "stringSetKey", Collectionx.toSet(Collectionx.mutableListOf("1", "2")));
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("1", "2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSetFrom(context, name, "stringSetKey", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));


            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("-1", "-2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSet(context, "stringSetsKey1", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("-1", "-2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSet(context, "stringSetsKey2", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Preferencex.putStringSets(context, Mapx.builder("stringSetsKey1", Collectionx.toSet(Collectionx.mutableListOf("1", "2"))).put("stringSetsKey2", Collectionx.toSet(Collectionx.mutableListOf("3", "4"))).buildHashMap());
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("1", "2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSet(context, "stringSetsKey1", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("4", "3")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSet(context, "stringSetsKey2", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));

            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("-1", "-2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSetFrom(context, name, "stringSetsKey1", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("-1", "-2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSetFrom(context, name, "stringSetsKey2", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Preferencex.putStringSetsTo(context, name, Mapx.builder("stringSetsKey1", Collectionx.toSet(Collectionx.mutableListOf("1", "2"))).put("stringSetsKey2", Collectionx.toSet(Collectionx.mutableListOf("3", "4"))).buildHashMap());
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("1", "2")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSetFrom(context, name, "stringSetsKey1", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
            Assert.assertEquals(Collectionx.joinToString(Collectionx.toSet(Collectionx.sorted(Collectionx.mutableListOf("4", "3")))), Collectionx.joinToString(Collectionx.sorted(Preferencex.getStringSetFrom(context, name, "stringSetsKey2", Collectionx.toSet(Collectionx.mutableListOf("-1", "-2"))))));
        } finally {
            Preferencex.clear(context);
            Preferencex.clearFrom(context, name);
        }
    }

    @Test
    public void testGetAll() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String name = "testGetAll";

        Preferencex.clear(context);
        Preferencex.clearFrom(context, name);
        Assert.assertTrue(Preferencex.isEmpty(context));
        Assert.assertTrue(Preferencex.isEmptyFrom(context, name));

        try {
            Preferencex.putString(context, "removeKey1", "removeValue1");
            Preferencex.putString(context, "removeKey2", "removeValue2");
            Preferencex.putString(context, "removeKey3", "removeValue3");

            Map<String, ?> allValues = Preferencex.getAll(context);
            Assert.assertEquals("removeValue1", allValues.get("removeKey1"));
            Assert.assertEquals("removeValue2", allValues.get("removeKey2"));
            Assert.assertEquals("removeValue3", allValues.get("removeKey3"));

            Preferencex.putStringTo(context, name, "removeKey1", "removeValue1");
            Preferencex.putStringTo(context, name, "removeKey2", "removeValue2");
            Preferencex.putStringTo(context, name, "removeKey3", "removeValue3");

            Map<String, ?> allValues2 = Preferencex.getAllFrom(context, name);
            Assert.assertEquals("removeValue1", allValues2.get("removeKey1"));
            Assert.assertEquals("removeValue2", allValues2.get("removeKey2"));
            Assert.assertEquals("removeValue3", allValues2.get("removeKey3"));
        } finally {
            Preferencex.clear(context);
            Preferencex.clearFrom(context, name);
        }
    }

    @Test
    public void testRemoveAndContains() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String name = "testRemoveAndContains";

        Preferencex.clear(context);
        Preferencex.clearFrom(context, name);
        Assert.assertTrue(Preferencex.isEmpty(context));
        Assert.assertTrue(Preferencex.isEmptyFrom(context, name));

        try {
            Preferencex.putString(context, "removeKey1", "removeValue1");
            Preferencex.putString(context, "removeKey2", "removeValue2");
            Preferencex.putString(context, "removeKey3", "removeValue3");

            Assert.assertTrue(Preferencex.contains(context, "removeKey1"));
            Assert.assertTrue(Preferencex.contains(context, "removeKey2"));
            Assert.assertTrue(Preferencex.contains(context, "removeKey3"));

            Assert.assertTrue(Preferencex.containsAll(context, "removeKey1", "removeKey2", "removeKey3"));
            Assert.assertFalse(Preferencex.containsAll(context, "removeKey1", "removeKey2", "removeKey4"));

            Assert.assertTrue(Preferencex.containsAny(context, "removeKey1", "removeKey2", "removeKey3"));
            Assert.assertFalse(Preferencex.containsAny(context, "removeKey4", "removeKey5", "removeKey6"));

            Preferencex.remove(context, "removeKey1", "removeKey2", "removeKey3");
            Assert.assertFalse(Preferencex.containsAll(context, "removeKey1", "removeKey2", "removeKey3"));


            Preferencex.putStringTo(context, name, "removeKey1", "removeValue1");
            Preferencex.putStringTo(context, name, "removeKey2", "removeValue2");
            Preferencex.putStringTo(context, name, "removeKey3", "removeValue3");

            Assert.assertTrue(Preferencex.containsFrom(context, name, "removeKey1"));
            Assert.assertTrue(Preferencex.containsFrom(context, name, "removeKey2"));
            Assert.assertTrue(Preferencex.containsFrom(context, name, "removeKey3"));

            Assert.assertTrue(Preferencex.containsAllFrom(context, name, "removeKey1", "removeKey2", "removeKey3"));
            Assert.assertFalse(Preferencex.containsAllFrom(context, name, "removeKey1", "removeKey2", "removeKey4"));

            Assert.assertTrue(Preferencex.containsAnyFrom(context, name, "removeKey1", "removeKey2", "removeKey3"));
            Assert.assertFalse(Preferencex.containsAnyFrom(context, name, "removeKey4", "removeKey5", "removeKey6"));

            Preferencex.removeFrom(context, name, "removeKey1", "removeKey2", "removeKey3");
            Assert.assertFalse(Preferencex.containsAllFrom(context, name, "removeKey1", "removeKey2", "removeKey3"));
        } finally {
            Preferencex.clear(context);
            Preferencex.clearFrom(context, name);
        }
    }

    @Test
    public void testListener() throws InterruptedException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String name = "testListener";

        Preferencex.clear(context);
        Preferencex.clearFrom(context, name);
        Assert.assertTrue(Preferencex.isEmpty(context));
        Assert.assertTrue(Preferencex.isEmptyFrom(context, name));

        final Map<String, Object> backupValues = new HashMap<>();
        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Object value = sharedPreferences.getAll().get(key);
                if (value != null) {
                    backupValues.put(key, value);
                } else {
                    backupValues.remove(key);
                }
            }
        };

        final Map<String, Object> backupValues2 = new HashMap<>();
        SharedPreferences.OnSharedPreferenceChangeListener listener2 = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Object value = sharedPreferences.getAll().get(key);
                if (value != null) {
                    backupValues2.put(key, value);
                } else {
                    backupValues2.remove(key);
                }
            }
        };

        try {
            Preferencex.registerOnChangeListener(context, listener);

            Preferencex.putString(context, "removeKey1", "removeValue1");
            Preferencex.putString(context, "removeKey2", "removeValue2");
            Preferencex.putString(context, "removeKey3", "removeValue3");
            Thread.sleep(100);

            Assert.assertEquals("removeValue1", backupValues.get("removeKey1"));
            Assert.assertEquals("removeValue2", backupValues.get("removeKey2"));
            Assert.assertEquals("removeValue3", backupValues.get("removeKey3"));

            Preferencex.unregisterOnChangeListener(context, listener);

            Preferencex.putString(context, "removeKey4", "removeValue4");
            Thread.sleep(100);

            Assert.assertNull(backupValues.get("removeKey4"));


            Preferencex.registerOnChangeListenerTo(context, name, listener2);

            Preferencex.putStringTo(context, name, "removeKey1", "removeValue1");
            Preferencex.putStringTo(context, name, "removeKey2", "removeValue2");
            Preferencex.putStringTo(context, name, "removeKey3", "removeValue3");
            Thread.sleep(100);

            Assert.assertEquals("removeValue1", backupValues2.get("removeKey1"));
            Assert.assertEquals("removeValue2", backupValues2.get("removeKey2"));
            Assert.assertEquals("removeValue3", backupValues2.get("removeKey3"));

            Preferencex.unregisterOnChangeListenerFrom(context, name, listener2);

            Preferencex.putStringTo(context, name, "removeKey4", "removeValue4");
            Thread.sleep(100);

            Assert.assertNull(backupValues2.get("removeKey4"));
        } finally {
            Preferencex.unregisterOnChangeListener(context, listener);
            Preferencex.unregisterOnChangeListenerFrom(context, name, listener2);
            Preferencex.clear(context);
            Preferencex.clearFrom(context, name);
        }
    }
}
