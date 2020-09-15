## new

Upgrade:
* upgrade: Upgrade androidx.core to 1.3.1
* upgrade: Upgrade target sdk to 30

Change:
* change: All methods in Runx are renamed as follows:
    * runOnUIThread rename to runOnUiThread
    * runOnUIThreadAndWait rename to runOnUiThreadAndWait
    * runOnUIThreadAndWaitResult rename to runOnUiThreadAndWaitResult
    * runOnUIThreadAndWaitNullableResult rename to runOnUiThreadAndWaitNullableResult
    * isOnTheMainThread rename to isOnMainThread
    * isOnTheMainProcess rename to isOnMainProcess
    * getTheProcessName rename to getProcessName
    * getTheProcessNameSuffix rename to getProcessNameSuffix
* change: Contextx.wifiRttManager() mtehod now returns nullable WifiRttManager
* change: Contextx.wifiRttManager() rename to wifiRttManagerOrNull()
* move: Textx from 'tools4a-utils' move to 'tools4a-other'
* change: Contextx.systemService\*InUI rename to Contextx.systemService\*OnUiThread

New:
* new: Devivex added isEmulator method

Improve:
* improve: Buildx adapts to android 11

Remove:
* remove: Remove 'tools4a-utils-ktx' module

## v1.0.0-beta02
upgrade: Kotlin upgraded to 1.4.0

## v1.0.0-beta01
Initial submission