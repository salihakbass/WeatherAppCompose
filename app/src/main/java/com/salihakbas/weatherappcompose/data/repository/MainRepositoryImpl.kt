package com.salihakbas.weatherappcompose.data.repository

import com.salihakbas.weatherappcompose.data.model.Weather
import com.salihakbas.weatherappcompose.data.source.remote.MainService
import com.salihakbas.weatherappcompose.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
) : MainRepository {
    override suspend fun getWeather(lat: Double, lon: Double, apiKey: String): Weather {
        return mainService.getWeather(lat,lon,apiKey)
    }
}