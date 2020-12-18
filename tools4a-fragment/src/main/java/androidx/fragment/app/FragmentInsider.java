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

package androidx.fragment.app;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

public class FragmentInsider {

    /**
     * Returns the maxState of the specified Fragment
     */
    @NonNull
    public static Lifecycle.State getMaxState(@NonNull Fragment fragment) {
        return fragment.mMaxState;
    }

    /**
     * Returns the smallest maxState of the specified Fragment and all its parent Fragments
     */
    @NonNull
    public static Lifecycle.State getMaxStateWithParent(@NonNull Fragment fragment) {
        Lifecycle.State state = null;
        Fragment currentFragment = fragment;
        while (currentFragment != null) {
            Lifecycle.State parentState = currentFragment.mMaxState;
            if (state == null || parentState.compareTo(state) < 0) {
                state = parentState;
            }
            currentFragment = currentFragment.getParentFragment();
        }
        return state;
    }
}
