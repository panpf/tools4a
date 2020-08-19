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

package com.github.panpf.tools4a.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.NonNull;

public class Textx {

    private Textx() {
    }


    /**
     * Modify the display color of a string using Html
     */
    @NonNull
    public static String changeColorByHtml(@NonNull String string, @NonNull String color) {
        return "<font color=\"" + color + "\">" + string + "</font>";
    }

    /**
     * Modify the display red color of a string using Html
     */
    @NonNull
    public static String changeColorToRedByHtml(@NonNull String string) {
        return changeColorByHtml(string, "red");
    }


    /**
     * Use Html to modify the display color of all specified keywords in a string
     */
    @NonNull
    public static String changeKeywordColorByHtml(@NonNull String sourceString, @NonNull String keyword, @NonNull String color) {
        return sourceString.replaceAll(keyword, "<font color=\"" + color + "\">" + keyword + "</font>");
    }

    /**
     * Use Html to modify the display color of all specified keywords in a string
     */
    @NonNull
    public static String changeKeywordColorToRedByHtml(@NonNull String sourceString, @NonNull String keyword) {
        return changeKeywordColorByHtml(sourceString, keyword, "red");
    }


    /**
     * Use Spannable to modify the display color of a string
     */
    @NonNull
    public static SpannableStringBuilder changeColorBySpannable(@NonNull String sourceString, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(sourceString);
        builder.setSpan(new ForegroundColorSpan(color), 0, sourceString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /**
     * Use Spannable to modify the display color of a string
     */
    @NonNull
    public static SpannableStringBuilder changeColorToRedBySpannable(@NonNull String sourceString) {
        return changeColorBySpannable(sourceString, Color.RED);
    }


    /**
     * Use Spannable to modify the display color of all specified keywords in a string
     */
    @NonNull
    public static SpannableStringBuilder changeKeywordColorBySpannable(@NonNull String sourceString, @NonNull String keyword, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(sourceString);
        int fromIndex = 0;
        while (fromIndex < sourceString.length()) {
            int index = sourceString.indexOf(keyword, fromIndex);
            if (index != -1) {
                int endIndex = index + keyword.length();
                builder.setSpan(new ForegroundColorSpan(color), index, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                fromIndex = endIndex;
            } else {
                break;
            }
        }
        return builder;
    }

    /**
     * Use Spannable to modify the display color of all specified keywords in a string
     */
    @NonNull
    public static SpannableStringBuilder changeKeywordColorToRedBySpannable(@NonNull String sourceString, @NonNull String keyword) {
        return changeKeywordColorBySpannable(sourceString, keyword, Color.RED);
    }
}
