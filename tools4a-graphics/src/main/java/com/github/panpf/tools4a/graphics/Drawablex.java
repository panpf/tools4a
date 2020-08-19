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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Drawablex {

    private Drawablex() {
    }

    /**
     * Convert Drawable to bitmap, use intrinsic size as the size of the new bitmap
     *
     * @param drawable    Source Drawable
     * @param config      Bitmap configuration, default value Bitmap.Config.ARGB_8888
     * @param reuseBitmap Reusable Bitmap
     */
    @NonNull
    public static Bitmap toBitmapWithIntrinsicSize(@NonNull Drawable drawable, @Nullable Bitmap.Config config, @Nullable Bitmap reuseBitmap) {
        final int intrinsicWidth = drawable.getIntrinsicWidth();
        final int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            throw new IllegalArgumentException("Both drawable intrinsicWidth and intrinsicHeight must be greater than 0");
        }

        Rect originBounds = new Rect(drawable.getBounds());

        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);

        Bitmap bitmap = reuseBitmap;
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config != null ? config : Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);

        // Restore bounds
        drawable.setBounds(originBounds);
        return bitmap;
    }

    /**
     * Convert Drawable to bitmap, use intrinsic size as the size of the new bitmap
     *
     * @param drawable Source Drawable
     * @param config   Bitmap configuration, default value Bitmap.Config.ARGB_8888
     */
    @NonNull
    public static Bitmap toBitmapWithIntrinsicSize(@NonNull Drawable drawable, @Nullable Bitmap.Config config) {
        return toBitmapWithIntrinsicSize(drawable, config, null);
    }

    /**
     * Convert Drawable to bitmap, use intrinsic size as the size of the new bitmap
     *
     * @param drawable    Source Drawable
     * @param reuseBitmap Reusable Bitmap
     */
    @NonNull
    public static Bitmap toBitmapWithIntrinsicSize(@NonNull Drawable drawable, @Nullable Bitmap reuseBitmap) {
        return toBitmapWithIntrinsicSize(drawable, null, reuseBitmap);
    }

    /**
     * Convert Drawable to bitmap, use intrinsic size as the size of the new bitmap
     *
     * @param drawable Source Drawable
     */
    @NonNull
    public static Bitmap toBitmapWithIntrinsicSize(@NonNull Drawable drawable) {
        return toBitmapWithIntrinsicSize(drawable, null, null);
    }


    /**
     * Convert Drawable to bitmap, use bounds size as the size of the new bitmap
     *
     * @param drawable    Source Drawable
     * @param config      Bitmap configuration, default value Bitmap.Config.ARGB_8888
     * @param reuseBitmap Reusable Bitmap
     */
    @NonNull
    public static Bitmap toBitmapWithBoundsSize(@NonNull Drawable drawable, @Nullable Bitmap.Config config, @Nullable Bitmap reuseBitmap) {
        Rect originBounds = new Rect(drawable.getBounds());
        if (originBounds.isEmpty()) throw new IllegalStateException("drawable bounds is empty");

        Bitmap bitmap = reuseBitmap;
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(originBounds.width(), originBounds.height(), config != null ? config : Bitmap.Config.ARGB_8888);
        }
        if (originBounds.left != 0 || originBounds.top != 0) {
            drawable.setBounds(0, 0, originBounds.width(), originBounds.height());
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);

        // Restore bounds
        if (originBounds.left != 0 || originBounds.top != 0) {
            drawable.setBounds(originBounds);
        }
        return bitmap;
    }

    /**
     * Convert Drawable to bitmap, use bounds size as the size of the new bitmap
     *
     * @param drawable Source Drawable
     * @param config   Bitmap configuration, default value Bitmap.Config.ARGB_8888
     */
    @NonNull
    public static Bitmap toBitmapWithBoundsSize(@NonNull Drawable drawable, @Nullable Bitmap.Config config) {
        return toBitmapWithBoundsSize(drawable, config, null);
    }

    /**
     * Convert Drawable to bitmap, use bounds size as the size of the new bitmap
     *
     * @param drawable    Source Drawable
     * @param reuseBitmap Reusable Bitmap
     */
    @NonNull
    public static Bitmap toBitmapWithBoundsSize(@NonNull Drawable drawable, @Nullable Bitmap reuseBitmap) {
        return toBitmapWithBoundsSize(drawable, null, reuseBitmap);
    }

    /**
     * Convert Drawable to bitmap, use bounds size as the size of the new bitmap
     *
     * @param drawable Source Drawable
     */
    @NonNull
    public static Bitmap toBitmapWithBoundsSize(@NonNull Drawable drawable) {
        return toBitmapWithBoundsSize(drawable, null, null);
    }


    /**
     * Change the color of the drawable
     */
    @NonNull
    public static <T extends Drawable> T changeColor(@NonNull T drawable, @ColorInt int color) {
        //noinspection unchecked
        T newDrawable = (T) drawable.mutate();
        newDrawable.setColorFilter(Colorx.createMatrixColorFilter(color));
        return drawable;
    }

    /**
     * Change the color of the resource drawable
     *
     * @param resId Drawable resource id
     */
    @NonNull
    public static Drawable changeResDrawableColor(@NonNull Context context, @DrawableRes int resId, @ColorInt int color) {
        return changeColor(context.getResources().getDrawable(resId), color);
    }
}
