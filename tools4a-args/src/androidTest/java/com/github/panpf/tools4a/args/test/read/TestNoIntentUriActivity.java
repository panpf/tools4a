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
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.github.panpf.tools4a.args.Argsx;

import org.junit.Assert;

public class TestNoIntentUriActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntentWithNothing(@NonNull Context context) {
        Intent starter = new Intent(context, TestNoIntentUriActivity.class);
        starter.setAction(Intent.ACTION_VIEW);
        starter.setData(Uri.parse("tools4a://tools4a.com/panpf"));
        return starter;
    }

    public void checkParams() {
        Activity activity = this;

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, "byteIntentUriOrDefault", ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, "shortIntentUriOrDefault", ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, "intIntentUriOrDefault", 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, "longIntentUriOrDefault", 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, "floatIntentUriOrDefault", ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, "doubleIntentUriOrDefault", ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, "booleanIntentUriOrDefault", false);
//        String stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, "stringIntentUriRequired");
        String stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefault", "default");
        String stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, "stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey");
        String stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptional");
        String stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, "stringIntentUriOptionalErrKey");

        //uri intent
        byte byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, "byteUriIntentOrDefault", ((byte) 0));
        short shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, "shortUriIntentOrDefault", ((short) 0));
        int intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, "intUriIntentOrDefault", 0);
        long longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, "longUriIntentOrDefault", 0L);
        float floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, "floatUriIntentOrDefault", ((float) 0));
        double doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, "doubleUriIntentOrDefault", ((double) 0));
        boolean booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, "booleanUriIntentOrDefault", false);
//        String stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, "stringUriIntentRequired");
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefault", "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptional");
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptionalErrKey");

        Assert.assertTrue(byteIntentUriOrDefault == ((byte) 0));
        Assert.assertTrue(shortIntentUriOrDefault == ((short) 0));
        Assert.assertTrue(intIntentUriOrDefault == 0);
        Assert.assertTrue(longIntentUriOrDefault == 0);
        Assert.assertTrue(floatIntentUriOrDefault == 0);
        Assert.assertTrue(doubleIntentUriOrDefault == 0);
        Assert.assertTrue(!booleanIntentUriOrDefault);
        Assert.assertNull(stringIntentUriOptional);
        Assert.assertNull(stringIntentUriOptionalErrKey);
        Assert.assertTrue(stringIntentUriOrDefault.equals("default"));
        Assert.assertTrue(stringIntentUriOrDefaultErrKey.equals("stringIntentUriOrDefaultErrKey"));

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == ((byte) 0));
        Assert.assertTrue(shortUriIntentOrDefault == ((short) 0));
        Assert.assertTrue(intUriIntentOrDefault == 0);
        Assert.assertTrue(longUriIntentOrDefault == 0L);
        Assert.assertTrue(floatUriIntentOrDefault == 0f);
        Assert.assertTrue(doubleUriIntentOrDefault == 0d);
        Assert.assertTrue(!booleanUriIntentOrDefault);
        Assert.assertNull(stringUriIntentOptional);
        Assert.assertNull(stringUriIntentOptionalErrKey);
        Assert.assertTrue(stringUriIntentOrDefault.equals("default"));
        Assert.assertTrue(stringUriIntentOrDefaultErrKey.equals("stringUriIntentOrDefaultErrKey"));
    }

    public void checkParams2() {
        Activity activity = this;

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.byte_intent_uri_or_default, ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.short_intent_uri_or_default, ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_intent_uri_or_default, 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.long_intent_uri_or_default, 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.float_intent_uri_or_default, ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.double_intent_uri_or_default, ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.boolean_intent_uri_or_default, false);
//        String stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, R.string.string_intent_uri_required);
        String stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_intent_uri_or_default, "default");
        String stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "stringIntentUriOrDefaultErrKey");
        String stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.string_intent_uri_optional);
        String stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        //uri intent
        byte byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.byte_uri_intent_or_default, ((byte) 0));
        short shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.short_uri_intent_or_default, ((short) 0));
        int intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_uri_intent_or_default, 0);
        long longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.long_uri_intent_or_default, 0L);
        float floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.float_uri_intent_or_default, ((float) 0));
        double doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.double_uri_intent_or_default, ((double) 0));
        boolean booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.boolean_uri_intent_or_default, false);
//        String stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, R.string.string_uri_intent_required);
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_intent_or_default, "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_intent_optional);
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        Assert.assertTrue(byteIntentUriOrDefault == ((byte) 0));
        Assert.assertTrue(shortIntentUriOrDefault == ((short) 0));
        Assert.assertTrue(intIntentUriOrDefault == 0);
        Assert.assertTrue(longIntentUriOrDefault == 0);
        Assert.assertTrue(floatIntentUriOrDefault == 0);
        Assert.assertTrue(doubleIntentUriOrDefault == 0);
        Assert.assertTrue(!booleanIntentUriOrDefault);
        Assert.assertNull(stringIntentUriOptional);
        Assert.assertNull(stringIntentUriOptionalErrKey);
        Assert.assertTrue(stringIntentUriOrDefault.equals("default"));
        Assert.assertTrue(stringIntentUriOrDefaultErrKey.equals("stringIntentUriOrDefaultErrKey"));

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == ((byte) 0));
        Assert.assertTrue(shortUriIntentOrDefault == ((short) 0));
        Assert.assertTrue(intUriIntentOrDefault == 0);
        Assert.assertTrue(longUriIntentOrDefault == 0L);
        Assert.assertTrue(floatUriIntentOrDefault == 0f);
        Assert.assertTrue(doubleUriIntentOrDefault == 0d);
        Assert.assertTrue(!booleanUriIntentOrDefault);
        Assert.assertNull(stringUriIntentOptional);
        Assert.assertNull(stringUriIntentOptionalErrKey);
        Assert.assertTrue(stringUriIntentOrDefault.equals("default"));
        Assert.assertTrue(stringUriIntentOrDefaultErrKey.equals("stringUriIntentOrDefaultErrKey"));
    }
}
