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
import android.graphics.Rect
import android.util.TypedValue
import androidx.annotation.DrawableRes
import com.github.panpf.tools4a.graphics.Imagex
import java.io.File
import java.io.FileDescriptor
import java.io.InputStream


/* ************************************** getMimeType ******************************************  */

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun File?.getImageMimeType(): String? = Imagex.getMimeType(this)

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun InputStream?.getImageMimeType(): String? = Imagex.getMimeType(this)

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun ByteArray?.getImageMimeType(offset: Int, length: Int): String? = Imagex.getMimeType(this, offset, length)

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun ByteArray?.getImageMimeType(): String? = Imagex.getMimeType(this)

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun FileDescriptor?.getImageMimeType(): String? = Imagex.getMimeType(this)

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun Resources?.getImageMimeType(@DrawableRes resId: Int): String? = Imagex.getMimeType(this, resId)

/**
 * Get the MimeType of the image, for example 'image/jpeg'
 */
inline fun Resources?.getImageMimeType(value: TypedValue?, `is`: InputStream?, pad: Rect?): String? = Imagex.getMimeType(this, value, `is`, pad)


/* ************************************** getMimeSubType ******************************************  */

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun File?.getImageMimeSubType(): String? = Imagex.getMimeSubType(this)

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun InputStream?.getImageMimeSubType(): String? = Imagex.getMimeSubType(this)

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun ByteArray?.getImageMimeSubType(offset: Int, length: Int): String? = Imagex.getMimeSubType(this, offset, length)

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun ByteArray?.getImageMimeSubType(): String? = Imagex.getMimeSubType(this)

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun FileDescriptor?.getImageMimeSubType(): String? = Imagex.getMimeSubType(this)

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun Resources?.getImageMimeSubType(@DrawableRes resId: Int): String? = Imagex.getMimeSubType(this, resId)

/**
 * Get the MimeType sub type of the image, for example 'jpeg'
 */
inline fun Resources?.getImageMimeSubType(value: TypedValue?, `is`: InputStream?, pad: Rect?): String? = Imagex.getMimeSubType(this, value, `is`, pad)