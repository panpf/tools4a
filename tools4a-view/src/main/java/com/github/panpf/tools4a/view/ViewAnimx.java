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

package com.github.panpf.tools4a.view;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * View animation tool method
 */
public class ViewAnimx {

    /**
     * Default animation duration
     */
    public static final long DEFAULT_ANIMATION_DURATION = 400;


    private ViewAnimx() {
    }


    /**
     * Perform alpha animation
     *
     * @param fromAlpha      Initial alpha value
     * @param toAlpha        End alpha value
     * @param durationMillis Animation duration, in milliseconds
     * @param isBanClick     If true, clicks will be disabled during the animation
     * @param listener       Animation listener
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis,
                                 final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        final AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha);
        anim.setDuration(durationMillis);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
        view.startAnimation(anim);
    }

    /**
     * Perform alpha animation
     *
     * @param fromAlpha Initial alpha value
     * @param toAlpha   End alpha value
     * @param listener  Animation listener
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, @Nullable final Animation.AnimationListener listener) {
        animAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * Perform alpha animation
     *
     * @param fromAlpha  Initial alpha value
     * @param toAlpha    End alpha value
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, boolean isBanClick) {
        animAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, isBanClick, null);
    }

    /**
     * Perform alpha animation
     *
     * @param fromAlpha      Initial alpha value
     * @param toAlpha        End alpha value
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha, long durationMillis) {
        animAlpha(view, fromAlpha, toAlpha, durationMillis, false, null);
    }

    /**
     * Perform alpha animation
     *
     * @param fromAlpha Initial alpha value
     * @param toAlpha   End alpha value
     */
    public static void animAlpha(@NonNull final View view, float fromAlpha, float toAlpha) {
        animAlpha(view, fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION, false, null);
    }


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
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles, long durationMillis, final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        TranslateAnimation anim = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        anim.setDuration(durationMillis);
        if (cycles > 0.0f) {
            anim.setInterpolator(new CycleInterpolator(cycles));
        }
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
        view.startAnimation(anim);
    }

    /**
     * Perform translate animation
     *
     * @param fromXDelta Change in X coordinate to apply at the start of the animation
     * @param toXDelta   Change in X coordinate to apply at the end of the animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the animation
     * @param toYDelta   Change in Y coordinate to apply at the end of the animation
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, final boolean isBanClick) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, 0, DEFAULT_ANIMATION_DURATION, isBanClick, null);
    }

    /**
     * Perform translate animation
     *
     * @param fromXDelta Change in X coordinate to apply at the start of the animation
     * @param toXDelta   Change in X coordinate to apply at the end of the animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the animation
     * @param toYDelta   Change in Y coordinate to apply at the end of the animation
     * @param listener   Animation listener
     */
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, 0, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * Perform translate animation
     *
     * @param fromXDelta     Change in X coordinate to apply at the start of the animation
     * @param toXDelta       Change in X coordinate to apply at the end of the animation
     * @param fromYDelta     Change in Y coordinate to apply at the start of the animation
     * @param toYDelta       Change in Y coordinate to apply at the end of the animation
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long durationMillis) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, 0, durationMillis, false, null);
    }

    /**
     * Perform translate animation
     *
     * @param fromXDelta Change in X coordinate to apply at the start of the animation
     * @param toXDelta   Change in X coordinate to apply at the end of the animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the animation
     * @param toYDelta   Change in Y coordinate to apply at the end of the animation
     * @param cycles     Number of cycle executions
     */
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta,
                                     float cycles) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, cycles, DEFAULT_ANIMATION_DURATION, false, null);
    }

    /**
     * Perform translate animation
     *
     * @param fromXDelta Change in X coordinate to apply at the start of the animation
     * @param toXDelta   Change in X coordinate to apply at the end of the animation
     * @param fromYDelta Change in Y coordinate to apply at the start of the animation
     * @param toYDelta   Change in Y coordinate to apply at the end of the animation
     */
    public static void animTranslate(@NonNull final View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        animTranslate(view, fromXDelta, toXDelta, fromYDelta, toYDelta, 0, DEFAULT_ANIMATION_DURATION, false, null);
    }


    /**
     * Shake left and right
     *
     * @param extent         Shake amplitude
     * @param cycles         Number of cycle executions
     * @param durationMillis Animation duration, in milliseconds
     * @param isBanClick     If true, clicks will be disabled during the animation
     * @param listener       Animation listener
     */
    public static void shakeLandscape(@NonNull final View view, float extent, float cycles, long durationMillis,
                                      boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, 0.0f, extent, 0.0f, 0.0f, cycles, durationMillis, isBanClick, listener);
    }

    /**
     * Shake left and right
     *
     * @param listener Animation listener
     */
    public static void shakeLandscape(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        shakeLandscape(view, 10f, 7f, 700, false, listener);
    }

    /**
     * Shake left and right
     *
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void shakeLandscape(@NonNull final View view, boolean isBanClick) {
        shakeLandscape(view, 10f, 7f, 700, isBanClick, null);
    }

    /**
     * Shake left and right
     *
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void shakeLandscape(@NonNull final View view, long durationMillis) {
        shakeLandscape(view, 10f, 7f, durationMillis, false, null);
    }

    /**
     * Shake left and right
     *
     * @param extent Shake amplitude
     */
    public static void shakeLandscape(@NonNull final View view, float extent) {
        shakeLandscape(view, extent, 7f, 700, false, null);
    }

    /**
     * Shake left and right
     */
    public static void shakeLandscape(@NonNull final View view) {
        shakeLandscape(view, 10f, 7, 700, false, null);
    }


    /**
     * Shock up and down
     *
     * @param extent         Shake amplitude
     * @param cycles         Number of cycle executions
     * @param durationMillis Animation duration, in milliseconds
     * @param isBanClick     If true, clicks will be disabled during the animation
     * @param listener       Animation listener
     */
    public static void shakePortrait(@NonNull final View view, float extent, float cycles, long durationMillis,
                                     boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        animTranslate(view, 0.0f, 0.0f, 0.0f, extent, cycles, durationMillis, isBanClick, listener);
    }

    /**
     * Shock up and down
     *
     * @param listener Animation listener
     */
    public static void shakePortrait(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        shakePortrait(view, 10.f, 7f, 700, false, listener);
    }

    /**
     * Shock up and down
     *
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void shakePortrait(@NonNull final View view, boolean isBanClick) {
        shakePortrait(view, 10.f, 7f, 700, isBanClick, null);
    }

    /**
     * Shock up and down
     *
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void shakePortrait(@NonNull final View view, long durationMillis) {
        shakePortrait(view, 10.f, 7f, durationMillis, false, null);
    }

    /**
     * Shock up and down
     *
     * @param extent Shake amplitude
     */
    public static void shakePortrait(@NonNull final View view, float extent) {
        shakePortrait(view, extent, 7f, 700, false, null);
    }

    /**
     * Shock up and down
     */
    public static void shakePortrait(@NonNull final View view) {
        shakePortrait(view, 10.f, 7f, 700, false, null);
    }


    /**
     * Parse the animation from the resource file and execute
     *
     * @param animId     Animation resource ID
     * @param isBanClick If true, clicks will be disabled during the animation
     * @param listener   Animation listener
     */
    public static void startAnimFromRes(@NonNull final View view, int animId, final boolean isBanClick,
                                        @Nullable final Animation.AnimationListener listener) {
        view.clearAnimation();
        final boolean isClickable = view.isClickable();

        Animation anim = AnimationUtils.loadAnimation(view.getContext(), animId);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(false);
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                if (isClickable && isBanClick) view.setClickable(true);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
        view.startAnimation(anim);
    }

    /**
     * Parse the animation from the resource file and execute
     *
     * @param animId   Animation resource ID
     * @param listener Animation listener
     */
    public static void startAnimFromRes(@NonNull final View view, int animId, @Nullable final Animation.AnimationListener listener) {
        startAnimFromRes(view, animId, false, listener);
    }

    /**
     * Parse the animation from the resource file and execute
     *
     * @param animId     Animation resource ID
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void startAnimFromRes(@NonNull final View view, int animId, final boolean isBanClick) {
        startAnimFromRes(view, animId, isBanClick, null);
    }

    /**
     * Parse the animation from the resource file and execute
     *
     * @param animId Animation resource ID
     */
    public static void startAnimFromRes(@NonNull final View view, int animId) {
        startAnimFromRes(view, animId, false, null);
    }


    /**
     * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
     *
     * @param durationMillis Animation duration, in milliseconds
     * @param isBanClick     If true, clicks will be disabled during the animation
     * @param listener       Animation listener
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, long durationMillis,
                                            final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.INVISIBLE) return;
        animAlpha(view, 1.0f, 0.0f, durationMillis, isBanClick, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                view.setVisibility(View.INVISIBLE);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
    }

    /**
     * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
     *
     * @param listener Animation listener
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        invisibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
     *
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, final boolean isBanClick) {
        invisibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, isBanClick, null);
    }

    /**
     * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
     *
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void invisibleByAnimAlpha(@NonNull final View view, long durationMillis) {
        invisibleByAnimAlpha(view, durationMillis, false, null);
    }

    /**
     * Hide the view with a alpha animation, with visibility set to INVISIBLE at the end
     */
    public static void invisibleByAnimAlpha(@NonNull final View view) {
        invisibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }


    /**
     * Hide the view with a alpha animation, with visibility set to GONE at the end
     *
     * @param durationMillis Animation duration, in milliseconds
     * @param isBanClick     If true, clicks will be disabled during the animation
     * @param listener       Animation listener
     */
    public static void goneByAnimAlpha(@NonNull final View view, long durationMillis,
                                       final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.GONE) return;
        animAlpha(view, 1.0f, 0.0f, durationMillis, isBanClick, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationStart(anim);
            }

            @Override
            public void onAnimationRepeat(@NonNull Animation anim) {
                if (listener != null) listener.onAnimationRepeat(anim);
            }

            @Override
            public void onAnimationEnd(@NonNull Animation anim) {
                view.setVisibility(View.GONE);
                if (listener != null) listener.onAnimationEnd(anim);
            }
        });
    }

    /**
     * Hide the view with a alpha animation, with visibility set to GONE at the end
     *
     * @param listener Animation listener
     */
    public static void goneByAnimAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        goneByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * Hide the view with a alpha animation, with visibility set to GONE at the end
     *
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void goneByAnimAlpha(@NonNull final View view, final boolean isBanClick) {
        goneByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, isBanClick, null);
    }

    /**
     * Hide the view with a alpha animation, with visibility set to GONE at the end
     *
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void goneByAnimAlpha(@NonNull final View view, long durationMillis) {
        goneByAnimAlpha(view, durationMillis, false, null);
    }

    /**
     * Hide the view with a alpha animation, with visibility set to GONE at the end
     */
    public static void goneByAnimAlpha(@NonNull final View view) {
        goneByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }


    /**
     * Use alpha animation to display View
     *
     * @param durationMillis Animation duration, in milliseconds
     * @param isBanClick     If true, clicks will be disabled during the animation
     * @param listener       Animation listener
     */
    public static void visibleByAnimAlpha(@NonNull final View view, long durationMillis,
                                          final boolean isBanClick, @Nullable final Animation.AnimationListener listener) {
        if (view.getVisibility() == View.VISIBLE) return;
        view.setVisibility(View.VISIBLE);
        animAlpha(view, 0.0f, 1.0f, durationMillis, isBanClick, listener);
    }

    /**
     * Use alpha animation to display View
     *
     * @param listener Animation listener
     */
    public static void visibleByAnimAlpha(@NonNull final View view, @Nullable final Animation.AnimationListener listener) {
        visibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, listener);
    }

    /**
     * Use alpha animation to display View
     *
     * @param isBanClick If true, clicks will be disabled during the animation
     */
    public static void visibleByAnimAlpha(@NonNull final View view, final boolean isBanClick) {
        visibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, isBanClick, null);
    }

    /**
     * Use alpha animation to display View
     *
     * @param durationMillis Animation duration, in milliseconds
     */
    public static void visibleByAnimAlpha(@NonNull final View view, long durationMillis) {
        visibleByAnimAlpha(view, durationMillis, false, null);
    }

    /**
     * Use alpha animation to display View
     */
    public static void visibleByAnimAlpha(@NonNull final View view) {
        visibleByAnimAlpha(view, DEFAULT_ANIMATION_DURATION, false, null);
    }
}