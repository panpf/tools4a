package com.github.panpf.tools4a.packages;

import android.content.pm.ApplicationInfo;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef(flag = true, value = {
        ApplicationInfo.FLAG_SYSTEM,
        ApplicationInfo.FLAG_DEBUGGABLE,
        ApplicationInfo.FLAG_HAS_CODE,
        ApplicationInfo.FLAG_PERSISTENT,
        ApplicationInfo.FLAG_FACTORY_TEST,
        ApplicationInfo.FLAG_ALLOW_TASK_REPARENTING,
        ApplicationInfo.FLAG_ALLOW_CLEAR_USER_DATA,
        ApplicationInfo.FLAG_UPDATED_SYSTEM_APP,
        ApplicationInfo.FLAG_TEST_ONLY,
        ApplicationInfo.FLAG_SUPPORTS_SMALL_SCREENS,
        ApplicationInfo.FLAG_SUPPORTS_NORMAL_SCREENS,
        ApplicationInfo.FLAG_SUPPORTS_LARGE_SCREENS,
        ApplicationInfo.FLAG_SUPPORTS_XLARGE_SCREENS,
        ApplicationInfo.FLAG_RESIZEABLE_FOR_SCREENS,
        ApplicationInfo.FLAG_SUPPORTS_SCREEN_DENSITIES,
        ApplicationInfo.FLAG_VM_SAFE_MODE,
        ApplicationInfo.FLAG_ALLOW_BACKUP,
        ApplicationInfo.FLAG_KILL_AFTER_RESTORE,
        ApplicationInfo.FLAG_RESTORE_ANY_VERSION,
        ApplicationInfo.FLAG_EXTERNAL_STORAGE,
        ApplicationInfo.FLAG_LARGE_HEAP,
        ApplicationInfo.FLAG_STOPPED,
        ApplicationInfo.FLAG_SUPPORTS_RTL,
        ApplicationInfo.FLAG_INSTALLED,
        ApplicationInfo.FLAG_IS_DATA_ONLY,
        ApplicationInfo.FLAG_IS_GAME,
        ApplicationInfo.FLAG_FULL_BACKUP_ONLY,
        ApplicationInfo.FLAG_USES_CLEARTEXT_TRAFFIC,
        ApplicationInfo.FLAG_MULTIARCH
})
@Retention(RetentionPolicy.SOURCE)
public @interface ApplicationInfoFlags {
}
