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

package com.github.panpf.tools4a.dialog.ktx

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import androidx.fragment.app.Fragment
import com.github.panpf.tools4a.dialog.Dialogx

/**
 * Whether to automatically close the dialog box when setting the click button
 */
inline fun Dialog.setClickButtonClosable(closable: Boolean): Boolean = Dialogx.setClickButtonClosable(this, closable)

/**
 * Display a progress dialog
 */
inline fun Activity.showProgressDialog(message: String): ProgressDialog = Dialogx.showProgressDialog(this, message)

/**
 * Display a progress dialog
 */
inline fun Activity.showProgressDialog(messageId: Int): ProgressDialog = Dialogx.showProgressDialog(this, messageId)

/**
 * Display a progress dialog
 */
inline fun Fragment.showProgressDialog(message: String): ProgressDialog? = Dialogx.showProgressDialog(this, message)

/**
 * Display a progress dialog
 */
inline fun Fragment.showProgressDialog(messageId: Int): ProgressDialog? = Dialogx.showProgressDialog(this, messageId)