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

import android.graphics.Paint
import android.graphics.Rect
import com.github.panpf.tools4a.graphics.Paintx


/**
 * Get the width of the given text
 */
inline fun Paint.getTextWidth(text: String): Float = Paintx.getTextWidth(this, text)


/**
 * Get the text height of a given patin (bottom - top)
 */
inline fun Paint.getTextHeight(): Float = Paintx.getTextHeight(this)

/**
 * Get the text height of a given paint (descent - ascent)
 */
inline fun Paint.getTextHeightCompact(): Float = Paintx.getTextHeightCompact(this)

/**
 * Get the bounds of a given text
 */
inline fun Paint.getTextBounds(text: String): Rect = Paintx.getTextBounds(this, text)


/**
 * Get the baseline position of the vertical center when drawing the text, using bottom and top
 *
 * @param rectTop    Draw the top position of the panel
 * @param rectBottom Draw the bottom position of the panel
 */
inline fun Paint.getDrawTextVerticalCenterBaseLine(rectTop: Float, rectBottom: Float): Float = Paintx.getDrawTextVerticalCenterBaseLine(this, rectTop, rectBottom)

/**
 * Get the baseline position of the vertical center when drawing the text, using descent and ascent
 *
 * @param rectTop    Draw the top position of the panel
 * @param rectBottom Draw the bottom position of the panel
 */
inline fun Paint.getDrawTextVerticalCenterBaseLineCompact(rectTop: Float, rectBottom: Float): Float = Paintx.getDrawTextVerticalCenterBaseLineCompact(this, rectTop, rectBottom)
