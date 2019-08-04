package com.mehmettas.cent.di

import com.mehmettas.cent.ui.currencybase.CurrencyBaseViewModel
import com.mehmettas.cent.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CurrencyBaseViewModel(get()) }
}