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

@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION")

package com.github.panpf.tools4a.display.ktx

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View
import com.github.panpf.tools4a.display.Displayx


/*
 * Extension method related to screen display
 */


/**
 * Get screen size
 */
inline fun Context.getScreenSize(): Point = Displayx.getScreenSize(this)

/**
 * Get screen width
 */
inline fun Context.getScreenWidth(): Int = Displayx.getScreenWidth(this)

/**
 * Get screen height
 */
inline fun Context.getScreenHeight(): Int = Displayx.getScreenHeight(this)

/**
 * Get actionBar size
 */
inline fun Context.getActionBarSize(): Int = Displayx.getActionBarSize(this)


/**
 * Get DisplayMetrics
 */
inline fun Context.getDisplayMetrics(): DisplayMetrics = Displayx.getMetrics(this)

/**
 * Get screen density
 */
inline fun Context.getDisplayDensity(): Float = Displayx.getDensity(this)

/**
 * Get screen density dpi
 */
inline fun Context.getDisplayDensityDpi(): Int = Displayx.getDensityDpi(this)


/**
 * Get the screen rotation angle, 0, 90, 180, 270
 */
inline fun Context.getDisplayRotation(): Int = Displayx.getRotation(this)

/**
 * Get the screen rotation angle, 0, 90, 180, 270
 */
inline fun View.getDisplayRotation(): Int = Displayx.getRotation(this)


/**
 * Return true if the current screen orientation is portrait
 */
inline fun Context.isOrientationPortrait(): Boolean = Displayx.isOrientationPortrait(this)

/**
 * Return true if the current screen orientation is portrait
 */
inline fun View.isOrientationPortrait(): Boolean = Displayx.isOrientationPortrait(this)


/**
 * Return true if the current screen orientation is landscape
 */
inline fun Context.isOrientationLandscape(): Boolean = Displayx.isOrientationLandscape(this)

/**
 * Return true if the current screen orientation is landscape
 */
inline fun View.isOrientationLandscape(): Boolean = Displayx.isOrientationLandscape(this)


/**
 * Return true if the current screen orientation is undefined
 */
inline fun Context.isOrientationUndefined(): Boolean = Displayx.isOrientationUndefined(this)

/**
 * Return true if the current screen orientation is undefined
 */
inline fun View.isOrientationUndefined(): Boolean = Displayx.isOrientationUndefined(this)


/**
 * Get the height of the system status bar.
 *
 * @return The height of the status bar (in pixels).
 */
inline fun Context.getStatusBarHeight(): Int = Displayx.getStatusBarHeight(this)


/**
 * Whether you have a navigation bar
 */
inline fun Context.hasNavigationBar(): Boolean = Displayx.hasNavigationBar(this)

/**
 * Get the height of the navigation bar
 */
inline fun Context.getNavigationBarHeight(): Int = Displayx.getNavigationBarHeight(this)

/**
 * Get the width of the navigation bar
 */
inline fun Context.getNavigationBarWidth(): Int = Displayx.getNavigationBarWidth(this)