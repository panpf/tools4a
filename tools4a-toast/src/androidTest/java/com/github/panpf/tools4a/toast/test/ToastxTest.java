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

package com.github.panpf.tools4a.toast.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.toast.Toastx;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ToastxTest {

    @NonNull
    private final ActivityTestRule<TestFragmentActivity> fragmentActivityRule = new ActivityTestRule<>(TestFragmentActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getFragmentActivityRule() {
        return this.fragmentActivityRule;
    }

    @Test
    public final void testContextToast() {
        Activity activity = this.fragmentActivityRule.getActivity();

        Toastx.showLong(activity, "今天是2018年10月18号");
        Toastx.showLong(activity, R.string.toast_test);
        Toastx.showWithFormatLong(activity, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showWithFormatLong(activity, R.string.toast_test_tp, 2018, 10, 18);

        Toastx.showShort(activity, "今天是2018年10月18号");
        Toastx.showShort(activity, R.string.toast_test);
        Toastx.showWithFormatShort(activity, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showWithFormatShort(activity, R.string.toast_test_tp, 2018, 10, 18);
    }

    @Test
    public final void testSupportFragmentToast() {
        Fragment fragment = fragmentActivityRule.getActivity().getFragment();

        Toastx.showLong(fragment, "今天是2018年10月18号");
        Toastx.showLong(fragment, R.string.toast_test);
        Toastx.showWithFormatLong(fragment, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showWithFormatLong(fragment, R.string.toast_test_tp, 2018, 10, 18);

        Toastx.showShort(fragment, "今天是2018年10月18号");
        Toastx.showShort(fragment, R.string.toast_test);
        Toastx.showWithFormatShort(fragment, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showWithFormatShort(fragment, R.string.toast_test_tp, 2018, 10, 18);
    }

    @Test
    public final void testViewToast() {
        View view = this.fragmentActivityRule.getActivity().getView();

        Toastx.showLong(view, "今天是2018年10月18号");
        Toastx.showLong(view, R.string.toast_test);
        Toastx.showWithFormatLong(view, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showWithFormatLong(view, R.string.toast_test_tp, 2018, 10, 18);

        Toastx.showShort(view, "今天是2018年10月18号");
        Toastx.showShort(view, R.string.toast_test);
        Toastx.showWithFormatShort(view, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showWithFormatShort(view, R.string.toast_test_tp, 2018, 10, 18);
    }

    @Test
    public final void testWithViewToast() {
        Toastx.showLongWithView(LayoutInflater.from(InstrumentationRegistry.getContext()).inflate(R.layout.view_toast, null, false));
        Toastx.showShortWithView(LayoutInflater.from(InstrumentationRegistry.getContext()).inflate(R.layout.view_toast, null, false));
    }

    public static class TestFragmentActivity extends FragmentActivity {

        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new Fragment()).commit();
        }

        @NonNull
        public Fragment getFragment() {
            return getSupportFragmentManager().findFragmentById(android.R.id.content);
        }

        public View getView() {
            return findViewById(android.R.id.content);
        }
    }
}
