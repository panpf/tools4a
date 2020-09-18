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

package com.github.panpf.tools4a.args.test.read;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.github.panpf.tools4j.lang.Stringx;

//bean for test
class TestParcelable implements Parcelable {
    public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel in) {
            return new TestParcelable(in);
        }

        @Override
        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };
    String tag;

    public TestParcelable(String tag) {
        this.tag = tag;
    }

    protected TestParcelable(Parcel in) {
        tag = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tag);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof TestParcelable) {
            return Stringx.equals(((TestParcelable) obj).tag, this.tag);
        }
        return super.equals(obj);
    }
}
