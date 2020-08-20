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

package com.github.panpf.tools4a.content;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

/**
 * Intent tool method
 */
public class Intentx {

    private Intentx() {
    }


    /**
     * Create an Intent that opens the recording page
     */
    @NonNull
    public static Intent createRecordingIntent() {
        return new Intent(Intent.ACTION_GET_CONTENT).setType("audio/amr");
    }

    /**
     * Create an Intent that opens the dialing page and displays the specified phone number
     *
     * @param phoneNumber Target phone number
     */
    @NonNull
    public static Intent createLaunchDialingIntent(@NonNull String phoneNumber) {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
    }

    /**
     * Create an Intent that can call the specified phone number
     *
     * @param phoneNumber Target phone number
     */
    @NonNull
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static Intent createCallPhoneIntent(@NonNull String phoneNumber) {
        return new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
    }

    /**
     * Create an Intent that can start sending SMS pages
     *
     * @param phoneNumber    Target phone number
     * @param messageContent SMS content
     */
    @NonNull
    public static Intent createLaunchSendSmsIntent(@NonNull String phoneNumber, String messageContent) {
        return new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber))
                .putExtra("sms_body", messageContent);
    }

    /**
     * Create an Intent that opens the specified web page
     *
     * @param url Web page url
     */
    @NonNull
    public static Intent createLaunchWebBrowserIntent(String url) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

    /**
     * Create a broadcast Intent that lets System Explorer scan the specified file uri
     */
    @NonNull
    public static Intent createScanFileBroadcastIntent(@NonNull Uri fileUri) {
        return new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
    }

    /**
     * Create an Intent based on the source Intent and the ResolveInfo found with it
     */
    @NonNull
    public static Intent createActivityIntentByResolveInfo(@NonNull Intent sourceIntent, @NonNull ResolveInfo resolveInfo) {
        Intent resolveIntent = new Intent();
        resolveIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        resolveIntent.setAction(sourceIntent.getAction());
        Bundle bundle = sourceIntent.getExtras();
        if (bundle != null) {
            resolveIntent.putExtras(bundle);
        }
        resolveIntent.setType(sourceIntent.getType());
        resolveIntent.addFlags(resolveInfo.activityInfo.flags);
        return resolveIntent;
    }


    /**
     * Create an Intent to take a photo with your camera
     *
     * @param saveFileUri Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
     *                    for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
     */
    @NonNull
    public static Intent createTakePhotoIntent(@Nullable Uri saveFileUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (saveFileUri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
        }
        return intent;
    }

    /**
     * Create an Intent that selects a picture from the system album, And then get the image uri from the returned Intent at onActivityResult,
     * for example: Uri imageUri = (Bitmap) intent.getData()
     */
    @NonNull
    public static Intent createPickImageIntent() {
        return new Intent(Intent.ACTION_PICK).setType("image/*");
    }

    /**
     * Create an intent that crops the image
     *
     * @param sourceFileUri Picture uri
     * @param targetWidth   Target width
     * @param targetHeight  Target height
     * @param saveFileUri   Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
     *                      for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
     */
    @NonNull
    public static Intent createCropImageIntent(@NonNull Uri sourceFileUri, int targetWidth, int targetHeight, @Nullable Uri saveFileUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(sourceFileUri, "image/*");
        intent.putExtra("crop", true);

        intent.putExtra("aspectX", targetWidth);
        intent.putExtra("aspectY", targetHeight);

        intent.putExtra("outputX", targetWidth);
        intent.putExtra("outputY", targetHeight);

        if (saveFileUri != null) {
            intent.putExtra("output", saveFileUri);
        } else {
            intent.putExtra("return-data", true);
            intent.putExtra("scale", true);
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
        return intent;
    }


    /**
     * Create an Intent to send text
     */
    public static Intent createSendTextIntent(@NonNull String text, @NonNull String subject) {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_TEXT, text);
    }

    /**
     * Create an Intent to send text
     */
    public static Intent createSendTextIntent(@NonNull String text) {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, text);
    }

    /**
     * Create an Intent to send text
     */
    public static Intent createSendTextFileIntent(@NonNull Uri textFileUri, @NonNull String subject) {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_STREAM, textFileUri)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }

    /**
     * Create an Intent to send text
     */
    public static Intent createSendTextFileIntent(@NonNull Uri textFileUri) {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_STREAM, textFileUri)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }


    /**
     * Create an Intent to send image
     */
    public static Intent createSendImageFileIntent(@NonNull Uri imageFileUri, @NonNull String subType) {
        int index = subType.indexOf("/");
        final String finalSubType = index != -1 ? subType.substring(index + 1) : subType;
        return new Intent(Intent.ACTION_SEND)
                .setType("image/" + finalSubType)
                .putExtra(Intent.EXTRA_STREAM, imageFileUri)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }

    /**
     * Create an Intent to send image
     */
    public static Intent createSendImageFileIntent(@NonNull Uri imageFileUri) {
        return new Intent(Intent.ACTION_SEND)
                .setType("image/*")
                .putExtra(Intent.EXTRA_STREAM, imageFileUri)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }


    /**
     * Create an Intent to send image
     */
    public static Intent createSendFileIntent(@NonNull Uri fileUri, @NonNull String mimeType) {
        return new Intent(Intent.ACTION_SEND)
                .setType(mimeType)
                .putExtra(Intent.EXTRA_STREAM, fileUri)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }
}
