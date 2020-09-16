package com.github.panpf.tools4a.test;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;

public interface ActivityScenarioAction<A extends Activity> {
    void perform(@NonNull ActivityScenario<A> scenario);
}