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

package com.github.panpf.tools4a.service.ktx

import android.accessibilityservice.AccessibilityService
import android.app.Service
import android.content.Context
import android.os.Bundle
import com.github.panpf.tools4a.service.Servicex

/**
 * Return true if the service specifying Class is running
 */
inline fun Context.isServiceRunning(serviceClass: Class<out Service>, packageName: String? = null): Boolean = Servicex.isRunning(this, serviceClass, packageName)

/**
 * Start Service
 */
inline fun Context.startService(serviceClass: Class<out Service>, extras: Bundle? = null) = Servicex.start(this, serviceClass, extras)

/**
 * Stop Service
 */
inline fun Context.stopService(serviceClass: Class<out Service>) = Servicex.stop(this, serviceClass)

/**
 * Return true if the specified AccessibilityService service has been enabled
 */
inline fun Context.isAccessibilityServiceEnabled(service: Class<out AccessibilityService>, packageName: String? = null): Boolean =
        Servicex.isAccessibilityServiceEnabled(this, service, packageName)