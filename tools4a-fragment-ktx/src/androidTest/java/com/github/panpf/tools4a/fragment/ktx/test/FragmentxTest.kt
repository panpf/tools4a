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

package com.github.panpf.tools4a.fragment.ktx.test

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
import com.github.panpf.tools4a.fragment.ktx.*
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

        assertNotNull(fragment.getApplication())

        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertNull(fragment.getApplication())
    }

    @Test
    fun testRequireApplication() {
        val scenario = Fragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()

        assertNoThrow { fragment.requireApplication() }

        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertThrow(IllegalStateException::class) { fragment.requireApplication() }
    }

    @Test
    fun testDestroyed() {
        val scenario = Fragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()

        assertFalse(fragment.isDestroyed())

        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertTrue(fragment.isDestroyed())

        assertTrue(Fragment().isDestroyed())
    }

    @Test
    fun testGetImplWithParent() {
        val scenario = TestImplSupportFragment::class.launchFragmentInContainer()
        val fragment = scenario.getFragmentSync()
        assertEquals(
                TestImplSupportFragment::class.java,
                fragment.getImplFromParent(ImplTestInterface::class.java)!!.javaClass)
        assertEquals(
                TestImplSupportFragment::class.java,
                fragment.childFragment.getImplFromParent(ImplTestInterface::class.java)!!.javaClass)

        val scenario2 = TestImplSupportFragment2::class.launchFragmentInContainer()
        val fragment2 = scenario2.getFragmentSync()
        assertNull(fragment2.getImplFromParent(ImplTestInterface::class.java))
        assertNull(TestImplSupportFragment2().getImplFromParent(ImplTestInterface::class.java))
    }

    @Test
    fun testInstantiate() {
        val fragment = Fragment::class.java.instantiate(BundleBuilder().putString("key", "testInstantiate").build())
        assertEquals(Fragment::class.java.name, fragment.javaClass.name)
        assertEquals("testInstantiate", fragment.arguments!!.getString("key"))

        val fragment1 = Fragment::class.java.instantiate()
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

            var userVisibleChildViewPagerFragment = activity.findUserVisibleChildFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
            assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = activity.findUserVisibleChildFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
            assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = activity.findUserVisibleChildFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
            assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)
        }

        /*
         * findUserVisibleChildFragmentByUserVisibleHint(Fragment)
         */
        val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildViewPagerFragment = fragment.findUserVisibleChildFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = fragment.findUserVisibleChildFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = fragment.findUserVisibleChildFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
        assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)

        /*
         * findUserVisibleFragmentByUserVisibleHint(List<Fragment>)
         */
        val fragmentScenario1 = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildViewPagerFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentByUserVisibleHint() as UserVisibleHintChildViewPagerFragment
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

            var userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
            assertEquals(0, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
            assertEquals(1, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(1, userVisibleChildTextFragment.pageIndex)
        }

        /*
         * findUserVisibleChildFragmentRecursivelyByUserVisibleHint(Fragment)
         */
        val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(0, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(1, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(1, userVisibleChildTextFragment.pageIndex)

        /*
         * findUserVisibleChildFragmentRecursivelyByUserVisibleHint(List<Fragment>)
         */
        val fragmentScenario1 = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(0, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(1, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByUserVisibleHint() as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            userVisibleChildTextFragment1.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByUserVisibleHint() as TextFragment
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

            var userVisibleChildViewPagerFragment = activity.findUserVisibleChildFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = activity.findUserVisibleChildFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildViewPagerFragment = activity.findUserVisibleChildFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)
        }

        /*
         * findUserVisibleChildFragmentByResumed(Fragment)
         */
        val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildViewPagerFragment = fragment.findUserVisibleChildFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = fragment.findUserVisibleChildFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment.tabIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment = fragment.findUserVisibleChildFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(2, userVisibleChildViewPagerFragment.tabIndex)

        /*
         * findUserVisibleFragmentByResumed(List<Fragment>)
         */
        val fragmentScenario1 = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildViewPagerFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(0, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
        assertEquals(1, userVisibleChildViewPagerFragment1.tabIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildViewPagerFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentByResumed() as ResumeOnlyCurrentChildViewPagerFragment
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

            var userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
            assertEquals(0, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
            assertEquals(1, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                it.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(0, userVisibleChildTextFragment.pageIndex)

            activityScenario.onActivity {
                userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)
            userVisibleChildTextFragment = activity.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
            assertEquals(2, userVisibleChildTextFragment.tabIndex)
            assertEquals(1, userVisibleChildTextFragment.pageIndex)
        }

        /*
         * findUserVisibleChildFragmentRecursivelyByResumed(Fragment)
         */
        val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment = fragmentScenario.getFragmentSync()

        var userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
        assertEquals(0, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
        assertEquals(1, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(0, userVisibleChildTextFragment.pageIndex)

        fragmentScenario.onFragment {
            userVisibleChildTextFragment.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment = fragment.findUserVisibleChildFragmentRecursivelyByResumed() as TextFragment
        assertEquals(2, userVisibleChildTextFragment.tabIndex)
        assertEquals(1, userVisibleChildTextFragment.pageIndex)

        /*
         * findUserVisibleChildFragmentRecursivelyByResumed(List<Fragment>)
         */
        val fragmentScenario1 = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
        val fragment1 = fragmentScenario1.getFragmentSync()

        var userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByResumed() as TextFragment
        assertEquals(0, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByResumed() as TextFragment
        assertEquals(1, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            it.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByResumed() as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(0, userVisibleChildTextFragment1.pageIndex)

        fragmentScenario1.onFragment {
            userVisibleChildTextFragment1.parentFragment!!.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
        }
        Thread.sleep(200)
        userVisibleChildTextFragment1 = fragment1.childFragmentManager.fragments.findUserVisibleFragmentRecursivelyByResumed() as TextFragment
        assertEquals(2, userVisibleChildTextFragment1.tabIndex)
        assertEquals(1, userVisibleChildTextFragment1.pageIndex)
    }

    @Test
    fun testFindFragmentByViewPagerCurrentItem() {
        ResumeOnlyCurrentParentViewPagerActivity::class.launchActivityWithUse { activityScenario ->
            val activity = activityScenario.getActivitySync()

            var childFragmentIndex0 = activity.findFragmentByViewPagerCurrentItem(0) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(0, childFragmentIndex0!!.tabIndex)

            var childFragmentIndex1 = activity.findFragmentByViewPagerCurrentItem(1) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(1, childFragmentIndex1!!.tabIndex)

            var childFragmentIndex2 = activity.findFragmentByViewPagerCurrentItem(2) as ResumeOnlyCurrentChildViewPagerFragment?
            assertNull(childFragmentIndex2)

            activityScenario.onActivity {
                activity.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            childFragmentIndex0 = activity.findFragmentByViewPagerCurrentItem(0) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(0, childFragmentIndex0!!.tabIndex)

            childFragmentIndex1 = activity.findFragmentByViewPagerCurrentItem(1) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(1, childFragmentIndex1!!.tabIndex)

            childFragmentIndex2 = activity.findFragmentByViewPagerCurrentItem(2) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(2, childFragmentIndex2!!.tabIndex)

            activityScenario.onActivity {
                activity.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)

            childFragmentIndex0 = activity.supportFragmentManager.fragments.findFragmentByViewPagerCurrentItem(0) as ResumeOnlyCurrentChildViewPagerFragment?
            assertNull(childFragmentIndex0)

            childFragmentIndex1 = activity.supportFragmentManager.fragments.findFragmentByViewPagerCurrentItem(1) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(1, childFragmentIndex1!!.tabIndex)

            childFragmentIndex2 = activity.supportFragmentManager.fragments.findFragmentByViewPagerCurrentItem(2) as ResumeOnlyCurrentChildViewPagerFragment?
            assertEquals(2, childFragmentIndex2!!.tabIndex)

            var childTextFragment0 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(0) as TextFragment?
            assertEquals(2, childTextFragment0!!.tabIndex)
            assertEquals(0, childTextFragment0.pageIndex)

            var childTextFragment1 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(1) as TextFragment?
            assertEquals(2, childTextFragment1!!.tabIndex)
            assertEquals(1, childTextFragment1.pageIndex)

            var childTextFragment2 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(2) as TextFragment?
            assertNull(childTextFragment2)

            activityScenario.onActivity {
                childFragmentIndex2.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            childTextFragment0 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(0) as TextFragment?
            assertEquals(2, childTextFragment0!!.tabIndex)
            assertEquals(0, childTextFragment0.pageIndex)

            childTextFragment1 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(1) as TextFragment?
            assertEquals(2, childTextFragment1!!.tabIndex)
            assertEquals(1, childTextFragment1.pageIndex)

            childTextFragment2 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(2) as TextFragment?
            assertEquals(2, childTextFragment2!!.tabIndex)
            assertEquals(2, childTextFragment2.pageIndex)

            activityScenario.onActivity {
                childFragmentIndex2.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 2
            }
            Thread.sleep(200)

            childTextFragment0 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(0) as TextFragment?
            assertNull(childTextFragment0)

            childTextFragment1 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(1) as TextFragment?
            assertEquals(2, childTextFragment1!!.tabIndex)
            assertEquals(1, childTextFragment1.pageIndex)

            childTextFragment2 = childFragmentIndex2.findFragmentByViewPagerCurrentItem(2) as TextFragment?
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

            val childFragment0 = fragment.findFragmentByViewPagerCurrentItem(0) as UserVisibleHintChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertTrue(childFragment0.isUserVisibleHintWithParent())

            val grandsonFragment00 = childFragment0.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertTrue(grandsonFragment00.isUserVisibleHintWithParent())

            val grandsonFragment01 = childFragment0.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(grandsonFragment01.isUserVisibleHintWithParent())

            val childFragment1 = fragment.findFragmentByViewPagerCurrentItem(1) as UserVisibleHintChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertFalse(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertFalse(childFragment1.isUserVisibleHintWithParent())

            val grandsonFragment10 = childFragment1.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertTrue(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertFalse(grandsonFragment10.isUserVisibleHintWithParent())

            val grandsonFragment11 = childFragment1.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(grandsonFragment11.isUserVisibleHintWithParent())

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertFalse(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertFalse(childFragment0.isUserVisibleHintWithParent())

            assertTrue(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertFalse(grandsonFragment00.isUserVisibleHintWithParent())

            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(grandsonFragment01.isUserVisibleHintWithParent())

            assertTrue(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertTrue(childFragment1.isUserVisibleHintWithParent())

            assertTrue(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertTrue(grandsonFragment10.isUserVisibleHintWithParent())

            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(grandsonFragment11.isUserVisibleHintWithParent())
        } catch (e: Exception) {
            throw e
        }

        try {
            val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = fragment.findFragmentByViewPagerCurrentItem(0) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertTrue(childFragment0.isUserVisibleHintWithParent())

            val grandsonFragment00 = childFragment0.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertTrue(grandsonFragment00.isUserVisibleHintWithParent())

            val grandsonFragment01 = childFragment0.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(grandsonFragment01.isUserVisibleHintWithParent())

            val childFragment1 = fragment.findFragmentByViewPagerCurrentItem(1) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertFalse(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertFalse(childFragment1.isUserVisibleHintWithParent())

            val grandsonFragment10 = childFragment1.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertFalse(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertFalse(grandsonFragment10.isUserVisibleHintWithParent())

            val grandsonFragment11 = childFragment1.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(grandsonFragment11.isUserVisibleHintWithParent())

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertFalse(childFragment0.isResumed && childFragment0.userVisibleHint)
            assertFalse(childFragment0.isUserVisibleHintWithParent())

            assertFalse(grandsonFragment00.isResumed && grandsonFragment00.userVisibleHint)
            assertFalse(grandsonFragment00.isUserVisibleHintWithParent())

            assertFalse(grandsonFragment01.isResumed && grandsonFragment01.userVisibleHint)
            assertFalse(grandsonFragment01.isUserVisibleHintWithParent())

            assertTrue(childFragment1.isResumed && childFragment1.userVisibleHint)
            assertTrue(childFragment1.isUserVisibleHintWithParent())

            assertTrue(grandsonFragment10.isResumed && grandsonFragment10.userVisibleHint)
            assertTrue(grandsonFragment10.isUserVisibleHintWithParent())

            assertFalse(grandsonFragment11.isResumed && grandsonFragment11.userVisibleHint)
            assertFalse(grandsonFragment11.isUserVisibleHintWithParent())
        } catch (e: Exception) {
            throw e
        }
    }

    @Test
    fun testIsResumedWithParent() {
        try {
            val fragmentScenario = ResumeOnlyCurrentParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = fragment.findFragmentByViewPagerCurrentItem(0) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed)
            assertTrue(childFragment0.isResumedWithParent())

            val grandsonFragment00 = childFragment0.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed)
            assertTrue(grandsonFragment00.isResumedWithParent())

            val grandsonFragment01 = childFragment0.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertFalse(grandsonFragment01.isResumed)
            assertFalse(grandsonFragment01.isResumedWithParent())

            val childFragment1 = fragment.findFragmentByViewPagerCurrentItem(1) as ResumeOnlyCurrentChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertFalse(childFragment1.isResumed)
            assertFalse(childFragment1.isResumedWithParent())

            val grandsonFragment10 = childFragment1.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertFalse(grandsonFragment10.isResumed)
            assertFalse(grandsonFragment10.isResumedWithParent())

            val grandsonFragment11 = childFragment1.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertFalse(grandsonFragment11.isResumed)
            assertFalse(grandsonFragment11.isResumedWithParent())

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertFalse(childFragment0.isResumed)
            assertFalse(childFragment0.isResumedWithParent())

            assertFalse(grandsonFragment00.isResumed)
            assertFalse(grandsonFragment00.isResumedWithParent())

            assertFalse(grandsonFragment01.isResumed)
            assertFalse(grandsonFragment01.isResumedWithParent())

            assertTrue(childFragment1.isResumed)
            assertTrue(childFragment1.isResumedWithParent())

            assertTrue(grandsonFragment10.isResumed)
            assertTrue(grandsonFragment10.isResumedWithParent())

            assertFalse(grandsonFragment11.isResumed)
            assertFalse(grandsonFragment11.isResumedWithParent())
        } catch (e: Exception) {
            throw e
        }

        try {
            val fragmentScenario = UserVisibleHintParentViewPagerFragment::class.launchFragmentInContainer()
            val fragment = fragmentScenario.getFragmentSync()

            val childFragment0 = fragment.findFragmentByViewPagerCurrentItem(0) as UserVisibleHintChildViewPagerFragment
            assertEquals(0, childFragment0.tabIndex)
            assertTrue(childFragment0.isResumed)
            assertTrue(childFragment0.isResumedWithParent())

            val grandsonFragment00 = childFragment0.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(0, grandsonFragment00.tabIndex)
            assertEquals(0, grandsonFragment00.pageIndex)
            assertTrue(grandsonFragment00.isResumed)
            assertTrue(grandsonFragment00.isResumedWithParent())

            val grandsonFragment01 = childFragment0.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(0, grandsonFragment01.tabIndex)
            assertEquals(1, grandsonFragment01.pageIndex)
            assertTrue(grandsonFragment01.isResumed)
            assertTrue(grandsonFragment01.isResumedWithParent())

            val childFragment1 = fragment.findFragmentByViewPagerCurrentItem(1) as UserVisibleHintChildViewPagerFragment
            assertEquals(1, childFragment1.tabIndex)
            assertTrue(childFragment1.isResumed)
            assertTrue(childFragment1.isResumedWithParent())

            val grandsonFragment10 = childFragment1.findFragmentByViewPagerCurrentItem(0) as TextFragment
            assertEquals(1, grandsonFragment10.tabIndex)
            assertEquals(0, grandsonFragment10.pageIndex)
            assertTrue(grandsonFragment10.isResumed)
            assertTrue(grandsonFragment10.isResumedWithParent())

            val grandsonFragment11 = childFragment1.findFragmentByViewPagerCurrentItem(1) as TextFragment
            assertEquals(1, grandsonFragment11.tabIndex)
            assertEquals(1, grandsonFragment11.pageIndex)
            assertTrue(grandsonFragment11.isResumed)
            assertTrue(grandsonFragment11.isResumedWithParent())

            fragmentScenario.onFragment {
                fragment.view!!.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).currentItem = 1
            }
            Thread.sleep(200)

            assertTrue(childFragment0.isResumed)
            assertTrue(childFragment0.isResumedWithParent())

            assertTrue(grandsonFragment00.isResumed)
            assertTrue(grandsonFragment00.isResumedWithParent())

            assertTrue(grandsonFragment01.isResumed)
            assertTrue(grandsonFragment01.isResumedWithParent())

            assertTrue(childFragment1.isResumed)
            assertTrue(childFragment1.isResumedWithParent())

            assertTrue(grandsonFragment10.isResumed)
            assertTrue(grandsonFragment10.isResumedWithParent())

            assertTrue(grandsonFragment11.isResumed)
            assertTrue(grandsonFragment11.isResumedWithParent())
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