package hr.factory.base.app.resource_repo

import android.content.res.Resources

interface ResourceRepo {
    fun getString(stringId: Int): String
}

internal class ResourceRepoImpl(val resources: Resources): ResourceRepo {
    override fun getString(stringId: Int): String = resources.getString(stringId)

}