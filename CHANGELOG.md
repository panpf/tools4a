## new
* change: Refactor the names of all methods in Packagex
* change: Fragmentx added isUserVisibleHintWithParent, isResumedWithParent, findUserVisibleFragmentRecursivelyByUserVisibleHint, findUserVisibleFragmentRecursivelyByResumed method
* change: Fragmentx's findUserVisibleFragmentByUserVisibleHint, findUserVisibleFragmentByResumed methods no longer traverse grandchildren Fragment
* new: Added FragmentInsider class, which contains getMaxState, getMaxStateWithParent methods
* fix: Fix the bug of extra parameters of KClass<T>.launchFragment(KClass<T>) function of Testx.kt 

## v1.0.0-rc01

Upgrade:
* upgrade: Upgrade androidx.core to 1.3.1
* upgrade: Upgrade target sdk to 30
* upgrade: Upgrade kotlin to 1.4.10

Change:
* change: All methods in Runx are renamed
* change: Contextx.wifiRttManager() method now returns nullable WifiRttManager
* change: Contextx.wifiRttManager() rename to wifiRttManagerOrNull()
* move: Textx from 'tools4a-utils' move to 'tools4a-other'
* change: Contextx.systemService\*InUI rename to Contextx.systemService\*OnUiThread
* change: Contextx.isDestroyedCompat rename to isDestroyed
* change: 'tools4a-dimen' completely refactored
* change: 'tools4a-network' completely refactored
* change: Mark the Fragmentx findUserVisibleChildFragment method as deprecated
* change: Fragmentx findUserVisibleChildFragment method rename to findUserVisibleChildFragmentByUserVisibleHint

New:
* new: Devicex added isEmulator method
* new: Added 'tools4a-test' and 'tools4a-test-ktx' module
* new: Runx added isOnNotMainThread, checkMainThread, checkNotMainThread, isOnNotMainProcess method
* new: Fragmentx added findUserVisibleChildFragmentByResumed method

Improve:
* improve: Buildx adapts to android 11

Remove:
* remove: Remove 'tools4a-utils-ktx' module
* remove: Activityx remove start, startByClass method

Fix:
* bug: Fix the bug that the Contextx wifiManagerOrNull() method did not convert the incoming Context to ApplicationContext

## v1.0.0-beta02
upgrade: Kotlin upgraded to 1.4.0

## v1.0.0-beta01
Initial submission