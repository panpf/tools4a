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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.panpf.tools4a.fragment.Fragmentx


/**
 * Get Application from Fragment
 */
inline fun Fragment?.getApplication(): Application? = Fragmentx.getApplication(this)

/**
 * Get Application from Fragment, throws an exception if null
 */
inline fun Fragment.requireApplication(): Application = Fragmentx.requireApplication(this)

/**
 * Return true if the fragment has been destroyed
 */
inline fun Fragment.isDestroyed(): Boolean = Fragmentx.isDestroyed(this)


/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> Fragment.getImplFromParent(clazz: Class<T>): T? = Fragmentx.getImplFromParent(this, clazz)


/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : Fragment> Class<out T>.instantiate(arguments: Bundle): T = Fragmentx.instantiate(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : Fragment> Class<out T>.instantiate(): T = Fragmentx.instantiate(this)


/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleFragmentByResumed()"))
inline fun List<Fragment>?.findUserVisibleFragmentByUserVisibleHint(): Fragment? = Fragmentx.findUserVisibleFragmentByUserVisibleHint(this)

/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun FragmentActivity?.findUserVisibleChildFragmentByUserVisibleHint(): Fragment? = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(this)

/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun Fragment?.findUserVisibleChildFragmentByUserVisibleHint(): Fragment? = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(this)


/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleFragmentByResumed()"))
inline fun List<Fragment>?.findUserVisibleFragmentRecursivelyByUserVisibleHint(): Fragment? = Fragmentx.findUserVisibleFragmentRecursivelyByUserVisibleHint(this)

/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun FragmentActivity?.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(): Fragment? = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(this)

/**
 * Find the user visible fragment by UserVisibleHint from the FragmentActivity
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("findUserVisibleChildFragmentByResumed()"))
inline fun Fragment?.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(): Fragment? = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun List<Fragment>?.findUserVisibleFragmentByResumed(): Fragment? = Fragmentx.findUserVisibleFragmentByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun FragmentActivity?.findUserVisibleChildFragmentByResumed(): Fragment? = Fragmentx.findUserVisibleChildFragmentByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun Fragment?.findUserVisibleChildFragmentByResumed(): Fragment? = Fragmentx.findUserVisibleChildFragmentByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun List<Fragment>?.findUserVisibleFragmentRecursivelyByResumed(): Fragment? = Fragmentx.findUserVisibleFragmentRecursivelyByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun FragmentActivity?.findUserVisibleChildFragmentRecursivelyByResumed(): Fragment? = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(this)

/**
 * Find the user visible fragment by resumed from the FragmentActivity
 */
inline fun Fragment?.findUserVisibleChildFragmentRecursivelyByResumed(): Fragment? = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(this)


/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun List<Fragment>?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun FragmentActivity?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun Fragment?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)


/**
 * If the specified Fragment and all its parent Fragment isResumed and getUserVisibleHint are all true, return true, otherwise return false
 */
@Suppress("DEPRECATION")
@Deprecated(
        message = "Please use isResumedWithParent() method instead. Fragment getUserVisibleHint() method is obsolete, please use FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT and isResumed methods instead",
        replaceWith = ReplaceWith("isResumedWithParent()"))
inline fun Fragment?.isUserVisibleHintWithParent(): Boolean = Fragmentx.isUserVisibleHintWithParent(this)

/**
 * If the specified Fragment and all its parent Fragment isResumed are all true, return true, otherwise return false
 */
inline fun Fragment?.isResumedWithParent(): Boolean = Fragmentx.isResumedWithParent(this)
