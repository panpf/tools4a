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


fun <T : Activity> launchActivity(activityClass: KClass<T>): ActivityScenario<T> = Testx.launchActivity(activityClass.java)

fun <T : Activity> launchActivity(activityClass: KClass<T>, activityOptions: Bundle?): ActivityScenario<T> = Testx.launchActivity(activityClass.java, activityOptions)

fun <T : Activity> launchActivity(activityIntent: Intent): ActivityScenario<T> = Testx.launchActivity(activityIntent)

fun <T : Activity> launchActivity(activityIntent: Intent, activityOptions: Bundle?): ActivityScenario<T> = Testx.launchActivity(activityIntent, activityOptions)


fun <T : Activity> launchActivityWithOn(
        activityClass: KClass<T>, action: ActivityScenarioAction<T>): ActivityScenario<T> = Testx.launchActivityWithOn(activityClass.java, action)

fun <T : Activity> launchActivityWithOn(
        activityClass: KClass<T>, activityOptions: Bundle?, action: ActivityScenarioAction<T>): ActivityScenario<T> = Testx.launchActivityWithOn(activityClass.java, activityOptions, action)

fun <T : Activity> launchActivityWithOn(
        activityIntent: Intent, action: ActivityScenarioAction<T>): ActivityScenario<T> = Testx.launchActivityWithOn(activityIntent, action)

fun <T : Activity> launchActivityWithOn(
        activityIntent: Intent, activityOptions: Bundle?, action: ActivityScenarioAction<T>): ActivityScenario<T> = Testx.launchActivityWithOn(activityIntent, activityOptions, action)


fun <T : Activity> launchActivityWithOnUse(
        activityClass: KClass<T>, action: ActivityAction<T>) = Testx.launchActivityWithOnUse(activityClass.java, action)

fun <T : Activity> launchActivityWithOnUse(
        activityClass: KClass<T>, activityOptions: Bundle?, action: ActivityAction<T>) = Testx.launchActivityWithOnUse(activityClass.java, activityOptions, action)

fun <T : Activity> launchActivityWithOnUse(
        activityIntent: Intent, action: ActivityAction<T>) = Testx.launchActivityWithOnUse(activityIntent, action)

fun <T : Activity> launchActivityWithOnUse(
        activityIntent: Intent, activityOptions: Bundle?, action: ActivityAction<T>) = Testx.launchActivityWithOnUse(activityIntent, activityOptions, action)


fun <T : Activity> launchActivityWithUse(
        activityClass: KClass<T>, action: ActivityScenarioAction<T>) = Testx.launchActivityWithUse(activityClass.java, action)

fun <T : Activity> launchActivityWithUse(
        activityClass: KClass<T>, activityOptions: Bundle?, action: ActivityScenarioAction<T>) = Testx.launchActivityWithUse(activityClass.java, activityOptions, action)

fun <T : Activity> launchActivityWithUse(
        activityIntent: Intent, action: ActivityScenarioAction<T>) = Testx.launchActivityWithUse(activityIntent, action)

fun <T : Activity> launchActivityWithUse(
        activityIntent: Intent, activityOptions: Bundle?, action: ActivityScenarioAction<T>) = Testx.launchActivityWithUse(activityIntent, activityOptions, action)


fun <T : Activity> ActivityScenario<T>.getActivitySync(): T = Testx.getActivitySync(this)

fun <T : Activity> ActivityScenario<T>?.getActivityOrNullSync(): T? = Testx.getActivityOrNullSync(this)


fun <T : Fragment> launchFragment(fragmentClass: KClass<T>): FragmentScenario<T> = Testx.launchFragment(fragmentClass.java)

fun <T : Fragment> launchFragment(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?): FragmentScenario<T> = Testx.launchFragment(fragmentClass.java, fragmentArgs)

fun <T : Fragment> launchFragment(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, factory: FragmentFactory?): FragmentScenario<T> = Testx.launchFragment(fragmentClass.java, fragmentArgs, factory)

fun <T : Fragment> launchFragment(fragmentClass: KClass<T>, fragmentArgs: Bundle?,
                                  @StyleRes themeResId: Int, factory: FragmentFactory?): FragmentScenario<T> = Testx.launchFragment(fragmentClass.java, fragmentArgs, themeResId, factory)


fun <T : Fragment> launchFragmentInContainer(fragmentClass: KClass<T>): FragmentScenario<T> = Testx.launchFragmentInContainer(fragmentClass.java)

fun <T : Fragment> launchFragmentInContainer(fragmentClass: KClass<T>, fragmentArgs: Bundle?): FragmentScenario<T> = Testx.launchFragmentInContainer(fragmentClass.java, fragmentArgs)

fun <T : Fragment> launchFragmentInContainer(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, factory: FragmentFactory?): FragmentScenario<T> = Testx.launchFragmentInContainer(fragmentClass.java, fragmentArgs, factory)

fun <T : Fragment> launchFragmentInContainer(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?,
        @StyleRes themeResId: Int, factory: FragmentFactory?): FragmentScenario<T> = Testx.launchFragmentInContainer(fragmentClass.java, fragmentArgs, themeResId, factory)


fun <T : Fragment> launchFragmentWithOn(fragmentClass: KClass<T>, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentWithOn(fragmentClass.java, action)

fun <T : Fragment> launchFragmentWithOn(fragmentClass: KClass<T>, fragmentArgs: Bundle?, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentWithOn(fragmentClass.java, fragmentArgs, action)

fun <T : Fragment> launchFragmentWithOn(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentWithOn(fragmentClass.java, fragmentArgs, factory, action)

fun <T : Fragment> launchFragmentWithOn(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, @StyleRes themeResId: Int,
        factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentWithOn(fragmentClass.java, fragmentArgs, themeResId, factory, action)


fun <T : Fragment> launchFragmentInContainerWithOn(
        fragmentClass: KClass<T>, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentInContainerWithOn(fragmentClass.java, action)

fun <T : Fragment> launchFragmentInContainerWithOn(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentInContainerWithOn(fragmentClass.java, fragmentArgs, action)

fun <T : Fragment> launchFragmentInContainerWithOn(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentInContainerWithOn(fragmentClass.java, fragmentArgs, factory, action)

fun <T : Fragment> launchFragmentInContainerWithOn(
        fragmentClass: KClass<T>, fragmentArgs: Bundle?, @StyleRes themeResId: Int,
        factory: FragmentFactory?, action: FragmentAction<T>): FragmentScenario<T> = Testx.launchFragmentInContainerWithOn(fragmentClass.java, fragmentArgs, themeResId, factory, action)


fun <T : Fragment> FragmentScenario<T>.getFragmentSync(): T = Testx.getFragmentSync(this)