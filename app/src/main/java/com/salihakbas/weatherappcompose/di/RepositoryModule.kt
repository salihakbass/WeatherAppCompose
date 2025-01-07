package com.salihakbas.weatherappcompose.di

import com.salihakbas.weatherappcompose.data.repository.MainRepositoryImpl
import com.salihakbas.weatherappcompose.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository
}