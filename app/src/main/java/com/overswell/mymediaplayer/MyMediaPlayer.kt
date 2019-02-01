package com.overswell.mymediaplayer

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyMediaPlayer : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
    }
}

val myModule = module {
    viewModel {
        MainViewModel(get())
    }
    single {
        ResourceArray(arrayOf(R.raw.kid))
    }
}