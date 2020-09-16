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

package com.github.panpf.tools4a.test.ktx

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.github.panpf.tools4a.test.ActivityScenarioAction
import com.github.panpf.tools4a.test.Testx

inline fun <T : Activity> ActivityScenario<T>.getActivitySync(): T = Testx.getActivitySync(this)

inline fun <T : Activity> ActivityScenario<T>.getActivityOrNullSync(): T? = Testx.getActivityOrNullSync(this)

inline fun <T : Activity> launchAndOnActivityWithUse(activityClass: Class<T>, action: ActivityScenario.ActivityAction<T>) = Testx.launchAndOnActivityWithUse(activityClass, action)

inline fun <T : Activity> launchActivityWithUse(activityClass: Class<T>, action: ActivityScenarioAction<T>) = Testx.launchActivityWithUse(activityClass, action)