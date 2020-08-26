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

package com.github.panpf.tools4a.toast.ktx

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.github.panpf.tools4a.toast.Toastx

/*
 * Toast related extension method
 */


/* ************************************* Context ***************************************** */


inline fun Context.showLongToast(message: CharSequence) = Toastx.showLong(this, message)

inline fun Context.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun Context.showLongToastWithFormat(message: String, vararg params: Any) = Toastx.showLongWithFormat(this, message, *params)

inline fun Context.showLongToastWithFormat(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLongWithFormat(this, messageResId, *params)


inline fun Context.showShortToast(message: CharSequence) = Toastx.showShort(this, message)

inline fun Context.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun Context.showShortToastWithFormat(message: String, vararg params: Any) = Toastx.showShortWithFormat(this, message, *params)

inline fun Context.showShortToastWithFormat(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShortWithFormat(this, messageResId, *params)


/* ************************************* androidx.fragment.app.Fragment ***************************************** */


inline fun androidx.fragment.app.Fragment.showLongToast(message: CharSequence) = Toastx.showLong(this, message)

inline fun androidx.fragment.app.Fragment.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun androidx.fragment.app.Fragment.showLongToastWithFormat(message: String, vararg params: Any) = Toastx.showLongWithFormat(this, message, *params)

inline fun androidx.fragment.app.Fragment.showLongToastWithFormat(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLongWithFormat(this, messageResId, *params)


inline fun androidx.fragment.app.Fragment.showShortToast(message: CharSequence) = Toastx.showShort(this, message)

inline fun androidx.fragment.app.Fragment.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun androidx.fragment.app.Fragment.showShortToastWithFormat(message: String, vararg params: Any) = Toastx.showShortWithFormat(this, message, *params)

inline fun androidx.fragment.app.Fragment.showShortToastWithFormat(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShortWithFormat(this, messageResId, *params)


/* ************************************* View ***************************************** */


inline fun View.showLongToast(message: CharSequence) = Toastx.showLong(this, message)

inline fun View.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun View.showLongToastWithFormat(message: String, vararg params: Any) = Toastx.showLongWithFormat(this, message, *params)

inline fun View.showLongToastWithFormat(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLongWithFormat(this, messageResId, *params)


inline fun View.showShortToast(message: CharSequence) = Toastx.showShort(this, message)

inline fun View.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun View.showShortToastWithFormat(message: String, vararg params: Any) = Toastx.showShortWithFormat(this, message, *params)

inline fun View.showShortToastWithFormat(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShortWithFormat(this, messageResId, *params)


/* ************************************* Show View ***************************************** */


inline fun View.showLongToastWithSelf() = Toastx.showLongWithView(this)

inline fun View.showShortToastWithSelf() = Toastx.showShortWithView(this)