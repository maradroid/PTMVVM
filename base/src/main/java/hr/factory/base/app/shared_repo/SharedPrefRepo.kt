package hr.factory.base.app.shared_repo

import android.preference.PreferenceManager
import hr.factory.base.BaseApp

private const val TAG_ONBOARDING = "TAG_ONBOARDING"
private const val TAG_TOKEN = "TAG_TOKEN"

interface SharedPrefRepo {
    fun onboardingDone(): Boolean
    fun setOnboardingDone(onboarding: Boolean)
    fun authToken(): String?
    fun setAuthToken(token: String?)
}

internal class SharedPrefRepoImpl(app: BaseApp): SharedPrefRepo {
    private val defaultSharedPref = PreferenceManager.getDefaultSharedPreferences(app)

    override fun onboardingDone(): Boolean = defaultSharedPref.getBoolean(TAG_ONBOARDING, false)

    override fun setOnboardingDone(onboarding: Boolean) = defaultSharedPref.edit().putBoolean(TAG_ONBOARDING, onboarding).apply()

    override fun authToken(): String? = defaultSharedPref.getString(TAG_TOKEN, null)

    override fun setAuthToken(token: String?) = defaultSharedPref.edit().putString(TAG_TOKEN, token).apply()
}