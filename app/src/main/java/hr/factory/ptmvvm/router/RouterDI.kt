package hr.factory.ptmvvm.router

import hr.factory.base.app.router.Router
import org.koin.dsl.module

val routerModule = module {
    single<Router>{ RouterImpl() }
}