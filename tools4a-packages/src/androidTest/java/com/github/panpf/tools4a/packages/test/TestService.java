package com.github.panpf.tools4a.packages.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class TestService extends Service {

    public static String SHARE_KEY = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SHARE_KEY = intent.getStringExtra("SHARE_KEY");
        return super.onStartCommand(intent, flags, startId);
    }
}
