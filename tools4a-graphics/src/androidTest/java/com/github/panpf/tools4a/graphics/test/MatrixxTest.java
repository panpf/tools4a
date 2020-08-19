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

package com.github.panpf.tools4a.graphics.test;

import android.graphics.Matrix;

import androidx.test.runner.AndroidJUnit4;

import com.github.panpf.tools4a.graphics.Matrixx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MatrixxTest {

    @Test
    public void testGetValue() {
        Matrix matrix = new Matrix();
        Assert.assertEquals(0f, Matrixx.getValue(matrix, Matrix.MTRANS_X), 0f);
        Assert.assertEquals(0f, Matrixx.getValue(matrix, Matrix.MTRANS_Y), 0f);
        matrix.postTranslate(100f, 210f);
        Assert.assertEquals(100f, Matrixx.getValue(matrix, Matrix.MTRANS_X), 0f);
        Assert.assertEquals(210f, Matrixx.getValue(matrix, Matrix.MTRANS_Y), 0f);

        Matrix matrix3 = new Matrix();
        Assert.assertEquals(1f, Matrixx.getValue(matrix3, Matrix.MSCALE_X), 0f);
        Assert.assertEquals(1f, Matrixx.getValue(matrix3, Matrix.MSCALE_Y), 0f);
        matrix3.postScale(3f, 4f);
        Assert.assertEquals(3f, Matrixx.getValue(matrix3, Matrix.MSCALE_X), 0f);
        Assert.assertEquals(4f, Matrixx.getValue(matrix3, Matrix.MSCALE_Y), 0f);

        Matrix matrix4 = new Matrix();
        Assert.assertEquals(0f, Matrixx.getValue(matrix4, Matrix.MSKEW_X), 0f);
        Assert.assertEquals(0f, Matrixx.getValue(matrix4, Matrix.MSKEW_Y), 0f);
        matrix4.postSkew(10f, 9f);
        Assert.assertEquals(10f, Matrixx.getValue(matrix4, Matrix.MSKEW_X), 0f);
        Assert.assertEquals(9f, Matrixx.getValue(matrix4, Matrix.MSKEW_Y), 0f);
    }

    @Test
    public void testGetScale() {
        Matrix matrix = new Matrix();
        Assert.assertEquals(1f, Matrixx.getScale(matrix), 0f);
        matrix.postScale(3f, 4f);
        Assert.assertEquals(3f, Matrixx.getScale(matrix), 0f);
        matrix.setScale(4f, 3f);
        Assert.assertEquals(4f, Matrixx.getScale(matrix), 0f);
    }
}
