package com.github.panpf.tools4a.args.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.args.Argsx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;
import java.util.ArrayList;

import com.github.panpf.tools4j.collections.Collectionx;
import com.github.panpf.tools4j.lang.Stringx;

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

/**
 * <P>Created by Vincent on 2018/11/29.</P>
 */
@RunWith(AndroidJUnit4.class)
public class ArgsxTest {

    @Rule
    public final ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<TestActivity>(TestActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return TestActivity.createIntent(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<ResTestActivity> mResActivityTestRule = new ActivityTestRule<ResTestActivity>(ResTestActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return ResTestActivity.createIntent(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<NoExtraActivity> mNoExtrasActivityTestRule = new ActivityTestRule<NoExtraActivity>(NoExtraActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return NoExtraActivity.createIntent(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<TestOnlyUriNoIntentActivity> uriNoIntentActivityActivityTestRule = new ActivityTestRule<TestOnlyUriNoIntentActivity>(TestOnlyUriNoIntentActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return TestOnlyUriNoIntentActivity.createIntentWithUri();
        }
    };

    @Rule
    public final ActivityTestRule<TestOnlyIntentNoUriActivity> intentNoUriActivityActivityTestRule = new ActivityTestRule<TestOnlyIntentNoUriActivity>(TestOnlyIntentNoUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return TestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<TestBothIntentUriActivity> bothIntentUriActivityActivityTestRule = new ActivityTestRule<TestBothIntentUriActivity>(TestBothIntentUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return TestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<TestNoIntentUriActivity> noIntentUriActivityActivityTestRule = new ActivityTestRule<TestNoIntentUriActivity>(TestNoIntentUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return TestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<ResTestOnlyUriNoIntentActivity> resUriNoIntentActivityActivityTestRule = new ActivityTestRule<ResTestOnlyUriNoIntentActivity>(ResTestOnlyUriNoIntentActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return ResTestOnlyUriNoIntentActivity.createIntentWithUri();
        }
    };

    @Rule
    public final ActivityTestRule<ResTestOnlyIntentNoUriActivity> resIntentNoUriActivityActivityTestRule = new ActivityTestRule<ResTestOnlyIntentNoUriActivity>(ResTestOnlyIntentNoUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return ResTestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<ResTestBothIntentUriActivity> resBothIntentUriActivityActivityTestRule = new ActivityTestRule<ResTestBothIntentUriActivity>(ResTestBothIntentUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return ResTestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<ResTestNoIntentUriActivity> resNoIntentUriActivityActivityTestRule = new ActivityTestRule<ResTestNoIntentUriActivity>(ResTestNoIntentUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return ResTestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext());
        }
    };

    @Rule
    public final ActivityTestRule<TestUriActivity> uriActivityTestRule = new ActivityTestRule<TestUriActivity>(TestUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return TestUriActivity.createIntent();
        }
    };

    @Rule
    public final ActivityTestRule<ResTestUriActivity> resUriActivityTestRule = new ActivityTestRule<ResTestUriActivity>(ResTestUriActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            return ResTestUriActivity.createIntent();
        }
    };

    @Test
    public void activityIntentArgsTest() {
        TestActivity activity = mActivityTestRule.getActivity();

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
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptional");
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
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptional");
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptionalErrKey");
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefault", arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(activity, "charSequenceArrayListRequired");
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefault", Collectionx.arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefaultErrKey", Collectionx.arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptional");
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptionalErrKey");


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(activity, "parcelableRequired", new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, "parcelableRequiredErrKey", new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(activity, "parcelableArrayRequired");
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(activity, "parcelableArrayOptional");
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(activity, "parcelableArrayOptionalErrKey");
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefault", arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefaultErrKey", arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(activity, "parcelableArrayListRequired");
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefault", Collectionx.arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefaultErrKey", Collectionx.arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(activity, "parcelableArrayListOptional");
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(activity, "parcelableArrayListOptionalErrKey");

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(activity, "serializableRequired");
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(activity, "serializableOptional");
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(activity, "serializableOptionalErrKey");
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(activity, "serializableOrDefault", new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, "serializableOrDefaultErrKey", new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(activity, "bundleRequired");
        Bundle bundleOptional = Argsx.readBundleArgOrNull(activity, "bundleOptional");
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, "bundleOptionalErrKey");
        Bundle bundleOrDefault = Argsx.readBundleArgOr(activity, "bundleOrDefault", new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, "bundleOrDefaultErrKey", defaultBundle);

        Bundle extrasRequired = Argsx.readExtrasArgOrThrow(activity);
        Bundle extrasOptional = Argsx.readExtrasArgOrNull(activity);
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

    @Test
    public void supportFragmentArgsTest() {
        TestActivity activityHost = mActivityTestRule.getActivity();
        Fragment activity = activityHost.getSupportFragmentManager().findFragmentById(R.id.testAt_frame);

        assert activity != null;

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
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, "stringArrayOptional");
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
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptional");
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, "charSequenceArrayOptionalErrKey");
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefault", arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, "charSequenceArrayOrDefaultErrKey", arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(activity, "charSequenceArrayListRequired");
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefault", Collectionx.<CharSequence>arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, "charSequenceArrayListOrDefaultErrKey", Collectionx.<CharSequence>arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptional");
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, "charSequenceArrayListOptionalErrKey");


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(activity, "parcelableRequired", new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, "parcelableRequiredErrKey", new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(activity, "parcelableArrayRequired");
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(activity, "parcelableArrayOptional");
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(activity, "parcelableArrayOptionalErrKey");
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefault", arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, "parcelableArrayOrDefaultErrKey", arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(activity, "parcelableArrayListRequired");
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefault", Collectionx.<Parcelable>arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, "parcelableArrayListOrDefaultErrKey", Collectionx.<Parcelable>arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(activity, "parcelableArrayListOptional");
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(activity, "parcelableArrayListOptionalErrKey");

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(activity, "serializableRequired");
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(activity, "serializableOptional");
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(activity, "serializableOptionalErrKey");
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(activity, "serializableOrDefault", new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, "serializableOrDefaultErrKey", new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(activity, "bundleRequired");
        Bundle bundleOptional = Argsx.readBundleArgOrNull(activity, "bundleOptional");
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, "bundleOptionalErrKey");
        Bundle bundleOrDefault = Argsx.readBundleArgOr(activity, "bundleOrDefault", new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, "bundleOrDefaultErrKey", defaultBundle);


        SparseArray<Parcelable> sparseArrayDefault = new SparseArray<>();
        sparseArrayDefault.put(0, new TestParcelable("0"));
        SparseArray<Parcelable> sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArgOrThrow(activity, "sparseParcelableArrayRequired");
        SparseArray<Parcelable> sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull(activity, "sparseParcelableArrayOptional");
        SparseArray<Parcelable> sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull(activity, "sparseParcelableArrayOptionalErrKey");
        SparseArray<Parcelable> sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(activity, "sparseParcelableArrayOrDefault", sparseArrayDefault);
        SparseArray<Parcelable> sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(activity, "sparseParcelableArrayOrDefaultErrKey", sparseArrayDefault);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            IBinder binderRequired = Argsx.readBinderArgOrThrow(activity, "binderRequired");
            IBinder binderOptional = Argsx.readBinderArgOrNull(activity, "binderOptional");
            IBinder binderOptionalErrKey = Argsx.readBinderArgOrNull(activity, "binderOptionalErrKey");
            IBinder binderOrDefault = Argsx.readBinderArgOr(activity, "binderOrDefault", new TestBinder(""));
            IBinder binderOrDefaultErrKey = Argsx.readBinderArgOr(activity, "binderOrDefaultErrKey", new TestBinder("binderOrDefaultErrKey"));

            Assert.assertEquals(binderRequired, new TestBinder("binderRequired"));
            Assert.assertEquals(binderOptional, new TestBinder("binderOptional"));
            Assert.assertNull(binderOptionalErrKey);
            Assert.assertEquals(binderOrDefault, new TestBinder("binderOrDefault"));
            Assert.assertEquals(binderOrDefaultErrKey, new TestBinder("binderOrDefaultErrKey"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Size sizeRequired = Argsx.readSizeArgOrThrow(activity, "sizeRequired");
            Size sizeOptional = Argsx.readSizeArgOrNull(activity, "sizeOptional");
            Size sizeOptionalErrKey = Argsx.readSizeArgOrNull(activity, "sizeOptionalErrKey");
            Size sizeOrDefault = Argsx.readSizeArgOr(activity, "sizeOrDefault", new Size(0, 0));
            Size sizeOrDefaultErrKey = Argsx.readSizeArgOr(activity, "sizeOrDefaultErrKey", new Size(4, 4));

            SizeF sizeFRequired = Argsx.readSizeFArgOrThrow(activity, "sizeFRequired");
            SizeF sizeFOptional = Argsx.readSizeFArgOrNull(activity, "sizeFOptional");
            SizeF sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(activity, "sizeFOptionalErrKey");
            SizeF sizeFOrDefault = Argsx.readSizeFArgOr(activity, "sizeFOrDefault", new SizeF(0f, 0f));
            SizeF sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(activity, "sizeFOrDefaultErrKey", new SizeF(4f, 4f));

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

    @Test
    public void noExtrasActivityTest() {
        Activity activity = mNoExtrasActivityTestRule.getActivity();

        Bundle extrasOptional = Argsx.readExtrasArgOrNull(activity);
        Bundle bundleDefault = new Bundle();
        bundleDefault.putString("errDefault", "default");
        Bundle extrasDefault = Argsx.readExtrasArgOr(activity, bundleDefault);

        Assert.assertNull(extrasOptional);
        Assert.assertTrue(extrasDefault.getString("errDefault").equals("default"));
    }

    //res
    @Test
    public void resActivityIntentArgsTest() {
        ResTestActivity activity = mResActivityTestRule.getActivity();

        byte byteRequired = Argsx.readByteArgOr(activity, R.string.byte_required, (byte) 0);
        byte byteRequiredErrKey = Argsx.readByteArgOr(activity, R.string.not_exist_key, (byte) 0);
        byte[] byteArrayRequired = Argsx.readByteArrayArgOrThrow(activity, R.string.byte_array_required);
        byte[] byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, R.string.byte_array_optional);
        byte[] byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, R.string.not_exist_key);
        byte[] byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, R.string.byte_array_or_default, byteArrayOf((byte) 0, (byte) 0));
        byte[] byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, R.string.not_exist_key, byteArrayOf((byte) 0, (byte) (-1)));

        short shortRequired = Argsx.readShortArgOr(activity, R.string.short_required, (short) 0);
        short shortRequiredErrKey = Argsx.readShortArgOr(activity, R.string.not_exist_key, (short) 0);
        short[] shortArrayRequired = Argsx.readShortArrayArgOrThrow(activity, R.string.short_array_required);
        short[] shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, R.string.short_array_optional);
        short[] shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, R.string.not_exist_key);
        short[] shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, R.string.short_array_or_default, shortArrayOf((short) 0, (short) 0));
        short[] shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, R.string.not_exist_key, shortArrayOf((short) 0, (short) (-1)));


        int intRequired = Argsx.readIntArgOr(activity, R.string.int_required, 0);
        int intRequiredErrKey = Argsx.readIntArgOr(activity, R.string.not_exist_key, 0);
        int[] intArrayRequired = Argsx.readIntArrayArgOrThrow(activity, R.string.int_array_required);
        int[] intArrayOptional = Argsx.readIntArrayArgOrNull(activity, R.string.int_array_optional);
        int[] intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, R.string.not_exist_key);
        int[] intArrayOrDefault = Argsx.readIntArrayArgOr(activity, R.string.int_array_or_default, intArrayOf(0, 0));
        int[] intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, R.string.not_exist_key, intArrayOf(0, (-1)));
        ArrayList<Integer> intArrayListRequired = Argsx.readIntArrayListArgOrThrow(activity, R.string.int_array_list_required);
        ArrayList<Integer> intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, R.string.int_array_list_or_default, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, R.string.not_exist_key, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, R.string.int_array_list_optional);
        ArrayList<Integer> intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, R.string.not_exist_key);

        long longRequired = Argsx.readLongArgOr(activity, R.string.long_required, 0);
        long longRequiredErrKey = Argsx.readLongArgOr(activity, R.string.not_exist_key, 0);
        long[] longArrayRequired = Argsx.readLongArrayArgOrThrow(activity, R.string.long_array_required);
        long[] longArrayOptional = Argsx.readLongArrayArgOrNull(activity, R.string.long_array_optional);
        long[] longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, R.string.not_exist_key);
        long[] longArrayOrDefault = Argsx.readLongArrayArgOr(activity, R.string.long_array_or_default, longArrayOf(0, 0));
        long[] longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, R.string.not_exist_key, longArrayOf(0, (-1)));

        float floatRequired = Argsx.readFloatArgOr(activity, R.string.float_required, 0f);
        float floatRequiredErrKey = Argsx.readFloatArgOr(activity, R.string.not_exist_key, 0f);
        float[] floatArrayRequired = Argsx.readFloatArrayArgOrThrow(activity, R.string.float_array_required);
        float[] floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, R.string.float_array_optional);
        float[] floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, R.string.not_exist_key);
        float[] floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, R.string.float_array_or_default, floatArrayOf(0f, 0f));
        float[] floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, R.string.not_exist_key, floatArrayOf(0f, (-1f)));

        double doubleRequired = Argsx.readDoubleArgOr(activity, R.string.double_required, 0d);
        double doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, R.string.not_exist_key, 0d);
        double[] doubleArrayRequired = Argsx.readDoubleArrayArgOrThrow(activity, R.string.double_array_required);
        double[] doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, R.string.double_array_optional);
        double[] doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, R.string.not_exist_key);
        double[] doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, R.string.double_array_or_default, doubleArrayOf(0d, 0d));
        double[] doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, R.string.not_exist_key, doubleArrayOf(0d, (-1d)));

        boolean booleanRequired = Argsx.readBooleanArgOr(activity, R.string.boolean_required, false);
        boolean booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, R.string.not_exist_key, false);
        boolean[] booleanArrayRequired = Argsx.readBooleanArrayArgOrThrow(activity, R.string.boolean_array_required);
        boolean[] booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, R.string.boolean_array_optional);
        boolean[] booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, R.string.not_exist_key);
        boolean[] booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, R.string.boolean_array_or_default, booleanArrayOf(true, false));
        boolean[] booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, R.string.not_exist_key, booleanArrayOf(false, true));

        char charRequired = Argsx.readCharArgOr(activity, R.string.char_required, 'a');
        char charRequiredErrKey = Argsx.readCharArgOr(activity, R.string.not_exist_key, 'b');
        char[] charArrayRequired = Argsx.readCharArrayArgOrThrow(activity, R.string.char_array_required);
        char[] charArrayOptional = Argsx.readCharArrayArgOrNull(activity, R.string.char_array_optional);
        char[] charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, R.string.not_exist_key);
        char[] charArrayOrDefault = Argsx.readCharArrayArgOr(activity, R.string.char_array_or_default, charArrayOf('a', 'b'));
        char[] charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, R.string.not_exist_key, charArrayOf('b', 'a'));

        String stringRequired = Argsx.readStringArgOr(activity, R.string.string_required, "stringRequired");
        String stringRequiredErrKey = Argsx.readStringArgOr(activity, R.string.not_exist_key, "stringRequiredErrKey");
        String[] stringArrayRequired = Argsx.readStringArrayArgOrThrow(activity, R.string.string_array_required);
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, R.string.string_array_optional);
        String[] stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, R.string.not_exist_key);
        String[] stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, R.string.string_array_or_default, arrayOf("array", "dft"));
        String[] stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<String> stringArrayListRequired = Argsx.readStringArrayListArgOrThrow(activity, R.string.string_array_list_required);
        ArrayList<String> stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, R.string.string_array_list_or_default, arrayListOf("list", "default"));
        ArrayList<String> stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"));
        ArrayList<String> stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, R.string.string_array_list_optional);
        ArrayList<String> stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, R.string.not_exist_key);

        CharSequence charSequenceRequired = Argsx.readCharSequenceArgOr(activity, R.string.char_sequence_required, "charSequenceRequired");
        CharSequence charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, R.string.not_exist_key, "charSequenceRequiredErrKey");
        CharSequence[] charSequenceArrayRequired = Argsx.readCharSequenceArrayArgOrThrow(activity, R.string.char_sequence_array_required);
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.char_sequence_array_optional);
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.not_exist_key);
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, R.string.char_sequence_array_or_default, arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(activity, R.string.char_sequence_array_list_required);
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, R.string.char_sequence_array_list_or_default, Collectionx.<CharSequence>arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, R.string.not_exist_key, Collectionx.<CharSequence>arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.char_sequence_array_list_optional);
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.not_exist_key);


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(activity, R.string.parcelable_required, new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, R.string.not_exist_key, new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(activity, R.string.parcelable_array_required);
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(activity, R.string.parcelable_array_optional);
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(activity, R.string.not_exist_key);
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, R.string.parcelable_array_or_default, arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, R.string.not_exist_key, arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(activity, R.string.parcelable_array_list_required);
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, R.string.parcelable_array_list_or_default, Collectionx.<Parcelable>arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, R.string.not_exist_key, Collectionx.<Parcelable>arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(activity, R.string.parcelable_array_list_optional);
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(activity, R.string.not_exist_key);

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(activity, R.string.serializable_required);
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(activity, R.string.serializable_optional);
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(activity, R.string.not_exist_key);
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(activity, R.string.serializable_or_default, new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, R.string.not_exist_key, new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(activity, R.string.bundle_required);
        Bundle bundleOptional = Argsx.readBundleArgOrNull(activity, R.string.bundle_optional);
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, R.string.not_exist_key);
        Bundle bundleOrDefault = Argsx.readBundleArgOr(activity, R.string.bundle_or_default, new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, R.string.not_exist_key, defaultBundle);

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

    @Test
    public void resSupportFragmentArgsTest() {
        ResTestActivity hostActivity = mResActivityTestRule.getActivity();
        Fragment activity = hostActivity.getSupportFragmentManager().findFragmentById(R.id.testAt_frame);
        assert activity != null;

        byte byteRequired = Argsx.readByteArgOr(activity, R.string.byte_required, (byte) 0);
        byte byteRequiredErrKey = Argsx.readByteArgOr(activity, R.string.not_exist_key, (byte) 0);
        byte[] byteArrayRequired = Argsx.readByteArrayArgOrThrow(activity, R.string.byte_array_required);
        byte[] byteArrayOptional = Argsx.readByteArrayArgOrNull(activity, R.string.byte_array_optional);
        byte[] byteArrayOptionalErrKey = Argsx.readByteArrayArgOrNull(activity, R.string.not_exist_key);
        byte[] byteArrayOrDefault = Argsx.readByteArrayArgOr(activity, R.string.byte_array_or_default, byteArrayOf((byte) 0, (byte) 0));
        byte[] byteArrayOrDefaultErrKey = Argsx.readByteArrayArgOr(activity, R.string.not_exist_key, byteArrayOf((byte) 0, (byte) (-1)));

        short shortRequired = Argsx.readShortArgOr(activity, R.string.short_required, (short) 0);
        short shortRequiredErrKey = Argsx.readShortArgOr(activity, R.string.not_exist_key, (short) 0);
        short[] shortArrayRequired = Argsx.readShortArrayArgOrThrow(activity, R.string.short_array_required);
        short[] shortArrayOptional = Argsx.readShortArrayArgOrNull(activity, R.string.short_array_optional);
        short[] shortArrayOptionalErrKey = Argsx.readShortArrayArgOrNull(activity, R.string.not_exist_key);
        short[] shortArrayOrDefault = Argsx.readShortArrayArgOr(activity, R.string.short_array_or_default, shortArrayOf((short) 0, (short) 0));
        short[] shortArrayOrDefaultErrKey = Argsx.readShortArrayArgOr(activity, R.string.not_exist_key, shortArrayOf((short) 0, (short) (-1)));


        int intRequired = Argsx.readIntArgOr(activity, R.string.int_required, 0);
        int intRequiredErrKey = Argsx.readIntArgOr(activity, R.string.not_exist_key, 0);
        int[] intArrayRequired = Argsx.readIntArrayArgOrThrow(activity, R.string.int_array_required);
        int[] intArrayOptional = Argsx.readIntArrayArgOrNull(activity, R.string.int_array_optional);
        int[] intArrayOptionalErrKey = Argsx.readIntArrayArgOrNull(activity, R.string.not_exist_key);
        int[] intArrayOrDefault = Argsx.readIntArrayArgOr(activity, R.string.int_array_or_default, intArrayOf(0, 0));
        int[] intArrayOrDefaultErrKey = Argsx.readIntArrayArgOr(activity, R.string.not_exist_key, intArrayOf(0, (-1)));
        ArrayList<Integer> intArrayListRequired = Argsx.readIntArrayListArgOrThrow(activity, R.string.int_array_list_required);
        ArrayList<Integer> intArrayListOrDefault = Argsx.readIntArrayListArgOr(activity, R.string.int_array_list_or_default, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOrDefaultErrKey = Argsx.readIntArrayListArgOr(activity, R.string.not_exist_key, arrayListOf(0, 0));
        ArrayList<Integer> intArrayListOptional = Argsx.readIntArrayListArgOrNull(activity, R.string.int_array_list_optional);
        ArrayList<Integer> intArrayListOptionalErrKey = Argsx.readIntArrayListArgOrNull(activity, R.string.not_exist_key);

        long longRequired = Argsx.readLongArgOr(activity, R.string.long_required, 0);
        long longRequiredErrKey = Argsx.readLongArgOr(activity, R.string.not_exist_key, 0);
        long[] longArrayRequired = Argsx.readLongArrayArgOrThrow(activity, R.string.long_array_required);
        long[] longArrayOptional = Argsx.readLongArrayArgOrNull(activity, R.string.long_array_optional);
        long[] longArrayOptionalErrKey = Argsx.readLongArrayArgOrNull(activity, R.string.not_exist_key);
        long[] longArrayOrDefault = Argsx.readLongArrayArgOr(activity, R.string.long_array_or_default, longArrayOf(0, 0));
        long[] longArrayOrDefaultErrKey = Argsx.readLongArrayArgOr(activity, R.string.not_exist_key, longArrayOf(0, (-1)));

        float floatRequired = Argsx.readFloatArgOr(activity, R.string.float_required, 0f);
        float floatRequiredErrKey = Argsx.readFloatArgOr(activity, R.string.not_exist_key, 0f);
        float[] floatArrayRequired = Argsx.readFloatArrayArgOrThrow(activity, R.string.float_array_required);
        float[] floatArrayOptional = Argsx.readFloatArrayArgOrNull(activity, R.string.float_array_optional);
        float[] floatArrayOptionalErrKey = Argsx.readFloatArrayArgOrNull(activity, R.string.not_exist_key);
        float[] floatArrayOrDefault = Argsx.readFloatArrayArgOr(activity, R.string.float_array_or_default, floatArrayOf(0f, 0f));
        float[] floatArrayOrDefaultErrKey = Argsx.readFloatArrayArgOr(activity, R.string.not_exist_key, floatArrayOf(0f, (-1f)));

        double doubleRequired = Argsx.readDoubleArgOr(activity, R.string.double_required, 0d);
        double doubleRequiredErrKey = Argsx.readDoubleArgOr(activity, R.string.not_exist_key, 0d);
        double[] doubleArrayRequired = Argsx.readDoubleArrayArgOrThrow(activity, R.string.double_array_required);
        double[] doubleArrayOptional = Argsx.readDoubleArrayArgOrNull(activity, R.string.double_array_optional);
        double[] doubleArrayOptionalErrKey = Argsx.readDoubleArrayArgOrNull(activity, R.string.not_exist_key);
        double[] doubleArrayOrDefault = Argsx.readDoubleArrayArgOr(activity, R.string.double_array_or_default, doubleArrayOf(0d, 0d));
        double[] doubleArrayOrDefaultErrKey = Argsx.readDoubleArrayArgOr(activity, R.string.not_exist_key, doubleArrayOf(0d, (-1d)));

        boolean booleanRequired = Argsx.readBooleanArgOr(activity, R.string.boolean_required, false);
        boolean booleanRequiredErrKey = Argsx.readBooleanArgOr(activity, R.string.not_exist_key, false);
        boolean[] booleanArrayRequired = Argsx.readBooleanArrayArgOrThrow(activity, R.string.boolean_array_required);
        boolean[] booleanArrayOptional = Argsx.readBooleanArrayArgOrNull(activity, R.string.boolean_array_optional);
        boolean[] booleanArrayOptionalErrKey = Argsx.readBooleanArrayArgOrNull(activity, R.string.not_exist_key);
        boolean[] booleanArrayOrDefault = Argsx.readBooleanArrayArgOr(activity, R.string.boolean_array_or_default, booleanArrayOf(true, false));
        boolean[] booleanArrayOrDefaultErrKey = Argsx.readBooleanArrayArgOr(activity, R.string.not_exist_key, booleanArrayOf(false, true));

        char charRequired = Argsx.readCharArgOr(activity, R.string.char_required, 'a');
        char charRequiredErrKey = Argsx.readCharArgOr(activity, R.string.not_exist_key, 'b');
        char[] charArrayRequired = Argsx.readCharArrayArgOrThrow(activity, R.string.char_array_required);
        char[] charArrayOptional = Argsx.readCharArrayArgOrNull(activity, R.string.char_array_optional);
        char[] charArrayOptionalErrKey = Argsx.readCharArrayArgOrNull(activity, R.string.not_exist_key);
        char[] charArrayOrDefault = Argsx.readCharArrayArgOr(activity, R.string.char_array_or_default, charArrayOf('a', 'b'));
        char[] charArrayOrDefaultErrKey = Argsx.readCharArrayArgOr(activity, R.string.not_exist_key, charArrayOf('b', 'a'));

        String stringRequired = Argsx.readStringArgOr(activity, R.string.string_required, "stringRequired");
        String stringRequiredErrKey = Argsx.readStringArgOr(activity, R.string.not_exist_key, "stringRequiredErrKey");
        String[] stringArrayRequired = Argsx.readStringArrayArgOrThrow(activity, R.string.string_array_required);
        String[] stringArrayOptional = Argsx.readStringArrayArgOrNull(activity, R.string.string_array_optional);
        String[] stringArrayOptionalErrKey = Argsx.readStringArrayArgOrNull(activity, R.string.not_exist_key);
        String[] stringArrayOrDefault = Argsx.readStringArrayArgOr(activity, R.string.string_array_or_default, arrayOf("array", "dft"));
        String[] stringArrayOrDefaultErrKey = Argsx.readStringArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<String> stringArrayListRequired = Argsx.readStringArrayListArgOrThrow(activity, R.string.string_array_list_required);
        ArrayList<String> stringArrayListOrDefault = Argsx.readStringArrayListArgOr(activity, R.string.string_array_list_or_default, arrayListOf("list", "default"));
        ArrayList<String> stringArrayListOrDefaultErrKey = Argsx.readStringArrayListArgOr(activity, R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"));
        ArrayList<String> stringArrayListOptional = Argsx.readStringArrayListArgOrNull(activity, R.string.string_array_list_optional);
        ArrayList<String> stringArrayListOptionalErrKey = Argsx.readStringArrayListArgOrNull(activity, R.string.not_exist_key);

        CharSequence charSequenceRequired = Argsx.readCharSequenceArgOr(activity, R.string.char_sequence_required, "charSequenceRequired");
        CharSequence charSequenceRequiredErrKey = Argsx.readCharSequenceArgOr(activity, R.string.not_exist_key, "charSequenceRequiredErrKey");
        CharSequence[] charSequenceArrayRequired = Argsx.readCharSequenceArrayArgOrThrow(activity, R.string.char_sequence_array_required);
        CharSequence[] charSequenceArrayOptional = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.char_sequence_array_optional);
        CharSequence[] charSequenceArrayOptionalErrKey = Argsx.readCharSequenceArrayArgOrNull(activity, R.string.not_exist_key);
        CharSequence[] charSequenceArrayOrDefault = Argsx.readCharSequenceArrayArgOr(activity, R.string.char_sequence_array_or_default, arrayOf("array", "dft"));
        CharSequence[] charSequenceArrayOrDefaultErrKey = Argsx.readCharSequenceArrayArgOr(activity, R.string.not_exist_key, arrayOf("error", "erk"));
        ArrayList<CharSequence> charSequenceArrayListRequired = Argsx.readCharSequenceArrayListArgOrThrow(activity, R.string.char_sequence_array_list_required);
        ArrayList<CharSequence> charSequenceArrayListOrDefault = Argsx.readCharSequenceArrayListArgOr(activity, R.string.char_sequence_array_list_or_default, Collectionx.<CharSequence>arrayListOf("list", "default"));
        ArrayList<CharSequence> charSequenceArrayListOrDefaultErrKey = Argsx.readCharSequenceArrayListArgOr(activity, R.string.not_exist_key, Collectionx.<CharSequence>arrayListOf("charSequenceArrayListOrDefaultErrKey", "errKey"));
        ArrayList<CharSequence> charSequenceArrayListOptional = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.char_sequence_array_list_optional);
        ArrayList<CharSequence> charSequenceArrayListOptionalErrKey = Argsx.readCharSequenceArrayListArgOrNull(activity, R.string.not_exist_key);


        Parcelable parcelableRequired = Argsx.readParcelableArgOr(activity, R.string.parcelable_required, new TestParcelable("required"));
        Parcelable parcelableRequiredErrKey = Argsx.readParcelableArgOr(activity, R.string.not_exist_key, new TestParcelable("parcelableRequiredErrKey"));
        Parcelable[] parcelableArrayRequired = Argsx.readParcelableArrayArgOrThrow(activity, R.string.parcelable_array_required);
        Parcelable[] parcelableArrayOptional = Argsx.readParcelableArrayArgOrNull(activity, R.string.parcelable_array_optional);
        Parcelable[] parcelableArrayOptionalErrKey = Argsx.readParcelableArrayArgOrNull(activity, R.string.not_exist_key);
        Parcelable[] parcelableArrayOrDefault = Argsx.readParcelableArrayArgOr(activity, R.string.parcelable_array_or_default, arrayOf(new TestParcelable("array"), new TestParcelable("dft")));
        Parcelable[] parcelableArrayOrDefaultErrKey = Argsx.readParcelableArrayArgOr(activity, R.string.not_exist_key, arrayOf(new TestParcelable("error"), new TestParcelable("erk")));
        ArrayList<Parcelable> parcelableArrayListRequired = Argsx.readParcelableArrayListArgOrThrow(activity, R.string.parcelable_array_list_required);
        ArrayList<Parcelable> parcelableArrayListOrDefault = Argsx.readParcelableArrayListArgOr(activity, R.string.parcelable_array_list_or_default, Collectionx.<Parcelable>arrayListOf(new TestParcelable("list"), new TestParcelable("default")));
        ArrayList<Parcelable> parcelableArrayListOrDefaultErrKey = Argsx.readParcelableArrayListArgOr(activity, R.string.not_exist_key, Collectionx.<Parcelable>arrayListOf(new TestParcelable("parcelableArrayListOrDefaultErrKey"), new TestParcelable("errKey")));
        ArrayList<Parcelable> parcelableArrayListOptional = Argsx.readParcelableArrayListArgOrNull(activity, R.string.parcelable_array_list_optional);
        ArrayList<Parcelable> parcelableArrayListOptionalErrKey = Argsx.readParcelableArrayListArgOrNull(activity, R.string.not_exist_key);

        TestSerializable serializableRequired = Argsx.readSerializableArgOrThrow(activity, R.string.serializable_required);
        TestSerializable serializableOptional = Argsx.readSerializableArgOrNull(activity, R.string.serializable_optional);
        TestSerializable serializableOptionalErrKey = Argsx.readSerializableArgOrNull(activity, R.string.not_exist_key);
        TestSerializable serializableOrDefault = Argsx.readSerializableArgOr(activity, R.string.serializable_or_default, new TestSerializable("default"));
        TestSerializable serializableOrDefaultErrKey = Argsx.readSerializableArgOr(activity, R.string.not_exist_key, new TestSerializable("errKey"));

        Bundle bundleRequired = Argsx.readBundleArgOrThrow(activity, R.string.bundle_required);
        Bundle bundleOptional = Argsx.readBundleArgOrNull(activity, R.string.bundle_optional);
        Bundle bundleOptionalErrKey = Argsx.readBundleArgOrNull(activity, R.string.not_exist_key);
        Bundle bundleOrDefault = Argsx.readBundleArgOr(activity, R.string.bundle_or_default, new Bundle());
        Bundle defaultBundle = new Bundle();
        defaultBundle.putString("bundle", "bundleErrKey");
        Bundle bundleOrDefaultErrKey = Argsx.readBundleArgOr(activity, R.string.not_exist_key, defaultBundle);


        SparseArray<Parcelable> sparseArrayDefault = new SparseArray<>();
        sparseArrayDefault.put(0, new TestParcelable("0"));
        SparseArray<Parcelable> sparseParcelableArrayRequired = Argsx.readSparseParcelableArrayArgOrThrow(activity, R.string.sparse_parcelable_array_required);
        SparseArray<Parcelable> sparseParcelableArrayOptional = Argsx.readSparseParcelableArrayArgOrNull(activity, R.string.sparse_parcelable_array_optional);
        SparseArray<Parcelable> sparseParcelableArrayOptionalErrKey = Argsx.readSparseParcelableArrayArgOrNull(activity, R.string.not_exist_key);
        SparseArray<Parcelable> sparseParcelableArrayOrDefault = Argsx.readSparseParcelableArrayArgOr(activity, R.string.sparse_parcelable_array_or_default, sparseArrayDefault);
        SparseArray<Parcelable> sparseParcelableArrayOrDefaultErrKey = Argsx.readSparseParcelableArrayArgOr(activity, R.string.not_exist_key, sparseArrayDefault);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            IBinder binderRequired = Argsx.readBinderArgOrThrow(activity, R.string.binder_required);
            IBinder binderOptional = Argsx.readBinderArgOrNull(activity, R.string.binder_optional);
            IBinder binderOptionalErrKey = Argsx.readBinderArgOrNull(activity, R.string.not_exist_key);
            IBinder binderOrDefault = Argsx.readBinderArgOr(activity, R.string.binder_or_default, new TestBinder(""));
            IBinder binderOrDefaultErrKey = Argsx.readBinderArgOr(activity, R.string.not_exist_key, new TestBinder("binderOrDefaultErrKey"));

            Assert.assertEquals(binderRequired, new TestBinder("binderRequired"));
            Assert.assertEquals(binderOptional, new TestBinder("binderOptional"));
            Assert.assertNull(binderOptionalErrKey);
            Assert.assertEquals(binderOrDefault, new TestBinder("binderOrDefault"));
            Assert.assertEquals(binderOrDefaultErrKey, new TestBinder("binderOrDefaultErrKey"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Size sizeRequired = Argsx.readSizeArgOrThrow(activity, R.string.size_required);
            Size sizeOptional = Argsx.readSizeArgOrNull(activity, R.string.size_optional);
            Size sizeOptionalErrKey = Argsx.readSizeArgOrNull(activity, R.string.not_exist_key);
            Size sizeOrDefault = Argsx.readSizeArgOr(activity, R.string.size_or_default, new Size(0, 0));
            Size sizeOrDefaultErrKey = Argsx.readSizeArgOr(activity, R.string.not_exist_key, new Size(4, 4));

            SizeF sizeFRequired = Argsx.readSizeFArgOrThrow(activity, R.string.sizeF_required);
            SizeF sizeFOptional = Argsx.readSizeFArgOrNull(activity, R.string.sizeF_optional);
            SizeF sizeFOptionalErrKey = Argsx.readSizeFArgOrNull(activity, R.string.not_exist_key);
            SizeF sizeFOrDefault = Argsx.readSizeFArgOr(activity, R.string.sizeF_or_default, new SizeF(0f, 0f));
            SizeF sizeFOrDefaultErrKey = Argsx.readSizeFArgOr(activity, R.string.not_exist_key, new SizeF(4f, 4f));

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

    //uri || intent
    @Test
    public void uriOnlyActivityTest() {
        Activity activity = uriNoIntentActivityActivityTestRule.getActivity();

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

        Assert.assertTrue(byteIntentUriOrDefault == ((byte) 1));
        Assert.assertTrue(shortIntentUriOrDefault == ((short) 2));
        Assert.assertTrue(intIntentUriOrDefault == 3);
        Assert.assertTrue(longIntentUriOrDefault == 4L);
        Assert.assertTrue(floatIntentUriOrDefault == 5f);
        Assert.assertTrue(doubleIntentUriOrDefault == 6d);
        Assert.assertTrue(booleanIntentUriOrDefault);
        Assert.assertTrue(stringIntentUriRequired.equals("stringIntentUriRequired"));
        Assert.assertTrue(stringIntentUriOptional.equals("stringIntentUriOptional"));
        Assert.assertNull(stringIntentUriOptionalErrKey);
        Assert.assertTrue(stringIntentUriOrDefault.equals("stringIntentUriOrDefault"));
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

    @Test
    public void intentOnlyActivityTest() {
        Activity activity = intentNoUriActivityActivityTestRule.getActivity();

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
        Assert.assertTrue(byteUriIntentOrDefault == ((byte) -11));
        Assert.assertTrue(shortUriIntentOrDefault == ((short) -12));
        Assert.assertTrue(intUriIntentOrDefault == -13);
        Assert.assertTrue(longUriIntentOrDefault == -14L);
        Assert.assertTrue(floatUriIntentOrDefault == -15f);
        Assert.assertTrue(doubleUriIntentOrDefault == -16d);
        Assert.assertTrue(booleanUriIntentOrDefault);
        Assert.assertTrue(stringUriIntentRequired.equals("stringUriIntentRequired"));
        Assert.assertTrue(stringUriIntentOptional.equals("stringUriIntentOptional"));
        Assert.assertNull(stringUriIntentOptionalErrKey);
        Assert.assertTrue(stringUriIntentOrDefault.equals("stringUriIntentOrDefault"));
        Assert.assertTrue(stringUriIntentOrDefaultErrKey.equals("stringUriIntentOrDefaultErrKey"));
    }

    @Test
    public void bothUriIntentActivityTest() {
        Activity activity = bothIntentUriActivityActivityTestRule.getActivity();

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

    @Test
    public void noUriIntentActivityTest() {
        Activity activity = noIntentUriActivityActivityTestRule.getActivity();

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

    //res
    @Test
    public void resUriOnlyActivityTest() {
        Activity activity = resUriNoIntentActivityActivityTestRule.getActivity();

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false);
        String stringIntentUriRequired = Argsx.readStringIntentUriArgOrThrow(activity, R.string.string_intent_uri_required);
        String stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default");
        String stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey");
        String stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional);
        String stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key);

        //uri intent
        byte byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, ((byte) 0));
        short shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, ((short) 0));
        int intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0);
        long longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L);
        float floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, ((float) 0));
        double doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, ((double) 0));
        boolean booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false);
        String stringUriIntentRequired = Argsx.readStringUriIntentArgOrThrow(activity, R.string.string_uri_intent_required);
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional);
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key);

        Assert.assertTrue(byteIntentUriOrDefault == ((byte) 1));
        Assert.assertTrue(shortIntentUriOrDefault == ((short) 2));
        Assert.assertTrue(intIntentUriOrDefault == 3);
        Assert.assertTrue(longIntentUriOrDefault == 4L);
        Assert.assertTrue(floatIntentUriOrDefault == 5f);
        Assert.assertTrue(doubleIntentUriOrDefault == 6d);
        Assert.assertTrue(booleanIntentUriOrDefault);
        Assert.assertTrue(stringIntentUriRequired.equals("stringIntentUriRequired"));
        Assert.assertTrue(stringIntentUriOptional.equals("stringIntentUriOptional"));
        Assert.assertNull(stringIntentUriOptionalErrKey);
        Assert.assertTrue(stringIntentUriOrDefault.equals("stringIntentUriOrDefault"));
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

    @Test
    public void resIntentOnlyActivityTest() {
        Activity activity = intentNoUriActivityActivityTestRule.getActivity();

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false);
        String stringIntentUriRequired = Argsx.readStringIntentUriArgOrThrow(activity, R.string.string_intent_uri_required);
        String stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default");
        String stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey");
        String stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional);
        String stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key);

        //uri intent
        byte byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, ((byte) 0));
        short shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, ((short) 0));
        int intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0);
        long longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L);
        float floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, ((float) 0));
        double doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, ((double) 0));
        boolean booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false);
        String stringUriIntentRequired = Argsx.readStringUriIntentArgOrThrow(activity, R.string.string_uri_intent_required);
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional);
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key);

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
        Assert.assertTrue(byteUriIntentOrDefault == ((byte) -11));
        Assert.assertTrue(shortUriIntentOrDefault == ((short) -12));
        Assert.assertTrue(intUriIntentOrDefault == -13);
        Assert.assertTrue(longUriIntentOrDefault == -14L);
        Assert.assertTrue(floatUriIntentOrDefault == -15f);
        Assert.assertTrue(doubleUriIntentOrDefault == -16d);
        Assert.assertTrue(booleanUriIntentOrDefault);
        Assert.assertTrue(stringUriIntentRequired.equals("stringUriIntentRequired"));
        Assert.assertTrue(stringUriIntentOptional.equals("stringUriIntentOptional"));
        Assert.assertNull(stringUriIntentOptionalErrKey);
        Assert.assertTrue(stringUriIntentOrDefault.equals("stringUriIntentOrDefault"));
        Assert.assertTrue(stringUriIntentOrDefaultErrKey.equals("stringUriIntentOrDefaultErrKey"));
    }

    @Test
    public void resBothUriIntentActivityTest() {
        Activity activity = resBothIntentUriActivityActivityTestRule.getActivity();

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false);
        String stringIntentUriRequired = Argsx.readStringIntentUriArgOrThrow(activity, R.string.string_intent_uri_required);
        String stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default");
        String stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey");
        String stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional);
        String stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key);

        //uri intent
        byte byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, ((byte) 0));
        short shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, ((short) 0));
        int intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0);
        long longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L);
        float floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, ((float) 0));
        double doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, ((double) 0));
        boolean booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false);
        String stringUriIntentRequired = Argsx.readStringUriIntentArgOrThrow(activity, R.string.string_uri_intent_required);
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional);
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key);

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

    @Test
    public void resNoUriIntentActivityTest() {
        Activity activity = noIntentUriActivityActivityTestRule.getActivity();

        byte byteIntentUriOrDefault = Argsx.readByteIntentUriArgOr(activity, R.string.byte_intent_uri_or_default, ((byte) 0));
        short shortIntentUriOrDefault = Argsx.readShortIntentUriArgOr(activity, R.string.short_intent_uri_or_default, ((short) 0));
        int intIntentUriOrDefault = Argsx.readIntIntentUriArgOr(activity, R.string.int_intent_uri_or_default, 0);
        long longIntentUriOrDefault = Argsx.readLongIntentUriArgOr(activity, R.string.long_intent_uri_or_default, 0L);
        float floatIntentUriOrDefault = Argsx.readFloatIntentUriArgOr(activity, R.string.float_intent_uri_or_default, ((float) 0));
        double doubleIntentUriOrDefault = Argsx.readDoubleIntentUriArgOr(activity, R.string.double_intent_uri_or_default, ((double) 0));
        boolean booleanIntentUriOrDefault = Argsx.readBooleanIntentUriArgOr(activity, R.string.boolean_intent_uri_or_default, false);
//        String stringIntentUriRequired = Argsx.readStringIntentUriArg(activity, R.string.string_intent_uri_required);
        String stringIntentUriOrDefault = Argsx.readStringIntentUriArgOr(activity, R.string.string_intent_uri_or_default, "default");
        String stringIntentUriOrDefaultErrKey = Argsx.readStringIntentUriArgOr(activity, R.string.not_exist_key, "stringIntentUriOrDefaultErrKey");
        String stringIntentUriOptional = Argsx.readStringIntentUriArgOrNull(activity, R.string.string_intent_uri_optional);
        String stringIntentUriOptionalErrKey = Argsx.readStringIntentUriArgOrNull(activity, R.string.not_exist_key);

        //uri intent
        byte byteUriIntentOrDefault = Argsx.readByteUriIntentArgOr(activity, R.string.byte_uri_intent_or_default, ((byte) 0));
        short shortUriIntentOrDefault = Argsx.readShortUriIntentArgOr(activity, R.string.short_uri_intent_or_default, ((short) 0));
        int intUriIntentOrDefault = Argsx.readIntUriIntentArgOr(activity, R.string.int_uri_intent_or_default, 0);
        long longUriIntentOrDefault = Argsx.readLongUriIntentArgOr(activity, R.string.long_uri_intent_or_default, 0L);
        float floatUriIntentOrDefault = Argsx.readFloatUriIntentArgOr(activity, R.string.float_uri_intent_or_default, ((float) 0));
        double doubleUriIntentOrDefault = Argsx.readDoubleUriIntentArgOr(activity, R.string.double_uri_intent_or_default, ((double) 0));
        boolean booleanUriIntentOrDefault = Argsx.readBooleanUriIntentArgOr(activity, R.string.boolean_uri_intent_or_default, false);
//        String stringUriIntentRequired = Argsx.readStringUriIntentArg(activity, R.string.string_uri_intent_required);
        String stringUriIntentOrDefault = Argsx.readStringUriIntentArgOr(activity, R.string.string_uri_intent_or_default, "default");
        String stringUriIntentOrDefaultErrKey = Argsx.readStringUriIntentArgOr(activity, R.string.not_exist_key, "stringUriIntentOrDefaultErrKey");
        String stringUriIntentOptional = Argsx.readStringUriIntentArgOrNull(activity, R.string.string_uri_intent_optional);
        String stringUriIntentOptionalErrKey = Argsx.readStringUriIntentArgOrNull(activity, R.string.not_exist_key);

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

    //activity uri
    @Test
    public void uriActivityTest() {
        Activity activity = uriActivityTestRule.getActivity();

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

    @Test
    public void resUriActivityTest() {
        Activity activity = resUriActivityTestRule.getActivity();

        byte byteUriRequired = Argsx.readByteUriArgOrThrow(activity, R.string.byte_uri_required);
        Byte byteUriOptional = Argsx.readByteUriArgOrNull(activity, R.string.byte_uri_optional);
        Byte byteUriOptionalErrKey = Argsx.readByteUriArgOrNull(activity, R.string.not_exist_key);
        byte byteUriOrDefault = Argsx.readByteUriArgOr(activity, R.string.byte_uri_or_default, ((byte) 0));
        byte byteUriOrDefaultErrKey = Argsx.readByteUriArgOr(activity, R.string.not_exist_key, ((byte) 0));

        short shortUriRequired = Argsx.readShortUriArgOrThrow(activity, R.string.short_uri_required);
        Short shortUriOptional = Argsx.readShortUriArgOrNull(activity, R.string.short_uri_optional);
        Short shortUriOptionalErrKey = Argsx.readShortUriArgOrNull(activity, R.string.not_exist_key);
        short shortUriOrDefault = Argsx.readShortUriArgOr(activity, R.string.short_uri_or_default, ((short) 0));
        short shortUriOrDefaultErrKey = Argsx.readShortUriArgOr(activity, R.string.not_exist_key, ((short) 0));

        int intUriRequired = Argsx.readIntUriArgOrThrow(activity, R.string.int_uri_required);
        Integer intUriOptional = Argsx.readIntUriArgOrNull(activity, R.string.int_uri_optional);
        Integer intUriOptionalErrKey = Argsx.readIntUriArgOrNull(activity, R.string.not_exist_key);
        int intUriOrDefault = Argsx.readIntUriArgOr(activity, R.string.int_uri_or_default, 0);
        int intUriOrDefaultErrKey = Argsx.readIntUriArgOr(activity, R.string.not_exist_key, 0);

        long longUriRequired = Argsx.readLongUriArgOrThrow(activity, R.string.long_uri_required);
        Long longUriOptional = Argsx.readLongUriArgOrNull(activity, R.string.long_uri_optional);
        Long longUriOptionalErrKey = Argsx.readLongUriArgOrNull(activity, R.string.not_exist_key);
        long longUriOrDefault = Argsx.readLongUriArgOr(activity, R.string.long_uri_or_default, 0L);
        long longUriOrDefaultErrKey = Argsx.readLongUriArgOr(activity, R.string.not_exist_key, 0L);

        float floatUriRequired = Argsx.readFloatUriArgOrThrow(activity, R.string.float_uri_required);
        Float floatUriOptional = Argsx.readFloatUriArgOrNull(activity, R.string.float_uri_optional);
        Float floatUriOptionalErrKey = Argsx.readFloatUriArgOrNull(activity, R.string.not_exist_key);
        float floatUriOrDefault = Argsx.readFloatUriArgOr(activity, R.string.float_uri_or_default, 0f);
        float floatUriOrDefaultErrKey = Argsx.readFloatUriArgOr(activity, R.string.not_exist_key, -1f);

        double doubleUriRequired = Argsx.readDoubleUriArgOrThrow(activity, R.string.double_uri_required);
        Double doubleUriOptional = Argsx.readDoubleUriArgOrNull(activity, R.string.double_uri_optional);
        Double doubleUriOptionalErrKey = Argsx.readDoubleUriArgOrNull(activity, R.string.not_exist_key);
        double doubleUriOrDefault = Argsx.readDoubleUriArgOr(activity, R.string.double_uri_or_default, 1d);
        double doubleUriOrDefaultErrKey = Argsx.readDoubleUriArgOr(activity, R.string.not_exist_key, -1d);

        boolean booleanUriRequired = Argsx.readBooleanUriArgOrThrow(activity, R.string.boolean_uri_required);
        Boolean booleanUriOptional = Argsx.readBooleanUriArgOrNull(activity, R.string.boolean_uri_optional);
        Boolean booleanUriOptionalErrKey = Argsx.readBooleanUriArgOrNull(activity, R.string.not_exist_key);
        boolean booleanUriOrDefault = Argsx.readBooleanUriArgOr(activity, R.string.boolean_uri_or_default, true);
        boolean booleanUriOrDefaultErrKey = Argsx.readBooleanUriArgOr(activity, R.string.not_exist_key, false);

        String stringUriRequired = Argsx.readStringUriArgOrThrow(activity, R.string.string_uri_required);
        String stringUriOptional = Argsx.readStringUriArgOrNull(activity, R.string.string_uri_optional);
        String stringUriOptionalErrKey = Argsx.readStringUriArgOrNull(activity, R.string.not_exist_key);
        String stringUriOrDefault = Argsx.readStringUriArgOr(activity, R.string.string_uri_or_default, "");
        String stringUriOrDefaultErrKey = Argsx.readStringUriArgOr(activity, R.string.not_exist_key, "stringUriOrDefaultErrKey");

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


    ///////////////////////////////////////////////////////////////////////////
    // class for test used
    ///////////////////////////////////////////////////////////////////////////

    public static class TestActivity extends FragmentActivity {

        public static Intent createIntent(Context context) {
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
            setContentView(R.layout.at_test);
            TestSupportFragment supportFragment = TestSupportFragment.newInstance(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.testAt_frame, supportFragment).commit();
        }
    }

    public static class TestSupportFragment extends Fragment {
        public static TestSupportFragment newInstance(Bundle args) {
            TestSupportFragment fragment = new TestSupportFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    //res
    public static class ResTestActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_test);
            ResTestSupportFragment supportFragment = ResTestSupportFragment.newInstance(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.testAt_frame, supportFragment).commit();
        }

        public static Intent createIntent(Context context) {
            Intent starter = new Intent(context, ResTestActivity.class);
            starter.putExtra(context.getString(R.string.byte_required), (byte) 2);
            starter.putExtra(context.getString(R.string.byte_array_required), byteArrayOf((byte) 2, (byte) (-2)));
            starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf((byte) 2, (byte) (-2)));
            starter.putExtra(context.getString(R.string.byte_array_optional), byteArrayOf((byte) (-2), (byte) 2));


            starter.putExtra(context.getString(R.string.short_required), (short) 3);
            starter.putExtra(context.getString(R.string.short_array_required), shortArrayOf((short) 3, (short) (-3)));
            starter.putExtra(context.getString(R.string.short_array_optional), shortArrayOf((short) (-3), (short) 3));

            starter.putExtra(context.getString(R.string.int_required), 500);
            starter.putExtra(context.getString(R.string.int_array_required), intArrayOf(500, -500));
            starter.putExtra(context.getString(R.string.int_array_optional), intArrayOf(-500, 500));
            starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_required), arrayListOf(500, -500));
            starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500));

            starter.putExtra(context.getString(R.string.long_required), 1000L);
            starter.putExtra(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L));
            starter.putExtra(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L));

            starter.putExtra(context.getString(R.string.float_required), 4f);
            starter.putExtra(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f));
            starter.putExtra(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f));


            starter.putExtra(context.getString(R.string.double_required), 6d);
            starter.putExtra(context.getString(R.string.double_array_required), doubleArrayOf(6d, (-6d)));
            starter.putExtra(context.getString(R.string.double_array_optional), doubleArrayOf((-6d), 6d));


            starter.putExtra(context.getString(R.string.boolean_required), true);
            starter.putExtra(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false));
            starter.putExtra(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true));


            starter.putExtra(context.getString(R.string.char_required), 'a');
            starter.putExtra(context.getString(R.string.char_array_required), charArrayOf('a', 'b'));
            starter.putExtra(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'));

            starter.putExtra(context.getString(R.string.string_required), "stringRequired");
            starter.putExtra(context.getString(R.string.string_optional), "stringOptional");
            starter.putExtra(context.getString(R.string.string_array_required), arrayOf("stringRequired", "stringOptional"));
            starter.putExtra(context.getString(R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"));
            starter.putStringArrayListExtra(context.getString(R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"));
            starter.putStringArrayListExtra(context.getString(R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"));

            starter.putExtra(context.getString(R.string.char_sequence_required), "stringRequired");
            starter.putExtra(context.getString(R.string.char_sequence_optional), "stringOptional");
            starter.putExtra(context.getString(R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"));
            starter.putExtra(context.getString(R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"));


            starter.putExtra(context.getString(R.string.parcelable_required), new TestParcelable("parcelableRequired"));
            starter.putExtra(context.getString(R.string.parcelable_optional), new TestParcelable("parcelableOptional"));
            starter.putExtra(context.getString(R.string.parcelable_array_required), arrayOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
            starter.putExtra(context.getString(R.string.parcelable_array_optional), arrayOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));
            starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_required), arrayListOf(new TestParcelable("parcelableRequired"), new TestParcelable("parcelableOptional")));
            starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_optional), arrayListOf(new TestParcelable("parcelableOptional"), new TestParcelable("parcelableRequired")));


            starter.putExtra(context.getString(R.string.serializable_required), new TestSerializable("serializableRequired"));
            starter.putExtra(context.getString(R.string.serializable_optional), new TestSerializable("serializableOptional"));

            starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf((byte) 2, (byte) (-2)));
            starter.putExtra(context.getString(R.string.short_array_or_default), shortArrayOf((short) 3, (short) (-3)));
            starter.putExtra(context.getString(R.string.int_array_or_default), intArrayOf(500, -500));
            starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600));
            starter.putExtra(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L));
            starter.putExtra(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f));
            starter.putExtra(context.getString(R.string.double_array_or_default), doubleArrayOf(6d, (-6d)));
            starter.putExtra(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false));
            starter.putExtra(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'));
            starter.putExtra(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault");
            starter.putExtra(context.getString(R.string.char_sequence_array_or_default), arrayOf("charSequence", "default"));


            starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_required), Collectionx.arrayListOf("charSequenceArrayListRequired", "required"));
            starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_optional), Collectionx.arrayListOf("charSequenceArrayListOptional", "optional"));
            starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_or_default), Collectionx.arrayListOf("charSequenceArrayListOrDefault", "default"));

            starter.putExtra(context.getString(R.string.string_or_default), "stringOrDefault");
            starter.putExtra(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"));
            starter.putExtra(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"));

            starter.putExtra(context.getString(R.string.parcelable_or_default), new TestParcelable("parcelableOrDefault"));
            starter.putExtra(context.getString(R.string.parcelable_array_or_default), arrayOf(new TestParcelable("parcelableArrayOrDefault"), new TestParcelable("default")));
            starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(new TestParcelable("parcelableArrayListOrDefault"), new TestParcelable("default")));

            starter.putExtra(context.getString(R.string.serializable_or_default), new TestSerializable("serializableOrDefault"));

            Bundle bundleDefault = new Bundle();
            bundleDefault.putString("bundle", "bundleOrDefault");
            starter.putExtra(context.getString(R.string.bundle_or_default), bundleDefault);

            Bundle bundleRequired = new Bundle();
            bundleRequired.putString("bundle", "bundleRequired");
            starter.putExtra(context.getString(R.string.bundle_required), bundleRequired);

            Bundle bundleOptional = new Bundle();
            bundleOptional.putString("bundle", "bundleOptional");
            starter.putExtra(context.getString(R.string.bundle_optional), bundleOptional);

            Bundle b = new Bundle();
            b.putString("extrasRequired", "extrasRequired");
            b.putString("extrasOptional", "extrasOptional");
            b.putString("extrasOrDefault", "extrasOrDefault");
            starter.putExtras(b);

            Bundle args = new Bundle();
            SparseArray<Parcelable> sparseParcelableArrayRequired = new SparseArray<>();
            sparseParcelableArrayRequired.put(-1, new TestParcelable("-1"));
            sparseParcelableArrayRequired.put(1, new TestParcelable("1"));
            args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_required), sparseParcelableArrayRequired);

            SparseArray<Parcelable> sparseParcelableArrayOptional = new SparseArray<>();
            sparseParcelableArrayOptional.put(-2, new TestParcelable("-2"));
            sparseParcelableArrayOptional.put(2, new TestParcelable("2"));
            args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_optional), sparseParcelableArrayOptional);

            SparseArray<Parcelable> sparseParcelableArrayOrDefault = new SparseArray<>();
            sparseParcelableArrayOrDefault.put(-3, new TestParcelable("-3"));
            sparseParcelableArrayOrDefault.put(3, new TestParcelable("3"));
            args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_or_default), sparseParcelableArrayOrDefault);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                args.putBinder(context.getString(R.string.binder_required), new TestBinder("binderRequired"));
                args.putBinder(context.getString(R.string.binder_optional), new TestBinder("binderOptional"));
                args.putBinder(context.getString(R.string.binder_or_default), new TestBinder("binderOrDefault"));
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                args.putSize(context.getString(R.string.size_required), new Size(1, 1));
                args.putSize(context.getString(R.string.size_optional), new Size(2, 2));
                args.putSize(context.getString(R.string.size_or_default), new Size(3, 3));

                args.putSizeF(context.getString(R.string.sizeF_required), new SizeF(1f, 1f));
                args.putSizeF(context.getString(R.string.sizeF_optional), new SizeF(2f, 2f));
                args.putSizeF(context.getString(R.string.sizeF_or_default), new SizeF(3f, 3f));
            }

            starter.putExtras(args);

            return starter;
        }
    }

    public static class ResTestSupportFragment extends Fragment {
        public static ResTestSupportFragment newInstance(Bundle args) {
            ResTestSupportFragment fragment = new ResTestSupportFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }

    //no extras
    public static class NoExtraActivity extends FragmentActivity {
        public static Intent createIntent(Context context) {
            return new Intent(context, NoExtraActivity.class);
        }
    }

    //uri || intent
    public static class TestOnlyUriNoIntentActivity extends FragmentActivity {
        public static Intent createIntentWithUri() {
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

            Uri uri = Uri.parse("https://github.com/panpf/androidx/uri?" + params);

            return new Intent(Intent.ACTION_VIEW, uri);
        }
    }

    public static class TestOnlyIntentNoUriActivity extends FragmentActivity {
        public static Intent createIntentWithExtras(Context context) {
            Intent starter = new Intent(context, TestOnlyIntentNoUriActivity.class);
            starter.setAction(Intent.ACTION_VIEW);
            starter.setData(Uri.parse("https://github.com/panpf"));
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
    }

    public static class TestBothIntentUriActivity extends FragmentActivity {

        public static Intent createIntentWithUriAndExtras(Context context) {

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

            Uri uri = Uri.parse("https://github.com/panpf/androidx/uri/intent?" + params);

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
    }

    public static class TestNoIntentUriActivity extends FragmentActivity {
        public static Intent createIntentWithNothing(Context context) {
            Intent starter = new Intent(context, TestNoIntentUriActivity.class);
            starter.setAction(Intent.ACTION_VIEW);
            starter.setData(Uri.parse("https://github.com/panpf"));
            return starter;
        }
    }

    //res
    public static class ResTestOnlyUriNoIntentActivity extends FragmentActivity {
        public static Intent createIntentWithUri() {
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

            Uri uri = Uri.parse("https://github.com/panpf/androidx/res/uri?" + params);

            return new Intent(Intent.ACTION_VIEW, uri);
        }
    }

    public static class ResTestOnlyIntentNoUriActivity extends FragmentActivity {
        public static Intent createIntentWithExtras(Context context) {
            Intent starter = new Intent(context, ResTestOnlyIntentNoUriActivity.class);
            starter.setAction(Intent.ACTION_VIEW);
            starter.setData(Uri.parse("https://github.com/panpf"));
            starter.putExtra(context.getString(R.string.byte_intent_uri_or_default), ((byte) -1));
            starter.putExtra(context.getString(R.string.short_intent_uri_or_default), ((short) -2));
            starter.putExtra(context.getString(R.string.int_intent_uri_or_default), -3);
            starter.putExtra(context.getString(R.string.long_intent_uri_or_default), -4L);
            starter.putExtra(context.getString(R.string.float_intent_uri_or_default), -5f);
            starter.putExtra(context.getString(R.string.double_intent_uri_or_default), (-6d));
            starter.putExtra(context.getString(R.string.boolean_intent_uri_or_default), true);
            starter.putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired");
            starter.putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional");
            starter.putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault");

            //Activity Uri Intent
            starter.putExtra(context.getString(R.string.byte_uri_intent_or_default), ((byte) -11));
            starter.putExtra(context.getString(R.string.short_uri_intent_or_default), ((short) -12));
            starter.putExtra(context.getString(R.string.int_uri_intent_or_default), -13);
            starter.putExtra(context.getString(R.string.long_uri_intent_or_default), -14L);
            starter.putExtra(context.getString(R.string.float_uri_intent_or_default), -15f);
            starter.putExtra(context.getString(R.string.double_uri_intent_or_default), (-16d));
            starter.putExtra(context.getString(R.string.boolean_uri_intent_or_default), true);
            starter.putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired");
            starter.putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional");
            starter.putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault");
            return starter;
        }
    }

    public static class ResTestBothIntentUriActivity extends FragmentActivity {

        public static Intent createIntentWithUriAndExtras(Context context) {

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

            Uri uri = Uri.parse("https://github.com/panpf/androidx/res/uri/intent?" + params);

            Intent starter = new Intent(context, ResTestBothIntentUriActivity.class);
            starter.setAction(Intent.ACTION_VIEW);
            starter.setData(uri);
            starter.putExtra(context.getString(R.string.byte_intent_uri_or_default), ((byte) -1));
            starter.putExtra(context.getString(R.string.short_intent_uri_or_default), ((short) -2));
            starter.putExtra(context.getString(R.string.int_intent_uri_or_default), -3);
            starter.putExtra(context.getString(R.string.long_intent_uri_or_default), -4L);
            starter.putExtra(context.getString(R.string.float_intent_uri_or_default), -5f);
            starter.putExtra(context.getString(R.string.double_intent_uri_or_default), (-6d));
            starter.putExtra(context.getString(R.string.boolean_intent_uri_or_default), true);
            starter.putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired");
            starter.putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional");
            starter.putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault");

            //Activity Uri Intent
            starter.putExtra(context.getString(R.string.byte_uri_intent_or_default), ((byte) -11));
            starter.putExtra(context.getString(R.string.short_uri_intent_or_default), ((short) -12));
            starter.putExtra(context.getString(R.string.int_uri_intent_or_default), -13);
            starter.putExtra(context.getString(R.string.long_uri_intent_or_default), -14L);
            starter.putExtra(context.getString(R.string.float_uri_intent_or_default), -15f);
            starter.putExtra(context.getString(R.string.double_uri_intent_or_default), (-16d));
            starter.putExtra(context.getString(R.string.boolean_uri_intent_or_default), true);
            starter.putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired");
            starter.putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional");
            starter.putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault");
            return starter;
        }
    }

    public static class ResTestNoIntentUriActivity extends FragmentActivity {
        public static Intent createIntentWithNothing(Context context) {
            Intent starter = new Intent(context, ResTestNoIntentUriActivity.class);
            starter.setAction(Intent.ACTION_VIEW);
            starter.setData(Uri.parse("https://github.com/panpf"));
            return starter;
        }
    }

    //activity uri
    public static class TestUriActivity extends FragmentActivity {

        public static Intent createIntent() {
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
            Uri uri = Uri.parse("https://github.com/panpf/androidx?" + params);
            return new Intent(Intent.ACTION_VIEW, uri);
        }
    }

    public static class ResTestUriActivity extends FragmentActivity {

        public static Intent createIntent() {
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
            Uri uri = Uri.parse("https://github.com/panpf/androidx/res?" + params);
            return new Intent(Intent.ACTION_VIEW, uri);
        }

    }

    //bean for test
    static class TestParcelable implements Parcelable {
        String tag;

        public TestParcelable(String tag) {
            this.tag = tag;
        }

        protected TestParcelable(Parcel in) {
            tag = in.readString();
        }

        public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
            @Override
            public TestParcelable createFromParcel(Parcel in) {
                return new TestParcelable(in);
            }

            @Override
            public TestParcelable[] newArray(int size) {
                return new TestParcelable[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(tag);
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof TestParcelable) {
                return Stringx.equals(((TestParcelable) obj).tag, this.tag);
            }
            return super.equals(obj);
        }
    }

    static class TestSerializable implements Serializable {
        String tag;

        public TestSerializable(String tag) {
            this.tag = tag;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof TestSerializable) {
                return Stringx.equals(((TestSerializable) obj).tag, this.tag);
            }
            return super.equals(obj);
        }
    }

    static class TestBinder extends Binder {
        String tag;

        public TestBinder(String tag) {
            this.tag = tag;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof TestBinder) {
                return Stringx.equals(((TestBinder) obj).tag, this.tag);
            }
            return super.equals(obj);
        }
    }
}
