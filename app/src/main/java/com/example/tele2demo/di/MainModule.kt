package com.example.tele2demo.di

import com.example.tele2demo.domain.model.UserData
import com.example.tele2demo.presentaion.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { UserData() }

    viewModel { LoginViewModel(localRepository = get(), userData = get()) }
}