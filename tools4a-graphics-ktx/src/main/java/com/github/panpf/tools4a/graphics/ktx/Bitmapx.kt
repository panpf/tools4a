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

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import com.github.panpf.tools4a.graphics.Bitmapx
import java.io.File
import java.io.FileDescriptor
import java.io.InputStream

/*
 * Bitmap related extension methods or properties
 */


/* ************************************** read ******************************************  */


/**
 * Read bitmap from file
 */
inline fun File.readBitmap(options: BitmapFactory.Options? = null): Bitmap? = Bitmapx.readBitmap(this, options)

/**
 * Read bitmap from file
 */
inline fun File.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

/**
 * Read bitmap from InputStream
 */
inline fun InputStream.readBitmap(outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, outPadding, options)

/**
 * Read bitmap from InputStream
 */
inline fun InputStream.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

/**
 * Read bitmap from byte array
 */
inline fun ByteArray.readBitmap(offset: Int, length: Int, options: BitmapFactory.Options): Bitmap? = Bitmapx.readBitmap(this, offset, length, options)

/**
 * Read bitmap from byte array
 */
inline fun ByteArray.readBitmap(options: BitmapFactory.Options): Bitmap? = Bitmapx.readBitmap(this, options)

/**
 * Read bitmap from byte array
 */
inline fun ByteArray.readBitmap(offset: Int, length: Int): Bitmap? = Bitmapx.readBitmap(this, offset, length)

/**
 * Read bitmap from byte array
 */
inline fun ByteArray.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

/**
 * Read bitmap from FileDescriptor
 */
inline fun FileDescriptor.readBitmap(outPadding: Rect?, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, outPadding, options)

/**
 * Read bitmap from FileDescriptor
 */
inline fun FileDescriptor.readBitmap(): Bitmap? = Bitmapx.readBitmap(this)

/**
 * Read bitmap from resource
 */
inline fun Resources.readBitmap(resId: Int, options: BitmapFactory.Options?): Bitmap? = Bitmapx.readBitmap(this, resId, options)

/**
 * Read bitmap from resource
 */
inline fun Resources.readBitmap(resId: Int): Bitmap? = Bitmapx.readBitmap(this, resId)

/**
 * Read bitmap from resource
 */
inline fun Resources.readBitmap(value: TypedValue?, inputStream: InputStream?, pad: Rect?, options: BitmapFactory.Options?): Bitmap? =
        Bitmapx.readBitmap(this, value, inputStream, pad, options)


/* ************************************** save ******************************************  */


/**
 * Save Bitmap to file
 */
inline fun Bitmap.writeToFile(file: File, format: Bitmap.CompressFormat, quality: Int) = Bitmapx.writeToFile(this, file, format, quality)


/* ************************************** toByteArray ******************************************  */


/**
 * Convert to byte array
 */
inline fun Bitmap.toByteArray(format: Bitmap.CompressFormat, quality: Int): ByteArray = Bitmapx.toByteArray(this, format, quality)


/* ************************************** toDrawable ******************************************  */

/**
 * Change the color of the bitmap
 */
inline fun Bitmap.toDrawable(resources: Resources): BitmapDrawable = Bitmapx.toDrawable(this, resources)

/**
 * Change the color of the bitmap
 */
inline fun Bitmap.toDrawable(): BitmapDrawable = Bitmapx.toDrawable(this)


/* ************************************** circular ******************************************  */


/**
 * Change to circular bitmap
 */
inline fun Bitmap.circularTo(dstBitmap: Bitmap): Bitmap = Bitmapx.circularTo(this, dstBitmap)

/**
 * Change to circular bitmap
 */
inline fun Bitmap.circular(newSize: Int, config: Bitmap.Config): Bitmap = Bitmapx.circular(this, newSize, config)

/**
 * Change to circular bitmap
 */
inline fun Bitmap.circular(newSize: Int): Bitmap = Bitmapx.circular(this, newSize)

/**
 * Change to circular bitmap
 */
inline fun Bitmap.circular(config: Bitmap.Config): Bitmap = Bitmapx.circular(this, config)

/**
 * Change to circular bitmap
 */
inline fun Bitmap.circular(): Bitmap = Bitmapx.circular(this)


/* ************************************** crop ******************************************  */


/**
 * Crop bitmap by the srcRect
 */
inline fun Bitmap.crop(srcRect: Rect): Bitmap = Bitmapx.crop(this, srcRect)

/**
 * Crop bitmap by the srcRect, and return dstBitmap
 */
inline fun Bitmap.cropTo(srcRect: Rect, dstBitmap: Bitmap): Bitmap = Bitmapx.cropTo(this, srcRect, dstBitmap)

/**
 * Zoom in and then center cropped bitmap
 */
inline fun Bitmap.centerCropTo(dstBitmap: Bitmap): Bitmap = Bitmapx.centerCropTo(this, dstBitmap)

/**
 * Zoom in and then center cropped bitmap
 */
inline fun Bitmap.centerCrop(newWidth: Int, newHeight: Int, config: Bitmap.Config): Bitmap = Bitmapx.centerCrop(this, newWidth, newHeight, config)

/**
 * Zoom in and then center cropped bitmap
 */
inline fun Bitmap.centerCrop(newWidth: Int, newHeight: Int): Bitmap = Bitmapx.centerCrop(this, newWidth, newHeight)


/* ************************************** tint ******************************************  */


/**
 * Change bitmap color
 */
inline fun Bitmap.tint(@ColorInt color: Int): Bitmap = Bitmapx.tint(this, color)


/* ************************************** use ******************************************  */


inline fun <R> Bitmap.use(block: (Bitmap) -> R): R {
    try {
        return block(this)
    } finally {
        this@use.recycle()
    }
}
