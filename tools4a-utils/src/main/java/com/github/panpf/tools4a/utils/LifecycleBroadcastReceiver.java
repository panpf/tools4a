package com.github.panpf.tools4a.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * Listening to Lifecycle implements automatic registration, anti-registration of the broadcast receiver, as follows:
 * <pre class="prettyprint">
 * new LifecycleBroadcastReceiver(context, getLifecycle()){
 *     public void onReceive(Context context, Intent intent){
 *         ...
 *     }
 * }.registerCreateDestroy(new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
 * </pre>
 */
public abstract class LifecycleBroadcastReceiver extends BroadcastReceiver {

    @NonNull
    private final Context appContext;
    @NonNull
    private final LifecycleOwner lifecycleOwner;

    public LifecycleBroadcastReceiver(@NonNull Context context, @NonNull LifecycleOwner lifecycleOwner) {
        this.appContext = context.getApplicationContext();
        this.lifecycleOwner = lifecycleOwner;
    }

    public synchronized void registerCreateDestroy(@NonNull IntentFilter filter) {
        final ReceiverLifecycleEventObserver receiverLifecycleEventObserver = new ReceiverLifecycleEventObserver(
                appContext, this, filter, Lifecycle.Event.ON_CREATE, Lifecycle.Event.ON_DESTROY);
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.addObserver(receiverLifecycleEventObserver);
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            receiverLifecycleEventObserver.register();
        }
    }

    public synchronized void registerStartStop(@NonNull IntentFilter filter) {
        final ReceiverLifecycleEventObserver receiverLifecycleEventObserver = new ReceiverLifecycleEventObserver(
                appContext, this, filter, Lifecycle.Event.ON_START, Lifecycle.Event.ON_STOP);
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.addObserver(receiverLifecycleEventObserver);
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            receiverLifecycleEventObserver.register();
        }
    }

    public synchronized void registerResumePause(@NonNull IntentFilter filter) {
        final ReceiverLifecycleEventObserver receiverLifecycleEventObserver = new ReceiverLifecycleEventObserver(
                appContext, this, filter, Lifecycle.Event.ON_RESUME, Lifecycle.Event.ON_PAUSE);
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.addObserver(receiverLifecycleEventObserver);
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            receiverLifecycleEventObserver.register();
        }
    }

    private static class ReceiverLifecycleEventObserver implements LifecycleEventObserver {
        @NonNull
        private final Context appContext;
        @NonNull
        private final LifecycleBroadcastReceiver receiver;
        @NonNull
        private final IntentFilter filter;
        @NonNull
        private final Lifecycle.Event registerEvent;
        @NonNull
        private final Lifecycle.Event unregisterEvent;

        ReceiverLifecycleEventObserver(
                @NonNull Context context, @NonNull LifecycleBroadcastReceiver receiver,
                @NonNull IntentFilter filter, @NonNull Lifecycle.Event registerEvent,
                @NonNull Lifecycle.Event unregisterEvent
        ) {
            this.appContext = context.getApplicationContext();
            this.receiver = receiver;
            this.filter = filter;
            this.registerEvent = registerEvent;
            this.unregisterEvent = unregisterEvent;
        }

        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            if (event == registerEvent) {
                register();
            } else if (event == unregisterEvent) {
                unregister();
            }
        }

        private void register() {
            try {
                appContext.registerReceiver(receiver, filter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void unregister() {
            try {
                appContext.unregisterReceiver(receiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
