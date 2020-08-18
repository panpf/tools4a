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
inline fun runInUI(block: Runnable) = Runx.runInUI(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun runInUI(noinline block: () -> Unit) = Runx.runInUI(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun waitRunInUI(block: Runnable) = Runx.waitRunInUI(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun waitRunInUI(noinline block: () -> Unit) = Runx.waitRunInUI(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUIResult(block: ResultRunnable<T>): T = Runx.waitRunInUIResult(block)

/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUIResult(noinline block: () -> T): T = Runx.waitRunInUIResult { block() }


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUINullableResult(block: ResultNullableRunnable<T>): T? = Runx.waitRunInUINullableResult(block)


/**
 * Execute the specified code block in the main thread
 */
inline fun <T> waitRunInUINullableResult(noinline block: () -> T?): T? = Runx.waitRunInUINullableResult { block() }


/**
 * Get the name of the current process
 */
inline fun Context.getInProcessName(): String? = Runx.getInProcessName(this)

/**
 * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
 */
inline fun Context.getInProcessNameSuffix(): String? = Runx.getInProcessNameSuffix(this)

/**
 * Is in the main process?
 */
inline fun Context.isMainProcess(): Boolean = Runx.isMainProcess(this)