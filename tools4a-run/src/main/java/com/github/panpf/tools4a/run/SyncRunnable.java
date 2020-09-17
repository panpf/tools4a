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

package com.github.panpf.tools4a.run;

public final class SyncRunnable implements Runnable {

    private final Runnable mTarget;
    private boolean mComplete;

    public SyncRunnable(Runnable target) {
        mTarget = target;
    }

    @Override
    public void run() {
        mTarget.run();
        synchronized (this) {
            mComplete = true;
            notifyAll();
        }
    }

    public void waitForComplete() {
        synchronized (this) {
            while (!mComplete) {
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
