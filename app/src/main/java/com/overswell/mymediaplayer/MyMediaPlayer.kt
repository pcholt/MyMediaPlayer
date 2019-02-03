package com.overswell.mymediaplayer

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyMediaPlayer : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(
                viewModelsModule,
                resourceModule
            )
        )
    }
}

val viewModelsModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val resourceModule = module {
    single {
        ResourceArray(
            arrayOf(
                ResourceEntry(R.raw.kid, "start"),
                ResourceEntry(R.raw.fart1, "fart1"),
                ResourceEntry(R.raw.fart2, "fart2"),
                ResourceEntry(R.raw.fart3, "fart3")
            )
        )
    }
}
