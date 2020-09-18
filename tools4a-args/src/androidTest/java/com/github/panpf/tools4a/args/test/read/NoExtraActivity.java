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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.github.panpf.tools4a.args.Argsx;

import org.junit.Assert;

public class NoExtraActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        return new Intent(context, NoExtraActivity.class);
    }

    public void checkParams() {
        Activity activity = this;

        Bundle extrasOptional = Argsx.readExtrasArgOrNull(activity);
        Bundle bundleDefault = new Bundle();
        bundleDefault.putString("errDefault", "default");
        Bundle extrasDefault = Argsx.readExtrasArgOr(activity, bundleDefault);

        Assert.assertNull(extrasOptional);
        Assert.assertEquals("default", extrasDefault.getString("errDefault"));
    }
}
