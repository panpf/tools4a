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

//activity uri
public class TestUriActivity extends FragmentActivity {

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
        Intent starter = new Intent(context, TestUriActivity.class);
        starter.setAction(Intent.ACTION_VIEW);
        starter.setData(Uri.parse("tools4a://tools4a.com/panpf/tools4a?" + params));
        return starter;
    }

    public void checkParams() {
        Activity activity = this;

        byte byteUriRequired = Argsx.readByteUriArgOrThrow(activity, "byteUriRequired");
        Byte byteUriOptional = Argsx.readByteUriArgOrNull(activity, "byteUriOptional");
        Byte byteUriOptionalErrKey = Argsx.readByteUriArgOrNull(activity, "byteUriOptionalErrKey");
        byte byteUriOrDefault = Argsx.readByteUriArgOr(activity, "byteUriOrDefault", ((byte) 0));
        byte byteUriOrDefaultErrKey = Argsx.readByteUriArgOr(activity, "byteUriOrDefaultErrKey", ((byte) 0));

        short shortUriRequired = Argsx.readShortUriArgOrThrow(activity, "shortUriRequired");
        Short shortUriOptional = Argsx.readShortUriArgOrNull(activity, "shortUriOptional");
        Short shortUriOptionalErrKey = Argsx.readShortUriArgOrNull(activity, "shortUriOptionalErrKey");
        short shortUriOrDefault = Argsx.readShortUriArgOr(activity, "shortUriOrDefault", ((short) 0));
        short shortUriOrDefaultErrKey = Argsx.readShortUriArgOr(activity, "shortUriOrDefaultErrKey", ((short) 0));

        int intUriRequired = Argsx.readIntUriArgOrThrow(activity, "intUriRequired");
        Integer intUriOptional = Argsx.readIntUriArgOrNull(activity, "intUriOptional");
        Integer intUriOptionalErrKey = Argsx.readIntUriArgOrNull(activity, "intUriOptionalErrKey");
        int intUriOrDefault = Argsx.readIntUriArgOr(activity, "intUriOrDefault", 0);
        int intUriOrDefaultErrKey = Argsx.readIntUriArgOr(activity, "intUriOrDefaultErrKey", 0);

        long longUriRequired = Argsx.readLongUriArgOrThrow(activity, "longUriRequired");
        Long longUriOptional = Argsx.readLongUriArgOrNull(activity, "longUriOptional");
        Long longUriOptionalErrKey = Argsx.readLongUriArgOrNull(activity, "longUriOptionalErrKey");
        long longUriOrDefault = Argsx.readLongUriArgOr(activity, "longUriOrDefault", 0L);
        long longUriOrDefaultErrKey = Argsx.readLongUriArgOr(activity, "longUriOrDefaultErrKey", 0L);

        float floatUriRequired = Argsx.readFloatUriArgOrThrow(activity, "floatUriRequired");
        Float floatUriOptional = Argsx.readFloatUriArgOrNull(activity, "floatUriOptional");
        Float floatUriOptionalErrKey = Argsx.readFloatUriArgOrNull(activity, "floatUriOptionalErrKey");
        float floatUriOrDefault = Argsx.readFloatUriArgOr(activity, "floatUriOrDefault", 0f);
        float floatUriOrDefaultErrKey = Argsx.readFloatUriArgOr(activity, "floatUriOrDefaultErrKey", -1f);

        double doubleUriRequired = Argsx.readDoubleUriArgOrThrow(activity, "doubleUriRequired");
        Double doubleUriOptional = Argsx.readDoubleUriArgOrNull(activity, "doubleUriOptional");
        Double doubleUriOptionalErrKey = Argsx.readDoubleUriArgOrNull(activity, "doubleUriOptionalErrKey");
        double doubleUriOrDefault = Argsx.readDoubleUriArgOr(activity, "doubleUriOrDefault", 1d);
        double doubleUriOrDefaultErrKey = Argsx.readDoubleUriArgOr(activity, "doubleUriOrDefaultErrKey", -1d);

        boolean booleanUriRequired = Argsx.readBooleanUriArgOrThrow(activity, "booleanUriRequired");
        Boolean booleanUriOptional = Argsx.readBooleanUriArgOrNull(activity, "booleanUriOptional");
        Boolean booleanUriOptionalErrKey = Argsx.readBooleanUriArgOrNull(activity, "booleanUriOptionalErrKey");
        boolean booleanUriOrDefault = Argsx.readBooleanUriArgOr(activity, "booleanUriOrDefault", true);
        boolean booleanUriOrDefaultErrKey = Argsx.readBooleanUriArgOr(activity, "booleanUriOrDefaultErrKey", false);

        String stringUriRequired = Argsx.readStringUriArgOrThrow(activity, "stringUriRequired");
        String stringUriOptional = Argsx.readStringUriArgOrNull(activity, "stringUriOptional");
        String stringUriOptionalErrKey = Argsx.readStringUriArgOrNull(activity, "stringUriOptionalErrKey");
        String stringUriOrDefault = Argsx.readStringUriArgOr(activity, "stringUriOrDefault", "");
        String stringUriOrDefaultErrKey = Argsx.readStringUriArgOr(activity, "stringUriOrDefaultErrKey", "stringUriOrDefaultErrKey");

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
