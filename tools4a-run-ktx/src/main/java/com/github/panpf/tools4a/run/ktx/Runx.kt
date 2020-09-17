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
import android.os.Handler
import com.github.panpf.tools4a.run.ResultNullableRunnable
import com.github.panpf.tools4a.run.ResultRunnable
import com.github.panpf.tools4a.run.Runx


/**
 * Get the main thread Handler
 */
inline fun getMainHandler(): Handler = Runx.getMainHandler()


/**
 * Is on main thread?
 */
inline fun isOnMainThread(): Boolean = Runx.isOnMainThread()

/**
 * Is on not main thread?
 */
inline fun isOnNotMainThread(): Boolean = Runx.isOnNotMainThread()

/**
 * Checks if the current thread is the main thread, otherwise throws.
 *
 * @throws IllegalStateException if current thread is not the main thread.
 */
inline fun checkMainThread() = Runx.checkMainThread()

/**
 * Checks if the current thread is not the main thread, otherwise throws.
 *
 * @throws IllegalStateException if current thread is the main thread.
 */
inline fun checkNotMainThread() = Runx.checkNotMainThread()


/**
 * Execute the specified code block in the main thread
 */
inline fun runOnMainThread(block: Runnable) = Runx.runOnMainThread(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun runOnMainThreadSync(block: Runnable) = Runx.runOnMainThreadSync(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnMainThreadSyncResult(block: ResultRunnable<T>): T = Runx.runOnMainThreadSyncResult(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun <T> runOnMainThreadSyncResultNullable(block: ResultNullableRunnable<T>): T? = Runx.runOnMainThreadSyncResultNullable(block)


/**
 * Is on main process?
 */
inline fun Context.isOnMainProcess(): Boolean = Runx.isOnMainProcess(this)

/**
 * Is on not main process?
 */
inline fun Context.isOnNotMainProcess(): Boolean = Runx.isOnNotMainProcess(this)


/**
 * Get name of the current process
 */
inline fun Context.getOnProcessNameOrNull(): String? = Runx.getOnProcessNameOrNull(this)

/**
 * Get suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​'push'
 */
inline fun Context.getOnProcessNameSuffixOrNull(): String? = Runx.getOnProcessNameSuffixOrNull(this)