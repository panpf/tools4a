/*
 * Copyright (C) 2019 Peng fei Pan <panpfpanpf@outlook.com>
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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IntDef({AcceptPackageType.ALL, AcceptPackageType.ALL_AND_EXCLUDE_SELF, AcceptPackageType.SYSTEM,
        AcceptPackageType.USER_AND_EXCLUDE_SELF, AcceptPackageType.USER, AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF})
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
public @interface AcceptPackageType {
    int ALL = 0;
    int ALL_AND_EXCLUDE_SELF = 1;
    int USER = 2;
    int USER_AND_EXCLUDE_SELF = 3;
    int SYSTEM = 4;
    int SYSTEM_AND_EXCLUDE_SELF = 5;
}