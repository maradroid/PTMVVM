package hr.factory.ptmvvm

import android.os.Bundle
import hr.factory.app_common.constants.FRAGMENT_HOME
import hr.factory.base.app.activity.BaseActivity
import hr.factory.base.app.router.Router

class MainActivity : BaseActivity() {
    override val openOn401 = null

    override fun openFirstFragment(router: Router) {
        router.showFragment(supportFragmentManager, Bundle.EMPTY, FRAGMENT_HOME)
    }
}
