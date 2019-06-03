package hr.factory.app_home.view_model

import androidx.lifecycle.MutableLiveData
import hr.factory.app_home.common.interactor.HomeInteractor
import hr.factory.app_home.common.ui.HomeUI
import hr.factory.app_home.common.ui.provideHomeUI
import hr.factory.base.app.shared_repo.SharedPrefRepo
import hr.factory.base.app.view_model.BaseViewModel
import io.reactivex.Observable

class HomeVM(private val interactor: HomeInteractor, private val sharedPrefRepo: SharedPrefRepo): BaseViewModel() {

    val datesData = MutableLiveData<HomeUI>()

    fun getDates(): Observable<HomeUI> {
            return interactor.getDates()
                .map { provideHomeUI(it) }
                .doOnNext { datesData.value = it }
    }

    fun deleteByPosition(position: Int) {
        val temp = datesData.value
        temp?.dataList?.removeAt(position)
        datesData.value = temp
    }
}