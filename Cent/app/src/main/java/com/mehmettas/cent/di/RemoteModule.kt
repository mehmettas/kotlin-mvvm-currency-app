package com.mehmettas.cent.di

import com.mehmettas.cent.data.remote.service.ICurrencyModelService
import com.mehmettas.cent.data.remote.service.ServiceClient.createWebService
import org.koin.dsl.module.module

val remoteModule = module {
  factory { createWebService<ICurrencyModelService>() }
}