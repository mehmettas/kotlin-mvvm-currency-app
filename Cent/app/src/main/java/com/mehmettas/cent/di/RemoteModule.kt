package com.mehmettas.cent.di

import com.mehmettas.cent.data.remote.service.ICurrencyModelService
import com.mehmettas.cent.data.remote.service.ICurrencyTimePeriodService
import com.mehmettas.cent.data.remote.service.IRatesService
import com.mehmettas.cent.data.remote.service.ServiceClient.createWebService
import org.koin.dsl.module.module

val remoteModule = module {
  factory { createWebService<ICurrencyModelService>() }
  factory { createWebService<IRatesService>() }
  factory { createWebService<ICurrencyTimePeriodService>() }

}