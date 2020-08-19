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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class Bitmapx {

    private Bitmapx() {
    }


    /* ************************************** read ******************************************  */


    /**
     * Read bitmap from file
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull File file, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    /**
     * Read bitmap from file
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull File file) {
        return BitmapFactory.decodeFile(file.getPath());
    }

    /**
     * Read bitmap from InputStream
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeStream(inputStream, outPadding, options);
    }

    /**
     * Read bitmap from InputStream
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    /**
     * Read bitmap from byte array
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data, int offset, int length, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(data, offset, length, options);
    }

    /**
     * Read bitmap from byte array
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /**
     * Read bitmap from byte array
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data, int offset, int length) {
        return BitmapFactory.decodeByteArray(data, offset, length);
    }

    /**
     * Read bitmap from byte array
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * Read bitmap from FileDescriptor
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull FileDescriptor fileDescriptor, @Nullable Rect outPadding, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, outPadding, options);
    }

    /**
     * Read bitmap from FileDescriptor
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull FileDescriptor fileDescriptor) {
        return BitmapFactory.decodeFileDescriptor(fileDescriptor);
    }

    /**
     * Read bitmap from resource
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull Resources resources, @DrawableRes int resId, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     * Read bitmap from resource
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull Resources resources, @DrawableRes int resId) {
        return BitmapFactory.decodeResource(resources, resId);
    }

    /**
     * Read bitmap from resource
     */
    @Nullable
    public static Bitmap readBitmap(@NonNull Resources res, @Nullable TypedValue value, @Nullable InputStream is, @Nullable Rect pad, @Nullable BitmapFactory.Options options) {
        return BitmapFactory.decodeResourceStream(res, value, is, pad, options);
    }


    /* ************************************** create ******************************************  */


    /**
     * Create a Bitmap of the specified color
     */
    public static Bitmap createByColor(int width, int height, @NonNull Bitmap.Config config, @ColorInt int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(color);
        return bitmap;
    }

    /**
     * Create a Bitmap of the specified color
     */
    public static Bitmap createByColor(int width, int height, @ColorInt int color) {
        return createByColor(width, height, Bitmap.Config.ARGB_8888, color);
    }


    /* ************************************** save ******************************************  */


    /**
     * Save Bitmap to file
     */
    public static void writeToFile(@NonNull Bitmap bitmap, @NonNull File file, @NonNull Bitmap.CompressFormat format, int quality) throws IOException {
        if (!file.exists()) {
            File dir = file.getParentFile();
            if (dir != null && !dir.exists() && (!dir.mkdirs() || !dir.exists())) {
                throw new IOException("Unable create dir: " + dir);
            }
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException("Unable create file: " + file, e);
            }
            if (!file.exists()) throw new IOException("Unable create file: ");
        }
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
            throw e;
        }
        try {
            bitmap.compress(format, quality, bos);
        } finally {
            bos.flush();
            bos.close();
        }
    }


    /* ************************************** toByteArray ******************************************  */


    /**
     * Convert to byte array
     */
    public static byte[] toByteArray(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, outputStream);
        return outputStream.toByteArray();
    }


    /* ************************************** toDrawable ******************************************  */

    /**
     * Change the color of the bitmap
     */
    @NonNull
    public static BitmapDrawable toDrawable(@NonNull Bitmap bitmap, @Nullable Resources resources) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
        if (resources == null) {
            bitmapDrawable.setTargetDensity(bitmap.getDensity());
        }
        return bitmapDrawable;
    }

    /**
     * Change the color of the bitmap
     */
    @NonNull
    public static BitmapDrawable toDrawable(@NonNull Bitmap bitmap) {
        return toDrawable(bitmap, null);
    }


    /* ************************************** circular ******************************************  */


    /**
     * Change to circular bitmap
     */
    @NonNull
    public static Bitmap circularTo(@NonNull Bitmap srcBitmap, @NonNull Bitmap dstBitmap) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFFF0000);

        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawARGB(0, 0, 0, 0);

        float newBitmapRadius = Math.min(dstBitmap.getWidth(), dstBitmap.getHeight()) / 2;
        canvas.drawCircle(newBitmapRadius, newBitmapRadius, newBitmapRadius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Resizex.Result result = Resizex.calculator(srcBitmap.getWidth(), srcBitmap.getHeight(),
                dstBitmap.getWidth(), dstBitmap.getHeight(), ImageView.ScaleType.CENTER_CROP, false);
        canvas.drawBitmap(srcBitmap, result.srcRect, result.destRect, paint);

        return dstBitmap;
    }

    /**
     * Change to circular bitmap
     */
    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap, int newSize, @NonNull Bitmap.Config config) {
        return circularTo(srcBitmap, Bitmap.createBitmap(newSize, newSize, config));
    }

    /**
     * Change to circular bitmap
     */
    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap, int newSize) {
        return circularTo(srcBitmap, Bitmap.createBitmap(newSize, newSize, Bitmap.Config.ARGB_8888));
    }

    /**
     * Change to circular bitmap
     */
    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap, @NonNull Bitmap.Config config) {
        final int newBitmapSize = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
        return circularTo(srcBitmap, Bitmap.createBitmap(newBitmapSize, newBitmapSize, config));
    }

    /**
     * Change to circular bitmap
     */
    @NonNull
    public static Bitmap circular(@NonNull Bitmap srcBitmap) {
        return circular(srcBitmap, Bitmap.Config.ARGB_8888);
    }


    /* ************************************** crop ******************************************  */


    /**
     * Crop bitmap by the srcRect
     */
    @NonNull
    public static Bitmap crop(@NonNull Bitmap srcBitmap, @NonNull Rect srcRect) {
        return Bitmap.createBitmap(srcBitmap, srcRect.left, srcRect.top, srcRect.width(), srcRect.height());
    }

    /**
     * Crop bitmap by the srcRect, and return dstBitmap
     */
    @NonNull
    public static Bitmap cropTo(@NonNull Bitmap srcBitmap, @NonNull Rect srcRect, @NonNull Bitmap dstBitmap) {
        float srcSizeScale = BigDecimal.valueOf((float) srcRect.width() / srcRect.height()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        float destSizeScale = BigDecimal.valueOf((float) dstBitmap.getWidth() / dstBitmap.getHeight()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        if (srcSizeScale != destSizeScale) {
            throw new IllegalArgumentException(String.format("srcRect is inconsistent with dstBitmap's aspect ratio. srcRect=%s, dstBitmap=%dx%d",
                    srcRect.toShortString(), dstBitmap.getWidth(), dstBitmap.getHeight()));
        }
        Paint paint = new Paint();
        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawBitmap(srcBitmap, srcRect, new Rect(0, 0, dstBitmap.getWidth(), dstBitmap.getHeight()), paint);
        return dstBitmap;
    }

    /**
     * Zoom in and then center cropped bitmap
     */
    @NonNull
    public static Bitmap centerCropTo(@NonNull Bitmap srcBitmap, @NonNull Bitmap dstBitmap) {
        Paint paint = new Paint();
        Canvas canvas = new Canvas(dstBitmap);

        Resizex.Result result = Resizex.calculator(srcBitmap.getWidth(), srcBitmap.getHeight(),
                dstBitmap.getWidth(), dstBitmap.getHeight(), ImageView.ScaleType.CENTER_CROP, false);
        canvas.drawBitmap(srcBitmap, result.srcRect, result.destRect, paint);

        return dstBitmap;
    }

    /**
     * Zoom in and then center cropped bitmap
     */
    @NonNull
    public static Bitmap centerCrop(@NonNull Bitmap srcBitmap, int newWidth, int newHeight, @NonNull Bitmap.Config config) {
        return centerCropTo(srcBitmap, Bitmap.createBitmap(newWidth, newHeight, config));
    }

    /**
     * Zoom in and then center cropped bitmap
     */
    @NonNull
    public static Bitmap centerCrop(@NonNull Bitmap srcBitmap, int newWidth, int newHeight) {
        return centerCropTo(srcBitmap, Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888));
    }


    /* ************************************** tint ******************************************  */


    /**
     * Change bitmap color
     */
    @NonNull
    public static Bitmap tint(@NonNull Bitmap srcBitmap, @ColorInt int color) {
        Paint mPaint = new Paint();
        mPaint.setColorFilter(Colorx.createMatrixColorFilter(color));

        Bitmap newBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(srcBitmap, new Matrix(), mPaint);

        return newBitmap;
    }


    /* ************************************** other ******************************************  */


    /**
     * Calculate the value after inSampleSize
     */
    public static int calculateSamplingSize(int size, int inSampleSize) {
        return (int) Math.ceil(size / (float) inSampleSize);
    }

    /**
     * Calculate the value after inSampleSize
     */
    public static int calculateSamplingSizeForRegion(int size, int inSampleSize) {
        return (int) Math.floor(size / (float) inSampleSize);
    }
}
