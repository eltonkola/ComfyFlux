package com.eltonkola.comfyflux.app

import android.app.Application
import com.eltonkola.comfyflux.app.data.AppSettings
import com.eltonkola.comfyflux.app.netwrok.GroqAPI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class ConfyFluxApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@ConfyFluxApp)
            // Load modules
            modules(appModule)
        }
    }
}

private val appModule = module {

    singleOf(::AppSettings)
    singleOf(::GroqAPI)


//    singleOf(::SimpleServiceImpl){ bind<SimpleService>() }
//
//    factoryOf(::FactoryPresenter)
//
//    viewModelOf(::SimpleViewModel)
//
//    scope<MyActivity>(){
//        scopedOf(::Session)
//    }
//
//    workerOf(::SimpleWorker)
}