package hr.factory.base

import android.app.Application
import org.koin.core.context.startKoin

open class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}