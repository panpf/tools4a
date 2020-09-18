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

public class ResTestUriActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        String params = "byteUriRequired=" + ((byte) 1) +
                "&byteUriOptional=" + ((byte) -1) +
                "&byteUriOrDefault=" + ((byte) 2) +
                "&shortUriRequired=" + ((short) 3) +
                "&shortUriOptional=" + ((short) -3) +
                "&shortUriOrDefault=" + ((short) 4) +
                "&intUriRequired=" + 5 +
                "&intUriOptional=" + -5 +
                "&intUriOrDefault=" + 6 +
                "&longUriRequired=" + 7L +
                "&longUriOptional=" + (-7L) +
                "&longUriOrDefault=" + 8L +
                "&floatUriRequired=" + 9f +
                "&floatUriOptional=" + -9f +
                "&floatUriOrDefault=" + 10f +
                "&doubleUriRequired=" + 11d +
                "&doubleUriOptional=" + (-11d) +
                "&doubleUriOrDefault=" + 12d +
                "&booleanUriRequired=" + true +
                "&booleanUriOptional=" + true +
                "&booleanUriOrDefault=" + false +
                "&stringUriRequired=" + "stringUriRequired" +
                "&stringUriOptional=" + "stringUriOptional" +
                "&stringUriOrDefault=" + "stringUriOrDefault";
        Intent intent = new Intent(context, ResTestUriActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tools4a://tools4a.com/panpf/tools4a/res?" + params));
        return intent;
    }

    public void checkParams() {
        Activity activity = this;

        byte byteUriRequired = Argsx.readByteUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.byte_uri_required);
        Byte byteUriOptional = Argsx.readByteUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.byte_uri_optional);
        Byte byteUriOptionalErrKey = Argsx.readByteUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        byte byteUriOrDefault = Argsx.readByteUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.byte_uri_or_default, ((byte) 0));
        byte byteUriOrDefaultErrKey = Argsx.readByteUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, ((byte) 0));

        short shortUriRequired = Argsx.readShortUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.short_uri_required);
        Short shortUriOptional = Argsx.readShortUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.short_uri_optional);
        Short shortUriOptionalErrKey = Argsx.readShortUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        short shortUriOrDefault = Argsx.readShortUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.short_uri_or_default, ((short) 0));
        short shortUriOrDefaultErrKey = Argsx.readShortUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, ((short) 0));

        int intUriRequired = Argsx.readIntUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.int_uri_required);
        Integer intUriOptional = Argsx.readIntUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.int_uri_optional);
        Integer intUriOptionalErrKey = Argsx.readIntUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        int intUriOrDefault = Argsx.readIntUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_uri_or_default, 0);
        int intUriOrDefaultErrKey = Argsx.readIntUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0);

        long longUriRequired = Argsx.readLongUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.long_uri_required);
        Long longUriOptional = Argsx.readLongUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.long_uri_optional);
        Long longUriOptionalErrKey = Argsx.readLongUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        long longUriOrDefault = Argsx.readLongUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.long_uri_or_default, 0L);
        long longUriOrDefaultErrKey = Argsx.readLongUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0L);

        float floatUriRequired = Argsx.readFloatUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.float_uri_required);
        Float floatUriOptional = Argsx.readFloatUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.float_uri_optional);
        Float floatUriOptionalErrKey = Argsx.readFloatUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        float floatUriOrDefault = Argsx.readFloatUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.float_uri_or_default, 0f);
        float floatUriOrDefaultErrKey = Argsx.readFloatUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, -1f);

        double doubleUriRequired = Argsx.readDoubleUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.double_uri_required);
        Double doubleUriOptional = Argsx.readDoubleUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.double_uri_optional);
        Double doubleUriOptionalErrKey = Argsx.readDoubleUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        double doubleUriOrDefault = Argsx.readDoubleUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.double_uri_or_default, 1d);
        double doubleUriOrDefaultErrKey = Argsx.readDoubleUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, -1d);

        boolean booleanUriRequired = Argsx.readBooleanUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.boolean_uri_required);
        Boolean booleanUriOptional = Argsx.readBooleanUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.boolean_uri_optional);
        Boolean booleanUriOptionalErrKey = Argsx.readBooleanUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        boolean booleanUriOrDefault = Argsx.readBooleanUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.boolean_uri_or_default, true);
        boolean booleanUriOrDefaultErrKey = Argsx.readBooleanUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, false);

        String stringUriRequired = Argsx.readStringUriArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_required);
        String stringUriOptional = Argsx.readStringUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_optional);
        String stringUriOptionalErrKey = Argsx.readStringUriArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        String stringUriOrDefault = Argsx.readStringUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_uri_or_default, "");
        String stringUriOrDefaultErrKey = Argsx.readStringUriArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "stringUriOrDefaultErrKey");

        Assert.assertNull(byteUriOptionalErrKey);
        Assert.assertNull(shortUriOptionalErrKey);
        Assert.assertNull(intUriOptionalErrKey);
        Assert.assertNull(longUriOptionalErrKey);
        Assert.assertNull(floatUriOptionalErrKey);
        Assert.assertNull(doubleUriOptionalErrKey);
        Assert.assertNull(booleanUriOptionalErrKey);
        Assert.assertNull(stringUriOptionalErrKey);

        Assert.assertTrue(byteUriRequired == ((byte) 1));
        Assert.assertTrue(byteUriOptional == ((byte) -1));
        Assert.assertTrue(byteUriOrDefault == ((byte) 2));
        Assert.assertTrue(byteUriOrDefaultErrKey == ((byte) 0));

        Assert.assertTrue(shortUriRequired == ((short) 3));
        Assert.assertTrue(shortUriOptional == ((short) -3));
        Assert.assertTrue(shortUriOrDefault == ((short) 4));
        Assert.assertTrue(shortUriOrDefaultErrKey == ((short) 0));

        Assert.assertTrue(intUriRequired == 5);
        Assert.assertTrue(intUriOptional == -5);
        Assert.assertTrue(intUriOrDefault == 6);
        Assert.assertTrue(intUriOrDefaultErrKey == 0);

        Assert.assertTrue(longUriRequired == 7L);
        Assert.assertTrue(longUriOptional == -7L);
        Assert.assertTrue(longUriOrDefault == 8L);
        Assert.assertTrue(longUriOrDefaultErrKey == 0L);

        Assert.assertTrue(floatUriRequired == 9f);
        Assert.assertTrue(floatUriOptional == -9f);
        Assert.assertTrue(floatUriOrDefault == 10f);
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f);

        Assert.assertTrue(doubleUriRequired == 11d);
        Assert.assertTrue(doubleUriOptional == -11d);
        Assert.assertTrue(doubleUriOrDefault == 12d);
        Assert.assertTrue(doubleUriOrDefaultErrKey == (-1d));

        Assert.assertTrue(booleanUriRequired);
        Assert.assertTrue(booleanUriOptional);
        Assert.assertTrue(!booleanUriOrDefault);
        Assert.assertTrue(!booleanUriOrDefaultErrKey);

        Assert.assertTrue(stringUriRequired.equals("stringUriRequired"));
        Assert.assertTrue(stringUriOptional.equals("stringUriOptional"));
        Assert.assertTrue(stringUriOrDefault.equals("stringUriOrDefault"));
        Assert.assertTrue(stringUriOrDefaultErrKey.equals("stringUriOrDefaultErrKey"));
    }
}
