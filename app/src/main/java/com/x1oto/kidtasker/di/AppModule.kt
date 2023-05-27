package com.x1oto.kidtasker.di

import com.x1oto.kidtasker.data.AuthRepositoryImpl
import com.x1oto.kidtasker.data.FirebaseDataSource
import com.x1oto.kidtasker.data.TaskRepositoryImpl
import com.x1oto.kidtasker.domain.AuthRepository
import com.x1oto.kidtasker.domain.TaskRepository
import com.x1oto.kidtasker.presentation.viewmodel.DashboardViewModel
import com.x1oto.kidtasker.presentation.viewmodel.SignInViewModel
import com.x1oto.kidtasker.presentation.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        FirebaseDataSource()
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }

    viewModel {
        SignInViewModel(get())
    }

    viewModel {
        SignUpViewModel(get())
    }

    viewModel {
        DashboardViewModel(get())
    }

}