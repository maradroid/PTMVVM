package hr.factory.app_home.common.interactor

import com.google.gson.GsonBuilder
import hr.factory.app_home.common.data_models.DatesResponse
import hr.factory.app_home.common.rest_interface.HomeRestInterface
import hr.factory.base.utils.ptSchedulers
import io.reactivex.Observable

const val dummyResponse = "{\"http_code\":200,\"message\":\"Success\",\"method\":\"GET\",\"base_url\":\"http:\\/\\/tummi.devtvornica.org\\/\",\"uri\":\"api\\/v1\\/meals\\/dates\",\"query_parameters\":[],\"errors\":null,\"meta\":{\"currentPage\":1,\"totalItems\":26,\"itemsPerPage\":14,\"totalPages\":2},\"data\":[{\"date\":\"2019-06-03\",\"flag\":\"no-data\"},{\"date\":\"2019-06-02\",\"flag\":\"no-data\"},{\"date\":\"2019-06-01\",\"flag\":\"no-data\"},{\"date\":\"2019-05-31\",\"flag\":\"meals-without-correlation\"},{\"date\":\"2019-05-30\",\"flag\":\"no-data\"},{\"date\":\"2019-05-29\",\"flag\":\"meals-without-correlation\"},{\"date\":\"2019-05-28\",\"flag\":\"no-data\"},{\"date\":\"2019-05-27\",\"flag\":\"no-data\"},{\"date\":\"2019-05-26\",\"flag\":\"no-data\"},{\"date\":\"2019-05-25\",\"flag\":\"no-data\"},{\"date\":\"2019-05-24\",\"flag\":\"no-data\"},{\"date\":\"2019-05-23\",\"flag\":\"no-data\"},{\"date\":\"2019-05-22\",\"flag\":\"no-data\"},{\"date\":\"2019-05-21\",\"flag\":\"no-data\"}],\"links\":{\"prev\":null,\"next\":\"http:\\/\\/tummi.devtvornica.org\\/api\\/v1\\/meals\\/dates?page=2\",\"self\":\"http:\\/\\/tummi.devtvornica.org\\/api\\/v1\\/meals\\/dates\"}}"

class HomeInteractor(private val restInterface: HomeRestInterface) {
    val gson = GsonBuilder().create()

    //fun getDates() = restInterface.getDates().ptSchedulers()
    fun getDates() = Observable.just(gson.fromJson(dummyResponse, DatesResponse::class.java)).ptSchedulers()
}