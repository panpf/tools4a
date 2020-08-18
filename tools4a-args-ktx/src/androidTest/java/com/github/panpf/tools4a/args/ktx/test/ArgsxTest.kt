package com.github.panpf.tools4a.args.ktx.test

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.args.ktx.*
import kotlinx.android.parcel.Parcelize
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * <P>Created by Vincent on 2018/12/4.</P>
 */
@RunWith(AndroidJUnit4::class)
class ArgsxTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<TestActivity> = object : ActivityTestRule<TestActivity>(TestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val mResActivityTestRule: ActivityTestRule<ResTestActivity> = object : ActivityTestRule<ResTestActivity>(ResTestActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val mNoExtrasActivityTestRule: ActivityTestRule<NoExtraActivity> = object : ActivityTestRule<NoExtraActivity>(NoExtraActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return NoExtraActivity.createIntent(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val uriNoIntentActivityActivityTestRule: ActivityTestRule<TestOnlyUriNoIntentActivity> = object : ActivityTestRule<TestOnlyUriNoIntentActivity>(TestOnlyUriNoIntentActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestOnlyUriNoIntentActivity.createIntentWithUri()
        }
    }

    @get:Rule
    val intentNoUriActivityActivityTestRule: ActivityTestRule<TestOnlyIntentNoUriActivity> = object : ActivityTestRule<TestOnlyIntentNoUriActivity>(TestOnlyIntentNoUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val bothIntentUriActivityActivityTestRule: ActivityTestRule<TestBothIntentUriActivity> = object : ActivityTestRule<TestBothIntentUriActivity>(TestBothIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val noIntentUriActivityActivityTestRule: ActivityTestRule<TestNoIntentUriActivity> = object : ActivityTestRule<TestNoIntentUriActivity>(TestNoIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resUriNoIntentActivityActivityTestRule: ActivityTestRule<ResTestOnlyUriNoIntentActivity> = object : ActivityTestRule<ResTestOnlyUriNoIntentActivity>(ResTestOnlyUriNoIntentActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestOnlyUriNoIntentActivity.createIntentWithUri()
        }
    }

    @get:Rule
    val resIntentNoUriActivityActivityTestRule: ActivityTestRule<ResTestOnlyIntentNoUriActivity> = object : ActivityTestRule<ResTestOnlyIntentNoUriActivity>(ResTestOnlyIntentNoUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestOnlyIntentNoUriActivity.createIntentWithExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resBothIntentUriActivityActivityTestRule: ActivityTestRule<ResTestBothIntentUriActivity> = object : ActivityTestRule<ResTestBothIntentUriActivity>(ResTestBothIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestBothIntentUriActivity.createIntentWithUriAndExtras(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val resNoIntentUriActivityActivityTestRule: ActivityTestRule<ResTestNoIntentUriActivity> = object : ActivityTestRule<ResTestNoIntentUriActivity>(ResTestNoIntentUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestNoIntentUriActivity.createIntentWithNothing(InstrumentationRegistry.getContext())
        }
    }

    @get:Rule
    val uriActivityTestRule: ActivityTestRule<TestUriActivity> = object : ActivityTestRule<TestUriActivity>(TestUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return TestUriActivity.createIntent()
        }
    }

    @get:Rule
    val resUriActivityTestRule: ActivityTestRule<ResTestUriActivity> = object : ActivityTestRule<ResTestUriActivity>(ResTestUriActivity::class.java) {
        override fun getActivityIntent(): Intent {
            return ResTestUriActivity.createIntent()
        }
    }

    @Test
    fun activityIntentArgsTest() {
        val activity = mActivityTestRule.activity

        val byteRequired = activity.readByteArgOr("byteRequired", 0.toByte())
        val byteRequiredErrKey = activity.readByteArgOr("byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = activity.readByteArrayArgOrThrow("byteArrayRequired")
        val byteArrayOptional = activity.readByteArrayArgOrNull("byteArrayOptional")
        val byteArrayOptionalErrKey = activity.readByteArrayArgOrNull("byteArrayOptionalErrKey")
        val byteArrayOrDefault = activity.readByteArrayArgOr("byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = activity.readByteArrayArgOr("byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = activity.readShortArgOr("shortRequired", 0.toShort())
        val shortRequiredErrKey = activity.readShortArgOr("shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = activity.readShortArrayArgOrThrow("shortArrayRequired")
        val shortArrayOptional = activity.readShortArrayArgOrNull("shortArrayOptional")
        val shortArrayOptionalErrKey = activity.readShortArrayArgOrNull("shortArrayOptionalErrKey")
        val shortArrayOrDefault = activity.readShortArrayArgOr("shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = activity.readShortArrayArgOr("shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = activity.readIntArgOr("intRequired", 0)
        val intRequiredErrKey = activity.readIntArgOr("intRequiredErrKey", 0)
        val intArrayRequired = activity.readIntArrayArgOrThrow("intArrayRequired")
        val intArrayOptional = activity.readIntArrayArgOrNull("intArrayOptional")
        val intArrayOptionalErrKey = activity.readIntArrayArgOrNull("intArrayOptionalErrKey")
        val intArrayOrDefault = activity.readIntArrayArgOr("intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = activity.readIntArrayArgOr("intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = activity.readIntArrayListArgOrThrow("intArrayListRequired")
        val intArrayListOrDefault = activity.readIntArrayListArgOr("intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = activity.readIntArrayListArgOr("intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = activity.readIntArrayListArgOrNull("intArrayListOptional")
        val intArrayListOptionalErrKey = activity.readIntArrayListArgOrNull("intArrayListOptionalErrKey")

        val longRequired = activity.readLongArgOr("longRequired", 0)
        val longRequiredErrKey = activity.readLongArgOr("longRequiredErrKey", 0)
        val longArrayRequired = activity.readLongArrayArgOrThrow("longArrayRequired")
        val longArrayOptional = activity.readLongArrayArgOrNull("longArrayOptional")
        val longArrayOptionalErrKey = activity.readLongArrayArgOrNull("longArrayOptionalErrKey")
        val longArrayOrDefault = activity.readLongArrayArgOr("longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = activity.readLongArrayArgOr("longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = activity.readFloatArgOr("floatRequired", 0f)
        val floatRequiredErrKey = activity.readFloatArgOr("floatRequiredErrKey", 0f)
        val floatArrayRequired = activity.readFloatArrayArgOrThrow("floatArrayRequired")
        val floatArrayOptional = activity.readFloatArrayArgOrNull("floatArrayOptional")
        val floatArrayOptionalErrKey = activity.readFloatArrayArgOrNull("DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = activity.readFloatArrayArgOr("floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = activity.readFloatArrayArgOr("floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = activity.readDoubleArgOr("doubleRequired", 0.0)
        val doubleRequiredErrKey = activity.readDoubleArgOr("doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = activity.readDoubleArrayArgOrThrow("doubleArrayRequired")
        val doubleArrayOptional = activity.readDoubleArrayArgOrNull("doubleArrayOptional")
        val doubleArrayOptionalErrKey = activity.readDoubleArrayArgOrNull("doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = activity.readDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = activity.readDoubleArrayArgOr("doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = activity.readBooleanArgOr("booleanRequired", false)
        val booleanRequiredErrKey = activity.readBooleanArgOr("booleanRequiredErrKey", false)
        val booleanArrayRequired = activity.readBooleanArrayArgOrThrow("booleanArrayRequired")
        val booleanArrayOptional = activity.readBooleanArrayArgOrNull("booleanArrayOptional")
        val booleanArrayOptionalErrKey = activity.readBooleanArrayArgOrNull("booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = activity.readBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = activity.readBooleanArrayArgOr("booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = activity.readCharArgOr("charRequired", 'a')
        val charRequiredErrKey = activity.readCharArgOr("charRequiredErrKey", 'b')
        val charArrayRequired = activity.readCharArrayArgOrThrow("charArrayRequired")
        val charArrayOptional = activity.readCharArrayArgOrNull("charArrayOptional")
        val charArrayOptionalErrKey = activity.readCharArrayArgOrNull("charArrayOptionalErrKey")
        val charArrayOrDefault = activity.readCharArrayArgOr("charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = activity.readCharArrayArgOr("charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = activity.readStringArgOr("stringRequired", "stringRequired")
        val stringRequiredErrKey = activity.readStringArgOr("stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = activity.readStringArrayArgOrThrow("stringArrayRequired")
        val stringArrayOptional = activity.readStringArrayArgOrNull("stringArrayOptional")
        val stringArrayOptionalErrKey = activity.readStringArrayArgOrNull("stringArrayOptionalErrKey")
        val stringArrayOrDefault = activity.readStringArrayArgOr("stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = activity.readStringArrayArgOr("stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = activity.readStringArrayListArgOrThrow("stringArrayListRequired")
        val stringArrayListOrDefault = activity.readStringArrayListArgOr("stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = activity.readStringArrayListArgOr("stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = activity.readStringArrayListArgOrNull("stringArrayListOptional")
        val stringArrayListOptionalErrKey = activity.readStringArrayListArgOrNull("stringArrayListOptionalErrKey")

        val charSequenceRequired = activity.readCharSequenceArgOr("charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = activity.readCharSequenceArgOr("charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = activity.readCharSequenceArrayArgOrThrow("charSequenceArrayRequired")
        val charSequenceArrayOptional = activity.readCharSequenceArrayArgOrNull("charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = activity.readCharSequenceArrayArgOrNull("charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = activity.readCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf<CharSequence>("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = activity.readCharSequenceArrayArgOr("charSequenceArrayOrDefaultErrKey", arrayOf<CharSequence>("error", "erk"))
        val charSequenceArrayListRequired = activity.readCharSequenceArrayListArgOrThrow("charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = activity.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = activity.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = activity.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = activity.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptionalErrKey")


        val parcelableRequired = activity.readParcelableArgOr("parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = activity.readParcelableArgOr("parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = activity.readParcelableArrayArgOrThrow<Parcelable>("parcelableArrayRequired")
        val parcelableArrayOptional = activity.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = activity.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = activity.readParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = activity.readParcelableArrayArgOr("parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = activity.readParcelableArrayListArgOrThrow<Parcelable>("parcelableArrayListRequired")
        val parcelableArrayListOrDefault = activity.readParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = activity.readParcelableArrayListArgOr("parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = activity.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = activity.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptionalErrKey")

        val serializableRequired = activity.readSerializableArgOrThrow<TestSerializable>("serializableRequired")
        val serializableOptional = activity.readSerializableArgOrNull<TestSerializable>("serializableOptional")
        val serializableOptionalErrKey = activity.readSerializableArgOrNull<TestSerializable>("serializableOptionalErrKey")
        val serializableOrDefault = activity.readSerializableArgOr("serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = activity.readSerializableArgOr("serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = activity.readBundleArgOrThrow("bundleRequired")
        val bundleOptional = activity.readBundleArgOrNull("bundleOptional")
        val bundleOptionalErrKey = activity.readBundleArgOrNull("bundleOptionalErrKey")
        val bundleOrDefault = activity.readBundleArgOr("bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = activity.readBundleArgOr("bundleOrDefaultErrKey", defaultBundle)

        val extrasRequired = activity.readExtrasArgOrThrow()
        val extrasOptional = activity.readExtrasArgOrNull()
        val extrasDefault = activity.readExtrasArgOr(Bundle())

        //test start
        Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
        Assert.assertTrue(extrasOptional!!.getString("extrasOptional") == "extrasOptional")
        Assert.assertTrue(extrasDefault.getString("extrasOrDefault") == "extrasOrDefault")

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun supportFragmentArgsTest() {
        val activityHost = mActivityTestRule.activity
        val activity = activityHost.supportFragmentManager.findFragmentById(R.id.testAt_frame)!!

        val byteRequired = activity.readByteArgOr("byteRequired", 0.toByte())
        val byteRequiredErrKey = activity.readByteArgOr("byteRequiredErrKey", 0.toByte())
        val byteArrayRequired = activity.readByteArrayArgOrThrow("byteArrayRequired")
        val byteArrayOptional = activity.readByteArrayArgOrNull("byteArrayOptional")
        val byteArrayOptionalErrKey = activity.readByteArrayArgOrNull("byteArrayOptionalErrKey")
        val byteArrayOrDefault = activity.readByteArrayArgOr("byteArrayOrDefault", byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = activity.readByteArrayArgOr("byteArrayOrDefaultErrKey", byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = activity.readShortArgOr("shortRequired", 0.toShort())
        val shortRequiredErrKey = activity.readShortArgOr("shortRequiredErrKey", 0.toShort())
        val shortArrayRequired = activity.readShortArrayArgOrThrow("shortArrayRequired")
        val shortArrayOptional = activity.readShortArrayArgOrNull("shortArrayOptional")
        val shortArrayOptionalErrKey = activity.readShortArrayArgOrNull("shortArrayOptionalErrKey")
        val shortArrayOrDefault = activity.readShortArrayArgOr("shortArrayOrDefault", shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = activity.readShortArrayArgOr("shortArrayOrDefaultErrKey", shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = activity.readIntArgOr("intRequired", 0)
        val intRequiredErrKey = activity.readIntArgOr("intRequiredErrKey", 0)
        val intArrayRequired = activity.readIntArrayArgOrThrow("intArrayRequired")
        val intArrayOptional = activity.readIntArrayArgOrNull("intArrayOptional")
        val intArrayOptionalErrKey = activity.readIntArrayArgOrNull("intArrayOptionalErrKey")
        val intArrayOrDefault = activity.readIntArrayArgOr("intArrayOrDefault", intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = activity.readIntArrayArgOr("intArrayOrDefaultErrKey", intArrayOf(0, -1))
        val intArrayListRequired = activity.readIntArrayListArgOrThrow("intArrayListRequired")
        val intArrayListOrDefault = activity.readIntArrayListArgOr("intArrayListOrDefault", arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = activity.readIntArrayListArgOr("intArrayListOrDefaultErrKey", arrayListOf(0, 0))
        val intArrayListOptional = activity.readIntArrayListArgOrNull("intArrayListOptional")
        val intArrayListOptionalErrKey = activity.readIntArrayListArgOrNull("intArrayListOptionalErrKey")

        val longRequired = activity.readLongArgOr("longRequired", 0)
        val longRequiredErrKey = activity.readLongArgOr("longRequiredErrKey", 0)
        val longArrayRequired = activity.readLongArrayArgOrThrow("longArrayRequired")
        val longArrayOptional = activity.readLongArrayArgOrNull("longArrayOptional")
        val longArrayOptionalErrKey = activity.readLongArrayArgOrNull("longArrayOptionalErrKey")
        val longArrayOrDefault = activity.readLongArrayArgOr("longArrayOrDefault", longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = activity.readLongArrayArgOr("longArrayOrDefaultErrKey", longArrayOf(0, -1))

        val floatRequired = activity.readFloatArgOr("floatRequired", 0f)
        val floatRequiredErrKey = activity.readFloatArgOr("floatRequiredErrKey", 0f)
        val floatArrayRequired = activity.readFloatArrayArgOrThrow("floatArrayRequired")
        val floatArrayOptional = activity.readFloatArrayArgOrNull("floatArrayOptional")
        val floatArrayOptionalErrKey = activity.readFloatArrayArgOrNull("DoubleArrayOptionalErrKey")
        val floatArrayOrDefault = activity.readFloatArrayArgOr("floatArrayOrDefault", floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = activity.readFloatArrayArgOr("floatArrayOrDefaultErrKey", floatArrayOf(0f, -1f))

        val doubleRequired = activity.readDoubleArgOr("doubleRequired", 0.0)
        val doubleRequiredErrKey = activity.readDoubleArgOr("doubleRequiredErrKey", 0.0)
        val doubleArrayRequired = activity.readDoubleArrayArgOrThrow("doubleArrayRequired")
        val doubleArrayOptional = activity.readDoubleArrayArgOrNull("doubleArrayOptional")
        val doubleArrayOptionalErrKey = activity.readDoubleArrayArgOrNull("doubleArrayOptionalErrKey")
        val doubleArrayOrDefault = activity.readDoubleArrayArgOr("doubleArrayOrDefault", doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = activity.readDoubleArrayArgOr("doubleArrayOrDefaultErrKey", doubleArrayOf(0.0, -1.0))

        val booleanRequired = activity.readBooleanArgOr("booleanRequired", false)
        val booleanRequiredErrKey = activity.readBooleanArgOr("booleanRequiredErrKey", false)
        val booleanArrayRequired = activity.readBooleanArrayArgOrThrow("booleanArrayRequired")
        val booleanArrayOptional = activity.readBooleanArrayArgOrNull("booleanArrayOptional")
        val booleanArrayOptionalErrKey = activity.readBooleanArrayArgOrNull("booleanArrayOptionalErrKey")
        val booleanArrayOrDefault = activity.readBooleanArrayArgOr("booleanArrayOrDefault", booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = activity.readBooleanArrayArgOr("booleanArrayOrDefaultErrKey", booleanArrayOf(false, true))

        val charRequired = activity.readCharArgOr("charRequired", 'a')
        val charRequiredErrKey = activity.readCharArgOr("charRequiredErrKey", 'b')
        val charArrayRequired = activity.readCharArrayArgOrThrow("charArrayRequired")
        val charArrayOptional = activity.readCharArrayArgOrNull("charArrayOptional")
        val charArrayOptionalErrKey = activity.readCharArrayArgOrNull("charArrayOptionalErrKey")
        val charArrayOrDefault = activity.readCharArrayArgOr("charArrayOrDefault", charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = activity.readCharArrayArgOr("charArrayOrDefaultErrKey", charArrayOf('b', 'a'))

        val stringRequired = activity.readStringArgOr("stringRequired", "stringRequired")
        val stringRequiredErrKey = activity.readStringArgOr("stringRequiredErrKey", "stringRequiredErrKey")
        val stringArrayRequired = activity.readStringArrayArgOrThrow("stringArrayRequired")
        val stringArrayOptional = activity.readStringArrayArgOrNull("stringArrayOptional")
        val stringArrayOptionalErrKey = activity.readStringArrayArgOrNull("stringArrayOptionalErrKey")
        val stringArrayOrDefault = activity.readStringArrayArgOr("stringArrayOrDefault", arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = activity.readStringArrayArgOr("stringArrayOrDefaultErrKey", arrayOf("error", "erk"))
        val stringArrayListRequired = activity.readStringArrayListArgOrThrow("stringArrayListRequired")
        val stringArrayListOrDefault = activity.readStringArrayListArgOr("stringArrayListOrDefault", arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = activity.readStringArrayListArgOr("stringArrayListOrDefaultErrKey", arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = activity.readStringArrayListArgOrNull("stringArrayListOptional")
        val stringArrayListOptionalErrKey = activity.readStringArrayListArgOrNull("stringArrayListOptionalErrKey")

        val charSequenceRequired = activity.readCharSequenceArgOr("charSequenceRequired", "charSequenceRequired")
        val charSequenceRequiredErrKey = activity.readCharSequenceArgOr("charSequenceRequiredErrKey", "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = activity.readCharSequenceArrayArgOrThrow("charSequenceArrayRequired")
        val charSequenceArrayOptional = activity.readCharSequenceArrayArgOrNull("charSequenceArrayOptional")
        val charSequenceArrayOptionalErrKey = activity.readCharSequenceArrayArgOrNull("charSequenceArrayOptionalErrKey")
        val charSequenceArrayOrDefault = activity.readCharSequenceArrayArgOr("charSequenceArrayOrDefault", arrayOf<CharSequence>("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = activity.readCharSequenceArrayArgOr("charSequenceArrayOrDefaultErrKey", arrayOf<CharSequence>("error", "erk"))
        val charSequenceArrayListRequired = activity.readCharSequenceArrayListArgOrThrow("charSequenceArrayListRequired")
        val charSequenceArrayListOrDefault = activity.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefault", arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = activity.readCharSequenceArrayListArgOr("charSequenceArrayListOrDefaultErrKey", arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = activity.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptional")
        val charSequenceArrayListOptionalErrKey = activity.readCharSequenceArrayListArgOrNull("charSequenceArrayListOptionalErrKey")


        val parcelableRequired = activity.readParcelableArgOr("parcelableRequired", TestParcelable("required"))
        val parcelableRequiredErrKey = activity.readParcelableArgOr("parcelableRequiredErrKey", TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = activity.readParcelableArrayArgOrThrow<Parcelable>("parcelableArrayRequired")
        val parcelableArrayOptional = activity.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptional")
        val parcelableArrayOptionalErrKey = activity.readParcelableArrayArgOrNull<Parcelable>("parcelableArrayOptionalErrKey")
        val parcelableArrayOrDefault = activity.readParcelableArrayArgOr("parcelableArrayOrDefault", arrayOf())
        val parcelableArrayOrDefaultErrKey = activity.readParcelableArrayArgOr("parcelableArrayOrDefaultErrKey", arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = activity.readParcelableArrayListArgOrThrow<Parcelable>("parcelableArrayListRequired")
        val parcelableArrayListOrDefault = activity.readParcelableArrayListArgOr("parcelableArrayListOrDefault", arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = activity.readParcelableArrayListArgOr("parcelableArrayListOrDefaultErrKey", arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = activity.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptional")
        val parcelableArrayListOptionalErrKey = activity.readParcelableArrayListArgOrNull<Parcelable>("parcelableArrayListOptionalErrKey")

        val serializableRequired = activity.readSerializableArgOrThrow<TestSerializable>("serializableRequired")
        val serializableOptional = activity.readSerializableArgOrNull<TestSerializable>("serializableOptional")
        val serializableOptionalErrKey = activity.readSerializableArgOrNull<TestSerializable>("serializableOptionalErrKey")
        val serializableOrDefault = activity.readSerializableArgOr("serializableOrDefault", TestSerializable("default"))
        val serializableOrDefaultErrKey = activity.readSerializableArgOr("serializableOrDefaultErrKey", TestSerializable("errKey"))

        val bundleRequired = activity.readBundleArgOrThrow("bundleRequired")
        val bundleOptional = activity.readBundleArgOrNull("bundleOptional")
        val bundleOptionalErrKey = activity.readBundleArgOrNull("bundleOptionalErrKey")
        val bundleOrDefault = activity.readBundleArgOr("bundleOrDefault", Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = activity.readBundleArgOr("bundleOrDefaultErrKey", defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = activity.readSparseParcelableArrayArgOrThrow<Parcelable>("sparseParcelableArrayRequired")
        val sparseParcelableArrayOptional = activity.readSparseParcelableArrayArgOrNull<Parcelable>("sparseParcelableArrayOptional")
        val sparseParcelableArrayOptionalErrKey = activity.readSparseParcelableArrayArgOrNull<Parcelable>("sparseParcelableArrayOptionalErrKey")
        val sparseParcelableArrayOrDefault = activity.readSparseParcelableArrayArgOr("sparseParcelableArrayOrDefault", sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = activity.readSparseParcelableArrayArgOr("sparseParcelableArrayOrDefaultErrKey", sparseArrayDefault)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val binderRequired = activity.readBinderArgOrThrow("binderRequired")
            val binderOptional = activity.readBinderArgOrNull("binderOptional")
            val binderOptionalErrKey = activity.readBinderArgOrNull("binderOptionalErrKey")
            val binderOrDefault = activity.readBinderArgOr("binderOrDefault", TestBinder(""))
            val binderOrDefaultErrKey = activity.readBinderArgOr("binderOrDefaultErrKey", TestBinder("binderOrDefaultErrKey"))

            Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
            Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
            Assert.assertNull(binderOptionalErrKey)
            Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
            Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val sizeRequired = activity.readSizeArgOrThrow("sizeRequired")
            val sizeOptional = activity.readSizeArgOrNull("sizeOptional")
            val sizeOptionalErrKey = activity.readSizeArgOrNull("sizeOptionalErrKey")
            val sizeOrDefault = activity.readSizeArgOr("sizeOrDefault", Size(0, 0))
            val sizeOrDefaultErrKey = activity.readSizeArgOr("sizeOrDefaultErrKey", Size(4, 4))

            val sizeFRequired = activity.readSizeFArgOrThrow("sizeFRequired")
            val sizeFOptional = activity.readSizeFArgOrNull("sizeFOptional")
            val sizeFOptionalErrKey = activity.readSizeFArgOrNull("sizeFOptionalErrKey")
            val sizeFOrDefault = activity.readSizeFArgOr("sizeFOrDefault", SizeF(0f, 0f))
            val sizeFOrDefaultErrKey = activity.readSizeFArgOr("sizeFOrDefaultErrKey", SizeF(4f, 4f))

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
            Assert.assertNull(sizeFOptionalErrKey)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
            Assert.assertNull(sizeOptionalErrKey)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)
        }


        //test start
        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun noExtrasActivityTest() {
        val activity = mNoExtrasActivityTestRule.activity

        val extrasOptional = activity.readExtrasArgOrNull()
        val bundleDefault = Bundle()
        bundleDefault.putString("errDefault", "default")
        val extrasDefault = activity.readExtrasArgOr(bundleDefault)

        Assert.assertNull(extrasOptional)
        Assert.assertTrue(extrasDefault.getString("errDefault") == "default")

    }

    //res
    @Test
    fun resActivityIntentArgsTest() {
        val activity = mResActivityTestRule.activity

        val byteRequired = activity.readByteArgOr(R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = activity.readByteArgOr(R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = activity.readByteArrayArgOrThrow(R.string.byte_array_required)
        val byteArrayOptional = activity.readByteArrayArgOrNull(R.string.byte_array_optional)
        val byteArrayOptionalErrKey = activity.readByteArrayArgOrNull(R.string.not_exist_key)
        val byteArrayOrDefault = activity.readByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = activity.readByteArrayArgOr(R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = activity.readShortArgOr(R.string.short_required, 0.toShort())
        val shortRequiredErrKey = activity.readShortArgOr(R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = activity.readShortArrayArgOrThrow(R.string.short_array_required)
        val shortArrayOptional = activity.readShortArrayArgOrNull(R.string.short_array_optional)
        val shortArrayOptionalErrKey = activity.readShortArrayArgOrNull(R.string.not_exist_key)
        val shortArrayOrDefault = activity.readShortArrayArgOr(R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = activity.readShortArrayArgOr(R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = activity.readIntArgOr(R.string.int_required, 0)
        val intRequiredErrKey = activity.readIntArgOr(R.string.not_exist_key, 0)
        val intArrayRequired = activity.readIntArrayArgOrThrow(R.string.int_array_required)
        val intArrayOptional = activity.readIntArrayArgOrNull(R.string.int_array_optional)
        val intArrayOptionalErrKey = activity.readIntArrayArgOrNull(R.string.not_exist_key)
        val intArrayOrDefault = activity.readIntArrayArgOr(R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = activity.readIntArrayArgOr(R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = activity.readIntArrayListArgOrThrow(R.string.int_array_list_required)
        val intArrayListOrDefault = activity.readIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = activity.readIntArrayListArgOr(R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = activity.readIntArrayListArgOrNull(R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = activity.readIntArrayListArgOrNull(R.string.not_exist_key)

        val longRequired = activity.readLongArgOr(R.string.long_required, 0)
        val longRequiredErrKey = activity.readLongArgOr(R.string.not_exist_key, 0)
        val longArrayRequired = activity.readLongArrayArgOrThrow(R.string.long_array_required)
        val longArrayOptional = activity.readLongArrayArgOrNull(R.string.long_array_optional)
        val longArrayOptionalErrKey = activity.readLongArrayArgOrNull(R.string.not_exist_key)
        val longArrayOrDefault = activity.readLongArrayArgOr(R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = activity.readLongArrayArgOr(R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = activity.readFloatArgOr(R.string.float_required, 0f)
        val floatRequiredErrKey = activity.readFloatArgOr(R.string.not_exist_key, 0f)
        val floatArrayRequired = activity.readFloatArrayArgOrThrow(R.string.float_array_required)
        val floatArrayOptional = activity.readFloatArrayArgOrNull(R.string.float_array_optional)
        val floatArrayOptionalErrKey = activity.readFloatArrayArgOrNull(R.string.not_exist_key)
        val floatArrayOrDefault = activity.readFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = activity.readFloatArrayArgOr(R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = activity.readDoubleArgOr(R.string.double_required, 0.0)
        val doubleRequiredErrKey = activity.readDoubleArgOr(R.string.not_exist_key, 0.0)
        val doubleArrayRequired = activity.readDoubleArrayArgOrThrow(R.string.double_array_required)
        val doubleArrayOptional = activity.readDoubleArrayArgOrNull(R.string.double_array_optional)
        val doubleArrayOptionalErrKey = activity.readDoubleArrayArgOrNull(R.string.not_exist_key)
        val doubleArrayOrDefault = activity.readDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = activity.readDoubleArrayArgOr(R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = activity.readBooleanArgOr(R.string.boolean_required, false)
        val booleanRequiredErrKey = activity.readBooleanArgOr(R.string.not_exist_key, false)
        val booleanArrayRequired = activity.readBooleanArrayArgOrThrow(R.string.boolean_array_required)
        val booleanArrayOptional = activity.readBooleanArrayArgOrNull(R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = activity.readBooleanArrayArgOrNull(R.string.not_exist_key)
        val booleanArrayOrDefault = activity.readBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = activity.readBooleanArrayArgOr(R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = activity.readCharArgOr(R.string.char_required, 'a')
        val charRequiredErrKey = activity.readCharArgOr(R.string.not_exist_key, 'b')
        val charArrayRequired = activity.readCharArrayArgOrThrow(R.string.char_array_required)
        val charArrayOptional = activity.readCharArrayArgOrNull(R.string.char_array_optional)
        val charArrayOptionalErrKey = activity.readCharArrayArgOrNull(R.string.not_exist_key)
        val charArrayOrDefault = activity.readCharArrayArgOr(R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = activity.readCharArrayArgOr(R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = activity.readStringArgOr(R.string.string_required, "stringRequired")
        val stringRequiredErrKey = activity.readStringArgOr(R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = activity.readStringArrayArgOrThrow(R.string.string_array_required)
        val stringArrayOptional = activity.readStringArrayArgOrNull(R.string.string_array_optional)
        val stringArrayOptionalErrKey = activity.readStringArrayArgOrNull(R.string.not_exist_key)
        val stringArrayOrDefault = activity.readStringArrayArgOr(R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = activity.readStringArrayArgOr(R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = activity.readStringArrayListArgOrThrow(R.string.string_array_list_required)
        val stringArrayListOrDefault = activity.readStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = activity.readStringArrayListArgOr(R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = activity.readStringArrayListArgOrNull(R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = activity.readStringArrayListArgOrNull(R.string.not_exist_key)

        val charSequenceRequired = activity.readCharSequenceArgOr(R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = activity.readCharSequenceArgOr(R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = activity.readCharSequenceArrayArgOrThrow(R.string.char_sequence_array_required)
        val charSequenceArrayOptional = activity.readCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = activity.readCharSequenceArrayArgOrNull(R.string.not_exist_key)
        val charSequenceArrayOrDefault = activity.readCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf<CharSequence>("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = activity.readCharSequenceArrayArgOr(R.string.not_exist_key, arrayOf<CharSequence>("error", "erk"))
        val charSequenceArrayListRequired = activity.readCharSequenceArrayListArgOrThrow(R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = activity.readCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = activity.readCharSequenceArrayListArgOr(R.string.not_exist_key, arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = activity.readCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = activity.readCharSequenceArrayListArgOrNull(R.string.not_exist_key)


        val parcelableRequired = activity.readParcelableArgOr(R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = activity.readParcelableArgOr(R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = activity.readParcelableArrayArgOrThrow<Parcelable>(R.string.parcelable_array_required)
        val parcelableArrayOptional = activity.readParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = activity.readParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
        val parcelableArrayOrDefault = activity.readParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = activity.readParcelableArrayArgOr(R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = activity.readParcelableArrayListArgOrThrow<Parcelable>(R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = activity.readParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = activity.readParcelableArrayListArgOr(R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = activity.readParcelableArrayListArgOrNull<Parcelable>(R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = activity.readParcelableArrayListArgOrNull<Parcelable>(R.string.not_exist_key)

        val serializableRequired = activity.readSerializableArgOrThrow<TestSerializable>(R.string.serializable_required)
        val serializableOptional = activity.readSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)
        val serializableOptionalErrKey = activity.readSerializableArgOrNull<TestSerializable>(R.string.not_exist_key)
        val serializableOrDefault = activity.readSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = activity.readSerializableArgOr(R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = activity.readBundleArgOrThrow(R.string.bundle_required)
        val bundleOptional = activity.readBundleArgOrNull(R.string.bundle_optional)
        val bundleOptionalErrKey = activity.readBundleArgOrNull(R.string.not_exist_key)
        val bundleOrDefault = activity.readBundleArgOr(R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = activity.readBundleArgOr(R.string.not_exist_key, defaultBundle)

        val extrasRequired = activity.readExtrasArgOrThrow()
        val extrasOptional = activity.readExtrasArgOrNull()
        val extrasDefault = activity.readExtrasArgOr(Bundle())

        //test start
        Assert.assertTrue(extrasRequired.getString("extrasRequired") == "extrasRequired")
        Assert.assertTrue(extrasOptional!!.getString("extrasOptional") == "extrasOptional")
        Assert.assertTrue(extrasDefault.getString("extrasOrDefault") == "extrasOrDefault")

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    @Test
    fun resSupportFragmentArgsTest() {
        val hostActivity = mResActivityTestRule.activity
        val activity = hostActivity.supportFragmentManager.findFragmentById(R.id.testAt_frame)!!

        val byteRequired = activity.readByteArgOr(R.string.byte_required, 0.toByte())
        val byteRequiredErrKey = activity.readByteArgOr(R.string.not_exist_key, 0.toByte())
        val byteArrayRequired = activity.readByteArrayArgOrThrow(R.string.byte_array_required)
        val byteArrayOptional = activity.readByteArrayArgOrNull(R.string.byte_array_optional)
        val byteArrayOptionalErrKey = activity.readByteArrayArgOrNull(R.string.not_exist_key)
        val byteArrayOrDefault = activity.readByteArrayArgOr(R.string.byte_array_or_default, byteArrayOf(0.toByte(), 0.toByte()))
        val byteArrayOrDefaultErrKey = activity.readByteArrayArgOr(R.string.not_exist_key, byteArrayOf(0.toByte(), (-1).toByte()))

        val shortRequired = activity.readShortArgOr(R.string.short_required, 0.toShort())
        val shortRequiredErrKey = activity.readShortArgOr(R.string.not_exist_key, 0.toShort())
        val shortArrayRequired = activity.readShortArrayArgOrThrow(R.string.short_array_required)
        val shortArrayOptional = activity.readShortArrayArgOrNull(R.string.short_array_optional)
        val shortArrayOptionalErrKey = activity.readShortArrayArgOrNull(R.string.not_exist_key)
        val shortArrayOrDefault = activity.readShortArrayArgOr(R.string.short_array_or_default, shortArrayOf(0.toShort(), 0.toShort()))
        val shortArrayOrDefaultErrKey = activity.readShortArrayArgOr(R.string.not_exist_key, shortArrayOf(0.toShort(), (-1).toShort()))


        val intRequired = activity.readIntArgOr(R.string.int_required, 0)
        val intRequiredErrKey = activity.readIntArgOr(R.string.not_exist_key, 0)
        val intArrayRequired = activity.readIntArrayArgOrThrow(R.string.int_array_required)
        val intArrayOptional = activity.readIntArrayArgOrNull(R.string.int_array_optional)
        val intArrayOptionalErrKey = activity.readIntArrayArgOrNull(R.string.not_exist_key)
        val intArrayOrDefault = activity.readIntArrayArgOr(R.string.int_array_or_default, intArrayOf(0, 0))
        val intArrayOrDefaultErrKey = activity.readIntArrayArgOr(R.string.not_exist_key, intArrayOf(0, -1))
        val intArrayListRequired = activity.readIntArrayListArgOrThrow(R.string.int_array_list_required)
        val intArrayListOrDefault = activity.readIntArrayListArgOr(R.string.int_array_list_or_default, arrayListOf(0, 0))
        val intArrayListOrDefaultErrKey = activity.readIntArrayListArgOr(R.string.not_exist_key, arrayListOf(0, 0))
        val intArrayListOptional = activity.readIntArrayListArgOrNull(R.string.int_array_list_optional)
        val intArrayListOptionalErrKey = activity.readIntArrayListArgOrNull(R.string.not_exist_key)

        val longRequired = activity.readLongArgOr(R.string.long_required, 0)
        val longRequiredErrKey = activity.readLongArgOr(R.string.not_exist_key, 0)
        val longArrayRequired = activity.readLongArrayArgOrThrow(R.string.long_array_required)
        val longArrayOptional = activity.readLongArrayArgOrNull(R.string.long_array_optional)
        val longArrayOptionalErrKey = activity.readLongArrayArgOrNull(R.string.not_exist_key)
        val longArrayOrDefault = activity.readLongArrayArgOr(R.string.long_array_or_default, longArrayOf(0, 0))
        val longArrayOrDefaultErrKey = activity.readLongArrayArgOr(R.string.not_exist_key, longArrayOf(0, -1))

        val floatRequired = activity.readFloatArgOr(R.string.float_required, 0f)
        val floatRequiredErrKey = activity.readFloatArgOr(R.string.not_exist_key, 0f)
        val floatArrayRequired = activity.readFloatArrayArgOrThrow(R.string.float_array_required)
        val floatArrayOptional = activity.readFloatArrayArgOrNull(R.string.float_array_optional)
        val floatArrayOptionalErrKey = activity.readFloatArrayArgOrNull(R.string.not_exist_key)
        val floatArrayOrDefault = activity.readFloatArrayArgOr(R.string.float_array_or_default, floatArrayOf(0f, 0f))
        val floatArrayOrDefaultErrKey = activity.readFloatArrayArgOr(R.string.not_exist_key, floatArrayOf(0f, -1f))

        val doubleRequired = activity.readDoubleArgOr(R.string.double_required, 0.0)
        val doubleRequiredErrKey = activity.readDoubleArgOr(R.string.not_exist_key, 0.0)
        val doubleArrayRequired = activity.readDoubleArrayArgOrThrow(R.string.double_array_required)
        val doubleArrayOptional = activity.readDoubleArrayArgOrNull(R.string.double_array_optional)
        val doubleArrayOptionalErrKey = activity.readDoubleArrayArgOrNull(R.string.not_exist_key)
        val doubleArrayOrDefault = activity.readDoubleArrayArgOr(R.string.double_array_or_default, doubleArrayOf(0.0, 0.0))
        val doubleArrayOrDefaultErrKey = activity.readDoubleArrayArgOr(R.string.not_exist_key, doubleArrayOf(0.0, -1.0))

        val booleanRequired = activity.readBooleanArgOr(R.string.boolean_required, false)
        val booleanRequiredErrKey = activity.readBooleanArgOr(R.string.not_exist_key, false)
        val booleanArrayRequired = activity.readBooleanArrayArgOrThrow(R.string.boolean_array_required)
        val booleanArrayOptional = activity.readBooleanArrayArgOrNull(R.string.boolean_array_optional)
        val booleanArrayOptionalErrKey = activity.readBooleanArrayArgOrNull(R.string.not_exist_key)
        val booleanArrayOrDefault = activity.readBooleanArrayArgOr(R.string.boolean_array_or_default, booleanArrayOf(true, false))
        val booleanArrayOrDefaultErrKey = activity.readBooleanArrayArgOr(R.string.not_exist_key, booleanArrayOf(false, true))

        val charRequired = activity.readCharArgOr(R.string.char_required, 'a')
        val charRequiredErrKey = activity.readCharArgOr(R.string.not_exist_key, 'b')
        val charArrayRequired = activity.readCharArrayArgOrThrow(R.string.char_array_required)
        val charArrayOptional = activity.readCharArrayArgOrNull(R.string.char_array_optional)
        val charArrayOptionalErrKey = activity.readCharArrayArgOrNull(R.string.not_exist_key)
        val charArrayOrDefault = activity.readCharArrayArgOr(R.string.char_array_or_default, charArrayOf('a', 'b'))
        val charArrayOrDefaultErrKey = activity.readCharArrayArgOr(R.string.not_exist_key, charArrayOf('b', 'a'))

        val stringRequired = activity.readStringArgOr(R.string.string_required, "stringRequired")
        val stringRequiredErrKey = activity.readStringArgOr(R.string.not_exist_key, "stringRequiredErrKey")
        val stringArrayRequired = activity.readStringArrayArgOrThrow(R.string.string_array_required)
        val stringArrayOptional = activity.readStringArrayArgOrNull(R.string.string_array_optional)
        val stringArrayOptionalErrKey = activity.readStringArrayArgOrNull(R.string.not_exist_key)
        val stringArrayOrDefault = activity.readStringArrayArgOr(R.string.string_array_or_default, arrayOf("array", "dft"))
        val stringArrayOrDefaultErrKey = activity.readStringArrayArgOr(R.string.not_exist_key, arrayOf("error", "erk"))
        val stringArrayListRequired = activity.readStringArrayListArgOrThrow(R.string.string_array_list_required)
        val stringArrayListOrDefault = activity.readStringArrayListArgOr(R.string.string_array_list_or_default, arrayListOf("list", "default"))
        val stringArrayListOrDefaultErrKey = activity.readStringArrayListArgOr(R.string.not_exist_key, arrayListOf("stringArrayListOrDefaultErrKey", "errKey"))
        val stringArrayListOptional = activity.readStringArrayListArgOrNull(R.string.string_array_list_optional)
        val stringArrayListOptionalErrKey = activity.readStringArrayListArgOrNull(R.string.not_exist_key)

        val charSequenceRequired = activity.readCharSequenceArgOr(R.string.char_sequence_required, "charSequenceRequired")
        val charSequenceRequiredErrKey = activity.readCharSequenceArgOr(R.string.not_exist_key, "charSequenceRequiredErrKey")
        val charSequenceArrayRequired = activity.readCharSequenceArrayArgOrThrow(R.string.char_sequence_array_required)
        val charSequenceArrayOptional = activity.readCharSequenceArrayArgOrNull(R.string.char_sequence_array_optional)
        val charSequenceArrayOptionalErrKey = activity.readCharSequenceArrayArgOrNull(R.string.not_exist_key)
        val charSequenceArrayOrDefault = activity.readCharSequenceArrayArgOr(R.string.char_sequence_array_or_default, arrayOf<CharSequence>("array", "dft"))
        val charSequenceArrayOrDefaultErrKey = activity.readCharSequenceArrayArgOr(R.string.not_exist_key, arrayOf<CharSequence>("error", "erk"))
        val charSequenceArrayListRequired = activity.readCharSequenceArrayListArgOrThrow(R.string.char_sequence_array_list_required)
        val charSequenceArrayListOrDefault = activity.readCharSequenceArrayListArgOr(R.string.char_sequence_array_list_or_default, arrayListOf<CharSequence>("list", "default"))
        val charSequenceArrayListOrDefaultErrKey = activity.readCharSequenceArrayListArgOr(R.string.not_exist_key, arrayListOf<CharSequence>("charSequenceArrayListOrDefaultErrKey", "errKey"))
        val charSequenceArrayListOptional = activity.readCharSequenceArrayListArgOrNull(R.string.char_sequence_array_list_optional)
        val charSequenceArrayListOptionalErrKey = activity.readCharSequenceArrayListArgOrNull(R.string.not_exist_key)


        val parcelableRequired = activity.readParcelableArgOr(R.string.parcelable_required, TestParcelable("required"))
        val parcelableRequiredErrKey = activity.readParcelableArgOr(R.string.not_exist_key, TestParcelable("parcelableRequiredErrKey"))
        val parcelableArrayRequired = activity.readParcelableArrayArgOrThrow<Parcelable>(R.string.parcelable_array_required)
        val parcelableArrayOptional = activity.readParcelableArrayArgOrNull<Parcelable>(R.string.parcelable_array_optional)
        val parcelableArrayOptionalErrKey = activity.readParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
        val parcelableArrayOrDefault = activity.readParcelableArrayArgOr(R.string.parcelable_array_or_default, arrayOf())
        val parcelableArrayOrDefaultErrKey = activity.readParcelableArrayArgOr(R.string.not_exist_key, arrayOf(TestParcelable("error"), TestParcelable("erk")))
        val parcelableArrayListRequired = activity.readParcelableArrayListArgOrThrow<Parcelable>(R.string.parcelable_array_list_required)
        val parcelableArrayListOrDefault = activity.readParcelableArrayListArgOr(R.string.parcelable_array_list_or_default, arrayListOf<Parcelable>(TestParcelable("list"), TestParcelable("default")))
        val parcelableArrayListOrDefaultErrKey = activity.readParcelableArrayListArgOr(R.string.not_exist_key, arrayListOf<Parcelable>(TestParcelable("parcelableArrayListOrDefaultErrKey"), TestParcelable("errKey")))
        val parcelableArrayListOptional = activity.readParcelableArrayListArgOrNull<Parcelable>(R.string.parcelable_array_list_optional)
        val parcelableArrayListOptionalErrKey = activity.readParcelableArrayListArgOrNull<Parcelable>(R.string.not_exist_key)

        val serializableRequired = activity.readSerializableArgOrThrow<TestSerializable>(R.string.serializable_required)
        val serializableOptional = activity.readSerializableArgOrNull<TestSerializable>(R.string.serializable_optional)
        val serializableOptionalErrKey = activity.readSerializableArgOrNull<TestSerializable>(R.string.not_exist_key)
        val serializableOrDefault = activity.readSerializableArgOr(R.string.serializable_or_default, TestSerializable("default"))
        val serializableOrDefaultErrKey = activity.readSerializableArgOr(R.string.not_exist_key, TestSerializable("errKey"))

        val bundleRequired = activity.readBundleArgOrThrow(R.string.bundle_required)
        val bundleOptional = activity.readBundleArgOrNull(R.string.bundle_optional)
        val bundleOptionalErrKey = activity.readBundleArgOrNull(R.string.not_exist_key)
        val bundleOrDefault = activity.readBundleArgOr(R.string.bundle_or_default, Bundle())
        val defaultBundle = Bundle()
        defaultBundle.putString("bundle", "bundleErrKey")
        val bundleOrDefaultErrKey = activity.readBundleArgOr(R.string.not_exist_key, defaultBundle)


        val sparseArrayDefault = SparseArray<Parcelable>()
        sparseArrayDefault.put(0, TestParcelable("0"))
        val sparseParcelableArrayRequired = activity.readSparseParcelableArrayArgOrThrow<Parcelable>(R.string.sparse_parcelable_array_required)
        val sparseParcelableArrayOptional = activity.readSparseParcelableArrayArgOrNull<Parcelable>(R.string.sparse_parcelable_array_optional)
        val sparseParcelableArrayOptionalErrKey = activity.readSparseParcelableArrayArgOrNull<Parcelable>(R.string.not_exist_key)
        val sparseParcelableArrayOrDefault = activity.readSparseParcelableArrayArgOr(R.string.sparse_parcelable_array_or_default, sparseArrayDefault)
        val sparseParcelableArrayOrDefaultErrKey = activity.readSparseParcelableArrayArgOr(R.string.not_exist_key, sparseArrayDefault)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            val binderRequired = activity.readBinderArgOrThrow(R.string.binder_required)
            val binderOptional = activity.readBinderArgOrNull(R.string.binder_optional)
            val binderOptionalErrKey = activity.readBinderArgOrNull(R.string.not_exist_key)
            val binderOrDefault = activity.readBinderArgOr(R.string.binder_or_default, TestBinder(""))
            val binderOrDefaultErrKey = activity.readBinderArgOr(R.string.not_exist_key, TestBinder("binderOrDefaultErrKey"))

            Assert.assertEquals(binderRequired, TestBinder("binderRequired"))
            Assert.assertEquals(binderOptional, TestBinder("binderOptional"))
            Assert.assertNull(binderOptionalErrKey)
            Assert.assertEquals(binderOrDefault, TestBinder("binderOrDefault"))
            Assert.assertEquals(binderOrDefaultErrKey, TestBinder("binderOrDefaultErrKey"))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val sizeRequired = activity.readSizeArgOrThrow(R.string.size_required)
            val sizeOptional = activity.readSizeArgOrNull(R.string.size_optional)
            val sizeOptionalErrKey = activity.readSizeArgOrNull(R.string.not_exist_key)
            val sizeOrDefault = activity.readSizeArgOr(R.string.size_or_default, Size(0, 0))
            val sizeOrDefaultErrKey = activity.readSizeArgOr(R.string.not_exist_key, Size(4, 4))

            val sizeFRequired = activity.readSizeFArgOrThrow(R.string.sizeF_required)
            val sizeFOptional = activity.readSizeFArgOrNull(R.string.sizeF_optional)
            val sizeFOptionalErrKey = activity.readSizeFArgOrNull(R.string.not_exist_key)
            val sizeFOrDefault = activity.readSizeFArgOr(R.string.sizeF_or_default, SizeF(0f, 0f))
            val sizeFOrDefaultErrKey = activity.readSizeFArgOr(R.string.not_exist_key, SizeF(4f, 4f))

            Assert.assertTrue(sizeFRequired.width == 1f && sizeFRequired.height == 1f)
            Assert.assertTrue(sizeFOptional!!.width == 2f && sizeFOptional.height == 2f)
            Assert.assertNull(sizeFOptionalErrKey)
            Assert.assertTrue(sizeFOrDefault.width == 3f && sizeFOrDefault.height == 3f)
            Assert.assertTrue(sizeFOrDefaultErrKey.width == 4f && sizeFOrDefaultErrKey.height == 4f)

            Assert.assertTrue(sizeRequired.width == 1 && sizeRequired.height == 1)
            Assert.assertTrue(sizeOptional!!.width == 2 && sizeOptional.height == 2)
            Assert.assertNull(sizeOptionalErrKey)
            Assert.assertTrue(sizeOrDefault.width == 3 && sizeOrDefault.height == 3)
            Assert.assertTrue(sizeOrDefaultErrKey.width == 4 && sizeOrDefaultErrKey.height == 4)
        }


        //test start

        Assert.assertTrue(sparseParcelableArrayRequired.get(-1) == TestParcelable("-1") && sparseParcelableArrayRequired.get(1) == TestParcelable("1"))
        Assert.assertTrue(sparseParcelableArrayOptional!!.get(-2) == TestParcelable("-2") && sparseParcelableArrayOptional.get(2) == TestParcelable("2"))
        Assert.assertNull(sparseParcelableArrayOptionalErrKey)
        Assert.assertTrue(sparseParcelableArrayOrDefault.get(-3) == TestParcelable("-3") && sparseParcelableArrayOrDefault.get(3) == TestParcelable("3"))
        Assert.assertTrue(sparseParcelableArrayOrDefaultErrKey.get(0) == TestParcelable("0"))

        Assert.assertTrue(bundleRequired.getString("bundle") == "bundleRequired")
        Assert.assertTrue(bundleOptional!!.getString("bundle") == "bundleOptional")
        Assert.assertNull(bundleOptionalErrKey)
        Assert.assertTrue(bundleOrDefault.getString("bundle") == "bundleOrDefault")
        Assert.assertTrue(bundleOrDefaultErrKey.getString("bundle") == "bundleErrKey")


        Assert.assertEquals(serializableRequired, TestSerializable("serializableRequired"))
        Assert.assertEquals(serializableOptional, TestSerializable("serializableOptional"))
        Assert.assertNull(serializableOptionalErrKey)
        Assert.assertEquals(serializableOrDefault, TestSerializable("serializableOrDefault"))
        Assert.assertEquals(serializableOrDefaultErrKey, TestSerializable("errKey"))

        Assert.assertEquals(parcelableRequired, TestParcelable("parcelableRequired"))
        Assert.assertEquals(parcelableRequiredErrKey, TestParcelable("parcelableRequiredErrKey"))
        Assert.assertTrue(parcelableArrayRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayOptionalErrKey)
        Assert.assertTrue(parcelableArrayOrDefault[0] == TestParcelable("parcelableArrayOrDefault") && parcelableArrayOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayOrDefaultErrKey[0] == TestParcelable("error") && parcelableArrayOrDefaultErrKey[1] == TestParcelable("erk"))
        Assert.assertTrue(parcelableArrayListRequired[0] == TestParcelable("parcelableRequired") && parcelableArrayListRequired[1] == TestParcelable("parcelableOptional"))
        Assert.assertTrue(parcelableArrayListOptional!![0] == TestParcelable("parcelableOptional") && parcelableArrayListOptional[1] == TestParcelable("parcelableRequired"))
        Assert.assertNull(parcelableArrayListOptionalErrKey)
        Assert.assertTrue(parcelableArrayListOrDefault[0] == TestParcelable("parcelableArrayListOrDefault") && parcelableArrayListOrDefault[1] == TestParcelable("default"))
        Assert.assertTrue(parcelableArrayListOrDefaultErrKey[0] == TestParcelable("parcelableArrayListOrDefaultErrKey") && parcelableArrayListOrDefaultErrKey[1] == TestParcelable("errKey"))

        Assert.assertTrue(charSequenceRequired == "stringRequired")
        Assert.assertTrue(charSequenceRequiredErrKey == "charSequenceRequiredErrKey")
        Assert.assertTrue(charSequenceArrayRequired[0] == "stringRequired" && charSequenceArrayRequired[1] == "stringOptional")
        Assert.assertTrue(charSequenceArrayOptional!![0] == "stringOptional" && charSequenceArrayOptional[1] == "stringRequired")
        Assert.assertNull(charSequenceArrayOptionalErrKey)
        Assert.assertTrue(charSequenceArrayOrDefault[0] == "charSequence" && charSequenceArrayOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayOrDefaultErrKey[0] == "error" && charSequenceArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(charSequenceArrayListRequired[0] == "charSequenceArrayListRequired" && charSequenceArrayListRequired[1] == "required")
        Assert.assertTrue(charSequenceArrayListOrDefault[0] == "charSequenceArrayListOrDefault" && charSequenceArrayListOrDefault[1] == "default")
        Assert.assertTrue(charSequenceArrayListOrDefaultErrKey[0] == "charSequenceArrayListOrDefaultErrKey" && charSequenceArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(charSequenceArrayListOptional!![0] == "charSequenceArrayListOptional" && charSequenceArrayListOptional[1] == "optional")
        Assert.assertNull(charSequenceArrayListOptionalErrKey)

        Assert.assertTrue(stringRequired == "stringRequired")
        Assert.assertTrue(stringRequiredErrKey == "stringRequiredErrKey")
        Assert.assertTrue(stringArrayRequired[0] == "stringRequired" && stringArrayRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayOptional!![0] == "stringOptional" && stringArrayOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayOptionalErrKey)
        Assert.assertTrue(stringArrayOrDefault[0] == "stringArrayOrDefault" && stringArrayOrDefault[1] == "default")
        Assert.assertTrue(stringArrayOrDefaultErrKey[0] == "error" && stringArrayOrDefaultErrKey[1] == "erk")
        Assert.assertTrue(stringArrayListRequired[0] == "stringRequired" && stringArrayListRequired[1] == "stringOptional")
        Assert.assertTrue(stringArrayListOrDefault[0] == "stringArrayListOrDefault" && stringArrayListOrDefault[1] == "default")
        Assert.assertTrue(stringArrayListOrDefaultErrKey[0] == "stringArrayListOrDefaultErrKey" && stringArrayListOrDefaultErrKey[1] == "errKey")
        Assert.assertTrue(stringArrayListOptional!![0] == "stringOptional" && stringArrayListOptional[1] == "stringRequired")
        Assert.assertNull(stringArrayListOptionalErrKey)

        Assert.assertTrue(byteRequired == 2.toByte())
        Assert.assertTrue(byteRequiredErrKey.toInt() == 0)
        Assert.assertTrue(byteArrayRequired[0] == 2.toByte() && byteArrayRequired[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOptional!![0] == (-2).toByte() && byteArrayOptional[1] == 2.toByte())
        Assert.assertNull(byteArrayOptionalErrKey)
        Assert.assertTrue(byteArrayOrDefault[0] == 2.toByte() && byteArrayOrDefault[1] == (-2).toByte())
        Assert.assertTrue(byteArrayOrDefaultErrKey[0] == 0.toByte() && byteArrayOrDefaultErrKey[1] == (-1).toByte())

        Assert.assertTrue(shortRequired == 3.toShort())
        Assert.assertTrue(shortRequiredErrKey.toInt() == 0)
        Assert.assertTrue(shortArrayRequired[0] == 3.toShort() && shortArrayRequired[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOptional!![0] == (-3).toShort() && shortArrayOptional[1] == 3.toShort())
        Assert.assertNull(shortArrayOptionalErrKey)
        Assert.assertTrue(shortArrayOrDefault[0] == 3.toShort() && shortArrayOrDefault[1] == (-3).toShort())
        Assert.assertTrue(shortArrayOrDefaultErrKey[0] == 0.toShort() && shortArrayOrDefaultErrKey[1] == (-1).toShort())

        Assert.assertTrue(intRequired == 500)
        Assert.assertTrue(intRequiredErrKey == 0)
        Assert.assertTrue(intArrayRequired[0] == 500 && intArrayRequired[1] == -500)
        Assert.assertTrue(intArrayOptional!![0] == -500 && intArrayOptional[1] == 500)
        Assert.assertNull(intArrayOptionalErrKey)
        Assert.assertTrue(intArrayOrDefault[0] == 500 && intArrayOrDefault[1] == -500)
        Assert.assertTrue(intArrayOrDefaultErrKey[0] == 0 && intArrayOrDefaultErrKey[1] == -1)
        Assert.assertTrue(intArrayListRequired[0] == 500 && intArrayListRequired[1] == -500)
        Assert.assertTrue(intArrayListOrDefault[0] == 600 && intArrayListOrDefault[1] == -600)
        Assert.assertTrue(intArrayListOrDefaultErrKey[0] == 0 && intArrayListOrDefaultErrKey[1] == 0)
        Assert.assertTrue(intArrayListOptional!![0] == -500 && intArrayListOptional[1] == 500)
        Assert.assertNull(intArrayListOptionalErrKey)

        Assert.assertTrue(longRequired == 1000L)
        Assert.assertTrue(longRequiredErrKey == 0L)
        Assert.assertTrue(longArrayRequired[0] == 1000L && longArrayRequired[1] == -1000L)
        Assert.assertTrue(longArrayOptional!![0] == -1000L && longArrayOptional[1] == 1000L)
        Assert.assertNull(longArrayOptionalErrKey)
        Assert.assertTrue(longArrayOrDefault[0] == 1000L && longArrayOrDefault[1] == -1000L)
        Assert.assertTrue(longArrayOrDefaultErrKey[0] == 0L && longArrayOrDefaultErrKey[1] == (-1).toLong())

        Assert.assertTrue(floatRequired == 4f)
        Assert.assertTrue(floatRequiredErrKey == 0f)
        Assert.assertTrue(floatArrayRequired[0] == 4f && floatArrayRequired[1] == -4f)
        Assert.assertTrue(floatArrayOptional!![0] == -4f && floatArrayOptional[1] == 4f)
        Assert.assertNull(floatArrayOptionalErrKey)
        Assert.assertTrue(floatArrayOrDefault[0] == 4f && floatArrayOrDefault[1] == -4f)
        Assert.assertTrue(floatArrayOrDefaultErrKey[0] == 0f && floatArrayOrDefaultErrKey[1] == -1f)

        Assert.assertTrue(doubleRequired == 6.0)
        Assert.assertTrue(doubleRequiredErrKey == 0.0)
        Assert.assertTrue(doubleArrayRequired[0] == 6.0 && doubleArrayRequired[1] == -6.0)
        Assert.assertTrue(doubleArrayOptional!![0] == -6.0 && doubleArrayOptional[1] == 6.0)
        Assert.assertNull(doubleArrayOptionalErrKey)
        Assert.assertTrue(doubleArrayOrDefault[0] == 6.0 && doubleArrayOrDefault[1] == -6.0)
        Assert.assertTrue(doubleArrayOrDefaultErrKey[0] == 0.0 && doubleArrayOrDefaultErrKey[1] == -1.0)

        Assert.assertTrue(booleanRequired)
        Assert.assertTrue(!booleanRequiredErrKey)
        Assert.assertTrue(booleanArrayRequired[0] && !booleanArrayRequired[1])
        Assert.assertTrue(!booleanArrayOptional!![0] && booleanArrayOptional[1])
        Assert.assertNull(booleanArrayOptionalErrKey)
        Assert.assertTrue(booleanArrayOrDefault[0] && !booleanArrayOrDefault[1])
        Assert.assertTrue(!booleanArrayOrDefaultErrKey[0] && booleanArrayOrDefaultErrKey[1])

        Assert.assertTrue(charRequired == 'a')
        Assert.assertTrue(charRequiredErrKey == 'b')
        Assert.assertTrue(charArrayRequired[0] == 'a' && charArrayRequired[1] == 'b')
        Assert.assertTrue(charArrayOptional!![0] == 'b' && charArrayOptional[1] == 'a')
        Assert.assertNull(charArrayOptionalErrKey)
        Assert.assertTrue(charArrayOrDefault[0] == 'a' && charArrayOrDefault[1] == 'b')
        Assert.assertTrue(charArrayOrDefaultErrKey[0] == 'b' && charArrayOrDefaultErrKey[1] == 'a')
    }

    //uri || intent
    @Test
    fun uriOnlyActivityTest() {
        val activity = uriNoIntentActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr("intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr("longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr("floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow("stringIntentUriRequired")
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull("stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull("stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr("byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr("shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr("intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr("longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr("floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr("doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr("booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow("stringUriIntentRequired")
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr("stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull("stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull("stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == 1.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 2.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 3)
        Assert.assertTrue(longIntentUriOrDefault == 4L)
        Assert.assertTrue(floatIntentUriOrDefault == 5f)
        Assert.assertTrue(doubleIntentUriOrDefault == 6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentUriRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentUriOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentUriOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun intentOnlyActivityTest() {
        val activity = intentNoUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr("intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr("longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr("floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow("stringIntentUriRequired")
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull("stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull("stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr("byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr("shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr("intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr("longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr("floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr("doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr("booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow("stringUriIntentRequired")
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr("stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull("stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull("stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == (-11).toByte())
        Assert.assertTrue(shortUriIntentOrDefault == (-12).toShort())
        Assert.assertTrue(intUriIntentOrDefault == -13)
        Assert.assertTrue(longUriIntentOrDefault == -14L)
        Assert.assertTrue(floatUriIntentOrDefault == -15f)
        Assert.assertTrue(doubleUriIntentOrDefault == -16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun bothUriIntentActivityTest() {
        val activity = bothIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr("intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr("longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr("floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow("stringIntentUriRequired")
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull("stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull("stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr("byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr("shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr("intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr("longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr("floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr("doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr("booleanUriIntentOrDefault", false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow("stringUriIntentRequired")
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr("stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull("stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull("stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun noUriIntentActivityTest() {
        val activity = noIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr("byteIntentUriOrDefault", 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr("shortIntentUriOrDefault", 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr("intIntentUriOrDefault", 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr("longIntentUriOrDefault", 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr("floatIntentUriOrDefault", 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr("doubleIntentUriOrDefault", 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr("booleanIntentUriOrDefault", false)
        //        String stringIntentUriRequired = activity.readStringIntentUriArgOrThrow("stringIntentUriRequired");
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr("stringIntentUriOrDefault", "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr("stringIntentUriOrDefaultErrKey", "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull("stringIntentUriOptional")
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull("stringIntentUriOptionalErrKey")

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr("byteUriIntentOrDefault", 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr("shortUriIntentOrDefault", 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr("intUriIntentOrDefault", 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr("longUriIntentOrDefault", 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr("floatUriIntentOrDefault", 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr("doubleUriIntentOrDefault", 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr("booleanUriIntentOrDefault", false)
        //        String stringUriIntentRequired = activity.readStringUriIntentArgOrThrow("stringUriIntentRequired");
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr("stringUriIntentOrDefault", "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr("stringUriIntentOrDefaultErrKey", "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull("stringUriIntentOptional")
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull("stringUriIntentOptionalErrKey")

        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.0)
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.0)
        Assert.assertTrue(!booleanUriIntentOrDefault)
        Assert.assertNull(stringUriIntentOptional)
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    //res
    @Test
    fun resUriOnlyActivityTest() {
        val activity = resUriNoIntentActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow(R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr(R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr(R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull(R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull(R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr(R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr(R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr(R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr(R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr(R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr(R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr(R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow(R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull(R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == 1.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 2.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 3)
        Assert.assertTrue(longIntentUriOrDefault == 4L)
        Assert.assertTrue(floatIntentUriOrDefault == 5f)
        Assert.assertTrue(doubleIntentUriOrDefault == 6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentUriRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentUriOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentUriOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun resIntentOnlyActivityTest() {
        val activity = intentNoUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow(R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr(R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr(R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull(R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull(R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr(R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr(R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr(R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr(R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr(R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr(R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr(R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow(R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull(R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == (-11).toByte())
        Assert.assertTrue(shortUriIntentOrDefault == (-12).toShort())
        Assert.assertTrue(intUriIntentOrDefault == -13)
        Assert.assertTrue(longUriIntentOrDefault == -14L)
        Assert.assertTrue(floatUriIntentOrDefault == -15f)
        Assert.assertTrue(doubleUriIntentOrDefault == -16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun resBothUriIntentActivityTest() {
        val activity = resBothIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)
        val stringIntentUriRequired = activity.readStringIntentUriArgOrThrow(R.string.string_intent_uri_required)
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr(R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr(R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull(R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull(R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr(R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr(R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr(R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr(R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr(R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr(R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr(R.string.boolean_uri_intent_or_default, false)
        val stringUriIntentRequired = activity.readStringUriIntentArgOrThrow(R.string.string_uri_intent_required)
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull(R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == (-1).toByte())
        Assert.assertTrue(shortIntentUriOrDefault == (-2).toShort())
        Assert.assertTrue(intIntentUriOrDefault == -3)
        Assert.assertTrue(longIntentUriOrDefault == -4L)
        Assert.assertTrue(floatIntentUriOrDefault == -5f)
        Assert.assertTrue(doubleIntentUriOrDefault == -6.0)
        Assert.assertTrue(booleanIntentUriOrDefault)
        Assert.assertTrue(stringIntentUriRequired == "stringIntentRequired")
        Assert.assertTrue(stringIntentUriOptional == "stringIntentOptional")
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "stringIntentOrDefault")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 11.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 12.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 13)
        Assert.assertTrue(longUriIntentOrDefault == 14L)
        Assert.assertTrue(floatUriIntentOrDefault == 15f)
        Assert.assertTrue(doubleUriIntentOrDefault == 16.0)
        Assert.assertTrue(booleanUriIntentOrDefault)
        Assert.assertTrue(stringUriIntentRequired == "stringUriIntentRequired")
        Assert.assertTrue(stringUriIntentOptional == "stringUriIntentOptional")
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "stringUriIntentOrDefault")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    @Test
    fun resNoUriIntentActivityTest() {
        val activity = noIntentUriActivityActivityTestRule.activity

        val byteIntentUriOrDefault = activity.readByteIntentUriArgOr(R.string.byte_intent_uri_or_default, 0.toByte())
        val shortIntentUriOrDefault = activity.readShortIntentUriArgOr(R.string.short_intent_uri_or_default, 0.toShort())
        val intIntentUriOrDefault = activity.readIntIntentUriArgOr(R.string.int_intent_uri_or_default, 0)
        val longIntentUriOrDefault = activity.readLongIntentUriArgOr(R.string.long_intent_uri_or_default, 0L)
        val floatIntentUriOrDefault = activity.readFloatIntentUriArgOr(R.string.float_intent_uri_or_default, 0.toFloat())
        val doubleIntentUriOrDefault = activity.readDoubleIntentUriArgOr(R.string.double_intent_uri_or_default, 0.toDouble())
        val booleanIntentUriOrDefault = activity.readBooleanIntentUriArgOr(R.string.boolean_intent_uri_or_default, false)
        //        String stringIntentUriRequired = activity.readStringIntentUriArgOrThrow(R.string.string_intent_uri_required);
        val stringIntentUriOrDefault = activity.readStringIntentUriArgOr(R.string.string_intent_uri_or_default, "default")
        val stringIntentUriOrDefaultErrKey = activity.readStringIntentUriArgOr(R.string.not_exist_key, "stringIntentUriOrDefaultErrKey")
        val stringIntentUriOptional = activity.readStringIntentUriArgOrNull(R.string.string_intent_uri_optional)
        val stringIntentUriOptionalErrKey = activity.readStringIntentUriArgOrNull(R.string.not_exist_key)

        //uri intent
        val byteUriIntentOrDefault = activity.readByteUriIntentArgOr(R.string.byte_uri_intent_or_default, 0.toByte())
        val shortUriIntentOrDefault = activity.readShortUriIntentArgOr(R.string.short_uri_intent_or_default, 0.toShort())
        val intUriIntentOrDefault = activity.readIntUriIntentArgOr(R.string.int_uri_intent_or_default, 0)
        val longUriIntentOrDefault = activity.readLongUriIntentArgOr(R.string.long_uri_intent_or_default, 0L)
        val floatUriIntentOrDefault = activity.readFloatUriIntentArgOr(R.string.float_uri_intent_or_default, 0.toFloat())
        val doubleUriIntentOrDefault = activity.readDoubleUriIntentArgOr(R.string.double_uri_intent_or_default, 0.toDouble())
        val booleanUriIntentOrDefault = activity.readBooleanUriIntentArgOr(R.string.boolean_uri_intent_or_default, false)
        //        String stringUriIntentRequired = activity.readStringUriIntentArgOrThrow(R.string.string_uri_intent_required);
        val stringUriIntentOrDefault = activity.readStringUriIntentArgOr(R.string.string_uri_intent_or_default, "default")
        val stringUriIntentOrDefaultErrKey = activity.readStringUriIntentArgOr(R.string.not_exist_key, "stringUriIntentOrDefaultErrKey")
        val stringUriIntentOptional = activity.readStringUriIntentArgOrNull(R.string.string_uri_intent_optional)
        val stringUriIntentOptionalErrKey = activity.readStringUriIntentArgOrNull(R.string.not_exist_key)

        Assert.assertTrue(byteIntentUriOrDefault == 0.toByte())
        Assert.assertTrue(shortIntentUriOrDefault == 0.toShort())
        Assert.assertTrue(intIntentUriOrDefault == 0)
        Assert.assertTrue(longIntentUriOrDefault == 0L)
        Assert.assertTrue(floatIntentUriOrDefault == 0f)
        Assert.assertTrue(doubleIntentUriOrDefault == 0.0)
        Assert.assertTrue(!booleanIntentUriOrDefault)
        Assert.assertNull(stringIntentUriOptional)
        Assert.assertNull(stringIntentUriOptionalErrKey)
        Assert.assertTrue(stringIntentUriOrDefault == "default")
        Assert.assertTrue(stringIntentUriOrDefaultErrKey == "stringIntentUriOrDefaultErrKey")

        //Activity Uri Intent
        Assert.assertTrue(byteUriIntentOrDefault == 0.toByte())
        Assert.assertTrue(shortUriIntentOrDefault == 0.toShort())
        Assert.assertTrue(intUriIntentOrDefault == 0)
        Assert.assertTrue(longUriIntentOrDefault == 0L)
        Assert.assertTrue(floatUriIntentOrDefault == 0f)
        Assert.assertTrue(doubleUriIntentOrDefault == 0.0)
        Assert.assertTrue(!booleanUriIntentOrDefault)
        Assert.assertNull(stringUriIntentOptional)
        Assert.assertNull(stringUriIntentOptionalErrKey)
        Assert.assertTrue(stringUriIntentOrDefault == "default")
        Assert.assertTrue(stringUriIntentOrDefaultErrKey == "stringUriIntentOrDefaultErrKey")
    }

    //activity uri
    @Test
    fun uriActivityTest() {
        val activity = uriActivityTestRule.activity

        val byteUriRequired = activity.readByteUriArgOrThrow("byteUriRequired")
        val byteUriOptional = activity.readByteUriArgOrNull("byteUriOptional")
        val byteUriOptionalErrKey = activity.readByteUriArgOrNull("byteUriOptionalErrKey")
        val byteUriOrDefault = activity.readByteUriArgOr("byteUriOrDefault", 0.toByte())
        val byteUriOrDefaultErrKey = activity.readByteUriArgOr("byteUriOrDefaultErrKey", 0.toByte())

        val shortUriRequired = activity.readShortUriArgOrThrow("shortUriRequired")
        val shortUriOptional = activity.readShortUriArgOrNull("shortUriOptional")
        val shortUriOptionalErrKey = activity.readShortUriArgOrNull("shortUriOptionalErrKey")
        val shortUriOrDefault = activity.readShortUriArgOr("shortUriOrDefault", 0.toShort())
        val shortUriOrDefaultErrKey = activity.readShortUriArgOr("shortUriOrDefaultErrKey", 0.toShort())

        val intUriRequired = activity.readIntUriArgOrThrow("intUriRequired")
        val intUriOptional = activity.readIntUriArgOrNull("intUriOptional")
        val intUriOptionalErrKey = activity.readIntUriArgOrNull("intUriOptionalErrKey")
        val intUriOrDefault = activity.readIntUriArgOr("intUriOrDefault", 0)
        val intUriOrDefaultErrKey = activity.readIntUriArgOr("intUriOrDefaultErrKey", 0)

        val longUriRequired = activity.readLongUriArgOrThrow("longUriRequired")
        val longUriOptional = activity.readLongUriArgOrNull("longUriOptional")
        val longUriOptionalErrKey = activity.readLongUriArgOrNull("longUriOptionalErrKey")
        val longUriOrDefault = activity.readLongUriArgOr("longUriOrDefault", 0L)
        val longUriOrDefaultErrKey = activity.readLongUriArgOr("longUriOrDefaultErrKey", 0L)

        val floatUriRequired = activity.readFloatUriArgOrThrow("floatUriRequired")
        val floatUriOptional = activity.readFloatUriArgOrNull("floatUriOptional")
        val floatUriOptionalErrKey = activity.readFloatUriArgOrNull("floatUriOptionalErrKey")
        val floatUriOrDefault = activity.readFloatUriArgOr("floatUriOrDefault", 0f)
        val floatUriOrDefaultErrKey = activity.readFloatUriArgOr("floatUriOrDefaultErrKey", -1f)

        val doubleUriRequired = activity.readDoubleUriArgOrThrow("doubleUriRequired")
        val doubleUriOptional = activity.readDoubleUriArgOrNull("doubleUriOptional")
        val doubleUriOptionalErrKey = activity.readDoubleUriArgOrNull("doubleUriOptionalErrKey")
        val doubleUriOrDefault = activity.readDoubleUriArgOr("doubleUriOrDefault", 1.0)
        val doubleUriOrDefaultErrKey = activity.readDoubleUriArgOr("doubleUriOrDefaultErrKey", -1.0)

        val booleanUriRequired = activity.readBooleanUriArgOrThrow("booleanUriRequired")
        val booleanUriOptional = activity.readBooleanUriArgOrNull("booleanUriOptional")
        val booleanUriOptionalErrKey = activity.readBooleanUriArgOrNull("booleanUriOptionalErrKey")
        val booleanUriOrDefault = activity.readBooleanUriArgOr("booleanUriOrDefault", true)
        val booleanUriOrDefaultErrKey = activity.readBooleanUriArgOr("booleanUriOrDefaultErrKey", false)

        val stringUriRequired = activity.readStringUriArgOrThrow("stringUriRequired")
        val stringUriOptional = activity.readStringUriArgOrNull("stringUriOptional")
        val stringUriOptionalErrKey = activity.readStringUriArgOrNull("stringUriOptionalErrKey")
        val stringUriOrDefault = activity.readStringUriArgOr("stringUriOrDefault", "")
        val stringUriOrDefaultErrKey = activity.readStringUriArgOr("stringUriOrDefaultErrKey", "stringUriOrDefaultErrKey")

        Assert.assertNull(byteUriOptionalErrKey)
        Assert.assertNull(shortUriOptionalErrKey)
        Assert.assertNull(intUriOptionalErrKey)
        Assert.assertNull(longUriOptionalErrKey)
        Assert.assertNull(floatUriOptionalErrKey)
        Assert.assertNull(doubleUriOptionalErrKey)
        Assert.assertNull(booleanUriOptionalErrKey)
        Assert.assertNull(stringUriOptionalErrKey)

        Assert.assertTrue(byteUriRequired == 1.toByte())
        Assert.assertTrue(byteUriOptional == (-1).toByte())
        Assert.assertTrue(byteUriOrDefault == 2.toByte())
        Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

        Assert.assertTrue(shortUriRequired == 3.toShort())
        Assert.assertTrue(shortUriOptional == (-3).toShort())
        Assert.assertTrue(shortUriOrDefault == 4.toShort())
        Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

        Assert.assertTrue(intUriRequired == 5)
        Assert.assertTrue(intUriOptional == -5)
        Assert.assertTrue(intUriOrDefault == 6)
        Assert.assertTrue(intUriOrDefaultErrKey == 0)

        Assert.assertTrue(longUriRequired == 7L)
        Assert.assertTrue(longUriOptional == -7L)
        Assert.assertTrue(longUriOrDefault == 8L)
        Assert.assertTrue(longUriOrDefaultErrKey == 0L)

        Assert.assertTrue(floatUriRequired == 9f)
        Assert.assertTrue(floatUriOptional == -9f)
        Assert.assertTrue(floatUriOrDefault == 10f)
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

        Assert.assertTrue(doubleUriRequired == 11.0)
        Assert.assertTrue(doubleUriOptional == -11.0)
        Assert.assertTrue(doubleUriOrDefault == 12.0)
        Assert.assertTrue(doubleUriOrDefaultErrKey == -1.0)

        Assert.assertTrue(booleanUriRequired)
        Assert.assertTrue(booleanUriOptional!!)
        Assert.assertTrue(!booleanUriOrDefault)
        Assert.assertTrue(!booleanUriOrDefaultErrKey)

        Assert.assertTrue(stringUriRequired == "stringUriRequired")
        Assert.assertTrue(stringUriOptional == "stringUriOptional")
        Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
        Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")

    }

    @Test
    fun resUriActivityTest() {
        val activity = resUriActivityTestRule.activity

        val byteUriRequired = activity.readByteUriArgOrThrow(R.string.byte_uri_required)
        val byteUriOptional = activity.readByteUriArgOrNull(R.string.byte_uri_optional)
        val byteUriOptionalErrKey = activity.readByteUriArgOrNull(R.string.not_exist_key)
        val byteUriOrDefault = activity.readByteUriArgOr(R.string.byte_uri_or_default, 0.toByte())
        val byteUriOrDefaultErrKey = activity.readByteUriArgOr(R.string.not_exist_key, 0.toByte())

        val shortUriRequired = activity.readShortUriArgOrThrow(R.string.short_uri_required)
        val shortUriOptional = activity.readShortUriArgOrNull(R.string.short_uri_optional)
        val shortUriOptionalErrKey = activity.readShortUriArgOrNull(R.string.not_exist_key)
        val shortUriOrDefault = activity.readShortUriArgOr(R.string.short_uri_or_default, 0.toShort())
        val shortUriOrDefaultErrKey = activity.readShortUriArgOr(R.string.not_exist_key, 0.toShort())

        val intUriRequired = activity.readIntUriArgOrThrow(R.string.int_uri_required)
        val intUriOptional = activity.readIntUriArgOrNull(R.string.int_uri_optional)
        val intUriOptionalErrKey = activity.readIntUriArgOrNull(R.string.not_exist_key)
        val intUriOrDefault = activity.readIntUriArgOr(R.string.int_uri_or_default, 0)
        val intUriOrDefaultErrKey = activity.readIntUriArgOr(R.string.not_exist_key, 0)

        val longUriRequired = activity.readLongUriArgOrThrow(R.string.long_uri_required)
        val longUriOptional = activity.readLongUriArgOrNull(R.string.long_uri_optional)
        val longUriOptionalErrKey = activity.readLongUriArgOrNull(R.string.not_exist_key)
        val longUriOrDefault = activity.readLongUriArgOr(R.string.long_uri_or_default, 0L)
        val longUriOrDefaultErrKey = activity.readLongUriArgOr(R.string.not_exist_key, 0L)

        val floatUriRequired = activity.readFloatUriArgOrThrow(R.string.float_uri_required)
        val floatUriOptional = activity.readFloatUriArgOrNull(R.string.float_uri_optional)
        val floatUriOptionalErrKey = activity.readFloatUriArgOrNull(R.string.not_exist_key)
        val floatUriOrDefault = activity.readFloatUriArgOr(R.string.float_uri_or_default, 0f)
        val floatUriOrDefaultErrKey = activity.readFloatUriArgOr(R.string.not_exist_key, -1f)

        val doubleUriRequired = activity.readDoubleUriArgOrThrow(R.string.double_uri_required)
        val doubleUriOptional = activity.readDoubleUriArgOrNull(R.string.double_uri_optional)
        val doubleUriOptionalErrKey = activity.readDoubleUriArgOrNull(R.string.not_exist_key)
        val doubleUriOrDefault = activity.readDoubleUriArgOr(R.string.double_uri_or_default, 1.0)
        val doubleUriOrDefaultErrKey = activity.readDoubleUriArgOr(R.string.not_exist_key, -1.0)

        val booleanUriRequired = activity.readBooleanUriArgOrThrow(R.string.boolean_uri_required)
        val booleanUriOptional = activity.readBooleanUriArgOrNull(R.string.boolean_uri_optional)
        val booleanUriOptionalErrKey = activity.readBooleanUriArgOrNull(R.string.not_exist_key)
        val booleanUriOrDefault = activity.readBooleanUriArgOr(R.string.boolean_uri_or_default, true)
        val booleanUriOrDefaultErrKey = activity.readBooleanUriArgOr(R.string.not_exist_key, false)

        val stringUriRequired = activity.readStringUriArgOrThrow(R.string.string_uri_required)
        val stringUriOptional = activity.readStringUriArgOrNull(R.string.string_uri_optional)
        val stringUriOptionalErrKey = activity.readStringUriArgOrNull(R.string.not_exist_key)
        val stringUriOrDefault = activity.readStringUriArgOr(R.string.string_uri_or_default, "")
        val stringUriOrDefaultErrKey = activity.readStringUriArgOr(R.string.not_exist_key, "stringUriOrDefaultErrKey")

        Assert.assertNull(byteUriOptionalErrKey)
        Assert.assertNull(shortUriOptionalErrKey)
        Assert.assertNull(intUriOptionalErrKey)
        Assert.assertNull(longUriOptionalErrKey)
        Assert.assertNull(floatUriOptionalErrKey)
        Assert.assertNull(doubleUriOptionalErrKey)
        Assert.assertNull(booleanUriOptionalErrKey)
        Assert.assertNull(stringUriOptionalErrKey)

        Assert.assertTrue(byteUriRequired == 1.toByte())
        Assert.assertTrue(byteUriOptional == (-1).toByte())
        Assert.assertTrue(byteUriOrDefault == 2.toByte())
        Assert.assertTrue(byteUriOrDefaultErrKey == 0.toByte())

        Assert.assertTrue(shortUriRequired == 3.toShort())
        Assert.assertTrue(shortUriOptional == (-3).toShort())
        Assert.assertTrue(shortUriOrDefault == 4.toShort())
        Assert.assertTrue(shortUriOrDefaultErrKey == 0.toShort())

        Assert.assertTrue(intUriRequired == 5)
        Assert.assertTrue(intUriOptional == -5)
        Assert.assertTrue(intUriOrDefault == 6)
        Assert.assertTrue(intUriOrDefaultErrKey == 0)

        Assert.assertTrue(longUriRequired == 7L)
        Assert.assertTrue(longUriOptional == -7L)
        Assert.assertTrue(longUriOrDefault == 8L)
        Assert.assertTrue(longUriOrDefaultErrKey == 0L)

        Assert.assertTrue(floatUriRequired == 9f)
        Assert.assertTrue(floatUriOptional == -9f)
        Assert.assertTrue(floatUriOrDefault == 10f)
        Assert.assertTrue(floatUriOrDefaultErrKey == -1f)

        Assert.assertTrue(doubleUriRequired == 11.0)
        Assert.assertTrue(doubleUriOptional == -11.0)
        Assert.assertTrue(doubleUriOrDefault == 12.0)
        Assert.assertTrue(doubleUriOrDefaultErrKey == -1.0)

        Assert.assertTrue(booleanUriRequired)
        Assert.assertTrue(booleanUriOptional!!)
        Assert.assertTrue(!booleanUriOrDefault)
        Assert.assertTrue(!booleanUriOrDefaultErrKey)

        Assert.assertTrue(stringUriRequired == "stringUriRequired")
        Assert.assertTrue(stringUriOptional == "stringUriOptional")
        Assert.assertTrue(stringUriOrDefault == "stringUriOrDefault")
        Assert.assertTrue(stringUriOrDefaultErrKey == "stringUriOrDefaultErrKey")

    }


    ///////////////////////////////////////////////////////////////////////////
    // class for test used
    ///////////////////////////////////////////////////////////////////////////

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_test)
            val supportFragment = TestSupportFragment.newInstance(intent.extras)
            supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()
        }

        companion object {

            fun createIntent(context: Context): Intent {
                val starter = Intent(context, TestActivity::class.java)
                starter.putExtra("byteRequired", 2.toByte())
                starter.putExtra("byteArrayRequired", byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra("byteArrayOptional", byteArrayOf((-2).toByte(), 2.toByte()))


                starter.putExtra("shortRequired", 3.toShort())
                starter.putExtra("shortArrayRequired", shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra("shortArrayOptional", shortArrayOf((-3).toShort(), 3.toShort()))

                starter.putExtra("intRequired", 500)
                starter.putExtra("intArrayRequired", intArrayOf(500, -500))
                starter.putExtra("intArrayOptional", intArrayOf(-500, 500))
                starter.putIntegerArrayListExtra("intArrayListRequired", arrayListOf(500, -500))
                starter.putIntegerArrayListExtra("intArrayListOptional", arrayListOf(-500, 500))

                starter.putExtra("longRequired", 1000L)
                starter.putExtra("longArrayRequired", longArrayOf(1000L, -1000L))
                starter.putExtra("longArrayOptional", longArrayOf(-1000L, 1000L))

                starter.putExtra("floatRequired", 4f)
                starter.putExtra("floatArrayRequired", floatArrayOf(4f, -4f))
                starter.putExtra("floatArrayOptional", floatArrayOf(-4f, 4f))


                starter.putExtra("doubleRequired", 6.0)
                starter.putExtra("doubleArrayRequired", doubleArrayOf(6.0, -6.0))
                starter.putExtra("doubleArrayOptional", doubleArrayOf(-6.0, 6.0))


                starter.putExtra("booleanRequired", true)
                starter.putExtra("booleanArrayRequired", booleanArrayOf(true, false))
                starter.putExtra("booleanArrayOptional", booleanArrayOf(false, true))


                starter.putExtra("charRequired", 'a')
                starter.putExtra("charArrayRequired", charArrayOf('a', 'b'))
                starter.putExtra("charArrayOptional", charArrayOf('b', 'a'))

                starter.putExtra("stringRequired", "stringRequired")
                starter.putExtra("stringOptional", "stringOptional")
                starter.putExtra("stringArrayRequired", arrayOf("stringRequired", "stringOptional"))
                starter.putExtra("stringArrayOptional", arrayOf("stringOptional", "stringRequired"))
                starter.putStringArrayListExtra("stringArrayListRequired", arrayListOf("stringRequired", "stringOptional"))
                starter.putStringArrayListExtra("stringArrayListOptional", arrayListOf("stringOptional", "stringRequired"))

                starter.putExtra("charSequenceRequired", "stringRequired")
                starter.putExtra("charSequenceOptional", "stringOptional")
                starter.putExtra("charSequenceArrayRequired", arrayOf("stringRequired", "stringOptional"))
                starter.putExtra("charSequenceArrayOptional", arrayOf("stringOptional", "stringRequired"))


                starter.putExtra("parcelableRequired", TestParcelable("parcelableRequired"))
                starter.putExtra("parcelableOptional", TestParcelable("parcelableOptional"))
                starter.putExtra("parcelableArrayRequired", arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putExtra("parcelableArrayOptional", arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                starter.putParcelableArrayListExtra("parcelableArrayListRequired", arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putParcelableArrayListExtra("parcelableArrayListOptional", arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))


                starter.putExtra("serializableRequired", TestSerializable("serializableRequired"))
                starter.putExtra("serializableOptional", TestSerializable("serializableOptional"))

                starter.putExtra("byteArrayOrDefault", byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra("shortArrayOrDefault", shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra("intArrayOrDefault", intArrayOf(500, -500))
                starter.putIntegerArrayListExtra("intArrayListOrDefault", arrayListOf(600, -600))
                starter.putExtra("longArrayOrDefault", longArrayOf(1000L, -1000L))
                starter.putExtra("floatArrayOrDefault", floatArrayOf(4f, -4f))
                starter.putExtra("doubleArrayOrDefault", doubleArrayOf(6.0, -6.0))
                starter.putExtra("booleanArrayOrDefault", booleanArrayOf(true, false))
                starter.putExtra("charArrayOrDefault", charArrayOf('a', 'b'))
                starter.putExtra("charSequenceOrDefault", "charSequenceOrDefault")
                starter.putExtra("charSequenceArrayOrDefault", arrayOf("charSequence", "default"))


                starter.putCharSequenceArrayListExtra("charSequenceArrayListRequired", arrayListOf("charSequenceArrayListRequired", "required"))
                starter.putCharSequenceArrayListExtra("charSequenceArrayListOptional", arrayListOf("charSequenceArrayListOptional", "optional"))
                starter.putCharSequenceArrayListExtra("charSequenceArrayListOrDefault", arrayListOf("charSequenceArrayListOrDefault", "default"))

                starter.putExtra("stringOrDefault", "stringOrDefault")
                starter.putExtra("stringArrayOrDefault", arrayOf("stringArrayOrDefault", "default"))
                starter.putExtra("stringArrayListOrDefault", arrayListOf("stringArrayListOrDefault", "default"))

                starter.putExtra("parcelableOrDefault", TestParcelable("parcelableOrDefault"))
                starter.putExtra("parcelableArrayOrDefault", arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                starter.putParcelableArrayListExtra("parcelableArrayListOrDefault", arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                starter.putExtra("serializableOrDefault", TestSerializable("serializableOrDefault"))

                val bundleDefault = Bundle()
                bundleDefault.putString("bundle", "bundleOrDefault")
                starter.putExtra("bundleOrDefault", bundleDefault)

                val bundleRequired = Bundle()
                bundleRequired.putString("bundle", "bundleRequired")
                starter.putExtra("bundleRequired", bundleRequired)

                val bundleOptional = Bundle()
                bundleOptional.putString("bundle", "bundleOptional")
                starter.putExtra("bundleOptional", bundleOptional)

                val b = Bundle()
                b.putString("extrasRequired", "extrasRequired")
                b.putString("extrasOptional", "extrasOptional")
                b.putString("extrasOrDefault", "extrasOrDefault")
                starter.putExtras(b)

                val args = Bundle()
                val sparseParcelableArrayRequired = SparseArray<Parcelable>()
                sparseParcelableArrayRequired.put(-1, TestParcelable("-1"))
                sparseParcelableArrayRequired.put(1, TestParcelable("1"))
                args.putSparseParcelableArray("sparseParcelableArrayRequired", sparseParcelableArrayRequired)

                val sparseParcelableArrayOptional = SparseArray<Parcelable>()
                sparseParcelableArrayOptional.put(-2, TestParcelable("-2"))
                sparseParcelableArrayOptional.put(2, TestParcelable("2"))
                args.putSparseParcelableArray("sparseParcelableArrayOptional", sparseParcelableArrayOptional)

                val sparseParcelableArrayOrDefault = SparseArray<Parcelable>()
                sparseParcelableArrayOrDefault.put(-3, TestParcelable("-3"))
                sparseParcelableArrayOrDefault.put(3, TestParcelable("3"))
                args.putSparseParcelableArray("sparseParcelableArrayOrDefault", sparseParcelableArrayOrDefault)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    args.putBinder("binderRequired", TestBinder("binderRequired"))
                    args.putBinder("binderOptional", TestBinder("binderOptional"))
                    args.putBinder("binderOrDefault", TestBinder("binderOrDefault"))
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    args.putSize("sizeRequired", Size(1, 1))
                    args.putSize("sizeOptional", Size(2, 2))
                    args.putSize("sizeOrDefault", Size(3, 3))

                    args.putSizeF("sizeFRequired", SizeF(1f, 1f))
                    args.putSizeF("sizeFOptional", SizeF(2f, 2f))
                    args.putSizeF("sizeFOrDefault", SizeF(3f, 3f))
                }

                starter.putExtras(args)

                return starter
            }
        }
    }

    class TestSupportFragment : androidx.fragment.app.Fragment() {
        companion object {
            fun newInstance(args: Bundle?): TestSupportFragment {
                val fragment = TestSupportFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }

    //res
    class ResTestActivity : androidx.fragment.app.FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_test)
            val supportFragment = ResTestSupportFragment.newInstance(intent.extras)
            supportFragmentManager.beginTransaction().replace(R.id.testAt_frame, supportFragment).commit()
        }

        companion object {

            fun createIntent(context: Context): Intent {
                val starter = Intent(context, ResTestActivity::class.java)
                starter.putExtra(context.getString(R.string.byte_required), 2.toByte())
                starter.putExtra(context.getString(R.string.byte_array_required), byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra(context.getString(R.string.byte_array_optional), byteArrayOf((-2).toByte(), 2.toByte()))


                starter.putExtra(context.getString(R.string.short_required), 3.toShort())
                starter.putExtra(context.getString(R.string.short_array_required), shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra(context.getString(R.string.short_array_optional), shortArrayOf((-3).toShort(), 3.toShort()))

                starter.putExtra(context.getString(R.string.int_required), 500)
                starter.putExtra(context.getString(R.string.int_array_required), intArrayOf(500, -500))
                starter.putExtra(context.getString(R.string.int_array_optional), intArrayOf(-500, 500))
                starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_required), arrayListOf(500, -500))
                starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_optional), arrayListOf(-500, 500))

                starter.putExtra(context.getString(R.string.long_required), 1000L)
                starter.putExtra(context.getString(R.string.long_array_required), longArrayOf(1000L, -1000L))
                starter.putExtra(context.getString(R.string.long_array_optional), longArrayOf(-1000L, 1000L))

                starter.putExtra(context.getString(R.string.float_required), 4f)
                starter.putExtra(context.getString(R.string.float_array_required), floatArrayOf(4f, -4f))
                starter.putExtra(context.getString(R.string.float_array_optional), floatArrayOf(-4f, 4f))


                starter.putExtra(context.getString(R.string.double_required), 6.0)
                starter.putExtra(context.getString(R.string.double_array_required), doubleArrayOf(6.0, -6.0))
                starter.putExtra(context.getString(R.string.double_array_optional), doubleArrayOf(-6.0, 6.0))


                starter.putExtra(context.getString(R.string.boolean_required), true)
                starter.putExtra(context.getString(R.string.boolean_array_required), booleanArrayOf(true, false))
                starter.putExtra(context.getString(R.string.boolean_array_optional), booleanArrayOf(false, true))


                starter.putExtra(context.getString(R.string.char_required), 'a')
                starter.putExtra(context.getString(R.string.char_array_required), charArrayOf('a', 'b'))
                starter.putExtra(context.getString(R.string.char_array_optional), charArrayOf('b', 'a'))

                starter.putExtra(context.getString(R.string.string_required), "stringRequired")
                starter.putExtra(context.getString(R.string.string_optional), "stringOptional")
                starter.putExtra(context.getString(R.string.string_array_required), arrayOf("stringRequired", "stringOptional"))
                starter.putExtra(context.getString(R.string.string_array_optional), arrayOf("stringOptional", "stringRequired"))
                starter.putStringArrayListExtra(context.getString(R.string.string_array_list_required), arrayListOf("stringRequired", "stringOptional"))
                starter.putStringArrayListExtra(context.getString(R.string.string_array_list_optional), arrayListOf("stringOptional", "stringRequired"))

                starter.putExtra(context.getString(R.string.char_sequence_required), "stringRequired")
                starter.putExtra(context.getString(R.string.char_sequence_optional), "stringOptional")
                starter.putExtra(context.getString(R.string.char_sequence_array_required), arrayOf("stringRequired", "stringOptional"))
                starter.putExtra(context.getString(R.string.char_sequence_array_optional), arrayOf("stringOptional", "stringRequired"))


                starter.putExtra(context.getString(R.string.parcelable_required), TestParcelable("parcelableRequired"))
                starter.putExtra(context.getString(R.string.parcelable_optional), TestParcelable("parcelableOptional"))
                starter.putExtra(context.getString(R.string.parcelable_array_required), arrayOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putExtra(context.getString(R.string.parcelable_array_optional), arrayOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))
                starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_required), arrayListOf(TestParcelable("parcelableRequired"), TestParcelable("parcelableOptional")))
                starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_optional), arrayListOf(TestParcelable("parcelableOptional"), TestParcelable("parcelableRequired")))


                starter.putExtra(context.getString(R.string.serializable_required), TestSerializable("serializableRequired"))
                starter.putExtra(context.getString(R.string.serializable_optional), TestSerializable("serializableOptional"))

                starter.putExtra(context.getString(R.string.byte_array_or_default), byteArrayOf(2.toByte(), (-2).toByte()))
                starter.putExtra(context.getString(R.string.short_array_or_default), shortArrayOf(3.toShort(), (-3).toShort()))
                starter.putExtra(context.getString(R.string.int_array_or_default), intArrayOf(500, -500))
                starter.putIntegerArrayListExtra(context.getString(R.string.int_array_list_or_default), arrayListOf(600, -600))
                starter.putExtra(context.getString(R.string.long_array_or_default), longArrayOf(1000L, -1000L))
                starter.putExtra(context.getString(R.string.float_array_or_default), floatArrayOf(4f, -4f))
                starter.putExtra(context.getString(R.string.double_array_or_default), doubleArrayOf(6.0, -6.0))
                starter.putExtra(context.getString(R.string.boolean_array_or_default), booleanArrayOf(true, false))
                starter.putExtra(context.getString(R.string.char_array_or_default), charArrayOf('a', 'b'))
                starter.putExtra(context.getString(R.string.char_sequence_or_default), "charSequenceOrDefault")
                starter.putExtra(context.getString(R.string.char_sequence_array_or_default), arrayOf("charSequence", "default"))


                starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_required), arrayListOf("charSequenceArrayListRequired", "required"))
                starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_optional), arrayListOf("charSequenceArrayListOptional", "optional"))
                starter.putCharSequenceArrayListExtra(context.getString(R.string.char_sequence_array_list_or_default), arrayListOf("charSequenceArrayListOrDefault", "default"))

                starter.putExtra(context.getString(R.string.string_or_default), "stringOrDefault")
                starter.putExtra(context.getString(R.string.string_array_or_default), arrayOf("stringArrayOrDefault", "default"))
                starter.putExtra(context.getString(R.string.string_array_list_or_default), arrayListOf("stringArrayListOrDefault", "default"))

                starter.putExtra(context.getString(R.string.parcelable_or_default), TestParcelable("parcelableOrDefault"))
                starter.putExtra(context.getString(R.string.parcelable_array_or_default), arrayOf(TestParcelable("parcelableArrayOrDefault"), TestParcelable("default")))
                starter.putParcelableArrayListExtra(context.getString(R.string.parcelable_array_list_or_default), arrayListOf(TestParcelable("parcelableArrayListOrDefault"), TestParcelable("default")))

                starter.putExtra(context.getString(R.string.serializable_or_default), TestSerializable("serializableOrDefault"))

                val bundleDefault = Bundle()
                bundleDefault.putString("bundle", "bundleOrDefault")
                starter.putExtra(context.getString(R.string.bundle_or_default), bundleDefault)

                val bundleRequired = Bundle()
                bundleRequired.putString("bundle", "bundleRequired")
                starter.putExtra(context.getString(R.string.bundle_required), bundleRequired)

                val bundleOptional = Bundle()
                bundleOptional.putString("bundle", "bundleOptional")
                starter.putExtra(context.getString(R.string.bundle_optional), bundleOptional)

                val b = Bundle()
                b.putString("extrasRequired", "extrasRequired")
                b.putString("extrasOptional", "extrasOptional")
                b.putString("extrasOrDefault", "extrasOrDefault")
                starter.putExtras(b)

                val args = Bundle()
                val sparseParcelableArrayRequired = SparseArray<Parcelable>()
                sparseParcelableArrayRequired.put(-1, TestParcelable("-1"))
                sparseParcelableArrayRequired.put(1, TestParcelable("1"))
                args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_required), sparseParcelableArrayRequired)

                val sparseParcelableArrayOptional = SparseArray<Parcelable>()
                sparseParcelableArrayOptional.put(-2, TestParcelable("-2"))
                sparseParcelableArrayOptional.put(2, TestParcelable("2"))
                args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_optional), sparseParcelableArrayOptional)

                val sparseParcelableArrayOrDefault = SparseArray<Parcelable>()
                sparseParcelableArrayOrDefault.put(-3, TestParcelable("-3"))
                sparseParcelableArrayOrDefault.put(3, TestParcelable("3"))
                args.putSparseParcelableArray(context.getString(R.string.sparse_parcelable_array_or_default), sparseParcelableArrayOrDefault)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    args.putBinder(context.getString(R.string.binder_required), TestBinder("binderRequired"))
                    args.putBinder(context.getString(R.string.binder_optional), TestBinder("binderOptional"))
                    args.putBinder(context.getString(R.string.binder_or_default), TestBinder("binderOrDefault"))
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    args.putSize(context.getString(R.string.size_required), Size(1, 1))
                    args.putSize(context.getString(R.string.size_optional), Size(2, 2))
                    args.putSize(context.getString(R.string.size_or_default), Size(3, 3))

                    args.putSizeF(context.getString(R.string.sizeF_required), SizeF(1f, 1f))
                    args.putSizeF(context.getString(R.string.sizeF_optional), SizeF(2f, 2f))
                    args.putSizeF(context.getString(R.string.sizeF_or_default), SizeF(3f, 3f))
                }

                starter.putExtras(args)

                return starter
            }
        }
    }

    class ResTestSupportFragment : androidx.fragment.app.Fragment() {
        companion object {
            fun newInstance(args: Bundle?): ResTestSupportFragment {
                val fragment = ResTestSupportFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }

    //no extras
    class NoExtraActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntent(context: Context): Intent {
                return Intent(context, NoExtraActivity::class.java)
            }
        }
    }

    //uri || intent
    class TestOnlyUriNoIntentActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntentWithUri(): Intent {
                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/uri?$params")

                return Intent(Intent.ACTION_VIEW, uri)
            }
        }
    }

    class TestOnlyIntentNoUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntentWithExtras(context: Context): Intent {
                val starter = Intent(context, TestOnlyIntentNoUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                starter.putExtra("byteIntentUriOrDefault", (-1).toByte())
                starter.putExtra("shortIntentUriOrDefault", (-2).toShort())
                starter.putExtra("intIntentUriOrDefault", -3)
                starter.putExtra("longIntentUriOrDefault", -4L)
                starter.putExtra("floatIntentUriOrDefault", -5f)
                starter.putExtra("doubleIntentUriOrDefault", -6.0)
                starter.putExtra("booleanIntentUriOrDefault", true)
                starter.putExtra("stringIntentUriRequired", "stringIntentRequired")
                starter.putExtra("stringIntentUriOptional", "stringIntentOptional")
                starter.putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra("byteUriIntentOrDefault", (-11).toByte())
                starter.putExtra("shortUriIntentOrDefault", (-12).toShort())
                starter.putExtra("intUriIntentOrDefault", -13)
                starter.putExtra("longUriIntentOrDefault", -14L)
                starter.putExtra("floatUriIntentOrDefault", -15f)
                starter.putExtra("doubleUriIntentOrDefault", -16.0)
                starter.putExtra("booleanUriIntentOrDefault", true)
                starter.putExtra("stringUriIntentRequired", "stringUriIntentRequired")
                starter.putExtra("stringUriIntentOptional", "stringUriIntentOptional")
                starter.putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class TestBothIntentUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {

            fun createIntentWithUriAndExtras(context: Context): Intent {

                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/uri/intent?$params")

                val starter = Intent(context, TestBothIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = uri
                starter.putExtra("byteIntentUriOrDefault", (-1).toByte())
                starter.putExtra("shortIntentUriOrDefault", (-2).toShort())
                starter.putExtra("intIntentUriOrDefault", -3)
                starter.putExtra("longIntentUriOrDefault", -4L)
                starter.putExtra("floatIntentUriOrDefault", -5f)
                starter.putExtra("doubleIntentUriOrDefault", -6.0)
                starter.putExtra("booleanIntentUriOrDefault", true)
                starter.putExtra("stringIntentUriRequired", "stringIntentRequired")
                starter.putExtra("stringIntentUriOptional", "stringIntentOptional")
                starter.putExtra("stringIntentUriOrDefault", "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra("byteUriIntentOrDefault", (-11).toByte())
                starter.putExtra("shortUriIntentOrDefault", (-12).toShort())
                starter.putExtra("intUriIntentOrDefault", -13)
                starter.putExtra("longUriIntentOrDefault", -14L)
                starter.putExtra("floatUriIntentOrDefault", -15f)
                starter.putExtra("doubleUriIntentOrDefault", -16.0)
                starter.putExtra("booleanUriIntentOrDefault", true)
                starter.putExtra("stringUriIntentRequired", "stringUriIntentRequired")
                starter.putExtra("stringUriIntentOptional", "stringUriIntentOptional")
                starter.putExtra("stringUriIntentOrDefault", "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class TestNoIntentUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntentWithNothing(context: Context): Intent {
                val starter = Intent(context, TestNoIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                return starter
            }
        }
    }

    //res
    class ResTestOnlyUriNoIntentActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntentWithUri(): Intent {
                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/res/uri?$params")

                return Intent(Intent.ACTION_VIEW, uri)
            }
        }
    }

    class ResTestOnlyIntentNoUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntentWithExtras(context: Context): Intent {
                val starter = Intent(context, ResTestOnlyIntentNoUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                starter.putExtra(context.getString(R.string.byte_intent_uri_or_default), (-1).toByte())
                starter.putExtra(context.getString(R.string.short_intent_uri_or_default), (-2).toShort())
                starter.putExtra(context.getString(R.string.int_intent_uri_or_default), -3)
                starter.putExtra(context.getString(R.string.long_intent_uri_or_default), -4L)
                starter.putExtra(context.getString(R.string.float_intent_uri_or_default), -5f)
                starter.putExtra(context.getString(R.string.double_intent_uri_or_default), -6.0)
                starter.putExtra(context.getString(R.string.boolean_intent_uri_or_default), true)
                starter.putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired")
                starter.putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional")
                starter.putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra(context.getString(R.string.byte_uri_intent_or_default), (-11).toByte())
                starter.putExtra(context.getString(R.string.short_uri_intent_or_default), (-12).toShort())
                starter.putExtra(context.getString(R.string.int_uri_intent_or_default), -13)
                starter.putExtra(context.getString(R.string.long_uri_intent_or_default), -14L)
                starter.putExtra(context.getString(R.string.float_uri_intent_or_default), -15f)
                starter.putExtra(context.getString(R.string.double_uri_intent_or_default), -16.0)
                starter.putExtra(context.getString(R.string.boolean_uri_intent_or_default), true)
                starter.putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired")
                starter.putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional")
                starter.putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class ResTestBothIntentUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {

            fun createIntentWithUriAndExtras(context: Context): Intent {

                val params = StringBuilder()
                        .append("byteIntentUriOrDefault=").append(1.toByte().toInt())
                        .append("&shortIntentUriOrDefault=").append(2.toShort().toInt())
                        .append("&intIntentUriOrDefault=").append(3)
                        .append("&longIntentUriOrDefault=").append(4L)
                        .append("&floatIntentUriOrDefault=").append(5.toFloat())
                        .append("&doubleIntentUriOrDefault=").append(6.toDouble())
                        .append("&booleanIntentUriOrDefault=").append(true)
                        .append("&stringIntentUriRequired=").append("stringIntentUriRequired")
                        .append("&stringIntentUriOptional=").append("stringIntentUriOptional")
                        .append("&stringIntentUriOrDefault=").append("stringIntentUriOrDefault")

                        //Activity Uri Intent
                        .append("&byteUriIntentOrDefault=").append(11.toByte().toInt())
                        .append("&shortUriIntentOrDefault=").append(12.toShort().toInt())
                        .append("&intUriIntentOrDefault=").append(13)
                        .append("&longUriIntentOrDefault=").append(14L)
                        .append("&floatUriIntentOrDefault=").append(15.toFloat())
                        .append("&doubleUriIntentOrDefault=").append(16.toDouble())
                        .append("&booleanUriIntentOrDefault=").append(true)
                        .append("&stringUriIntentRequired=").append("stringUriIntentRequired")
                        .append("&stringUriIntentOptional=").append("stringUriIntentOptional")
                        .append("&stringUriIntentOrDefault=").append("stringUriIntentOrDefault")
                        .toString()

                val uri = Uri.parse("https://github.com/panpf/androidx/res/uri/intent?$params")

                val starter = Intent(context, ResTestBothIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = uri
                starter.putExtra(context.getString(R.string.byte_intent_uri_or_default), (-1).toByte())
                starter.putExtra(context.getString(R.string.short_intent_uri_or_default), (-2).toShort())
                starter.putExtra(context.getString(R.string.int_intent_uri_or_default), -3)
                starter.putExtra(context.getString(R.string.long_intent_uri_or_default), -4L)
                starter.putExtra(context.getString(R.string.float_intent_uri_or_default), -5f)
                starter.putExtra(context.getString(R.string.double_intent_uri_or_default), -6.0)
                starter.putExtra(context.getString(R.string.boolean_intent_uri_or_default), true)
                starter.putExtra(context.getString(R.string.string_intent_uri_required), "stringIntentRequired")
                starter.putExtra(context.getString(R.string.string_intent_uri_optional), "stringIntentOptional")
                starter.putExtra(context.getString(R.string.string_intent_uri_or_default), "stringIntentOrDefault")

                //Activity Uri Intent
                starter.putExtra(context.getString(R.string.byte_uri_intent_or_default), (-11).toByte())
                starter.putExtra(context.getString(R.string.short_uri_intent_or_default), (-12).toShort())
                starter.putExtra(context.getString(R.string.int_uri_intent_or_default), -13)
                starter.putExtra(context.getString(R.string.long_uri_intent_or_default), -14L)
                starter.putExtra(context.getString(R.string.float_uri_intent_or_default), -15f)
                starter.putExtra(context.getString(R.string.double_uri_intent_or_default), -16.0)
                starter.putExtra(context.getString(R.string.boolean_uri_intent_or_default), true)
                starter.putExtra(context.getString(R.string.string_uri_intent_required), "stringUriIntentRequired")
                starter.putExtra(context.getString(R.string.string_uri_intent_optional), "stringUriIntentOptional")
                starter.putExtra(context.getString(R.string.string_uri_intent_or_default), "stringUriIntentOrDefault")
                return starter
            }
        }
    }

    class ResTestNoIntentUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {
            fun createIntentWithNothing(context: Context): Intent {
                val starter = Intent(context, ResTestNoIntentUriActivity::class.java)
                starter.action = Intent.ACTION_VIEW
                starter.data = Uri.parse("https://github.com/panpf")
                return starter
            }
        }
    }

    //activity uri
    class TestUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {

            fun createIntent(): Intent {
                val params = StringBuilder()
                        .append("byteUriRequired=").append(1.toByte().toInt())
                        .append("&byteUriOptional=").append((-1).toByte().toInt())
                        .append("&byteUriOrDefault=").append(2.toByte().toInt())

                        .append("&shortUriRequired=").append(3.toShort().toInt())
                        .append("&shortUriOptional=").append((-3).toShort().toInt())
                        .append("&shortUriOrDefault=").append(4.toShort().toInt())

                        .append("&intUriRequired=").append(5)
                        .append("&intUriOptional=").append(-5)
                        .append("&intUriOrDefault=").append(6)

                        .append("&longUriRequired=").append(7L)
                        .append("&longUriOptional=").append(-7L)
                        .append("&longUriOrDefault=").append(8L)

                        .append("&floatUriRequired=").append(9f)
                        .append("&floatUriOptional=").append(-9f)
                        .append("&floatUriOrDefault=").append(10f)

                        .append("&doubleUriRequired=").append(11.0)
                        .append("&doubleUriOptional=").append(-11.0)
                        .append("&doubleUriOrDefault=").append(12.0)

                        .append("&booleanUriRequired=").append(true)
                        .append("&booleanUriOptional=").append(true)
                        .append("&booleanUriOrDefault=").append(false)

                        .append("&stringUriRequired=").append("stringUriRequired")
                        .append("&stringUriOptional=").append("stringUriOptional")
                        .append("&stringUriOrDefault=").append("stringUriOrDefault")
                        .toString()
                val uri = Uri.parse("https://github.com/panpf/androidx?$params")
                return Intent(Intent.ACTION_VIEW, uri)
            }
        }
    }

    class ResTestUriActivity : androidx.fragment.app.FragmentActivity() {
        companion object {

            fun createIntent(): Intent {
                val params = StringBuilder()
                        .append("byteUriRequired=").append(1.toByte().toInt())
                        .append("&byteUriOptional=").append((-1).toByte().toInt())
                        .append("&byteUriOrDefault=").append(2.toByte().toInt())

                        .append("&shortUriRequired=").append(3.toShort().toInt())
                        .append("&shortUriOptional=").append((-3).toShort().toInt())
                        .append("&shortUriOrDefault=").append(4.toShort().toInt())

                        .append("&intUriRequired=").append(5)
                        .append("&intUriOptional=").append(-5)
                        .append("&intUriOrDefault=").append(6)

                        .append("&longUriRequired=").append(7L)
                        .append("&longUriOptional=").append(-7L)
                        .append("&longUriOrDefault=").append(8L)

                        .append("&floatUriRequired=").append(9f)
                        .append("&floatUriOptional=").append(-9f)
                        .append("&floatUriOrDefault=").append(10f)

                        .append("&doubleUriRequired=").append(11.0)
                        .append("&doubleUriOptional=").append(-11.0)
                        .append("&doubleUriOrDefault=").append(12.0)

                        .append("&booleanUriRequired=").append(true)
                        .append("&booleanUriOptional=").append(true)
                        .append("&booleanUriOrDefault=").append(false)

                        .append("&stringUriRequired=").append("stringUriRequired")
                        .append("&stringUriOptional=").append("stringUriOptional")
                        .append("&stringUriOrDefault=").append("stringUriOrDefault")
                        .toString()
                val uri = Uri.parse("https://github.com/panpf/androidx/res?$params")
                return Intent(Intent.ACTION_VIEW, uri)
            }
        }

    }

    @Parcelize
    data class TestParcelable(val tag: String) : Parcelable

    data class TestSerializable(val tag: String) : java.io.Serializable

    data class TestBinder(val tag: String = "") : Binder()
}