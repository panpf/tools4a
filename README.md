# ANDROIDX

[![Platform][platform_android_icon]][platform_android_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
[![API][min_api_icon]][min_api_link]
[![License][license_icon]][license_link]

Extensions to the Android standard library and support libraries and some basic tools

## Getting Started

Add the following maven repository to your project `build.gradle` file:
```groovy
allprojects {
    repositories {
        maven { url "https://dl.bintray.com/panpf/maven/" }
    }
}
```

Add the following dependencies to your module `build.gradle` file ：

```grovvy
implementation "com.github.panpf.tools4a:tools4a:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-ktx:$LAST_VERSION" // Kotlin extension, not required
```

Please replace `$LAST_VERSION` with the latest version: [![Download][version_java_icon]][version_java_link]

The "com.github.panpf.tools4a:tools4a:$LAST_VERSION" dependency will add all the modules included in tools4a to your project. If you only need of one of the modules, you can just add it to your project, all supported modules as follows:
```groovy
implementation "com.github.panpf.tools4a:tools4a-activity:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-activity-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-args:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-args-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-build:$LAST_VERSION"

implementation "com.github.panpf.tools4a:tools4a-clipboard:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-clipboard-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-content:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-content-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-device:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-device-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-dialog:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-dialog-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-dimen:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-dimen-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-display:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-display-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-fileprovider:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-fileprovider-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-fragment:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-fragment-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-graphics:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-graphics-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-inputmethod:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-inputmethod-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-network:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-network-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-packages:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-packages-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-permission:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-permission-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-prefsdelegate:$LAST_VERSION"   // All written by Kotlin

implementation "com.github.panpf.tools4a:tools4a-rom:$LAST_VERSION"

implementation "com.github.panpf.tools4a:tools4a-root:$LAST_VERSION"

implementation "com.github.panpf.tools4a:tools4a-run:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-run-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-service:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-service-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-settings:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-settings-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-storage:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-storage-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-systemproperties:$LAST_VERSION"

implementation "com.github.panpf.tools4a:tools4a-toast:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-toast-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-utils:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-utils-ktx:$LAST_VERSION" // Kotlin extension, not required

implementation "com.github.panpf.tools4a:tools4a-view:$LAST_VERSION"
implementation "com.github.panpf.tools4a:tools4a-view-ktx:$LAST_VERSION" // Kotlin extension, not required
```

Dependencies:
* [androidx.fragment:fragment:1.2.5][fragment_versions]
* [androidx.annotation:annotation:1.1.0][annotation_versions]
* [androidx.core:core:1.1.0][core_versions]
* [androidx.collection:collection:1.1.0][collection_versions]
* [androidx.lifecycle:lifecycle-common:2.2.0][lifecycle_versions]
* [org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72][kotlin_versions]（Only the '*-ktx' library dependencies it）


## Modules

### tools4a-activity
* Activityx: [Activityx.java] ([Test][ActivityxTest.java]) | [Activityx.kt] ([Test][ActivityxTest.kt])
    * isDestroyedCompat, convertToTranslucentCompat, convertFromTranslucentCompat, 
    * getImplFromParent, canStart, start, startByClass, safeStart, safeStartByClass

### tools4a-args
* Argsx: [Argsx.java] ([Test][ArgsxTest.java]) | [Argsx.kt] ([Test][ArgsxTest.kt])
    * read\*ArgOrThrow, read\*ArgOr, read\*ArgOrNull,
    * read\*UriArgOrThrow, read\*UriArgOr, read\*UriArgOrNull,
    * read\*IntentUriArgOrThrow, read\*IntentUriArgOr,read\*IntentUriArgOrNull,
    * read\*UriIntentArgOrThrow, read\*UriIntentArgOr, read\*UriIntentArgOrNull
* ArgsBinder: [ArgsBinder.kt] ([Test][ArgsBinderTest.kt])
    * bind\*Arg, bind\*ArgOr, bind\*UriArg, bind\*UriArgOr, bind\*IntentUriArg, 
    * bind\*IntentUriArgOr, bind\*UriIntentArg, bind\*UriIntentArgOr

### tools4a-build
* Buildx: [Buildx.java] ([Test]BuildxTest.java])
    * isAtLeastJ/isAtLeast16/isAtLeast4_1..., getVersionName, getVersionCodeName

### tools4a-clipboard
* Clipboardx: [Clipboardx.java] ([Test][ClipboardxTest.java]) | [Clipboardx.kt] ([Test][ClipboardxTest.kt])
    * copy, copyText, copyHtmlText, copyIntent, copyUri, copyRawUri, copyMimeTypeUri, 
    * copyContents, get, getLabel, getContents, getText, getHtmlText, getIntent, getUri, 
    * addPrimaryClipChangedListener, removePrimaryClipChangedListener, clear

### tools4a-context
* Context: [Contextx.java] ([Test][ContextxTest.java]) | [Contextx.kt] ([Test][ContextxTest.kt])
    * appContext, requireContext, requireAppContext, systemService, systemServiceOrNull, 
    * systemServiceInUI, systemServiceOrNullInUI, \*Manager

### tools4a-device
* Devicex: [Devicex.java] ([Test][DevicexTest.java]) | [Devicex.kt] ([Test][DevicexTest.kt])
    * getProduct, getBrand, getModel, getDevice, getHardware, getSupportedAbis, 
    * getPhoneNumberOr, getPhoneNumberOrThrow, getPhoneNumberOrNull, getDeviceIdOr,
    * getDeviceIdOrThrow, getDeviceIdOrNull, getAndroidIdOr, getAndroidIdOrThrow, 
    * getAndroidIdOrNull, getSubscriberIdOr, getSubscriberIdOrThrow, getSubscriberIdOrNull,
    * getSimSerialNumberOr, getSimSerialNumberOrThrow, getSimSerialNumberOrNull, 
    * getSerialOr, getSerialOrThrow, getSerialOrNull, getIMEIOr, getIMEIOrThrow, 
    * getIMEIOrNull, getIMSIOr, getIMSIOrThrow, getIMSIOrNull, getMacAddressOr, getMacAddress

### tools4a-dialog
* Dialog: [Dialogx.java] ([Test][DialogxTest.java]) | [Dialogx.kt] ([Test][DialogxTest.kt])
    * setClickButtonClosable, showProgressDialog

### tools4a-dimen
* Dimenx: [Dimenx.java] ([Test][DimenxTest.java]) | [Dimenx.kt] ([Test][DimenxTest.kt])
    * dp2px, px2dp, sp2px, px2sp, applyDimension

### tools4a-display
* Displayx: [Displayx.java] ([Test][DisplayxTest.java]) | [Displayx.kt] ([Test][DisplayxTest.kt])
    * getScreenSize, getScreenWidth, getScreenHeight, getActionBarSize, getMetrics, getDensity, 
    * getDisplayRotation, getDensityDpi, isOrientationPortrait, isOrientationLandscape, 
    * isOrientationUndefined, getRotation, hasNavigationBar, getStatusBarHeight, 
    * getNavigationBarHeight, getNavigationBarWidth

### tools4a-fileprovider
* FileProviderx: [FileProviderx.java] ([Test][FileProviderxTest.java]) | [FileProviderx.kt] ([Test][FileProviderxTest.kt])
    * getShareFileUri

### tools4a-fragment
* Fragmentx: [Fragmentx.java] ([Test][FragmentxTest.java]) | [Fragmentx.kt] ([Test][FragmentxTest.kt])
    * getApplication, requireApplication, isDestroyedCompat, getImplFromParent, instantiate,
    * findUserVisibleChildFragment, findFragmentByViewPagerCurrentItem

### graphics
* Drawablex: [Drawablex.java] ([Test][DrawablexTest.java]) | [Drawablex.kt] ([Test][DrawablexTest.kt])
    * toBitmapWithIntrinsicSize, toBitmapWithBoundsSize, changeColor, changeResDrawableColor
* Bitmapx: [Bitmapx.java] ([Test][BitmapxTest.java]) | [Bitmapx.kt] ([Test][BitmapxTest.kt])
    * crop, cropTo, centerCrop, centerCropTo, circular, circularTo, tint, createByColor, 
    * toByteArray, writeToFile, readBitmap, toDrawable, use, calculateSamplingSize, 
    * calculateSamplingSizeForRegion
* Imagex: [Imagex.java] ([Test][ImagexTest.java]) | [Imagex.kt] ([Test][ImagexTest.kt])
    * getMimeType, getMimeSubType
* Colorx: [Colorx.java] ([Test][ColorxTest.java]) | [Colorx.kt] ([Test][ColorxTest.kt])
    * WHITE/BLACK/RED..., getAlpha, setAlpha, addAlpha, getHSVHue, setHSVHue, getHSVSaturation,
    * setHSVSaturation, addHSVSaturation, getHSVValue, setHSVValue, addHSVValue, isLight,
    * createMatrixColorFilter, argbEvaluate
* Matrixx: [Matrixx.java] ([Test][MatrixxTest.java]) | [Matrixx.kt] ([Test][MatrixxTest.kt])
    * getValue, getScale
* OpenGlx: [OpenGlx.java] ([Test][OpenGlxTest.java]) | [OpenGlx.kt] [Matrixx.kt] ([Test][OpenGlxTest.kt])
    * getVersion, getMaxTextureSize,
* Paintx: [Paintx.java] ([Test][PaintxTest.java]) | [Paintx.kt] ([Test][PaintxTest.kt])
    * getTextWidth, getTextHeight, getTextWidthByBounds, getTextHeightByBounds, getTextLeading
* Resizex: [Resizex.java] ([Test][ResizexTest.java])
    * srcMappingStartRect, srcMappingCenterRect, srcMappingEndRect, srcMatrixRect,
    * scaleTargetSize, calculator

### tools4a-inputmethod
* InputMethodx: [InputMethodx.java] ([Test][InputMethodxTest.java]) | [InputMethodx.kt] ([Test][InputMethodxTest.kt])
    * showSoftInput, delayShowSoftInput, hideSoftInput, moveCursorToEnd, 
    * moveCursorToStart, moveCursorTo

### tools4a-intent
* Intentx: [Intentx.java] ([Test][IntentxTest.java]) | [Intentx.kt] ([Test][IntentxTest.kt])
    * createRecordingIntent, createLaunchDialingIntent, createCallPhoneIntent, 
    * createLaunchSendSmsIntent, createLaunchWebBrowserIntent, createScanFileBroadcastIntent, 
    * createActivityIntentByResolveInfo, createTakePhotoIntent, createPickImageIntent, 
    * createCropImageIntent, createSendTextIntent, createSendTextFileIntent, 
    * createSendImageFileIntent, createSendFileIntent

### tools4a-network
* Network: [Networkx.java] ([Test][NetworkxTest.java]) | [Networkx.kt] ([Test][NetworkxTest.kt])
    * getState, isActivated, isWifiActivated, isNoMeteredWifiActivated, isMobileActivated,
    * isBluetoothActivated, getType, isVPNActivated, isMetered, isRoaming, isFailover, 
    & getTypeName, getSubtypeName, getExtraInfo, getNetworkInfo, getConnectivity, 
    * getWifiState, isWifiEnabled, setWifiEnabled, isMobileEnabled, setMobileEnabled, 
    * getGateway, getDNS1, getDNS2
* NetworkState: [NetworkState.java] ([Test][NetworkStateTest.java])
    * isActivated, isWifiActivated, isNoMeteredWifiActivated, isMobileActivated, 
    * isBluetoothActivated, getType, isVPNActivated, isMetered, isRoaming, isFailover, 
    * getTypeName, getSubtypeName, getExtraInfo, getNetworkInfo, getConnectivity

### tools4a-packages
* Packagex: [Packagex.java] ([Test][PackagexTest.java]) | [Packagex.kt] ([Test][PackagexTest.kt])
    * isPackageInstalled, isSystemPackage, isSystemPackageOr, isDebuggablePackage,
    * isDebuggablePackageOr, isJunitTestPackage, isJunitTestPackageOr,
    * createInstallPackageIntent, createUninstallPackageIntent, createLaunchPackageIntent,
    * createApplicationDetailsSettingsIntent, requestInstallPackage, requestUninstallPackage,
    * getPackage, getPackageOrNull, getPackageVersionCode, getPackageVersionCodeOr,
    * getPackageLongVersionCode, getPackageLongVersionCodeOr, getPackageVersionName,
    * getPackageVersionNameOr, getPackageVersionNameOrEmpty, getPackageVersionNameOrNull,
    * getFirstPackageByFilter, getFirstPackageByFilterFlags, getFirstPackage,
    * listPackageInfo, listPackageByFilter, listPackageByFilterFlags, listPackage,
    * listPackageVersionCodeToMapByFilter, listPackageVersionCodeToMapByFilterFlags,
    * listPackageVersionCodeToMap, listPackageNameByFilter, listPackageNameByFilterFlags,
    * listPackageName, countPackageByFilter, countPackageByFilterFlags, countPackage,
    * getPackageApkFile, getPackageApkFileOrNull, getPackageSignatureBytes,
    * getPackageSignatureBytesOrNull, getPackageIconDrawable, getPackageIconDrawableOrNull,
    * getApkIconDrawable, getApkIconDrawableOrNull
* PackageInfox: [PackageInfox.java] ([Test][PackageInfoxTest.java]) | [PackageInfox.kt] ([Test][PackageInfoxTest.kt])
    * findActivityInfo, findActivityInfoByName, findServiceInfo, findServiceInfoByName
    * findReceiverInfo, findReceiverInfoByName, findProviderInfo, findProviderInfoByName
    * findPermissionInfo, findPermissionInfoByName, findRequestedPermission, findRequestedPermissionByName
    * findMetaDataWithName, findMetaDataByName, findMetaDataWithValue, findMetaDataByValue

### tools4a-permission
* Permissionx: [Permissionx.java] ([Test][PermissionxTest.java]) | [Permissionx.kt] ([Test][PermissionxTest.kt])
    * isGrantPermissions, filterDeniedPermissions

### tools4a-prefsdelegate
* [PrefsDelegateTest.kt][PrefsDelegateTest.kt]
* [BeanListNullablePrefsDelegate.kt][BeanListNullablePrefsDelegate.kt], [BeanListPrefsDelegate.kt][BeanListPrefsDelegate.kt], 
* [BeanNullablePrefsDelegate.kt][BeanNullablePrefsDelegate.kt], [BeanPrefsDelegate.kt][BeanPrefsDelegate.kt], 
* [BooleanNullablePrefsDelegate.kt][BooleanNullablePrefsDelegate.kt], [BooleanPrefsDelegate.kt][BooleanPrefsDelegate.kt], 
* [EnumNullablePrefsDelegate.kt][EnumNullablePrefsDelegate.kt], [EnumPrefsDelegate.kt][EnumPrefsDelegate.kt], 
* [FloatNullablePrefsDelegate.kt][FloatNullablePrefsDelegate.kt], [FloatPrefsDelegate.kt][FloatPrefsDelegate.kt],
* [IntNullablePrefsDelegate.kt][IntNullablePrefsDelegate.kt], [IntPrefsDelegate.kt][IntPrefsDelegate.kt], 
* [LongNullablePrefsDelegate.kt][LongNullablePrefsDelegate.kt], [LongPrefsDelegate.kt][LongPrefsDelegate.kt], 
* [StringListNullablePrefsDelegate.kt][StringListNullablePrefsDelegate.kt], [StringListPrefsDelegate.kt][StringListPrefsDelegate.kt], 
* [StringNullablePrefsDelegate.kt][StringNullablePrefsDelegate.kt], [StringPrefsDelegate.kt][StringPrefsDelegate.kt], 
* [StringSetNullablePrefsDelegate.kt][StringSetNullablePrefsDelegate.kt], [StringSetPrefsDelegate.kt][StringSetPrefsDelegate.kt], 

### tools4a-rom
* Romx: [Romx.java] ([Test][RomxTest.java])
    * getType, isType, isMiuiType, isEmuiType, isFlymeType, isColorType, 
    * isFuntouchOSType, isSmartisanOSType, isH2OSType, getTypeName, 
    * getVersionName, getVersionCode, getVersionIncremental

### tools4a-root
* Rootx: [Rootx.java] ([Test][RootxTest.java])
    * isRooted

### tools4a-run
* Runx: [Runx.java] ([Test][RunxTest.java]) | [Runx.kt] ([Test][RunxTest.kt])
    * getMainHandler, runOnUIThread, runOnUIThreadAndWait, runOnUIThreadAndWaitResult, 
    * runOnUIThreadAndWaitNullableResult, isOnTheMainThread, isOnTheMainProcess, 
    * getTheProcessName, getTheProcessNameSuffix

### tools4a-service
* Servicex: [Servicex.java] ([Test][ServicexTest.java]) | [Servicex.kt] ([Test][ServicexTest.kt])
    * isRunning, start, stop, isAccessibilityServiceEnabled

### tools4a-settings
* Settingsx: [Settingsx.java] ([Test][SettingsxTest.java])  | [Settingsx.kt] ([Test][SettingsxTest.kt])
    * isScreenBrightnessModeAutomatic, setScreenBrightnessModeAutomatic, getScreenBrightness,
    * setScreenBrightness, getScreenOffTimeout, setScreenOffTimeout, isAirplaneModeOn, 
    * setAirplaneModeOn, isBluetoothOn, setBluetoothOn

### tools4a-storage
* Storagex: [Storagex.java] ([Test][StoragexTest.java]) | [Storagex.kt] ([Test][StoragexTest.kt])
    * getFreeBytesOr, getTotalBytesOr, getAvailableBytesOr, getVolumeState, isVolumeMounted, isPrimaryVolume,
    * isVolumeEmulated, isVolumeRemovable, getVolumePaths, getMountedVolumePaths, getVolumeFiles,
    * getMountedVolumeFiles, getVolumeList, getMountedVolumeList, getVolumes, getMountedVolumes, getVolume,
    * getExternalStorageState, isExternalStorageMounted, isPrimaryExternalStorage, isExternalStorageEmulated,
    * isExternalStorageEmulated, isExternalStorageRemovable, isExternalStorageRemovable,
    * getExternalStorageDirectory, getMountedExternalStorageDirectory, getExternalStorageDirectorys,
    * getMountedExternalStorageDirectorys, getExternalStorageDirectorys, getMountedExternalStorageDirectorys,
    * getExternalStorageDirectorysWithPath, getMountedExternalStorageDirectorysWithPath,
    * getAppExternalCacheDir, getAppExternalCacheDirs, getAppInternalCacheDir, getAppCacheDirs,
    * lengthAppCacheDirs, cleanAppCacheDirs, getAppExternalFilesDir, getAppExternalFilesDirs,
    * getAppInternalFilesDir, getAppFilesDirs, lengthAppFilesDirs, cleanAppFilesDirs,
    * getAppObbDir, getAppObbDirs, lengthAppObbDirs, cleanAppObbDirs, filterByMinBytes, getFileIn
* StatFs: [StatFsx.java] ([Test][StatFsxTest.java]) | [StatFsx.kt] ([Test][StatFsxTest.kt])
    * getCompatAvailableBytes, getCompatFreeBytes, getCompatTotalBytes
* StorageManagerCompat: [StorageManagerCompat.java] ([Test][StorageManagerCompatTest.java]) | [StorageManagerCompat.kt] ([Test][StorageManagerCompatTest.kt])
    * getVolumeList, getVolumes, getVolumePaths, getVolumeState, getVolume
* StorageVolumeCompat: [StorageVolumeCompat.java] ([Test][StorageVolumeCompatTest.java]) | [StorageVolumeCompat.kt] ([Test][StorageVolumeCompatTest.kt])
    * getPath, getPathFile, isPrimary, isRemovable, isEmulated, getState, allowMassStorage, getMaxFileSize,

### tools4a-systemproperties
* SystemPropertiesx: [SystemPropertiesx.java] ([Test][SystemPropertiesxTest.java])
    * get, getOr, getIntOr, getLongOr, getBooleanOr, set, addChangeCallbacks, callChangeCallbacks

### tools4a-toast
* Toastx: [Toastx.java] ([Test][ToastxTest.java]) | [Toastx.kt] ([Test][ToastxTest.kt])
    * showLong, showWithFormatLong, showShort, showWithFormatShort, showLongWithView, showShortWithView

### tools4a-utils
* LifecycleBroadcastReceiver: [LifecycleBroadcastReceiver.java] ([Test][LifecycleBroadcastReceiverTest.java])
* Textx: [Textx.java] ([Test][TextxTest.java]) | [Textx.kt] ([Test][TextxTest.kt])
    * textToBitmap, changeColorByHtml, changeColorToRedByHtml, changeKeywordColorByHtml, 
    * changeKeywordColorToRedByHtml, changeColorBySpannable, changeColorToRedBySpannable, 
    * changeKeywordColorBySpannable, changeKeywordColorToRedBySpannable
* WeakAsyncTask: [WeakAsyncTask.java] ([Test][WeakAsyncTaskTest.java])

### tools4a-view
* ViewAnim: [ViewAnimx.java] ([Test][ViewAnimxTest.java]) | [ViewAnimx.kt] ([Test][ViewAnimxTest.kt])
    * animAlpha, animTranslate, shake, shock, startAnimFromRes, invisibleByAnimAlpha,
    * goneByAnimAlpha, visibleByAnimAlpha,
* View: [Viewx.java] ([Test][ViewxTest.java]) | [Viewx.kt] ([Test][ViewxTest.kt])
    * setLongClickToastHint, setLayoutWidth, setLayoutHeight, setLayoutSize, setLayoutMarginTop,
    * addLayoutHeight, addLayoutWidth, addLayoutSize, addLayoutMarginTop, toBitmap, toBitmapByMaxWidth,
    * toBitmapByMaxHeight, inflateLayout, addPaddingTopByStatusBarHeight
* Window: [Windowx.java] ([Test][WindowxTest.java]) | [Windowx.kt] ([Test][WindowxTest.kt])
    * getBrightness, setBrightness, isBrightnessFlowSystem

### tools4a-other（Not available for the time being）
* Preferencex: [Preferencex.java] ([Test][PreferencexTest.java]) | [Preferencex.kt] ([Test][PreferencexTest.kt])
    * getPreferences, getDefaultPreferences, putIntTo, putInt, putIntsTo, putInts, putLongTo, putLong, putLongsTo, putLongs,
    * putBooleanTo, putBoolean, putBooleansTo, putBooleans, putFloatTo, putFloat, putFloatsTo, putFloats,
    * putStringTo, putString, putStringsTo, putStrings, putStringSetTo, putStringSet, putStringSetsTo, putStringSets,
    * getIntFrom, getInt, getLongFrom, getLong, getBooleanFrom, getBoolean, getFloatFrom, getFloat,
    * getStringFrom, getStringOrNullFrom, getString, getStringOrNull, getStringSetFrom, getStringSetOrNullFrom,
    * getStringSet, getStringSetOrNull, getAllFrom, getAll, removeFrom, remove, contains, containsAny, containsAll,
    * containsFrom, containsAnyFrom, containsAllFrom, isEmpty, isEmptyFrom, clear,
    * registerOnChangeListener, registerOnChangeListenerTo, unregisterOnChangeListener, unregisterOnChangeListenerFrom
* SingletonLazy: [SingletonLazy.java]

## Change Log

Please view the [CHANGELOG.md] file


## License
    Copyright (C) 2020 panpf <panpfpanpf@outlook.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[platform_android_icon]: https://img.shields.io/badge/Platform-Android-brightgreen.svg
[platform_android_link]: https://android.com
[platform_kotlin_icon]: https://img.shields.io/badge/Platform-Kotlin-blue.svg
[platform_kotlin_link]: http://kotlinlang.org
[min_api_icon]: https://img.shields.io/badge/API-16%2B-orange.svg
[min_api_link]: https://developer.android.com/about/dashboards/
[license_icon]: https://img.shields.io/badge/License-Apache%202-blue.svg
[license_link]: https://www.apache.org/licenses/LICENSE-2.0
[version_java_icon]: https://api.bintray.com/packages/panpf/maven/tools4a/images/download.svg
[version_java_link]: https://bintray.com/panpf/maven/tools4a/_latestVersion
[version_kotlin_icon]: https://api.bintray.com/packages/panpf/maven/tools4a-ktx/images/download.svg
[version_kotlin_link]: https://bintray.com/panpf/maven/tools4a-ktx/_latestVersion
[kotlin_stdlib]: https://kotlinlang.org/
[kotlin_versions]: https://blog.jetbrains.com/kotlin/
[fragment_versions]: https://developer.android.google.cn/jetpack/androidx/releases/fragment
[annotation_versions]: https://developer.android.google.cn/jetpack/androidx/releases/annotation
[core_versions]: https://developer.android.google.cn/jetpack/androidx/releases/core
[collection_versions]: https://developer.android.google.cn/jetpack/androidx/releases/collection
[lifecycle_versions]: https://developer.android.google.cn/jetpack/androidx/releases/lifecycle

[CHANGELOG.md]: CHANGELOG.md

[Activityx.java]: tools4a-activity/src/main/java/com/github/panpf/tools4a/activity/Activityx.java
[ActivityxTest.java]: tools4a-activity/src/androidTest/java/com/github/panpf/tools4a/activity/test/ActivityxTest.java
[Activityx.kt]: tools4a-activity-ktx/src/main/java/com/github/panpf/tools4a/activity/ktx/Activityx.kt
[ActivityxTest.kt]: tools4a-activity-ktx/src/androidTest/java/com/github/panpf/tools4a/activity/ktx/test/ActivityxTest.kt

[Argsx.java]: tools4a-args/src/main/java/com/github/panpf/tools4a/args/Argsx.java
[ArgsxTest.java]: tools4a-args/src/androidTest/java/com/github/panpf/tools4a/args/test/ArgsxTest.java
[Argsx.kt]: tools4a-args-ktx/src/main/java/com/github/panpf/tools4a/args/ktx/Argsx.kt
[ArgsxTest.kt]: tools4a-args-ktx/src/androidTest/java/com/github/panpf/tools4a/args/ktx/test/ArgsxTest.kt

[ArgsBinder.kt]: tools4a-args-ktx/src/main/java/com/github/panpf/tools4a/args/ktx/ArgsBinder.kt
[ArgsBinderTest.kt]: tools4a-args-ktx/src/androidTest/java/com/github/panpf/tools4a/args/ktx/test/ArgsBinderTest.kt

[Buildx.java]: tools4a-build/src/main/java/com/github/panpf/tools4a/build/Buildx.java
[BuildxTest.java]: tools4a-build/src/androidTest/java/com/github/panpf/tools4a/build/test/BuildxTest.java

[Clipboardx.java]: tools4a-clipboard/src/main/java/com/github/panpf/tools4a/clipboard/Clipboardx.java
[ClipboardxTest.java]: tools4a-clipboard/src/androidTest/java/com/github/panpf/tools4a/clipboard/test/ClipboardxTest.java
[Clipboardx.kt]: tools4a-clipboard-ktx/src/main/java/com/github/panpf/tools4a/clipboard/ktx/Clipboardx.kt
[ClipboardxTest.kt]: tools4a-clipboard-ktx/src/androidTest/java/com/github/panpf/tools4a/clipboard/ktx/test/ClipboardxTest.kt

[Contextx.java]: tools4a-context/src/main/java/com/github/panpf/tools4a/context/Contextx.java
[ContextxTest.java]: tools4a-context/src/androidTest/java/com/github/panpf/tools4a/context/test/ContextxTest.java
[Contextx.kt]: tools4a-context-ktx/src/main/java/com/github/panpf/tools4a/context/ktx/Contextx.kt
[ContextxTest.kt]: tools4a-context-ktx/src/androidTest/java/com/github/panpf/tools4a/context/ktx/test/ContextxTest.kt

[Devicex.java]: tools4a-device/src/main/java/com/github/panpf/tools4a/device/Devicex.java
[DevicexTest.java]: tools4a-device/src/androidTest/java/com/github/panpf/tools4a/device/test/DevicexTest.java
[Devicex.kt]: tools4a-device-ktx/src/main/java/com/github/panpf/tools4a/device/ktx/Devicex.kt
[DevicexTest.kt]: tools4a-device-ktx/src/androidTest/java/com/github/panpf/tools4a/device/ktx/test/DevicexTest.kt

[Dialogx.java]: tools4a-dialog/src/main/java/com/github/panpf/tools4a/dialog/Dialogx.java
[DialogxTest.java]: tools4a-dialog/src/androidTest/java/com/github/panpf/tools4a/dialog/test/DialogxTest.java
[Dialogx.kt]: tools4a-dialog-ktx/src/main/java/com/github/panpf/tools4a/dialog/ktx/Dialogx.kt
[DialogxTest.kt]: tools4a-dialog-ktx/src/androidTest/java/com/github/panpf/tools4a/dialog/ktx/test/DialogxTest.kt

[Dimenx.java]: tools4a-dimen/src/main/java/com/github/panpf/tools4a/dimen/Dimenx.java
[DimenxTest.java]: tools4a-dimen/src/androidTest/java/com/github/panpf/tools4a/dimen/test/DimenxTest.java
[Dimenx.kt]: tools4a-dimen-ktx/src/main/java/com/github/panpf/tools4a/dimen/ktx/Dimenx.kt
[DimenxTest.kt]: tools4a-dimen-ktx/src/androidTest/java/com/github/panpf/tools4a/dimen/ktx/test/DimenxTest.kt

[Displayx.java]: tools4a-display/src/main/java/com/github/panpf/tools4a/display/Displayx.java
[DisplayxTest.java]: tools4a-display/src/androidTest/java/com/github/panpf/tools4a/display/test/DisplayxTest.java
[Displayx.kt]: tools4a-display-ktx/src/main/java/com/github/panpf/tools4a/display/ktx/Displayx.kt
[DisplayxTest.kt]: tools4a-display-ktx/src/androidTest/java/com/github/panpf/tools4a/display/ktx/test/DisplayxTest.kt

[FileProviderx.java]: tools4a-fileprovider/src/main/java/com/github/panpf/tools4a/fileprovider/FileProviderx.java
[FileProviderxTest.java]: tools4a-fileprovider/src/androidTest/java/com/github/panpf/tools4a/fileprovider/test/FileProviderxTest.java
[FileProviderx.kt]: tools4a-fileprovider-ktx/src/main/java/com/github/panpf/tools4a/fileprovider/ktx/FileProviderx.kt
[FileProviderxTest.kt]: tools4a-fileprovider-ktx/src/androidTest/java/com/github/panpf/tools4a/fileprovider/ktx/test/FileProviderxTest.kt

[Fragmentx.java]: tools4a-fragment/src/main/java/com/github/panpf/tools4a/fragment/Fragmentx.java
[FragmentxTest.java]: tools4a-fragment/src/androidTest/java/com/github/panpf/tools4a/fragment/test/FragmentxTest.java
[Fragmentx.kt]: tools4a-fragment-ktx/src/main/java/com/github/panpf/tools4a/fragment/ktx/Fragmentx.kt
[FragmentxTest.kt]: tools4a-fragment-ktx/src/androidTest/java/com/github/panpf/tools4a/fragment/ktx/test/FragmentxTest.kt

[Bitmapx.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Bitmapx.java
[BitmapxTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/BitmapxTest.java
[Bitmapx.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/Bitmapx.kt
[BitmapxTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/BitmapxTest.kt

[Colorx.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Colorx.java
[ColorxTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/ColorxTest.java
[Colorx.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/Colorx.kt
[ColorxTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/ColorxTest.kt

[Drawablex.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Drawablex.java
[DrawablexTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/DrawablexTest.java
[Drawablex.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/Drawablex.kt
[DrawablexTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/DrawablexTest.kt

[Imagex.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Imagex.java
[ImagexTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/ImagexTest.java
[Imagex.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/Imagex.kt
[ImagexTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/ImagexTest.kt

[Matrixx.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Matrixx.java
[MatrixxTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/MatrixxTest.java
[Matrixx.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/Matrixx.kt
[MatrixxTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/MatrixxTest.kt

[OpenGlx.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/OpenGlx.java
[OpenGlxTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/OpenGlxTest.java
[OpenGlx.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/OpenGlx.kt
[OpenGlxTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/OpenGlxTest.kt

[Paintx.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Paintx.java
[PaintxTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/PaintxTest.java
[Paintx.kt]: tools4a-graphics-ktx/src/main/java/com/github/panpf/tools4a/graphics/ktx/Paintx.kt
[PaintxTest.kt]: tools4a-graphics-ktx/src/androidTest/java/com/github/panpf/tools4a/graphics/ktx/test/PaintxTest.kt

[Resizex.java]: tools4a-graphics/src/main/java/com/github/panpf/tools4a/graphics/Resizex.java
[ResizexTest.java]: tools4a-graphics/src/androidTest/java/com/github/panpf/tools4a/graphics/test/ResizexTest.java

[InputMethodx.java]: tools4a-inputmethod/src/main/java/com/github/panpf/tools4a/inputmethod/InputMethodx.java
[InputMethodxTest.java]: tools4a-inputmethod/src/androidTest/java/com/github/panpf/tools4a/inputmethod/test/InputMethodxTest.java
[InputMethodx.kt]: tools4a-inputmethod-ktx/src/main/java/com/github/panpf/tools4a/inputmethod/ktx/InputMethodx.kt
[InputMethodxTest.kt]: tools4a-inputmethod-ktx/src/androidTest/java/com/github/panpf/tools4a/inputmethod/ktx/test/InputMethodxTest.kt

[Intentx.java]: tools4a-intent/src/main/java/com/github/panpf/tools4a/intent/Intentx.java
[IntentxTest.java]: tools4a-intent/src/androidTest/java/com/github/panpf/tools4a/intent/test/IntentxTest.java
[Intentx.kt]: tools4a-intent-ktx/src/main/java/com/github/panpf/tools4a/intent/ktx/Intentx.kt
[IntentxTest.kt]: tools4a-intent-ktx/src/androidTest/java/com/github/panpf/tools4a/intent/ktx/test/IntentxTest.kt

[NetworkState.java]: tools4a-network/src/main/java/com/github/panpf/tools4a/network/NetworkState.java
[NetworkStateTest.java]: tools4a-network/src/androidTest/java/com/github/panpf/tools4a/network/test/NetworkStateTest.java

[Networkx.java]: tools4a-network/src/main/java/com/github/panpf/tools4a/network/Networkx.java
[NetworkxTest.java]: tools4a-network/src/androidTest/java/com/github/panpf/tools4a/network/test/NetworkxTest.java
[Networkx.kt]: tools4a-network-ktx/src/main/java/com/github/panpf/tools4a/network/ktx/Networkx.kt
[NetworkxTest.kt]: tools4a-network-ktx/src/androidTest/java/com/github/panpf/tools4a/network/ktx/test/NetworkxTest.kt

[PackageInfox.java]: tools4a-packages/src/main/java/com/github/panpf/tools4a/packages/PackageInfox.java
[PackageInfoxTest.java]: tools4a-packages/src/androidTest/java/com/github/panpf/tools4a/packages/test/PackageInfoxTest.java
[PackageInfox.kt]: tools4a-packages-ktx/src/main/java/com/github/panpf/tools4a/packages/ktx/PackageInfox.kt
[PackageInfoxTest.kt]: tools4a-packages-ktx/src/androidTest/java/com/github/panpf/tools4a/packages/ktx/test/PackageInfoxTest.kt

[Packagex.java]: tools4a-packages/src/main/java/com/github/panpf/tools4a/packages/Packagex.java
[PackagexTest.java]: tools4a-packages/src/androidTest/java/com/github/panpf/tools4a/packages/test/PackagexTest.java
[Packagex.kt]: tools4a-packages-ktx/src/main/java/com/github/panpf/tools4a/packages/ktx/Packagex.kt
[PackagexTest.kt]: tools4a-packages-ktx/src/androidTest/java/com/github/panpf/tools4a/packages/ktx/test/PackagexTest.kt

[Permissionx.java]: tools4a-permission/src/main/java/com/github/panpf/tools4a/permission/Permissionx.java
[PermissionxTest.java]: tools4a-permission/src/androidTest/java/com/github/panpf/tools4a/permission/test/PermissionxTest.java
[Permissionx.kt]: tools4a-permission-ktx/src/main/java/com/github/panpf/tools4a/permission/ktx/Permissionx.kt
[PermissionxTest.kt]: tools4a-permission-ktx/src/androidTest/java/com/github/panpf/tools4a/permission/ktx/test/PermissionxTest.kt

[PrefsDelegateTest.kt]: tools4a-prefsdelegate/src/androidTest/java/com/github/panpf/tools4a/prefsdelegate/test/PrefsDelegateTest.kt
[BeanListNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/BeanListNullablePrefsDelegate.kt
[BeanListPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/BeanListPrefsDelegate.kt
[BeanNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/BeanNullablePrefsDelegate.kt
[BeanPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/BeanPrefsDelegate.kt
[BooleanNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/BooleanNullablePrefsDelegate.kt
[BooleanPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/BooleanPrefsDelegate.kt
[EnumNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/EnumNullablePrefsDelegate.kt
[EnumPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/EnumPrefsDelegate.kt
[FloatNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/FloatNullablePrefsDelegate.kt
[FloatPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/FloatPrefsDelegate.kt
[IntNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/IntNullablePrefsDelegate.kt
[IntPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/IntPrefsDelegate.kt
[LongNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/LongNullablePrefsDelegate.kt
[LongPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/LongPrefsDelegate.kt
[StringListNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/StringListNullablePrefsDelegate.kt
[StringListPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/StringListPrefsDelegate.kt
[StringNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/StringNullablePrefsDelegate.kt
[StringPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/StringPrefsDelegate.kt
[StringSetNullablePrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/StringSetNullablePrefsDelegate.kt
[StringSetPrefsDelegate.kt]: tools4a-prefsdelegate/src/main/java/com/github/panpf/tools4a/prefsdelegate/StringSetPrefsDelegate.kt

[Romx.java]: tools4a-rom/src/main/java/com/github/panpf/tools4a/rom/Romx.java
[RomxTest.java]: tools4a-rom/src/androidTest/java/com/github/panpf/tools4a/rom/test/RomxTest.java

[Rootx.java]: tools4a-root/src/main/java/com/github/panpf/tools4a/root/Rootx.java
[RootxTest.java]: tools4a-root/src/androidTest/java/com/github/panpf/tools4a/root/test/RootxTest.java

[Runx.java]: tools4a-run/src/main/java/com/github/panpf/tools4a/run/Runx.java
[RunxTest.java]: tools4a-run/src/androidTest/java/com/github/panpf/tools4a/run/test/RunxTest.java
[Runx.kt]: tools4a-run-ktx/src/main/java/com/github/panpf/tools4a/run/ktx/Runx.kt
[RunxTest.kt]: tools4a-run-ktx/src/androidTest/java/com/github/panpf/tools4a/run/ktx/test/RunxTest.kt

[Servicex.java]: tools4a-service/src/main/java/com/github/panpf/tools4a/service/Servicex.java
[ServicexTest.java]: tools4a-service/src/androidTest/java/com/github/panpf/tools4a/service/test/ServicexTest.java
[Servicex.kt]: tools4a-service-ktx/src/main/java/com/github/panpf/tools4a/service/ktx/Servicex.kt
[ServicexTest.kt]: tools4a-service-ktx/src/androidTest/java/com/github/panpf/tools4a/service/ktx/test/ServicexTest.kt

[Settingsx.java]: tools4a-settings/src/main/java/com/github/panpf/tools4a/settings/Settingsx.java
[SettingsxTest.java]: tools4a-settings/src/androidTest/java/com/github/panpf/tools4a/settings/test/SettingsxTest.java
[Settingsx.kt]: tools4a-settings-ktx/src/main/java/com/github/panpf/tools4a/settings/ktx/Settingsx.kt
[SettingsxTest.kt]: tools4a-settings-ktx/src/androidTest/java/com/github/panpf/tools4a/settings/ktx/test/SettingsxTest.kt

[Storagex.java]: tools4a-storage/src/main/java/com/github/panpf/tools4a/storage/Storagex.java
[StoragexTest.java]: tools4a-storage/src/androidTest/java/com/github/panpf/tools4a/storage/test/StoragexTest.java
[Storagex.kt]: tools4a-storage-ktx/src/main/java/com/github/panpf/tools4a/storage/ktx/Storagex.kt
[StoragexTest.kt]: tools4a-storage-ktx/src/androidTest/java/com/github/panpf/tools4a/storage/ktx/test/StoragexTest.kt

[StorageManagerCompat.java]: tools4a-storage/src/main/java/com/github/panpf/tools4a/storage/StorageManagerCompat.java
[StorageManagerCompatTest.java]: tools4a-storage/src/androidTest/java/com/github/panpf/tools4a/storage/test/StorageManagerCompatTest.java

[StorageVolumeCompat.java]: tools4a-storage/src/main/java/com/github/panpf/tools4a/storage/StorageVolumeCompat.java
[StorageVolumeCompatTest.java]: tools4a-storage/src/androidTest/java/com/github/panpf/tools4a/storage/test/StorageVolumeCompatTest.java

[StatFsx.java]: tools4a-storage/src/main/java/com/github/panpf/tools4a/storage/StatFsx.java
[StatFsxTest.java]: tools4a-storage/src/androidTest/java/com/github/panpf/tools4a/storage/test/StatFsxTest.java
[StatFsx.kt]: tools4a-storage-ktx/src/main/java/com/github/panpf/tools4a/storage/ktx/StatFsx.kt
[StatFsxTest.kt]: tools4a-storage-ktx/src/androidTest/java/com/github/panpf/tools4a/storage/ktx/test/StatFsxTest.kt

[SystemPropertiesx.java]: tools4a-systemproperties/src/main/java/com/github/panpf/tools4a/systemproperties/SystemPropertiesx.java
[SystemPropertiesxTest.java]: tools4a-systemproperties/src/androidTest/java/com/github/panpf/tools4a/systemproperties/test/SystemPropertiesxTest.java

[Toastx.java]: tools4a-toast/src/main/java/com/github/panpf/tools4a/toast/Toastx.java
[ToastxTest.java]: tools4a-toast/src/androidTest/java/com/github/panpf/tools4a/toast/test/ToastxTest.java
[Toastx.kt]: tools4a-toast-ktx/src/main/java/com/github/panpf/tools4a/toast/ktx/Toastx.kt
[ToastxTest.kt]: tools4a-toast-ktx/src/androidTest/java/com/github/panpf/tools4a/toast/ktx/test/ToastxTest.kt

[LifecycleBroadcastReceiver.java]: tools4a-utils/src/main/java/com/github/panpf/tools4a/utils/LifecycleBroadcastReceiver.java
[LifecycleBroadcastReceiverTest.java]: tools4a-utils/src/androidTest/java/com/github/panpf/tools4a/utils/test/LifecycleBroadcastReceiverTest.java

[Textx.java]: tools4a-utils/src/main/java/com/github/panpf/tools4a/utils/Textx.java
[TextxTest.java]: tools4a-utils/src/androidTest/java/com/github/panpf/tools4a/utils/test/TextxTest.java
[Textx.kt]: tools4a-utils-ktx/src/main/java/com/github/panpf/tools4a/utils/ktx/Textx.kt
[TextxTest.kt]: tools4a-utils-ktx/src/androidTest/java/com/github/panpf/tools4a/utils/ktx/test/TextxTest.kt

[WeakAsyncTask.java]: tools4a-utils/src/main/java/com/github/panpf/tools4a/utils/WeakAsyncTask.java
[WeakAsyncTaskTest.java]: tools4a-utils/src/androidTest/java/com/github/panpf/tools4a/utils/test/WeakAsyncTaskTest.java

[ViewAnimx.java]: tools4a-view/src/main/java/com/github/panpf/tools4a/view/ViewAnimx.java
[ViewAnimxTest.java]: tools4a-view/src/androidTest/java/com/github/panpf/tools4a/view/test/ViewAnimxTest.java
[ViewAnimx.kt]: tools4a-view-ktx/src/main/java/com/github/panpf/tools4a/view/ktx/ViewAnimx.kt
[ViewAnimxTest.kt]: tools4a-view-ktx/src/androidTest/java/com/github/panpf/tools4a/view/ktx/test/ViewAnimxTest.kt

[Viewx.java]: tools4a-view/src/main/java/com/github/panpf/tools4a/view/Viewx.java
[ViewxTest.java]: tools4a-view/src/androidTest/java/com/github/panpf/tools4a/view/test/ViewxTest.java
[Viewx.kt]: tools4a-view-ktx/src/main/java/com/github/panpf/tools4a/view/ktx/Viewx.kt
[ViewxTest.kt]: tools4a-view-ktx/src/androidTest/java/com/github/panpf/tools4a/view/ktx/test/ViewxTest.kt

[Windowx.java]: tools4a-view/src/main/java/com/github/panpf/tools4a/view/Windowx.java
[WindowxTest.java]: tools4a-view/src/androidTest/java/com/github/panpf/tools4a/view/test/WindowxTest.java
[Windowx.kt]: tools4a-view-ktx/src/main/java/com/github/panpf/tools4a/view/ktx/Windowx.kt
[WindowxTest.kt]: tools4a-view-ktx/src/androidTest/java/com/github/panpf/tools4a/view/ktx/test/WindowxTest.kt

[Preferencex.java]: other/src/main/java/com/github/panpf/tools4a/other/Preferencex.java
[PreferencexTest.java]: other/src/androidTest/java/com/github/panpf/tools4a/other/test/PreferencexTest.java
[Preferencex.kt]: other-ktx/src/main/java/com/github/panpf/tools4a/other/ktx/Preferencex.kt
[PreferencexTest.kt]: other-ktx/src/androidTest/java/com/github/panpf/tools4a/other/ktx/test/PreferencexTest.kt

[SingletonLazy.java]: other/src/main/java/com/github/panpf/tools4a/other/SingletonLazy.java