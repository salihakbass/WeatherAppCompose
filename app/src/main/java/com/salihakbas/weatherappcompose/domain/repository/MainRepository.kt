package com.salihakbas.weatherappcompose.domain.repository

import com.salihakbas.weatherappcompose.data.model.CurrentResponse
import com.salihakbas.weatherappcompose.data.model.ForecastResponse

interface MainRepository {
    suspend fun getWeather(lat: Double, lon: Double, apiKey: String): CurrentResponse

    suspend fun getForecastWeather(lat:Double,lon: Double,apiKey: String) : ForecastResponse
}