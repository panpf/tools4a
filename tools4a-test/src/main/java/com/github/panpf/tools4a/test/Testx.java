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
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;

public class Testx {


    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivity(@NonNull Class<T> activityClass) {
        return ActivityScenario.launch(activityClass);
    }

    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivity(
            @NonNull Class<T> activityClass, @Nullable Bundle activityOptions) {
        return ActivityScenario.launch(activityClass, activityOptions);
    }

    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivity(
            @NonNull Intent activityIntent) {
        return ActivityScenario.launch(activityIntent);
    }

    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivity(
            @NonNull Intent activityIntent, @Nullable Bundle activityOptions) {
        return ActivityScenario.launch(activityIntent, activityOptions);
    }


    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivityWithOn(
            @NonNull Class<T> activityClass, @NonNull ActivityScenarioAction<T> action) {
        ActivityScenario<T> scenario = ActivityScenario.launch(activityClass);
        action.perform(scenario);
        return scenario;
    }

    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivityWithOn(
            @NonNull Class<T> activityClass, @Nullable Bundle activityOptions, @NonNull ActivityScenarioAction<T> action) {
        ActivityScenario<T> scenario = ActivityScenario.launch(activityClass, activityOptions);
        action.perform(scenario);
        return scenario;
    }

    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivityWithOn(
            @NonNull Intent activityIntent, @NonNull ActivityScenarioAction<T> action) {
        ActivityScenario<T> scenario = ActivityScenario.launch(activityIntent);
        action.perform(scenario);
        return scenario;
    }

    @NonNull
    public static <T extends Activity> ActivityScenario<T> launchActivityWithOn(
            @NonNull Intent activityIntent, @Nullable Bundle activityOptions, @NonNull ActivityScenarioAction<T> action) {
        ActivityScenario<T> scenario = ActivityScenario.launch(activityIntent, activityOptions);
        action.perform(scenario);
        return scenario;
    }


    public static <T extends Activity> void launchActivityWithOnUse(
            @NonNull Class<T> activityClass, @NonNull ActivityScenario.ActivityAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityClass)) {
            scenario.onActivity(action);
        }
    }

    public static <T extends Activity> void launchActivityWithOnUse(
            @NonNull Class<T> activityClass, @Nullable Bundle activityOptions, @NonNull ActivityScenario.ActivityAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityClass, activityOptions)) {
            scenario.onActivity(action);
        }
    }

    public static <T extends Activity> void launchActivityWithOnUse(
            @NonNull Intent activityIntent, @NonNull ActivityScenario.ActivityAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityIntent)) {
            scenario.onActivity(action);
        }
    }

    public static <T extends Activity> void launchActivityWithOnUse(
            @NonNull Intent activityIntent, @Nullable Bundle activityOptions, @NonNull ActivityScenario.ActivityAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityIntent, activityOptions)) {
            scenario.onActivity(action);
        }
    }


    public static <T extends Activity> void launchActivityWithUse(
            @NonNull Class<T> activityClass, @NonNull ActivityScenarioAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityClass)) {
            action.perform(scenario);
        }
    }

    public static <T extends Activity> void launchActivityWithUse(
            @NonNull Class<T> activityClass, @Nullable Bundle activityOptions, @NonNull ActivityScenarioAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityClass, activityOptions)) {
            action.perform(scenario);
        }
    }

    public static <T extends Activity> void launchActivityWithUse(
            @NonNull Intent activityIntent, @NonNull ActivityScenarioAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityIntent)) {
            action.perform(scenario);
        }
    }

    public static <T extends Activity> void launchActivityWithUse(
            @NonNull Intent activityIntent, @Nullable Bundle activityOptions, @NonNull ActivityScenarioAction<T> action) {
        try (ActivityScenario<T> scenario = ActivityScenario.launch(activityIntent, activityOptions)) {
            action.perform(scenario);
        }
    }


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


    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragment(@NonNull Class<T> fragmentClass) {
        return FragmentScenario.launch(fragmentClass);
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragment(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs) {
        return FragmentScenario.launch(fragmentClass, fragmentArgs);
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragment(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs, @Nullable FragmentFactory factory) {
        return FragmentScenario.launch(fragmentClass, fragmentArgs, factory);
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragment(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs,
            @StyleRes int themeResId, @Nullable FragmentFactory factory) {
        return FragmentScenario.launch(fragmentClass, fragmentArgs, themeResId, factory);
    }


    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainer(@NonNull Class<T> fragmentClass) {
        return FragmentScenario.launchInContainer(fragmentClass);
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainer(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs) {
        return FragmentScenario.launchInContainer(fragmentClass, fragmentArgs);
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainer(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs, @Nullable FragmentFactory factory) {
        return FragmentScenario.launchInContainer(fragmentClass, fragmentArgs, factory);
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainer(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs,
            @StyleRes int themeResId, @Nullable FragmentFactory factory) {
        return FragmentScenario.launchInContainer(fragmentClass, fragmentArgs, themeResId, factory);
    }


    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentWithOn(
            @NonNull Class<T> fragmentClass, @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launch(fragmentClass);
        scenario.onFragment(action);
        return scenario;
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentWithOn(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs,
            @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launch(fragmentClass, fragmentArgs);
        scenario.onFragment(action);
        return scenario;
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentWithOn(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs,
            @Nullable FragmentFactory factory, @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launch(fragmentClass, fragmentArgs, factory);
        scenario.onFragment(action);
        return scenario;
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentWithOn(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs, @StyleRes int themeResId,
            @Nullable FragmentFactory factory, @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launch(fragmentClass, fragmentArgs, themeResId, factory);
        scenario.onFragment(action);
        return scenario;
    }


    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainerWithOn(
            @NonNull Class<T> fragmentClass, @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launchInContainer(fragmentClass);
        scenario.onFragment(action);
        return scenario;
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainerWithOn(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs,
            @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launchInContainer(fragmentClass, fragmentArgs);
        scenario.onFragment(action);
        return scenario;
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainerWithOn(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs,
            @Nullable FragmentFactory factory, @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launchInContainer(fragmentClass, fragmentArgs, factory);
        scenario.onFragment(action);
        return scenario;
    }

    @NonNull
    public static <T extends Fragment> FragmentScenario<T> launchFragmentInContainerWithOn(
            @NonNull Class<T> fragmentClass, @Nullable Bundle fragmentArgs, @StyleRes int themeResId,
            @Nullable FragmentFactory factory, @NonNull FragmentScenario.FragmentAction<T> action) {
        FragmentScenario<T> scenario = FragmentScenario.launchInContainer(fragmentClass, fragmentArgs, themeResId, factory);
        scenario.onFragment(action);
        return scenario;
    }


    @NonNull
    public static <T extends Fragment> T getFragmentSync(@NonNull FragmentScenario<T> scenario) {
        Fragment[] activityArray = new Fragment[1];
        scenario.onFragment(activity -> activityArray[0] = activity);
        //noinspection unchecked
        T t = (T) activityArray[0];
        if (t == null) {
            throw new RuntimeException("Fragment is destroyed");
        }
        return t;
    }
}
