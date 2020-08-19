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

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class Resizex {

    private Resizex() {
    }


    @NonNull
    public static Rect srcMappingStartRect(int originalWidth, int originalHeight, int targetWidth, int targetHeight) {
        float widthScale = (float) originalWidth / targetWidth;
        float heightScale = (float) originalHeight / targetHeight;
        float finalScale = Math.min(widthScale, heightScale);
        int srcWidth = (int) (targetWidth * finalScale);
        int srcHeight = (int) (targetHeight * finalScale);
        int srcLeft = 0;
        int srcTop = 0;
        return new Rect(srcLeft, srcTop, srcLeft + srcWidth, srcTop + srcHeight);
    }

    @NonNull
    public static Rect srcMappingCenterRect(int originalWidth, int originalHeight, int targetWidth, int targetHeight) {
        float widthScale = (float) originalWidth / targetWidth;
        float heightScale = (float) originalHeight / targetHeight;
        float finalScale = Math.min(widthScale, heightScale);
        int srcWidth = (int) (targetWidth * finalScale);
        int srcHeight = (int) (targetHeight * finalScale);
        int srcLeft = (originalWidth - srcWidth) / 2;
        int srcTop = (originalHeight - srcHeight) / 2;
        return new Rect(srcLeft, srcTop, srcLeft + srcWidth, srcTop + srcHeight);
    }

    @NonNull
    public static Rect srcMappingEndRect(int originalWidth, int originalHeight, int targetWidth, int targetHeight) {
        float widthScale = (float) originalWidth / targetWidth;
        float heightScale = (float) originalHeight / targetHeight;
        float finalScale = Math.min(widthScale, heightScale);
        int srcWidth = (int) (targetWidth * finalScale);
        int srcHeight = (int) (targetHeight * finalScale);

        int srcLeft;
        int srcTop;
        // todo 这里有问题
        if (originalWidth > originalHeight) {
            srcLeft = originalWidth - srcWidth;
            srcTop = originalHeight - srcHeight;
        } else {
            srcLeft = originalWidth - srcWidth;
            srcTop = originalHeight - srcHeight;
        }
        return new Rect(srcLeft, srcTop, srcLeft + srcWidth, srcTop + srcHeight);
    }

    @NonNull
    public static Rect srcMatrixRect(int originalWidth, int originalHeight, int targetWidth, int targetHeight) {
        if (originalWidth > targetWidth && originalHeight > targetHeight) {
            return new Rect(0, 0, targetWidth, targetHeight);
        } else {
            float scale = targetWidth - originalWidth > targetHeight - originalHeight ? (float) targetWidth / originalWidth : (float) targetHeight / originalHeight;
            int srcWidth = (int) (targetWidth / scale);
            int srcHeight = (int) (targetHeight / scale);
            int srcLeft = 0;
            int srcTop = 0;
            return new Rect(srcLeft, srcTop, srcLeft + srcWidth, srcTop + srcHeight);
        }
    }

    @NonNull
    public static Point scaleTargetSize(int originalWidth, int originalHeight, int targetWidth, int targetHeight) {
        if (targetWidth > originalWidth || targetHeight > originalHeight) {
            float scale = Math.abs(targetWidth - originalWidth) < Math.abs(targetHeight - originalHeight)
                    ? (float) targetWidth / originalWidth : (float) targetHeight / originalHeight;
            return new Point(Math.round(targetWidth / scale), Math.round(targetHeight / scale));
        } else {
            return new Point(targetWidth, targetHeight);
        }
    }

    /**
     * Calculation
     *
     * @param scaleType Zoom type
     * @param forceSame Force the size of the new image to match the resizeWidth and resizeHeight
     */
    @NonNull
    public static Result calculator(int imageWidth, int imageHeight, int resizeWidth, int resizeHeight,
                                    @NonNull ImageView.ScaleType scaleType, boolean forceSame) {
        if (imageWidth == resizeWidth && imageHeight == resizeHeight) {
            return new Result(imageWidth, imageHeight, new Rect(0, 0, imageWidth, imageHeight), new Rect(0, 0, imageWidth, imageHeight));
        }

        int newWidth;
        int newHeight;
        if (forceSame) {
            newWidth = resizeWidth;
            newHeight = resizeHeight;
        } else {
            Point finalSize = scaleTargetSize(imageWidth, imageHeight, resizeWidth, resizeHeight);
            newWidth = finalSize.x;
            newHeight = finalSize.y;
        }
        Rect srcRect;
        Rect destRect = new Rect(0, 0, newWidth, newHeight);
        if (scaleType == ImageView.ScaleType.CENTER || scaleType == ImageView.ScaleType.CENTER_CROP || scaleType == ImageView.ScaleType.CENTER_INSIDE) {
            srcRect = srcMappingCenterRect(imageWidth, imageHeight, newWidth, newHeight);
        } else if (scaleType == ImageView.ScaleType.FIT_START) {
            srcRect = srcMappingStartRect(imageWidth, imageHeight, newWidth, newHeight);
        } else if (scaleType == ImageView.ScaleType.FIT_CENTER) {
            srcRect = srcMappingCenterRect(imageWidth, imageHeight, newWidth, newHeight);
        } else if (scaleType == ImageView.ScaleType.FIT_END) {
            srcRect = srcMappingEndRect(imageWidth, imageHeight, newWidth, newHeight);
        } else if (scaleType == ImageView.ScaleType.FIT_XY) {
            srcRect = new Rect(0, 0, imageWidth, imageHeight);
        } else if (scaleType == ImageView.ScaleType.MATRIX) {
            srcRect = srcMatrixRect(imageWidth, imageHeight, newWidth, newHeight);
        } else {
            srcRect = srcMappingCenterRect(imageWidth, imageHeight, newWidth, newHeight);
        }

        return new Result(newWidth, newHeight, srcRect, destRect);
    }

    @SuppressWarnings("WeakerAccess")
    public static class Result implements Parcelable {
        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
        public final int width;
        public final int height;
        @NonNull
        public final Rect srcRect;
        public final Rect destRect;

        public Result(int width, int height, @NonNull Rect srcRect, @NonNull Rect destRect) {
            this.width = width;
            this.height = height;
            this.srcRect = srcRect;
            this.destRect = destRect;
        }

        protected Result(Parcel in) {
            width = in.readInt();
            height = in.readInt();
            //noinspection ConstantConditions
            srcRect = in.readParcelable(Rect.class.getClassLoader());
            destRect = in.readParcelable(Rect.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(width);
            dest.writeInt(height);
            dest.writeParcelable(srcRect, flags);
            dest.writeParcelable(destRect, flags);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return width == result.width &&
                    height == result.height &&
                    srcRect.equals(result.srcRect) &&
                    destRect.equals(result.destRect);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{width, height, srcRect, destRect});
        }

        @NonNull
        @Override
        public String toString() {
            return "Result{" +
                    "width=" + width +
                    ", height=" + height +
                    ", srcRect=" + srcRect +
                    ", destRect=" + destRect +
                    '}';
        }
    }
}
