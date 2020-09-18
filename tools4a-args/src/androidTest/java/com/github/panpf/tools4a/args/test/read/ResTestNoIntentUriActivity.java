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

package com.github.panpf.tools4a.args.test.read;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

public class ResTestNoIntentUriActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntentWithNothing(@NonNull Context context) {
        Intent starter = new Intent(context, ResTestNoIntentUriActivity.class);
        starter.setAction(Intent.ACTION_VIEW);
        starter.setData(Uri.parse("tools4a://tools4a.com/panpf"));
        return starter;
    }
}
