package hr.factory.base.utils

import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import hr.factory.base.app.DEBOUNCE_TIME_MILISEC
import hr.factory.base.app.PASSWORD_LENTH_MIN
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

data class FormCell(var input: String = "", var error: String = "")

fun ptEditTextObservable(editText: EditText): Observable<CharSequence> {
    return RxTextView.textChanges(editText).skipInitialValue().debounce(DEBOUNCE_TIME_MILISEC, TimeUnit.MILLISECONDS)
}

fun ptCheckBoxObservable(checkBox: CheckBox): Observable<Boolean>{
    return RxCompoundButton.checkedChanges(checkBox)
}

fun ptPopulateViews(editText: EditText, textInputLayout: TextInputLayout, data: FormCell) {
    if (editText.text.toString() != data.input)
        editText.setText(data.input)

    if (data.error.isNotEmpty()) {
        textInputLayout.error = data.error
        //textInputLayout.isErrorEnabled = true
    } else {
        textInputLayout.error = null
        //textInputLayout.isErrorEnabled = false
    }
}

fun ptVerifyMail(text: CharSequence, data: FormCell, errorMessage: String) {
    if (!ptCheckMail(text)) {
        data.error = errorMessage
    } else {
        data.error = ""
    }
    data.input = text.toString()
}

fun ptVerifyPassword(text: CharSequence, data: FormCell, errorMessage: String) {
    if (text.length >= PASSWORD_LENTH_MIN) {
        data.error = ""
    } else {
        data.error = errorMessage
    }
    data.input = text.toString()
}

fun ptVerifyPasswordConf(text: CharSequence, passConf: FormCell, pass: FormCell, errorShort: String, errorNotMatching: String) {
    if (text.length >= PASSWORD_LENTH_MIN) {
        passConf.error = ""
    } else {
        passConf.error = errorShort
    }
    passConf.input = text.toString()

    if (passConf.error.isNotEmpty())
        return

    if (pass.input == passConf.input) {
        passConf.error = ""
    } else {
        passConf.error = errorNotMatching
    }
}

fun ptVerifyEmpty(text: CharSequence, data: FormCell, errorMessage: String) {
    if (text.isNotEmpty()) {
        data.error = ""
    } else {
        data.error = errorMessage
    }
    data.input = text.toString()
}

/**
 * Final check(button enabled or disabled)
 * */

fun ptVerifyEmptyOrError(data: List<FormCell>): Boolean =
    data.none {
        it.error.isNotEmpty() || it.input.isEmpty()
    }

fun ptVerifyChecboxes(data: List<Boolean>): Boolean =
    data.all {
            it
    }