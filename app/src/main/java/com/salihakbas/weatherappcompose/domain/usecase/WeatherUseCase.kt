package com.salihakbas.weatherappcompose.domain.usecase

import com.salihakbas.weatherappcompose.data.model.CurrentResponse
import com.salihakbas.weatherappcompose.data.model.ForecastResponse
import com.salihakbas.weatherappcompose.domain.repository.MainRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun getWeather(lat: Double, lon: Double, apiKey: String): Result<CurrentResponse> {
        return runCatching { mainRepository.getWeather(lat, lon, apiKey) }
    }

    suspend fun getForecast(lat: Double, lon: Double, apiKey: String): Result<ForecastResponse> {
        return runCatching { mainRepository.getForecastWeather(lat, lon, apiKey) }
    }


}