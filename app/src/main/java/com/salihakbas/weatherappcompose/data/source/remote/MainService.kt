package com.salihakbas.weatherappcompose.data.source.remote

import com.salihakbas.weatherappcompose.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): Weather
}