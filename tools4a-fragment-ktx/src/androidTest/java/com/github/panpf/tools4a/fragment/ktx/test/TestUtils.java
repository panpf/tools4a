package com.github.panpf.tools4a.fragment.ktx.test;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.github.panpf.tools4a.fragment.ktx.test.ResultRunnable;

import java.util.concurrent.CountDownLatch;

public class TestUtils {

    /**
     * Execute the specified code block in the main thread
     */
    public static void waitRunInUI(@NonNull final Runnable block) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            block.run();
        } else {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    block.run();
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Execute the specified code block in the main thread
     */
    @NonNull
    public static <T> T waitRunInUIResult(@NonNull final ResultRunnable<T> block) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return block.run();
        } else {
            final Object[] results = new Object[1];
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    results[0] = block.run();
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //noinspection unchecked
            T result = (T) results[0];
            if (result != null) {
                return result;
            } else {
                throw new IllegalArgumentException("return result cannot be null");
            }
        }
    }
}
