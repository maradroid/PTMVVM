package hr.factory.ptmvvm.router

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import hr.factory.app_common.constants.FRAGMENT_HOME
import hr.factory.app_home.home.HomeFragment
import hr.factory.base.app.router.Router
import hr.factory.base.app.router.displayFragment
import hr.factory.base.app.router.popBackStackImpl
import hr.factory.base.app.router.popBackStackToFragmentImpl

class RouterImpl: Router {
    override fun showFragment(fragmentManager: FragmentManager?, bundle: Bundle, tag: String, container: Int) {
        when(tag) {
            FRAGMENT_HOME -> displayFragment<HomeFragment>(fragmentManager, bundle, tag )
        }
    }

    override fun popBackStack(fragmentManager: FragmentManager?) {
        popBackStackImpl(fragmentManager)
    }

    override fun popBackStackToFragment(fragmentManager: FragmentManager?, tag: String) {
        popBackStackToFragmentImpl(fragmentManager, tag)
    }
}