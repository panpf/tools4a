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

package com.github.panpf.tools4a.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.github.panpf.tools4a.run.Runx;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class Clipboardx {

    private Clipboardx() {
    }

    /**
     * copy
     */
    public static void copy(@NonNull Context context, @NonNull ClipData clipData) {
        getClipboardManager(context).setPrimaryClip(clipData);
    }


    /**
     * Copy text
     */
    public static void copyText(@NonNull Context context, @NonNull CharSequence label, @NonNull CharSequence[] texts) {
        if (texts.length <= 0) return;
        ClipData data = ClipData.newPlainText(label, texts[0]);
        if (texts.length > 1) {
            for (int index = 1, size = texts.length; index < size; index++) {
                data.addItem(new ClipData.Item(texts[index]));
            }
        }
        copy(context, data);
    }

    /**
     * Copy text
     */
    public static void copyText(@NonNull Context context, @NonNull CharSequence[] texts) {
        copyText(context, "text", texts);
    }

    /**
     * Copy text
     */
    public static void copyText(@NonNull Context context, @NonNull CharSequence label, @NonNull CharSequence text) {
        copy(context, ClipData.newPlainText(label, text));
    }

    /**
     * Copy text
     */
    public static void copyText(@NonNull Context context, @NonNull CharSequence text) {
        copyText(context, "text", text);
    }


    /**
     * Copy html text
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void copyHtmlText(@NonNull Context context, @NonNull CharSequence label, @NonNull ClipHtmlText[] htmlContents) {
        if (htmlContents.length <= 0) return;
        ClipHtmlText htmlContent = htmlContents[0];
        ClipData data = ClipData.newHtmlText(label, htmlContent.text, htmlContent.htmlText);
        if (htmlContents.length > 1) {
            for (int index = 1, size = htmlContents.length; index < size; index++) {
                htmlContent = htmlContents[index];
                data.addItem(new ClipData.Item(htmlContent.text, htmlContent.htmlText));
            }
        }
        copy(context, data);
    }

    /**
     * Copy html text
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void copyHtmlText(@NonNull Context context, @NonNull ClipHtmlText[] htmlContents) {
        copyHtmlText(context, "htmlText", htmlContents);
    }

    /**
     * Copy html text
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void copyHtmlText(@NonNull Context context, @NonNull CharSequence label, @NonNull CharSequence text, @NonNull String htmlText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            copy(context, ClipData.newHtmlText(label, text, htmlText));
        }
    }

    /**
     * Copy html text
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void copyHtmlText(@NonNull Context context, @NonNull CharSequence text, @NonNull String htmlText) {
        copyHtmlText(context, "htmlText", text, htmlText);
    }


    /**
     * Copy intent
     */
    public static void copyIntent(@NonNull Context context, @NonNull CharSequence label, @NonNull Intent[] intents) {
        if (intents.length <= 0) return;
        ClipData data = ClipData.newIntent(label, intents[0]);
        if (intents.length > 1) {
            for (int index = 1, size = intents.length; index < size; index++) {
                data.addItem(new ClipData.Item(intents[index]));
            }
        }
        copy(context, data);
    }

    /**
     * Copy intent
     */
    public static void copyIntent(@NonNull Context context, @NonNull Intent[] intents) {
        copyIntent(context, "intent", intents);
    }

    /**
     * Copy intent
     */
    public static void copyIntent(@NonNull Context context, @NonNull CharSequence label, @NonNull Intent intent) {
        copy(context, ClipData.newIntent(label, intent));
    }

    /**
     * Copy intent
     */
    public static void copyIntent(@NonNull Context context, @NonNull Intent intent) {
        copyIntent(context, "intent", intent);
    }


    /**
     * Copy uri
     */
    public static void copyUri(@NonNull Context context, @NonNull CharSequence label, @NonNull Uri[] uris) {
        if (uris.length <= 0) return;
        ClipData data = ClipData.newUri(context.getContentResolver(), label, uris[0]);
        if (uris.length > 1) {
            for (int index = 1, size = uris.length; index < size; index++) {
                data.addItem(new ClipData.Item(uris[index]));
            }
        }
        copy(context, data);
    }

    /**
     * Copy uri
     */
    public static void copyUri(@NonNull Context context, @NonNull Uri[] uris) {
        copyUri(context, "uri", uris);
    }

    /**
     * Copy uri
     */
    public static void copyUri(@NonNull Context context, @NonNull CharSequence label, @NonNull Uri uri) {
        copy(context, ClipData.newUri(context.getContentResolver(), label, uri));
    }

    /**
     * Copy uri
     */
    public static void copyUri(@NonNull Context context, @NonNull Uri uri) {
        copyUri(context, "uri", uri);
    }


    /**
     * Copy raw uri
     */
    public static void copyRawUri(@NonNull Context context, @NonNull CharSequence label, @NonNull Uri[] uris) {
        if (uris.length <= 0) return;
        ClipData data = ClipData.newRawUri(label, uris[0]);
        if (uris.length > 1) {
            for (int index = 1, size = uris.length; index < size; index++) {
                data.addItem(new ClipData.Item(uris[index]));
            }
        }
        copy(context, data);
    }

    /**
     * Copy raw uri
     */
    public static void copyRawUri(@NonNull Context context, @NonNull Uri[] uris) {
        copyRawUri(context, "rawUri", uris);
    }

    /**
     * Copy raw uri
     */
    public static void copyRawUri(@NonNull Context context, @NonNull CharSequence label, @NonNull Uri uri) {
        copy(context, ClipData.newRawUri(label, uri));
    }

    /**
     * Copy raw uri
     */
    public static void copyRawUri(@NonNull Context context, @NonNull Uri uri) {
        copyRawUri(context, "rawUri", uri);
    }


    /**
     * Copy uri
     */
    public static void copyMimeTypeUri(@NonNull Context context, @NonNull CharSequence label, @NonNull String mimeType, @NonNull Uri[] uris) {
        if (uris.length <= 0) return;
        ClipData data = new ClipData(label, new String[]{mimeType}, new ClipData.Item(uris[0]));
        if (uris.length > 1) {
            for (int index = 1, size = uris.length; index < size; index++) {
                data.addItem(new ClipData.Item(uris[index]));
            }
        }
        copy(context, data);
    }

    /**
     * Copy uri
     */
    public static void copyMimeTypeUri(@NonNull Context context, @NonNull String mimeType, @NonNull Uri[] uris) {
        copyMimeTypeUri(context, "mimeTypeUri", mimeType, uris);
    }

    /**
     * Copy uri
     */
    public static void copyMimeTypeUri(@NonNull Context context, @NonNull CharSequence label, @NonNull String mimeType, @NonNull Uri uri) {
        copy(context, new ClipData(label, new String[]{mimeType}, new ClipData.Item(uri)));
    }

    /**
     * Copy uri
     */
    public static void copyMimeTypeUri(@NonNull Context context, @NonNull String mimeType, @NonNull Uri uri) {
        copyMimeTypeUri(context, "mimeTypeUri", mimeType, uri);
    }

    /**
     * Copy multi type content
     */
    public static void copyContents(@NonNull Context context, @NonNull CharSequence label, @NonNull ClipContent[] contents) {
        if (contents.length <= 0) return;

        String[] mimeTypes = new String[contents.length];
        for (int index = 0, size = contents.length; index < size; index++) {
            mimeTypes[index] = contents[index].mimeType;
        }

        ClipData data = new ClipData(label, mimeTypes, contents[0].toItem());
        for (int index = 1, size = contents.length; index < size; index++) {
            data.addItem(contents[index].toItem());
        }

        copy(context, data);
    }

    /**
     * Copy multi type content
     */
    public static void copyContents(@NonNull Context context, @NonNull ClipContent[] contents) {
        copyContents(context, "contents", contents);
    }

    /**
     * Get current clip data
     */
    @Nullable
    public static ClipData get(@NonNull Context context) {
        return getClipboardManager(context).getPrimaryClip();
    }

    /**
     * Get current clip label
     */
    @Nullable
    public static CharSequence getLabel(@NonNull Context context) {
        ClipData data = get(context);
        return data != null ? data.getDescription().getLabel() : null;
    }

    /**
     * Get current clip all content
     */
    @Nullable
    public static ClipContent[] getContents(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        ClipDescription clipDescription = data.getDescription();
        List<ClipContent> objectList = new LinkedList<>();
        for (int index = 0, size = data.getItemCount(); index < size; index++) {
            ClipData.Item item = data.getItemAt(index);
            // Usually multiple items have only one mimeType
            String mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.getMimeTypeCount() - 1));
            if (ClipDescription.MIMETYPE_TEXT_PLAIN.equals(mimeType)) {
                objectList.add(new ClipPlainText(mimeType, item.getText()));
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && ClipDescription.MIMETYPE_TEXT_HTML.equals(mimeType)) {
                objectList.add(new ClipHtmlText(mimeType, item.getText(), item.getHtmlText()));
            } else if (ClipDescription.MIMETYPE_TEXT_INTENT.equals(mimeType)) {
                objectList.add(new ClipIntent(mimeType, item.getIntent()));
            } else {
                Uri uri = item.getUri();
                if (uri != null) {
                    objectList.add(new ClipUri(mimeType, uri));
                }
            }
        }
        return objectList.toArray(new ClipContent[0]);
    }

    /**
     * Get current clip text data
     */
    @Nullable
    public static CharSequence getText(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        if (ClipDescription.MIMETYPE_TEXT_PLAIN.equals(data.getDescription().getMimeType(0))) {
            return data.getItemAt(0).getText();
        } else {
            return null;
        }
    }

    /**
     * Get current clip all text data
     */
    @Nullable
    public static CharSequence[] getTexts(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        ClipDescription clipDescription = data.getDescription();
        List<CharSequence> textList = new LinkedList<>();
        for (int index = 0, size = data.getItemCount(); index < size; index++) {
            // Usually multiple items have only one mimeType
            String mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.getMimeTypeCount() - 1));
            if (ClipDescription.MIMETYPE_TEXT_PLAIN.equals(mimeType)) {
                textList.add(data.getItemAt(index).getText());
            }
        }
        return textList.toArray(new CharSequence[0]);
    }


    /**
     * Get current clip html text data
     */
    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    public static ClipHtmlText getHtmlText(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        String mimeType = data.getDescription().getMimeType(0);
        if (ClipDescription.MIMETYPE_TEXT_HTML.equals(mimeType)) {
            ClipData.Item clipDataItem = data.getItemAt(0);
            return new ClipHtmlText(mimeType, clipDataItem.getText(), clipDataItem.getHtmlText());
        } else {
            return null;
        }
    }

    /**
     * Get current clip all html text data
     */
    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    public static ClipHtmlText[] getHtmlTexts(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        ClipDescription clipDescription = data.getDescription();
        List<ClipHtmlText> htmlTestList = new LinkedList<>();
        for (int index = 0, size = data.getItemCount(); index < size; index++) {
            // Usually multiple items have only one mimeType
            String mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.getMimeTypeCount() - 1));
            if (ClipDescription.MIMETYPE_TEXT_HTML.equals(mimeType)) {
                ClipData.Item clipDataItem = data.getItemAt(index);
                htmlTestList.add(new ClipHtmlText(mimeType, clipDataItem.getText(), clipDataItem.getHtmlText()));
            }
        }
        return htmlTestList.toArray(new ClipHtmlText[0]);
    }


    /**
     * Get current clip intent data
     */
    @Nullable
    public static Intent getIntent(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        if (ClipDescription.MIMETYPE_TEXT_INTENT.equals(data.getDescription().getMimeType(0))) {
            return data.getItemAt(0).getIntent();
        } else {
            return null;
        }
    }

    /**
     * Get current clip all intent data
     */
    @Nullable
    public static Intent[] getIntents(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        ClipDescription clipDescription = data.getDescription();
        List<Intent> intentList = new LinkedList<>();
        for (int index = 0, size = data.getItemCount(); index < size; index++) {
            // Usually multiple items have only one mimeType
            String mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.getMimeTypeCount() - 1));
            if (ClipDescription.MIMETYPE_TEXT_INTENT.equals(mimeType)) {
                intentList.add(data.getItemAt(index).getIntent());
            }
        }
        return intentList.toArray(new Intent[0]);
    }


    /**
     * Get current clip uri data
     */
    @Nullable
    public static ClipUri getUri(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        String mimeType = data.getDescription().getMimeType(0);
        if (!ClipDescription.MIMETYPE_TEXT_PLAIN.equals(mimeType) && !ClipDescription.MIMETYPE_TEXT_HTML.equals(mimeType) && !ClipDescription.MIMETYPE_TEXT_INTENT.equals(mimeType)) {
            Uri uri = data.getItemAt(0).getUri();
            if (uri != null) {
                return new ClipUri(mimeType, uri);
            }
        }
        return null;
    }

    /**
     * Get current clip all uri data
     */
    @Nullable
    public static ClipUri[] getUris(@NonNull Context context) {
        ClipData data = get(context);
        if (data == null || data.getItemCount() <= 0 || data.getDescription().getMimeTypeCount() <= 0)
            return null;

        ClipDescription clipDescription = data.getDescription();
        List<ClipUri> uriList = new LinkedList<>();
        for (int index = 0, size = data.getItemCount(); index < size; index++) {
            // Usually multiple items have only one mimeType
            String mimeType = clipDescription.getMimeType(Math.min(index, clipDescription.getMimeTypeCount() - 1));
            if (!ClipDescription.MIMETYPE_TEXT_PLAIN.equals(mimeType) && !ClipDescription.MIMETYPE_TEXT_HTML.equals(mimeType) && !ClipDescription.MIMETYPE_TEXT_INTENT.equals(mimeType)) {
                Uri uri = data.getItemAt(index).getUri();
                if (uri != null) {
                    uriList.add(new ClipUri(mimeType, uri));
                }
            }
        }
        return uriList.toArray(new ClipUri[0]);
    }


    /**
     * Add primary clip changed listener
     */
    public static void addPrimaryClipChangedListener(@NonNull Context context, @NonNull ClipboardManager.OnPrimaryClipChangedListener listener) {
        getClipboardManager(context).addPrimaryClipChangedListener(listener);
    }

    /**
     * Remove primary clip changed listener
     */
    public static void removePrimaryClipChangedListener(@NonNull Context context, @NonNull ClipboardManager.OnPrimaryClipChangedListener listener) {
        getClipboardManager(context).removePrimaryClipChangedListener(listener);
    }


    /**
     * Clean clip data
     */
    @RequiresApi(Build.VERSION_CODES.P)
    public static void clear(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getClipboardManager(context).clearPrimaryClip();
        }
    }

    @NonNull
    private static ClipboardManager getClipboardManager(@NonNull final Context context) {
        final WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        return Runx.runOnUiThreadAndWaitResult(() -> {
            Context nowContext = contextWeakReference.get();
            if (nowContext == null) throw new IllegalStateException("Context has death");
            ClipboardManager clipboardManager = (ClipboardManager) nowContext.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager != null) {
                return clipboardManager;
            } else {
                throw new IllegalArgumentException("Not found service '" + Context.CLIPBOARD_SERVICE + "'");
            }
        });
    }
}
