package hr.factory.base.app.app_relay

import com.jakewharton.rxrelay2.PublishRelay

sealed class AppData
object Response401 : AppData()
object ResetMealsData : AppData()

class AppRelay {
    val relay = PublishRelay.create<AppData>()
}