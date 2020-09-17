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
import android.os.Looper;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.run.ResultNullableRunnable;
import com.github.panpf.tools4a.run.ResultRunnable;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.run.SyncRunnable;
import com.github.panpf.tools4j.test.Assertx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RunxTest {

    @Test
    public void testGetMainHandler() {
        Assert.assertNotNull(Runx.getMainHandler());
        Assert.assertSame(Looper.getMainLooper(), Runx.getMainHandler().getLooper());
        Assert.assertSame(Runx.getMainHandler(), Runx.getMainHandler());
    }

    @Test
    public void testIsOnMainThread() {
        Assert.assertFalse(Runx.isOnMainThread());

        final SyncRunnable syncRunnable = new SyncRunnable(() ->
                Assert.assertTrue(Runx.isOnMainThread())
        );
        Runx.getMainHandler().post(syncRunnable);
        syncRunnable.waitForComplete();
    }

    @Test
    public void testIsOnNotMainThread() {
        Assert.assertTrue(Runx.isOnNotMainThread());

        final SyncRunnable syncRunnable = new SyncRunnable(() ->
                Assert.assertFalse(Runx.isOnNotMainThread())
        );
        Runx.getMainHandler().post(syncRunnable);
        syncRunnable.waitForComplete();
    }

    @Test
    public void testCheckMainThread() {
        Assertx.assertThrow(IllegalStateException.class, Runx::checkMainThread);

        final SyncRunnable syncRunnable = new SyncRunnable(() ->
                Assertx.assertNoThrow(Runx::checkMainThread)
        );
        Runx.getMainHandler().post(syncRunnable);
        syncRunnable.waitForComplete();
    }

    @Test
    public void testCheckNotMainThread() {
        Assertx.assertNoThrow(Runx::checkNotMainThread);

        final SyncRunnable syncRunnable = new SyncRunnable(() ->
                Assertx.assertThrow(IllegalStateException.class, Runx::checkNotMainThread)
        );
        Runx.getMainHandler().post(syncRunnable);
        syncRunnable.waitForComplete();
    }

    @Test
    public void testRunOnMainThread() {
        final SyncRunnable syncRunnable = new SyncRunnable(Runx::checkMainThread);
        Runx.runOnMainThread(syncRunnable);
        syncRunnable.waitForComplete();

        final SyncRunnable syncRunnable1 = new SyncRunnable(() -> Runx.runOnMainThread(Runx::checkMainThread));
        Runx.getMainHandler().post(syncRunnable1);
        syncRunnable1.waitForComplete();
    }

    @Test
    public void testRunOnMainThreadSync() {
        Runx.runOnMainThreadSync(Runx::checkMainThread);

        final SyncRunnable syncRunnable = new SyncRunnable(() ->
                Runx.runOnMainThreadSync(Runx::checkMainThread)
        );
        Runx.getMainHandler().post(syncRunnable);
        syncRunnable.waitForComplete();
    }

    @Test
    public void testRunOnMainThreadSyncResult() {
        final ResultRunnable<String> resultRunnable = () -> {
            Runx.checkMainThread();
            return "testRunOnMainThreadSyncResult";
        };
        Assert.assertEquals("testRunOnMainThreadSyncResult", Runx.runOnMainThreadSyncResult(resultRunnable));

        final SyncRunnable syncRunnable1 = new SyncRunnable(() ->
                Assert.assertEquals("testRunOnMainThreadSyncResult", Runx.runOnMainThreadSyncResult(resultRunnable))
        );
        Runx.getMainHandler().post(syncRunnable1);
        syncRunnable1.waitForComplete();

        final ResultRunnable<String> resultRunnable1 = () -> {
            Runx.checkMainThread();
            //noinspection ConstantConditions
            return null;
        };
        Assertx.assertThrow(IllegalArgumentException.class, () -> Runx.runOnMainThreadSyncResult(resultRunnable1));
    }

    @Test
    public void testRunOnMainThreadSyncResultNullable() {
        final ResultNullableRunnable<String> resultNullableRunnable = () -> {
            Runx.checkMainThread();
            return "testRunOnMainThreadSyncResultNullable";
        };
        Assert.assertEquals("testRunOnMainThreadSyncResultNullable", Runx.runOnMainThreadSyncResultNullable(resultNullableRunnable));

        final SyncRunnable syncRunnable1 = new SyncRunnable(() ->
                Assert.assertEquals("testRunOnMainThreadSyncResultNullable", Runx.runOnMainThreadSyncResultNullable(resultNullableRunnable))
        );
        Runx.getMainHandler().post(syncRunnable1);
        syncRunnable1.waitForComplete();

        final ResultNullableRunnable<String> resultNullableRunnable1 = () -> {
            Runx.checkMainThread();
            return null;
        };
        Assert.assertNull(Runx.runOnMainThreadSyncResultNullable(resultNullableRunnable1));
    }

    @Test
    public void testIsOnMainProcess() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertTrue(Runx.isOnMainProcess(context));
    }

    @Test
    public void testIsOnNotMainProcess() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertFalse(Runx.isOnNotMainProcess(context));
    }

    @Test
    public void testGetOnProcessNameOrNull() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertEquals(context.getPackageName(), Runx.getOnProcessNameOrNull(context));
    }

    @Test
    public void testGetOnProcessNameSuffixOrNull() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Assert.assertNull(Runx.getOnProcessNameSuffixOrNull(context));
    }
}
