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

package com.github.panpf.tools4a.rom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.panpf.tools4a.systemproperties.SystemPropertiesx;

public class Romx {

    public static final int TYPE_MIUI = 1;
    public static final int TYPE_FLYME = 2;
    public static final int TYPE_EMUI = 3;
    public static final int TYPE_COLOR_OS = 4;
    public static final int TYPE_FUNTOUCH_OS = 5;
    public static final int TYPE_SMARTISAN_OS = 6;
    public static final int TYPE_H2OS = 7;
    public static final int TYPE_LINEAGE_OS = 8;
    public static final int TYPE_ANDROID = 9;
    public static final int TYPE_SAMSUNG = 10;
    public static final int TYPE_UNKNOWN = 999;

    private static final int TYPE;
    @NonNull
    private static final String TYPE_NAME;
    @NonNull
    private static final String VERSION_NAME;
    private static final String VERSION_CODE;
    @NonNull
    private static final String VERSION_INCREMENTAL;

    static {
        String[] versionInfos;
        if ((versionInfos = checkMIUI()) != null) {
            TYPE = TYPE_MIUI;
            TYPE_NAME = "MIUI";
        } else if ((versionInfos = checkEMUI()) != null) {
            TYPE = TYPE_EMUI;
            TYPE_NAME = "EMUI";
        } else if ((versionInfos = checkColorOS()) != null) {
            TYPE = TYPE_COLOR_OS;
            TYPE_NAME = "ColorOS";
        } else if ((versionInfos = checkFuntouchOS()) != null) {
            TYPE = TYPE_FUNTOUCH_OS;
            TYPE_NAME = "FuntouchOS";
        } else if ((versionInfos = checkH2OS()) != null) {
            TYPE = TYPE_H2OS;
            TYPE_NAME = "H2OS";
        } else if ((versionInfos = checkSmartisanOS()) != null) {
            TYPE = TYPE_SMARTISAN_OS;
            TYPE_NAME = "SmartisanOS";
        } else if ((versionInfos = checkFlyme()) != null) {
            TYPE = TYPE_FLYME;
            TYPE_NAME = "Flyme";
        } else if ((versionInfos = checkLineageOS()) != null) {
            TYPE = TYPE_LINEAGE_OS;
            TYPE_NAME = "LineageOS";
        } else if ((versionInfos = checkAndroid()) != null) {
            TYPE = TYPE_ANDROID;
            TYPE_NAME = "Android";
        } else if ((versionInfos = checkSamsung()) != null) {
            TYPE = TYPE_SAMSUNG;
            TYPE_NAME = "Samsung";
        } else {
            TYPE = TYPE_UNKNOWN;
            TYPE_NAME = "Unknown";
        }

        if (versionInfos != null) {
            VERSION_NAME = versionInfos[0];
            VERSION_CODE = versionInfos[1];
            VERSION_INCREMENTAL = versionInfos[2];
        } else {
            VERSION_NAME = "";
            VERSION_CODE = "";
            VERSION_INCREMENTAL = "";
        }
    }

    private Romx() {
    }

    @Nullable
    private static String[] checkMIUI() {
        String versionName = SystemPropertiesx.get("ro.miui.ui.version.name");
        if (!versionName.isEmpty()) {
            String versionCode = SystemPropertiesx.get("ro.miui.ui.version.code");
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, versionCode, versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkFlyme() {
        if ("flyme".equalsIgnoreCase(SystemPropertiesx.get("ro.build.user")) || !SystemPropertiesx.get("ro.flyme.published").isEmpty()) {
            String displayId = SystemPropertiesx.get("ro.build.display.id");
            String versionName;
            if (displayId.startsWith("Flyme OS ")) {
                versionName = displayId.substring("Flyme OS ".length());
            } else if (displayId.startsWith("Flyme ")) {
                versionName = displayId.substring("Flyme ".length());
            } else {
                versionName = displayId;
            }
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkColorOS() {
        String versionName = SystemPropertiesx.get("ro.build.version.opporom");
        if (!versionName.isEmpty()) {
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkEMUI() {
        String versionName = SystemPropertiesx.get("ro.build.version.emui");
        versionName = versionName.startsWith("EmotionUI_") ? versionName.substring("EmotionUI_".length()) : versionName;
        String versionCode = SystemPropertiesx.get("ro.oppo.version"); // oppo rom 的 build_prop 文件中定义了 ro.oppo.version 属性，但是值始终是空的
        String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
        if (!versionName.isEmpty()) {
            return new String[]{versionName, versionCode, versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkFuntouchOS() {
        if ("Funtouch".equalsIgnoreCase(SystemPropertiesx.get("ro.vivo.os.name"))) {
            String versionName = SystemPropertiesx.get("ro.vivo.os.version");
            String versionCode = SystemPropertiesx.get("ro.vivo.product.version");
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, versionCode, versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkSmartisanOS() {
        String versionName = SystemPropertiesx.get("ro.smartisan.version");
        if (!versionName.isEmpty()) {
            String[] versionItems = versionName.split("-");
            versionName = versionItems.length > 0 ? versionItems[0] : null;
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkH2OS() {
        String versionName = SystemPropertiesx.get("ro.rom.version");
        if (versionName.toLowerCase().startsWith("H2OS V".toLowerCase())) {
            versionName = versionName.substring("H2OS V".length());
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else if (versionName.toLowerCase().startsWith("Hydrogen OS".toLowerCase())) {
            versionName = versionName.substring("Hydrogen OS".length()).trim();
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkLineageOS() {
        if ("lineage".equalsIgnoreCase(SystemPropertiesx.get("ro.build.user"))) {
            String versionName = SystemPropertiesx.get("ro.cm.build.version");
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkAndroid() {
        if ("android-build".equalsIgnoreCase(SystemPropertiesx.get("ro.build.user"))) {
            String versionName = SystemPropertiesx.get("ro.build.version.release");
            String versionCode = SystemPropertiesx.get("ro.build.version.sdk");
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, versionCode, versionIncremental};
        } else {
            return null;
        }
    }

    @Nullable
    private static String[] checkSamsung() {
        if ("dpi".equalsIgnoreCase(SystemPropertiesx.get("ro.build.user"))) {
            String versionName = SystemPropertiesx.get("ro.build.display.id");
            String versionIncremental = SystemPropertiesx.get("ro.build.version.incremental");
            return new String[]{versionName, "", versionIncremental};
        } else {
            return null;
        }
    }

    /**
     * Get the ROM type, Ranges: {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI},
     * {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}
     */
    public static int getType() {
        return TYPE;
    }

    /**
     * Return true if the current ROM type is the same as the specified type
     *
     * @param type Ranges: {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI},
     *             {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}, {@link Romx#TYPE_MIUI}
     */
    public static boolean is(int type) {
        return TYPE == type;
    }

    /**
     * Return true if the ROM type is MIUI
     */
    public static boolean isMIUI() {
        return TYPE == TYPE_MIUI;
    }

    /**
     * Return true if the ROM type is EMUI
     */
    public static boolean isEMUI() {
        return TYPE == TYPE_EMUI;
    }

    /**
     * Return true if the ROM type is Flyme
     */
    public static boolean isFlyme() {
        return TYPE == TYPE_FLYME;
    }

    /**
     * Return true if the ROM type is Color OS
     */
    public static boolean isColorOS() {
        return TYPE == TYPE_COLOR_OS;
    }

    /**
     * Return true if the ROM type is Funtouch OS
     */
    public static boolean isFuntouchOS() {
        return TYPE == TYPE_FUNTOUCH_OS;
    }

    /**
     * Return true if the ROM type is Smartisan OS
     */
    public static boolean isSmartisanOS() {
        return TYPE == TYPE_SMARTISAN_OS;
    }

    /**
     * Return true if the ROM type is H2OS
     */
    public static boolean isH2OS() {
        return TYPE == TYPE_H2OS;
    }

    /**
     * Return true if the ROM type is Lineage OS
     */
    public static boolean isLineageOS() {
        return TYPE == TYPE_LINEAGE_OS;
    }

    /**
     * Return true if the ROM type is origin Android
     */
    public static boolean isAndroid() {
        return TYPE == TYPE_ANDROID;
    }

    /**
     * Return true if the ROM type is origin Samsung
     */
    public static boolean isSamsung() {
        return TYPE == TYPE_SAMSUNG;
    }

    /**
     * Return true if the ROM type is Unknown
     */
    public static boolean isUnknown() {
        return TYPE == TYPE_UNKNOWN;
    }

    /**
     * Get the name of the ROM type
     */
    @NonNull
    public static String getTypeName() {
        return TYPE_NAME;
    }

    /**
     * Get the ROM version name
     */
    @NonNull
    public static String getVersionName() {
        return VERSION_NAME;
    }

    /**
     * Get the ROM version code
     */
    @NonNull
    public static String getVersionCode() {
        return VERSION_CODE;
    }

    /**
     * Get the ROM version incremental
     */
    @NonNull
    public static String getVersionIncremental() {
        return VERSION_INCREMENTAL;
    }

    @NonNull
    public static String getInfo() {
        return TYPE_NAME + ":" + VERSION_NAME + ":" + VERSION_CODE + ":" + VERSION_INCREMENTAL;
    }
}
