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

package com.github.panpf.tools4a.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.panpf.tools4a.display.Displayx;

public class Viewx {

    private Viewx() {
    }


    /**
     * Set the Layout width of the given View, if there is no LayoutParams, it will not be set
     */
    public static void setLayoutWidth(@NonNull View view, int newWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = newWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * Set the Layout width of the given View, if there is no LayoutParams, create a new LayoutParams and set it up
     */
    public static void setLayoutWidthOrInitSize(@NonNull View view, int newWidth, int newHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(newWidth, newHeight);
        } else {
            layoutParams.width = newWidth;
        }
        view.setLayoutParams(layoutParams);
    }

    /**
     * Set the Layout height of the given View, if there is no LayoutParams, it will not be set
     */
    public static void setLayoutHeight(@NonNull View view, int newHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = newHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * Set the Layout height of the given View, if there is no LayoutParams, create a new LayoutParams and set it up
     */
    public static void setLayoutHeightOrInitSize(@NonNull View view, int newWidth, int newHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(newWidth, newHeight);
        } else {
            layoutParams.height = newHeight;
        }
        view.setLayoutParams(layoutParams);
    }

    /**
     * Set the Layout width and height of the given View, if there is no LayoutParams, it will not be set
     */
    public static void setLayoutSize(@NonNull View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(width, height);
        } else {
            layoutParams.width = width;
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    /**
     * Set the Layout top margin of the given View, if there is no LayoutParams, it will not be set
     */
    public static void setLayoutMarginTop(@NonNull View view, int newMarinTop) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = newMarinTop;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * Increase the layout width of the given View, if there is no LayoutParams, it will not be set
     */
    public static void addLayoutWidth(@NonNull View view, int addWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width += addWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * Increase the layout height of the given View, if there is no LayoutParams, it will not be set
     */
    public static void addLayoutHeight(@NonNull View view, int addHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height += addHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * Increase the layout width and height of the given View, if there is no LayoutParams, it will not be set
     */
    public static void addLayoutSize(@NonNull View view, int addWidth, int addHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width += addWidth;
            layoutParams.height += addHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * Increase the layout top margin of the given View, if there is no LayoutParams, it will not be set
     */
    public static void addLayoutMarginTop(@NonNull View view, int addMarinTop) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin += addMarinTop;
            view.setLayoutParams(layoutParams);
        }
    }


    /**
     * Convert View to Bitmap
     *
     * @param scale scaling ratio
     */
    @NonNull
    public static Bitmap toBitmap(@NonNull View view, @NonNull Bitmap.Config config, float scale) {
        int bitmapWidth = view.getWidth();
        int bitmapHeight = view.getHeight();
        Matrix matrix = null;
        if (scale > (float) 0) {
            bitmapWidth = (int) ((float) bitmapWidth * scale);
            bitmapHeight = (int) ((float) bitmapHeight * scale);
            matrix = new Matrix();
            matrix.setScale(scale, scale);
        }

        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, config);
        Canvas canvas = new Canvas(bitmap);
        if (matrix != null) {
            canvas.setMatrix(matrix);
        }
        view.draw(canvas);
        return bitmap;
    }

    /**
     * Convert View to Bitmap
     */
    @NonNull
    public static Bitmap toBitmap(@NonNull View view, @NonNull Bitmap.Config config) {
        return toBitmap(view, config, 0f);
    }

    /**
     * Convert View to Bitmap, The maximum Bitmap width is [maxWidth] and the height is scaled accordingly.
     */
    @NonNull
    public static Bitmap toBitmapByMaxWidth(@NonNull View view, @NonNull Bitmap.Config config, int maxWidth) {
        return toBitmap(view, config, Math.min((float) maxWidth / (float) view.getWidth(), 1.0F));
    }

    /**
     * Convert View to Bitmap, The maximum Bitmap height is [maxHeight] and the width is scaled accordingly.
     */
    @NonNull
    public static Bitmap toBitmapByMaxHeight(@NonNull View view, @NonNull Bitmap.Config config, int maxHeight) {
        return toBitmap(view, config, Math.min((float) maxHeight / (float) view.getHeight(), 1.0F));
    }


    /**
     * Inflate a new view hierarchy from the specified xml resource. Throws {@link android.view.InflateException} if there is an error.
     *
     * @param resource     ID for an XML layout resource to load (e.g.,
     *                     <code>R.layout.main_page</code>)
     * @param root         Optional view to be the parent of the generated hierarchy (if
     *                     <em>attachToRoot</em> is true), or else simply an object that
     *                     provides a set of LayoutParams values for root of the returned
     *                     hierarchy (if <em>attachToRoot</em> is false.)
     * @param attachToRoot Whether the inflated hierarchy should be attached to
     *                     the root parameter? If false, root is only used to create the
     *                     correct subclass of LayoutParams for the root view in the XML.
     * @return The root View of the inflated hierarchy. If root was supplied and
     * attachToRoot is true, this is root; otherwise it is the root of
     * the inflated XML file.
     */
    @NonNull
    public static View inflateLayout(@NonNull Context context, @LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(resource, root, attachToRoot);
    }

    /**
     * Inflate a new view hierarchy from the specified xml resource. Throws {@link android.view.InflateException} if there is an error.
     *
     * @param resource ID for an XML layout resource to load (e.g.,
     *                 <code>R.layout.main_page</code>)
     * @param root     Optional view to be the parent of the generated hierarchy (if
     *                 <em>attachToRoot</em> is true), or else simply an object that
     *                 provides a set of LayoutParams values for root of the returned
     *                 hierarchy (if <em>attachToRoot</em> is false.)
     * @return The root View of the inflated hierarchy. If root was supplied and
     * attachToRoot is true, this is root; otherwise it is the root of
     * the inflated XML file.
     */
    @NonNull
    public static View inflateLayout(@NonNull Context context, @LayoutRes int resource, @Nullable ViewGroup root) {
        return LayoutInflater.from(context).inflate(resource, root, false);
    }

    /**
     * Inflate a new view hierarchy from the specified xml resource. Throws {@link android.view.InflateException} if there is an error.
     *
     * @return The root View of the inflated hierarchy. If root was supplied and
     * attachToRoot is true, this is root; otherwise it is the root of
     * the inflated XML file.
     */
    @NonNull
    public static View inflateLayout(@NonNull Context context, @LayoutRes int resource) {
        return LayoutInflater.from(context).inflate(resource, null, false);
    }

    /**
     * If the system version is greater than or equal to KITKAT, use the status bar height to increase the top padding of the given view.
     */
    public static void addPaddingTopByStatusBarHeight(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = Displayx.getStatusBarHeight(view.getContext());
            if (statusBarHeight > 0) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + statusBarHeight, view.getPaddingRight(), view.getPaddingBottom());
            }
        }
    }
}