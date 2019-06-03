package hr.factory.app_common.router_bundles

import android.os.Bundle

const val EXTRA_SYMPTOMS_EDIT = "EXTRA_SYMPTOMS_EDIT"

fun provideSymptomsBundle(edit: Boolean): Bundle {
    val bundle = Bundle()
    bundle.putBoolean(EXTRA_SYMPTOMS_EDIT, edit)
    return bundle
}