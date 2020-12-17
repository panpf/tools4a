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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager.widget.ViewPager
import com.github.panpf.tools4a.fragment.Fragmentx
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
        val fragment = Fragmentx.instantiate(Fragment::class.java, BundleBuilder().putString("key", "testInstantiate").build())
        assertEquals(Fragment::class.java.name, fragment.javaClass.name)
        assertEquals("testInstantiate", fragment.arguments!!.getString("key"))

        val fragment1 = Fragmentx.instantiate(Fragment::class.java)
        assertEquals(Fragment::class.java.name, fragment1.javaClass.name)
    }

    @Test
    @Suppress("DEPRECATION")
    fun testFindUserVisibleChildFragmentByUserVisibleHint() {
        TestFindUserVisibleByUserVisibleHintActivity::class.launchActivityWithOnUse { activity: TestFindUserVisibleByUserVisibleHintActivity ->
            val fragmentFromActivity = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(activity)!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromActivity.javaClass.name)

            val fragmentFromList = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(activity.supportFragmentManager.fragments)!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromList.javaClass.name)

            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByUserVisibleHintFragment
            val fragmentFromChildFragment = Fragmentx.findUserVisibleChildFragmentByUserVisibleHint(fragmentFromActivity2)!!
            assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    @Test
    fun testFindUserVisibleChildFragmentByResumed() {
        TestFindUserVisibleByResumedActivity::class.launchActivityWithOnUse { activity ->
            val fragmentFromActivity = Fragmentx.findUserVisibleChildFragmentByResumed(activity)!!
            assertEquals(TestFindUserVisibleByResumedFragment::class.java.name, fragmentFromActivity.javaClass.name)

            val fragmentFromList = Fragmentx.findUserVisibleChildFragmentByResumed(activity.supportFragmentManager.fragments)!!
            assertEquals(TestFindUserVisibleByResumedFragment::class.java.name, fragmentFromList.javaClass.name)

            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByResumedFragment
            val fragmentFromChildFragment = Fragmentx.findUserVisibleChildFragmentByResumed(fragmentFromActivity2)!!
            assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    @Test
    fun testFindFragmentByViewPagerCurrentItem() {
        TestFindUserVisibleByUserVisibleHintActivity::class.launchActivityWithOnUse { activity ->
            val fragmentFromActivity = Fragmentx.findFragmentByViewPagerCurrentItem(activity, 2)!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromActivity.javaClass.name)
            assertTrue(fragmentFromActivity.tag, fragmentFromActivity.tag.orEmpty().startsWith("android:switcher") && fragmentFromActivity.tag.orEmpty().endsWith(":2"))

            val fragmentFromList = Fragmentx.findFragmentByViewPagerCurrentItem(activity.supportFragmentManager.fragments, 2)!!
            assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromList.javaClass.name)
            assertTrue(fragmentFromList.tag, fragmentFromList.tag.orEmpty().startsWith("android:switcher") && fragmentFromList.tag.orEmpty().endsWith(":2"))

            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByUserVisibleHintFragment
            val fragmentFromChildFragment = Fragmentx.findFragmentByViewPagerCurrentItem(fragmentFromActivity2, 3)!!
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
            this.childFragmentManager.beginTransaction()
                    .replace(R.id.testAt_frame, TestImplSupportFragment2())
                    .commit()
        }

        val childFragment: Fragment
            get() = this.childFragmentManager.findFragmentById(R.id.testAt_frame)!!
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
                        Fragmentx.instantiate(TestFindUserVisibleByUserVisibleHintFragment::class.java, BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        Fragmentx.instantiate(TextFragment::class.java, BundleBuilder().putString("position", p0.toString()).build())
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
                        Fragmentx.instantiate(TestFindUserVisibleByResumedFragment::class.java, BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        Fragmentx.instantiate(TextFragment::class.java, BundleBuilder().putString("position", p0.toString()).build())
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
                            Fragmentx.instantiate(TextFragment::class.java, BundleBuilder().putString("position", p0.toString()).build())
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
                            Fragmentx.instantiate(TextFragment::class.java, BundleBuilder().putString("position", p0.toString()).build())
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
            (view as TextView).text = "position: " + arguments!!.getString("position")
        }
    }
}