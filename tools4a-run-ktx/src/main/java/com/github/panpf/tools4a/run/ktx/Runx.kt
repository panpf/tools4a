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
inline fun runOnUIThread(block: Runnable) = Runx.runOnUIThread(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun runOnUIThread(noinline block: () -> Unit) = Runx.runOnUIThread(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun runOnUIThreadAndWait(block: Runnable) = Runx.runOnUIThreadAndWait(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun runOnUIThreadAndWait(noinline block: () -> Unit) = Runx.runOnUIThreadAndWait(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnUIThreadAndWaitResult(block: ResultRunnable<T>): T = Runx.runOnUIThreadAndWaitResult(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnUIThreadAndWaitResult(noinline block: () -> T): T = Runx.runOnUIThreadAndWaitResult { block() }


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnUIThreadAndWaitNullableResult(block: ResultNullableRunnable<T>): T? = Runx.runOnUIThreadAndWaitNullableResult(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnUIThreadAndWaitNullableResult(noinline block: () -> T?): T? = Runx.runOnUIThreadAndWaitNullableResult { block() }


/**
 * Is on the main thread?
 */
inline fun isOnTheMainThread(): Boolean = Runx.isOnTheMainThread()

/**
 * Is on the main process?
 */
inline fun Context.isOnTheMainProcess(): Boolean = Runx.isOnTheMainProcess(this)

/**
 * Get the name of the current process
 */
inline fun Context.getTheProcessName(): String? = Runx.getTheProcessName(this)

/**
 * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
 */
inline fun Context.getTheProcessNameSuffix(): String? = Runx.getTheProcessNameSuffix(this)