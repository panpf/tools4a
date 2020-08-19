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

package com.github.panpf.tools4a.view.ktx

import android.view.Window
import androidx.annotation.FloatRange
import com.github.panpf.tools4a.view.Windowx

/**
 * This can be used to override the user's preferred brightness of the screen.
 * A value of less than 0, the default, means to use the preferred screen brightness.
 * 0 to 1 adjusts the brightness from dark to full bright.
 */
@FloatRange(from = -1.0, to = 1.0)
inline fun Window.getBrightness(): Float = Windowx.getBrightness(this)

/**
 * Set the brightness of the Activity window (you can see the effect, the brightness of the system will not change)
 *
 * @param brightness This can be used to override the user's preferred brightness of the screen.
 * A value of less than 0, the default, means to use the preferred screen brightness.
 * 0 to 1 adjusts the brightness from dark to full bright.
 */
inline fun Window.setBrightness(@FloatRange(from = -1.0, to = 1.0) brightness: Float) = Windowx.setBrightness(this, brightness)

/**
 * Return true if the current window use the preferred screen brightness.
 */
inline fun Window.isBrightnessFlowSystem(): Boolean = Windowx.isBrightnessFlowSystem(this)
