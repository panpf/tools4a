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

public class ResTestActivity extends FragmentActivity {

    @NonNull
    public static Intent createIntent(@NonNull Context context) {
        Intent starter = new Intent(context, ResTestActivity.class);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_required), (byte) 2);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_array_required), byteArrayOf((byte) 2, (byte) (-2)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_array_or_default), byteArrayOf((byte) 2, (byte) (-2)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_array_optional), byteArrayOf((byte) (-2), (byte) 2));


        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.short_required), (short) 3);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.short_array_required), shortArrayOf((short) 3, (short) (-3)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.short_array_optional), shortArrayOf((short) (-3), (short) 3));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_required), 500);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_array_required), intArrayOf(500, -500));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_array_optional), intArrayOf(-500, 500));
        starter.putIntegerArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_array_list_required), arrayListOf(500, -500));
        starter.putIntegerArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_array_list_optional), arrayListOf(-500, 500));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.long_required), 1000L);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.long_array_required), longArrayOf(1000L, -1000L));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.long_array_optional), longArrayOf(-1000L, 1000L));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.float_required), 4f);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.float_array_required), floatArrayOf(4f, -4f));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.float_array_optional), floatArrayOf(-4f, 4f));


        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.double_required), 6d);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.double_array_required), doubleArrayOf(6d, (-6d)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.double_array_optional), doubleArrayOf((-6d), 6d));


        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.boolean_required), true);
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.boolean_array_required), booleanArrayOf(true, false));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.boolean_array_optional), booleanArrayOf(false, true));


        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_required), 'a');
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_array_required), charArrayOf('a', 'b'));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_array_optional), charArrayOf('b', 'a'));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_required), "stringRequired");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_optional), "stringOptional");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_array_required), arrayOf("stringRequired", "stringOptional"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"));
        starter.putStringArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"));
        starter.putStringArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_required), "stringRequired");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_optional), "stringOptional");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"));


        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_required), new TestParcelable("parcelableRequired"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_optional), new TestParcelable("parcelableOptional"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_array_required), arrayOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_array_optional), arrayOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));
        starter.putParcelableArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_required), arrayListOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
        starter.putParcelableArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_optional), arrayListOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));


        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.serializable_required), new TestSerializable("serializableRequired"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.serializable_optional), new TestSerializable("serializableOptional"));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.byte_array_or_default), byteArrayOf((byte) 2, (byte) (-2)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.short_array_or_default), shortArrayOf((short) 3, (short) (-3)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_array_or_default), intArrayOf(500, -500));
        starter.putIntegerArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.int_array_list_or_default), arrayListOf(600, -600));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.long_array_or_default), longArrayOf(1000L, -1000L));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.float_array_or_default), floatArrayOf(4f, -4f));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.double_array_or_default), doubleArrayOf(6d, (-6d)));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.boolean_array_or_default), booleanArrayOf(true, false));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_array_or_default), charArrayOf('a', 'b'));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_or_default), "charSequenceOrDefault");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_array_or_default), arrayOf("charSequence", "default"));


        starter.putCharSequenceArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_required), Collectionx.arrayListOf("charSequenceArrayListRequired", "required"));
        starter.putCharSequenceArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_optional), Collectionx.arrayListOf("charSequenceArrayListOptional", "optional"));
        starter.putCharSequenceArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_or_default), Collectionx.arrayListOf("charSequenceArrayListOrDefault", "default"));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_or_default), "stringOrDefault");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_or_default), new TestParcelable("parcelableOrDefault"));
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_array_or_default), arrayOf(new TestParcelable("parcelableArrayOrDefault"), new TestParcelable("default")));
        starter.putParcelableArrayListExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_or_default), arrayListOf(new TestParcelable("parcelableArrayListOrDefault"), new TestParcelable("default")));

        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.serializable_or_default), new TestSerializable("serializableOrDefault"));

        Bundle bundleDefault = new Bundle();
        bundleDefault.putString("bundle", "bundleOrDefault");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.bundle_or_default), bundleDefault);

        Bundle bundleRequired = new Bundle();
        bundleRequired.putString("bundle", "bundleRequired");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.bundle_required), bundleRequired);

        Bundle bundleOptional = new Bundle();
        bundleOptional.putString("bundle", "bundleOptional");
        starter.putExtra(context.getString(com.github.panpf.tools4a.args.test.R.string.bundle_optional), bundleOptional);

        Bundle b = new Bundle();
        b.putString("extrasRequired", "extrasRequired");
        b.putString("extrasOptional", "extrasOptional");
        b.putString("extrasOrDefault", "extrasOrDefault");
        starter.putExtras(b);

        Bundle args = new Bundle();
        SparseArray<Parcelable> sparseParcelableArrayRequired = new SparseArray<>();
        sparseParcelableArrayRequired.put(-1, new TestParcelable("-1"));
        sparseParcelableArrayRequired.put(1, new TestParcelable("1"));
        args.putSparseParcelableArray(context.getString(com.github.panpf.tools4a.args.test.R.string.sparse_parcelable_array_required), sparseParcelableArrayRequired);

        SparseArray<Parcelable> sparseParcelableArrayOptional = new SparseArray<>();
        sparseParcelableArrayOptional.put(-2, new TestParcelable("-2"));
        sparseParcelableArrayOptional.put(2, new TestParcelable("2"));
        args.putSparseParcelableArray(context.getString(com.github.panpf.tools4a.args.test.R.string.sparse_parcelable_array_optional), sparseParcelableArrayOptional);

        SparseArray<Parcelable> sparseParcelableArrayOrDefault = new SparseArray<>();
        sparseParcelableArrayOrDefault.put(-3, new TestParcelable("-3"));
        sparseParcelableArrayOrDefault.put(3, new TestParcelable("3"));
        args.putSparseParcelableArray(context.getString(com.github.panpf.tools4a.args.test.R.string.sparse_parcelable_array_or_default), sparseParcelableArrayOrDefault);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            args.putBinder(context.getString(com.github.panpf.tools4a.args.test.R.string.binder_required), new TestBinder("binderRequired"));
            args.putBinder(context.getString(com.github.panpf.tools4a.args.test.R.string.binder_optional), new TestBinder("binderOptional"));
            args.putBinder(context.getString(com.github.panpf.tools4a.args.test.R.string.binder_or_default), new TestBinder("binderOrDefault"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            args.putSize(context.getString(com.github.panpf.tools4a.args.test.R.string.size_required), new Size(1, 1));
            args.putSize(context.getString(com.github.panpf.tools4a.args.test.R.string.size_optional), new Size(2, 2));
            args.putSize(context.getString(com.github.panpf.tools4a.args.test.R.string.size_or_default), new Size(3, 3));

            args.putSizeF(context.getString(com.github.panpf.tools4a.args.test.R.string.sizeF_required), new SizeF(1f, 1f));
            args.putSizeF(context.getString(com.github.panpf.tools4a.args.test.R.string.sizeF_optional), new SizeF(2f, 2f));
            args.putSizeF(context.getString(com.github.panpf.tools4a.args.test.R.string.sizeF_or_default), new SizeF(3f, 3f));
        }

        starter.putExtras(args);

        return starter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.github.panpf.tools4a.args.test.R.layout.at_test);
        ResTestSupportFragment supportFragment = ResTestSupportFragment.newInstance(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(com.github.panpf.tools4a.args.test.R.id.testAt_frame, supportFragment).commit();
    }

    @NonNull
    public ResTestSupportFragment getFragment() {
        return (ResTestSupportFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(com.github.panpf.tools4a.args.test.R.id.testAt_frame));
    }

    public void checkParams() {
        Activity activity = this;

        byte byteRequired = Argsx.readByteArgOr(activity, com.github.panpf.tools4a.args.test.R.string.byte_required, (byte) 0);
        byte byteRequiredErrKey = Argsx.readByteArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, (byte) 0);
        byte[] byteArrayRequired = Argsx.readByteArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.byte_array_required);
        byte[] byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.byte_array_optional);
        byte[] byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        byte[] byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.byte_array_or_default, byteArrayOf((byte) 0, (byte) 0));
        byte[] byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, byteArrayOf((byte) 0, (byte) (-1)));

        short shortRequired = Argsx.readShortArgOr(activity, com.github.panpf.tools4a.args.test.R.string.short_required, (short) 0);
        short shortRequiredErrKey = Argsx.readShortArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, (short) 0);
        short[] shortArrayRequired = Argsx.readShortArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.short_array_required);
        short[] shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.short_array_optional);
        short[] shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        short[] shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.short_array_or_default, shortArrayOf((short) 0, (short) 0));
        short[] shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, shortArrayOf((short) 0, (short) (-1)));


        int intRequired = Argsx.readIntArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_required, 0);
        int intRequiredErrKey = Argsx.readIntArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0);
        int[] intArrayRequired = Argsx.readIntArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.int_array_required);
        int[] intArrayOptional = Argsx.readIntArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.int_array_optional);
        int[] intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        int[] intArrayOrDefault = Argsx.readIntArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_array_or_default, intArrayOf(0, 0));
        int[] intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, intArrayOf(0, (-1)));
        ArrayList<Integer> intArrayListRequired = Argsx.readIntArrayListArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.int_array_list_required);
        ArrayList<Integer> intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.int_array_list_or_default, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.int_array_list_optional);
        ArrayList<Integer> intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        long longRequired = Argsx.readLongArgOr(activity, com.github.panpf.tools4a.args.test.R.string.long_required, 0);
        long longRequiredErrKey = Argsx.readLongArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0);
        long[] longArrayRequired = Argsx.readLongArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.long_array_required);
        long[] longArrayOptional = Argsx.readLongArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.long_array_optional);
        long[] longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        long[] longArrayOrDefault = Argsx.readLongArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.long_array_or_default, longArrayOf(0, 0));
        long[] longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, longArrayOf(0, (-1)));

        float floatRequired = Argsx.readFloatArgOr(activity, com.github.panpf.tools4a.args.test.R.string.float_required, 0f);
        float floatRequiredErrKey = Argsx.readFloatArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0f);
        float[] floatArrayRequired = Argsx.readFloatArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.float_array_required);
        float[] floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.float_array_optional);
        float[] floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        float[] floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.float_array_or_default, floatArrayOf(0f, 0f));
        float[] floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, floatArrayOf(0f, (-1f)));

        double doubleRequired = Argsx.readDoubleArgOr(activity, com.github.panpf.tools4a.args.test.R.string.double_required, 0d);
        double doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 0d);
        double[] doubleArrayRequired = Argsx.readDoubleArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.double_array_required);
        double[] doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.double_array_optional);
        double[] doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        double[] doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.double_array_or_default, doubleArrayOf(0d, 0d));
        double[] doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, doubleArrayOf(0d, (-1d)));

        boolean booleanRequired = Argsx.readBooleanArgOr(activity, com.github.panpf.tools4a.args.test.R.string.boolean_required, false);
        boolean booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, false);
        boolean[] booleanArrayRequired = Argsx.readBooleanArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.boolean_array_required);
        boolean[] booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.boolean_array_optional);
        boolean[] booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        boolean[] booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.boolean_array_or_default, booleanArrayOf(true, false));
        boolean[] booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, booleanArrayOf(false, true));

        char charRequired = Argsx.readCharArgOr(activity, com.github.panpf.tools4a.args.test.R.string.char_required, 'a');
        char charRequiredErrKey = Argsx.readCharArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, 'b');
        char[] charArrayRequired = Argsx.readCharArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.char_array_required);
        char[] charArrayOptional = Argsx.readCharArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.char_array_optional);
        char[] charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        char[] charArrayOrDefault = Argsx.readCharArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.char_array_or_default, charArrayOf('a', 'b'));
        char[] charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, charArrayOf('b', 'a'));

        String stringRequired = Argsx.readStringArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_required, "stringRequired");
        String stringRequiredErrKey = Argsx.readStringArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "stringRequiredErrKey");
        String[] stringArrayRequired = Argsx.readStringArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.string_array_required);
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.string_array_optional);
        String[] stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        String[] stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_array_or_default, arrayOf("array", "dft"));
        String[] stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<String> stringArrayListRequired = Argsx.readStringArrayListArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.string_array_list_required);
        ArrayList<String> stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.string_array_list_or_default, arrayListOf("list", "default"));
        ArrayList<String> stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"));
        ArrayList<String> stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.string_array_list_optional);
        ArrayList<String> stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        CharSequence charSequenceRequired = Argsx.readCharSequenceArgOr(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_required, "charSequenceRequired");
        CharSequence charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, "charSequenceRequiredErrKey");
        CharSequence[] charSequenceArrayRequired = Argsx.readCharSequenceArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_required);
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_optional);
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_or_default, arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_required);
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_or_default, Collectionx.<CharSequence>arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, Collectionx.<CharSequence>arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.char_sequence_array_list_optional);
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_required, new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_array_required);
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_array_optional);
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_array_or_default, arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_required);
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_or_default, Collectionx.<Parcelable>arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, Collectionx.<Parcelable>arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.parcelable_array_list_optional);
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.serializable_required);
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.serializable_optional);
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(activity, com.github.panpf.tools4a.args.test.R.string.serializable_or_default, new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(activity, com.github.panpf.tools4a.args.test.R.string.bundle_required);
        Bundle bundleOptional = Argsx.readBundleArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.bundle_optional);
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key);
        Bundle bundleOrDefault = Argsx.readBundleArgOr(activity, com.github.panpf.tools4a.args.test.R.string.bundle_or_default, new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, com.github.panpf.tools4a.args.test.R.string.not_exist_key, defaultBundle);

        Bundle extrasRequired = Argsx.readExtrasArgOrThrow(activity);
        Bundle extrasOptional = Argsx.readExtrasArgOrNull(activity);
        Bundle extrasDefault = Argsx.readExtrasArgOr(activity, new Bundle());

        //test start
        Assert.assertTrue(extrasRequired.getString("extrasRequired").equals("extrasRequired"));
        Assert.assertTrue(extrasOptional.getString("extrasOptional").equals("extrasOptional"));
        Assert.assertTrue(extrasDefault.getString("extrasOrDefault").equals("extrasOrDefault"));

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
