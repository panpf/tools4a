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
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.test.Testx;
import com.github.panpf.tools4a.view.ViewAnimx;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ViewAnimxTest {

    @Test
    public void testAnimAlpha() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.animAlpha(view, 1.0f, 0.0f, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener));
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, invisibleListener);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, true);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f, 500);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animAlpha(view, 1.0f, 0.0f);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testAnimTranslate() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, 0, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener));
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, invisibleListener);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, true);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, 500);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f, 3f);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.animTranslate(view, 0f, 300f, 0f, 300f);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testShakeLandscape() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.shakeLandscape(view, 10f, 7, 700, true, invisibleListener));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakeLandscape(view, invisibleListener));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakeLandscape(view, true));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakeLandscape(view, 500L));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakeLandscape(view, 15f));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakeLandscape(view));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testShakePortrait() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.shakePortrait(view, 10f, 7, 700, true, invisibleListener));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakePortrait(view, invisibleListener));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakePortrait(view, true));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakePortrait(view, 500L));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakePortrait(view, 15f));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> ViewAnimx.shakePortrait(view));
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testStartAnimFromRes() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test, true, invisibleListener));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test, invisibleListener);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test, true);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.startAnimFromRes(view, R.anim.view_anim_test);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testInvisibleByAnimAlpha() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.invisibleByAnimAlpha(view, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener));
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view, invisibleListener);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view, true);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view, 500);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.invisibleByAnimAlpha(view);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testGoneByAnimAlpha() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.goneByAnimAlpha(view, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener));
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view, invisibleListener);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view, true);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view, 500);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.goneByAnimAlpha(view);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testVisibleByAnimAlpha() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();

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

            Runx.runOnMainThread(() -> ViewAnimx.visibleByAnimAlpha(view, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener));
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view, invisibleListener);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view, true);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view, 500);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runx.runOnMainThread(() -> {
                view.setVisibility(View.VISIBLE);
                ViewAnimx.visibleByAnimAlpha(view);
            });
            try {
                Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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

        @NonNull
        public View getView() {
            return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
    }
}
