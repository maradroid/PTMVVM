package hr.factory.app_home.common.di

import hr.factory.app_home.common.interactor.HomeInteractor
import hr.factory.app_home.common.rest_interface.HomeRestInterface
import hr.factory.app_home.home.HomeController
import hr.factory.app_home.view_model.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    factory { provideHomeRestInterface(get()) }
    factory { HomeInteractor(get()) }
    viewModel { HomeVM(get(), get()) }

    factory { (homeVM: HomeVM) -> HomeController(homeVM) }
}

fun provideHomeRestInterface(retrofit: Retrofit) = retrofit.create(HomeRestInterface::class.java)