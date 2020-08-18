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

package com.github.panpf.tools4a.args;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;

public class Argsx {

    private Argsx() {
    }

    
    /* ************************************* Activity Intent Args ***************************************** */


    public static byte readByteArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        byte value = intent.getByteExtra(argName, Byte.MIN_VALUE);
        if (value != Byte.MIN_VALUE || intent.getByteExtra(argName, Byte.MAX_VALUE) != Byte.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Byte.parseByte(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static byte readByteArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        Intent intent = activity.getIntent();
        byte value = intent.getByteExtra(argName, Byte.MIN_VALUE);
        if (value != Byte.MIN_VALUE || intent.getByteExtra(argName, Byte.MAX_VALUE) != Byte.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Byte.parseByte(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Byte readByteArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        byte value = intent.getByteExtra(argName, Byte.MIN_VALUE);
        if (value != Byte.MIN_VALUE || intent.getByteExtra(argName, Byte.MAX_VALUE) != Byte.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Byte.parseByte(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static byte[] readByteArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        byte[] bytes = activity.getIntent().getByteArrayExtra(argName);
        if (bytes != null) return bytes;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull byte[] defaultValue) {
        byte[] bytes = activity.getIntent().getByteArrayExtra(argName);
        return bytes != null ? bytes : defaultValue;
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getByteArrayExtra(argName);
    }


    public static short readShortArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        short value = intent.getShortExtra(argName, Short.MIN_VALUE);
        if (value != Short.MIN_VALUE || intent.getShortExtra(argName, Short.MAX_VALUE) != Short.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Short.parseShort(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static short readShortArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        Intent intent = activity.getIntent();
        short value = intent.getShortExtra(argName, Short.MIN_VALUE);
        if (value != Short.MIN_VALUE || intent.getShortExtra(argName, Short.MAX_VALUE) != Short.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Short.parseShort(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Short readShortArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        short value = intent.getShortExtra(argName, Short.MIN_VALUE);
        if (value != Short.MIN_VALUE || intent.getShortExtra(argName, Short.MAX_VALUE) != Short.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Short.parseShort(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static short[] readShortArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        short[] shorts = activity.getIntent().getShortArrayExtra(argName);
        if (shorts != null) return shorts;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull short[] defaultValue) {
        short[] shorts = activity.getIntent().getShortArrayExtra(argName);
        return shorts != null ? shorts : defaultValue;
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getShortArrayExtra(argName);
    }


    public static int readIntArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        int value = intent.getIntExtra(argName, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE || intent.getIntExtra(argName, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static int readIntArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        Intent intent = activity.getIntent();
        int value = intent.getIntExtra(argName, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE || intent.getIntExtra(argName, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Integer readIntArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        int value = intent.getIntExtra(argName, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE || intent.getIntExtra(argName, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static int[] readIntArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        int[] ints = activity.getIntent().getIntArrayExtra(argName);
        if (ints != null) return ints;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull int[] defaultValue) {
        int[] ints = activity.getIntent().getIntArrayExtra(argName);
        return ints != null ? ints : defaultValue;
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getIntArrayExtra(argName);
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<Integer> integers = activity.getIntent().getIntegerArrayListExtra(argName);
        if (integers != null) return integers;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<Integer> defaultValue) {
        ArrayList<Integer> integers = activity.getIntent().getIntegerArrayListExtra(argName);
        return integers != null ? integers : defaultValue;
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getIntegerArrayListExtra(argName);
    }


    public static long readLongArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        long value = intent.getLongExtra(argName, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE || intent.getLongExtra(argName, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static long readLongArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        Intent intent = activity.getIntent();
        long value = intent.getLongExtra(argName, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE || intent.getLongExtra(argName, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Long readLongArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        long value = intent.getLongExtra(argName, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE || intent.getLongExtra(argName, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static long[] readLongArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        long[] longs = activity.getIntent().getLongArrayExtra(argName);
        if (longs != null) return longs;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull long[] defaultValue) {
        long[] longs = activity.getIntent().getLongArrayExtra(argName);
        return longs != null ? longs : defaultValue;
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getLongArrayExtra(argName);
    }


    public static float readFloatArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        float value = intent.getFloatExtra(argName, Float.MIN_VALUE);
        if (value != Float.MIN_VALUE || intent.getFloatExtra(argName, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static float readFloatArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        Intent intent = activity.getIntent();
        float value = intent.getFloatExtra(argName, Float.MIN_VALUE);
        if (value != Float.MIN_VALUE || intent.getFloatExtra(argName, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Float readFloatArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        float value = intent.getFloatExtra(argName, Float.MIN_VALUE);
        if (value != Float.MIN_VALUE || intent.getFloatExtra(argName, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static float[] readFloatArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        float[] floats = activity.getIntent().getFloatArrayExtra(argName);
        if (floats != null) return floats;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull float[] defaultValue) {
        float[] floats = activity.getIntent().getFloatArrayExtra(argName);
        return floats != null ? floats : defaultValue;
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getFloatArrayExtra(argName);
    }


    public static double readDoubleArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        double value = intent.getDoubleExtra(argName, Double.MIN_VALUE);
        if (value != Double.MIN_VALUE || intent.getDoubleExtra(argName, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static double readDoubleArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        Intent intent = activity.getIntent();
        double value = intent.getDoubleExtra(argName, Double.MIN_VALUE);
        if (value != Double.MIN_VALUE || intent.getDoubleExtra(argName, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Double readDoubleArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        double value = intent.getDoubleExtra(argName, Double.MIN_VALUE);
        if (value != Double.MIN_VALUE || intent.getDoubleExtra(argName, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static double[] readDoubleArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        double[] doubles = activity.getIntent().getDoubleArrayExtra(argName);
        if (doubles != null) return doubles;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull double[] defaultValue) {
        double[] doubles = activity.getIntent().getDoubleArrayExtra(argName);
        return doubles != null ? doubles : defaultValue;
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getDoubleArrayExtra(argName);
    }


    public static boolean readBooleanArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        boolean value = intent.getBooleanExtra(argName, false);
        if (value || !intent.getBooleanExtra(argName, true)) return value;
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static boolean readBooleanArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        Intent intent = activity.getIntent();
        boolean value = intent.getBooleanExtra(argName, false);
        if (value || !intent.getBooleanExtra(argName, true)) return value;
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Boolean readBooleanArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        boolean value = intent.getBooleanExtra(argName, false);
        if (value || !intent.getBooleanExtra(argName, true)) return value;
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static boolean[] readBooleanArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        boolean[] booleans = activity.getIntent().getBooleanArrayExtra(argName);
        if (booleans != null) return booleans;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull boolean[] defaultValue) {
        boolean[] booleans = activity.getIntent().getBooleanArrayExtra(argName);
        return booleans != null ? booleans : defaultValue;
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getBooleanArrayExtra(argName);
    }


    public static char readCharArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        char value = intent.getCharExtra(argName, Character.MIN_VALUE);
        if (value != Character.MIN_VALUE || intent.getCharExtra(argName, Character.MAX_VALUE) != Character.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return (char) Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static char readCharArgOr(@NonNull Activity activity, @NonNull String argName, char defaultValue) {
        Intent intent = activity.getIntent();
        char value = intent.getCharExtra(argName, Character.MIN_VALUE);
        if (value != Character.MIN_VALUE || intent.getCharExtra(argName, Character.MAX_VALUE) != Character.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return (char) Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Character readCharArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Intent intent = activity.getIntent();
        char value = intent.getCharExtra(argName, Character.MIN_VALUE);
        if (value != Character.MIN_VALUE || intent.getCharExtra(argName, Character.MAX_VALUE) != Character.MAX_VALUE) {
            return value;
        }
        String stringValue = intent.getStringExtra(argName);
        if (stringValue != null) {
            try {
                return (char) Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static char[] readCharArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        char[] chars = activity.getIntent().getCharArrayExtra(argName);
        if (chars != null) return chars;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull char[] defaultValue) {
        char[] chars = activity.getIntent().getCharArrayExtra(argName);
        return chars != null ? chars : defaultValue;
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharArrayExtra(argName);
    }


    @NonNull
    public static CharSequence readCharSequenceArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        CharSequence charSequence = activity.getIntent().getCharSequenceExtra(argName);
        if (charSequence != null) return charSequence;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull CharSequence defaultValue) {
        CharSequence charSequence = activity.getIntent().getCharSequenceExtra(argName);
        return charSequence != null && !"".equals(charSequence.toString().trim()) ? charSequence : defaultValue;
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharSequenceExtra(argName);
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        CharSequence[] charSequences = activity.getIntent().getCharSequenceArrayExtra(argName);
        if (charSequences != null) return charSequences;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull CharSequence[] defaultValue) {
        CharSequence[] charSequences = activity.getIntent().getCharSequenceArrayExtra(argName);
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharSequenceArrayExtra(argName);
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<CharSequence> charSequences = activity.getIntent().getCharSequenceArrayListExtra(argName);
        if (charSequences != null) return charSequences;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<CharSequence> defaultValue) {
        ArrayList<CharSequence> charSequences = activity.getIntent().getCharSequenceArrayListExtra(argName);
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharSequenceArrayListExtra(argName);
    }


    @NonNull
    public static String readStringArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        String string = activity.getIntent().getStringExtra(argName);
        if (string != null) return string;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static String readStringArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull String defaultValue) {
        String string = activity.getIntent().getStringExtra(argName);
        return string != null && !"".equals(string.trim()) ? string : defaultValue;
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getStringExtra(argName);
    }


    @NonNull
    public static String[] readStringArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        String[] strings = activity.getIntent().getStringArrayExtra(argName);
        if (strings != null) return strings;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull String[] defaultValue) {
        String[] strings = activity.getIntent().getStringArrayExtra(argName);
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getStringArrayExtra(argName);
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<String> strings = activity.getIntent().getStringArrayListExtra(argName);
        if (strings != null) return strings;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<String> defaultValue) {
        ArrayList<String> strings = activity.getIntent().getStringArrayListExtra(argName);
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getStringArrayListExtra(argName);
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        V parcelable = activity.getIntent().getParcelableExtra(argName);
        if (parcelable != null) return parcelable;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull V defaultValue) {
        V parcelable = activity.getIntent().getParcelableExtra(argName);
        return parcelable != null ? parcelable : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getParcelableExtra(argName);
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        V[] parcelables = (V[]) activity.getIntent().getParcelableArrayExtra(argName);
        if (parcelables != null) return parcelables;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull V[] defaultValue) {
        //noinspection unchecked
        V[] parcelables = (V[]) activity.getIntent().getParcelableArrayExtra(argName);
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        return (V[]) activity.getIntent().getParcelableArrayExtra(argName);
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<V> parcelables = activity.getIntent().getParcelableArrayListExtra(argName);
        if (parcelables != null) return parcelables;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<V> defaultValue) {
        ArrayList<V> parcelables = activity.getIntent().getParcelableArrayListExtra(argName);
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getParcelableArrayListExtra(argName);
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        V serializable = (V) activity.getIntent().getSerializableExtra(argName);
        if (serializable != null) return serializable;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull V defaultValue) {
        //noinspection unchecked
        V serializable = (V) activity.getIntent().getSerializableExtra(argName);
        return serializable != null ? serializable : defaultValue;
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        return (V) activity.getIntent().getSerializableExtra(argName);
    }


    @NonNull
    public static Bundle readBundleArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Bundle bundle = activity.getIntent().getBundleExtra(argName);
        if (bundle != null) return bundle;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull Bundle defaultValue) {
        Bundle bundle = activity.getIntent().getBundleExtra(argName);
        return bundle != null ? bundle : defaultValue;
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getBundleExtra(argName);
    }


    @NonNull
    public static Bundle readExtrasArgOrThrow(@NonNull Activity activity) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) return extras;
        throw (new IllegalArgumentException("Not found 'extras'"));
    }

    @NonNull
    public static Bundle readExtrasArgOr(@NonNull Activity activity, @NonNull Bundle defaultValue) {
        Bundle extras = activity.getIntent().getExtras();
        return extras != null ? extras : defaultValue;
    }

    @Nullable
    public static Bundle readExtrasArgOrNull(@NonNull Activity activity) {
        return activity.getIntent().getExtras();
    }


    /* ************************************* Activity Uri Args ***************************************** */


    public static byte readByteUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Byte.parseByte(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static byte readByteUriArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Byte.parseByte(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Byte readByteUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Byte.parseByte(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static short readShortUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Short.parseShort(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static short readShortUriArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Short.parseShort(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Short readShortUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Short.parseShort(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static int readIntUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Integer.parseInt(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static int readIntUriArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Integer readIntUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static long readLongUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Long.parseLong(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static long readLongUriArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Long readLongUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static float readFloatUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Float.parseFloat(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static float readFloatUriArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Float readFloatUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static double readDoubleUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Double.parseDouble(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static double readDoubleUriArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Double readDoubleUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean readBooleanUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return Boolean.parseBoolean(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static boolean readBooleanUriArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Boolean.parseBoolean(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Boolean readBooleanUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return Boolean.parseBoolean(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    @NonNull
    public static String readStringUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        if (uri == null) throw new IllegalArgumentException("Intent data is null");
        String value = uri.getQueryParameter(argName);
        value = value != null ? value.trim() : "";
        if (!"".equals(value)) return value;
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    @NonNull
    public static String readStringUriArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull String defaultValue) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return defaultValue;
        try {
            return value;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static String readStringUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Uri uri = activity.getIntent().getData();
        String value = uri != null ? uri.getQueryParameter(argName) : null;
        value = value != null ? value.trim() : "";
        if ("".equals(value.trim())) return null;
        try {
            return value;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    /* ************************************* Activity Uri Intent Args ***************************************** */


    public static byte readByteUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Byte value = readByteUriArgOrNull(activity, argName);
        return value != null ? value : readByteArgOrThrow(activity, argName);
    }

    public static byte readByteUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        Byte value = readByteUriArgOrNull(activity, argName);
        return value != null ? value : readByteArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Byte readByteUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Byte value = readByteUriArgOrNull(activity, argName);
        return value != null ? value : readByteArgOrNull(activity, argName);
    }


    public static short readShortUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Short value = readShortUriArgOrNull(activity, argName);
        return value != null ? value : readShortArgOrThrow(activity, argName);
    }

    public static short readShortUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        Short value = readShortUriArgOrNull(activity, argName);
        return value != null ? value : readShortArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Short readShortUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Short value = readShortUriArgOrNull(activity, argName);
        return value != null ? value : readShortArgOrNull(activity, argName);
    }


    public static int readIntUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Integer value = readIntUriArgOrNull(activity, argName);
        return value != null ? value : readIntArgOrThrow(activity, argName);
    }

    public static int readIntUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        Integer value = readIntUriArgOrNull(activity, argName);
        return value != null ? value : readIntArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Integer readIntUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Integer value = readIntUriArgOrNull(activity, argName);
        return value != null ? value : readIntArgOrNull(activity, argName);
    }


    public static long readLongUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Long value = readLongUriArgOrNull(activity, argName);
        return value != null ? value : readLongArgOrThrow(activity, argName);
    }

    public static long readLongUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        Long value = readLongUriArgOrNull(activity, argName);
        return value != null ? value : readLongArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Long readLongUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Long value = readLongUriArgOrNull(activity, argName);
        return value != null ? value : readLongArgOrNull(activity, argName);
    }


    public static float readFloatUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Float value = readFloatUriArgOrNull(activity, argName);
        return value != null ? value : readFloatArgOrThrow(activity, argName);
    }

    public static float readFloatUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        Float value = readFloatUriArgOrNull(activity, argName);
        return value != null ? value : readFloatArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Float readFloatUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Float value = readFloatUriArgOrNull(activity, argName);
        return value != null ? value : readFloatArgOrNull(activity, argName);
    }


    public static double readDoubleUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Double value = readDoubleUriArgOrNull(activity, argName);
        return value != null ? value : readDoubleArgOrThrow(activity, argName);
    }

    public static double readDoubleUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        Double value = readDoubleUriArgOrNull(activity, argName);
        return value != null ? value : readDoubleArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Double readDoubleUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Double value = readDoubleUriArgOrNull(activity, argName);
        return value != null ? value : readDoubleArgOrNull(activity, argName);
    }


    public static boolean readBooleanUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Boolean value = readBooleanUriArgOrNull(activity, argName);
        return value != null ? value : readBooleanArgOrThrow(activity, argName);
    }

    public static boolean readBooleanUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        Boolean value = readBooleanUriArgOrNull(activity, argName);
        return value != null ? value : readBooleanArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Boolean readBooleanUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Boolean value = readBooleanUriArgOrNull(activity, argName);
        return value != null ? value : readBooleanArgOrNull(activity, argName);
    }


    @NonNull
    public static String readStringUriIntentArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        String value = readStringUriArgOrNull(activity, argName);
        return value != null ? value : readStringArgOrThrow(activity, argName);
    }

    @NonNull
    public static String readStringUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, String defaultValue) {
        String value = readStringUriArgOrNull(activity, argName);
        return value != null ? value : readStringArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        String value = readStringUriArgOrNull(activity, argName);
        return value != null ? value : readStringArgOrNull(activity, argName);
    }


    /* ************************************* Activity Intent Uri Args ***************************************** */


    public static byte readByteIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Byte value = readByteArgOrNull(activity, argName);
        return value != null ? value : readByteUriArgOrThrow(activity, argName);
    }

    public static byte readByteIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        Byte value = readByteArgOrNull(activity, argName);
        return value != null ? value : readByteUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Byte readByteIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Byte value = readByteArgOrNull(activity, argName);
        return value != null ? value : readByteUriArgOrNull(activity, argName);
    }


    public static short readShortIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Short value = readShortArgOrNull(activity, argName);
        return value != null ? value : readShortUriArgOrThrow(activity, argName);
    }

    public static short readShortIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        Short value = readShortArgOrNull(activity, argName);
        return value != null ? value : readShortUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Short readShortIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Short value = readShortArgOrNull(activity, argName);
        return value != null ? value : readShortUriArgOrNull(activity, argName);
    }


    public static int readIntIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Integer value = readIntArgOrNull(activity, argName);
        return value != null ? value : readIntUriArgOrThrow(activity, argName);
    }

    public static int readIntIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        Integer value = readIntArgOrNull(activity, argName);
        return value != null ? value : readIntUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Integer readIntIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Integer value = readIntArgOrNull(activity, argName);
        return value != null ? value : readIntUriArgOrNull(activity, argName);
    }


    public static long readLongIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Long value = readLongArgOrNull(activity, argName);
        return value != null ? value : readLongUriArgOrThrow(activity, argName);
    }

    public static long readLongIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        Long value = readLongArgOrNull(activity, argName);
        return value != null ? value : readLongUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Long readLongIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Long value = readLongArgOrNull(activity, argName);
        return value != null ? value : readLongUriArgOrNull(activity, argName);
    }


    public static float readFloatIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Float value = readFloatArgOrNull(activity, argName);
        return value != null ? value : readFloatUriArgOrThrow(activity, argName);
    }

    public static float readFloatIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        Float value = readFloatArgOrNull(activity, argName);
        return value != null ? value : readFloatUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Float readFloatIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Float value = readFloatArgOrNull(activity, argName);
        return value != null ? value : readFloatUriArgOrNull(activity, argName);
    }


    public static double readDoubleIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Double value = readDoubleArgOrNull(activity, argName);
        return value != null ? value : readDoubleUriArgOrThrow(activity, argName);
    }

    public static double readDoubleIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        Double value = readDoubleArgOrNull(activity, argName);
        return value != null ? value : readDoubleUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Double readDoubleIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Double value = readDoubleArgOrNull(activity, argName);
        return value != null ? value : readDoubleUriArgOrNull(activity, argName);
    }


    public static boolean readBooleanIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        Boolean value = readBooleanArgOrNull(activity, argName);
        return value != null ? value : readBooleanUriArgOrThrow(activity, argName);
    }

    public static boolean readBooleanIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        Boolean value = readBooleanArgOrNull(activity, argName);
        return value != null ? value : readBooleanUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static Boolean readBooleanIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Boolean value = readBooleanArgOrNull(activity, argName);
        return value != null ? value : readBooleanUriArgOrNull(activity, argName);
    }


    @NonNull
    public static String readStringIntentUriArgOrThrow(@NonNull Activity activity, @NonNull String argName) {
        String value = readStringArgOrNull(activity, argName);
        return value != null && !value.isEmpty() ? value : readStringUriArgOrThrow(activity, argName);
    }

    @NonNull
    public static String readStringIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, String defaultValue) {
        String value = readStringArgOrNull(activity, argName);
        return value != null && !value.isEmpty() ? value : readStringUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        String value = readStringArgOrNull(activity, argName);
        return value != null && !value.isEmpty() ? value : readStringUriArgOrNull(activity, argName);
    }


    /* ************************************* Activity Intent Args ***************************************** */


    public static byte readByteArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static byte readByteArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Byte readByteArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static byte[] readByteArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull byte[] defaultValue) {
        return Argsx.readByteArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static short readShortArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static short readShortArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Short readShortArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static short[] readShortArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull short[] defaultValue) {
        return Argsx.readShortArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static int readIntArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static int readIntArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Integer readIntArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static int[] readIntArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull int[] defaultValue) {
        return Argsx.readIntArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<Integer> defaultValue) {
        return Argsx.readIntArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    public static long readLongArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static long readLongArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Long readLongArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static long[] readLongArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull long[] defaultValue) {
        return Argsx.readLongArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static float readFloatArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static float readFloatArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Float readFloatArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static float[] readFloatArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull float[] defaultValue) {
        return Argsx.readFloatArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static double readDoubleArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static double readDoubleArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Double readDoubleArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static double[] readDoubleArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull double[] defaultValue) {
        return Argsx.readDoubleArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static boolean readBooleanArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static boolean readBooleanArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Boolean readBooleanArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static boolean[] readBooleanArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull boolean[] defaultValue) {
        return Argsx.readBooleanArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static char readCharArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static char readCharArgOr(@NonNull Activity activity, @StringRes int argNameResId, char defaultValue) {
        return Argsx.readCharArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Character readCharArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static char[] readCharArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull char[] defaultValue) {
        return Argsx.readCharArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static CharSequence readCharSequenceArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull CharSequence defaultValue) {
        return Argsx.readCharSequenceArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull CharSequence[] defaultValue) {
        return Argsx.readCharSequenceArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<CharSequence> defaultValue) {
        return Argsx.readCharSequenceArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String readStringArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String[] readStringArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull String[] defaultValue) {
        return Argsx.readStringArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<String> defaultValue) {
        return Argsx.readStringArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readParcelableArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull V[] defaultValue) {
        return Argsx.readParcelableArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<V> defaultValue) {
        return Argsx.readParcelableArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readSerializableArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static Bundle readBundleArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull Bundle defaultValue) {
        return Argsx.readBundleArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* Activity Uri Args ***************************************** */


    public static byte readByteUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static byte readByteUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Byte readByteUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static short readShortUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static short readShortUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Short readShortUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static int readIntUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static int readIntUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Integer readIntUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static long readLongUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static long readLongUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Long readLongUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static float readFloatUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static float readFloatUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Float readFloatUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static double readDoubleUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static double readDoubleUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Double readDoubleUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static boolean readBooleanUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static boolean readBooleanUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Boolean readBooleanUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanUriArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String readStringUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* Activity Uri Intent Args ***************************************** */


    public static byte readByteUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static byte readByteUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Byte readByteUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    public static short readShortUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static short readShortUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Short readShortUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    public static int readIntUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static int readIntUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Integer readIntUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    public static long readLongUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static long readLongUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Long readLongUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    public static float readFloatUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static float readFloatUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Float readFloatUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    public static double readDoubleUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static double readDoubleUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Double readDoubleUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    public static boolean readBooleanUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static boolean readBooleanUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Boolean readBooleanUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String readStringUriIntentArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriIntentArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, String defaultValue) {
        return Argsx.readStringUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* Activity Intent Uri Args ***************************************** */


    public static byte readByteIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static byte readByteIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Byte readByteIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static short readShortIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static short readShortIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Short readShortIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static int readIntIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static int readIntIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Integer readIntIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static long readLongIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static long readLongIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Long readLongIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static float readFloatIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static float readFloatIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Float readFloatIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static double readDoubleIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static double readDoubleIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Double readDoubleIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static boolean readBooleanIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    public static boolean readBooleanIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Boolean readBooleanIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String readStringIntentUriArgOrThrow(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringIntentUriArgOrThrow(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, String defaultValue) {
        return Argsx.readStringIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* SupportFragment Args ***************************************** */


    public static byte readByteArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        byte value = arguments.getByte(argName, Byte.MIN_VALUE);
        if (value != Byte.MIN_VALUE || arguments.getByte(argName, Byte.MAX_VALUE) != Byte.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Byte.parseByte(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static byte readByteArgOr(@NonNull Fragment fragment, @NonNull String argName, byte defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        byte value = arguments.getByte(argName, Byte.MIN_VALUE);
        if (value != Byte.MIN_VALUE || arguments.getByte(argName, Byte.MAX_VALUE) != Byte.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Byte.parseByte(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Byte readByteArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        byte value = arguments.getByte(argName, Byte.MIN_VALUE);
        if (value != Byte.MIN_VALUE || arguments.getByte(argName, Byte.MAX_VALUE) != Byte.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Byte.parseByte(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static byte[] readByteArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        byte[] bytes = arguments != null ? arguments.getByteArray(argName) : null;
        if (bytes != null) return bytes;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull byte[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        byte[] bytes = arguments != null ? arguments.getByteArray(argName) : null;
        return bytes != null ? bytes : defaultValue;
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getByteArray(argName) : null;
    }


    public static short readShortArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        short value = arguments.getShort(argName, Short.MIN_VALUE);
        if (value != Short.MIN_VALUE || arguments.getShort(argName, Short.MAX_VALUE) != Short.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Short.parseShort(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static short readShortArgOr(@NonNull Fragment fragment, @NonNull String argName, short defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        short value = arguments.getShort(argName, Short.MIN_VALUE);
        if (value != Short.MIN_VALUE || arguments.getShort(argName, Short.MAX_VALUE) != Short.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Short.parseShort(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Short readShortArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        short value = arguments.getShort(argName, Short.MIN_VALUE);
        if (value != Short.MIN_VALUE || arguments.getShort(argName, Short.MAX_VALUE) != Short.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Short.parseShort(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static short[] readShortArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        short[] shorts = arguments != null ? arguments.getShortArray(argName) : null;
        if (shorts != null) return shorts;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull short[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        short[] shorts = arguments != null ? arguments.getShortArray(argName) : null;
        return shorts != null ? shorts : defaultValue;
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getShortArray(argName) : null;
    }


    public static int readIntArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        int value = arguments.getInt(argName, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE || arguments.getInt(argName, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static int readIntArgOr(@NonNull Fragment fragment, @NonNull String argName, int defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        int value = arguments.getInt(argName, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE || arguments.getInt(argName, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Integer readIntArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        int value = arguments.getInt(argName, Integer.MIN_VALUE);
        if (value != Integer.MIN_VALUE || arguments.getInt(argName, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static int[] readIntArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        int[] ints = arguments != null ? arguments.getIntArray(argName) : null;
        if (ints != null) return ints;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull int[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        int[] ints = arguments != null ? arguments.getIntArray(argName) : null;
        return ints != null ? ints : defaultValue;
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getIntArray(argName) : null;
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<Integer> ints = arguments != null ? arguments.getIntegerArrayList(argName) : null;
        if (ints != null) return ints;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull ArrayList<Integer> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<Integer> integers = arguments != null ? arguments.getIntegerArrayList(argName) : null;
        return integers != null ? integers : defaultValue;
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getIntegerArrayList(argName) : null;
    }


    public static long readLongArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        long value = arguments.getLong(argName, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE || arguments.getLong(argName, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static long readLongArgOr(@NonNull Fragment fragment, @NonNull String argName, long defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        long value = arguments.getLong(argName, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE || arguments.getLong(argName, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Long readLongArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        long value = arguments.getLong(argName, Long.MIN_VALUE);
        if (value != Long.MIN_VALUE || arguments.getLong(argName, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static long[] readLongArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        long[] longs = arguments != null ? arguments.getLongArray(argName) : null;
        if (longs != null) return longs;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull long[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        long[] longs = arguments != null ? arguments.getLongArray(argName) : null;
        return longs != null ? longs : defaultValue;
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getLongArray(argName) : null;
    }


    public static float readFloatArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        float value = arguments.getFloat(argName, Float.MIN_VALUE);
        if (value != Float.MIN_VALUE || arguments.getFloat(argName, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static float readFloatArgOr(@NonNull Fragment fragment, @NonNull String argName, float defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        float value = arguments.getFloat(argName, Float.MIN_VALUE);
        if (value != Float.MIN_VALUE || arguments.getFloat(argName, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Float readFloatArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        float value = arguments.getFloat(argName, Float.MIN_VALUE);
        if (value != Float.MIN_VALUE || arguments.getFloat(argName, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Float.parseFloat(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static float[] readFloatArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        float[] floats = arguments != null ? arguments.getFloatArray(argName) : null;
        if (floats != null) return floats;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull float[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        float[] floats = arguments != null ? arguments.getFloatArray(argName) : null;
        return floats != null ? floats : defaultValue;
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getFloatArray(argName) : null;
    }


    public static double readDoubleArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        double value = arguments.getDouble(argName, Double.MIN_VALUE);
        if (value != Double.MIN_VALUE || arguments.getDouble(argName, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static double readDoubleArgOr(@NonNull Fragment fragment, @NonNull String argName, double defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        double value = arguments.getDouble(argName, Double.MIN_VALUE);
        if (value != Double.MIN_VALUE || arguments.getDouble(argName, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Double readDoubleArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        double value = arguments.getDouble(argName, Double.MIN_VALUE);
        if (value != Double.MIN_VALUE || arguments.getDouble(argName, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Double.parseDouble(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static double[] readDoubleArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        double[] doubles = arguments != null ? arguments.getDoubleArray(argName) : null;
        if (doubles != null) return doubles;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull double[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        double[] doubles = arguments != null ? arguments.getDoubleArray(argName) : null;
        return doubles != null ? doubles : defaultValue;
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getDoubleArray(argName) : null;
    }


    public static boolean readBooleanArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        boolean value = arguments.getBoolean(argName, false);
        if (value || !arguments.getBoolean(argName, true)) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static boolean readBooleanArgOr(@NonNull Fragment fragment, @NonNull String argName, boolean defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        boolean value = arguments.getBoolean(argName, false);
        if (value || !arguments.getBoolean(argName, true)) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Boolean readBooleanArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        boolean value = arguments.getBoolean(argName, false);
        if (value || !arguments.getBoolean(argName, true)) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return Boolean.parseBoolean(stringValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static boolean[] readBooleanArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        boolean[] booleans = arguments != null ? arguments.getBooleanArray(argName) : null;
        if (booleans != null) return booleans;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull boolean[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        boolean[] booleans = arguments != null ? arguments.getBooleanArray(argName) : null;
        return booleans != null ? booleans : defaultValue;
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBooleanArray(argName) : null;
    }


    public static char readCharArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null)
            throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
        char value = arguments.getChar(argName, Character.MIN_VALUE);
        if (value != Character.MIN_VALUE || arguments.getChar(argName, Character.MAX_VALUE) != Character.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return (char) Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    public static char readCharArgOr(@NonNull Fragment fragment, @NonNull String argName, char defaultValue) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return defaultValue;
        char value = arguments.getChar(argName, Character.MIN_VALUE);
        if (value != Character.MIN_VALUE || arguments.getChar(argName, Character.MAX_VALUE) != Character.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return (char) Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Nullable
    public static Character readCharArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) return null;
        char value = arguments.getChar(argName, Character.MIN_VALUE);
        if (value != Character.MIN_VALUE || arguments.getChar(argName, Character.MAX_VALUE) != Character.MAX_VALUE) {
            return value;
        }
        String stringValue = arguments.getString(argName);
        if (stringValue != null) {
            try {
                return (char) Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @NonNull
    public static char[] readCharArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        char[] chars = arguments != null ? arguments.getCharArray(argName) : null;
        if (chars != null) return chars;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull char[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        char[] chars = arguments != null ? arguments.getCharArray(argName) : null;
        return chars != null ? chars : defaultValue;
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharArray(argName) : null;
    }


    @NonNull
    public static CharSequence readCharSequenceArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        CharSequence charSequence = arguments != null ? arguments.getCharSequence(argName) : null;
        if (charSequence != null) return charSequence;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull CharSequence defaultValue) {
        Bundle arguments = fragment.getArguments();
        CharSequence charSequence = arguments != null ? arguments.getCharSequence(argName, defaultValue) : null;
        return charSequence != null ? charSequence : defaultValue;
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequence(argName) : null;
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        CharSequence[] charSequences = arguments != null ? arguments.getCharSequenceArray(argName) : null;
        if (charSequences != null) return charSequences;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull CharSequence[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        CharSequence[] charSequences = arguments != null ? arguments.getCharSequenceArray(argName) : null;
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequenceArray(argName) : null;
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<CharSequence> charSequences = arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
        if (charSequences != null) return charSequences;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull ArrayList<CharSequence> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<CharSequence> charSequences = arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
    }


    @NonNull
    public static String readStringArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        String string = arguments != null ? arguments.getString(argName) : null;
        if (string != null) return string;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static String readStringArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull String defaultValue) {
        Bundle arguments = fragment.getArguments();
        String string = arguments != null ? arguments.getString(argName, defaultValue) : null;
        return string != null ? string : defaultValue;
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getString(argName) : null;
    }


    @NonNull
    public static String[] readStringArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        String[] strings = arguments != null ? arguments.getStringArray(argName) : null;
        if (strings != null) return strings;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull String[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        String[] strings = arguments != null ? arguments.getStringArray(argName) : null;
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getStringArray(argName) : null;
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<String> strings = arguments != null ? arguments.getStringArrayList(argName) : null;
        if (strings != null) return strings;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull ArrayList<String> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<String> strings = arguments != null ? arguments.getStringArrayList(argName) : null;
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getStringArrayList(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        V parcelable = arguments != null ? (V) arguments.getParcelable(argName) : null;
        if (parcelable != null) return parcelable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull V defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        Parcelable parcelable = arguments != null ? arguments.getParcelable(argName) : null;
        //noinspection unchecked
        return parcelable != null ? (V) parcelable : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (V) arguments.getParcelable(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        V[] parcelables = arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
        if (parcelables != null) return parcelables;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull V[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        V[] parcelables = arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        ArrayList<V> parcelable = arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
        if (parcelable != null) return parcelable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull ArrayList<V> defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        ArrayList<V> parcelables = arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        SparseArray<V> sparseArray = arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
        if (sparseArray != null) return sparseArray;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull SparseArray<V> defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        SparseArray<V> sparseArray = arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
        return sparseArray != null ? sparseArray : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Argsx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        V serializable = arguments != null ? (V) arguments.getSerializable(argName) : null;
        if (serializable != null) return serializable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull V defaultValue) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        V serializable = arguments != null ? (V) arguments.getSerializable(argName) : null;
        return serializable != null ? serializable : defaultValue;
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        return arguments != null ? (V) arguments.getSerializable(argName) : null;
    }


    @NonNull
    public static Bundle readBundleArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        Bundle bundle = arguments != null ? arguments.getBundle(argName) : null;
        if (bundle != null) return bundle;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull Bundle defaultValue) {
        Bundle arguments = fragment.getArguments();
        Bundle bundle = arguments != null ? arguments.getBundle(argName) : null;
        return bundle != null ? bundle : defaultValue;
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBundle(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        IBinder iBinder = arguments != null ? arguments.getBinder(argName) : null;
        if (iBinder != null) return iBinder;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull IBinder defaultValue) {
        Bundle arguments = fragment.getArguments();
        IBinder iBinder = arguments != null ? arguments.getBinder(argName) : null;
        return iBinder != null ? iBinder : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBinder(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        Size size = arguments != null ? arguments.getSize(argName) : null;
        if (size != null) return size;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull Size defaultValue) {
        Bundle arguments = fragment.getArguments();
        Size size = arguments != null ? arguments.getSize(argName) : null;
        return size != null ? size : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getSize(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrThrow(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        SizeF sizeF = arguments != null ? arguments.getSizeF(argName) : null;
        if (sizeF != null) return sizeF;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOr(@NonNull Fragment fragment, @NonNull String argName, @NonNull SizeF defaultValue) {
        Bundle arguments = fragment.getArguments();
        SizeF sizeF = arguments != null ? arguments.getSizeF(argName) : null;
        return sizeF != null ? sizeF : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrNull(@NonNull Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getSizeF(argName) : null;
    }


    /* ************************************* SupportFragment Args ***************************************** */


    public static byte readByteArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static byte readByteArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Byte readByteArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static byte[] readByteArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull byte[] defaultValue) {
        return Argsx.readByteArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static short readShortArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static short readShortArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Short readShortArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static short[] readShortArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull short[] defaultValue) {
        return Argsx.readShortArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static int readIntArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static int readIntArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Integer readIntArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static int[] readIntArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull int[] defaultValue) {
        return Argsx.readIntArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<Integer> defaultValue) {
        return Argsx.readIntArrayListArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static long readLongArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static long readLongArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Long readLongArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static long[] readLongArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull long[] defaultValue) {
        return Argsx.readLongArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static float readFloatArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static float readFloatArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Float readFloatArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static float[] readFloatArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull float[] defaultValue) {
        return Argsx.readFloatArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static double readDoubleArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static double readDoubleArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Double readDoubleArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static double[] readDoubleArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull double[] defaultValue) {
        return Argsx.readDoubleArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static boolean readBooleanArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static boolean readBooleanArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Boolean readBooleanArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static boolean[] readBooleanArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull boolean[] defaultValue) {
        return Argsx.readBooleanArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    public static char readCharArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    public static char readCharArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, char defaultValue) {
        return Argsx.readCharArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Character readCharArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static char[] readCharArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull char[] defaultValue) {
        return Argsx.readCharArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static CharSequence readCharSequenceArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull CharSequence defaultValue) {
        return Argsx.readCharSequenceArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull CharSequence[] defaultValue) {
        return Argsx.readCharSequenceArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<CharSequence> defaultValue) {
        return Argsx.readCharSequenceArrayListArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static String readStringArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static String readStringArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static String[] readStringArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull String[] defaultValue) {
        return Argsx.readStringArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<String> defaultValue) {
        return Argsx.readStringArrayListArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readParcelableArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull V[] defaultValue) {
        return Argsx.readParcelableArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<V> defaultValue) {
        return Argsx.readParcelableArrayListArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSparseParcelableArrayArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull SparseArray<V> defaultValue) {
        return Argsx.readSparseParcelableArrayArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSparseParcelableArrayArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readSerializableArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    public static Bundle readBundleArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull Bundle defaultValue) {
        return Argsx.readBundleArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBinderArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull IBinder defaultValue) {
        return Argsx.readBinderArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBinderArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull Size defaultValue) {
        return Argsx.readSizeArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeArgOrNull(fragment, fragment.getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrThrow(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeFArgOrThrow(fragment, fragment.getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOr(@NonNull Fragment fragment, @StringRes int argNameResId, @NonNull SizeF defaultValue) {
        return Argsx.readSizeFArgOr(fragment, fragment.getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrNull(@NonNull Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeFArgOrNull(fragment, fragment.getString(argNameResId));
    }
}