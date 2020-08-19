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

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.github.panpf.tools4a.view.Viewx


/*
 * View related extension method
 */


/**
 * Set the Layout width of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.setLayoutWidth(newWidth: Int) = Viewx.setLayoutWidth(this, newWidth)

/**
 * Set the Layout width of the given View, if there is no LayoutParams, create a new LayoutParams and set it up
 */
inline fun View.setLayoutWidthOrInitSize(newWidth: Int, newHeight: Int) = Viewx.setLayoutWidthOrInitSize(this, newWidth, newHeight)

/**
 * Set the Layout height of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.setLayoutHeight(newHeight: Int) = Viewx.setLayoutHeight(this, newHeight)

/**
 * Set the Layout height of the given View, if there is no LayoutParams, create a new LayoutParams and set it up
 */
inline fun View.setLayoutHeightOrInitSize(newWidth: Int, newHeight: Int) = Viewx.setLayoutHeightOrInitSize(this, newWidth, newHeight)

/**
 * Set the Layout width and height of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.setLayoutSize(width: Int, height: Int) = Viewx.setLayoutSize(this, width, height)

/**
 * Set the Layout top margin of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.setLayoutMarginTop(newMarinTop: Int) = Viewx.setLayoutMarginTop(this, newMarinTop)


/**
 * Increase the layout width of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.addLayoutWidth(addWidth: Int) = Viewx.addLayoutWidth(this, addWidth)

/**
 * Increase the layout height of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.addLayoutHeight(addHeight: Int) = Viewx.addLayoutHeight(this, addHeight)

/**
 * Increase the layout width and height of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.addLayoutSize(addWidth: Int, addHeight: Int) = Viewx.addLayoutSize(this, addWidth, addHeight)

/**
 * Increase the layout top margin of the given View, if there is no LayoutParams, it will not be set
 */
inline fun View.addLayoutMarginTop(addMarinTop: Int) = Viewx.addLayoutMarginTop(this, addMarinTop)


/**
 * Convert View to Bitmap
 *
 * @param scale scaling ratio
 */
inline fun View.toBitmap(config: Bitmap.Config, scale: Float): Bitmap = Viewx.toBitmap(this, config, scale)

/**
 * Convert View to Bitmap
 */
inline fun View.toBitmap(config: Bitmap.Config): Bitmap = Viewx.toBitmap(this, config)

/**
 * Convert View to Bitmap, The maximum Bitmap width is [maxWidth] and the height is scaled accordingly.
 */
inline fun View.toBitmapByMaxWidth(config: Bitmap.Config, maxWidth: Int): Bitmap = Viewx.toBitmapByMaxWidth(this, config, maxWidth)

/**
 * Convert View to Bitmap, The maximum Bitmap height is [maxHeight] and the width is scaled accordingly.
 */
inline fun View.toBitmapByMaxHeight(config: Bitmap.Config, maxHeight: Int): Bitmap = Viewx.toBitmapByMaxHeight(this, config, maxHeight)


/**
 * Inflate a new view hierarchy from the specified xml resource. Throws {@link android.view.InflateException} if there is an error.
 *
 * @param resource ID for an XML layout resource to load (e.g.,
 *        <code>R.layout.main_page</code>)
 * @param root Optional view to be the parent of the generated hierarchy (if
 *        <em>attachToRoot</em> is true), or else simply an object that
 *        provides a set of LayoutParams values for root of the returned
 *        hierarchy (if <em>attachToRoot</em> is false.)
 * @param attachToRoot Whether the inflated hierarchy should be attached to
 *        the root parameter? If false, root is only used to create the
 *        correct subclass of LayoutParams for the root view in the XML.
 * @return The root View of the inflated hierarchy. If root was supplied and
 *         attachToRoot is true, this is root; otherwise it is the root of
 *         the inflated XML file.
 */
inline fun Context.inflateLayout(@LayoutRes resource: Int, root: ViewGroup, attachToRoot: Boolean): View =
        Viewx.inflateLayout(this, resource, root, attachToRoot)

/**
 * Inflate a new view hierarchy from the specified xml resource. Throws {@link android.view.InflateException} if there is an error.
 *
 * @param resource ID for an XML layout resource to load (e.g.,
 *        <code>R.layout.main_page</code>)
 * @param root Optional view to be the parent of the generated hierarchy (if
 *        <em>attachToRoot</em> is true), or else simply an object that
 *        provides a set of LayoutParams values for root of the returned
 *        hierarchy (if <em>attachToRoot</em> is false.)
 * @return The root View of the inflated hierarchy. If root was supplied and
 *         attachToRoot is true, this is root; otherwise it is the root of
 *         the inflated XML file.
 */
inline fun Context.inflateLayout(@LayoutRes resource: Int, root: ViewGroup): View = Viewx.inflateLayout(this, resource, root)

/**
 * Inflate a new view hierarchy from the specified xml resource. Throws {@link android.view.InflateException} if there is an error.
 *
 * @return The root View of the inflated hierarchy. If root was supplied and
 *         attachToRoot is true, this is root; otherwise it is the root of
 *         the inflated XML file.
 */
inline fun Context.inflateLayout(@LayoutRes resource: Int): View = Viewx.inflateLayout(this, resource)


/**
 * If the system version is greater than or equal to KITKAT, use the status bar height to increase the top padding of the given view.
 */
inline fun View.addPaddingTopByStatusBarHeight() = Viewx.addPaddingTopByStatusBarHeight(this)
