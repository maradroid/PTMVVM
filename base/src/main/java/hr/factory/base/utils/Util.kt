package hr.factory.base.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.Patterns
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import hr.factory.base.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.fragment.app.FragmentManager
import java.text.ParseException
import java.text.SimpleDateFormat


fun ptCheckMail(email: CharSequence) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun showFakeSnackBar(message: String, context: Context?, colorId: Int = R.color.snack_error) {
    if (context != null) {
        val layout = LayoutInflater.from(context).inflate(R.layout.layout_snack, null)
        val title = layout.findViewById<TextView>(R.id.tvSnackText)
        val root = layout.findViewById<LinearLayout>(R.id.llSnackContainer)
        root.setBackgroundColor(ContextCompat.getColor(context, colorId))
        title.text = message
        with(Toast(context)) {
            setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, 0)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}

fun ChipGroup.disableClickAfterSelection() {
    this.setOnCheckedChangeListener { group, checkedId ->
        for (i in 0 until group.childCount) {
            val chip = group.getChildAt(i)
            chip.isClickable = chip.id != group.checkedChipId
        }
    }
}

fun Chip.disableClickOnSingleCheck() {
    val selectedChips = mutableListOf<Chip>()
    val parent = this.parent as ChipGroup
    for (i in 0 until parent.childCount) {
        val chip = parent.getChildAt(i) as Chip
        if (chip.isChecked)
            selectedChips.add(chip)
    }
    if (selectedChips.size == 1) {
        selectedChips[0].isClickable = false
    } else {
        selectedChips.forEach { it.isClickable = true }
    }
    selectedChips.clear()
}

fun <T> Observable<T>.ptSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun FragmentManager.clearBackStack() {
    if (this.backStackEntryCount > 0) {
        val entry = this.getBackStackEntryAt(0)
        this.popBackStack(entry.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

fun Boolean.toInt(): Int = if (this) 1 else 0

fun String.parseDate(serverFormat: SimpleDateFormat, appFormat: SimpleDateFormat): String {
    var parsedString = ""

    try {
        val date = serverFormat.parse(this)
        parsedString = appFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return parsedString
}

// https://antonioleiva.com/kotlin-ongloballayoutlistener/
inline fun  View.ptAfterMeasured(crossinline f: View.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}


// https://antonioleiva.com/listeners-several-functions-kotlin/
inline fun ViewPropertyAnimator.ptOnAnimationEnd(crossinline continuation: (Animator) -> Unit) {
    this.setListener(object:Animator.AnimatorListener{
        override fun onAnimationRepeat(animation: Animator?) = Unit

        override fun onAnimationCancel(animation: Animator?) = Unit

        override fun onAnimationStart(animation: Animator?) = Unit

        override fun onAnimationEnd(animation: Animator) = continuation(animation)
    })
}