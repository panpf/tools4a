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

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.panpf.tools4a.display.Displayx;
import com.github.panpf.tools4a.run.Runx;
import com.github.panpf.tools4a.test.Testx;
import com.github.panpf.tools4a.view.Viewx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ViewxTest {

    @Test
    public void testSetLayoutSize() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final View view = activity.getView();
            try {
                Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, view.getLayoutParams().width);
                Runx.runOnMainThread(() -> Viewx.setLayoutWidth(view, 100));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertEquals(100, view.getLayoutParams().width);
            } finally {
                Runx.runOnMainThread(() -> Viewx.setLayoutSize(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            final View newView1 = Runx.runOnMainThreadSyncResult(() -> new View(activity));
            Assert.assertNull(newView1.getLayoutParams());
            Runx.runOnMainThread(() -> Viewx.setLayoutWidth(newView1, 100));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNull(newView1.getLayoutParams());

            try {
                Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, view.getLayoutParams().height);
                Runx.runOnMainThread(() -> Viewx.setLayoutHeight(view, 300));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertEquals(300, view.getLayoutParams().height);
            } finally {
                Runx.runOnMainThread(() -> Viewx.setLayoutSize(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            final View newView2 = Runx.runOnMainThreadSyncResult(() -> new View(activity));
            Assert.assertNull(newView2.getLayoutParams());
            Runx.runOnMainThread(() -> Viewx.setLayoutHeight(newView2, 300));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNull(newView2.getLayoutParams());

            try {
                Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, view.getLayoutParams().width);
                Runx.runOnMainThread(() -> Viewx.setLayoutWidthOrInitSize(view, 200, 600));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertEquals(200, view.getLayoutParams().width);
            } finally {
                Runx.runOnMainThread(() -> Viewx.setLayoutSize(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            final View newView3 = Runx.runOnMainThreadSyncResult(() -> new View(activity));
            Assert.assertNull(newView3.getLayoutParams());
            Runx.runOnMainThread(() -> Viewx.setLayoutWidthOrInitSize(newView3, 200, 600));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNotNull(newView3.getLayoutParams());
            Assert.assertEquals(200, newView3.getLayoutParams().width);
            Assert.assertEquals(600, newView3.getLayoutParams().height);

            try {
                Assert.assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, view.getLayoutParams().height);
                Runx.runOnMainThread(() -> Viewx.setLayoutHeightOrInitSize(view, 200, 600));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertEquals(600, view.getLayoutParams().height);
            } finally {
                Runx.runOnMainThread(() -> Viewx.setLayoutSize(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            final View newView4 = Runx.runOnMainThreadSyncResult(() -> new View(activity));
            Assert.assertNull(newView4.getLayoutParams());
            Runx.runOnMainThread(() -> Viewx.setLayoutHeightOrInitSize(newView4, 200, 600));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNotNull(newView4.getLayoutParams());
            Assert.assertEquals(200, newView4.getLayoutParams().width);
            Assert.assertEquals(600, newView4.getLayoutParams().height);

            Assert.assertEquals(0, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
            Runx.runOnMainThread(() -> Viewx.setLayoutMarginTop(view, 200));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(200, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
        });
    }

    @Test
    public void testAddLayoutSize() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();
            Runx.runOnMainThread(() -> Viewx.setLayoutSize(view, 100, 300));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(100, view.getLayoutParams().width);
            Assert.assertEquals(300, view.getLayoutParams().height);

            Runx.runOnMainThread(() -> {
                Viewx.addLayoutWidth(view, 100);
                Viewx.addLayoutHeight(view, 100);
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(200, view.getLayoutParams().width);
            Assert.assertEquals(400, view.getLayoutParams().height);

            Runx.runOnMainThread(() -> Viewx.addLayoutSize(view, 100, 100));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(300, view.getLayoutParams().width);
            Assert.assertEquals(500, view.getLayoutParams().height);

            Assert.assertEquals(0, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
            Runx.runOnMainThread(() -> Viewx.setLayoutMarginTop(view, 200));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(200, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
            Runx.runOnMainThread(() -> Viewx.addLayoutMarginTop(view, 200));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertEquals(400, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
        });
    }

    @Test
    public void testToBitmap() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            final View view = Testx.getActivitySync(scenario).getView();
            Bitmap bitmap = Viewx.toBitmap(view, Bitmap.Config.ARGB_8888);
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            try {
                Assert.assertFalse(bitmap.isRecycled());
            } finally {
                bitmap.recycle();
            }

            Bitmap bitmap2 = Viewx.toBitmap(view, Bitmap.Config.ARGB_8888, 0.5f);
            Assert.assertEquals(bitmapWidth / 2, bitmap2.getWidth());
            Assert.assertEquals(bitmapHeight / 2, bitmap2.getHeight());
            try {
                Assert.assertFalse(bitmap2.isRecycled());
            } finally {
                bitmap2.recycle();
            }

            Bitmap bitmap3 = Viewx.toBitmapByMaxWidth(view, Bitmap.Config.ARGB_8888, 200);
            Assert.assertEquals(200, bitmap3.getWidth());
            Assert.assertEquals((int) ((float) 200 / bitmapWidth * bitmapHeight), bitmap3.getHeight());
            try {
                Assert.assertFalse(bitmap3.isRecycled());
            } finally {
                bitmap3.recycle();
            }

            Bitmap bitmap4 = Viewx.toBitmapByMaxHeight(view, Bitmap.Config.ARGB_8888, 200);
            Assert.assertEquals((int) ((float) 200 / bitmapHeight * bitmapWidth), bitmap4.getWidth());
            Assert.assertEquals(200, bitmap4.getHeight());
            try {
                Assert.assertFalse(bitmap4.isRecycled());
            } finally {
                bitmap4.recycle();
            }
        });

    }

    @Test
    public void testInflateLayout() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final ViewGroup contentView = activity.getContentView();
            int contentViewChildCount = contentView.getChildCount();

            View childView1 = Runx.runOnMainThreadSyncResult(() -> Viewx.inflateLayout(activity, R.layout.at_test, contentView));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNotNull(childView1);
            Assert.assertNull(childView1.getParent());
            Assert.assertNotNull(childView1.getLayoutParams());
            Assert.assertEquals(contentViewChildCount, contentView.getChildCount());

            View childView2 = Runx.runOnMainThreadSyncResult(() -> Viewx.inflateLayout(activity, R.layout.at_test, contentView, true).findViewById(R.id.testAt_frame));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNotNull(childView2);
            Assert.assertNotNull(childView2.getParent());
            Assert.assertNotNull(childView2.getLayoutParams());
            Assert.assertEquals(childView2.getParent(), contentView);
            Assert.assertEquals(contentViewChildCount + 1, contentView.getChildCount());

            View childView3 = Runx.runOnMainThreadSyncResult(() -> Viewx.inflateLayout(activity, R.layout.at_test));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertNotNull(childView3);
            Assert.assertNull(childView3.getParent());
            Assert.assertNull(childView3.getLayoutParams());
            Assert.assertEquals(contentViewChildCount + 1, contentView.getChildCount());
        });
    }

    @Test
    public void testAddPaddingTopByStatusBarHeight() {
        Testx.launchActivityWithUse(TestActivity.class, scenario -> {
            TestActivity activity = Testx.getActivitySync(scenario);
            final ViewGroup contentView = activity.getContentView();
            final int oldTopPadding = contentView.getPaddingTop();
            Runx.runOnMainThreadSync(() -> Viewx.addPaddingTopByStatusBarHeight(contentView));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Assert.assertEquals(oldTopPadding + Displayx.getStatusBarHeight(activity), contentView.getPaddingTop());
            } else {
                Assert.assertEquals(oldTopPadding, contentView.getPaddingTop());
            }
        });
    }

    public static class TestActivity extends Activity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            ViewGroup viewGroup = findViewById(android.R.id.content);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.rect);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewGroup.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        @NonNull
        public View getView() {
            return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }

        @NonNull
        public ViewGroup getContentView() {
            return (ViewGroup) findViewById(android.R.id.content);
        }
    }
}
