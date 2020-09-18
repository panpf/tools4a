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

public class ResTestSupportFragment extends Fragment {

    @NonNull
    public static ResTestSupportFragment newInstance(@NonNull Bundle args) {
        ResTestSupportFragment fragment = new ResTestSupportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void checkParams() {
        Fragment fragment = this;

        byte byteRequired = Argsx.readByteArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.byte_required, (byte) 0);
        byte byteRequiredErrKey = Argsx.readByteArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, (byte) 0);
        byte[] byteArrayRequired = Argsx.readByteArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.byte_array_required);
        byte[] byteArrayOptional = Argsx.readByteArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.byte_array_optional);
        byte[] byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        byte[] byteArrayOrDefault = Argsx.readByteArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.byte_array_or_default, byteArrayOf((byte) 0, (byte) 0));
        byte[] byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, byteArrayOf((byte) 0, (byte) (-1)));

        short shortRequired = Argsx.readShortArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.short_required, (short) 0);
        short shortRequiredErrKey = Argsx.readShortArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, (short) 0);
        short[] shortArrayRequired = Argsx.readShortArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.short_array_required);
        short[] shortArrayOptional = Argsx.readShortArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.short_array_optional);
        short[] shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        short[] shortArrayOrDefault = Argsx.readShortArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.short_array_or_default, shortArrayOf((short) 0, (short) 0));
        short[] shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, shortArrayOf((short) 0, (short) (-1)));


        int intRequired = Argsx.readIntArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.int_required, 0);
        int intRequiredErrKey = Argsx.readIntArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0);
        int[] intArrayRequired = Argsx.readIntArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.int_array_required);
        int[] intArrayOptional = Argsx.readIntArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.int_array_optional);
        int[] intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        int[] intArrayOrDefault = Argsx.readIntArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.int_array_or_default, intArrayOf(0, 0));
        int[] intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, intArrayOf(0, (-1)));
        ArrayList<Integer> intArrayListRequired = Argsx.readIntArrayListArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.int_array_list_required);
        ArrayList<Integer> intArrayListOrDefault = Argsx.readIntArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.int_array_list_or_default, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOptional = Argsx.readIntArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.int_array_list_optional);
        ArrayList<Integer> intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        long longRequired = Argsx.readLongArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.long_required, 0);
        long longRequiredErrKey = Argsx.readLongArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0);
        long[] longArrayRequired = Argsx.readLongArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.long_array_required);
        long[] longArrayOptional = Argsx.readLongArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.long_array_optional);
        long[] longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        long[] longArrayOrDefault = Argsx.readLongArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.long_array_or_default, longArrayOf(0, 0));
        long[] longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, longArrayOf(0, (-1)));

        float floatRequired = Argsx.readFloatArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.float_required, 0f);
        float floatRequiredErrKey = Argsx.readFloatArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0f);
        float[] floatArrayRequired = Argsx.readFloatArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.float_array_required);
        float[] floatArrayOptional = Argsx.readFloatArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.float_array_optional);
        float[] floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        float[] floatArrayOrDefault = Argsx.readFloatArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.float_array_or_default, floatArrayOf(0f, 0f));
        float[] floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, floatArrayOf(0f, (-1f)));

        double doubleRequired = Argsx.readDoubleArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.double_required, 0d);
        double doubleRequiredErrKey = Argsx.readDoubleArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0d);
        double[] doubleArrayRequired = Argsx.readDoubleArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.double_array_required);
        double[] doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.double_array_optional);
        double[] doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        double[] doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.double_array_or_default, doubleArrayOf(0d, 0d));
        double[] doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, doubleArrayOf(0d, (-1d)));

        boolean booleanRequired = Argsx.readBooleanArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.boolean_required, false);
        boolean booleanRequiredErrKey = Argsx.readBooleanArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, false);
        boolean[] booleanArrayRequired = Argsx.readBooleanArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.boolean_array_required);
        boolean[] booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.boolean_array_optional);
        boolean[] booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        boolean[] booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.boolean_array_or_default, booleanArrayOf(true, false));
        boolean[] booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, booleanArrayOf(false, true));

        char charRequired = Argsx.readCharArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.char_required, 'a');
        char charRequiredErrKey = Argsx.readCharArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 'b');
        char[] charArrayRequired = Argsx.readCharArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.char_array_required);
        char[] charArrayOptional = Argsx.readCharArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.char_array_optional);
        char[] charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        char[] charArrayOrDefault = Argsx.readCharArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.char_array_or_default, charArrayOf('a', 'b'));
        char[] charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, charArrayOf('b', 'a'));

        String stringRequired = Argsx.readStringArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.string_required, "stringRequired");
        String stringRequiredErrKey = Argsx.readStringArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "stringRequiredErrKey");
        String[] stringArrayRequired = Argsx.readStringArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.string_array_required);
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.string_array_optional);
        String[] stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        String[] stringArrayOrDefault = Argsx.readStringArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.string_array_or_default, arrayOf("array", "dft"));
        String[] stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<String> stringArrayListRequired = Argsx.readStringArrayListArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.string_array_list_required);
        ArrayList<String> stringArrayListOrDefault = Argsx.readStringArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.string_array_list_or_default, arrayListOf("list", "default"));
        ArrayList<String> stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"));
        ArrayList<String> stringArrayListOptional = Argsx.readStringArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.string_array_list_optional);
        ArrayList<String> stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        CharSequence charSequenceRequired = Argsx.readCharSequenceArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_required, "charSequenceRequired");
        CharSequence charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "charSequenceRequiredErrKey");
        CharSequence[] charSequenceArrayRequired = Argsx.readCharSequenceArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_required);
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_optional);
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_or_default, arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_required);
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_or_default, Collectionx.arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, Collectionx.arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_optional);
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_required, new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_array_required);
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_array_optional);
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_array_or_default, arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_required);
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_or_default, Collectionx.arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, Collectionx.arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_optional);
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.serializable_required);
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.serializable_optional);
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.serializable_or_default, new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.bundle_required);
        Bundle bundleOptional = Argsx.readBundleArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.bundle_optional);
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        Bundle bundleOrDefault = Argsx.readBundleArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.bundle_or_default, new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, defaultBundle);


        SparseArray<Parcelable> sparseArrayDefault = new SparseArray<>();
        sparseArrayDefault.put(0, new TestParcelable("0"));
        SparseArray<Parcelable> sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.sparse_parcelable_array_required);
        SparseArray<Parcelable> sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.sparse_parcelable_array_optional);
        SparseArray<Parcelable> sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        SparseArray<Parcelable> sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.sparse_parcelable_array_or_default, sparseArrayDefault);
        SparseArray<Parcelable> sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, sparseArrayDefault);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            IBinder binderRequired = Argsx.readBinderArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.binder_required);
            IBinder binderOptional = Argsx.readBinderArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.binder_optional);
            IBinder binderOptionalErrKey = Argsx.readBinderArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
            IBinder binderOrDefault = Argsx.readBinderArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.binder_or_default, new TestBinder(""));
            IBinder binderOrDefaultErrKey = Argsx.readBinderArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new TestBinder("binderOrDefaultErrKey"));

            Assert.assertEquals(binderRequired, new TestBinder("binderRequired"));
            Assert.assertEquals(binderOptional, new TestBinder("binderOptional"));
            Assert.assertNull(binderOptionalErrKey);
            Assert.assertEquals(binderOrDefault, new TestBinder("binderOrDefault"));
            Assert.assertEquals(binderOrDefaultErrKey, new TestBinder("binderOrDefaultErrKey"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Size sizeRequired = Argsx.readSizeArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.size_required);
            Size sizeOptional = Argsx.readSizeArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.size_optional);
            Size sizeOptionalErrKey = Argsx.readSizeArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
            Size sizeOrDefault = Argsx.readSizeArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.size_or_default, new Size(0, 0));
            Size sizeOrDefaultErrKey = Argsx.readSizeArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new Size(4, 4));

            SizeF sizeFRequired = Argsx.readSizeFArgOrThrow(fragment, com.github.panpf.tools4a.args.test.R.string.sizeF_required);
            SizeF sizeFOptional = Argsx.readSizeFArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.sizeF_optional);
            SizeF sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
            SizeF sizeFOrDefault = Argsx.readSizeFArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.sizeF_or_default, new SizeF(0f, 0f));
            SizeF sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(fragment, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new SizeF(4f, 4f));

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
