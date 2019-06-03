package hr.factory.base.dependency_injection

import com.google.gson.GsonBuilder
import hr.factory.base.BaseApp
import hr.factory.base.BuildConfig
import hr.factory.base.app.ErrorHandler
import hr.factory.base.app.app_relay.AppRelay
import hr.factory.base.app.app_relay.Response401
import hr.factory.base.app.resource_repo.ResourceRepo
import hr.factory.base.app.resource_repo.ResourceRepoImpl
import hr.factory.base.app.shared_repo.SharedPrefRepo
import hr.factory.base.app.shared_repo.SharedPrefRepoImpl
import hr.factory.base.app.view_model.DummyVM
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 30000 // miliseconds
private const val BEARER = "Bearer "
private const val AUTHORIZATION = "Authorization"
private const val ACCEPT = "Accept"
private const val JSON = "application/json"
private const val BASE_URL = "http://tummi.devtvornica.org/"

val appModule = module {
    single<SharedPrefRepo> { SharedPrefRepoImpl(androidContext() as BaseApp) }
    single { AppRelay() }
    single<ResourceRepo> { ResourceRepoImpl((androidContext() as BaseApp).resources) }
    single { ErrorHandler(GsonBuilder().create()) }
    viewModel { DummyVM() }
}

val serviceModule = module {
    single { provideRetrofit(get(), get()) }
}

private fun provideRetrofit(sharedPrefRepo: SharedPrefRepo, appRelay: AppRelay): Retrofit {
    val httpClientBuilder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addNetworkInterceptor(logging)
    }

    httpClientBuilder.addInterceptor(provideAuthInterceptor(sharedPrefRepo))
    httpClientBuilder.authenticator(TokenAuthenticator(appRelay))

    httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
    httpClientBuilder.readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
    httpClientBuilder.writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun provideAuthInterceptor(sharedPrefRepo: SharedPrefRepo): Interceptor {
    return Interceptor {
        val original = it.request()
        val credentials = BEARER + sharedPrefRepo.authToken()

        val requestBuilder = original.newBuilder()
            .addHeader(AUTHORIZATION, credentials)
            .addHeader(ACCEPT, JSON)
            .method(original.method(), original.body())
        val request = requestBuilder.build()
        it.proceed(request)
    }
}

class TokenAuthenticator(private val appRelay: AppRelay): Authenticator {

    override fun authenticate(route: Route, response: Response): Request? {
        if (!response.request().url().url().toString().contains("auth/login"))
            appRelay.relay.accept(Response401)
        return null
    }
}
