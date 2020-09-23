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
import com.github.panpf.tools4a.fragment.ktx.*
import com.github.panpf.tools4a.test.ktx.getFragmentSync
import com.github.panpf.tools4a.test.ktx.launchActivityWithOnUse
import com.github.panpf.tools4a.test.ktx.launchFragmentInContainer
import com.github.panpf.tools4j.test.ktx.assertNoThrow
import com.github.panpf.tools4j.test.ktx.assertThrow
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentxTest {

    @Test
    fun testGetApplication() {
        val scenario = launchFragmentInContainer(Fragment::class)
        val fragment = scenario.getFragmentSync()
        Assert.assertNotNull(fragment.getApplication())
        scenario.moveToState(Lifecycle.State.DESTROYED)
        Assert.assertNull(fragment.getApplication())
    }

    @Test
    fun testRequireApplication() {
        val scenario = launchFragmentInContainer(Fragment::class)
        val fragment = scenario.getFragmentSync()
        assertNoThrow { fragment.requireApplication() }
        scenario.moveToState(Lifecycle.State.DESTROYED)
        assertThrow(IllegalStateException::class) { fragment.requireApplication() }
    }

    @Test
    fun testDestroyed() {
        val scenario = launchFragmentInContainer(Fragment::class)
        val fragment = scenario.getFragmentSync()
        Assert.assertFalse(fragment.isDestroyed())
        scenario.moveToState(Lifecycle.State.DESTROYED)
        Assert.assertTrue(fragment.isDestroyed())
        Assert.assertTrue(Fragment().isDestroyed())
    }

    @Test
    fun testGetImplWithParent() {
        val scenario = launchFragmentInContainer(TestImplSupportFragment::class)
        val fragment = scenario.getFragmentSync()
        Assert.assertEquals(
                TestImplSupportFragment::class.java,
                fragment.getImplFromParent(ImplTestInterface::class.java)!!.javaClass
        )
        Assert.assertEquals(
                TestImplSupportFragment::class.java,
                fragment.childFragment.getImplFromParent(ImplTestInterface::class.java)!!.javaClass
        )
        val scenario2 = launchFragmentInContainer(TestImplSupportFragment2::class)
        val fragment2 = scenario2.getFragmentSync()
        Assert.assertNull(fragment2.getImplFromParent(ImplTestInterface::class.java))
        Assert.assertNull(TestImplSupportFragment2().getImplFromParent(ImplTestInterface::class.java))
    }

    @Test
    fun testInstantiate() {
        val fragment = Fragment::class.java.instantiate(BundleBuilder().putString("key", "testInstantiate").build())
        Assert.assertEquals(Fragment::class.java.name, fragment.javaClass.name)
        Assert.assertEquals("testInstantiate", fragment.arguments!!.getString("key"))
        val fragment1 = Fragment::class.java.instantiate()
        Assert.assertEquals(Fragment::class.java.name, fragment1.javaClass.name)
    }

    @Test
    @Suppress("DEPRECATION")
    fun testFindUserVisibleChildFragmentByUserVisibleHint() {
        launchActivityWithOnUse(TestFindUserVisibleByUserVisibleHintActivity::class) { activity: TestFindUserVisibleByUserVisibleHintActivity ->
            val fragmentFromActivity = activity.findUserVisibleChildFragmentByUserVisibleHint()!!
            Assert.assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromActivity.javaClass.name)
            val fragmentFromList = activity.supportFragmentManager.fragments.findUserVisibleChildFragmentByUserVisibleHint()!!
            Assert.assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromList.javaClass.name)
            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByUserVisibleHintFragment
            val fragmentFromChildFragment = fragmentFromActivity2.findUserVisibleChildFragmentByUserVisibleHint()!!
            Assert.assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    @Test
    fun testFindUserVisibleChildFragmentByResumed() {
        launchActivityWithOnUse(TestFindUserVisibleByResumedActivity::class) { activity: TestFindUserVisibleByResumedActivity ->
            val fragmentFromActivity = activity.findUserVisibleChildFragmentByResumed()!!
            Assert.assertEquals(TestFindUserVisibleByResumedFragment::class.java.name, fragmentFromActivity.javaClass.name)
            val fragmentFromList = activity.supportFragmentManager.fragments.findUserVisibleChildFragmentByResumed()!!
            Assert.assertEquals(TestFindUserVisibleByResumedFragment::class.java.name, fragmentFromList.javaClass.name)
            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByResumedFragment
            val fragmentFromChildFragment = fragmentFromActivity2.findUserVisibleChildFragmentByResumed()!!
            Assert.assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
        }
    }

    @Test
    fun testFindFragmentByViewPagerCurrentItem() {
        launchActivityWithOnUse(TestFindUserVisibleByUserVisibleHintActivity::class) { activity: TestFindUserVisibleByUserVisibleHintActivity ->
            val fragmentFromActivity = activity.findFragmentByViewPagerCurrentItem(2)!!
            Assert.assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromActivity.javaClass.name)
            Assert.assertTrue(fragmentFromActivity.tag, fragmentFromActivity.tag.orEmpty().startsWith("android:switcher") && fragmentFromActivity.tag.orEmpty().endsWith(":2"))
            val fragmentFromList = activity.supportFragmentManager.fragments.findFragmentByViewPagerCurrentItem(2)!!
            Assert.assertEquals(TestFindUserVisibleByUserVisibleHintFragment::class.java.name, fragmentFromList.javaClass.name)
            Assert.assertTrue(fragmentFromList.tag, fragmentFromList.tag.orEmpty().startsWith("android:switcher") && fragmentFromList.tag.orEmpty().endsWith(":2"))
            val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleByUserVisibleHintFragment
            val fragmentFromChildFragment = fragmentFromActivity2.findFragmentByViewPagerCurrentItem(3)!!
            Assert.assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
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

            val viewPager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPagerAt_viewPager)
            viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                override fun getItem(p0: Int): Fragment {
                    return if (p0 == 2) {
                        TestFindUserVisibleByUserVisibleHintFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    }
                }

                override fun getCount(): Int = 5
            }
            viewPager.currentItem = 2
        }
    }

    class TestFindUserVisibleByResumedActivity : FragmentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_view_pager)

            val viewPager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPagerAt_viewPager)
            viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(p0: Int): Fragment {
                    return if (p0 == 2) {
                        TestFindUserVisibleByResumedFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    }
                }

                override fun getCount(): Int = 5
            }
            viewPager.currentItem = 2
        }
    }

    class TestFindUserVisibleByUserVisibleHintFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        @Suppress("DEPRECATION")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val viewPager = view.findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPagerAt_viewPager)
            viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT) {
                override fun getItem(p0: Int): Fragment =
                        TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())

                override fun getCount(): Int = 5
            }
            viewPager.currentItem = 3
        }
    }

    class TestFindUserVisibleByResumedFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val viewPager = view.findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPagerAt_viewPager)
            viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(p0: Int): Fragment =
                        TextFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())

                override fun getCount(): Int = 5
            }
            viewPager.currentItem = 3
        }
    }

    class TextFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return TextView(context)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            (view as TextView).text = "position: ${arguments?.getString("position")}"
        }
    }
}
