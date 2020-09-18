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
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.github.panpf.tools4a.args.Argsx;
import com.github.panpf.tools4j.collections.Collectionx;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Objects;

import static com.github.panpf.tools4j.collections.Arrayx.arrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.booleanArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.byteArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.charArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.doubleArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.floatArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.intArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.longArrayOf;
import static com.github.panpf.tools4j.collections.Arrayx.shortArrayOf;
import static com.github.panpf.tools4j.collections.Collectionx.arrayListOf;

public class TestActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        starter.putExtra("byteRequired", (byte) 2);
        starter.putExtra("byteArrayRequired", byteArrayOf((byte) 2, (byte) (-2)));
        starter.putExtra("byteArrayOrDefault", byteArrayOf((byte) 2, (byte) (-2)));
        starter.putExtra("byteArrayOptional", byteArrayOf((byte) (-2), (byte) 2));


        starter.putExtra("shortRequired", (short) 3);
        starter.putExtra("shortArrayRequired", shortArrayOf((short) 3, (short) (-3)));
        starter.putExtra("shortArrayOptional", shortArrayOf((short) (-3), (short) 3));

        starter.putExtra("intRequired", 500);
        starter.putExtra("intArrayRequired", intArrayOf(500, -500));
        starter.putExtra("intArrayOptional", intArrayOf(-500, 500));
        starter.putIntegerArrayListExtra("intArrayListRequired", arrayListOf(500, -500));
        starter.putIntegerArrayListExtra("intArrayListOptional", arrayListOf(-500, 500));

        starter.putExtra("longRequired", 1000L);
        starter.putExtra("longArrayRequired", longArrayOf(1000L, -1000L));
        starter.putExtra("longArrayOptional", longArrayOf(-1000L, 1000L));

        starter.putExtra("floatRequired", 4f);
        starter.putExtra("floatArrayRequired", floatArrayOf(4f, -4f));
        starter.putExtra("floatArrayOptional", floatArrayOf(-4f, 4f));


        starter.putExtra("doubleRequired", 6d);
        starter.putExtra("doubleArrayRequired", doubleArrayOf(6d, (-6d)));
        starter.putExtra("doubleArrayOptional", doubleArrayOf((-6d), 6d));


        starter.putExtra("booleanRequired", true);
        starter.putExtra("booleanArrayRequired", booleanArrayOf(true, false));
        starter.putExtra("booleanArrayOptional", booleanArrayOf(false, true));


        starter.putExtra("charRequired", 'a');
        starter.putExtra("charArrayRequired", charArrayOf('a', 'b'));
        starter.putExtra("charArrayOptional", charArrayOf('b', 'a'));

        starter.putExtra("stringRequired", "stringRequired");
        starter.putExtra("stringOptional", "stringOptional");
        starter.putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"));
        starter.putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"));
        starter.putStringArrayListExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"));
        starter.putStringArrayListExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"));

        starter.putExtra("charSequenceRequired", "stringRequired");
        starter.putExtra("charSequenceOptional", "stringOptional");
        starter.putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"));
        starter.putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"));


        starter.putExtra("parcelableRequired", new TestParcelable("parcelableRequired"));
        starter.putExtra("parcelableOptional", new TestParcelable("parcelableOptional"));
        starter.putExtra("parcelableArrayRequired", arrayOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
        starter.putExtra("parcelableArrayOptional", arrayOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));
        starter.putParcelableArrayListExtra("parcelableArrayListRequired", arrayListOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
        starter.putParcelableArrayListExtra("parcelableArrayListOptional", arrayListOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));


        starter.putExtra("serializableRequired", new TestSerializable("serializableRequired"));
        starter.putExtra("serializableOptional", new TestSerializable("serializableOptional"));

        starter.putExtra("byteArrayOrDefault", byteArrayOf((byte) 2, (byte) (-2)));
        starter.putExtra("shortArrayOrDefault", shortArrayOf((short) 3, (short) (-3)));
        starter.putExtra("intArrayOrDefault", intArrayOf(500, -500));
        starter.putIntegerArrayListExtra("intArrayListOrDefault", arrayListOf(600, -600));
        starter.putExtra("longArrayOrDefault", longArrayOf(1000L, -1000L));
        starter.putExtra("floatArrayOrDefault", floatArrayOf(4f, -4f));
        starter.putExtra("doubleArrayOrDefault", doubleArrayOf(6d, (-6d)));
        starter.putExtra("booleanArrayOrDefault", booleanArrayOf(true, false));
        starter.putExtra("charArrayOrDefault", charArrayOf('a', 'b'));
        starter.putExtra("charSequenceOrDefault", "charSequenceOrDefault");
        starter.putExtra("charSequenceArrayOrDefault", arrayOf("charSequence", "default"));


        starter.putCharSequenceArrayListExtra("charSequenceArrayListRequired", Collectionx.<CharSequence>arrayListOf("charSequenceArrayListRequired", "required"));
        starter.putCharSequenceArrayListExtra("charSequenceArrayListOptional", Collectionx.<CharSequence>arrayListOf("charSequenceArrayListOptional", "optional"));
        starter.putCharSequenceArrayListExtra("charSequenceArrayListOrDefault", Collectionx.<CharSequence>arrayListOf("charSequenceArrayListOrDefault", "default"));

        starter.putExtra("stringOrDefault", "stringOrDefault");
        starter.putExtra("stringArrayOrDefault", arrayOf("stringArrayOrDefault", "default"));
        starter.putExtra("stringArrayListOrDefault", arrayListOf("stringArrayListOrDefault", "default"));

        starter.putExtra("parcelableOrDefault", new TestParcelable("parcelableOrDefault"));
        starter.putExtra("parcelableArrayOrDefault", arrayOf(new TestParcelable("parcelableArrayOrDefault"), new TestParcelable("default")));
        starter.putParcelableArrayListExtra("parcelableArrayListOrDefault", arrayListOf(new TestParcelable("parcelableArrayListOrDefault"), new TestParcelable("default")));

        starter.putExtra("serializableOrDefault", new TestSerializable("serializableOrDefault"));

        Bundle bundleDefault = new Bundle();
        bundleDefault.putString("bundle", "bundleOrDefault");
        starter.putExtra("bundleOrDefault", bundleDefault);

        Bundle bundleRequired = new Bundle();
        bundleRequired.putString("bundle", "bundleRequired");
        starter.putExtra("bundleRequired", bundleRequired);

        Bundle bundleOptional = new Bundle();
        bundleOptional.putString("bundle", "bundleOptional");
        starter.putExtra("bundleOptional", bundleOptional);

        Bundle b = new Bundle();
        b.putString("extrasRequired", "extrasRequired");
        b.putString("extrasOptional", "extrasOptional");
        b.putString("extrasOrDefault", "extrasOrDefault");
        starter.putExtras(b);

        Bundle args = new Bundle();
        SparseArray<Parcelable> sparseParcelableArrayRequired = new SparseArray<>();
        sparseParcelableArrayRequired.put(-1, new TestParcelable("-1"));
        sparseParcelableArrayRequired.put(1, new TestParcelable("1"));
        args.putSparseParcelableArray("sparseParcelableArrayRequired", sparseParcelableArrayRequired);

        SparseArray<Parcelable> sparseParcelableArrayOptional = new SparseArray<>();
        sparseParcelableArrayOptional.put(-2, new TestParcelable("-2"));
        sparseParcelableArrayOptional.put(2, new TestParcelable("2"));
        args.putSparseParcelableArray("sparseParcelableArrayOptional", sparseParcelableArrayOptional);

        SparseArray<Parcelable> sparseParcelableArrayOrDefault = new SparseArray<>();
        sparseParcelableArrayOrDefault.put(-3, new TestParcelable("-3"));
        sparseParcelableArrayOrDefault.put(3, new TestParcelable("3"));
        args.putSparseParcelableArray("sparseParcelableArrayOrDefault", sparseParcelableArrayOrDefault);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            args.putBinder("binderRequired", new TestBinder("binderRequired"));
            args.putBinder("binderOptional", new TestBinder("binderOptional"));
            args.putBinder("binderOrDefault", new TestBinder("binderOrDefault"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            args.putSize("sizeRequired", new Size(1, 1));
            args.putSize("sizeOptional", new Size(2, 2));
            args.putSize("sizeOrDefault", new Size(3, 3));

            args.putSizeF("sizeFRequired", new SizeF(1f, 1f));
            args.putSizeF("sizeFOptional", new SizeF(2f, 2f));
            args.putSizeF("sizeFOrDefault", new SizeF(3f, 3f));
        }

        starter.putExtras(args);

        return starter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.github.panpf.tools4a.args.test.R.layout.at_test);
        TestSupportFragment supportFragment = TestSupportFragment.newInstance(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(com.github.panpf.tools4a.args.test.R.id.testAt_frame, supportFragment).commit();
    }

    @NonNull
    public TestSupportFragment getFragment() {
        return (TestSupportFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(com.github.panpf.tools4a.args.test.R.id.testAt_frame));
    }

    public void checkParams() {
        Activity activity = this;

        byte byteRequired = Argsx.readByteArgOr(activity, "byteRequired", (byte) 0);
        byte byteRequiredErrKey = Argsx.readByteArgOr(activity, "byteRequiredErrKey", (byte) 0);
        byte[] byteArrayRequired = Argsx.readByteArrayArgOrThrow(activity, "byteArrayRequired");
        byte[] byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptional");
        byte[] byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, "byteArrayOptionalErrKey");
        byte[] byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefault", byteArrayOf((byte) 0, (byte) 0));
        byte[] byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, "byteArrayOrDefaultErrKey", byteArrayOf((byte) 0, (byte) (-1)));

        short shortRequired = Argsx.readShortArgOr(activity, "shortRequired", (short) 0);
        short shortRequiredErrKey = Argsx.readShortArgOr(activity, "shortRequiredErrKey", (short) 0);
        short[] shortArrayRequired = Argsx.readShortArrayArgOrThrow(activity, "shortArrayRequired");
        short[] shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptional");
        short[] shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, "shortArrayOptionalErrKey");
        short[] shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefault", shortArrayOf((short) 0, (short) 0));
        short[] shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, "shortArrayOrDefaultErrKey", shortArrayOf((short) 0, (short) (-1)));


        int intRequired = Argsx.readIntArgOr(activity, "intRequired", 0);
        int intRequiredErrKey = Argsx.readIntArgOr(activity, "intRequiredErrKey", 0);
        int[] intArrayRequired = Argsx.readIntArrayArgOrThrow(activity, "intArrayRequired");
        int[] intArrayOptional = Argsx.readIntArrayArgOrNull(activity, "intArrayOptional");
        int[] intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, "intArrayOptionalErrKey");
        int[] intArrayOrDefault = Argsx.readIntArrayArgOr(activity, "intArrayOrDefault", intArrayOf(0, 0));
        int[] intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, "intArrayOrDefaultErrKey", intArrayOf(0, (-1)));
        ArrayList<Integer> intArrayListRequired = Argsx.readIntArrayListArgOrThrow(activity, "intArrayListRequired");
        ArrayList<Integer> intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefault", arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, "intArrayListOrDefaultErrKey", arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptional");
        ArrayList<Integer> intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, "intArrayListOptionalErrKey");

        long longRequired = Argsx.readLongArgOr(activity, "longRequired", 0);
        long longRequiredErrKey = Argsx.readLongArgOr(activity, "longRequiredErrKey", 0);
        long[] longArrayRequired = Argsx.readLongArrayArgOrThrow(activity, "longArrayRequired");
        long[] longArrayOptional = Argsx.readLongArrayArgOrNull(activity, "longArrayOptional");
        long[] longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, "longArrayOptionalErrKey");
        long[] longArrayOrDefault = Argsx.readLongArrayArgOr(activity, "longArrayOrDefault", longArrayOf(0, 0));
        long[] longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, "longArrayOrDefaultErrKey", longArrayOf(0, (-1)));

        float floatRequired = Argsx.readFloatArgOr(activity, "floatRequired", 0f);
        float floatRequiredErrKey = Argsx.readFloatArgOr(activity, "floatRequiredErrKey", 0f);
        float[] floatArrayRequired = Argsx.readFloatArrayArgOrThrow(activity, "floatArrayRequired");
        float[] floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, "floatArrayOptional");
        float[] floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, "DoubleArrayOptionalErrKey");
        float[] floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefault", floatArrayOf(0f, 0f));
        float[] floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, "floatArrayOrDefaultErrKey", floatArrayOf(0f, (-1f)));

        double doubleRequired = Argsx.readDoubleArgOr(activity, "doubleRequired", 0d);
        double doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, "doubleRequiredErrKey", 0d);
        double[] doubleArrayRequired = Argsx.readDoubleArrayArgOrThrow(activity, "doubleArrayRequired");
        double[] doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptional");
        double[] doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, "doubleArrayOptionalErrKey");
        double[] doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefault", doubleArrayOf(0d, 0d));
        double[] doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, "doubleArrayOrDefaultErrKey", doubleArrayOf(0d, (-1d)));

        boolean booleanRequired = Argsx.readBooleanArgOr(activity, "booleanRequired", false);
        boolean booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, "booleanRequiredErrKey", false);
        boolean[] booleanArrayRequired = Argsx.readBooleanArrayArgOrThrow(activity, "booleanArrayRequired");
        boolean[] booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptional");
        boolean[] booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, "booleanArrayOptionalErrKey");
        boolean[] booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefault", booleanArrayOf(true, false));
        boolean[] booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, "booleanArrayOrDefaultErrKey", booleanArrayOf(false, true));

        char charRequired = Argsx.readCharArgOr(activity, "charRequired", 'a');
        char charRequiredErrKey = Argsx.readCharArgOr(activity, "charRequiredErrKey", 'b');
        char[] charArrayRequired = Argsx.readCharArrayArgOrThrow(activity, "charArrayRequired");
        char[] charArrayOptional = Argsx.readCharArrayArgOrNull(activity, "charArrayOptional");
        char[] charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, "charArrayOptionalErrKey");
        char[] charArrayOrDefault = Argsx.readCharArrayArgOr(activity, "charArrayOrDefault", charArrayOf('a', 'b'));
        char[] charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, "charArrayOrDefaultErrKey", charArrayOf('b', 'a'));

        String stringRequired = Argsx.readStringArgOr(activity, "stringRequired", "stringRequired");
        String stringRequiredErrKey = Argsx.readStringArgOr(activity, "stringRequiredErrKey", "stringRequiredErrKey");
        String[] stringArrayRequired = Argsx.readStringArrayArgOrThrow(activity, "stringArrayRequired");
        String[] stringArrayOptional = Objects.requireNonNull(Argsx.readStringArrayArgOrNull(activity, "stringArrayOptional"));
        String[] stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptionalErrKey");
        String[] stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefault", arrayOf("array", "dft"));
        String[] stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, "stringArrayOrDefaultErrKey", arrayOf("error", "erk"));
        ArrayList<String> stringArrayListRequired = Argsx.readStringArrayListArgOrThrow(activity, "stringArrayListRequired");
        ArrayList<String> stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefault", arrayListOf("list", "default"));
        ArrayList<String> stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, "stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"));
        ArrayList<String> stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptional");
        ArrayList<String> stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, "stringArrayListOptionalErrKey");

        CharSequence charSequenceRequired = Argsx.readCharSequenceArgOr(activity, "charSequenceRequired", "charSequenceRequired");
        CharSequence charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, "charSequenceRequiredErrKey", "charSequenceRequiredErrKey");
        CharSequence[] charSequenceArrayRequired = Argsx.readCharSequenceArrayArgOrThrow(activity, "charSequenceArrayRequired");
        CharSequence[] charSequenceArrayOptional = Objects.requireNonNull(Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptional"));
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptionalErrKey");
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefault", arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(activity, "charSequenceArrayListRequired");
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefault", Collectionx.arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefaultErrKey", Collectionx.arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Objects.requireNonNull(Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptional"));
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptionalErrKey");


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(activity, "parcelableRequired", new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, "parcelableRequiredErrKey", new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(activity, "parcelableArrayRequired");
        Parcelable[] parcelableArrayOptional = Objects.requireNonNull(Argsx.readParcelableArrayArgOrNull(activity, "parcelableArrayOptional"));
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(activity, "parcelableArrayOptionalErrKey");
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefault", arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefaultErrKey", arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(activity, "parcelableArrayListRequired");
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefault", Collectionx.arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefaultErrKey", Collectionx.arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Objects.requireNonNull(Argsx.readParcelableArrayListArgOrNull(activity, "parcelableArrayListOptional"));
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(activity, "parcelableArrayListOptionalErrKey");

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(activity, "serializableRequired");
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(activity, "serializableOptional");
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(activity, "serializableOptionalErrKey");
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(activity, "serializableOrDefault", new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, "serializableOrDefaultErrKey", new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(activity, "bundleRequired");
        Bundle bundleOptional = Objects.requireNonNull(Argsx.readBundleArgOrNull(activity, "bundleOptional"));
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, "bundleOptionalErrKey");
        Bundle bundleOrDefault = Argsx.readBundleArgOr(activity, "bundleOrDefault", new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, "bundleOrDefaultErrKey", defaultBundle);

        Bundle extrasRequired = Argsx.readExtrasArgOrThrow(activity);
        Bundle extrasOptional = Objects.requireNonNull(Argsx.readExtrasArgOrNull(activity));
        Bundle extrasDefault = Argsx.readExtrasArgOr(activity, new Bundle());

        //test start
        Assert.assertEquals("extrasRequired", extrasRequired.getString("extrasRequired"));
        Assert.assertEquals("extrasOptional", extrasOptional.getString("extrasOptional"));
        Assert.assertEquals("extrasOrDefault", extrasDefault.getString("extrasOrDefault"));

        Assert.assertEquals("bundleRequired", bundleRequired.getString("bundle"));
        Assert.assertEquals("bundleOptional", bundleOptional.getString("bundle"));
        Assert.assertNull(bundleOptionalErrKey);
        Assert.assertEquals("bundleOrDefault", bundleOrDefault.getString("bundle"));
        Assert.assertEquals("bundleErrKey", bundleOrDefaultErrKey.getString("bundle"));


        Assert.assertEquals(serializableRequired, new TestSerializable("serializableRequired"));
        Assert.assertEquals(serializableOptional, new TestSerializable("serializableOptional"));
        Assert.assertNull(serializableOptionalErrKey);
        Assert.assertEquals(serializableOrDefault, new TestSerializable("serializableOrDefault"));
        Assert.assertEquals(serializableOrDefaultErrKey, new TestSerializable("errKey"));

        Assert.assertEquals(parcelableRequired, new TestParcelable("parcelableRequired"));
        Assert.assertEquals(parcelableRequiredErrKey, new TestParcelable("parcelableRequiredErrKey"));
        Assert.assertTrue(parcelableArrayRequired[0].equals(new TestParcelable("parcelableRequired")) && parcelableArrayRequired[1].equals(new TestParcelable("parcelableOptional")));
        Assert.assertTrue(parcelableArrayOptional[0].equals(new TestParcelable("parcelableOptional")) && parcelableArrayOptional[1].equals(new TestParcelable("parcelableRequired")));
        Assert.assertNull(parcelableArrayOptionalErrKey);
        Assert.assertTrue(parcelableArrayOrDefault[0].equals(new TestParcelable("parcelableArrayOrDefault")) && parcelableArrayOrDefault[1].equals(new TestParcelable("default")));
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0].equals(new TestParcelable("error")) && parcelableArrayOrDefaultErrKey[1].equals(new TestParcelable("erk")));
        Assert.assertTrue(parcelableArrayListRequired.get(0).equals(new TestParcelable("parcelableRequired")) && parcelableArrayListRequired.get(1).equals(new TestParcelable("parcelableOptional")));
        Assert.assertTrue(parcelableArrayListOptional.get(0).equals(new TestParcelable("parcelableOptional")) && parcelableArrayListOptional.get(1).equals(new TestParcelable("parcelableRequired")));
        Assert.assertNull(parcelableArrayListOptionalErrKey);
        Assert.assertTrue(parcelableArrayListOrDefault.get(0).equals(new TestParcelable("parcelableArrayListOrDefault")) && parcelableArrayListOrDefault.get(1).equals(new TestParcelable("default")));
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey.get(0).equals(new TestParcelable("parcelableArrayListOrDefaultErrKey")) && parcelableArrayListOrDefaultErrKey.get(1).equals(new TestParcelable("errKey")));

        Assert.assertEquals("stringRequired", charSequenceRequired);
        Assert.assertEquals("charSequenceRequiredErrKey", charSequenceRequiredErrKey);
        Assert.assertTrue(charSequenceArrayRequired[0].equals("stringRequired") && charSequenceArrayRequired[1].equals("stringOptional"));
        Assert.assertTrue(charSequenceArrayOptional[0].equals("stringOptional") && charSequenceArrayOptional[1].equals("stringRequired"));
        Assert.assertNull(charSequenceArrayOptionalErrKey);
        Assert.assertTrue(charSequenceArrayOrDefault[0].equals("charSequence") && charSequenceArrayOrDefault[1].equals("default"));
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0].equals("error") && charSequenceArrayOrDefaultErrKey[1].equals("erk"));
        Assert.assertTrue(charSequenceArrayListRequired.get(0).equals("charSequenceArrayListRequired") && charSequenceArrayListRequired.get(1).equals("required"));
        Assert.assertTrue(charSequenceArrayListOrDefault.get(0).equals("charSequenceArrayListOrDefault") && charSequenceArrayListOrDefault.get(1).equals("default"));
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey.get(0).equals("charSequenceArrayListOrDefaultErrKey") && charSequenceArrayListOrDefaultErrKey.get(1).equals("errKey"));
        Assert.assertTrue(charSequenceArrayListOptional.get(0).equals("charSequenceArrayListOptional") && charSequenceArrayListOptional.get(1).equals("optional"));
        Assert.assertNull(charSequenceArrayListOptionalErrKey);

        Assert.assertEquals("stringRequired", stringRequired);
        Assert.assertEquals("stringRequiredErrKey", stringRequiredErrKey);
        Assert.assertTrue(stringArrayRequired[0].equals("stringRequired") && stringArrayRequired[1].equals("stringOptional"));
        Assert.assertTrue(stringArrayOptional[0].equals("stringOptional") && stringArrayOptional[1].equals("stringRequired"));
        Assert.assertNull(stringArrayOptionalErrKey);
        Assert.assertTrue(stringArrayOrDefault[0].equals("stringArrayOrDefault") && stringArrayOrDefault[1].equals("default"));
        Assert.assertTrue(stringArrayOrDefaultErrKey[0].equals("error") && stringArrayOrDefaultErrKey[1].equals("erk"));
        Assert.assertTrue(stringArrayListRequired.get(0).equals("stringRequired") && stringArrayListRequired.get(1).equals("stringOptional"));
        Assert.assertTrue(stringArrayListOrDefault.get(0).equals("stringArrayListOrDefault") && stringArrayListOrDefault.get(1).equals("default"));
        Assert.assertTrue(stringArrayListOrDefaultErrKey.get(0).equals("stringArrayListOrDefaultErrKey") && stringArrayListOrDefaultErrKey.get(1).equals("errKey"));
        Assert.assertTrue(stringArrayListOptional.get(0).equals("stringOptional") && stringArrayListOptional.get(1).equals("stringRequired"));
        Assert.assertNull(stringArrayListOptionalErrKey);

        Assert.assertTrue(byteRequired == (byte) 2);
        Assert.assertTrue(byteRequiredErrKey == 0);
        Assert.assertTrue(byteArrayRequired[0] == (byte) 2 && byteArrayRequired[1] == (byte) (-2));
        Assert.assertTrue(byteArrayOptional[0] == (byte) (-2) && byteArrayOptional[1] == (byte) 2);
        Assert.assertNull(byteArrayOptionalErrKey);
        Assert.assertTrue(byteArrayOrDefault[0] == (byte) 2 && byteArrayOrDefault[1] == (byte) (-2));
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == (byte) 0 && byteArrayOrDefaultErrKey[1] == (byte) (-1));

        Assert.assertTrue(shortRequired == (short) 3);
        Assert.assertTrue(shortRequiredErrKey == 0);
        Assert.assertTrue(shortArrayRequired[0] == (short) 3 && shortArrayRequired[1] == (short) (-3));
        Assert.assertTrue(shortArrayOptional[0] == (short) (-3) && shortArrayOptional[1] == (short) 3);
        Assert.assertNull(shortArrayOptionalErrKey);
        Assert.assertTrue(shortArrayOrDefault[0] == (short) 3 && shortArrayOrDefault[1] == (short) (-3));
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == (short) 0 && shortArrayOrDefaultErrKey[1] == (short) (-1));

        Assert.assertTrue(intRequired == 500);
        Assert.assertTrue(intRequiredErrKey == 0);
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == (-500));
        Assert.assertTrue(intArrayOptional[0] == (-500) && intArrayOptional[1] == 500);
        Assert.assertNull(intArrayOptionalErrKey);
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == (-500));
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == (-1));
        Assert.assertTrue(intArrayListRequired.get(0) == 500 && intArrayListRequired.get(1) == (-500));
        Assert.assertTrue(intArrayListOrDefault.get(0) == 600 && intArrayListOrDefault.get(1) == (-600));
        Assert.assertTrue(intArrayListOrDefaultErrKey.get(0) == 0 && intArrayListOrDefaultErrKey.get(1) == 0);
        Assert.assertTrue(intArrayListOptional.get(0) == -500 && intArrayListOptional.get(1) == 500);
        Assert.assertNull(intArrayListOptionalErrKey);

        Assert.assertTrue(longRequired == 1000L);
        Assert.assertTrue(longRequiredErrKey == 0);
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == (-1000L));
        Assert.assertTrue(longArrayOptional[0] == (-1000l) && longArrayOptional[1] == 1000L);
        Assert.assertNull(longArrayOptionalErrKey);
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == (-1000L));
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0 && longArrayOrDefaultErrKey[1] == (-1));

        Assert.assertTrue(floatRequired == 4f);
        Assert.assertTrue(floatRequiredErrKey == 0f);
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == (-4f));
        Assert.assertTrue(floatArrayOptional[0] == (-4f) && floatArrayOptional[1] == 4f);
        Assert.assertNull(floatArrayOptionalErrKey);
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == (-4f));
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0 && floatArrayOrDefaultErrKey[1] == (-1f));

        Assert.assertTrue(doubleRequired == 6d);
        Assert.assertTrue(doubleRequiredErrKey == 0d);
        Assert.assertTrue(doubleArrayRequired[0] == 6d && doubleArrayRequired[1] == (-6d));
        Assert.assertTrue(doubleArrayOptional[0] == (-6d) && doubleArrayOptional[1] == 6d);
        Assert.assertNull(doubleArrayOptionalErrKey);
        Assert.assertTrue(doubleArrayOrDefault[0] == 6d && doubleArrayOrDefault[1] == (-6d));
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0 && doubleArrayOrDefaultErrKey[1] == (-1d));

        Assert.assertTrue(booleanRequired);
        Assert.assertTrue(!booleanRequiredErrKey);
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1]);
        Assert.assertTrue(!booleanArrayOptional[0] && booleanArrayOptional[1]);
        Assert.assertNull(booleanArrayOptionalErrKey);
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1]);
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1]);

        Assert.assertTrue(charRequired == 'a');
        Assert.assertTrue(charRequiredErrKey == 'b');
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b');
        Assert.assertTrue(charArrayOptional[0] == 'b' && charArrayOptional[1] == 'a');
        Assert.assertNull(charArrayOptionalErrKey);
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b');
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a');
    }
}
