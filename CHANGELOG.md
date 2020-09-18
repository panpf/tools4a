## new

Upgrade:
* upgrade: Upgrade androidx.core to 1.3.1
* upgrade: Upgrade target sdk to 30

Change:
* change: All methods in Runx are renamed
* change: Contextx.wifiRttManager() method now returns nullable WifiRttManager
* change: Contextx.wifiRttManager() rename to wifiRttManagerOrNull()
* move: Textx from 'tools4a-utils' move to 'tools4a-other'
* change: Contextx.systemService\*InUI rename to Contextx.systemService\*OnUiThread
* change: Contextx.isDestroyedCompat rename to isDestroyed

New:
* new: Devivex added isEmulator method
* new: Added 'tools4a-test' and 'tools4a-test-ktx' module
* new: Runx added isOnNotMainThread, checkMainThread, checkNotMainThread, isOnNotMainProcess method

Improve:
* improve: Buildx adapts to android 11

Remove:
* remove: Remove 'tools4a-utils-ktx' module

## v1.0.0-beta02
upgrade: Kotlin upgraded to 1.4.0

## v1.0.0-beta01
Initial submission