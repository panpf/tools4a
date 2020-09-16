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

package com.github.panpf.tools4a.dimen.ktx

import android.content.Context
import android.view.View
import com.github.panpf.tools4a.dimen.Dimenx


/* ************************************* Context ***************************************** */

inline fun Context.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun Context.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun Context.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun Context.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun Context.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun Context.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun Context.applyDimension(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.applyDimension(this, unit, value)

inline fun Context.applyDimension(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.applyDimension(this, unit, value)


/* ************************************* androidx.fragment.app.Fragment ***************************************** */


inline fun androidx.fragment.app.Fragment.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun androidx.fragment.app.Fragment.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun androidx.fragment.app.Fragment.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun androidx.fragment.app.Fragment.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun androidx.fragment.app.Fragment.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun androidx.fragment.app.Fragment.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun androidx.fragment.app.Fragment.applyDimension(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.applyDimension(this, unit, value)

inline fun androidx.fragment.app.Fragment.applyDimension(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.applyDimension(this, unit, value)


/* ************************************* View ***************************************** */


inline fun View.dp2px(dpValue: Float): Int = Dimenx.dp2px(this, dpValue)

inline fun View.dp2px(dpValue: Int): Int = Dimenx.dp2px(this, dpValue)


inline fun View.px2dp(px: Int): Float = Dimenx.px2dp(this, px)


inline fun View.sp2px(dpValue: Float): Int = Dimenx.sp2px(this, dpValue)

inline fun View.sp2px(dpValue: Int): Int = Dimenx.sp2px(this, dpValue)


inline fun View.px2sp(px: Int): Float = Dimenx.px2sp(this, px)


inline fun View.applyDimension(@Dimenx.DimenUnit unit: Int, value: Float): Float = Dimenx.applyDimension(this, unit, value)

inline fun View.applyDimension(@Dimenx.DimenUnit unit: Int, value: Int): Float = Dimenx.applyDimension(this, unit, value)