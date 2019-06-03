package hr.factory.app_home.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.airbnb.epoxy.TypedEpoxyController
import hr.factory.app_home.R
import hr.factory.app_home.common.ui.HomeUI
import hr.factory.app_home.home.holder.calendarHolder
import hr.factory.app_home.view_model.HomeVM
import hr.factory.base.app.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment: BaseFragment<HomeVM>() {

    override val layoutRes = R.layout.fragment_home
    override val viewModel by viewModel<HomeVM>()

    private val controller by inject<HomeController>{ parametersOf(viewModel)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDates().ptLongSubscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        epoxy.setController(controller)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.datesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                controller.setData(it)
            }
        })
    }
}

class HomeController(val viewModel: HomeVM): TypedEpoxyController<HomeUI>() {

    override fun buildModels(data: HomeUI) {
        data.dataList.forEach { item ->
            calendarHolder {
                id(item.date.date)
                date(item.message)
                clickListener { model, parentView, clickedView, position ->
                    viewModel.deleteByPosition(position)
                    Toast.makeText(clickedView.context, "sudo izgubio mudo", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}