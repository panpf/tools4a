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

package com.github.panpf.tools4a.context.ktx

import android.accounts.AccountManager
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.CrossProfileApps
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.content.pm.ShortcutManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.fingerprint.FingerprintManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.IpSecManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.*
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import androidx.annotation.RequiresApi
import com.github.panpf.tools4a.context.Contextx


/**
 * Get Application Context
 */
inline fun Activity.appContext(): Context = Contextx.appContext(this)

///**
// * Get Context, Throw an exception if Fragment is dead
// */
//inline fun androidx.fragment.app.Fragment.requireContext(): Context = Contextx.requireContext(this)


/**
 * Get Application Context, Throw an exception if Fragment is dead
 */
inline fun androidx.fragment.app.Fragment.requireAppContext(): Context = Contextx.requireAppContext(this)


/**
 * Get Application Context
 */
inline fun View.appContext(): Context = Contextx.appContext(this)


/* ******************************************* SystemService *******************************************/


inline fun <T> Context.systemService(serviceName: String): T = Contextx.systemService(this, serviceName)

inline fun <T> Context.systemServiceOrNull(serviceName: String): T? = Contextx.systemServiceOrNull(this, serviceName)

inline fun <T> Context.systemServiceOnUiThread(serviceName: String): T = Contextx.systemServiceOnUiThread(this, serviceName)

inline fun <T> Context.systemServiceOrNullOnUiThread(serviceName: String): T? = Contextx.systemServiceOrNullOnUiThread(this, serviceName)

inline fun Context.packageManager(): PackageManager = Contextx.packageManager(this)

inline fun Context.powerManager(): PowerManager = Contextx.powerManager(this)

inline fun Context.windowManager(): WindowManager = Contextx.windowManager(this)

inline fun Context.layoutInflater(): LayoutInflater = Contextx.layoutInflater(this)

inline fun Context.accountManager(): AccountManager = Contextx.accountManager(this)

inline fun Context.activityManager(): ActivityManager = Contextx.activityManager(this)

inline fun Context.alarmManager(): AlarmManager = Contextx.alarmManager(this)

inline fun Context.notificationManager(): NotificationManager = Contextx.notificationManager(this)

inline fun Context.accessibilityManager(): AccessibilityManager = Contextx.accessibilityManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
inline fun Context.captioningManager(): CaptioningManager = Contextx.captioningManager(this)

inline fun Context.keyguardManager(): KeyguardManager = Contextx.keyguardManager(this)

inline fun Context.locationManager(): LocationManager = Contextx.locationManager(this)

inline fun Context.searchManager(): SearchManager = Contextx.searchManager(this)

inline fun Context.sensorManager(): SensorManager = Contextx.sensorManager(this)

inline fun Context.storageManager(): StorageManager = Contextx.storageManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
inline fun Context.storageStatsManager(): StorageStatsManager = Contextx.storageStatsManager(this)

inline fun Context.wallpaperManager(): WallpaperManager = Contextx.wallpaperManager(this)

// TIME_ZONE_RULES_MANAGER_SERVICE

inline fun Context.vibrator(): Vibrator = Contextx.vibrator(this)

inline fun Context.connectivityManager(): ConnectivityManager = Contextx.connectivityManager(this)

@RequiresApi(api = Build.VERSION_CODES.P)
inline fun Context.ipSecManager(): IpSecManager = Contextx.ipSecManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
inline fun Context.networkStatsManager(): NetworkStatsManager = Contextx.networkStatsManager(this)

inline fun Context.wifiManager(): WifiManager = Contextx.wifiManager(this)

inline fun Context.wifiManagerOrNull(): WifiManager? = Contextx.wifiManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.O)
inline fun Context.wifiAwareManager(): WifiAwareManager = Contextx.wifiAwareManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
inline fun Context.wifiAwareManagerOrNull(): WifiAwareManager? = Contextx.wifiAwareManagerOrNull(this)

inline fun Context.wifiP2pManager(): WifiP2pManager = Contextx.wifiP2pManager(this)

// WIFI_SCANNING_SERVICE

@RequiresApi(api = Build.VERSION_CODES.P)
inline fun Context.wifiRttManagerOrNull(): WifiRttManager? = Contextx.wifiRttManagerOrNull(this)

inline fun Context.nsdManager(): NsdManager = Contextx.nsdManager(this)

inline fun Context.audioManager(): AudioManager = Contextx.audioManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
inline fun Context.fingerprintManager(): FingerprintManager = Contextx.fingerprintManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
inline fun Context.fingerprintManagerOrNull(): FingerprintManager? = Contextx.fingerprintManagerOrNull(this)

inline fun Context.mediaRouter(): MediaRouter = Contextx.mediaRouter(this)

inline fun Context.telephonyManager(): TelephonyManager = Contextx.telephonyManager(this)

inline fun Context.telephonyManagerOrNull(): TelephonyManager? = Contextx.telephonyManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
inline fun Context.telephonySubscriptionManager(): SubscriptionManager = Contextx.telephonySubscriptionManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
inline fun Context.carrierConfigManager(): CarrierConfigManager = Contextx.carrierConfigManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.telecomManager(): TelecomManager = Contextx.telecomManager(this)

inline fun Context.clipboardManager(): ClipboardManager = Contextx.clipboardManager(this)

inline fun Context.inputMethodManager(): InputMethodManager = Contextx.inputMethodManager(this)

inline fun Context.textServicesManager(): TextServicesManager = Contextx.textServicesManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
inline fun Context.textClassificationManager(): TextClassificationManager = Contextx.textClassificationManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.appWidgetManager(): AppWidgetManager = Contextx.appWidgetManager(this)

inline fun Context.dropBoxManager(): DropBoxManager = Contextx.dropBoxManager(this)

inline fun Context.devicePolicyManager(): DevicePolicyManager = Contextx.devicePolicyManager(this)

inline fun Context.uiModeManager(): UiModeManager = Contextx.uiModeManager(this)

inline fun Context.downloadManager(): DownloadManager = Contextx.downloadManager(this)

inline fun Context.nfcManager(): NfcManager = Contextx.nfcManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
inline fun Context.bluetoothManager(): BluetoothManager = Contextx.bluetoothManager(this)

inline fun Context.usbManager(): UsbManager = Contextx.usbManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.launcherApps(): LauncherApps = Contextx.launcherApps(this)

inline fun Context.inputManager(): InputManager = Contextx.inputManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
inline fun Context.displayManager(): DisplayManager = Contextx.displayManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
inline fun Context.userManager(): UserManager = Contextx.userManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.restrictionsManager(): RestrictionsManager = Contextx.restrictionsManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
inline fun Context.appOpsManager(): AppOpsManager = Contextx.appOpsManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.cameraManager(): CameraManager = Contextx.cameraManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
inline fun Context.printManager(): PrintManager = Contextx.printManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
inline fun Context.consumerIrManager(): ConsumerIrManager = Contextx.consumerIrManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.tvInputManager(): TvInputManager = Contextx.tvInputManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.tvInputManagerOrNull(): TvInputManager? = Contextx.tvInputManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
inline fun Context.usageStatsManager(): UsageStatsManager = Contextx.usageStatsManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.mediaSessionManager(): MediaSessionManager = Contextx.mediaSessionManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.batteryManager(): BatteryManager = Contextx.batteryManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.jobScheduler(): JobScheduler = Contextx.jobScheduler(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
inline fun Context.mediaProjectionManager(): MediaProjectionManager = Contextx.mediaProjectionManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
inline fun Context.midiManager(): MidiManager = Contextx.midiManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
inline fun Context.midiManagerOrNull(): MidiManager? = Contextx.midiManagerOrNull(this)

// RADIO_SERVICE

@RequiresApi(api = Build.VERSION_CODES.N)
inline fun Context.hardwarePropertiesManager(): HardwarePropertiesManager = Contextx.hardwarePropertiesManager(this)

@RequiresApi(api = Build.VERSION_CODES.N_MR1)
inline fun Context.shortcutManager(): ShortcutManager = Contextx.shortcutManager(this)

@RequiresApi(api = Build.VERSION_CODES.N)
inline fun Context.systemHealthManager(): SystemHealthManager = Contextx.systemHealthManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
inline fun Context.companionDeviceManager(): CompanionDeviceManager = Contextx.companionDeviceManager(this)

@RequiresApi(api = Build.VERSION_CODES.P)
inline fun Context.crossProfileApps(): CrossProfileApps = Contextx.crossProfileApps(this)