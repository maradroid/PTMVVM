package hr.factory.base.utils

import android.view.View
import androidx.viewpager.widget.ViewPager
import hr.factory.base.R

class PageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, pos: Float) {
        var position: Float
        // pojma nemam kak ovo radi, samo sam kopirao s neta
        position = if (pos < -1) -1F else pos
        position = if (position > 1) 1F else position

        val tempScale = if (position < 0) 1 + position else 1 - position

        val slope = (MAX_SCALE - MIN_SCALE) / 1
        val scaleValue = MIN_SCALE + tempScale * slope
        //page.setScaleX(scaleValue);
        page.scaleY = scaleValue
    }

    companion object {

        val MAX_SCALE = 1.0f
        val MIN_SCALE = 0.8f
    }
}

fun ViewPager.pageTransformation(
    pageMargin: Int = resources.getDimensionPixelSize(R.dimen.page_transformer_margin),
    paddingLeft: Int = resources.getDimensionPixelSize(R.dimen.page_transformer_padding_left),
    paddingTop: Int = resources.getDimensionPixelSize(R.dimen.page_transformer_padding_top),
    paddingRight: Int = resources.getDimensionPixelSize(R.dimen.page_transformer_padding_rignt),
    paddingBottom: Int = resources.getDimensionPixelSize(R.dimen.page_transformer_padding_bottom)) {

    this.pageMargin = pageMargin
    this.clipToPadding = false
    this.setPageTransformer(true, PageTransformer())
    this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
}


