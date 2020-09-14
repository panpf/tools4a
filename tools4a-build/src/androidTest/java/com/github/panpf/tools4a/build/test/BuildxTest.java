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

package com.github.panpf.tools4a.build.test;

import android.os.Build;
import android.util.SparseArray;

import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.build.Buildx;
import com.github.panpf.tools4j.collections.Collectionx;
import com.github.panpf.tools4j.common.Predicate;
import com.github.panpf.tools4j.ranges.Rangex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class BuildxTest {

    @Test
    public void testIsAtLeast() {
        SparseArray<List<Predicate<String>>> array = new SparseArray<>();
        array.append(Build.VERSION_CODES.JELLY_BEAN, Collectionx.mutableListOf(s -> Buildx.isAtLeastJ(), s -> Buildx.isAtLeast16(), s -> Buildx.isAtLeast4_1()));
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR1, Collectionx.mutableListOf(s -> Buildx.isAtLeastJMR1(), s -> Buildx.isAtLeast17(), s -> Buildx.isAtLeast4_2()));
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR2, Collectionx.mutableListOf(s -> Buildx.isAtLeastJMR2(), s -> Buildx.isAtLeast18(), s -> Buildx.isAtLeast4_3()));
        array.append(Build.VERSION_CODES.KITKAT, Collectionx.mutableListOf(s -> Buildx.isAtLeastK(), s -> Buildx.isAtLeast19(), s -> Buildx.isAtLeast4_4()));
        array.append(Build.VERSION_CODES.KITKAT_WATCH, Collectionx.mutableListOf(s -> Buildx.isAtLeastKW(), s -> Buildx.isAtLeast20(), s -> Buildx.isAtLeast4_4_W()));
        array.append(Build.VERSION_CODES.LOLLIPOP, Collectionx.mutableListOf(s -> Buildx.isAtLeastL(), s -> Buildx.isAtLeast21(), s -> Buildx.isAtLeast5_0()));
        array.append(Build.VERSION_CODES.LOLLIPOP_MR1, Collectionx.mutableListOf(s -> Buildx.isAtLeastLMR1(), s -> Buildx.isAtLeast22(), s -> Buildx.isAtLeast5_1()));
        array.append(Build.VERSION_CODES.M, Collectionx.mutableListOf(s -> Buildx.isAtLeastM(), s -> Buildx.isAtLeast23(), s -> Buildx.isAtLeast6_0()));
        array.append(Build.VERSION_CODES.N, Collectionx.mutableListOf(s -> Buildx.isAtLeastN(), s -> Buildx.isAtLeast24(), s -> Buildx.isAtLeast7_0()));
        array.append(Build.VERSION_CODES.N_MR1, Collectionx.mutableListOf(s -> Buildx.isAtLeastNMR1(), s -> Buildx.isAtLeast25(), s -> Buildx.isAtLeast7_1()));
        array.append(Build.VERSION_CODES.O, Collectionx.mutableListOf(s -> Buildx.isAtLeastO(), s -> Buildx.isAtLeast26(), s -> Buildx.isAtLeast8_0()));
        array.append(Build.VERSION_CODES.O_MR1, Collectionx.mutableListOf(s -> Buildx.isAtLeastOMR1(), s -> Buildx.isAtLeast27(), s -> Buildx.isAtLeast8_1()));
        array.append(Build.VERSION_CODES.P, Collectionx.mutableListOf(s -> Buildx.isAtLeastP(), s -> Buildx.isAtLeast28(), s -> Buildx.isAtLeast9_0()));
        array.append(Build.VERSION_CODES.Q, Collectionx.mutableListOf(s -> Buildx.isAtLeastQ(), s -> Buildx.isAtLeast29(), s -> Buildx.isAtLeast10_0()));
        array.append(Build.VERSION_CODES.R, Collectionx.mutableListOf(s -> Buildx.isAtLeastR(), s -> Buildx.isAtLeast30(), s -> Buildx.isAtLeast11_0()));

        for (int index : Rangex.until(0, array.size())) {
            int key = array.keyAt(index);
            for (Predicate<String> predicate : array.get(key)) {
                if (Build.VERSION.SDK_INT >= key) {
                    Assert.assertTrue("sdkInt=" + Build.VERSION.SDK_INT + ", key=" + key, predicate.accept(""));
                } else {
                    Assert.assertFalse("sdkInt=" + Build.VERSION.SDK_INT + ", key=" + key, predicate.accept(""));
                }
            }
        }
    }

    @Test
    public void testGetVersionName() {
        Assert.assertEquals("1.0.0", Buildx.getVersionName(Build.VERSION_CODES.BASE));
        Assert.assertEquals("1.1.0", Buildx.getVersionName(Build.VERSION_CODES.BASE_1_1));
        Assert.assertEquals("1.5.0", Buildx.getVersionName(Build.VERSION_CODES.CUPCAKE));
        Assert.assertEquals("1.6.0", Buildx.getVersionName(Build.VERSION_CODES.DONUT));
        Assert.assertEquals("2.0.0", Buildx.getVersionName(Build.VERSION_CODES.ECLAIR));
        Assert.assertEquals("2.0.1", Buildx.getVersionName(Build.VERSION_CODES.ECLAIR_0_1));
        Assert.assertEquals("2.1.0", Buildx.getVersionName(Build.VERSION_CODES.ECLAIR_MR1));
        Assert.assertEquals("2.2.0", Buildx.getVersionName(Build.VERSION_CODES.FROYO));
        Assert.assertEquals("2.3.0", Buildx.getVersionName(Build.VERSION_CODES.GINGERBREAD));
        Assert.assertEquals("2.3.3", Buildx.getVersionName(Build.VERSION_CODES.GINGERBREAD_MR1));
        Assert.assertEquals("3.0.0", Buildx.getVersionName(Build.VERSION_CODES.HONEYCOMB));
        Assert.assertEquals("3.1.0", Buildx.getVersionName(Build.VERSION_CODES.HONEYCOMB_MR1));
        Assert.assertEquals("3.2.0", Buildx.getVersionName(Build.VERSION_CODES.HONEYCOMB_MR2));
        Assert.assertEquals("4.0.0", Buildx.getVersionName(Build.VERSION_CODES.ICE_CREAM_SANDWICH));
        Assert.assertEquals("4.0.3", Buildx.getVersionName(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1));
        Assert.assertEquals("4.1.0", Buildx.getVersionName(Build.VERSION_CODES.JELLY_BEAN));
        Assert.assertEquals("4.2.0", Buildx.getVersionName(Build.VERSION_CODES.JELLY_BEAN_MR1));
        Assert.assertEquals("4.3.0", Buildx.getVersionName(Build.VERSION_CODES.JELLY_BEAN_MR2));
        Assert.assertEquals("4.4.0", Buildx.getVersionName(Build.VERSION_CODES.KITKAT));
        Assert.assertEquals("4.4W", Buildx.getVersionName(Build.VERSION_CODES.KITKAT_WATCH));
        Assert.assertEquals("5.0.0", Buildx.getVersionName(Build.VERSION_CODES.LOLLIPOP));
        Assert.assertEquals("5.1.0", Buildx.getVersionName(Build.VERSION_CODES.LOLLIPOP_MR1));
        Assert.assertEquals("6.0.0", Buildx.getVersionName(Build.VERSION_CODES.M));
        Assert.assertEquals("7.0.0", Buildx.getVersionName(Build.VERSION_CODES.N));
        Assert.assertEquals("7.1.1", Buildx.getVersionName(Build.VERSION_CODES.N_MR1));
        Assert.assertEquals("8.0.0", Buildx.getVersionName(Build.VERSION_CODES.O));
        Assert.assertEquals("8.1.0", Buildx.getVersionName(Build.VERSION_CODES.O_MR1));
        Assert.assertEquals("9.0.0", Buildx.getVersionName(Build.VERSION_CODES.P));
        Assert.assertEquals("10.0.0", Buildx.getVersionName(Build.VERSION_CODES.Q));
        Assert.assertEquals("11.0.0", Buildx.getVersionName(Build.VERSION_CODES.R));

        SparseArray<String> array = new SparseArray<>();
        array.append(Build.VERSION_CODES.BASE, "1.0.0");
        array.append(Build.VERSION_CODES.BASE_1_1, "1.1.0");
        array.append(Build.VERSION_CODES.CUPCAKE, "1.5.0");
        array.append(Build.VERSION_CODES.DONUT, "1.6.0");
        array.append(Build.VERSION_CODES.ECLAIR, "2.0.0");
        array.append(Build.VERSION_CODES.ECLAIR_0_1, "2.0.1");
        array.append(Build.VERSION_CODES.ECLAIR_MR1, "2.1.0");
        array.append(Build.VERSION_CODES.FROYO, "2.2.0");
        array.append(Build.VERSION_CODES.GINGERBREAD, "2.3.0");
        array.append(Build.VERSION_CODES.GINGERBREAD_MR1, "2.3.3");
        array.append(Build.VERSION_CODES.HONEYCOMB, "3.0.0");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR1, "3.1.0");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR2, "3.2.0");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH, "4.0.0");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1, "4.0.3");
        array.append(Build.VERSION_CODES.JELLY_BEAN, "4.1.0");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR1, "4.2.0");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR2, "4.3.0");
        array.append(Build.VERSION_CODES.KITKAT, "4.4.0");
        array.append(Build.VERSION_CODES.KITKAT_WATCH, "4.4W");
        array.append(Build.VERSION_CODES.LOLLIPOP, "5.0.0");
        array.append(Build.VERSION_CODES.LOLLIPOP_MR1, "5.1.0");
        array.append(Build.VERSION_CODES.M, "6.0.0");
        array.append(Build.VERSION_CODES.N, "7.0.0");
        array.append(Build.VERSION_CODES.N_MR1, "7.1.1");
        array.append(Build.VERSION_CODES.O, "8.0.0");
        array.append(Build.VERSION_CODES.O_MR1, "8.1.0");
        array.append(Build.VERSION_CODES.P, "9.0.0");
        array.append(Build.VERSION_CODES.Q, "10.0.0");
        array.append(Build.VERSION_CODES.R, "11.0.0");

        Assert.assertEquals(array.get(Build.VERSION.SDK_INT), Buildx.getVersionName());
        Assert.assertEquals(array.get(Build.VERSION.SDK_INT), Buildx.getVersionName(Build.VERSION.SDK_INT));
    }

    @Test
    public void testGetVersionCodeName() {
        Assert.assertEquals("Base", Buildx.getVersionCodeName(Build.VERSION_CODES.BASE));
        Assert.assertEquals("Base1_1", Buildx.getVersionCodeName(Build.VERSION_CODES.BASE_1_1));
        Assert.assertEquals("Cupcake", Buildx.getVersionCodeName(Build.VERSION_CODES.CUPCAKE));
        Assert.assertEquals("Donut", Buildx.getVersionCodeName(Build.VERSION_CODES.DONUT));
        Assert.assertEquals("Eclair", Buildx.getVersionCodeName(Build.VERSION_CODES.ECLAIR));
        Assert.assertEquals("Eclair01", Buildx.getVersionCodeName(Build.VERSION_CODES.ECLAIR_0_1));
        Assert.assertEquals("EclairMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.ECLAIR_MR1));
        Assert.assertEquals("Froyo", Buildx.getVersionCodeName(Build.VERSION_CODES.FROYO));
        Assert.assertEquals("Gingerbread", Buildx.getVersionCodeName(Build.VERSION_CODES.GINGERBREAD));
        Assert.assertEquals("GingerbreadMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.GINGERBREAD_MR1));
        Assert.assertEquals("Honeycomb", Buildx.getVersionCodeName(Build.VERSION_CODES.HONEYCOMB));
        Assert.assertEquals("HoneycombMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.HONEYCOMB_MR1));
        Assert.assertEquals("HoneycombMR2", Buildx.getVersionCodeName(Build.VERSION_CODES.HONEYCOMB_MR2));
        Assert.assertEquals("IceCreamSandwich", Buildx.getVersionCodeName(Build.VERSION_CODES.ICE_CREAM_SANDWICH));
        Assert.assertEquals("IceCreamSandwichMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1));
        Assert.assertEquals("JellyBean", Buildx.getVersionCodeName(Build.VERSION_CODES.JELLY_BEAN));
        Assert.assertEquals("JellyBeanMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.JELLY_BEAN_MR1));
        Assert.assertEquals("JellyBeanMR2", Buildx.getVersionCodeName(Build.VERSION_CODES.JELLY_BEAN_MR2));
        Assert.assertEquals("KITKAT", Buildx.getVersionCodeName(Build.VERSION_CODES.KITKAT));
        Assert.assertEquals("KitkatWatch", Buildx.getVersionCodeName(Build.VERSION_CODES.KITKAT_WATCH));
        Assert.assertEquals("Lollipop", Buildx.getVersionCodeName(Build.VERSION_CODES.LOLLIPOP));
        Assert.assertEquals("LollipopMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.LOLLIPOP_MR1));
        Assert.assertEquals("Marshmallow", Buildx.getVersionCodeName(Build.VERSION_CODES.M));
        Assert.assertEquals("Nougat", Buildx.getVersionCodeName(Build.VERSION_CODES.N));
        Assert.assertEquals("NougatMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.N_MR1));
        Assert.assertEquals("Oreo", Buildx.getVersionCodeName(Build.VERSION_CODES.O));
        Assert.assertEquals("OreoMR1", Buildx.getVersionCodeName(Build.VERSION_CODES.O_MR1));
        Assert.assertEquals("Pie", Buildx.getVersionCodeName(Build.VERSION_CODES.P));
        Assert.assertEquals("10", Buildx.getVersionCodeName(Build.VERSION_CODES.Q));
        Assert.assertEquals("11", Buildx.getVersionCodeName(Build.VERSION_CODES.R));

        SparseArray<String> array = new SparseArray<>();
        array.append(Build.VERSION_CODES.BASE, "Base");
        array.append(Build.VERSION_CODES.BASE_1_1, "Base1_1");
        array.append(Build.VERSION_CODES.CUPCAKE, "Cupcake");
        array.append(Build.VERSION_CODES.DONUT, "Donut");
        array.append(Build.VERSION_CODES.ECLAIR, "Eclair");
        array.append(Build.VERSION_CODES.ECLAIR_0_1, "Eclair01");
        array.append(Build.VERSION_CODES.ECLAIR_MR1, "EclairMR1");
        array.append(Build.VERSION_CODES.FROYO, "Froyo");
        array.append(Build.VERSION_CODES.GINGERBREAD, "Gingerbread");
        array.append(Build.VERSION_CODES.GINGERBREAD_MR1, "GingerbreadMR1");
        array.append(Build.VERSION_CODES.HONEYCOMB, "Honeycomb");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR1, "HoneycombMR1");
        array.append(Build.VERSION_CODES.HONEYCOMB_MR2, "HoneycombMR2");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH, "IceCreamSandwich");
        array.append(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1, "IceCreamSandwichMR1");
        array.append(Build.VERSION_CODES.JELLY_BEAN, "JellyBean");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR1, "JellyBeanMR1");
        array.append(Build.VERSION_CODES.JELLY_BEAN_MR2, "JellyBeanMR2");
        array.append(Build.VERSION_CODES.KITKAT, "KITKAT");
        array.append(Build.VERSION_CODES.KITKAT_WATCH, "KitkatWatch");
        array.append(Build.VERSION_CODES.LOLLIPOP, "Lollipop");
        array.append(Build.VERSION_CODES.LOLLIPOP_MR1, "LollipopMR1");
        array.append(Build.VERSION_CODES.M, "Marshmallow");
        array.append(Build.VERSION_CODES.N, "Nougat");
        array.append(Build.VERSION_CODES.N_MR1, "NougatMR1");
        array.append(Build.VERSION_CODES.O, "Oreo");
        array.append(Build.VERSION_CODES.O_MR1, "OreoMR1");
        array.append(Build.VERSION_CODES.P, "Pie");
        array.append(Build.VERSION_CODES.Q, "10");
        array.append(Build.VERSION_CODES.R, "11");

        Assert.assertEquals(array.get(Build.VERSION.SDK_INT), Buildx.getVersionCodeName());
        Assert.assertEquals(array.get(Build.VERSION.SDK_INT), Buildx.getVersionCodeName(Build.VERSION.SDK_INT));
    }
}
