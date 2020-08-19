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

import android.graphics.ColorMatrixColorFilter
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.github.panpf.tools4a.graphics.Colorx

/**
 * Get color alpha value
 *
 * @receiver rgb color value
 */
@IntRange(from = 0, to = 255)
inline fun Int.getColorAlpha(): Int = Colorx.getAlpha(this)

/**
 * Modify color alpha value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newAlpha New alpha value (0...255)
 */
@ColorInt
inline fun Int.setColorAlpha(@IntRange(from = 0, to = 255) newAlpha: Int): Int = Colorx.setAlpha(this, newAlpha)

/**
 * Multiply [addRate] based on the color original alpha value to return the new RGB color value
 *
 * @receiver rgb color value
 * @param addRate Add the ratio (0...1)
 */
@ColorInt
inline fun Int.addColorAlpha(@FloatRange(from = 0.0, to = 1.0) addRate: Float): Int = Colorx.addAlpha(this, addRate)

/**
 * Get color HSV hue value
 *
 * @receiver rgb color value
 */
@FloatRange(from = 0.0, to = 360.0)
inline fun Int.getColorHSVHue(): Float = Colorx.getHSVHue(this)

/**
 * Modify color HSV hue value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newHue New HSV hue value (0...360)
 */
@ColorInt
inline fun Int.setColorHSVHue(@FloatRange(from = 0.0, to = 360.0) newHue: Float): Int = Colorx.setHSVHue(this, newHue)

/**
 * Get color HSV saturation value
 *
 * @receiver rgb color value
 */
@FloatRange(from = 0.0, to = 1.0)
inline fun Int.getColorHSVSaturation(): Float = Colorx.getHSVSaturation(this)

/**
 * Modify color HSV saturation value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newSaturation New HSV saturation value (0...1)
 */
@ColorInt
inline fun Int.setColorHSVSaturation(@FloatRange(from = 0.0, to = 1.0) newSaturation: Float): Int = Colorx.setHSVSaturation(this, newSaturation)

/**
 * Multiply [addRate] based on the color original HSV saturation value to return the new RGB color value
 *
 * @receiver rgb color value
 * @param addRate Add the ratio (0...1)
 */
@ColorInt
inline fun Int.addColorHSVSaturation(@FloatRange(from = 0.0, to = 1.0) addRate: Float): Int = Colorx.addHSVSaturation(this, addRate)

/**
 * Get color HSV 'value' value
 *
 * @receiver rgb color value
 */
@FloatRange(from = 0.0, to = 1.0)
inline fun Int.getColorHSVValue(): Float = Colorx.getHSVValue(this)

/**
 * Modify color HSV 'value' value, Return a new RGB color value
 *
 * @receiver rgb color value
 * @param newValue New HSV 'value' value (0...1)
 */
@ColorInt
inline fun Int.setColorHSVValue(@FloatRange(from = 0.0, to = 1.0) newValue: Float): Int = Colorx.setHSVValue(this, newValue)

/**
 * Multiply [addRate] based on the color original HSV 'value' value to return the new RGB color value
 *
 * @receiver rgb color value
 * @param addRate Add the ratio (0...1)
 */
@ColorInt
inline fun Int.addColorHSVValue(@FloatRange(from = 0.0, to = 1.0) addRate: Float): Int = Colorx.addHSVValue(this, addRate)

/**
 * Returns true if the color is a bright color, used to prevent the background color from being close to the text color, causing the text to be invisible
 *
 * @receiver rgb color value
 */
inline fun Int.isLightColor(): Boolean = Colorx.isLight(this)

/**
 * Create a ColorMatrixColorFilter that can be used to change the color of the Drawable
 *
 * @receiver rgb color value (alpha value is useless)
 */
inline fun Int.createMatrixColorFilter(): ColorMatrixColorFilter = Colorx.createMatrixColorFilter(this)


/**
 * This inline function returns the calculated in-between value for a color
 * given integers that represent the start and end values in the four
 * bytes of the 32-bit int. Each channel is separately linearly interpolated
 * and the resulting calculated values are recombined into the return value.
 *
 * @receiver startValue A 32-bit int value representing colors in the
 * @param endValue A 32-bit int value representing colors in the
 * @param fraction The fraction from the starting to the ending values
 * separate bytes of the parameter
 * separate bytes of the parameter
 * @return A value that is calculated to be the linearly interpolated
 * result, derived by separating the start and end values into separate
 * color channels and interpolating each one separately, recombining the
 * resulting values in the same way.
 */
@ColorInt
inline fun Int.colorArgbEvaluate(@ColorInt endValue: Int, @FloatRange(from = 0.0, to = 1.0) fraction: Float): Int = Colorx.argbEvaluate(this, endValue, fraction)
