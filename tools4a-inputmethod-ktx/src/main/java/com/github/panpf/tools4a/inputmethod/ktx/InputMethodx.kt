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

package com.github.panpf.tools4a.inputmethod.ktx

import android.app.Activity
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.github.panpf.tools4a.inputmethod.InputMethodx

/*
 * Input method related extension methods or properties
 */


/**
 * Return true if the soft keyboard has been popped up, Not applicable to landscape
 */
inline fun Activity.isSoftInputShowing(): Boolean = InputMethodx.isSoftInputShowing(this)


/**
 * Display soft keyboard
 */
inline fun EditText.showSoftInput() = InputMethodx.showSoftInput(this)


/**
 * Delay display soft keyboard
 *
 * @param delayMillisecond Delay milliseconds
 */
inline fun EditText.delayShowSoftInput(delayMillisecond: Long) = InputMethodx.delayShowSoftInput(this, delayMillisecond)

/**
 * Delay display soft keyboard
 */
inline fun EditText.delayShowSoftInput() = InputMethodx.delayShowSoftInput(this)


/**
 * Hide soft keyboard
 */
inline fun Activity.hideSoftInput() = InputMethodx.hideSoftInput(this)

/**
 * Hide soft keyboard
 */
inline fun Fragment.hideSoftInput() = InputMethodx.hideSoftInput(this)

/**
 * Hide soft keyboard
 */
inline fun EditText.hideSoftInput() = InputMethodx.hideSoftInput(this)


/**
 * Move the cursor to the end of the EditText
 */
inline fun EditText.moveCursorToEnd() = InputMethodx.moveCursorToEnd(this)

/**
 * Move the cursor to the start of the EditText
 */
inline fun EditText.moveCursorToStart() = InputMethodx.moveCursorToStart(this)

/**
 * Move the cursor to the specified index of the EditText
 */
inline fun EditText.moveCursorTo(index: Int) = InputMethodx.moveCursorTo(this, index)








