package hr.factory.base.app.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel: ViewModel() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    internal fun addDisposable(disposable: Disposable) {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(disposable)
    }

    protected fun <T>Observable<T>.ptSubscribe() {
        addDisposable(this.subscribe({},{it.printStackTrace()}))
    }
}