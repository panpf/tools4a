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

package com.github.panpf.tools4a.fileprovider;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.File;

public class FileProviderx {

    private FileProviderx() {
    }


    /**
     * Get the shared file uri
     *
     * @param authority FileProvider authority
     */
    @NonNull
    public static Uri getShareFileUri(@NonNull Context context, @NonNull File file, @NonNull String authority) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }

    /**
     * Get the shared file uri. Read the authority of provider 'androidx.core.content.FileProvider' from AndroidManifest to generate file uri
     */
    @NonNull
    public static Uri getShareFileUri(@NonNull Context context, @NonNull File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ProviderInfo fileProviderInfo = null;
            try {
                fileProviderInfo = findFileProviderInfo(context);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (fileProviderInfo == null) {
                throw new IllegalStateException("Please configure the provider 'androidx.core.content.FileProvider' in the AndroidManifest.xml file");
            }
            return FileProvider.getUriForFile(context, fileProviderInfo.authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }

    @Nullable
    private static ProviderInfo findFileProviderInfo(@NonNull Context context) throws PackageManager.NameNotFoundException {
        String providerName = FileProvider.class.getName();
        PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PROVIDERS);
        ProviderInfo[] providerInfos = info.providers;
        if (providerInfos != null) {
            for (ProviderInfo providerInfo : providerInfos) {
                final String providerInfoName = providerInfo.name;
                if (providerInfoName != null && providerInfoName.equals(providerName)) {
                    return providerInfo;
                }
            }
        }
        return null;
    }
}
