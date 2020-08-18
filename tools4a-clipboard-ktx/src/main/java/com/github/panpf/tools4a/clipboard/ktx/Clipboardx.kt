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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.panpf.tools4a.clipboard.ktx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.panpf.tools4a.clipboard.ClipContent
import com.github.panpf.tools4a.clipboard.ClipHtmlText
import com.github.panpf.tools4a.clipboard.ClipUri
import com.github.panpf.tools4a.clipboard.Clipboardx

/**
 * copy
 */
inline fun Context.copy(clipData: ClipData) = Clipboardx.copy(this, clipData)


/**
 * Copy text
 */
inline fun Context.copyText(label: CharSequence, texts: Array<CharSequence>) = Clipboardx.copyText(this, label, texts)

/**
 * Copy text
 */
inline fun Context.copyText(texts: Array<CharSequence>) = Clipboardx.copyText(this, texts)

/**
 * Copy text
 */
inline fun Context.copyText(label: CharSequence, text: CharSequence) = Clipboardx.copyText(this, label, text)

/**
 * Copy text
 */
inline fun Context.copyText(text: CharSequence) = Clipboardx.copyText(this, text)


/**
 * Copy html text
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun Context.copyHtmlText(label: CharSequence, htmlContents: Array<ClipHtmlText>) = Clipboardx.copyHtmlText(this, label, htmlContents)

/**
 * Copy html text
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun Context.copyHtmlText(htmlContents: Array<ClipHtmlText>) = Clipboardx.copyHtmlText(this, htmlContents)

/**
 * Copy html text
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun Context.copyHtmlText(label: CharSequence, text: CharSequence, htmlText: String) = Clipboardx.copyHtmlText(this, label, text, htmlText)

/**
 * Copy html text
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun Context.copyHtmlText(text: CharSequence, htmlText: String) = Clipboardx.copyHtmlText(this, text, htmlText)


/**
 * Copy intent
 */
inline fun Context.copyIntent(label: CharSequence, intents: Array<Intent>) = Clipboardx.copyIntent(this, label, intents)

/**
 * Copy intent
 */
inline fun Context.copyIntent(intents: Array<Intent>) = Clipboardx.copyIntent(this, intents)

/**
 * Copy intent
 */
inline fun Context.copyIntent(label: CharSequence, intent: Intent) = Clipboardx.copyIntent(this, label, intent)

/**
 * Copy intent
 */
inline fun Context.copyIntent(intent: Intent) = Clipboardx.copyIntent(this, intent)


/**
 * Copy uri
 */
inline fun Context.copyUri(label: CharSequence, uris: Array<Uri>) = Clipboardx.copyUri(this, label, uris)

/**
 * Copy uri
 */
inline fun Context.copyUri(uris: Array<Uri>) = Clipboardx.copyUri(this, uris)

/**
 * Copy uri
 */
inline fun Context.copyUri(label: CharSequence, uri: Uri) = Clipboardx.copyUri(this, label, uri)

/**
 * Copy uri
 */
inline fun Context.copyUri(uri: Uri) = Clipboardx.copyUri(this, uri)


/**
 * Copy raw uri
 */
inline fun Context.copyRawUri(label: CharSequence, uris: Array<Uri>) = Clipboardx.copyRawUri(this, label, uris)

/**
 * Copy raw uri
 */
inline fun Context.copyRawUri(uris: Array<Uri>) = Clipboardx.copyRawUri(this, uris)

/**
 * Copy raw uri
 */
inline fun Context.copyRawUri(label: CharSequence, uri: Uri) = Clipboardx.copyRawUri(this, label, uri)

/**
 * Copy raw uri
 */
inline fun Context.copyRawUri(uri: Uri) = Clipboardx.copyRawUri(this, uri)


/**
 * Copy uri
 */
inline fun Context.copyMimeTypeUri(label: CharSequence, mimeType: String, uris: Array<Uri>) = Clipboardx.copyMimeTypeUri(this, label, mimeType, uris)

/**
 * Copy uri
 */
inline fun Context.copyMimeTypeUri(mimeType: String, uris: Array<Uri>) = Clipboardx.copyMimeTypeUri(this, mimeType, uris)

/**
 * Copy uri
 */
inline fun Context.copyMimeTypeUri(label: CharSequence, mimeType: String, uri: Uri) = Clipboardx.copyMimeTypeUri(this, label, mimeType, uri)

/**
 * Copy uri
 */
inline fun Context.copyMimeTypeUri(mimeType: String, uri: Uri) = Clipboardx.copyMimeTypeUri(this, mimeType, uri)


/**
 * Copy multi type content
 */
inline fun Context.copyContents(label: CharSequence, contents: Array<ClipContent>) = Clipboardx.copyContents(this, label, contents)

/**
 * Copy multi type content
 */
inline fun Context.copyContents(contents: Array<ClipContent>) = Clipboardx.copyContents(this, contents)


/**
 * Get current clip data
 */
inline fun Context.getClipData(): ClipData? = Clipboardx.get(this)

/**
 * Get current clip label
 */
inline fun Context.getClipDataLabel(): CharSequence? = Clipboardx.getLabel(this)

/**
 * Get current clip all content
 */
inline fun Context.getClipDataContents(): Array<ClipContent>? = Clipboardx.getContents(this)


/**
 * Get current clip text data
 */
inline fun Context.getClipText(): CharSequence? = Clipboardx.getText(this)

/**
 * Get current clip all text data
 */
inline fun Context.getClipTexts(): Array<CharSequence>? = Clipboardx.getTexts(this)


/**
 * Get current clip html text data
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun Context.getClipHtmlText(): ClipHtmlText? = Clipboardx.getHtmlText(this)

/**
 * Get current clip all html text data
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
inline fun Context.getClipHtmlTexts(): Array<ClipHtmlText>? = Clipboardx.getHtmlTexts(this)


/**
 * Get current clip intent data
 */
inline fun Context.getClipIntent(): Intent? = Clipboardx.getIntent(this)

/**
 * Get current clip all intent data
 */
inline fun Context.getClipIntents(): Array<Intent>? = Clipboardx.getIntents(this)


/**
 * Get current clip uri data
 */
inline fun Context.getClipUri(): ClipUri? = Clipboardx.getUri(this)

/**
 * Get current clip all uri data
 */
inline fun Context.getClipUris(): Array<ClipUri>? = Clipboardx.getUris(this)


/**
 * Add primary clip changed listener
 */
inline fun Context.addPrimaryClipChangedListener(listener: ClipboardManager.OnPrimaryClipChangedListener) = Clipboardx.addPrimaryClipChangedListener(this, listener)

/**
 * Remove primary clip changed listener
 */
inline fun Context.removePrimaryClipChangedListener(listener: ClipboardManager.OnPrimaryClipChangedListener) = Clipboardx.removePrimaryClipChangedListener(this, listener)


/**
 * Clean clip date
 */
@RequiresApi(Build.VERSION_CODES.P)
inline fun Context.clearClip() = Clipboardx.clear(this)