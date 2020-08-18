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

package com.github.panpf.tools4a.storage;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Storage related tool methods
 */
public class Storagex {

    private Storagex() {
    }

    @NonNull
    public static StorageManagerCompat storageManagerCompat(@NonNull Context context) {
        return new StorageManagerCompat(context);
    }

    /* ******************************************* Bytes *******************************************/


    /**
     * Get the number of free bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
     */
    public static long getFreeBytesOr(@NonNull File path, long defaultValue) {
        try {
            if (!path.exists() && !path.mkdirs()) {
                return defaultValue;
            }
            return StatFsx.getFreeBytesCompat(new StatFs(path.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get the number of total bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
     */
    public static long getTotalBytesOr(@NonNull File path, long defaultValue) {
        try {
            if (!path.exists() && !path.mkdirs()) {
                return defaultValue;
            }
            return StatFsx.getTotalBytesCompat(new StatFs(path.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get the number of available bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
     */
    public static long getAvailableBytesOr(@NonNull File path, long defaultValue) {
        try {
            if (!path.exists() && !path.mkdirs()) {
                return defaultValue;
            }
            return StatFsx.getAvailableBytesCompat(new StatFs(path.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get the number of free bytes for the primary shared/external storage media
     */
    public static long getExternalStorageFreeBytes() {
        return getFreeBytesOr(getExternalStorageDirectory(), -1);
    }

    /**
     * Get the number of total bytes for the primary shared/external storage media
     */
    public static long getExternalStorageTotalBytes() {
        return getTotalBytesOr(getExternalStorageDirectory(), -1);
    }

    /**
     * Get the number of available bytes for the primary shared/external storage media
     */
    public static long getExternalStorageAvailableBytes() {
        return getAvailableBytesOr(getExternalStorageDirectory(), -1);
    }


    /* ******************************************* Volume *******************************************/

    /**
     * Returns volume state for given path
     */
    @NonNull
    public static String getVolumeState(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null ? volumeCompat.getState(context) : "unknown";
    }

    /**
     * Returns true if the state of the volume at which the given path is mounted
     */
    public static boolean isVolumeMounted(@NonNull Context context, @NonNull File path) {
        return Environment.MEDIA_MOUNTED.equals(getVolumeState(context, path));
    }


    /**
     * Return true if the volume of the given path is the primary volume
     */
    public static boolean isPrimaryVolume(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null && volumeCompat.isPrimary();
    }

    /**
     * Return true if the volume of the given path is the emulated volume
     */
    public static boolean isVolumeEmulated(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null && volumeCompat.isEmulated();
    }

    /**
     * Return true if the volume of the given path is the removable volume
     */
    public static boolean isVolumeRemovable(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null && volumeCompat.isRemovable();
    }


    /**
     * Returns list of path for all volumes.
     */
    @NonNull
    public static String[] getVolumePaths(@NonNull Context context) {
        return storageManagerCompat(context).getVolumePaths();
    }

    /**
     * Returns list of path for all mounted volumes.
     */
    @NonNull
    public static String[] getMountedVolumePaths(@NonNull final Context context) {
        String[] paths = getVolumePaths(context);
        List<String> filterPathLis = new LinkedList<>();
        for (String path : paths) {
            if (isVolumeMounted(context, new File(path))) {
                filterPathLis.add(path);
            }
        }
        return filterPathLis.toArray(new String[0]);
    }


    /**
     * Returns list of files for all volumes.
     */
    @NonNull
    public static File[] getVolumeFiles(@NonNull Context context) {
        String[] paths = getVolumePaths(context);
        List<File> fileList = new LinkedList<>();
        for (String path : paths) {
            fileList.add(new File(path));
        }
        return fileList.toArray(new File[0]);
    }

    /**
     * Returns list of files for all mounted volumes.
     */
    @NonNull
    public static File[] getMountedVolumeFiles(@NonNull final Context context) {
        String[] paths = getVolumePaths(context);
        List<File> fileList = new LinkedList<>();
        for (String path : paths) {
            File file = new File(path);
            if (isVolumeMounted(context, file)) {
                fileList.add(file);
            }
        }
        return fileList.toArray(new File[0]);
    }


    /**
     * Returns list of StorageVolume for all volumes.
     */
    @NonNull
    public static List<StorageVolumeCompat> getVolumeList(@NonNull Context context) {
        return storageManagerCompat(context).getVolumeList();
    }

    /**
     * Returns list of StorageVolume for all mounted volumes.
     */
    @NonNull
    public static List<StorageVolumeCompat> getMountedVolumeList(@NonNull final Context context) {
        List<StorageVolumeCompat> volumeList = getVolumeList(context);
        List<StorageVolumeCompat> filterVolumeList = new ArrayList<>();
        for (StorageVolumeCompat volumeCompat : volumeList) {
            if (Environment.MEDIA_MOUNTED.equals(volumeCompat.getState(context))) {
                filterVolumeList.add(volumeCompat);
            }
        }
        return filterVolumeList;
    }

    /**
     * Returns array of StorageVolume for all volumes.
     */
    @NonNull
    public static StorageVolumeCompat[] getVolumes(@NonNull Context context) {
        return storageManagerCompat(context).getVolumes();
    }

    /**
     * Returns array of StorageVolume for all mounted volumes.
     */
    @NonNull
    public static StorageVolumeCompat[] getMountedVolumes(@NonNull final Context context) {
        StorageVolumeCompat[] volumes = getVolumes(context);
        List<StorageVolumeCompat> filterVolumeList = new LinkedList<>();
        for (StorageVolumeCompat volumeCompat : volumes) {
            if (Environment.MEDIA_MOUNTED.equals(volumeCompat.getState(context))) {
                filterVolumeList.add(volumeCompat);
            }
        }
        return filterVolumeList.toArray(new StorageVolumeCompat[0]);
    }


    /**
     * Returns StorageVolume for path.
     */
    @Nullable
    public static StorageVolumeCompat getVolume(@NonNull Context context, @NonNull File path) {
        return storageManagerCompat(context).getVolume(path);
    }


    /* ******************************************* External Storage *******************************************/


    /**
     * Returns the current state of the shared/external storage media at the
     * given path.
     *
     * @return one of {@link Environment#MEDIA_UNKNOWN}, {@link Environment#MEDIA_REMOVED},
     * {@link Environment#MEDIA_UNMOUNTED}, {@link Environment#MEDIA_CHECKING},
     * {@link Environment#MEDIA_NOFS}, {@link Environment#MEDIA_MOUNTED},
     * {@link Environment#MEDIA_MOUNTED_READ_ONLY}, {@link Environment#MEDIA_SHARED},
     * {@link Environment#MEDIA_BAD_REMOVAL}, or {@link Environment#MEDIA_UNMOUNTABLE}.
     * @see #getExternalStorageDirectory()
     */
    @NonNull
    public static String getExternalStorageState(@NonNull Context context, @NonNull File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Environment.getExternalStorageState(path);
        } else {
            return getVolumeState(context, path);
        }
    }

    /**
     * Return true if the state of the given path is MOUNTED
     */
    public static boolean isExternalStorageMounted(@NonNull Context context, @NonNull File path) {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageState(context, path));
    }

    /**
     * Returns the current state of the primary shared/external storage media.
     *
     * @return one of {@link Environment#MEDIA_UNKNOWN}, {@link Environment#MEDIA_REMOVED},
     * {@link Environment#MEDIA_UNMOUNTED}, {@link Environment#MEDIA_CHECKING},
     * {@link Environment#MEDIA_NOFS}, {@link Environment#MEDIA_MOUNTED},
     * {@link Environment#MEDIA_MOUNTED_READ_ONLY}, {@link Environment#MEDIA_SHARED},
     * {@link Environment#MEDIA_BAD_REMOVAL}, or {@link Environment#MEDIA_UNMOUNTABLE}.
     * @see #getExternalStorageDirectory()
     */
    @NonNull
    public static String getExternalStorageState() {
        return Environment.getExternalStorageState();
    }

    /**
     * Return true if the state of the primary shared/external storage media is MOUNTED
     */
    public static boolean isExternalStorageMounted() {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageState());
    }

    /**
     * Return true if the path is the primary volume
     */
    public static boolean isPrimaryExternalStorage(@NonNull Context context, @NonNull File path) {
        return isPrimaryVolume(context, path);
    }

    /**
     * Return true if the volume of the given path is emulated
     */
    public static boolean isExternalStorageEmulated(@NonNull Context context, @NonNull File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Environment.isExternalStorageEmulated(path);
        } else {
            StorageVolumeCompat storageVolume = storageManagerCompat(context).getVolume(path);
            return storageVolume != null && storageVolume.isEmulated();
        }
    }

    /**
     * Return true if the primary shared/external storage media is emulated
     */
    public static boolean isExternalStorageEmulated() {
        return Environment.isExternalStorageEmulated();
    }

    /**
     * Return true if the state of the given path is REMOVED
     */
    public static boolean isExternalStorageRemovable(@NonNull Context context, @NonNull File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Environment.isExternalStorageRemovable(path);
        } else {
            StorageVolumeCompat storageVolume = storageManagerCompat(context).getVolume(path);
            return storageVolume != null && storageVolume.isRemovable();
        }
    }

    /**
     * Return true if the state of the primary shared/external storage media is REMOVED
     */
    public static boolean isExternalStorageRemovable() {
        return Environment.isExternalStorageRemovable();
    }


    /**
     * Return the primary shared/external storage directory.
     */
    @NonNull
    public static File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * Return if the primary shared/external storage directory is mounted, otherwise return null
     */
    @Nullable
    public static File getMountedExternalStorageDirectory(@NonNull Context context) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return isExternalStorageMounted(context, externalStorageDirectory) ? externalStorageDirectory : null;
    }


    /**
     * Return the all shared/external storage directory.
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getExternalStorageDirectorys(@NonNull Context context, final boolean ignorePrimary) {
        List<File> dirs = new LinkedList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File[] files = context.getExternalFilesDirs(null);
            if (files != null && files.length > 0) {
                String lowerCaseSuffix = "/Android/data/".toLowerCase();
                for (File file : files) {
                    if (file != null) {
                        String lowerCasePath = file.getPath().toLowerCase();
                        int index = lowerCasePath.indexOf(lowerCaseSuffix);
                        if (index != -1) {
                            dirs.add(new File(file.getPath().substring(0, index)));
                        }
                    }
                }
            }
        }

        if (dirs.isEmpty()) {
            File[] files = getVolumeFiles(context);
            if (files.length > 0) {
                Collections.addAll(dirs, files);
            }
        }

        final File primaryExternalStorageDirectory = getExternalStorageDirectory();
        if (dirs.isEmpty()) {
            //noinspection ConstantConditions
            if (primaryExternalStorageDirectory != null) {
                dirs.add(primaryExternalStorageDirectory);
            }
        }

        List<File> filterDirList = new LinkedList<>();
        for (File dir : dirs) {
            if (dir != null && (!ignorePrimary || !dir.getPath().equals(primaryExternalStorageDirectory.getPath()))) {
                filterDirList.add(dir);
            }
        }
        return filterDirList.toArray(new File[0]);
    }

    /**
     * Returns the all shared/external storage directories that are mounted.
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorys(@NonNull final Context context, final boolean ignorePrimary) {
        File[] directorys = getExternalStorageDirectorys(context, ignorePrimary);
        List<File> fileList = new LinkedList<>();
        for (File dir : directorys) {
            if (dir != null && isExternalStorageMounted(context, dir)) {
                fileList.add(dir);
            }
        }
        return fileList.toArray(new File[0]);
    }


    /**
     * Return the all shared/external storage directory.
     */
    @NonNull
    public static File[] getExternalStorageDirectorys(@NonNull Context context) {
        return getExternalStorageDirectorys(context, false);
    }

    /**
     * Returns the all shared/external storage directories that are mounted.
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorys(@NonNull final Context context) {
        return getMountedExternalStorageDirectorys(context, false);
    }


    /**
     * Get the given path under all shared/external storage media
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath, boolean ignorePrimary) {
        File[] directorys = getExternalStorageDirectorys(context, ignorePrimary);
        List<File> fileList = new LinkedList<>();
        for (File dir : directorys) {
            if (dir != null) {
                fileList.add(new File(dir, childPath));
            }
        }
        return fileList.toArray(new File[0]);
    }

    /**
     * Get the given path under all mounted shared/external storage media
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath, boolean ignorePrimary) {
        File[] directorys = getMountedExternalStorageDirectorys(context, ignorePrimary);
        List<File> fileList = new LinkedList<>();
        for (File dir : directorys) {
            if (dir != null) {
                fileList.add(new File(dir, childPath));
            }
        }
        return fileList.toArray(new File[0]);
    }


    /**
     * Get the given path under all shared/external storage media
     */
    @NonNull
    public static File[] getExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath) {
        return getExternalStorageDirectorysWithPath(context, childPath, false);
    }

    /**
     * Get the given path under all mounted shared/external storage media
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath) {
        return getMountedExternalStorageDirectorysWithPath(context, childPath, false);
    }


    /* ******************************************* App Cache Dir *******************************************/


    /**
     * Get the app external cache directory
     */
    @Nullable
    public static File getAppExternalCacheDir(@NonNull Context context) {
        File file = context.getExternalCacheDir();
        if (file != null) {
            // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getExternalCacheDir() returns null
            return file;
        } else {
            File externalDir = getMountedExternalStorageDirectory(context);
            return externalDir != null ? new File(externalDir, "Android/data/" + context.getPackageName() + "/cache") : null;
        }
    }

    /**
     * Get the external cache directory of the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static File getAppExternalCacheDir(@NonNull Context context, @NonNull String packageName) {
        File selfExternalCacheDir = getAppExternalCacheDir(context);
        return selfExternalCacheDir != null ? new File(selfExternalCacheDir.getPath().replace(context.getPackageName(), packageName)) : null;
    }

    /**
     * Get the all app external cache directory
     */
    @NonNull
    public static File[] getAppExternalCacheDirs(@NonNull final Context context) {
        File[] externalCacheDirs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            externalCacheDirs = context.getExternalCacheDirs();
        }
        if (externalCacheDirs != null && externalCacheDirs.length > 0) {
            List<File> fileList = new LinkedList<>();
            for (File externalCacheDir : externalCacheDirs) {
                if (externalCacheDir != null) {
                    fileList.add(externalCacheDir);
                }
            }
            return fileList.toArray(new File[0]);
        } else {
            return getMountedExternalStorageDirectorysWithPath(context, "Android/data/" + context.getPackageName() + "/cache");
        }
    }

    /**
     * Get the all external cache directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppExternalCacheDirs(@NonNull final Context context, @NonNull final String packageName) {
        File[] directorys = getAppExternalCacheDirs(context);
        List<File> fileList = new LinkedList<>();
        for (File dir : directorys) {
            if (dir != null) {
                fileList.add(new File(dir.getPath().replace(context.getPackageName(), packageName)));
            }
        }
        return fileList.toArray(new File[0]);
    }


    /**
     * Get the app internal cache directory
     */
    @NonNull
    public static File getAppInternalCacheDir(@NonNull Context context) {
        return context.getCacheDir();
    }

    /**
     * Get the internal cache directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File getAppInternalCacheDir(@NonNull Context context, @NonNull String packageName) {
        return new File(getAppInternalCacheDir(context).getPath().replace(context.getPackageName(), packageName));
    }


    /**
     * Get all app cache directories
     */
    @NonNull
    public static File[] getAppCacheDirs(@NonNull Context context) {
        List<File> fileList = new LinkedList<>();
        File[] externalCacheDirs = getAppExternalCacheDirs(context);
        for (File externalCacheDir : externalCacheDirs) {
            if (externalCacheDir != null) {
                fileList.add(externalCacheDir);
            }
        }
        fileList.add(getAppInternalCacheDir(context));
        return fileList.toArray(new File[0]);
    }

    /**
     * Get all cache directories of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppCacheDirs(@NonNull final Context context, @NonNull final String packageName) {
        List<File> fileList = new LinkedList<>();
        File[] externalCacheDirs = getAppExternalCacheDirs(context, packageName);
        for (File externalCacheDir : externalCacheDirs) {
            if (externalCacheDir != null) {
                fileList.add(externalCacheDir);
            }
        }
        fileList.add(getAppInternalCacheDir(context, packageName));
        return fileList.toArray(new File[0]);
    }


    /**
     * Count the size of all APP cache directories
     */
    @WorkerThread
    public static long lengthAppCacheDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAppCacheDirs(context)) {
            sum += lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP cache directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static long lengthAppCacheDirs(@NonNull Context context, @NonNull String packageName) {
        long sum = 0;
        for (File file : getAppCacheDirs(context, packageName)) {
            sum += lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Clean up all app cache directories
     *
     * @return If it returns true, all the specified directories have been cleaned successfully
     */
    @WorkerThread
    public static boolean cleanAppCacheDirs(@NonNull Context context) {
        boolean allSuccess = true;
        for (File file : getAppCacheDirs(context)) {
            allSuccess &= cleanRecursively(file);
        }
        return allSuccess;
    }

    /**
     * Clean up all cache directories of the specified app
     *
     * @param packageName App package name
     * @return If it returns true, all the specified directories have been cleaned successfully
     */
    @WorkerThread
    public static boolean cleanAppCacheDirs(@NonNull Context context, @NonNull String packageName) {
        boolean allSuccess = true;
        for (File file : getAppCacheDirs(context, packageName)) {
            allSuccess &= cleanRecursively(file);
        }
        return allSuccess;
    }


    /* ******************************************* App Files Dir *******************************************/


    /**
     * Get the app external files directory
     */
    @Nullable
    public static File getAppExternalFilesDir(@NonNull Context context) {
        File file = context.getExternalFilesDir(null);
        if (file != null) {
            // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getExternalFilesDir() returns null
            return file;
        } else {
            File externalDir = getMountedExternalStorageDirectory(context);
            return externalDir != null ? new File(externalDir, "Android/data/" + context.getPackageName() + "/files") : null;
        }
    }

    /**
     * Get the external files directory of the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static File getAppExternalFilesDir(@NonNull Context context, @NonNull String packageName) {
        File selfExternalFilesDir = getAppExternalFilesDir(context);
        return selfExternalFilesDir != null ? new File(selfExternalFilesDir.getPath().replace(context.getPackageName(), packageName)) : null;
    }

    /**
     * Get the all app external files directory
     */
    @NonNull
    public static File[] getAppExternalFilesDirs(@NonNull final Context context) {
        File[] externalFilesDirs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            externalFilesDirs = context.getExternalFilesDirs(null);
        }
        if (externalFilesDirs != null && externalFilesDirs.length > 0) {
            List<File> fileList = new LinkedList<>();
            for (File externalFileDir : externalFilesDirs) {
                if (externalFileDir != null) {
                    fileList.add(externalFileDir);
                }
            }
            return fileList.toArray(new File[0]);
        } else {
            return getMountedExternalStorageDirectorysWithPath(context, "Android/data/" + context.getPackageName() + "/files");
        }
    }

    /**
     * Get the all external files directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppExternalFilesDirs(@NonNull final Context context, @NonNull final String packageName) {
        File[] directorys = getAppExternalFilesDirs(context);
        List<File> fileList = new LinkedList<>();
        for (File dir : directorys) {
            if (dir != null) {
                fileList.add(new File(dir.getPath().replace(context.getPackageName(), packageName)));
            }
        }
        return fileList.toArray(new File[0]);
    }


    /**
     * Get the app internal files directory
     */
    @NonNull
    public static File getAppInternalFilesDir(@NonNull Context context) {
        return context.getFilesDir();
    }

    /**
     * Get the internal files directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File getAppInternalFilesDir(@NonNull Context context, @NonNull String packageName) {
        return new File(getAppInternalFilesDir(context).getPath().replace(context.getPackageName(), packageName));
    }


    /**
     * Get all app files directories
     */
    @NonNull
    public static File[] getAppFilesDirs(@NonNull Context context) {
        List<File> fileList = new LinkedList<>();
        File[] externalFilesDirs = getAppExternalFilesDirs(context);
        for (File externalFileDir : externalFilesDirs) {
            if (externalFileDir != null) {
                fileList.add(externalFileDir);
            }
        }
        fileList.add(getAppInternalFilesDir(context));
        return fileList.toArray(new File[0]);
    }

    /**
     * Get all files directories of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppFilesDirs(@NonNull final Context context, @NonNull final String packageName) {
        List<File> fileList = new LinkedList<>();
        File[] externalFilesDirs = getAppExternalFilesDirs(context, packageName);
        for (File externalFileDir : externalFilesDirs) {
            if (externalFileDir != null) {
                fileList.add(externalFileDir);
            }
        }
        fileList.add(getAppInternalFilesDir(context, packageName));
        return fileList.toArray(new File[0]);
    }


    /**
     * Count the size of all APP files directories
     */
    @WorkerThread
    public static long lengthAppFilesDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAppFilesDirs(context)) {
            sum += lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP files directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static long lengthAppFilesDirs(@NonNull Context context, @NonNull String packageName) {
        long sum = 0;
        for (File file : getAppFilesDirs(context, packageName)) {
            sum += lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Clean up all app files directories
     *
     * @return If it returns true, all the specified directories have been cleaned successfully
     */
    @WorkerThread
    public static boolean cleanAppFilesDirs(@NonNull Context context) {
        boolean allSuccess = true;
        for (File file : getAppFilesDirs(context)) {
            allSuccess &= cleanRecursively(file);
        }
        return allSuccess;
    }

    /**
     * Clean up all files directories of the specified app
     *
     * @param packageName App package name
     * @return If it returns true, all the specified directories have been cleaned successfully
     */
    @WorkerThread
    public static boolean cleanAppFilesDirs(@NonNull Context context, @NonNull String packageName) {
        boolean allSuccess = true;
        for (File file : getAppFilesDirs(context, packageName)) {
            allSuccess &= cleanRecursively(file);
        }
        return allSuccess;
    }


    /* ******************************************* App Obb Dir *******************************************/


    /**
     * Get the app obb directory
     */
    @Nullable
    public static File getAppObbDir(@NonNull Context context) {
        File file = context.getObbDir();
        if (file != null) {
            // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getObbDir() returns null
            return file;
        } else {
            File externalDir = getMountedExternalStorageDirectory(context);
            return externalDir != null ? new File(externalDir, "Android/obb/" + context.getPackageName()) : null;
        }
    }

    /**
     * Get the obb directory of the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static File getAppObbDir(@NonNull Context context, @NonNull String packageName) {
        File selfObbDir = getAppObbDir(context);
        return selfObbDir != null ? new File(selfObbDir.getPath().replace(context.getPackageName(), packageName)) : null;
    }

    /**
     * Get the all app obb directory
     */
    @NonNull
    public static File[] getAppObbDirs(@NonNull final Context context) {
        File[] obbDirs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            obbDirs = context.getObbDirs();
        }
        if (obbDirs != null && obbDirs.length > 0) {
            List<File> fileList = new LinkedList<>();
            for (File obbDir : obbDirs) {
                if (obbDir != null) {
                    fileList.add(obbDir);
                }
            }
            return fileList.toArray(new File[0]);
        } else {
            return getMountedExternalStorageDirectorysWithPath(context, "Android/obb/" + context.getPackageName());
        }
    }

    /**
     * Get the all obb directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppObbDirs(@NonNull final Context context, @NonNull final String packageName) {
        File[] directorys = getAppObbDirs(context);
        List<File> fileList = new LinkedList<>();
        for (File dir : directorys) {
            if (dir != null) {
                fileList.add(new File(dir.getPath().replace(context.getPackageName(), packageName)));
            }
        }
        return fileList.toArray(new File[0]);
    }


    /**
     * Count the size of all APP obb directories
     */
    @WorkerThread
    public static long lengthAppObbDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAppObbDirs(context)) {
            sum += lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP obb directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static long lengthAppObbDirs(@NonNull Context context, @NonNull String packageName) {
        long sum = 0;
        for (File file : getAppObbDirs(context, packageName)) {
            sum += lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Clean up all app obb directories
     *
     * @return If it returns true, all the specified directories have been cleaned successfully
     */
    @WorkerThread
    public static boolean cleanAppObbDirs(@NonNull Context context) {
        boolean allSuccess = true;
        for (File file : getAppObbDirs(context)) {
            allSuccess &= cleanRecursively(file);
        }
        return allSuccess;
    }

    /**
     * Clean up all obb directories of the specified app
     *
     * @param packageName App package name
     * @return If it returns true, all the specified directories have been cleaned successfully
     */
    @WorkerThread
    public static boolean cleanAppObbDirs(@NonNull Context context, @NonNull String packageName) {
        boolean allSuccess = true;
        for (File file : getAppObbDirs(context, packageName)) {
            allSuccess &= cleanRecursively(file);
        }
        return allSuccess;
    }


    /* ******************************************* Other *******************************************/


    /**
     * Traverses the given directory collection, returning a directory with free space greater than or equal to the minimum number of bytes
     *
     * @param paths    Directory list
     * @param minBytes Minimum number of bytes
     */
    @Nullable
    public static File filterByMinBytes(@Nullable File[] paths, final long minBytes) {
        if (paths == null || paths.length == 0) return null;
        for (File dir : paths) {
            if (!dir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();
            }
            if (dir.isDirectory() && getAvailableBytesOr(dir, 0) >= minBytes) {
                return dir;
            }
        }
        return null;
    }

    /**
     * Traverse the specified directory list, check the remaining space of the directory, and return the file (not created)
     *
     * @param fileName     file name
     * @param minBytes     Minimum available bytes
     * @param cleanOldFile Whether to delete old files before judging the space
     * @param dirs         Directory list
     */
    @Nullable
    public static File getFileIn(@Nullable File[] dirs, @NonNull String fileName, long minBytes, boolean cleanOldFile) {
        if (dirs != null && dirs.length > 0) {
            for (File dir : dirs) {
                if (dir == null || dir.isFile()) {
                    continue;
                }

                File newFile = new File(dir, fileName);
                if (cleanOldFile && newFile.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    newFile.delete();
                }

                if (Storagex.getAvailableBytesOr(dir, -1) >= minBytes) {
                    return newFile;
                }
            }
        }
        return null;
    }

    /**
     * Traverse the specified directory list, check the remaining space of the directory, and return the file (not created)
     *
     * @param fileName file name
     * @param minBytes Minimum available bytes
     * @param dirs     Directory list
     */
    @Nullable
    public static File getFileIn(@Nullable File[] dirs, @NonNull String fileName, long minBytes) {
        return getFileIn(dirs, fileName, minBytes, false);
    }

    /**
     * Get the length of the file or dir, if it is a directory, it will superimpose the length of all subfiles
     */
    private static long lengthRecursively(@NonNull File file) {
        if (!file.exists()) return 0;
        if (file.isFile()) return file.length();

        long length = 0;

        Queue<File> fileQueue = new LinkedList<>();
        fileQueue.add(file);

        File childFile;
        while (true) {
            childFile = fileQueue.poll();
            if (childFile == null || !childFile.exists()) {
                break;
            }

            if (childFile.isFile()) {
                length += childFile.length();
            } else {
                File[] childChildFiles = childFile.listFiles();
                if (childChildFiles != null && childChildFiles.length > 0) {
                    Collections.addAll(fileQueue, childChildFiles);
                }
            }
        }
        return length;
    }

    private static boolean cleanRecursively(@NonNull File dir) {
        if (!dir.exists() || dir.isFile()) return true;

        File[] childFiles = dir.listFiles();
        if (childFiles == null || childFiles.length <= 0) return true;

        boolean result = true;

        Stack<File> fileStack = new Stack<>();
        Stack<File> dirStack = new Stack<>();

        Collections.addAll(fileStack, childFiles);

        File childFile;
        while (true) {
            try {
                childFile = fileStack.pop();
            } catch (EmptyStackException e) {
                break;
            }

            if (childFile != null && childFile.exists()) {
                if (childFile.isFile()) {
                    result = result && childFile.delete();
                } else {
                    dirStack.push(childFile);

                    File[] childChildFiles = childFile.listFiles();
                    if (childChildFiles != null && childChildFiles.length > 0) {
                        Collections.addAll(fileStack, childChildFiles);
                    }
                }
            }
        }

        File childDir;
        while (true) {
            try {
                childDir = dirStack.pop();
            } catch (EmptyStackException e) {
                break;
            }

            if (childDir != null && childDir.exists()) {
                result = result && dir.delete();
            }
        }

        return result;
    }
}