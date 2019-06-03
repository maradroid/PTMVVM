package hr.factory.app_home.common.rest_interface

import hr.factory.app_home.common.data_models.DatesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeRestInterface {

    @GET("/api/v1/meals/dates")
    fun getDates(): Observable<DatesResponse>
}