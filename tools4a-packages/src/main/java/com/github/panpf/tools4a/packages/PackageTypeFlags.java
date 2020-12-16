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

package com.github.panpf.tools4a.packages;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef(flag = true, value = {
        PackageTypeFlags.ONLY_USER,
        PackageTypeFlags.ONLY_SYSTEM,
        PackageTypeFlags.ONLY_RELEASE,
        PackageTypeFlags.ONLY_DEBUGGABLE,
        PackageTypeFlags.EXCLUDE_USER,
        PackageTypeFlags.EXCLUDE_SYSTEM,
        PackageTypeFlags.EXCLUDE_RELEASE,
        PackageTypeFlags.EXCLUDE_DEBUGGABLE,
        PackageTypeFlags.EXCLUDE_SELF
})
@Retention(RetentionPolicy.SOURCE)
public @interface PackageTypeFlags {
    int ONLY_USER = 1 << 1;
    int ONLY_SYSTEM = 1 << 2;

    int ONLY_RELEASE = 1 << 3;
    int ONLY_DEBUGGABLE = 1 << 4;

    int EXCLUDE_USER = 1 << 5;
    int EXCLUDE_SYSTEM = 1 << 6;

    int EXCLUDE_RELEASE = 1 << 7;
    int EXCLUDE_DEBUGGABLE = 1 << 8;

    int EXCLUDE_SELF = 1 << 9;
}