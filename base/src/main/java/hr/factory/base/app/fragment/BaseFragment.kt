package hr.factory.base.app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding2.view.RxView
import hr.factory.base.R
import hr.factory.base.app.AppError
import hr.factory.base.app.ERROR_SHOW_500
import hr.factory.base.app.ERROR_SHOW_SNACK
import hr.factory.base.app.ErrorHandler
import hr.factory.base.app.view_model.BaseViewModel
import hr.factory.base.utils.showFakeSnackBar
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

abstract class BaseFragment<T : BaseViewModel>: Fragment() {

    private val errorHandler by inject<ErrorHandler> ()

    private var compositeDisposable = CompositeDisposable()

    var serverLayout: View? = null
    var retryButton: Button? = null
    var loaderLayout: FrameLayout? = null

    abstract val layoutRes: Int
    abstract val viewModel: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryButton = view.findViewById(R.id.btnRetry)
        loaderLayout = view.findViewById(R.id.loader)
        serverLayout = view.findViewById(R.id.serverProblemLayout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(disposable)
    }

    private fun displayError(error: AppError) {
        when(error.type) {
            ERROR_SHOW_SNACK -> showFakeSnackBar(error.message, context)
            ERROR_SHOW_500 -> serverLayout?.visibility = View.VISIBLE
        }
    }

    protected fun <T>Observable<T>.ptViewSubscribe() {
        val obs = this
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { it.printStackTrace() }

        addDisposable(obs.subscribe({},{displayError(errorHandler.handleError(it))}))
    }

    protected fun <T>Observable<T>.ptLongSubscribe() {
        val obs = this
            .doOnSubscribe { loaderLayout?.visibility = View.VISIBLE }
            .doOnNext { serverLayout?.visibility = View.GONE }
            .doOnError { loaderLayout?.visibility = View.GONE }
            .doOnComplete { loaderLayout?.visibility = View.GONE }

        retryButton?.let { btn ->
            obs.retryWhen {  RxView.clicks(btn) }
        }

        viewModel.addDisposable(obs.subscribe({},{displayError(errorHandler.handleError(it))}))
    }
}