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

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.TypedValue;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;

public class Imagex {

    private Imagex() {
    }

    /* ************************************** getMimeType ******************************************  */

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable File file) {
        if (file == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        return options.outMimeType;
    }

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable InputStream inputStream) {
        if (inputStream == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        return options.outMimeType;
    }

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable byte[] data, int offset, int length) {
        if (data == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, offset, length, options);
        return options.outMimeType;
    }

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable byte[] data) {
        if (data == null) return null;
        return getMimeType(data, 0, data.length);
    }

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable FileDescriptor fileDescriptor) {
        if (fileDescriptor == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        return options.outMimeType;
    }

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable Resources resources, @DrawableRes int resId) {
        if (resources == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        return options.outMimeType;
    }

    /**
     * Get the MimeType of the image, for example 'image/jpeg'
     */
    @Nullable
    public static String getMimeType(@Nullable Resources resources, @Nullable TypedValue value,
                                     @Nullable InputStream is, @Nullable Rect pad) {
        if (resources == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResourceStream(resources, value, is, pad, options);
        return options.outMimeType;
    }


    /* ************************************** getMimeSubType ******************************************  */

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable File file) {
        String mimeType = getMimeType(file);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable InputStream inputStream) {
        String mimeType = getMimeType(inputStream);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable byte[] data, int offset, int length) {
        String mimeType = getMimeType(data, offset, length);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable byte[] data) {
        String mimeType = getMimeType(data);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable FileDescriptor fileDescriptor) {
        String mimeType = getMimeType(fileDescriptor);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable Resources resources, @DrawableRes int resId) {
        String mimeType = getMimeType(resources, resId);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }

    /**
     * Get the MimeType sub type of the image, for example 'jpeg'
     */
    @Nullable
    public static String getMimeSubType(@Nullable Resources resources, @Nullable TypedValue value,
                                        @Nullable InputStream is, @Nullable Rect pad) {
        String mimeType = getMimeType(resources, value, is, pad);
        if (mimeType != null) {
            String[] items = mimeType.split("/");
            if (items.length > 1 && items[1] != null) {
                return items[1];
            }
        }
        return null;
    }
}
