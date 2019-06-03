package hr.factory.base.app.router

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction.*

interface Router {
    fun showFragment(fragmentManager: FragmentManager?, bundle: Bundle, tag: String, container: Int = android.R.id.content)
    fun popBackStack(fragmentManager: FragmentManager?)
    fun popBackStackToFragment(fragmentManager: FragmentManager?, tag: String)
}

inline fun <reified T : Fragment> displayFragment(fragmentManager: FragmentManager?, bundle: Bundle, tag: String, container: Int = android.R.id.content) {
    fragmentManager?.let {

        val fragment = T::class.java.newInstance().apply {
            arguments = bundle
        }

        val transaction = it.beginTransaction()
            .replace(container, fragment, tag)
            .setTransition(TRANSIT_FRAGMENT_OPEN)

        if (container == android.R.id.content)
                transaction.addToBackStack(tag)

            transaction.commitAllowingStateLoss()
    }
}

fun popBackStackImpl(fragmentManager: FragmentManager?) {
    fragmentManager?.popBackStack()
}

fun popBackStackToFragmentImpl(fragmentManager: FragmentManager?, tag: String){
        fragmentManager?.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}
/**
 *
 * Napraviti sljedecu klasu u app modulu za navigaciju
 * i napraviti koin module takoder u app kojim ce kreirati
 * objekt te klase
 *
 */

/*class RouterImpl: Router {

    override fun showFragment(fragmentManager: FragmentManager, bundle: Bundle, tag: String) {
        when(tag) {
            FRAGMENT_SPLASH -> displayFragment<SplashFragment>(fragmentManager, bundle, tag)

            FRAGMENT_ONBOARDING -> displayFragment<OnboardingFragment>(fragmentManager, bundle, tag)

            FRAGMENT_LOGIN -> displayFragment<LoginFragment>(fragmentManager, bundle, tag)

            FRAGMENT_REGISTER -> displayFragment<RegisterFragment>(fragmentManager, bundle, tag)

            FRAGMENT_SELECT_SYMPTOMS -> displayFragment<SymptomsFragment>(fragmentManager, bundle, tag)

            FRAGMENT_FORGOT_PASSWORD -> displayFragment<ForgotPasswordFragment>(fragmentManager, bundle, tag)
        }
    }
}*/

/*val routerModule = module {
    single<Router>{ RouterImpl() }
}*/

/**
 *
 * primjer izdvojene finkcije za kreaciju fragmenta
 *
 */

inline fun <reified T : Fragment> newFragmentInstance(bundle: Bundle) =
    T::class.java.newInstance().apply {
        arguments = bundle
    }
