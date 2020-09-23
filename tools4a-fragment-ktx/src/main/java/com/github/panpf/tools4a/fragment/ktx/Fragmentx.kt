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

package com.github.panpf.tools4a.fragment.ktx

import android.app.Application
import android.os.Bundle
import com.github.panpf.tools4a.fragment.Fragmentx


/**
 * Get Application from Fragment
 */
inline fun androidx.fragment.app.Fragment?.getApplication(): Application? = Fragmentx.getApplication(this)

/**
 * Get Application from Fragment, throws an exception if null
 */
inline fun androidx.fragment.app.Fragment.requireApplication(): Application = Fragmentx.requireApplication(this)

/**
 * Return true if the fragment has been destroyed
 */
inline fun androidx.fragment.app.Fragment.isDestroyed(): Boolean = Fragmentx.isDestroyed(this)


/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> androidx.fragment.app.Fragment.getImplFromParent(clazz: Class<T>): T? = Fragmentx.getImplFromParent(this, clazz)


/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : androidx.fragment.app.Fragment> Class<out T>.instantiate(arguments: Bundle): T = Fragmentx.instantiate(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : androidx.fragment.app.Fragment> Class<out T>.instantiate(): T = Fragmentx.instantiate(this)


/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun List<androidx.fragment.app.Fragment>?.findUserVisibleChildFragmentByUserVisibleHint(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(this)

/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun androidx.fragment.app.FragmentActivity?.findUserVisibleChildFragmentByUserVisibleHint(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(this)

/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun androidx.fragment.app.Fragment?.findUserVisibleChildFragmentByUserVisibleHint(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun List<androidx.fragment.app.Fragment>?.findUserVisibleChildFragmentByResumed(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragmentByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun androidx.fragment.app.FragmentActivity?.findUserVisibleChildFragmentByResumed(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragmentByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun androidx.fragment.app.Fragment?.findUserVisibleChildFragmentByResumed(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragmentByResumed(this)


/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun List<androidx.fragment.app.Fragment>?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): androidx.fragment.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun androidx.fragment.app.FragmentActivity?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): androidx.fragment.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun androidx.fragment.app.Fragment?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): androidx.fragment.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)
