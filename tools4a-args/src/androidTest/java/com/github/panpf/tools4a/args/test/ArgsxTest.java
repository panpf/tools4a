/*
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

package com.github.panpf.tools4a.args.test;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.args.test.read.NoExtraActivity;
import com.github.panpf.tools4a.args.test.read.ResTestActivity;
import com.github.panpf.tools4a.args.test.read.ResTestBothIntentUriActivity;
import com.github.panpf.tools4a.args.test.read.ResTestOnlyUriNoIntentActivity;
import com.github.panpf.tools4a.args.test.read.ResTestUriActivity;
import com.github.panpf.tools4a.args.test.read.TestActivity;
import com.github.panpf.tools4a.args.test.read.TestBothIntentUriActivity;
import com.github.panpf.tools4a.args.test.read.TestNoIntentUriActivity;
import com.github.panpf.tools4a.args.test.read.TestOnlyIntentNoUriActivity;
import com.github.panpf.tools4a.args.test.read.TestOnlyUriNoIntentActivity;
import com.github.panpf.tools4a.args.test.read.TestUriActivity;
import com.github.panpf.tools4a.test.Testx;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ArgsxTest {

    @Test
    public void activityIntentArgsTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestActivity.createIntent(context);
        Testx.<TestActivity>launchActivityWithOnUse(intent, activity -> {
            activity.checkParams();
            activity.getFragment().checkParams();
        });
    }

    @Test
    public void noExtrasActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = NoExtraActivity.createIntent(context);
        Testx.launchActivityWithOnUse(intent, NoExtraActivity::checkParams);
    }

    @Test
    public void resActivityIntentArgsTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = ResTestActivity.createIntent(context);
        Testx.<ResTestActivity>launchActivityWithOnUse(intent, activity -> {
            activity.checkParams();
            activity.getFragment().checkParams();
        });
    }

    @Test
    public void uriOnlyActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestOnlyUriNoIntentActivity.createIntentWithUri(context);
        Testx.launchActivityWithOnUse(intent, TestOnlyUriNoIntentActivity::checkParams);
    }

    @Test
    public void intentOnlyActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestOnlyIntentNoUriActivity.createIntentWithExtras(context);
        Testx.launchActivityWithOnUse(intent, TestOnlyIntentNoUriActivity::checkParams);
    }

    @Test
    public void bothUriIntentActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestBothIntentUriActivity.createIntentWithUriAndExtras(context);
        Testx.launchActivityWithOnUse(intent, TestBothIntentUriActivity::checkParams);
    }

    @Test
    public void noUriIntentActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestNoIntentUriActivity.createIntentWithNothing(context);
        Testx.launchActivityWithOnUse(intent, TestNoIntentUriActivity::checkParams);
    }

    @Test
    public void resUriOnlyActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = ResTestOnlyUriNoIntentActivity.createIntentWithUri(context);
        Testx.launchActivityWithOnUse(intent, ResTestOnlyUriNoIntentActivity::checkParams);
    }

    @Test
    public void resIntentOnlyActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestOnlyIntentNoUriActivity.createIntentWithExtras(context);
        Testx.launchActivityWithOnUse(intent, TestOnlyIntentNoUriActivity::checkParams2);
    }

    @Test
    public void resBothUriIntentActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = ResTestBothIntentUriActivity.createIntentWithUriAndExtras(context);
        Testx.launchActivityWithOnUse(intent, ResTestBothIntentUriActivity::checkParams);
    }

    @Test
    public void resNoUriIntentActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestNoIntentUriActivity.createIntentWithNothing(context);
        Testx.launchActivityWithOnUse(intent, TestNoIntentUriActivity::checkParams2);
    }

    @Test
    public void uriActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = TestUriActivity.createIntent(context);
        Testx.launchActivityWithOnUse(intent, TestUriActivity::checkParams);
    }

    @Test
    public void resUriActivityTest() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = ResTestUriActivity.createIntent(context);
        Testx.launchActivityWithOnUse(intent, ResTestUriActivity::checkParams);
    }
}
