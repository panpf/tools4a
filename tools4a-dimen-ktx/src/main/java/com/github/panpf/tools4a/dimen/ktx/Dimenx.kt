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

package com.github.panpf.tools4a.dimen.ktx

import com.github.panpf.tools4a.dimen.Dimenx


inline val Float.dp2px: Float get() = Dimenx.dp2px(this)
inline val Int.dp2px: Float get() = Dimenx.dp2px(this)

inline val Float.dp2pxInt: Int get() = Dimenx.dp2pxInt(this)
inline val Int.dp2pxInt: Int get() = Dimenx.dp2pxInt(this)


inline val Float.sp2px: Float get() = Dimenx.sp2px(this)
inline val Int.sp2px: Float get() = Dimenx.sp2px(this)

inline val Float.sp2pxInt: Int get() = Dimenx.sp2pxInt(this)
inline val Int.sp2pxInt: Int get() = Dimenx.sp2pxInt(this)


inline val Float.pt2px: Float get() = Dimenx.pt2px(this)
inline val Int.pt2px: Float get() = Dimenx.pt2px(this)

inline val Float.pt2pxInt: Int get() = Dimenx.pt2pxInt(this)
inline val Int.pt2pxInt: Int get() = Dimenx.pt2pxInt(this)


inline val Float.in2px: Float get() = Dimenx.in2px(this)
inline val Int.in2px: Float get() = Dimenx.in2px(this)

inline val Float.in2pxInt: Int get() = Dimenx.in2pxInt(this)
inline val Int.in2pxInt: Int get() = Dimenx.in2pxInt(this)


inline val Float.mm2px: Float get() = Dimenx.mm2px(this)
inline val Int.mm2px: Float get() = Dimenx.mm2px(this)

inline val Float.mm2pxInt: Int get() = Dimenx.mm2pxInt(this)
inline val Int.mm2pxInt: Int get() = Dimenx.mm2pxInt(this)


inline val Float.px2dp: Float get() = Dimenx.px2dp(this)
inline val Int.px2dp: Float get() = Dimenx.px2dp(this)

inline val Float.px2sp: Float get() = Dimenx.px2sp(this)
inline val Int.px2sp: Float get() = Dimenx.px2sp(this)

inline val Float.px2pt: Float get() = Dimenx.px2pt(this)
inline val Int.px2pt: Float get() = Dimenx.px2pt(this)

inline val Float.px2in: Float get() = Dimenx.px2in(this)
inline val Int.px2in: Float get() = Dimenx.px2in(this)

inline val Float.px2mm: Float get() = Dimenx.px2mm(this)
inline val Int.px2mm: Float get() = Dimenx.px2mm(this)