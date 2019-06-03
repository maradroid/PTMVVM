package hr.factory.ptmvvm

import hr.factory.base.BaseApp
import hr.factory.base.dependency_injection.appModule
import hr.factory.base.dependency_injection.serviceModule
import hr.factory.ptmvvm.router.routerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: BaseApp() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                routerModule,
                appModule,
                serviceModule)
        }
    }
}