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

package com.github.panpf.tools4a.test;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.core.app.ActivityScenario;

public class Testx {

    @NonNull
    public static <T extends Activity> T getActivitySync(@NonNull ActivityScenario<T> scenario) {
        Activity[] activityArray = new Activity[1];
        scenario.onActivity(activity -> activityArray[0] = activity);
        //noinspection unchecked
        T t = (T) activityArray[0];
        if (t == null) {
            throw new RuntimeException("Activity is destroyed");
        }
        return t;
    }

    @Nullable
    public static <T extends Activity> T getActivityOrNullSync(@Nullable ActivityScenario<T> scenario) {
        if (scenario == null) return null;
        Activity[] activityArray = new Activity[1];
        scenario.onActivity(activity -> activityArray[0] = activity);
        //noinspection unchecked
        return (T) activityArray[0];
    }

    public static <T extends Activity> void launchAndOnActivityWithUse(@NonNull Class<T> activityClass, @NonNull ActivityScenario.ActivityAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityClass)) {
            scenario.onActivity(action);
        }
    }

    public static <T extends Activity> void launchActivityWithUse(@NonNull Class<T> activityClass, @NonNull ActivityScenarioAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityClass)) {
            action.perform(scenario);
        }
    }
}
