package hr.factory.app_home.home.holder

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.deliverytest.base.epoxy.KotlinHolder
import hr.factory.app_home.R
import hr.factory.app_home.R2

@EpoxyModelClass(layout = R2.layout.cell_item)
abstract class CalendarHolderModel: EpoxyModelWithHolder<CalendarHolder>() {
    @EpoxyAttribute lateinit var date: String
    @EpoxyAttribute(hash=false) lateinit var clickListener: View.OnClickListener

    override fun bind(holder: CalendarHolder) {
        holder.tvDate.text = date
        holder.tvDate.setOnClickListener(clickListener)
    }
}

class CalendarHolder: KotlinHolder() {
    val tvDate by bind<TextView>(R.id.tvDate)
}