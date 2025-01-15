package com.salihakbas.weatherappcompose.domain.usecase

import com.salihakbas.weatherappcompose.data.model.Weather
import com.salihakbas.weatherappcompose.data.model.WeatherResponse
import com.salihakbas.weatherappcompose.domain.repository.MainRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun getWeather(city: String, apiKey: String): Result<Weather> {
        return runCatching { mainRepository.getWeather(city, apiKey) }
    }



}