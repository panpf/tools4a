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

package com.github.panpf.tools4a.graphics;

import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;

import androidx.annotation.NonNull;

public class Paintx {

    private Paintx() {
    }

    /**
     * Get the width of the given text
     */
    public static float getTextWidth(@NonNull Paint paint, @NonNull String text) {
        return paint.measureText(text);
    }

    /**
     * Get the width of the given text
     */
    public static float getTextWidth(float textSize, @NonNull String text) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        return paint.measureText(text);
    }


    /**
     * Get the text height of a given patin (bottom - top)
     */
    public static float getTextHeight(@NonNull Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        return fm.bottom - fm.top;
    }

    /**
     * Get the text height of a given size (bottom - top)
     */
    public static float getTextHeight(float textSize) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        return getTextHeight(paint);
    }

    /**
     * Get the text height of a given paint (descent - ascent)
     */
    public static float getTextHeightCompact(@NonNull Paint paint) {
        FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     * Get the text height of a given size (descent - ascent)
     */
    public static float getTextHeightCompact(float textSize) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        return getTextHeightCompact(paint);
    }


    /**
     * Get the bounds of a given text
     */
    public static Rect getTextBounds(@NonNull Paint paint, @NonNull String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }

    /**
     * Get the bounds of a given text
     */
    public static Rect getTextBounds(float textSize, @NonNull String text) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }


    /**
     * Get the baseline position of the vertical center when drawing the text, using bottom and top
     *
     * @param rectTop    Draw the top position of the panel
     * @param rectBottom Draw the bottom position of the panel
     */
    public static float getDrawTextVerticalCenterBaseLine(@NonNull Paint paint, float rectTop, float rectBottom) {
        return (rectBottom + rectTop - paint.getFontMetrics().bottom - paint.getFontMetrics().top) / 2;
    }

    /**
     * Get the baseline position of the vertical center when drawing the text, using descent and ascent
     *
     * @param rectTop    Draw the top position of the panel
     * @param rectBottom Draw the bottom position of the panel
     */
    public static float getDrawTextVerticalCenterBaseLineCompact(@NonNull Paint paint, float rectTop, float rectBottom) {
        return (rectBottom + rectTop - paint.getFontMetrics().descent - paint.getFontMetrics().ascent) / 2;
    }
}
