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

package com.github.panpf.tools4a.storage.ktx

import android.content.Context
import android.os.Environment
import androidx.annotation.WorkerThread
import com.github.panpf.tools4a.storage.StorageVolumeCompat
import com.github.panpf.tools4a.storage.Storagex
import java.io.File

/*
 * Storage related extension methods or properties
 */

/* ******************************************* Bytes *******************************************/


/**
 * Get the number of free bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
 */
inline fun File.getFreeBytesOr(defaultValue: Long): Long = Storagex.getFreeBytesOr(this, defaultValue)

/**
 * Get the number of total bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
 */
inline fun File.getTotalBytesOr(defaultValue: Long): Long = Storagex.getTotalBytesOr(this, defaultValue)

/**
 * Get the number of available bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
 */
inline fun File.getAvailableBytesOr(defaultValue: Long): Long = Storagex.getAvailableBytesOr(this, defaultValue)


/* ******************************************* Volume *******************************************/

/**
 * Returns volume state for given path
 */
inline fun Context.getVolumeState(path: File): String = Storagex.getVolumeState(this, path)

/**
 * Returns true if the state of the volume at which the given path is mounted
 */
inline fun Context.isVolumeMounted(path: File): Boolean = Storagex.isVolumeMounted(this, path)


/**
 * Return true if the volume of the given path is the primary volume
 */
inline fun Context.isPrimaryVolume(path: File): Boolean = Storagex.isPrimaryVolume(this, path)

/**
 * Return true if the volume of the given path is the emulated volume
 */
inline fun Context.isVolumeEmulated(path: File): Boolean = Storagex.isVolumeEmulated(this, path)

/**
 * Return true if the volume of the given path is the removable volume
 */
inline fun Context.isVolumeRemovable(path: File): Boolean = Storagex.isVolumeRemovable(this, path)


/**
 * Returns list of path for all volumes.
 */
inline fun Context.getVolumePaths(): Array<String> = Storagex.getVolumePaths(this)

/**
 * Returns list of path for all mounted volumes.
 */
inline fun Context.getMountedVolumePaths(): Array<String> = Storagex.getMountedVolumePaths(this)


/**
 * Returns list of files for all volumes.
 */
inline fun Context.getVolumeFiles(): Array<File> = Storagex.getVolumeFiles(this)

/**
 * Returns list of files for all mounted volumes.
 */
inline fun Context.getMountedVolumeFiles(): Array<File> = Storagex.getMountedVolumeFiles(this)


/**
 * Returns list of StorageVolume for all volumes.
 */
inline fun Context.getVolumeList(): List<StorageVolumeCompat> = Storagex.getVolumeList(this)

/**
 * Returns list of StorageVolume for all mounted volumes.
 */
inline fun Context.getMountedVolumeList(): List<StorageVolumeCompat> = Storagex.getMountedVolumeList(this)

/**
 * Returns array of StorageVolume for all volumes.
 */
inline fun Context.getVolumes(): Array<StorageVolumeCompat> = Storagex.getVolumes(this)

/**
 * Returns array of StorageVolume for all mounted volumes.
 */
inline fun Context.getMountedVolumes(): Array<StorageVolumeCompat> = Storagex.getMountedVolumes(this)


/**
 * Returns StorageVolume for path.
 */
inline fun Context.getVolume(path: File): StorageVolumeCompat? = Storagex.getVolume(this, path)


/* ******************************************* External Storage *******************************************/


/**
 * Returns the current state of the shared/external storage media at the
 * given path.
 *
 * @return one of [Environment.MEDIA_UNKNOWN], [Environment.MEDIA_REMOVED],
 * [Environment.MEDIA_UNMOUNTED], [Environment.MEDIA_CHECKING],
 * [Environment.MEDIA_NOFS], [Environment.MEDIA_MOUNTED],
 * [Environment.MEDIA_MOUNTED_READ_ONLY], [Environment.MEDIA_SHARED],
 * [Environment.MEDIA_BAD_REMOVAL], or [Environment.MEDIA_UNMOUNTABLE].
 * @see .getExternalStorageDirectory
 */
inline fun Context.getExternalStorageState(path: File): String = Storagex.getExternalStorageState(this, path)

/**
 * Return true if the state of the given path is MOUNTED
 */
inline fun Context.isExternalStorageMounted(path: File): Boolean = Storagex.isExternalStorageMounted(this, path)

/**
 * Returns the current state of the primary shared/external storage media.
 *
 * @return one of [Environment.MEDIA_UNKNOWN], [Environment.MEDIA_REMOVED],
 * [Environment.MEDIA_UNMOUNTED], [Environment.MEDIA_CHECKING],
 * [Environment.MEDIA_NOFS], [Environment.MEDIA_MOUNTED],
 * [Environment.MEDIA_MOUNTED_READ_ONLY], [Environment.MEDIA_SHARED],
 * [Environment.MEDIA_BAD_REMOVAL], or [Environment.MEDIA_UNMOUNTABLE].
 * @see .getExternalStorageDirectory
 */
inline fun getExternalStorageState(): String = Storagex.getExternalStorageState()

/**
 * Return true if the state of the primary shared/external storage media is MOUNTED
 */
inline fun isExternalStorageMounted(): Boolean = Storagex.isExternalStorageMounted()

/**
 * Return true if the path is the primary volume
 */
inline fun Context.isPrimaryExternalStorage(path: File): Boolean = Storagex.isPrimaryExternalStorage(this, path)

/**
 * Return true if the volume of the given path is emulated
 */
inline fun Context.isExternalStorageEmulated(path: File): Boolean = Storagex.isExternalStorageEmulated(this, path)

/**
 * Return true if the primary shared/external storage media is emulated
 */
inline fun isExternalStorageEmulated(): Boolean = Storagex.isExternalStorageEmulated()

/**
 * Return true if the state of the given path is REMOVED
 */
inline fun Context.isExternalStorageRemovable(path: File): Boolean = Storagex.isExternalStorageRemovable(this, path)

/**
 * Return true if the state of the primary shared/external storage media is REMOVED
 */
inline fun isExternalStorageRemovable(): Boolean = Storagex.isExternalStorageRemovable()


/**
 * Return the primary shared/external storage directory.
 */
inline fun getExternalStorageDirectory(): File = Storagex.getExternalStorageDirectory()

/**
 * Return if the primary shared/external storage directory is mounted, otherwise return null
 */
inline fun Context.getMountedExternalStorageDirectory(): File? = Storagex.getMountedExternalStorageDirectory(this)


/**
 * Return the all shared/external storage directory.
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
inline fun Context.getExternalStorageDirectorys(ignorePrimary: Boolean): Array<File> =
        Storagex.getExternalStorageDirectorys(this, ignorePrimary)

/**
 * Returns the all shared/external storage directories that are mounted.
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
inline fun Context.getMountedExternalStorageDirectorys(ignorePrimary: Boolean): Array<File> =
        Storagex.getMountedExternalStorageDirectorys(this, ignorePrimary)


/**
 * Return the all shared/external storage directory.
 */
inline fun Context.getExternalStorageDirectorys(): Array<File> = Storagex.getExternalStorageDirectorys(this)

/**
 * Returns the all shared/external storage directories that are mounted.
 */
inline fun Context.getMountedExternalStorageDirectorys(): Array<File> = Storagex.getMountedExternalStorageDirectorys(this)


/**
 * Get the given path under all shared/external storage media
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
inline fun Context.getExternalStorageDirectorysWithPath(childPath: String, ignorePrimary: Boolean): Array<File> =
        Storagex.getExternalStorageDirectorysWithPath(this, childPath, ignorePrimary)

/**
 * Get the given path under all mounted shared/external storage media
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
inline fun Context.getMountedExternalStorageDirectorysWithPath(childPath: String, ignorePrimary: Boolean): Array<File> =
        Storagex.getMountedExternalStorageDirectorysWithPath(this, childPath, ignorePrimary)


/**
 * Get the given path under all shared/external storage media
 */
inline fun Context.getExternalStorageDirectorysWithPath(childPath: String): Array<File> =
        Storagex.getExternalStorageDirectorysWithPath(this, childPath)

/**
 * Get the given path under all mounted shared/external storage media
 */
inline fun Context.getMountedExternalStorageDirectorysWithPath(childPath: String): Array<File> =
        Storagex.getMountedExternalStorageDirectorysWithPath(this, childPath)


/* ******************************************* App Cache Dir *******************************************/


/**
 * Get the app external cache directory
 */
inline fun Context.getAppExternalCacheDir(): File? = Storagex.getAppExternalCacheDir(this)

/**
 * Get the external cache directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppExternalCacheDir(packageName: String): File? = Storagex.getAppExternalCacheDir(this, packageName)

/**
 * Get the all app external cache directory
 */
inline fun Context.getAppExternalCacheDirs(): Array<File> = Storagex.getAppExternalCacheDirs(this)

/**
 * Get the all external cache directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppExternalCacheDirs(packageName: String): Array<File> = Storagex.getAppExternalCacheDirs(this, packageName)


/**
 * Get the app internal cache directory
 */
inline fun Context.getAppInternalCacheDir(): File = Storagex.getAppInternalCacheDir(this)

/**
 * Get the internal cache directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppInternalCacheDir(packageName: String): File = Storagex.getAppInternalCacheDir(this, packageName)


/**
 * Get all app cache directories
 */
inline fun Context.getAppCacheDirs(): Array<File> = Storagex.getAppCacheDirs(this)

/**
 * Get all cache directories of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppCacheDirs(packageName: String): Array<File> = Storagex.getAppCacheDirs(this, packageName)


/**
 * Count the size of all APP cache directories
 */
@WorkerThread
inline fun Context.lengthAppCacheDirs(): Long = Storagex.lengthAppCacheDirs(this)

/**
 * Count the size of all APP cache directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
inline fun Context.lengthAppCacheDirs(packageName: String): Long = Storagex.lengthAppCacheDirs(this, packageName)

/**
 * Clean up all app cache directories
 * @return If it returns true, all the specified directories have been cleaned successfully
 */
@WorkerThread
inline fun Context.cleanAppCacheDirs(): Boolean = Storagex.cleanAppCacheDirs(this)

/**
 * Clean up all cache directories of the specified app
 *
 * @param packageName App package name
 * @return If it returns true, all the specified directories have been cleaned successfully
 */
@WorkerThread
inline fun Context.cleanAppCacheDirs(packageName: String): Boolean = Storagex.cleanAppCacheDirs(this, packageName)


/* ******************************************* App Files Dir *******************************************/


/**
 * Get the app external files directory
 */
inline fun Context.getAppExternalFilesDir(): File? = Storagex.getAppExternalFilesDir(this)

/**
 * Get the external files directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppExternalFilesDir(packageName: String): File? = Storagex.getAppExternalFilesDir(this, packageName)

/**
 * Get the all app external files directory
 */
inline fun Context.getAppExternalFilesDirs(): Array<File> = Storagex.getAppExternalFilesDirs(this)

/**
 * Get the all external files directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppExternalFilesDirs(packageName: String): Array<File> = Storagex.getAppExternalFilesDirs(this, packageName)


/**
 * Get the app internal files directory
 */
inline fun Context.getAppInternalFilesDir(): File = Storagex.getAppInternalFilesDir(this)

/**
 * Get the internal files directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppInternalFilesDir(packageName: String): File = Storagex.getAppInternalFilesDir(this, packageName)


/**
 * Get all app files directories
 */
inline fun Context.getAppFilesDirs(): Array<File> = Storagex.getAppFilesDirs(this)

/**
 * Get all files directories of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppFilesDirs(packageName: String): Array<File> = Storagex.getAppFilesDirs(this, packageName)


/**
 * Count the size of all APP files directories
 */
@WorkerThread
inline fun Context.lengthAppFilesDirs(): Long = Storagex.lengthAppFilesDirs(this)

/**
 * Count the size of all APP files directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
inline fun Context.lengthAppFilesDirs(packageName: String): Long = Storagex.lengthAppFilesDirs(this, packageName)

/**
 * Clean up all app files directories
 * @return If it returns true, all the specified directories have been cleaned successfully
 */
@WorkerThread
inline fun Context.cleanAppFilesDirs(): Boolean = Storagex.cleanAppFilesDirs(this)

/**
 * Clean up all files directories of the specified app
 *
 * @param packageName App package name
 * @return If it returns true, all the specified directories have been cleaned successfully
 */
@WorkerThread
inline fun Context.cleanAppFilesDirs(packageName: String): Boolean = Storagex.cleanAppFilesDirs(this, packageName)


/* ******************************************* App Obb Dir *******************************************/


/**
 * Get the app obb directory
 */
inline fun Context.getAppObbDir(): File? = Storagex.getAppObbDir(this)

/**
 * Get the obb directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppObbDir(packageName: String): File? = Storagex.getAppObbDir(this, packageName)

/**
 * Get the all app obb directory
 */
inline fun Context.getAppObbDirs(): Array<File> = Storagex.getAppObbDirs(this)

/**
 * Get the all obb directory of the specified app
 *
 * @param packageName App package name
 */
inline fun Context.getAppObbDirs(packageName: String): Array<File> = Storagex.getAppObbDirs(this, packageName)


/**
 * Count the size of all APP obb directories
 */
@WorkerThread
inline fun Context.lengthAppObbDirs(): Long = Storagex.lengthAppObbDirs(this)

/**
 * Count the size of all APP obb directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
inline fun Context.lengthAppObbDirs(packageName: String): Long = Storagex.lengthAppObbDirs(this, packageName)

/**
 * Clean up all app obb directories
 * @return If it returns true, all the specified directories have been cleaned successfully
 */
@WorkerThread
inline fun Context.cleanAppObbDirs(): Boolean = Storagex.cleanAppObbDirs(this)

/**
 * Clean up all obb directories of the specified app
 *
 * @param packageName App package name
 * @return If it returns true, all the specified directories have been cleaned successfully
 */
@WorkerThread
inline fun Context.cleanAppObbDirs(packageName: String): Boolean = Storagex.cleanAppObbDirs(this, packageName)


/* ******************************************* Other *******************************************/


/**
 * Traverses the given directory collection, returning a directory with free space greater than or equal to the minimum number of bytes
 *
 * @param minBytes Minimum number of bytes
 */
inline fun Array<File>?.filterByMinBytes(minBytes: Long): File? = Storagex.filterByMinBytes(this, minBytes)

/**
 * Traverse the specified directory list, check the remaining space of the directory, and return the file (not created)
 *
 * @param fileName     file name
 * @param minBytes     Minimum available bytes
 * @param cleanOldFile Whether to delete old files before judging the space
 */
inline fun Array<File>?.getFileIn(fileName: String, minBytes: Long, cleanOldFile: Boolean): File? =
        Storagex.getFileIn(this, fileName, minBytes, cleanOldFile)

/**
 * Traverse the specified directory list, check the remaining space of the directory, and return the file (not created)
 *
 * @param fileName file name
 * @param minBytes Minimum available bytes
 */
inline fun Array<File>?.getFileIn(fileName: String, minBytes: Long): File? = Storagex.getFileIn(this, fileName, minBytes)
