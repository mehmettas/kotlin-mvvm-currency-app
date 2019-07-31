package com.mehmettas.cent.di

import com.mehmettas.cent.data.remote.RemoteDataManager
import com.mehmettas.cent.data.repository.DataManager
import org.koin.dsl.module.module

val managerModule = module {
    single { DataManager(get()) }
    single { RemoteDataManager(get()) }
}