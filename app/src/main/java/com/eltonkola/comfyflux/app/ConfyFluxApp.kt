package com.eltonkola.comfyflux.app

import android.app.Application
import android.content.Context
import com.eltonkola.comfyflux.app.data.AppSettings
import com.eltonkola.comfyflux.app.data.PromptRepo
import com.eltonkola.comfyflux.app.netwrok.FluxAPI
import com.eltonkola.comfyflux.app.netwrok.GroqAPI
import com.eltonkola.comfyflux.app.usecase.HistoryUseCase
import com.eltonkola.comfyflux.app.usecase.ImageViewerUseCase
import com.eltonkola.comfyflux.app.usecase.MainUseCase
import com.eltonkola.comfyflux.app.usecase.PromptSearchUseCase
import com.eltonkola.comfyflux.app.usecase.QueueUseCase
import com.eltonkola.comfyflux.app.usecase.TimerUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
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

    singleOf(::GroqAPI)
    singleOf(::PromptRepo)
    singleOf(::FluxAPI)
    singleOf(::PromptSearchUseCase)
    singleOf(::HistoryUseCase)
    singleOf(::ImageViewerUseCase)
    singleOf(::QueueUseCase)

    singleOf(::MainUseCase)

    viewModelOf(::MainViewModel)
    singleOf(::TimerUseCase)

    single {
        androidContext().getSharedPreferences("AppSettingsPrefs", Context.MODE_PRIVATE)
    }

    singleOf(::AppSettings)


//          AppSettings(get())
//      }  factoryOf(::FactoryPresenter)
//
//    viewModelOf(::SimpleViewModel)
//
//    scope<MyActivity>(){
//        scopedOf(::Session)
//    }
//
//    workerOf(::SimpleWorker)
}