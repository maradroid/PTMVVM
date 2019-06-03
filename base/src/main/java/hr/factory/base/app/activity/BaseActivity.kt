package hr.factory.base.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.factory.base.app.app_relay.AppData
import hr.factory.base.app.app_relay.AppRelay
import hr.factory.base.app.app_relay.Response401
import hr.factory.base.app.router.Router
import hr.factory.base.utils.clearBackStack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.android.ext.android.inject

abstract class BaseActivity: AppCompatActivity() {

    abstract val openOn401: String?
    abstract fun openFirstFragment(router: Router)

    val router by inject<Router>()
    private val appRelay by inject<AppRelay>()

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFirstFragment(router)

        disposable = appRelay.relay.subscribeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onNext = {
                if (it is Response401)
                    actOn401(it)
            },
            onError = { it.printStackTrace() }
        )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            super.onBackPressed()
        else
            finish()
    }

    open fun actOn401(appData: AppData) {
        if (appData is Response401 && openOn401 != null) {
            supportFragmentManager.clearBackStack()
            router.showFragment(supportFragmentManager, Bundle(), openOn401!!)
        }
    }
}