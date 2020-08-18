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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4a.permission.ktx

import android.content.Context
import com.github.panpf.tools4a.permission.Permissionx

/*
 * Permission related extension methods or properties
 */

/**
 * Return true if all specified permissions have been granted, false otherwise
 */
inline fun Context.isGrantPermissions(vararg permissions: String): Boolean = Permissionx.isGrantPermissions(this, *permissions)

/**
 * Filter all denied permissions
 */
inline fun Context.filterDeniedPermissions(vararg permissions: String): Array<String> = Permissionx.filterDeniedPermissions(this, *permissions)