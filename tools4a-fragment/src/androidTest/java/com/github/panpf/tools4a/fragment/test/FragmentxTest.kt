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
package com.github.panpf.tools4a.fragment.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager.widget.ViewPager
import com.github.panpf.tools4a.fragment.Fragmentx
import com.github.panpf.tools4a.test.ktx.getActivitySync
import com.github.panpf.tools4a.test.ktx.getFragmentSync
import com.github.panpf.tools4a.test.ktx.launchActivityWithUse
import com.github.panpf.tools4a.test.ktx.launchFragmentInContainer
import com.github.panpf.tools4j.test.ktx.assertNoThrow
import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentxTest {

    @Test
    fun testGetApplication() {
        val scenario = Fragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()

        assertNotNull(Fragmentx.getApplication(fragment))

        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertNull(Fragmentx.getApplication(fragment))
    }

    @Test
    fun testRequireApplication() {
        val scenario = Fragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()

        assertNoThrow { Fragmentx.requireApplication(fragment) }

        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertThrow(IllegalStateException::class) { Fragmentx.requireApplication(fragment) }
    }

    @Test
    fun testDestroyed() {
        val scenario = Fragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()

        assertFalse(Fragmentx.isDestroyed(fragment))

        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertTrue(Fragmentx.isDestroyed(fragment))

        assertTrue(Fragmentx.isDestroyed(Fragment()))
    }

    @Test
    fun testGetImplWithParent() {
        val scenario = TestImplSupportFragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()
        assertEquals(
                TestImplSupportFragment::class.java,
                Fragmentx.getImplFromParent(fragment, ImplTestInterface::class.java)!!.javaClass)
        assertEquals(
                TestImplSupportFragment::class.java,
                Fragmentx.getImplFromParent(fragment.childFragment, ImplTestInterface::class.java)!!.javaClass)

        val scenario2 = TestImplSupportFragment2::class.launchFragmentInContainer()
        val fragment2 = scenario2.getFragmentSync()
        assertNull(Fragmentx.getImplFromParent(fragment2, ImplTestInterface::class.java))
        assertNull(Fragmentx.getImplFromParent(TestImplSupportFragment2(), ImplTestInterface::class.java))
    }

    @Test
    fun testInstantiate() {
        val fragment = Fragmentx.instantiate(Fragment::class.java, bundleOf("key" to "testInstantiate"))
        assertEquals(Fragment::class.java.name, fragment.javaClass.name)
        assertEquals("testInstantiate", fragment.arguments!!.getString("key"))

        val fragment1 = Fragmentx.instantiate(Fragment::class.java)
        assertEquals(Fragment::class.java.name, fragment1.javaClass.name)
    }

    @Test
    @Suppress("DEPRECATION")
    fun testFindUserVisibleFragmentByUserVisibleHint() {
        /*
         * findUserVisibleChildFragmentByUserVisibleHint(Activity)
         */
        UserVisibleHintParentViewPagerActivity::class.launchActivityWithUse { activityScenario ->
            val activity = activityScenario.getActivitySync()

            var userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(activity) as UserVisibleHintChildViewPagerFragment
            assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(activity) as UserVisibleHintChildViewPagerFragment
            assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(activity) as UserVisibleHintChildViewPagerFragment
            assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)
        }

        /*
         * findUserVisibleChildFragmentByUserVisibleHint(Fragment)
         */
        val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(fragment) as UserVisibleHintChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(fragment) as UserVisibleHintChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(fragment) as UserVisibleHintChildViewPagerFragment
        assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)

        /*
         * findUserVisibleFragmentByUserVisibleHint(List<Fragment>)
         */
        val fragmentScenario1 = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildViewPagerFragment1 = Fragmentx.findUserVisibleFragmentByUserVisibleHint(fragment1.childFragmentManager.fragments) as UserVisibleHintChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = Fragmentx.findUserVisibleFragmentByUserVisibleHint(fragment1.childFragmentManager.fragments) as UserVisibleHintChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = Fragmentx.findUserVisibleFragmentByUserVisibleHint(fragment1.childFragmentManager.fragments) as UserVisibleHintChildViewPagerFragment
        assertEquals(2, userVisibleChildViewPagerFragment1.tabIndex)
    }

    @Test
    @Suppress("DEPRECATION")
    fun testFindUserVisibleFragmentRecursivelyByUserVisibleHint() {
        /*
         * findUserVisibleChildFragmentRecursivelyByUserVisibleHint(Activity)
         */
        UserVisibleHintParentViewPagerActivity::class.launchActivityWithUse { activityScenario ->
            val activity = activityScenario.getActivitySync()

            var userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(activity) as TextFragment
            assertEquals(0, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(activity) as TextFragment
            assertEquals(1, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(activity) as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(activity) as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(1, userVisibleChildTextFragment.pageIndex)
        }

        /*
         * findUserVisibleChildFragmentRecursivelyByUserVisibleHint(Fragment)
         */
        val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(fragment) as TextFragment
        assertEquals(0, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(fragment) as TextFragment
        assertEquals(1, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(fragment) as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByUserVisibleHint(fragment) as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(1, userVisibleChildTextFragment.pageIndex)

        /*
         * findUserVisibleChildFragmentRecursivelyByUserVisibleHint(List<Fragment>)
         */
        val fragmentScenario1 = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByUserVisibleHint(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(0, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByUserVisibleHint(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(1, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByUserVisibleHint(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            userVisibleChildTextFragment1.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByUserVisibleHint(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(1, userVisibleChildTextFragment1.pageIndex)
    }

    @Test
    @Suppress("DEPRECATION")
    fun testFindUserVisibleFragmentByResumed() {
        /*
         * findUserVisibleChildFragmentByResumed(Activity)
         */
        ResumeOnlyCurrentParentViewPagerActivity::class.launchActivityWithUse { activityScenario ->
            val activity = activityScenario.getActivitySync()

            var userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByResumed(activity) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByResumed(activity) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByResumed(activity) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)
        }

        /*
         * findUserVisibleChildFragmentByResumed(Fragment)
         */
        val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByResumed(fragment) as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByResumed(fragment) as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = Fragmentx.findUserVisibleChildFragmentByResumed(fragment) as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)

        /*
         * findUserVisibleFragmentByResumed(List<Fragment>)
         */
        val fragmentScenario1 = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildViewPagerFragment1 = Fragmentx.findUserVisibleFragmentByResumed(fragment1.childFragmentManager.fragments) as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = Fragmentx.findUserVisibleFragmentByResumed(fragment1.childFragmentManager.fragments) as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = Fragmentx.findUserVisibleFragmentByResumed(fragment1.childFragmentManager.fragments) as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(2, userVisibleChildViewPagerFragment1.tabIndex)
    }

    @Test
    @Suppress("DEPRECATION")
    fun testFindUserVisibleFragmentRecursivelyByResumed() {
        /*
         * findUserVisibleChildFragmentRecursivelyByResumed(Activity)
         */
        ResumeOnlyCurrentParentViewPagerActivity::class.launchActivityWithUse { activityScenario ->
            val activity = activityScenario.getActivitySync()

            var userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(activity) as TextFragment
            assertEquals(0, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(activity) as TextFragment
            assertEquals(1, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(activity) as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(activity) as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(1, userVisibleChildTextFragment.pageIndex)
        }

        /*
         * findUserVisibleChildFragmentRecursivelyByResumed(Fragment)
         */
        val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(fragment) as TextFragment
        assertEquals(0, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(fragment) as TextFragment
        assertEquals(1, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(fragment) as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = Fragmentx.findUserVisibleChildFragmentRecursivelyByResumed(fragment) as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(1, userVisibleChildTextFragment.pageIndex)

        /*
         * findUserVisibleChildFragmentRecursivelyByResumed(List<Fragment>)
         */
        val fragmentScenario1 = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByResumed(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(0, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByResumed(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(1, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByResumed(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            userVisibleChildTextFragment1.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = Fragmentx.findUserVisibleFragmentRecursivelyByResumed(fragment1.childFragmentManager.fragments) as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(1, userVisibleChildTextFragment1.pageIndex)
    }

    @Test
    fun testFindFragmentByViewPagerCurrentItem() {
        ResumeOnlyCurrentParentViewPagerActivity::class.launchActivityWithUse { activityScenario ->
            val activity = activityScenario.getActivitySync()

            var childFragmentIndex0 = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 0) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(0, childFragmentIndex0!!.tabIndex)

            var childFragmentIndex1 = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 1) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(1, childFragmentIndex1!!.tabIndex)

            var childFragmentIndex2 = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 2) as ResumeOnlyCurrentChildViewPagerFragment?
            assertNull(childFragmentIndex2)

            activityScenario.onActivity {
                activity.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            childFragmentIndex0 = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 0) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(0, childFragmentIndex0!!.tabIndex)

            childFragmentIndex1 = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 1) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(1, childFragmentIndex1!!.tabIndex)

            childFragmentIndex2 = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 2) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(2, childFragmentIndex2!!.tabIndex)

            activityScenario.onActivity {
                activity.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)

            childFragmentIndex0 = Fragmentx.findFragmentByViewPagerCurrentItem(activity.supportFragmentManager.fragments, 0) as ResumeOnlyCurrentChildViewPagerFragment?
            assertNull(childFragmentIndex0)

            childFragmentIndex1 = Fragmentx.findFragmentByViewPagerCurrentItem(activity.supportFragmentManager.fragments, 1) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(1, childFragmentIndex1!!.tabIndex)

            childFragmentIndex2 = Fragmentx.findFragmentByViewPagerCurrentItem(activity.supportFragmentManager.fragments, 2) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(2, childFragmentIndex2!!.tabIndex)

            var childTextFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 0) as TextFragment?
            assertEquals(2, childTextFragment0!!.tabIndex)
            assertEquals(0, childTextFragment0.pageIndex)

            var childTextFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 1) as TextFragment?
            assertEquals(2, childTextFragment1!!.tabIndex)
            assertEquals(1, childTextFragment1.pageIndex)

            var childTextFragment2 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 2) as TextFragment?
            assertNull(childTextFragment2)

            activityScenario.onActivity {
                childFragmentIndex2.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            childTextFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 0) as TextFragment?
            assertEquals(2, childTextFragment0!!.tabIndex)
            assertEquals(0, childTextFragment0.pageIndex)

            childTextFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 1) as TextFragment?
            assertEquals(2, childTextFragment1!!.tabIndex)
            assertEquals(1, childTextFragment1.pageIndex)

            childTextFragment2 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 2) as TextFragment?
            assertEquals(2, childTextFragment2!!.tabIndex)
            assertEquals(2, childTextFragment2.pageIndex)

            activityScenario.onActivity {
                childFragmentIndex2.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)

            childTextFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 0) as TextFragment?
            assertNull(childTextFragment0)

            childTextFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 1) as TextFragment?
            assertEquals(2, childTextFragment1!!.tabIndex)
            assertEquals(1, childTextFragment1.pageIndex)

            childTextFragment2 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragmentIndex2, 2) as TextFragment?
            assertEquals(2, childTextFragment2!!.tabIndex)
            assertEquals(2, childTextFragment2.pageIndex)
        }
    }

    @Test
    @Suppress("DEPRECATION")
    fun testIsUserVisibleHintWithParent() {
        try {
            val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 0) as UserVisibleHintChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(childFragment0))

            val grandsonFragment00 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(grandsonFragment00))

            val grandsonFragment01 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment01))

            val childFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 1) as UserVisibleHintChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertFalse(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(childFragment1))

            val grandsonFragment10 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertTrue(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment10))

            val grandsonFragment11 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment11))

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertFalse(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(childFragment0))

            assertTrue(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment00))

            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment01))

            assertTrue(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(childFragment1))

            assertTrue(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(grandsonFragment10))

            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment11))
        } catch (e: Exception) {
            throw e
        }

        try {
            val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 0) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(childFragment0))

            val grandsonFragment00 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(grandsonFragment00))

            val grandsonFragment01 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment01))

            val childFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 1) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertFalse(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(childFragment1))

            val grandsonFragment10 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertFalse(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment10))

            val grandsonFragment11 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment11))

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertFalse(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(childFragment0))

            assertFalse(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment00))

            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment01))

            assertTrue(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(childFragment1))

            assertTrue(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertTrue(Fragmentx.isUserVisibleHintWithParent(grandsonFragment10))

            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(Fragmentx.isUserVisibleHintWithParent(grandsonFragment11))
        } catch (e: Exception) {
            throw e
        }
    }

    @Test
    fun testIsResumedWithParent() {
        try {
            val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 0) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(childFragment0))

            val grandsonFragment00 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment00))

            val grandsonFragment01 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertFalse(grandsonFragment01.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(grandsonFragment01))

            val childFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 1) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertFalse(childFragment1.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(childFragment1))

            val grandsonFragment10 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertFalse(grandsonFragment10.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(grandsonFragment10))

            val grandsonFragment11 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertFalse(grandsonFragment11.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(grandsonFragment11))

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertFalse(childFragment0.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(childFragment0))

            assertFalse(grandsonFragment00.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(grandsonFragment00))

            assertFalse(grandsonFragment01.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(grandsonFragment01))

            assertTrue(childFragment1.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(childFragment1))

            assertTrue(grandsonFragment10.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment10))

            assertFalse(grandsonFragment11.isResumed)
            assertFalse(Fragmentx.isResumedWithParent(grandsonFragment11))
        } catch (e: Exception) {
            throw e
        }

        try {
            val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 0) as UserVisibleHintChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(childFragment0))

            val grandsonFragment00 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment00))

            val grandsonFragment01 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment0, 1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertTrue(grandsonFragment01.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment01))

            val childFragment1 = Fragmentx.findFragmentByViewPagerCurrentItem(fragment, 1) as UserVisibleHintChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertTrue(childFragment1.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(childFragment1))

            val grandsonFragment10 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertTrue(grandsonFragment10.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment10))

            val grandsonFragment11 = Fragmentx.findFragmentByViewPagerCurrentItem(childFragment1, 1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertTrue(grandsonFragment11.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment11))

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertTrue(childFragment0.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(childFragment0))

            assertTrue(grandsonFragment00.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment00))

            assertTrue(grandsonFragment01.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment01))

            assertTrue(childFragment1.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(childFragment1))

            assertTrue(grandsonFragment10.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment10))

            assertTrue(grandsonFragment11.isResumed)
            assertTrue(Fragmentx.isResumedWithParent(grandsonFragment11))
        } catch (e: Exception) {
            throw e
        }
    }

    interface ImplTestInterface

    class TestImplSupportFragment2 : Fragment()

    class TestImplSupportFragment : Fragment(), ImplTestInterface {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_test, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            this.childFragmentManager.beginTransaction()
                    .replace(R.id.testAt_frame, TestImplSupportFragment2())
                    .commit()
        }

        val childFragment: Fragment
            get() = this.childFragmentManager.findFragmentById(R.id.testAt_frame)!!
    }

    class TextFragment : Fragment() {
        companion object {
            fun instantiate(tabIndex: Int, pageIndex: Int) = TextFragment().apply {
                arguments = bundleOf("tabIndex" to tabIndex, "pageIndex" to pageIndex)
            }
        }

        val tabIndex by lazy { arguments!!.getInt("tabIndex") }
        val pageIndex by lazy { arguments!!.getInt("pageIndex") }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return TextView(context)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            (view as TextView).text = "tabIndex: $tabIndex, pageIndex: $pageIndex"
        }
    }

    class ResumeOnlyCurrentChildViewPagerFragment : Fragment() {
        companion object {
            fun instantiate(tabIndex: Int) = ResumeOnlyCurrentChildViewPagerFragment().apply {
                arguments = bundleOf("tabIndex" to tabIndex)
            }
        }

        val tabIndex by lazy { arguments!!.getInt("tabIndex") }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    override fun getCount(): Int = 3
                    override fun getItem(p0: Int): Fragment = TextFragment.instantiate(tabIndex, p0)
                }
            }
        }
    }

    class ResumeOnlyCurrentParentViewPagerFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    override fun getCount(): Int = 3
                    override fun getItem(p0: Int): Fragment = ResumeOnlyCurrentChildViewPagerFragment.instantiate(p0)
                }
            }
        }
    }

    class ResumeOnlyCurrentParentViewPagerActivity : FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_view_pager)
            findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    override fun getCount(): Int = 3
                    override fun getItem(p0: Int): Fragment = ResumeOnlyCurrentChildViewPagerFragment.instantiate(p0)
                }
            }
        }
    }

    class UserVisibleHintChildViewPagerFragment : Fragment() {
        companion object {
            fun instantiate(tabIndex: Int) = UserVisibleHintChildViewPagerFragment().apply {
                arguments = bundleOf("tabIndex" to tabIndex)
            }
        }

        val tabIndex by lazy { arguments!!.getInt("tabIndex") }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        @Suppress("DEPRECATION")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                    override fun getCount(): Int = 3
                    override fun getItem(p0: Int): Fragment = TextFragment.instantiate(tabIndex, p0)
                }
            }
        }
    }

    class UserVisibleHintParentViewPagerFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        @Suppress("DEPRECATION")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                    override fun getCount(): Int = 3
                    override fun getItem(p0: Int): Fragment = UserVisibleHintChildViewPagerFragment.instantiate(p0)
                }
            }
        }
    }

    class UserVisibleHintParentViewPagerActivity : FragmentActivity() {

        @Suppress("DEPRECATION")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_view_pager)
            findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                    override fun getCount(): Int = 3
                    override fun getItem(p0: Int): Fragment = UserVisibleHintChildViewPagerFragment.instantiate(p0)
                }
            }
        }
    }
}