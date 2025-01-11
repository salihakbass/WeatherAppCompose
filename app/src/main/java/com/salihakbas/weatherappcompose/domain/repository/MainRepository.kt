package com.salihakbas.weatherappcompose.domain.repository

import com.salihakbas.weatherappcompose.data.model.Weather

interface MainRepository {
    suspend fun getWeather(lat: Double, lon: Double, apiKey: String): Weather
}