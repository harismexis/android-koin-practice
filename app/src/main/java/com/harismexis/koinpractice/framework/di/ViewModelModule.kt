package com.harismexis.koinpractice.framework.di

import com.harismexis.koinpractice.presentation.screens.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(),get())
    }
}