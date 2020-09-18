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

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.panpf.tools4a.args.Argsx;
import com.github.panpf.tools4j.collections.Collectionx;

import org.junit.Assert;

import java.util.ArrayList;

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

public class TestSupportFragment extends Fragment {

    @NonNull
    public static TestSupportFragment newInstance(@NonNull Bundle args) {
        TestSupportFragment fragment = new TestSupportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void checkParams() {
        Fragment fragment = this;

        byte byteRequired = Argsx.readByteArgOr(fragment, "byteRequired", (byte) 0);
        byte byteRequiredErrKey = Argsx.readByteArgOr(fragment, "byteRequiredErrKey", (byte) 0);
        byte[] byteArrayRequired = Argsx.readByteArrayArgOrThrow(fragment, "byteArrayRequired");
        byte[] byteArrayOptional = Argsx.readByteArrayArgOrNull(fragment, "byteArrayOptional");
        byte[] byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(fragment, "byteArrayOptionalErrKey");
        byte[] byteArrayOrDefault = Argsx.readByteArrayArgOr(fragment, "byteArrayOrDefault", byteArrayOf((byte) 0, (byte) 0));
        byte[] byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(fragment, "byteArrayOrDefaultErrKey", byteArrayOf((byte) 0, (byte) (-1)));

        short shortRequired = Argsx.readShortArgOr(fragment, "shortRequired", (short) 0);
        short shortRequiredErrKey = Argsx.readShortArgOr(fragment, "shortRequiredErrKey", (short) 0);
        short[] shortArrayRequired = Argsx.readShortArrayArgOrThrow(fragment, "shortArrayRequired");
        short[] shortArrayOptional = Argsx.readShortArrayArgOrNull(fragment, "shortArrayOptional");
        short[] shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(fragment, "shortArrayOptionalErrKey");
        short[] shortArrayOrDefault = Argsx.readShortArrayArgOr(fragment, "shortArrayOrDefault", shortArrayOf((short) 0, (short) 0));
        short[] shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(fragment, "shortArrayOrDefaultErrKey", shortArrayOf((short) 0, (short) (-1)));


        int intRequired = Argsx.readIntArgOr(fragment, "intRequired", 0);
        int intRequiredErrKey = Argsx.readIntArgOr(fragment, "intRequiredErrKey", 0);
        int[] intArrayRequired = Argsx.readIntArrayArgOrThrow(fragment, "intArrayRequired");
        int[] intArrayOptional = Argsx.readIntArrayArgOrNull(fragment, "intArrayOptional");
        int[] intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(fragment, "intArrayOptionalErrKey");
        int[] intArrayOrDefault = Argsx.readIntArrayArgOr(fragment, "intArrayOrDefault", intArrayOf(0, 0));
        int[] intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(fragment, "intArrayOrDefaultErrKey", intArrayOf(0, (-1)));
        ArrayList<Integer> intArrayListRequired = Argsx.readIntArrayListArgOrThrow(fragment, "intArrayListRequired");
        ArrayList<Integer> intArrayListOrDefault = Argsx.readIntArrayListArgOr(fragment, "intArrayListOrDefault", arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(fragment, "intArrayListOrDefaultErrKey", arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOptional = Argsx.readIntArrayListArgOrNull(fragment, "intArrayListOptional");
        ArrayList<Integer> intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(fragment, "intArrayListOptionalErrKey");

        long longRequired = Argsx.readLongArgOr(fragment, "longRequired", 0);
        long longRequiredErrKey = Argsx.readLongArgOr(fragment, "longRequiredErrKey", 0);
        long[] longArrayRequired = Argsx.readLongArrayArgOrThrow(fragment, "longArrayRequired");
        long[] longArrayOptional = Argsx.readLongArrayArgOrNull(fragment, "longArrayOptional");
        long[] longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(fragment, "longArrayOptionalErrKey");
        long[] longArrayOrDefault = Argsx.readLongArrayArgOr(fragment, "longArrayOrDefault", longArrayOf(0, 0));
        long[] longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(fragment, "longArrayOrDefaultErrKey", longArrayOf(0, (-1)));

        float floatRequired = Argsx.readFloatArgOr(fragment, "floatRequired", 0f);
        float floatRequiredErrKey = Argsx.readFloatArgOr(fragment, "floatRequiredErrKey", 0f);
        float[] floatArrayRequired = Argsx.readFloatArrayArgOrThrow(fragment, "floatArrayRequired");
        float[] floatArrayOptional = Argsx.readFloatArrayArgOrNull(fragment, "floatArrayOptional");
        float[] floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(fragment, "DoubleArrayOptionalErrKey");
        float[] floatArrayOrDefault = Argsx.readFloatArrayArgOr(fragment, "floatArrayOrDefault", floatArrayOf(0f, 0f));
        float[] floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(fragment, "floatArrayOrDefaultErrKey", floatArrayOf(0f, (-1f)));

        double doubleRequired = Argsx.readDoubleArgOr(fragment, "doubleRequired", 0d);
        double doubleRequiredErrKey = Argsx.readDoubleArgOr(fragment, "doubleRequiredErrKey", 0d);
        double[] doubleArrayRequired = Argsx.readDoubleArrayArgOrThrow(fragment, "doubleArrayRequired");
        double[] doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(fragment, "doubleArrayOptional");
        double[] doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(fragment, "doubleArrayOptionalErrKey");
        double[] doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(fragment, "doubleArrayOrDefault", doubleArrayOf(0d, 0d));
        double[] doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(fragment, "doubleArrayOrDefaultErrKey", doubleArrayOf(0d, (-1d)));

        boolean booleanRequired = Argsx.readBooleanArgOr(fragment, "booleanRequired", false);
        boolean booleanRequiredErrKey = Argsx.readBooleanArgOr(fragment, "booleanRequiredErrKey", false);
        boolean[] booleanArrayRequired = Argsx.readBooleanArrayArgOrThrow(fragment, "booleanArrayRequired");
        boolean[] booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(fragment, "booleanArrayOptional");
        boolean[] booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(fragment, "booleanArrayOptionalErrKey");
        boolean[] booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(fragment, "booleanArrayOrDefault", booleanArrayOf(true, false));
        boolean[] booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(fragment, "booleanArrayOrDefaultErrKey", booleanArrayOf(false, true));

        char charRequired = Argsx.readCharArgOr(fragment, "charRequired", 'a');
        char charRequiredErrKey = Argsx.readCharArgOr(fragment, "charRequiredErrKey", 'b');
        char[] charArrayRequired = Argsx.readCharArrayArgOrThrow(fragment, "charArrayRequired");
        char[] charArrayOptional = Argsx.readCharArrayArgOrNull(fragment, "charArrayOptional");
        char[] charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(fragment, "charArrayOptionalErrKey");
        char[] charArrayOrDefault = Argsx.readCharArrayArgOr(fragment, "charArrayOrDefault", charArrayOf('a', 'b'));
        char[] charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(fragment, "charArrayOrDefaultErrKey", charArrayOf('b', 'a'));

        String stringRequired = Argsx.readStringArgOr(fragment, "stringRequired", "stringRequired");
        String stringRequiredErrKey = Argsx.readStringArgOr(fragment, "stringRequiredErrKey", "stringRequiredErrKey");
        String[] stringArrayRequired = Argsx.readStringArrayArgOrThrow(fragment, "stringArrayRequired");
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(fragment, "stringArrayOptional");
        String[] stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(fragment, "stringArrayOptionalErrKey");
        String[] stringArrayOrDefault = Argsx.readStringArrayArgOr(fragment, "stringArrayOrDefault", arrayOf("array", "dft"));
        String[] stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(fragment, "stringArrayOrDefaultErrKey", arrayOf("error", "erk"));
        ArrayList<String> stringArrayListRequired = Argsx.readStringArrayListArgOrThrow(fragment, "stringArrayListRequired");
        ArrayList<String> stringArrayListOrDefault = Argsx.readStringArrayListArgOr(fragment, "stringArrayListOrDefault", arrayListOf("list", "default"));
        ArrayList<String> stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(fragment, "stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"));
        ArrayList<String> stringArrayListOptional = Argsx.readStringArrayListArgOrNull(fragment, "stringArrayListOptional");
        ArrayList<String> stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(fragment, "stringArrayListOptionalErrKey");

        CharSequence charSequenceRequired = Argsx.readCharSequenceArgOr(fragment, "charSequenceRequired", "charSequenceRequired");
        CharSequence charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(fragment, "charSequenceRequiredErrKey", "charSequenceRequiredErrKey");
        CharSequence[] charSequenceArrayRequired = Argsx.readCharSequenceArrayArgOrThrow(fragment, "charSequenceArrayRequired");
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(fragment, "charSequenceArrayOptional");
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(fragment, "charSequenceArrayOptionalErrKey");
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(fragment, "charSequenceArrayOrDefault", arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(fragment, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(fragment, "charSequenceArrayListRequired");
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(fragment, "charSequenceArrayListOrDefault", Collectionx.arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(fragment, "charSequenceArrayListOrDefaultErrKey", Collectionx.arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(fragment, "charSequenceArrayListOptional");
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(fragment, "charSequenceArrayListOptionalErrKey");


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(fragment, "parcelableRequired", new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(fragment, "parcelableRequiredErrKey", new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(fragment, "parcelableArrayRequired");
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(fragment, "parcelableArrayOptional");
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(fragment, "parcelableArrayOptionalErrKey");
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(fragment, "parcelableArrayOrDefault", arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(fragment, "parcelableArrayOrDefaultErrKey", arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(fragment, "parcelableArrayListRequired");
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(fragment, "parcelableArrayListOrDefault", Collectionx.<Parcelable>arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(fragment, "parcelableArrayListOrDefaultErrKey", Collectionx.<Parcelable>arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(fragment, "parcelableArrayListOptional");
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(fragment, "parcelableArrayListOptionalErrKey");

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(fragment, "serializableRequired");
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(fragment, "serializableOptional");
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(fragment, "serializableOptionalErrKey");
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(fragment, "serializableOrDefault", new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(fragment, "serializableOrDefaultErrKey", new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(fragment, "bundleRequired");
        Bundle bundleOptional = Argsx.readBundleArgOrNull(fragment, "bundleOptional");
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(fragment, "bundleOptionalErrKey");
        Bundle bundleOrDefault = Argsx.readBundleArgOr(fragment, "bundleOrDefault", new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(fragment, "bundleOrDefaultErrKey", defaultBundle);


        SparseArray<Parcelable> sparseArrayDefault = new SparseArray<>();
        sparseArrayDefault.put(0, new TestParcelable("0"));
        SparseArray<Parcelable> sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArgOrThrow(fragment, "sparseParcelableArrayRequired");
        SparseArray<Parcelable> sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull(fragment, "sparseParcelableArrayOptional");
        SparseArray<Parcelable> sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull(fragment, "sparseParcelableArrayOptionalErrKey");
        SparseArray<Parcelable> sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(fragment, "sparseParcelableArrayOrDefault", sparseArrayDefault);
        SparseArray<Parcelable> sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(fragment, "sparseParcelableArrayOrDefaultErrKey", sparseArrayDefault);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            IBinder binderRequired = Argsx.readBinderArgOrThrow(fragment, "binderRequired");
            IBinder binderOptional = Argsx.readBinderArgOrNull(fragment, "binderOptional");
            IBinder binderOptionalErrKey = Argsx.readBinderArgOrNull(fragment, "binderOptionalErrKey");
            IBinder binderOrDefault = Argsx.readBinderArgOr(fragment, "binderOrDefault", new TestBinder(""));
            IBinder binderOrDefaultErrKey = Argsx.readBinderArgOr(fragment, "binderOrDefaultErrKey", new TestBinder("binderOrDefaultErrKey"));

            Assert.assertEquals(binderRequired, new TestBinder("binderRequired"));
            Assert.assertEquals(binderOptional, new TestBinder("binderOptional"));
            Assert.assertNull(binderOptionalErrKey);
            Assert.assertEquals(binderOrDefault, new TestBinder("binderOrDefault"));
            Assert.assertEquals(binderOrDefaultErrKey, new TestBinder("binderOrDefaultErrKey"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Size sizeRequired = Argsx.readSizeArgOrThrow(fragment, "sizeRequired");
            Size sizeOptional = Argsx.readSizeArgOrNull(fragment, "sizeOptional");
            Size sizeOptionalErrKey = Argsx.readSizeArgOrNull(fragment, "sizeOptionalErrKey");
            Size sizeOrDefault = Argsx.readSizeArgOr(fragment, "sizeOrDefault", new Size(0, 0));
            Size sizeOrDefaultErrKey = Argsx.readSizeArgOr(fragment, "sizeOrDefaultErrKey", new Size(4, 4));

            SizeF sizeFRequired = Argsx.readSizeFArgOrThrow(fragment, "sizeFRequired");
            SizeF sizeFOptional = Argsx.readSizeFArgOrNull(fragment, "sizeFOptional");
            SizeF sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(fragment, "sizeFOptionalErrKey");
            SizeF sizeFOrDefault = Argsx.readSizeFArgOr(fragment, "sizeFOrDefault", new SizeF(0f, 0f));
            SizeF sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(fragment, "sizeFOrDefaultErrKey", new SizeF(4f, 4f));

            Assert.assertTrue(sizeFRequired.getWidth() == 1f && sizeFRequired.getHeight() == 1f);
            Assert.assertTrue(sizeFOptional.getWidth() == 2f && sizeFOptional.getHeight() == 2f);
            Assert.assertNull(sizeFOptionalErrKey);
            Assert.assertTrue(sizeFOrDefault.getWidth() == 3f && sizeFOrDefault.getHeight() == 3f);
            Assert.assertTrue(sizeFOrDefaultErrKey.getWidth() == 4f && sizeFOrDefaultErrKey.getHeight() == 4f);

            Assert.assertTrue(sizeRequired.getWidth() == 1 && sizeRequired.getHeight() == 1);
            Assert.assertTrue(sizeOptional.getWidth() == 2 && sizeOptional.getHeight() == 2);
            Assert.assertNull(sizeOptionalErrKey);
            Assert.assertTrue(sizeOrDefault.getWidth() == 3 && sizeOrDefault.getHeight() == 3);
            Assert.assertTrue(sizeOrDefaultErrKey.getWidth() == 4 && sizeOrDefaultErrKey.getHeight() == 4);
        }

        //test start
        Assert.assertTrue(sparseParcelableArrayRequired.get(-1).equals(new TestParcelable("-1")) && sparseParcelableArrayRequired.get(1).equals(new TestParcelable("1")));
        Assert.assertTrue(sparseParcelableArrayOptional.get(-2).equals(new TestParcelable("-2")) && sparseParcelableArrayOptional.get(2).equals(new TestParcelable("2")));
        Assert.assertNull(sparseParcelableArrayOptionalErrKey);
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3).equals(new TestParcelable("-3")) && sparseParcelableArrayOrDefault.get(3).equals(new TestParcelable("3")));
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0).equals(new TestParcelable("0")));

        Assert.assertTrue(bundleRequired.getString("bundle").equals("bundleRequired"));
        Assert.assertTrue(bundleOptional.getString("bundle").equals("bundleOptional"));
        Assert.assertNull(bundleOptionalErrKey);
        Assert.assertTrue(bundleOrDefault.getString("bundle").equals("bundleOrDefault"));
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle").equals("bundleErrKey"));


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

        Assert.assertTrue(charSequenceRequired.equals("stringRequired"));
        Assert.assertTrue(charSequenceRequiredErrKey.equals("charSequenceRequiredErrKey"));
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

        Assert.assertTrue(stringRequired.equals("stringRequired"));
        Assert.assertTrue(stringRequiredErrKey.equals("stringRequiredErrKey"));
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
