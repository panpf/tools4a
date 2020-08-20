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

package com.github.panpf.tools4a.content.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.content.Intentx;
import com.github.panpf.tools4a.fileprovider.FileProviderx;
import com.github.panpf.tools4a.storage.Storagex;
import com.github.panpf.tools4j.io.Filex;
import com.github.panpf.tools4j.io.IOx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class IntentxTest {

    @Test
    public void testCreateRecordingIntent() {
        Intent recordingIntent = Intentx.createRecordingIntent();
        Assert.assertEquals(Intent.ACTION_GET_CONTENT, recordingIntent.getAction());
        Assert.assertEquals("audio/amr", recordingIntent.getType());
    }

    @Test
    public void testCreateLaunchDialingIntent() {
        Intent recordingIntent = Intentx.createLaunchDialingIntent("18801287688");
        Assert.assertEquals(Intent.ACTION_DIAL, recordingIntent.getAction());
        Assert.assertEquals("tel:18801287688", recordingIntent.getData().toString());
    }

    @Test
    public void testCreateCallPhoneIntent() {
        Intent callPhoneIntent = Intentx.createCallPhoneIntent("18801287688");
        Assert.assertEquals(Intent.ACTION_CALL, callPhoneIntent.getAction());
        Assert.assertEquals("tel:18801287688", callPhoneIntent.getData().toString());
    }

    @Test
    public void testCreateLaunchSendSmsIntent() {
        Intent sendSmsIntent = Intentx.createLaunchSendSmsIntent("18801287688", "好好学习吧，天天向上");
        Assert.assertEquals(Intent.ACTION_SENDTO, sendSmsIntent.getAction());
        Assert.assertEquals("smsto:18801287688", sendSmsIntent.getData().toString());
        Assert.assertEquals("好好学习吧，天天向上", sendSmsIntent.getStringExtra("sms_body"));
    }

    @Test
    public void testCreateLaunchWebBrowserIntent() {
        Intent webIntent = Intentx.createLaunchWebBrowserIntent("https://github.com");
        Assert.assertEquals(Intent.ACTION_VIEW, webIntent.getAction());
        Assert.assertEquals("https://github.com", webIntent.getData().toString());
    }

    @Test
    public void testCreateScanFileBroadcastIntent() {
        Context context = InstrumentationRegistry.getContext();
        File file = new File(Storagex.getAppExternalFilesDir(context), "testCreateScanFileBroadcastIntent.jpg");

        Intent scanFileIntent1 = Intentx.createScanFileBroadcastIntent(FileProviderx.getShareFileUri(context, file));
        Assert.assertEquals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, scanFileIntent1.getAction());
        Assert.assertEquals(FileProviderx.getShareFileUri(context, file).toString(),
                scanFileIntent1.getData().toString());
        Assert.assertTrue((scanFileIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
    }

    @Test
    public void testCreateActivityIntentByResolveInfo() {
        Context context = InstrumentationRegistry.getContext();

        Intent sendIntent = Intentx.createSendTextIntent("https://github.com");
        Assert.assertNull(sendIntent.getComponent());

        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(sendIntent, 0);
        ResolveInfo resolveInfo = resolveInfos.size() > 0 ? resolveInfos.get(0) : null;

        Intent finalIntent = Intentx.createActivityIntentByResolveInfo(sendIntent, resolveInfo);
        Assert.assertEquals(sendIntent.getAction(), finalIntent.getAction());
        Assert.assertNotNull(finalIntent.getComponent());
    }

    @Test
    public void testCreateTakePhotoIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();

        File file = new File(Storagex.getAppExternalFilesDir(context), "testCreateTakePhotoIntent_temp.jpg");
        try {
            Filex.createNewFileOrThrow(file);

            Intent takePhotoIntent1 = Intentx.createTakePhotoIntent(FileProviderx.getShareFileUri(context, file));
            Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, takePhotoIntent1.getAction());
            Assert.assertEquals(FileProviderx.getShareFileUri(context, file).toString(), takePhotoIntent1.getParcelableExtra(MediaStore.EXTRA_OUTPUT).toString());
            Assert.assertTrue((takePhotoIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
        } finally {
            Filex.deleteRecursively(file);
        }
    }

    @Test
    public void testCreatePickImageIntent() {
        Intent pickImageIntent = Intentx.createPickImageIntent();
        Assert.assertEquals(Intent.ACTION_PICK, pickImageIntent.getAction());
        Assert.assertEquals("image/*", pickImageIntent.getType());
    }

    @Test
    public void testCreateCropImageIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File sourceFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateCropImageIntent.jpg");
        File saveFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateCropImageIntent_temp.jpg");
        try {
            Filex.createNewFileOrThrow(saveFile);

            InputStream inputStream = context.getResources().openRawResource(R.drawable.rect);
            try {
                byte[] bytes = IOx.readBytes(inputStream);
                Filex.writeBytes(sourceFile, bytes);
            } finally {
                IOx.closeQuietly(inputStream);
            }

            Uri sourceFileUri = FileProviderx.getShareFileUri(context, sourceFile);
            Uri saveFileUri = FileProviderx.getShareFileUri(context, saveFile);

            Intent cropImageIntent1 = Intentx.createCropImageIntent(sourceFileUri, 50, 100, saveFileUri);
            Assert.assertEquals("com.android.camera.action.CROP", cropImageIntent1.getAction());
            Assert.assertEquals(sourceFileUri.toString(), cropImageIntent1.getData().toString());
            Assert.assertEquals("image/*", cropImageIntent1.getType());
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("aspectX", 0));
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("aspectY", 0));
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("outputX", 0));
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("outputY", 0));
            Assert.assertEquals(saveFileUri.toString(), cropImageIntent1.getParcelableExtra("output").toString());
            Assert.assertTrue((cropImageIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent cropImageIntent2 = Intentx.createCropImageIntent(sourceFileUri, 50, 100, null);
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("return-data", false));
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("scale", false));
        } finally {
            Filex.deleteRecursively(sourceFile);
            Filex.deleteRecursively(saveFile);
        }
    }

    @Test
    public void testCreateSendTextIntent() {
        Intent intent1 = Intentx.createSendTextIntent("好好学习吧，天天向上", "座右铭");
        Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
        Assert.assertEquals("text/plain", intent1.getType());
        Assert.assertEquals("好好学习吧，天天向上", intent1.getStringExtra(Intent.EXTRA_TEXT));
        Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT));

        Intent intent2 = Intentx.createSendTextIntent("好好学习吧，天天向上");
        Assert.assertEquals(Intent.ACTION_SEND, intent2.getAction());
        Assert.assertEquals("text/plain", intent2.getType());
        Assert.assertEquals("好好学习吧，天天向上", intent2.getStringExtra(Intent.EXTRA_TEXT));
        Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT));
    }

    @Test
    public void testCreateSendTextFileIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File textFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateSendTextFileIntent.txt");
        try {
            Filex.createNewFileOrThrow(textFile);
            Filex.writeText(textFile, "testCreateSendTextFileIntent");

            Intent intent1 = Intentx.createSendTextFileIntent(FileProviderx.getShareFileUri(context, textFile), "座右铭");
            Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
            Assert.assertEquals(FileProviderx.getShareFileUri(context, textFile).toString(),
                    intent1.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent1.getType());
            Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT));
            Assert.assertTrue((intent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent2 = Intentx.createSendTextFileIntent(FileProviderx.getShareFileUri(context, textFile));
            Assert.assertEquals(Intent.ACTION_SEND, intent2.getAction());
            Assert.assertEquals(FileProviderx.getShareFileUri(context, textFile).toString(),
                    intent2.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent2.getType());
            Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT));
            Assert.assertTrue((intent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
        } finally {
            Filex.deleteRecursively(textFile);
        }
    }

    @Test
    public void testCreateSendImageFileIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File imageFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateSendImageFileIntent.jpg");
        try {
            Filex.createNewFileOrThrow(imageFile);
            InputStream inputStream = context.getResources().openRawResource(R.drawable.rect);
            try {
                byte[] bytes = IOx.readBytes(inputStream);
                Filex.writeBytes(imageFile, bytes);
            } finally {
                IOx.closeQuietly(inputStream);
            }

            Intent intent1 = Intentx.createSendImageFileIntent(FileProviderx.getShareFileUri(context, imageFile), "bmp");
            Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
            Assert.assertEquals(FileProviderx.getShareFileUri(context, imageFile).toString(),
                    intent1.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("image/bmp", intent1.getType());
            Assert.assertTrue((intent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent2 = Intentx.createSendImageFileIntent(FileProviderx.getShareFileUri(context, imageFile));
            Assert.assertEquals(Intent.ACTION_SEND, intent2.getAction());
            Assert.assertEquals(FileProviderx.getShareFileUri(context, imageFile).toString(),
                    intent2.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("image/*", intent2.getType());
            Assert.assertTrue((intent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
        } finally {
            Filex.deleteRecursively(imageFile);
        }
    }

    @Test
    public void testCreateSendFileIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File textFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateSendFileIntent.txt");
        try {
            Filex.createNewFileOrThrow(textFile);
            Filex.writeText(textFile, "testCreateSendFileIntent");

            Intent intent1 = Intentx.createSendFileIntent(FileProviderx.getShareFileUri(context, textFile), "text/plain");
            Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
            Assert.assertEquals(FileProviderx.getShareFileUri(context, textFile).toString(),
                    intent1.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent1.getType());
            Assert.assertTrue((intent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
        } finally {
            Filex.deleteRecursively(textFile);
        }
    }
}
