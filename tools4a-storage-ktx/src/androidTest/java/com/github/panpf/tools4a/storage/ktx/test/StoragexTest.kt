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

package com.github.panpf.tools4a.storage.ktx.test

import android.os.Environment
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.panpf.tools4a.storage.Storagex
import com.github.panpf.tools4a.storage.ktx.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class StoragexTest {

    @Test
    fun testBytes() {
        assertTrue(Storagex.getExternalStorageFreeBytes() <= Storagex.getExternalStorageTotalBytes())
        assertTrue(Storagex.getExternalStorageAvailableBytes() <= Storagex.getExternalStorageTotalBytes())
        assertTrue(Storagex.getExternalStorageAvailableBytes() <= Storagex.getExternalStorageFreeBytes())

        val downloadDir = File(Environment.getExternalStorageDirectory(), "download")
        try {
            assertTrue(downloadDir.getFreeBytesOr(-1) <= downloadDir.getTotalBytesOr(-1))
            assertTrue(downloadDir.getAvailableBytesOr(-1) <= downloadDir.getTotalBytesOr(-1))
            assertTrue(downloadDir.getAvailableBytesOr(-1) <= downloadDir.getFreeBytesOr(-1))
        } finally {
            downloadDir.deleteRecursively()
        }

        val errorDir = File(Environment.getExternalStorageDirectory(), "fhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfasfhkasjfwurklfsnmfsdfaopsjfpioawejfasjfiwhfsjfkasjfksjfkoasfwfas")
        try {
            assertEquals(-1, errorDir.getFreeBytesOr(-1))
            assertEquals(-1, errorDir.getAvailableBytesOr(-1))
            assertEquals(-1, errorDir.getTotalBytesOr(-1))
        } finally {
            errorDir.deleteRecursively()
        }
    }

    @Test
    fun testVolumeState() {
        val context = InstrumentationRegistry.getContext()

        val volumeCompat = context.getVolume(getExternalStorageDirectory())
        val volumeFile = volumeCompat?.pathFile
        assertNotNull(volumeFile)

        assertNotNull(context.getVolumeState(volumeFile!!))
        assertTrue(context.isVolumeMounted(volumeFile))
    }

    @Test
    fun testVolumeAttr() {
        val context = InstrumentationRegistry.getContext()

        val volumeFiles = context.getVolumeFiles()

        assertTrue(context.isPrimaryVolume(volumeFiles[0]))
        if (volumeFiles.size > 1) {
            assertFalse(context.isPrimaryVolume(volumeFiles[1]))
        }

        context.isVolumeEmulated(volumeFiles[0])
        if (volumeFiles.size > 1) {
            assertFalse(context.isVolumeEmulated(volumeFiles[1]))
        }

        context.isVolumeRemovable(volumeFiles[0])
        if (volumeFiles.size > 1) {
            if (context.isVolumeEmulated(volumeFiles[1])) {
                assertFalse(context.isVolumeRemovable(volumeFiles[1]))
            } else {
                assertTrue(context.isVolumeRemovable(volumeFiles[1]))
            }
        }
    }

    @Test
    fun testVolumes() {
        val context = InstrumentationRegistry.getContext()

        assertTrue(context.getVolumes().isNotEmpty())
        assertTrue(context.getMountedVolumes().isNotEmpty())
        assertTrue(context.getVolumePaths().isNotEmpty())
        assertTrue(context.getMountedVolumePaths().isNotEmpty())

        assertTrue(context.getVolumeFiles().isNotEmpty())
        assertTrue(context.getMountedVolumeFiles().isNotEmpty())

        assertTrue(context.getVolumeList().isNotEmpty())
        assertTrue(context.getMountedVolumeList().isNotEmpty())
    }

    @Test
    fun testExternalStorageState() {
        val context = InstrumentationRegistry.getContext()
        assertEquals(getExternalStorageState(), Environment.MEDIA_MOUNTED)
        assertEquals(context.getExternalStorageState(getExternalStorageDirectory()), Environment.MEDIA_MOUNTED)
        assertTrue(isExternalStorageMounted())
        assertTrue(context.isExternalStorageMounted(getExternalStorageDirectory()))
    }

    @Test
    fun testExternalStorageAttr() {
        val context = InstrumentationRegistry.getContext()

        val storageDirectorys = context.getExternalStorageDirectorys()

        assertTrue(context.isPrimaryExternalStorage(storageDirectorys[0]))
        if (storageDirectorys.size > 1) {
            assertFalse(context.isPrimaryExternalStorage(storageDirectorys[1]))
        }

        context.isExternalStorageEmulated(storageDirectorys[0])
        if (storageDirectorys.size > 1) {
            assertFalse(context.isExternalStorageEmulated(storageDirectorys[1]))
        }

        context.isExternalStorageRemovable(storageDirectorys[0])
        if (storageDirectorys.size > 1) {
            if (context.isExternalStorageEmulated(storageDirectorys[1])) {
                assertFalse(context.isExternalStorageRemovable(storageDirectorys[1]))
            } else {
                assertTrue(context.isExternalStorageRemovable(storageDirectorys[1]))
            }
        }

        assertNotNull(isExternalStorageEmulated().toString())
        assertNotNull(isExternalStorageRemovable().toString())
    }

    @Test
    fun testExternalStorageDirectory() {
        val context = InstrumentationRegistry.getContext()
        assertNotNull(getExternalStorageDirectory())
        assertNotNull(context.getMountedExternalStorageDirectory())
    }

    @Test
    fun testExternalStorageDirectorys() {
        val context = InstrumentationRegistry.getContext()
        val sdcard = Environment.getExternalStorageDirectory()
        assertNotNull(context.getExternalStorageDirectorys().find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
        assertNull(context.getExternalStorageDirectorys(true).find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
        assertNotNull(context.getMountedExternalStorageDirectorys().find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
        assertNull(context.getMountedExternalStorageDirectorys(true).find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
    }

    @Test
    fun testExternalStorageDirectorysWithPath() {
        val context = InstrumentationRegistry.getContext()
        val childPath = "Download"
        val sdcard = File(Environment.getExternalStorageDirectory(), childPath)
        assertNotNull(context.getExternalStorageDirectorysWithPath(childPath).find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
        assertNull(context.getExternalStorageDirectorysWithPath(childPath, true).find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
        assertNotNull(context.getMountedExternalStorageDirectorysWithPath(childPath).find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
        assertNull(context.getMountedExternalStorageDirectorysWithPath(childPath, true).find {
            try {
                it.canonicalPath == sdcard.canonicalPath
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        })
    }

    @Test
    fun testAppCacheDir() {
        val context = InstrumentationRegistry.getContext()

        /*
         * appExternalCacheDir
         */
        val appExternalCacheDir = context.getAppExternalCacheDir()
        assertNotNull(appExternalCacheDir)
        assertTrue(!appExternalCacheDir!!.path.startsWith("/data/") && appExternalCacheDir.path.endsWith("Android/data/" + context.packageName + "/cache"))

        val appExternalCacheDir2 = context.getAppExternalCacheDir("com.github.panpf.test")
        assertNotNull(appExternalCacheDir2)
        assertTrue(!appExternalCacheDir2!!.path.startsWith("/data/") && appExternalCacheDir2.path.endsWith("Android/data/com.github.panpf.test/cache"))


        /*
         * appExternalCacheDir2
         */
        val appExternalCacheDirs = context.getAppExternalCacheDirs()
        assertTrue(appExternalCacheDirs.isNotEmpty() && appExternalCacheDirs.all {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/" + context.packageName + "/cache")
        })
        val appExternalCacheDirs2 = context.getAppExternalCacheDirs("com.github.panpf.test")
        assertTrue(appExternalCacheDirs2.isNotEmpty() && appExternalCacheDirs2.all {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/com.github.panpf.test/cache")
        })

        /*
         * appInternalCacheDir
         */
        val appInternalCacheDir = context.getAppInternalCacheDir()
        assertTrue(appInternalCacheDir.path.startsWith("/data/") && appInternalCacheDir.path.endsWith(context.packageName + "/cache"))

        val appInternalCacheDir2 = context.getAppInternalCacheDir("com.github.panpf.test")
        assertTrue(appInternalCacheDir2.path.startsWith("/data/") && appInternalCacheDir2.path.endsWith("com.github.panpf.test/cache"))

        /*
         * appCacheDirs
         */
        val appCacheDirs = context.getAppCacheDirs()
        assertTrue(appCacheDirs.size >= 2 && appCacheDirs.any {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/" + context.packageName + "/cache")
        } && appCacheDirs.any {
            it.path.startsWith("/data/") && it.path.endsWith(context.packageName + "/cache")
        })
        val appCacheDirs2 = context.getAppCacheDirs("com.github.panpf.test")
        assertTrue(appCacheDirs2.size >= 2 && appCacheDirs2.any {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/com.github.panpf.test/cache")
        } && appCacheDirs2.any {
            it.path.startsWith("/data/") && it.path.endsWith("com.github.panpf.test/cache")
        })

        /*
         * lengthAppCacheDirs
         */
        assertTrue(context.lengthAppCacheDirs() >= 0)
        assertTrue(context.lengthAppCacheDirs(context.packageName) >= 0)

        /*
         * cleanAppCacheDirs
         */
        context.cleanAppCacheDirs()
        context.cleanAppCacheDirs(context.packageName)
    }

    @Test
    fun testAppFilesDir() {
        val context = InstrumentationRegistry.getContext()

        /*
         * appExternalFilesDir
         */
        val appExternalFilesDir = context.getAppExternalFilesDir()
        assertNotNull(appExternalFilesDir)
        assertTrue(!appExternalFilesDir!!.path.startsWith("/data/") && appExternalFilesDir.path.endsWith("Android/data/" + context.packageName + "/files"))

        val appExternalFilesDir2 = context.getAppExternalFilesDir("com.github.panpf.test")
        assertNotNull(appExternalFilesDir2)
        assertTrue(!appExternalFilesDir2!!.path.startsWith("/data/") && appExternalFilesDir2.path.endsWith("Android/data/com.github.panpf.test/files"))


        /*
         * appExternalFilesDir2
         */
        val appExternalFilesDirs = context.getAppExternalFilesDirs()
        assertTrue(appExternalFilesDirs.isNotEmpty() && appExternalFilesDirs.all {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/" + context.packageName + "/files")
        })
        val appExternalFilesDirs2 = context.getAppExternalFilesDirs("com.github.panpf.test")
        assertTrue(appExternalFilesDirs2.isNotEmpty() && appExternalFilesDirs2.all {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/com.github.panpf.test/files")
        })

        /*
         * appInternalFilesDir
         */
        val appInternalFilesDir = context.getAppInternalFilesDir()
        assertTrue(appInternalFilesDir.path.startsWith("/data/") && appInternalFilesDir.path.endsWith(context.packageName + "/files"))

        val appInternalFilesDir2 = context.getAppInternalFilesDir("com.github.panpf.test")
        assertTrue(appInternalFilesDir2.path.startsWith("/data/") && appInternalFilesDir2.path.endsWith("com.github.panpf.test/files"))

        /*
         * appFilesDirs
         */
        val appFilesDirs = context.getAppFilesDirs()
        assertTrue(appFilesDirs.size >= 2 && appFilesDirs.any {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/" + context.packageName + "/files")
        } && appFilesDirs.any {
            it.path.startsWith("/data/") && it.path.endsWith(context.packageName + "/files")
        })
        val appFilesDirs2 = context.getAppFilesDirs("com.github.panpf.test")
        assertTrue(appFilesDirs2.size >= 2 && appFilesDirs2.any {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/data/com.github.panpf.test/files")
        } && appFilesDirs2.any {
            it.path.startsWith("/data/") && it.path.endsWith("com.github.panpf.test/files")
        })

        /*
         * lengthAppFilesDirs
         */
        assertTrue(context.lengthAppFilesDirs() >= 0)
        assertTrue(context.lengthAppFilesDirs(context.packageName) >= 0)

        /*
         * cleanAppFilesDirs
         */
        context.cleanAppFilesDirs()
        context.cleanAppFilesDirs(context.packageName)
    }

    @Test
    fun testAppObbDir() {
        val context = InstrumentationRegistry.getContext()

        /*
         * appObbDir
         */
        val appObbDir = context.getAppObbDir()
        assertNotNull(appObbDir)
        assertTrue(!appObbDir!!.path.startsWith("/data/") && appObbDir.path.endsWith("Android/obb/" + context.packageName))

        val appObbDir2 = context.getAppObbDir("com.github.panpf.test")
        assertNotNull(appObbDir2)
        assertTrue(!appObbDir2!!.path.startsWith("/data/") && appObbDir2.path.endsWith("Android/obb/com.github.panpf.test"))


        /*
         * appObbDir2
         */
        val appObbDirs = context.getAppObbDirs()
        assertTrue(appObbDirs.isNotEmpty() && appObbDirs.all {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/obb/" + context.packageName)
        })
        val appObbDirs2 = context.getAppObbDirs("com.github.panpf.test")
        assertTrue(appObbDirs2.isNotEmpty() && appObbDirs2.all {
            !it.path.startsWith("/data/") && it.path.endsWith("Android/obb/com.github.panpf.test")
        })

        /*
         * lengthAppObbDirs
         */
        assertTrue(context.lengthAppObbDirs() >= 0)
        assertTrue(context.lengthAppObbDirs(context.packageName) >= 0)

        /*
         * cleanAppObbDirs
         */
        context.cleanAppObbDirs()
        context.cleanAppObbDirs(context.packageName)
    }

    @Test
    fun testFilterByMinBytes() {
        val context = InstrumentationRegistry.getContext()
        assertNotNull(context.getExternalStorageDirectorys().filterByMinBytes(1024))
        assertNull(context.getExternalStorageDirectorys().filterByMinBytes(java.lang.Long.MAX_VALUE))
    }

    @Test
    fun testGetFileIn() {
        val context = InstrumentationRegistry.getContext()
        assertNotNull(context.getAppExternalCacheDirs().getFileIn("test.jpeg", 1024 * 1024 * 8, true))
        assertNotNull(context.getAppExternalCacheDirs().getFileIn("test.jpeg", 1024 * 1024 * 8))
    }
}
