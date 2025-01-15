package com.salihakbas.weatherappcompose.domain.repository

import com.salihakbas.weatherappcompose.data.model.Weather
import com.salihakbas.weatherappcompose.data.model.WeatherResponse

interface MainRepository {
    suspend fun getWeather(city: String, apiKey: String): Weather

}