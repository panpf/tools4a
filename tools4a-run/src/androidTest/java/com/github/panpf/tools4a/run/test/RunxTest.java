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

package com.github.panpf.tools4a.run.test;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.run.ResultNullableRunnable;
import com.github.panpf.tools4a.run.ResultRunnable;
import com.github.panpf.tools4a.run.Runx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class RunxTest {

    @Test
    public void testGetMainHandler() {
        Assert.assertNotNull(Runx.getMainHandler());
    }

    @Test
    public void testRunInUI() {
        final String[] results = new String[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Runx.runInUI(new Runnable() {
            @Override
            public void run() {
                results[0] = Runx.isMainThread() ? "MainThread1" : "NoMainThread1";
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(results[0], "MainThread1");

        Runx.waitRunInUI(new Runnable() {
            @Override
            public void run() {
                results[0] = Runx.isMainThread() ? "MainThread2" : "NoMainThread2";
            }
        });
        Assert.assertEquals(results[0], "MainThread2");

        results[0] = Runx.waitRunInUIResult(new ResultRunnable<String>() {
            @NonNull
            @Override
            public String run() {
                return Runx.isMainThread() ? "MainThread3" : "NoMainThread3";
            }
        });
        Assert.assertEquals(results[0], "MainThread3");

        results[0] = Runx.waitRunInUINullableResult(new ResultNullableRunnable<String>() {
            @Nullable
            @Override
            public String run() {
                return Runx.isMainThread() ? "MainThread4" : null;
            }
        });
        Assert.assertEquals(results[0], "MainThread4");
    }

    @Test
    public void testIsMainThread() {
        Assert.assertFalse(Runx.isMainThread());
    }

    @Test
    public void testIsMainProcess() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertTrue(Runx.isMainProcess(context));
    }

    @Test
    public void testInProcessName() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertNotNull(Runx.getInProcessName(context));
        Assert.assertEquals(Runx.getInProcessNameSuffix(context), "");
    }
}
