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

public class ResTestBothIntentUriActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntentWithUriAndExtras(@NonNull Context context) {
        String params = "byteIntentUriOrDefault=" + ((byte) 1) +
                "&shortIntentUriOrDefault=" + ((short) 2) +
                "&intIntentUriOrDefault=" + 3 +
                "&longIntentUriOrDefault=" + 4L +
                "&floatIntentUriOrDefault=" + ((float) 5) +
                "&doubleIntentUriOrDefault=" + ((double) 6) +
                "&booleanIntentUriOrDefault=" + true +
                "&stringIntentUriRequired=" + "stringIntentUriRequired" +
                "&stringIntentUriOptional=" + "stringIntentUriOptional" +
                "&stringIntentUriOrDefault=" + "stringIntentUriOrDefault" +

                //Activity Uri Intent
                "&byteUriIntentOrDefault=" + ((byte) 11) +
                "&shortUriIntentOrDefault=" + ((short) 12) +
                "&intUriIntentOrDefault=" + 13 +
                "&longUriIntentOrDefault=" + 14L +
                "&floatUriIntentOrDefault=" + ((float) 15) +
                "&doubleUriIntentOrDefault=" + ((double) 16) +
                "&booleanUriIntentOrDefault=" + true +
                "&stringUriIntentRequired=" + "stringUriIntentRequired" +
                "&stringUriIntentOptional=" + "stringUriIntentOptional" +
                "&stringUriIntentOrDefault=" + "stringUriIntentOrDefault";

        Intent starter = new Intent(context, ResTestBothIntentUriActivity.class);
        starter.setAction(Intent.ACTION_VIEW);
        starter.setData(Uri.parse("tools4a://tools4a.com/panpf/tools4a/res/uri/intent?" + params));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_intent_uri_or_default), ((byte) -1));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.short_intent_uri_or_default), ((short) -2));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_intent_uri_or_default), -3);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.long_intent_uri_or_default), -4L);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.float_intent_uri_or_default), -5f);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.double_intent_uri_or_default), (-6d));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.boolean_intent_uri_or_default), true);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_intent_uri_required), "stringIntentRequired");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_intent_uri_optional), "stringIntentOptional");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_intent_uri_or_default), "stringIntentOrDefault");

        //Activity Uri Intent
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_uri_intent_or_default), ((byte) -11));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.short_uri_intent_or_default), ((short) -12));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_uri_intent_or_default), -13);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.long_uri_intent_or_default), -14L);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.float_uri_intent_or_default), -15f);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.double_uri_intent_or_default), (-16d));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.boolean_uri_intent_or_default), true);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_uri_intent_required), "stringUriIntentRequired");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_uri_intent_optional), "stringUriIntentOptional");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_uri_intent_or_default), "stringUriIntentOrDefault");
        return starter;
    }

    public void checkParams() {
        Activity activity = this;

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.byte_intent_uri_or_default, ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.short_intent_uri_or_default, ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_intent_uri_or_default, 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.long_intent_uri_or_default, 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.float_intent_uri_or_default, ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.double_intent_uri_or_default, ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.boolean_intent_uri_or_default, false);
        String stringIntentUriRequired = Argsx.readStringIntentUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.string_intent_uri_required);
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
        String stringUriIntentRequired = Argsx.readStringUriIntentArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_intent_required);
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_intent_or_default, "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_intent_optional);
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        Assert.assertTrue(byteIntentUriOrDefault == ((byte) -1));
        Assert.assertTrue(shortIntentUriOrDefault == ((short) -2));
        Assert.assertTrue(intIntentUriOrDefault == -3);
        Assert.assertTrue(longIntentUriOrDefault == -4L);
        Assert.assertTrue(floatIntentUriOrDefault == -5f);
        Assert.assertTrue(doubleIntentUriOrDefault == -6d);
        Assert.assertTrue(booleanIntentUriOrDefault);
        Assert.assertTrue(stringIntentUriRequired.equals("stringIntentRequired"));
        Assert.assertTrue(stringIntentUriOptional.equals("stringIntentOptional"));
        Assert.assertNull(stringIntentUriOptionalErrKey);
        Assert.assertTrue(stringIntentUriOrDefault.equals("stringIntentOrDefault"));
        Assert.assertTrue(stringIntentUriOrDefaultErrKey.equals("stringIntentUriOrDefaultErrKey"));

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == ((byte) 11));
        Assert.assertTrue(shortUriIntentOrDefault == ((short) 12));
        Assert.assertTrue(intUriIntentOrDefault == 13);
        Assert.assertTrue(longUriIntentOrDefault == 14L);
        Assert.assertTrue(floatUriIntentOrDefault == 15f);
        Assert.assertTrue(doubleUriIntentOrDefault == 16d);
        Assert.assertTrue(booleanUriIntentOrDefault);
        Assert.assertTrue(stringUriIntentRequired.equals("stringUriIntentRequired"));
        Assert.assertTrue(stringUriIntentOptional.equals("stringUriIntentOptional"));
        Assert.assertNull(stringUriIntentOptionalErrKey);
        Assert.assertTrue(stringUriIntentOrDefault.equals("stringUriIntentOrDefault"));
        Assert.assertTrue(stringUriIntentOrDefaultErrKey.equals("stringUriIntentOrDefaultErrKey"));
    }
}
