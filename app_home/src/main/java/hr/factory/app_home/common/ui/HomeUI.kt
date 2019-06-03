package hr.factory.app_home.common.ui

import hr.factory.app_home.common.data_models.Date
import hr.factory.app_home.common.data_models.DatesResponse

data class HomeUI(val dataList: MutableList<HomeCell>)
data class HomeCell(val date: Date, val message: String)

fun provideHomeUI(response: DatesResponse)  = HomeUI(response.data.map { HomeCell(it, "${it.flag} ${it.date}") }.toMutableList())


