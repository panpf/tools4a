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

package com.github.panpf.tools4a.view.test;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.view.ViewAnimx;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ViewAnimxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getActivityRule() {
        return this.activityRule;
    }

    @Test
    public void testAnimAlpha() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, true);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, 500);
            }
        });
        Thread.sleep(500);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
    }

    @Test
    public void testAnimTranslate() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, 0, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, true);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, 500);
            }
        });
        Thread.sleep(500);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, 3f);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
    }

    @Test
    public void testShakeLandscape() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakeLandscape(view, 10f, 7, 700, true, invisibleListener);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakeLandscape(view, invisibleListener);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakeLandscape(view, true);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakeLandscape(view, 500L);
            }
        });
        Thread.sleep(500L);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakeLandscape(view, 15f);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakeLandscape(view);
            }
        });
        Thread.sleep(700);
    }

    @Test
    public void testShakePortrait() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakePortrait(view, 10f, 7, 700, true, invisibleListener);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakePortrait(view, invisibleListener);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakePortrait(view, true);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakePortrait(view, 500L);
            }
        });
        Thread.sleep(500L);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakePortrait(view, 15f);
            }
        });
        Thread.sleep(700);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.shakePortrait(view);
            }
        });
        Thread.sleep(700);
    }

    @Test
    public void testStartAnimFromRes() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test, true, invisibleListener);
            }
        });
        Thread.sleep(1000);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test, invisibleListener);
            }
        });
        Thread.sleep(1000);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test, true);
            }
        });
        Thread.sleep(1000);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test);
            }
        });
        Thread.sleep(1000);
    }

    @Test
    public void testInvisibleByAnimAlpha() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.invisibleByAnimAlpha(view, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view, true);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view, 500);
            }
        });
        Thread.sleep(500);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
    }

    @Test
    public void testGoneByAnimAlpha() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.goneByAnimAlpha(view, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view, true);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view, 500);
            }
        });
        Thread.sleep(500);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
    }

    @Test
    public void testVisibleByAnimAlpha() throws InterruptedException {
        final View view = activityRule.getActivity().getView();

        final Animation.AnimationListener invisibleListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                ViewAnimx.visibleByAnimAlpha(view, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view, invisibleListener);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view, true);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view, 500);
            }
        });
        Thread.sleep(500);

        Runx.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view);
            }
        });
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
    }

    public static class TestActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            ViewGroup viewGroup = findViewById(android.R.id.content);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.rect);
            viewGroup.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        public View getView() {
            return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
    }
}
