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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager.widget.ViewPager
import com.github.panpf.tools4a.fragment.ktx.*
import com.github.panpf.tools4a.test.ktx.getFragmentSync
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
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
    fun testFindUserVisibleChildFragmentByUserVisibleHint() {
        TestFindUserVisibleByUserVisibleHintActivity::class.launchActivityWithOnUse { activity: TestFindUserVisibleByUserVisibleHintActivity ->
            val fragmentFromActivity = activity.findUserVisibleChildFragmentByUserVisibleHint()!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromActivity.javaClass.name)

            val fragmentFromList = activity.supportFragmentManager.fragments.findUserVisibleChildFragmentByUserVisibleHint()!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromList.javaClass.name)

            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByUserVisibleHintFragment
            val fragmentFromChildFragment = fragmentFromActivity2.findUserVisibleChildFragmentByUserVisibleHint()!!
            assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    @Test
    fun testFindUserVisibleChildFragmentByResumed() {
        TestFindUserVisibleByResumedActivity::class.launchActivityWithOnUse { activity ->
            val fragmentFromActivity = activity.findUserVisibleChildFragmentByResumed()!!
            assertEquals(TestFindUserVisibleByResumedFragment::class.java.name, fragmentFromActivity.javaClass.name)

            val fragmentFromList = activity.supportFragmentManager.fragments.findUserVisibleChildFragmentByResumed()!!
            assertEquals(TestFindUserVisibleByResumedFragment::class.java.name, fragmentFromList.javaClass.name)

            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByResumedFragment
            val fragmentFromChildFragment = fragmentFromActivity2.findUserVisibleChildFragmentByResumed()!!
            assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    @Test
    fun testFindFragmentByViewPagerCurrentItem() {
        TestFindUserVisibleByUserVisibleHintActivity::class.launchActivityWithOnUse { activity ->
            val fragmentFromActivity = activity.findFragmentByViewPagerCurrentItem(2)!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromActivity.javaClass.name)
            assertTrue(fragmentFromActivity.tag, fragmentFromActivity.tag.orEmpty().startsWith("android:switcher") && fragmentFromActivity.tag.orEmpty().endsWith(":2"))

            val fragmentFromList = activity.supportFragmentManager.fragments.findFragmentByViewPagerCurrentItem(2)!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromList.javaClass.name)
            assertTrue(fragmentFromList.tag, fragmentFromList.tag.orEmpty().startsWith("android:switcher") && fragmentFromList.tag.orEmpty().endsWith(":2"))

            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByUserVisibleHintFragment
            val fragmentFromChildFragment = fragmentFromActivity2.findFragmentByViewPagerCurrentItem(3)!!
            assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    interface ImplTestInterface

    class TestImplSupportFragment : Fragment(), ImplTestInterface {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_test, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            childFragmentManager.beginTransaction()
                    .replace(R.id.testAt_frame, TestImplSupportFragment2())
                    .commit()
        }

        val childFragment: Fragment
            get() = childFragmentManager.findFragmentById(R.id.testAt_frame)!!
    }

    class TestImplSupportFragment2 : Fragment()

    class TestFindUserVisibleByUserVisibleHintActivity : FragmentActivity() {
        @Suppress("DEPRECATION")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_view_pager)
            findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                    override fun getCount(): Int = 5
                    override fun getItem(p0: Int) = if (p0 == 2) {
                        TestFindUserVisibleByUserVisibleHintFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    }
                }
                currentItem = 2
            }
        }
    }

    class TestFindUserVisibleByResumedActivity : FragmentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_view_pager)
            findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    override fun getCount(): Int = 5
                    override fun getItem(p0: Int) = if (p0 == 2) {
                        TestFindUserVisibleByResumedFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    }
                }
                currentItem = 2
            }
        }
    }

    class TestFindUserVisibleByUserVisibleHintFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        @Suppress("DEPRECATION")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                    override fun getCount(): Int = 5
                    override fun getItem(p0: Int): Fragment =
                            TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                }
                currentItem = 3
            }
        }
    }

    class TestFindUserVisibleByResumedFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager).apply {
                adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    override fun getCount(): Int = 5
                    override fun getItem(p0: Int): Fragment =
                            TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                }
                currentItem = 3
            }
        }
    }

    class TextFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return TextView(context)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            (view as TextView).text = "position: ${arguments?.getString("position")}"
        }
    }
}