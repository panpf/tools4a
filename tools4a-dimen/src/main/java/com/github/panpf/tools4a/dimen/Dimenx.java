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

package com.github.panpf.tools4a.dimen;

import android.content.res.Resources;

public class Dimenx {

    private Dimenx() {
    }


    public static float dp2px(float value) {
        return value * Resources.getSystem().getDisplayMetrics().density;
    }

    public static float dp2px(int value) {
        return (float) value * Resources.getSystem().getDisplayMetrics().density;
    }


    public static int dp2pxInt(float value) {
        return (int) ((value * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int dp2pxInt(int value) {
        return (int) (((float) value * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }



    public static float sp2px(float value) {
        return value * Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static float sp2px(int value) {
        return (float) value * Resources.getSystem().getDisplayMetrics().scaledDensity;
    }


    public static int sp2pxInt(float value) {
        return (int) ((value * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2pxInt(int value) {
        return (int) (((float) value * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }



    public static float pt2px(float value) {
        return value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 72);
    }

    public static float pt2px(int value) {
        return (float) value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 72);
    }


    public static int pt2pxInt(float value) {
        return (int) ((value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 72)) + 0.5f);
    }

    public static int pt2pxInt(int value) {
        return (int) (((float) value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 72)) + 0.5f);
    }



    public static float in2px(float value) {
        return value * Resources.getSystem().getDisplayMetrics().xdpi;
    }

    public static float in2px(int value) {
        return (float) value * Resources.getSystem().getDisplayMetrics().xdpi;
    }


    public static int in2pxInt(float value) {
        return (int) ((value * Resources.getSystem().getDisplayMetrics().xdpi) + 0.5f);
    }

    public static int in2pxInt(int value) {
        return (int) (((float) value * Resources.getSystem().getDisplayMetrics().xdpi) + 0.5f);
    }



    public static float mm2px(float value) {
        return value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 25.4f);
    }

    public static float mm2px(int value) {
        return (float) value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 25.4f);
    }


    public static int mm2pxInt(float value) {
        return (int) ((value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 25.4f)) + 0.5f);
    }

    public static int mm2pxInt(int value) {
        return (int) (((float) value * Resources.getSystem().getDisplayMetrics().xdpi * (1.0f / 25.4f)) + 0.5f);
    }



    public static float px2dp(float value) {
        return value / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float px2dp(int value) {
        return (float) value / Resources.getSystem().getDisplayMetrics().density;
    }


    public static float px2sp(float value) {
        return value / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    public static float px2sp(int value) {
        return (float) value / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }


    public static float px2pt(float value) {
        return value / Resources.getSystem().getDisplayMetrics().xdpi / (1.0f / 72);
    }

    public static float px2pt(int value) {
        return (float) value / Resources.getSystem().getDisplayMetrics().xdpi / (1.0f / 72);
    }


    public static float px2in(float value) {
        return value / Resources.getSystem().getDisplayMetrics().xdpi;
    }

    public static float px2in(int value) {
        return (float) value / Resources.getSystem().getDisplayMetrics().xdpi;
    }


    public static float px2mm(float value) {
        return value / Resources.getSystem().getDisplayMetrics().xdpi / (1.0f / 25.4f);
    }

    public static float px2mm(int value) {
        return (float) value / Resources.getSystem().getDisplayMetrics().xdpi / (1.0f / 25.4f);
    }
}