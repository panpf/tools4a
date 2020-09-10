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

package com.github.panpf.tools4a.run.ktx

import android.content.Context
import com.github.panpf.tools4a.run.ResultNullableRunnable
import com.github.panpf.tools4a.run.ResultRunnable
import com.github.panpf.tools4a.run.Runx

/**
 * Execute the specified code block in the main thread
 */
inline fun runOnUiThread(block: Runnable) = Runx.runOnUiThread(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun runOnUiThreadAndWait(block: Runnable) = Runx.runOnUiThreadAndWait(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnUiThreadAndWaitResult(block: ResultRunnable<T>): T = Runx.runOnUiThreadAndWaitResult(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnUiThreadAndWaitNullableResult(block: ResultNullableRunnable<T>): T? = Runx.runOnUiThreadAndWaitNullableResult(block)


/**
 * Is on main thread?
 */
inline fun isOnMainThread(): Boolean = Runx.isOnMainThread()

/**
 * Is on main process?
 */
inline fun Context.isOnMainProcess(): Boolean = Runx.isOnMainProcess(this)

/**
 * Get name of the current process
 */
inline fun Context.getProcessName(): String? = Runx.getProcessName(this)

/**
 * Get suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
 */
inline fun Context.getProcessNameSuffix(): String? = Runx.getProcessNameSuffix(this)