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

package com.github.panpf.tools4a.view.ktx

import android.view.View
import android.view.animation.Animation
import com.github.panpf.tools4a.view.ViewAnimx


/*
 * View animation related extension methods or properties
 */


/**
 * Perform alpha animation
 *
 * @param fromAlpha      Initial alpha value
 * @param toAlpha        End alpha value
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.animAlpha(fromAlpha: Float, toAlpha: Float, durationMillis: Long, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.animAlpha(this, fromAlpha, toAlpha, durationMillis, isBanClick, listener)

/**
 * Perform alpha animation
 *
 * @param fromAlpha Initial alpha value
 * @param toAlpha   End alpha value
 * @param listener  Animation listener
 */
inline fun View.animAlpha(fromAlpha: Float, toAlpha: Float, listener: Animation.AnimationListener?) =
        ViewAnimx.animAlpha(this, fromAlpha, toAlpha, listener)

/**
 * Perform alpha animation
 *
 * @param fromAlpha  Initial alpha value
 * @param toAlpha    End alpha value
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.animAlpha(fromAlpha: Float, toAlpha: Float, isBanClick: Boolean) = ViewAnimx.animAlpha(this, fromAlpha, toAlpha, isBanClick)

/**
 * Perform alpha animation
 *
 * @param fromAlpha      Initial alpha value
 * @param toAlpha        End alpha value
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.animAlpha(fromAlpha: Float, toAlpha: Float, durationMillis: Long) = ViewAnimx.animAlpha(this, fromAlpha, toAlpha, durationMillis)

/**
 * Perform alpha animation
 *
 * @param fromAlpha Initial alpha value
 * @param toAlpha   End alpha value
 */
inline fun View.animAlpha(fromAlpha: Float, toAlpha: Float) = ViewAnimx.animAlpha(this, fromAlpha, toAlpha)


/**
 * Perform translate animation
 *
 * @param fromXDelta     Change in X coordinate to apply at the start of the animation
 * @param toXDelta       Change in X coordinate to apply at the end of the animation
 * @param fromYDelta     Change in Y coordinate to apply at the start of the animation
 * @param toYDelta       Change in Y coordinate to apply at the end of the animation
 * @param cycles         Number of cycle executions
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float,
                              cycles: Float, durationMillis: Long, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, durationMillis, isBanClick, listener)

/**
 * Perform translate animation
 *
 * @param fromXDelta Change in X coordinate to apply at the start of the animation
 * @param toXDelta   Change in X coordinate to apply at the end of the animation
 * @param fromYDelta Change in Y coordinate to apply at the start of the animation
 * @param toYDelta   Change in Y coordinate to apply at the end of the animation
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float, isBanClick: Boolean) =
        ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta, isBanClick)

/**
 * Perform translate animation
 *
 * @param fromXDelta Change in X coordinate to apply at the start of the animation
 * @param toXDelta   Change in X coordinate to apply at the end of the animation
 * @param fromYDelta Change in Y coordinate to apply at the start of the animation
 * @param toYDelta   Change in Y coordinate to apply at the end of the animation
 * @param listener   Animation listener
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float, listener: Animation.AnimationListener?) =
        ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta, listener)

/**
 * Perform translate animation
 *
 * @param fromXDelta     Change in X coordinate to apply at the start of the animation
 * @param toXDelta       Change in X coordinate to apply at the end of the animation
 * @param fromYDelta     Change in Y coordinate to apply at the start of the animation
 * @param toYDelta       Change in Y coordinate to apply at the end of the animation
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float, durationMillis: Long) =
        ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta, durationMillis)

/**
 * Perform translate animation
 *
 * @param fromXDelta Change in X coordinate to apply at the start of the animation
 * @param toXDelta   Change in X coordinate to apply at the end of the animation
 * @param fromYDelta Change in Y coordinate to apply at the start of the animation
 * @param toYDelta   Change in Y coordinate to apply at the end of the animation
 * @param cycles     Number of cycle executions
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float, cycles: Float) =
        ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles)

/**
 * Perform translate animation
 *
 * @param fromXDelta Change in X coordinate to apply at the start of the animation
 * @param toXDelta   Change in X coordinate to apply at the end of the animation
 * @param fromYDelta Change in Y coordinate to apply at the start of the animation
 * @param toYDelta   Change in Y coordinate to apply at the end of the animation
 */
inline fun View.animTranslate(fromXDelta: Float, toXDelta: Float, fromYDelta: Float, toYDelta: Float) =
        ViewAnimx.animTranslate(this, fromXDelta, toXDelta, fromYDelta, toYDelta)


/**
 * Shake left and right
 *
 * @param extent         Shake amplitude
 * @param cycles         Number of cycle executions
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.shakeLandscape(extent: Float, cycles: Float, durationMillis: Long,
                               isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.shakeLandscape(this, extent, cycles, durationMillis, isBanClick, listener)

/**
 * Shake left and right
 *
 * @param listener Animation listener
 */
inline fun View.shakeLandscape(listener: Animation.AnimationListener?) = ViewAnimx.shakeLandscape(this, listener)

/**
 * Shake left and right
 *
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.shakeLandscape(isBanClick: Boolean) = ViewAnimx.shakeLandscape(this, isBanClick)

/**
 * Shake left and right
 *
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.shakeLandscape(durationMillis: Long) = ViewAnimx.shakeLandscape(this, durationMillis)

/**
 * Shake left and right
 *
 * @param extent Shake amplitude
 */
inline fun View.shakeLandscape(extent: Float) = ViewAnimx.shakeLandscape(this, extent)

/**
 * Shake left and right
 */
inline fun View.shakeLandscape() = ViewAnimx.shakeLandscape(this)


/**
 * Shock up and down
 *
 * @param extent         Shake amplitude
 * @param cycles         Number of cycle executions
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.shakePortrait(extent: Float, cycles: Float, durationMillis: Long, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.shakePortrait(this, extent, cycles, durationMillis, isBanClick, listener)

/**
 * Shock up and down
 *
 * @param listener Animation listener
 */
inline fun View.shakePortrait(listener: Animation.AnimationListener?) = ViewAnimx.shakePortrait(this, listener)

/**
 * Shock up and down
 *
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.shakePortrait(isBanClick: Boolean) = ViewAnimx.shakePortrait(this, isBanClick)

/**
 * Shock up and down
 *
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.shakePortrait(durationMillis: Long) = ViewAnimx.shakePortrait(this, durationMillis)

/**
 * Shock up and down
 *
 * @param extent Shake amplitude
 */
inline fun View.shakePortrait(extent: Float) = ViewAnimx.shakePortrait(this, extent)

/**
 * Shock up and down
 */
inline fun View.shakePortrait() = ViewAnimx.shakePortrait(this)


/**
 * Parse the animation from the resource file and execute
 *
 * @param animId     Animation resource ID
 * @param isBanClick If true, clicks will be disabled during the animation
 * @param listener   Animation listener
 */
inline fun View.startAnimFromRes(animId: Int, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.startAnimFromRes(this, animId, isBanClick, listener)

/**
 * Parse the animation from the resource file and execute
 *
 * @param animId   Animation resource ID
 * @param listener Animation listener
 */
inline fun View.startAnimFromRes(animId: Int, listener: Animation.AnimationListener?) = ViewAnimx.startAnimFromRes(this, animId, listener)

/**
 * Parse the animation from the resource file and execute
 *
 * @param animId     Animation resource ID
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.startAnimFromRes(animId: Int, isBanClick: Boolean) = ViewAnimx.startAnimFromRes(this, animId, isBanClick)

/**
 * Parse the animation from the resource file and execute
 *
 * @param animId Animation resource ID
 */
inline fun View.startAnimFromRes(animId: Int) = ViewAnimx.startAnimFromRes(this, animId)


/**
 * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
 *
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.invisibleByAnimAlpha(durationMillis: Long, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.invisibleByAnimAlpha(this, durationMillis, isBanClick, listener)

/**
 * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
 *
 * @param listener Animation listener
 */
inline fun View.invisibleByAnimAlpha(listener: Animation.AnimationListener?) = ViewAnimx.invisibleByAnimAlpha(this, listener)

/**
 * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
 *
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.invisibleByAnimAlpha(isBanClick: Boolean) = ViewAnimx.invisibleByAnimAlpha(this, isBanClick)

/**
 * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
 *
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.invisibleByAnimAlpha(durationMillis: Long) = ViewAnimx.invisibleByAnimAlpha(this, durationMillis)

/**
 * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
 */
inline fun View.invisibleByAnimAlpha() = ViewAnimx.invisibleByAnimAlpha(this)


/**
 * Hide the view with a alpha animation, with visibility set to GONE at the end
 *
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.goneByAnimAlpha(durationMillis: Long, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.goneByAnimAlpha(this, durationMillis, isBanClick, listener)

/**
 * Hide the view with a alpha animation, with visibility set to GONE at the end
 *
 * @param listener Animation listener
 */
inline fun View.goneByAnimAlpha(listener: Animation.AnimationListener?) = ViewAnimx.goneByAnimAlpha(this, listener)

/**
 * Hide the view with a alpha animation, with visibility set to GONE at the end
 *
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.goneByAnimAlpha(isBanClick: Boolean) = ViewAnimx.goneByAnimAlpha(this, isBanClick)

/**
 * Hide the view with a alpha animation, with visibility set to GONE at the end
 *
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.goneByAnimAlpha(durationMillis: Long) = ViewAnimx.goneByAnimAlpha(this, durationMillis)

/**
 * Hide the view with a alpha animation, with visibility set to GONE at the end
 */
inline fun View.goneByAnimAlpha() = ViewAnimx.goneByAnimAlpha(this)


/**
 * Use alpha animation to display View
 *
 * @param durationMillis Animation duration, in milliseconds
 * @param isBanClick     If true, clicks will be disabled during the animation
 * @param listener       Animation listener
 */
inline fun View.visibleByAnimAlpha(durationMillis: Long, isBanClick: Boolean, listener: Animation.AnimationListener?) =
        ViewAnimx.visibleByAnimAlpha(this, durationMillis, isBanClick, listener)

/**
 * Use alpha animation to display View
 *
 * @param listener Animation listener
 */
inline fun View.visibleByAnimAlpha(listener: Animation.AnimationListener?) = ViewAnimx.visibleByAnimAlpha(this, listener)

/**
 * Use alpha animation to display View
 *
 * @param isBanClick If true, clicks will be disabled during the animation
 */
inline fun View.visibleByAnimAlpha(isBanClick: Boolean) = ViewAnimx.visibleByAnimAlpha(this, isBanClick)

/**
 * Use alpha animation to display View
 *
 * @param durationMillis Animation duration, in milliseconds
 */
inline fun View.visibleByAnimAlpha(durationMillis: Long) = ViewAnimx.visibleByAnimAlpha(this, durationMillis)

/**
 * Use alpha animation to display View
 */
inline fun View.visibleByAnimAlpha() = ViewAnimx.visibleByAnimAlpha(this)
