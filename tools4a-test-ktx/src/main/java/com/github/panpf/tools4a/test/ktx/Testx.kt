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
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.FragmentAction
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import com.github.panpf.tools4a.test.ActivityScenarioAction
import com.github.panpf.tools4a.test.Testx
import kotlin.reflect.KClass


inline fun <T : Activity> KClass<T>.launchActivity(): ActivityScenario<T> =
        Testx.launchActivity(this.java)

inline fun <T : Activity> KClass<T>.launchActivity(activityOptions: Bundle?): ActivityScenario<T> =
        Testx.launchActivity(this.java, activityOptions)

inline fun <T : Activity> Intent.launchActivity(): ActivityScenario<T> =
        Testx.launchActivity(this)

inline fun <T : Activity> Intent.launchActivity(activityOptions: Bundle?): ActivityScenario<T> =
        Testx.launchActivity(this, activityOptions)


inline fun <T : Activity> KClass<T>.launchActivityWithOn(action: ActivityScenarioAction<T>): ActivityScenario<T> =
        Testx.launchActivityWithOn(this.java, action)

inline fun <T : Activity> KClass<T>.launchActivityWithOn(activityOptions: Bundle?, action: ActivityScenarioAction<T>): ActivityScenario<T> =
        Testx.launchActivityWithOn(this.java, activityOptions, action)

inline fun <T : Activity> Intent.launchActivityWithOn(action: ActivityScenarioAction<T>): ActivityScenario<T> =
        Testx.launchActivityWithOn(this, action)

inline fun <T : Activity> Intent.launchActivityWithOn(activityOptions: Bundle?, action: ActivityScenarioAction<T>): ActivityScenario<T> =
        Testx.launchActivityWithOn(this, activityOptions, action)


inline fun <T : Activity> KClass<T>.launchActivityWithOnUse(action: ActivityAction<T>) =
        Testx.launchActivityWithOnUse(this.java, action)

inline fun <T : Activity> KClass<T>.launchActivityWithOnUse(activityOptions: Bundle?, action: ActivityAction<T>) =
        Testx.launchActivityWithOnUse(this.java, activityOptions, action)

inline fun <T : Activity> Intent.launchActivityWithOnUse(action: ActivityAction<T>) =
        Testx.launchActivityWithOnUse(this, action)

inline fun <T : Activity> Intent.launchActivityWithOnUse(activityOptions: Bundle?, action: ActivityAction<T>) =
        Testx.launchActivityWithOnUse(this, activityOptions, action)


inline fun <T : Activity> KClass<T>.launchActivityWithUse(action: ActivityScenarioAction<T>) =
        Testx.launchActivityWithUse(this.java, action)

inline fun <T : Activity> KClass<T>.launchActivityWithUse(activityOptions: Bundle?, action: ActivityScenarioAction<T>) =
        Testx.launchActivityWithUse(this.java, activityOptions, action)

inline fun <T : Activity> Intent.launchActivityWithUse(action: ActivityScenarioAction<T>) =
        Testx.launchActivityWithUse(this, action)

inline fun <T : Activity> Intent.launchActivityWithUse(activityOptions: Bundle?, action: ActivityScenarioAction<T>) =
        Testx.launchActivityWithUse(this, activityOptions, action)


inline fun <T : Activity> ActivityScenario<T>.getActivitySync(): T =
        Testx.getActivitySync(this)

inline fun <T : Activity> ActivityScenario<T>?.getActivityOrNullSync(): T? =
        Testx.getActivityOrNullSync(this)


inline fun <T : Activity?> ActivityScenario<T>.onActivitySleep(sleepTime: Long, action: ActivityAction<T>) = Testx.onActivitySleep(this, sleepTime, action)

inline fun <T : Activity?> ActivityScenario<T>.onActivitySleep100ms(action: ActivityAction<T>) = Testx.onActivitySleep100ms(this, action)

inline fun <T : Activity?> ActivityScenario<T>.onActivityAndSleep200ms(action: ActivityAction<T>) = Testx.onActivityAndSleep200ms(this, action)


inline fun <T : Fragment> KClass<T>.launchFragment(): FragmentScenario<T> =
        Testx.launchFragment(this.java)

inline fun <T : Fragment> KClass<T>.launchFragment(
        fragmentArgs: Bundle?): FragmentScenario<T> = Testx.launchFragment(this.java, fragmentArgs)

inline fun <T : Fragment> KClass<T>.launchFragment(fragmentArgs: Bundle?, factory: FragmentFactory?): FragmentScenario<T> =
        Testx.launchFragment(this.java, fragmentArgs, factory)

inline fun <T : Fragment> KClass<T>.launchFragment(
        fragmentArgs: Bundle?, @StyleRes themeResId: Int, factory: FragmentFactory?): FragmentScenario<T> =
        Testx.launchFragment(this.java, fragmentArgs, themeResId, factory)


inline fun <T : Fragment> KClass<T>.launchFragmentInContainer(): FragmentScenario<T> =
        Testx.launchFragmentInContainer(this.java)

inline fun <T : Fragment> KClass<T>.launchFragmentInContainer(fragmentArgs: Bundle?): FragmentScenario<T> =
        Testx.launchFragmentInContainer(this.java, fragmentArgs)

inline fun <T : Fragment> KClass<T>.launchFragmentInContainer(
        fragmentArgs: Bundle?, factory: FragmentFactory?): FragmentScenario<T> =
        Testx.launchFragmentInContainer(this.java, fragmentArgs, factory)

inline fun <T : Fragment> KClass<T>.launchFragmentInContainer(
        fragmentArgs: Bundle?, @StyleRes themeResId: Int, factory: FragmentFactory?): FragmentScenario<T> =
        Testx.launchFragmentInContainer(this.java, fragmentArgs, themeResId, factory)


inline fun <T : Fragment> KClass<T>.launchFragmentWithOn(action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentWithOn(this.java, action)

inline fun <T : Fragment> KClass<T>.launchFragmentWithOn(fragmentArgs: Bundle?, action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentWithOn(this.java, fragmentArgs, action)

inline fun <T : Fragment> KClass<T>.launchFragmentWithOn(
        fragmentArgs: Bundle?, factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentWithOn(this.java, fragmentArgs, factory, action)

inline fun <T : Fragment> KClass<T>.launchFragmentWithOn(
        fragmentArgs: Bundle?, @StyleRes themeResId: Int, factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentWithOn(this.java, fragmentArgs, themeResId, factory, action)


inline fun <T : Fragment> KClass<T>.launchFragmentInContainerWithOn(
        action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentInContainerWithOn(this.java, action)

inline fun <T : Fragment> KClass<T>.launchFragmentInContainerWithOn(
        fragmentArgs: Bundle?, action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentInContainerWithOn(this.java, fragmentArgs, action)

inline fun <T : Fragment> KClass<T>.launchFragmentInContainerWithOn(
        fragmentArgs: Bundle?, factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentInContainerWithOn(this.java, fragmentArgs, factory, action)

inline fun <T : Fragment> KClass<T>.launchFragmentInContainerWithOn(
        fragmentArgs: Bundle?, @StyleRes themeResId: Int, factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> =
        Testx.launchFragmentInContainerWithOn(this.java, fragmentArgs, themeResId, factory, action)


inline fun <T : Fragment> FragmentScenario<T>.getFragmentSync(): T =
        Testx.getFragmentSync(this)


inline fun <T : Fragment?> FragmentScenario<T>.onFragmentSleep(sleepTime: Long, action: FragmentAction<T>) = Testx.onFragmentSleep(this, sleepTime, action)

inline fun <T : Fragment?> FragmentScenario<T>.onFragmentSleep100ms(action: FragmentAction<T>) = Testx.onFragmentSleep100ms(this, action)

inline fun <T : Fragment?> FragmentScenario<T>.onFragmentAndSleep200ms(action: FragmentAction<T>) = Testx.onFragmentAndSleep200ms(this, action)
