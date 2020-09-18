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

public class TestBothIntentUriActivity extends FragmentActivity {

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

        Uri uri = Uri.parse("tools4a://tools4a.com/panpf/tools4a/uri/intent?" + params);

        Intent starter = new Intent(context, TestBothIntentUriActivity.class);
        starter.setAction(Intent.ACTION_VIEW);
        starter.setData(uri);
        starter.putExtra("byteIntentUriOrDefault", ((byte) -1));
        starter.putExtra("shortIntentUriOrDefault", ((short) -2));
        starter.putExtra("intIntentUriOrDefault", -3);
        starter.putExtra("longIntentUriOrDefault", -4L);
        starter.putExtra("floatIntentUriOrDefault", -5f);
        starter.putExtra("doubleIntentUriOrDefault", (-6d));
        starter.putExtra("booleanIntentUriOrDefault", true);
        starter.putExtra("stringIntentUriRequired", "stringIntentRequired");
        starter.putExtra("stringIntentUriOptional", "stringIntentOptional");
        starter.putExtra("stringIntentUriOrDefault", "stringIntentOrDefault");

        //Activity Uri Intent
        starter.putExtra("byteUriIntentOrDefault", ((byte) -11));
        starter.putExtra("shortUriIntentOrDefault", ((short) -12));
        starter.putExtra("intUriIntentOrDefault", -13);
        starter.putExtra("longUriIntentOrDefault", -14L);
        starter.putExtra("floatUriIntentOrDefault", -15f);
        starter.putExtra("doubleUriIntentOrDefault", (-16d));
        starter.putExtra("booleanUriIntentOrDefault", true);
        starter.putExtra("stringUriIntentRequired", "stringUriIntentRequired");
        starter.putExtra("stringUriIntentOptional", "stringUriIntentOptional");
        starter.putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault");
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
        String stringIntentUriRequired = Argsx.readStringIntentUriArgOrThrow(activity, "stringIntentUriRequired");
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
        String stringUriIntentRequired = Argsx.readStringUriIntentArgOrThrow(activity, "stringUriIntentRequired");
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefault", "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, "stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptional");
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, "stringUriIntentOptionalErrKey");

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
