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

package com.github.panpf.tools4a.storage.test;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.github.panpf.tools4a.storage.StorageVolumeCompat;
import com.github.panpf.tools4a.storage.Storagex;
import com.github.panpf.tools4j.collections.Arrayx;
import com.github.panpf.tools4j.common.Predicate;
import com.github.panpf.tools4j.io.Filex;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class StoragexTest {

    @Test
    public void testBytes() {
        assertTrue(Storagex.getExternalStorageFreeBytes() <= Storagex.getExternalStorageTotalBytes());
        assertTrue(Storagex.getExternalStorageAvailableBytes() <= Storagex.getExternalStorageTotalBytes());
        assertTrue(Storagex.getExternalStorageAvailableBytes() <= Storagex.getExternalStorageFreeBytes());

        File downloadDir = new File(Environment.getExternalStorageDirectory(), "download");
        try {
            assertTrue(Storagex.getFreeBytesOr(downloadDir, -1) <= Storagex.getTotalBytesOr(downloadDir, -1));
            assertTrue(Storagex.getAvailableBytesOr(downloadDir, -1) <= Storagex.getTotalBytesOr(downloadDir, -1));
            assertTrue(Storagex.getAvailableBytesOr(downloadDir, -1) <= Storagex.getFreeBytesOr(downloadDir, -1));
        } finally {
            Filex.deleteRecursively(downloadDir);
        }

        File errorDir = new File(Environment.getExternalStorageDirectory(), "fhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfas");
        try {
            assertEquals(-1, Storagex.getFreeBytesOr(errorDir, -1));
            assertEquals(-1, Storagex.getAvailableBytesOr(errorDir, -1));
            assertEquals(-1, Storagex.getTotalBytesOr(errorDir, -1));
        } finally {
            Filex.deleteRecursively(errorDir);
        }
    }

    @Test
    public void testVolumeState() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        StorageVolumeCompat volumeCompat = Storagex.getVolume(context, Storagex.getExternalStorageDirectory());
        File volumeFile = volumeCompat != null ? volumeCompat.getPathFile() : null;
        assertNotNull(volumeFile);

        assertNotNull(Storagex.getVolumeState(context, volumeFile));
        assertTrue(Storagex.isVolumeMounted(context, volumeFile));
    }

    @Test
    public void testVolumeAttr() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        File[] volumeFiles = Storagex.getVolumeFiles(context);

        assertTrue(Storagex.isPrimaryVolume(context, volumeFiles[0]));
        if (volumeFiles.length > 1) {
            assertFalse(Storagex.isPrimaryVolume(context, volumeFiles[1]));
        }

        Storagex.isVolumeEmulated(context, volumeFiles[0]);
        if (volumeFiles.length > 1) {
            assertFalse(Storagex.isVolumeEmulated(context, volumeFiles[1]));
        }

        Storagex.isVolumeRemovable(context, volumeFiles[0]);
        if (volumeFiles.length > 1) {
            if (Storagex.isVolumeEmulated(context, volumeFiles[1])) {
                assertFalse(Storagex.isVolumeRemovable(context, volumeFiles[1]));
            } else {
                assertTrue(Storagex.isVolumeRemovable(context, volumeFiles[1]));
            }
        }
    }

    @Test
    public void testVolumes() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        assertTrue(Storagex.getVolumes(context).length >= 1);
        assertTrue(Storagex.getMountedVolumes(context).length >= 1);
        assertTrue(Storagex.getVolumePaths(context).length >= 1);
        assertTrue(Storagex.getMountedVolumePaths(context).length >= 1);

        assertTrue(Storagex.getVolumeFiles(context).length >= 1);
        assertTrue(Storagex.getMountedVolumeFiles(context).length >= 1);

        assertTrue(Storagex.getVolumeList(context).size() >= 1);
        assertTrue(Storagex.getMountedVolumeList(context).size() >= 1);
    }

    @Test
    public void testExternalStorageState() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        assertEquals(Storagex.getExternalStorageState(), Environment.MEDIA_MOUNTED);
        assertEquals(Storagex.getExternalStorageState(context, Storagex.getExternalStorageDirectory()), Environment.MEDIA_MOUNTED);
        assertTrue(Storagex.isExternalStorageMounted());
        assertTrue(Storagex.isExternalStorageMounted(context, Storagex.getExternalStorageDirectory()));
    }

    @Test
    public void testExternalStorageAttr() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        File[] storageDirectorys = Storagex.getExternalStorageDirectorys(context);

        assertTrue(Storagex.isPrimaryExternalStorage(context, storageDirectorys[0]));
        if (storageDirectorys.length > 1) {
            assertFalse(Storagex.isPrimaryExternalStorage(context, storageDirectorys[1]));
        }

        Storagex.isExternalStorageEmulated(context, storageDirectorys[0]);
        if (storageDirectorys.length > 1) {
            assertFalse(Storagex.isExternalStorageEmulated(context, storageDirectorys[1]));
        }

        Storagex.isExternalStorageRemovable(context, storageDirectorys[0]);
        if (storageDirectorys.length > 1) {
            if (Storagex.isExternalStorageEmulated(context, storageDirectorys[1])) {
                assertFalse(Storagex.isExternalStorageRemovable(context, storageDirectorys[1]));
            } else {
                assertTrue(Storagex.isExternalStorageRemovable(context, storageDirectorys[1]));
            }
        }

        assertNotNull(String.valueOf(Storagex.isExternalStorageEmulated()));
        assertNotNull(String.valueOf(Storagex.isExternalStorageRemovable()));
    }

    @Test
    public void testExternalStorageDirectory() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        assertNotNull(Storagex.getExternalStorageDirectory());
        assertNotNull(Storagex.getMountedExternalStorageDirectory(context));
    }

    @Test
    public void testExternalStorageDirectorys() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        final File sdcard = Environment.getExternalStorageDirectory();
        assertNotNull(Arrayx.find(Storagex.getExternalStorageDirectorys(context), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
        assertNull(Arrayx.find(Storagex.getExternalStorageDirectorys(context, true), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
        assertNotNull(Arrayx.find(Storagex.getMountedExternalStorageDirectorys(context), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
        assertNull(Arrayx.find(Storagex.getMountedExternalStorageDirectorys(context, true), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
    }

    @Test
    public void testExternalStorageDirectorysWithPath() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        String childPath = "Download";
        final File sdcard = new File(Environment.getExternalStorageDirectory(), childPath);
        assertNotNull(Arrayx.find(Storagex.getExternalStorageDirectorysWithPath(context, childPath), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
        assertNull(Arrayx.find(Storagex.getExternalStorageDirectorysWithPath(context, childPath, true), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
        assertNotNull(Arrayx.find(Storagex.getMountedExternalStorageDirectorysWithPath(context, childPath), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
        assertNull(Arrayx.find(Storagex.getMountedExternalStorageDirectorysWithPath(context, childPath, true), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                try {
                    return file.getCanonicalPath().equals(sdcard.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }));
    }

    @Test
    public void testAppCacheDir() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        /*
         * appExternalCacheDir
         */
        File appExternalCacheDir = Storagex.getAppExternalCacheDir(context);
        assertNotNull(appExternalCacheDir);
        assertTrue(!appExternalCacheDir.getPath().startsWith("/data/") && appExternalCacheDir.getPath().endsWith("Android/data/" + context.getPackageName() + "/cache"));

        File appExternalCacheDir2 = Storagex.getAppExternalCacheDir(context, "com.github.panpf.test");
        assertNotNull(appExternalCacheDir2);
        assertTrue(!appExternalCacheDir2.getPath().startsWith("/data/") && appExternalCacheDir2.getPath().endsWith("Android/data/com.github.panpf.test/cache"));


        /*
         * appExternalCacheDir2
         */
        File[] appExternalCacheDirs = Storagex.getAppExternalCacheDirs(context);
        assertTrue(appExternalCacheDirs.length >= 1 && Arrayx.all(appExternalCacheDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/" + context.getPackageName() + "/cache");
            }
        }));
        File[] appExternalCacheDirs2 = Storagex.getAppExternalCacheDirs(context, "com.github.panpf.test");
        assertTrue(appExternalCacheDirs2.length >= 1 && Arrayx.all(appExternalCacheDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/com.github.panpf.test/cache");
            }
        }));

        /*
         * appInternalCacheDir
         */
        File appInternalCacheDir = Storagex.getAppInternalCacheDir(context);
        assertTrue(appInternalCacheDir.getPath().startsWith("/data/") && appInternalCacheDir.getPath().endsWith(context.getPackageName() + "/cache"));

        File appInternalCacheDir2 = Storagex.getAppInternalCacheDir(context, "com.github.panpf.test");
        assertTrue(appInternalCacheDir2.getPath().startsWith("/data/") && appInternalCacheDir2.getPath().endsWith("com.github.panpf.test/cache"));

        /*
         * appCacheDirs
         */
        File[] appCacheDirs = Storagex.getAppCacheDirs(context);
        assertTrue(appCacheDirs.length >= 2 && Arrayx.any(appCacheDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/" + context.getPackageName() + "/cache");
            }
        }) && Arrayx.any(appCacheDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return file.getPath().startsWith("/data/") && file.getPath().endsWith(context.getPackageName() + "/cache");
            }
        }));
        File[] appCacheDirs2 = Storagex.getAppCacheDirs(context, "com.github.panpf.test");
        assertTrue(appCacheDirs2.length >= 2 && Arrayx.any(appCacheDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/com.github.panpf.test/cache");
            }
        }) && Arrayx.any(appCacheDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return file.getPath().startsWith("/data/") && file.getPath().endsWith("com.github.panpf.test/cache");
            }
        }));

        /*
         * lengthAppCacheDirs
         */
        assertTrue(Storagex.lengthAppCacheDirs(context) >= 0);
        assertTrue(Storagex.lengthAppCacheDirs(context, context.getPackageName()) >= 0);

        /*
         * cleanAppCacheDirs
         */
        Storagex.cleanAppCacheDirs(context);
        Storagex.cleanAppCacheDirs(context, context.getPackageName());
    }

    @Test
    public void testAppFilesDir() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        /*
         * appExternalFilesDir
         */
        File appExternalFilesDir = Storagex.getAppExternalFilesDir(context);
        assertNotNull(appExternalFilesDir);
        assertTrue(!appExternalFilesDir.getPath().startsWith("/data/") && appExternalFilesDir.getPath().endsWith("Android/data/" + context.getPackageName() + "/files"));

        File appExternalFilesDir2 = Storagex.getAppExternalFilesDir(context, "com.github.panpf.test");
        assertNotNull(appExternalFilesDir2);
        assertTrue(!appExternalFilesDir2.getPath().startsWith("/data/") && appExternalFilesDir2.getPath().endsWith("Android/data/com.github.panpf.test/files"));


        /*
         * appExternalFilesDir2
         */
        File[] appExternalFilesDirs = Storagex.getAppExternalFilesDirs(context);
        assertTrue(appExternalFilesDirs.length >= 1 && Arrayx.all(appExternalFilesDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/" + context.getPackageName() + "/files");
            }
        }));
        File[] appExternalFilesDirs2 = Storagex.getAppExternalFilesDirs(context, "com.github.panpf.test");
        assertTrue(appExternalFilesDirs2.length >= 1 && Arrayx.all(appExternalFilesDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/com.github.panpf.test/files");
            }
        }));

        /*
         * appInternalFilesDir
         */
        File appInternalFilesDir = Storagex.getAppInternalFilesDir(context);
        assertTrue(appInternalFilesDir.getPath().startsWith("/data/") && appInternalFilesDir.getPath().endsWith(context.getPackageName() + "/files"));

        File appInternalFilesDir2 = Storagex.getAppInternalFilesDir(context, "com.github.panpf.test");
        assertTrue(appInternalFilesDir2.getPath().startsWith("/data/") && appInternalFilesDir2.getPath().endsWith("com.github.panpf.test/files"));

        /*
         * appFilesDirs
         */
        File[] appFilesDirs = Storagex.getAppFilesDirs(context);
        assertTrue(appFilesDirs.length >= 2 && Arrayx.any(appFilesDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/" + context.getPackageName() + "/files");
            }
        }) && Arrayx.any(appFilesDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return file.getPath().startsWith("/data/") && file.getPath().endsWith(context.getPackageName() + "/files");
            }
        }));
        File[] appFilesDirs2 = Storagex.getAppFilesDirs(context, "com.github.panpf.test");
        assertTrue(appFilesDirs2.length >= 2 && Arrayx.any(appFilesDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/data/com.github.panpf.test/files");
            }
        }) && Arrayx.any(appFilesDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return file.getPath().startsWith("/data/") && file.getPath().endsWith("com.github.panpf.test/files");
            }
        }));

        /*
         * lengthAppFilesDirs
         */
        assertTrue(Storagex.lengthAppFilesDirs(context) >= 0);
        assertTrue(Storagex.lengthAppFilesDirs(context, context.getPackageName()) >= 0);

        /*
         * cleanAppFilesDirs
         */
        Storagex.cleanAppFilesDirs(context);
        Storagex.cleanAppFilesDirs(context, context.getPackageName());
    }

    @Test
    public void testAppObbDir() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();

        /*
         * appObbDir
         */
        File appObbDir = Storagex.getAppObbDir(context);
        assertNotNull(appObbDir);
        assertTrue(!appObbDir.getPath().startsWith("/data/") && appObbDir.getPath().endsWith("Android/obb/" + context.getPackageName()));

        File appObbDir2 = Storagex.getAppObbDir(context, "com.github.panpf.test");
        assertNotNull(appObbDir2);
        assertTrue(!appObbDir2.getPath().startsWith("/data/") && appObbDir2.getPath().endsWith("Android/obb/com.github.panpf.test"));


        /*
         * appObbDir2
         */
        File[] appObbDirs = Storagex.getAppObbDirs(context);
        assertTrue(appObbDirs.length >= 1 && Arrayx.all(appObbDirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/obb/" + context.getPackageName());
            }
        }));
        File[] appObbDirs2 = Storagex.getAppObbDirs(context, "com.github.panpf.test");
        assertTrue(appObbDirs2.length >= 1 && Arrayx.all(appObbDirs2, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !file.getPath().startsWith("/data/") && file.getPath().endsWith("Android/obb/com.github.panpf.test");
            }
        }));

        /*
         * lengthAppObbDirs
         */
        assertTrue(Storagex.lengthAppObbDirs(context) >= 0);
        assertTrue(Storagex.lengthAppObbDirs(context, context.getPackageName()) >= 0);

        /*
         * cleanAppObbDirs
         */
        Storagex.cleanAppObbDirs(context);
        Storagex.cleanAppObbDirs(context, context.getPackageName());
    }

    @Test
    public void testFilterByMinBytes() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();
        assertNotNull(Storagex.filterByMinBytes(Storagex.getExternalStorageDirectorys(context), 1024));
        assertNull(Storagex.filterByMinBytes(Storagex.getExternalStorageDirectorys(context), Long.MAX_VALUE));
    }

    @Test
    public void testGetFileIn() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();
        assertNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "test.jpeg", 1024 * 1024 * 8, true));
        assertNotNull(Storagex.getFileIn(Storagex.getAppExternalCacheDirs(context), "test.jpeg", 1024 * 1024 * 8));
    }
}
