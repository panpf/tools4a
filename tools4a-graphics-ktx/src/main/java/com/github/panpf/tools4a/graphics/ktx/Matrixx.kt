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

package com.github.panpf.tools4a.graphics.ktx

import android.graphics.Matrix
import com.github.panpf.tools4a.graphics.Matrixx

/**
 * Helper method that 'unpacks' a Matrix and returns the required value
 *
 * @param whichValue - Which value from Matrix.M* to return
 * @return float - returned value
 */
inline fun Matrix.getValue(whichValue: Int): Float = Matrixx.getValue(this, whichValue)

/**
 * Get scale ratio
 */
inline fun Matrix.getScale(): Float = Matrixx.getScale(this)
