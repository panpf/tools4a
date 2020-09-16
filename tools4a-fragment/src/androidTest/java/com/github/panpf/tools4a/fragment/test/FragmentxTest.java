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

package com.github.panpf.tools4a.fragment.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.viewpager.widget.ViewPager;

import com.github.panpf.tools4a.fragment.Fragmentx;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4j.lang.Stringx;
import com.github.panpf.tools4j.premise.Premisex;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class FragmentxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<>(TestActivity.class);
    @NonNull
    private final ActivityTestRule<TestFindUserVisibleChildActivity> findUserVisibleChildActivityRule = new ActivityTestRule<>(TestFindUserVisibleChildActivity.class);

    @Rule
    @NonNull
    public ActivityTestRule<TestActivity> getActivityTestRule() {
        return activityTestRule;
    }

    @Rule
    @NonNull
    public ActivityTestRule<TestFindUserVisibleChildActivity> getFindUserVisibleChildActivityRule() {
        return findUserVisibleChildActivityRule;
    }

    @Test
    public void testGetApplication() {
        Fragment supportFragment = activityTestRule.getActivity().getSupportFragment();

        Assert.assertNotNull(Fragmentx.getApplication(supportFragment));

        Assert.assertNull(Fragmentx.getApplication(new Fragment()));

        try {
            Fragmentx.requireApplication(new Fragment());
            Assert.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testDestroyed() throws InterruptedException {
        Fragment supportFragment = activityTestRule.getActivity().getSupportFragment();

        Assert.assertFalse(Fragmentx.isDestroyedCompat(supportFragment));

        activityTestRule.finishActivity();
        Thread.sleep(2000);

        Assert.assertTrue(Fragmentx.isDestroyedCompat(supportFragment));
    }

    @Test
    public void testGetImplWithParent() {
        TestImplSupportFragment supportFragment = (TestImplSupportFragment) activityTestRule.getActivity().getSupportFragment();

        Assert.assertEquals(Premisex.requireNotNull(Fragmentx.getImplFromParent(supportFragment, ImplTestInterface.class)).getClass(), TestImplSupportFragment.class);

        Assert.assertEquals(Premisex.requireNotNull(Fragmentx.getImplFromParent(supportFragment.getChildFragment(), ImplTestInterface.class)).getClass(), TestImplSupportFragment.class);

        Runx.runOnUiThreadAndWait(() -> activityTestRule.getActivity().convertChildFragment());

        TestImplSupportFragment2 supportFragment2 = Runx.runOnUiThreadAndWaitResult(() -> (TestImplSupportFragment2) activityTestRule.getActivity().getSupportFragment());

        Assert.assertEquals(Premisex.requireNotNull(Fragmentx.getImplFromParent(supportFragment2, ImplTestInterface.class)).getClass(), TestActivity.class);

        Assert.assertNull(Fragmentx.getImplFromParent(new TestImplSupportFragment2(), ImplTestInterface.class));
    }

    @Test
    public void testInstantiate() {
        Fragment supportFragment = Fragmentx.instantiate(Fragment.class, new BundleBuilder().putString("key", "testInstantiate").build());
        Assert.assertEquals(Fragment.class.getName(), supportFragment.getClass().getName());
        Assert.assertEquals("testInstantiate", Objects.requireNonNull(supportFragment.getArguments()).getString("key"));

        Fragment supportFragment2 = Fragmentx.instantiate(Fragment.class);
        Assert.assertEquals(Fragment.class.getName(), supportFragment2.getClass().getName());
    }

    @Test
    public void testFindUserVisibleChildFragment() {
        TestFindUserVisibleChildActivity activity = findUserVisibleChildActivityRule.getActivity();
        // 定义多少个 ActivityTestRule 测试方法执行的时候就会启动多少个 ActivityTestRule 为了让 findUserVisibleChildActivityRule 处于 resumed 状态
        activityTestRule.finishActivity();
        // 此测试要求手机处于屏幕解锁状态

        Fragment fragmentFromActivity = Premisex.requireNotNull(Fragmentx.findUserVisibleChildFragment(activity));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromActivity.getClass().getName());

        Fragment fragmentFromList = Premisex.requireNotNull(Fragmentx.findUserVisibleChildFragment(activity.getSupportFragmentManager().getFragments()));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromList.getClass().getName());

        TestFindUserVisibleChildFragment fragmentFromActivity2 = (TestFindUserVisibleChildFragment) fragmentFromActivity;
        Fragment fragmentFromChildFragment = Premisex.requireNotNull(Fragmentx.findUserVisibleChildFragment(fragmentFromActivity2));
        Assert.assertTrue(fragmentFromChildFragment.getTag(), Stringx.orEmpty(fragmentFromChildFragment.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromChildFragment.getTag()).endsWith(":3"));
    }

    @Test
    public void testFindFragmentByViewPagerCurrentItem() {
        TestFindUserVisibleChildActivity activity = findUserVisibleChildActivityRule.getActivity();
        // 定义多少个 ActivityTestRule 测试方法执行的时候就会启动多少个 ActivityTestRule 为了让 findUserVisibleChildActivityRule 处于 resumed 状态
        activityTestRule.finishActivity();
        // 此测试要求手机处于屏幕解锁状态

        Fragment fragmentFromActivity = Premisex.requireNotNull(Fragmentx.findFragmentByViewPagerCurrentItem(activity, 2));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromActivity.getClass().getName());
        Assert.assertTrue(fragmentFromActivity.getTag(), Stringx.orEmpty(fragmentFromActivity.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromActivity.getTag()).endsWith(":2"));

        Fragment fragmentFromList = Premisex.requireNotNull(Fragmentx.findFragmentByViewPagerCurrentItem(activity.getSupportFragmentManager().getFragments(), 2));
        Assert.assertEquals(TestFindUserVisibleChildFragment.class.getName(), fragmentFromList.getClass().getName());
        Assert.assertTrue(fragmentFromList.getTag(), Stringx.orEmpty(fragmentFromList.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromList.getTag()).endsWith(":2"));

        TestFindUserVisibleChildFragment fragmentFromActivity2 = (TestFindUserVisibleChildFragment) fragmentFromActivity;
        Fragment fragmentFromChildFragment = Premisex.requireNotNull(Fragmentx.findFragmentByViewPagerCurrentItem(fragmentFromActivity2, 3));
        Assert.assertTrue(fragmentFromChildFragment.getTag(), Stringx.orEmpty(fragmentFromChildFragment.getTag()).startsWith("android:switcher") && Stringx.orEmpty(fragmentFromChildFragment.getTag()).endsWith(":3"));
    }

    public interface ImplTestInterface {
    }

    public static class TestActivity extends FragmentActivity implements ImplTestInterface {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_multi_frame);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new TestImplSupportFragment())
                    .commit();
        }

        @NonNull
        public Fragment getSupportFragment() {
            return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        public final void convertChildFragment() {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new FragmentxTest.TestImplSupportFragment2())
                    .commit();
        }
    }

    public static final class TestImplSupportFragment extends Fragment implements FragmentxTest.ImplTestInterface {

        @Nullable
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.at_test, container, false);
        }

        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            this.getChildFragmentManager().beginTransaction()
                    .replace(R.id.testAt_frame, new TestImplSupportFragment2())
                    .commit();
        }

        @NonNull
        public final Fragment getChildFragment() {
            return Premisex.requireNotNull(this.getChildFragmentManager().findFragmentById(R.id.testAt_frame));
        }
    }

    public static final class TestImplSupportFragment2 extends Fragment {

    }

    public static class TestFindUserVisibleChildActivity extends FragmentActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_view_pager);

            ViewPager viewPager = findViewById(R.id.viewPagerAt_viewPager);
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                @NotNull
                @Override
                public Fragment getItem(int p0) {
                    if (p0 == 2) {
                        return Fragmentx.instantiate(TestFindUserVisibleChildFragment.class, new BundleBuilder().putString("position", String.valueOf(p0)).build());
                    } else {
                        return Fragmentx.instantiate(TestFindUserVisibleChildFragment2.class, new BundleBuilder().putString("position", String.valueOf(p0)).build());
                    }
                }

                @Override
                public int getCount() {
                    return 5;
                }
            });
            viewPager.setCurrentItem(2);
        }
    }

    public static class TestFindUserVisibleChildFragment extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.at_view_pager, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            ViewPager viewPager = view.findViewById(R.id.viewPagerAt_viewPager);
            viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                @NotNull
                @Override
                public Fragment getItem(int p0) {
                    return Fragmentx.instantiate(TestFindUserVisibleChildFragment2.class, new BundleBuilder().putString("position", String.valueOf(p0)).build());
                }

                @Override
                public int getCount() {
                    return 5;
                }
            });
            viewPager.setCurrentItem(3);
        }
    }

    public static class TestFindUserVisibleChildFragment2 extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return new TextView(getContext());
        }

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            ((TextView) view).setText("position: " + Objects.requireNonNull(getArguments()).getString("position"));
        }
    }
}
