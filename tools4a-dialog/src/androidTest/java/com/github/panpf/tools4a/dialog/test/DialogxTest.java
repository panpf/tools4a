package com.github.panpf.tools4a.dialog.test;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.dialog.Dialogx;
import com.github.panpf.tools4a.test.Testx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class DialogxTest {

    @Test
    public void testSetClickButtonClosable() {
        Testx.launchActivityWithOnUse(TestFragmentActivity.class, activity -> {
            Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), true));
            Assert.assertTrue(Dialogx.setClickButtonClosable(activity.getDialog(), false));
        });
    }

    @Test
    public void testShowProgressDialog() {
        Testx.launchActivityWithOnUse(TestFragmentActivity.class, activity -> {
            Assert.assertNotNull(Dialogx.showProgressDialog(activity, "by activity"));
            Assert.assertNotNull(Dialogx.showProgressDialog(activity, android.R.string.ok));

            Fragment fragment = activity.getFragment();
            Assert.assertNotNull(Dialogx.showProgressDialog(fragment, "by supportFragment"));
            Assert.assertNotNull(Dialogx.showProgressDialog(fragment, android.R.string.yes));
        });
    }

    public static class TestFragmentActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new androidx.fragment.app.Fragment())
                    .commit();
        }

        @NonNull
        public Fragment getFragment() {
            return Objects.requireNonNull(getSupportFragmentManager().findFragmentById(android.R.id.content));
        }

        @NonNull
        public Dialog getDialog() {
            return new Dialog(this);
        }
    }
}