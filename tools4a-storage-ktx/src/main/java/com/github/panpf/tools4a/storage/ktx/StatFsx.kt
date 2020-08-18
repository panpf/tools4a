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

package com.github.panpf.tools4a.storage.ktx

import android.os.StatFs
import com.github.panpf.tools4a.storage.StatFsx

/*
 * StatFs related extension methods or properties
 */

val StatFs.availableBytesCompat: Long
    get() = StatFsx.getAvailableBytesCompat(this)

val StatFs.freeBytesCompat: Long
    get() = StatFsx.getFreeBytesCompat(this)

val StatFs.totalBytesCompat: Long
    get() = StatFsx.getTotalBytesCompat(this)