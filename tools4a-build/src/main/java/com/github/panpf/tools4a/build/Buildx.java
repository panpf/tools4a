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

package com.github.panpf.tools4a.build;

import android.os.Build;

import androidx.annotation.NonNull;

public class Buildx {

    private Buildx() {
    }


    public static boolean isAtLeastJ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAtLeast16() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAtLeast4_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isAtLeastJMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isAtLeast17() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isAtLeast4_2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isAtLeastJMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isAtLeast18() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isAtLeast4_3() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isAtLeastK() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isAtLeast19() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isAtLeast4_4() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isAtLeastKW() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    public static boolean isAtLeast20() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    public static boolean isAtLeast4_4_W() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    public static boolean isAtLeastL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isAtLeast21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isAtLeast5_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isAtLeastLMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isAtLeast22() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isAtLeast5_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAtLeast23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAtLeast6_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isAtLeastN() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeast24() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeast7_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean isAtLeast25() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean isAtLeast7_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
    }

    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isAtLeast26() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isAtLeast8_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean isAtLeast27() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean isAtLeast8_1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1;
    }

    public static boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean isAtLeast28() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean isAtLeast9_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    public static boolean isAtLeast29() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    public static boolean isAtLeast10_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }

    public static boolean isAtLeast30() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }

    public static boolean isAtLeast11_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }


    @NonNull
    public static String getVersionName(int sdkVersion) {
        switch (sdkVersion) {
            case Build.VERSION_CODES.BASE:
                return "1.0.0";
            case Build.VERSION_CODES.BASE_1_1:
                return "1.1.0";
            case Build.VERSION_CODES.CUPCAKE:
                return "1.5.0";
            case Build.VERSION_CODES.DONUT:
                return "1.6.0";
            case Build.VERSION_CODES.ECLAIR:
                return "2.0.0";
            case Build.VERSION_CODES.ECLAIR_0_1:
                return "2.0.1";
            case Build.VERSION_CODES.ECLAIR_MR1:
                return "2.1.0";
            case Build.VERSION_CODES.FROYO:
                return "2.2.0";
            case Build.VERSION_CODES.GINGERBREAD:
                return "2.3.0";
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                return "2.3.3";
            case Build.VERSION_CODES.HONEYCOMB:
                return "3.0.0";
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                return "3.1.0";
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                return "3.2.0";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                return "4.0.0";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                return "4.0.3";
            case Build.VERSION_CODES.JELLY_BEAN:
                return "4.1.0";
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                return "4.2.0";
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                return "4.3.0";
            case Build.VERSION_CODES.KITKAT:
                return "4.4.0";
            case Build.VERSION_CODES.KITKAT_WATCH:
                return "4.4W";
            case Build.VERSION_CODES.LOLLIPOP:
                return "5.0.0";
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                return "5.1.0";
            case Build.VERSION_CODES.M:
                return "6.0.0";
            case Build.VERSION_CODES.N:
                return "7.0.0";
            case Build.VERSION_CODES.N_MR1:
                return "7.1.1";
            case Build.VERSION_CODES.O:
                return "8.0.0";
            case Build.VERSION_CODES.O_MR1:
                return "8.1.0";
            case Build.VERSION_CODES.P:
                return "9.0.0";
            case Build.VERSION_CODES.Q:
                return "10.0.0";
            case Build.VERSION_CODES.R:
                return "11.0.0";
            default:
                return "Unknown(" + sdkVersion + ')';
        }
    }

    @NonNull
    public static String getVersionName() {
        return getVersionName(Build.VERSION.SDK_INT);
    }

    @NonNull
    public static String getVersionCodeName(int sdkVersion) {
        switch (sdkVersion) {
            case Build.VERSION_CODES.BASE:
                return "Base";
            case Build.VERSION_CODES.BASE_1_1:
                return "Base1_1";
            case Build.VERSION_CODES.CUPCAKE:
                return "Cupcake";
            case Build.VERSION_CODES.DONUT:
                return "Donut";
            case Build.VERSION_CODES.ECLAIR:
                return "Eclair";
            case Build.VERSION_CODES.ECLAIR_0_1:
                return "Eclair01";
            case Build.VERSION_CODES.ECLAIR_MR1:
                return "EclairMR1";
            case Build.VERSION_CODES.FROYO:
                return "Froyo";
            case Build.VERSION_CODES.GINGERBREAD:
                return "Gingerbread";
            case Build.VERSION_CODES.GINGERBREAD_MR1:
                return "GingerbreadMR1";
            case Build.VERSION_CODES.HONEYCOMB:
                return "Honeycomb";
            case Build.VERSION_CODES.HONEYCOMB_MR1:
                return "HoneycombMR1";
            case Build.VERSION_CODES.HONEYCOMB_MR2:
                return "HoneycombMR2";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
                return "IceCreamSandwich";
            case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
                return "IceCreamSandwichMR1";
            case Build.VERSION_CODES.JELLY_BEAN:
                return "JellyBean";
            case Build.VERSION_CODES.JELLY_BEAN_MR1:
                return "JellyBeanMR1";
            case Build.VERSION_CODES.JELLY_BEAN_MR2:
                return "JellyBeanMR2";
            case Build.VERSION_CODES.KITKAT:
                return "KITKAT";
            case Build.VERSION_CODES.KITKAT_WATCH:
                return "KitkatWatch";
            case Build.VERSION_CODES.LOLLIPOP:
                return "Lollipop";
            case Build.VERSION_CODES.LOLLIPOP_MR1:
                return "LollipopMR1";
            case Build.VERSION_CODES.M:
                return "Marshmallow";
            case Build.VERSION_CODES.N:
                return "Nougat";
            case Build.VERSION_CODES.N_MR1:
                return "NougatMR1";
            case Build.VERSION_CODES.O:
                return "Oreo";
            case Build.VERSION_CODES.O_MR1:
                return "OreoMR1";
            case Build.VERSION_CODES.P:
                return "Pie";
            case Build.VERSION_CODES.Q:
                return "10";
            case Build.VERSION_CODES.R:
                return "11";
            default:
                return "Unknown(" + sdkVersion + ')';
        }
    }

    @NonNull
    public static String getVersionCodeName() {
        return getVersionCodeName(Build.VERSION.SDK_INT);
    }
}
