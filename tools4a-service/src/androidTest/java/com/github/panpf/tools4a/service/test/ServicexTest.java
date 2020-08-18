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

package com.github.panpf.tools4a.service.test;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.service.Servicex;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class ServicexTest {

    @Test
    public void testIsRunning() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Servicex.start(context, TestService.class);
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertTrue(Servicex.isRunning(context, TestService.class, context.getPackageName()));
            Assert.assertFalse(Servicex.isRunning(context, NoRegisterTestService.class));
            Assert.assertFalse(Servicex.isRunning(context, TestService.class, context.getPackageName() + "_error"));

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertTrue(Servicex.isRunning(context, TestService.class, context.getPackageName()));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStart() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));

            Servicex.start(context, TestService.class);
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }

        try {
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));

            Servicex.start(context, TestService.class, new BundleBuilder().putString("SHARE_KEY", "testStartExtras").build());
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertEquals("testStartExtras", TestService.SHARE_KEY);
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStop() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Servicex.start(context, TestService.class);
            Thread.sleep(100);
            Assert.assertTrue(Servicex.isRunning(context, TestService.class));

            Servicex.stop(context, TestService.class);
            Thread.sleep(100);
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testIsAccessibilityServiceEnabled() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertFalse(Servicex.isAccessibilityServiceEnabled(context, AccessibilityService.class));
        Assert.assertFalse(Servicex.isAccessibilityServiceEnabled(context, AccessibilityService.class, context.getPackageName()));
    }

    public static class NoRegisterTestService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
