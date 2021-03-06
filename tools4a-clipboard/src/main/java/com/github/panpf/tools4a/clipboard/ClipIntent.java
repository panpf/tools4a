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

package com.github.panpf.tools4a.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;

import androidx.annotation.NonNull;

public class ClipIntent extends ClipContent {

    @NonNull
    public Intent intent;

    ClipIntent(@NonNull String mimeType, @NonNull Intent intent) {
        super(mimeType);
        this.intent = intent;
    }

    public ClipIntent(@NonNull Intent intent) {
        this(ClipDescription.MIMETYPE_TEXT_INTENT, intent);
    }

    @Override
    public ClipData.Item toItem() {
        return new ClipData.Item(intent);
    }
}
