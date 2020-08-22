package com.github.panpf.tools4a.packages;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SuppressLint("UniqueConstants")
@IntDef(flag = true, value = {
        PackageManager.GET_ACTIVITIES,
        PackageManager.GET_CONFIGURATIONS,
        PackageManager.GET_GIDS,
        PackageManager.GET_INSTRUMENTATION,
        PackageManager.GET_INTENT_FILTERS,
        PackageManager.GET_META_DATA,
        PackageManager.GET_PERMISSIONS,
        PackageManager.GET_PROVIDERS,
        PackageManager.GET_RECEIVERS,
        PackageManager.GET_SERVICES,
        PackageManager.GET_SHARED_LIBRARY_FILES,
        PackageManager.GET_SIGNATURES,
        PackageManager.GET_SIGNING_CERTIFICATES,
        PackageManager.GET_URI_PERMISSION_PATTERNS,
        PackageManager.MATCH_UNINSTALLED_PACKAGES,
        PackageManager.MATCH_DISABLED_COMPONENTS,
        PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS,
        PackageManager.MATCH_SYSTEM_ONLY,
//        PackageManager.MATCH_FACTORY_ONLY,
//        PackageManager.MATCH_DEBUG_TRIAGED_MISSING,
//        PackageManager.MATCH_INSTANT,
        PackageManager.MATCH_APEX,
        PackageManager.GET_DISABLED_COMPONENTS,
        PackageManager.GET_DISABLED_UNTIL_USED_COMPONENTS,
        PackageManager.GET_UNINSTALLED_PACKAGES,
//        PackageManager.MATCH_HIDDEN_UNTIL_INSTALLED_COMPONENTS,
})
@Retention(RetentionPolicy.SOURCE)
public @interface PackageInfoFlags {
}
