package com.salihakbas.weatherappcompose.data.repository

import com.salihakbas.weatherappcompose.data.remote.MainService
import com.salihakbas.weatherappcompose.data.source.remote.MainService
import com.salihakbas.weatherappcompose.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
) : MainRepository