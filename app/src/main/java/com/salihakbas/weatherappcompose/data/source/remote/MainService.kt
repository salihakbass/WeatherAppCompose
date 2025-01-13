package com.salihakbas.weatherappcompose.data.source.remote

import com.salihakbas.weatherappcompose.data.model.CurrentResponse
import com.salihakbas.weatherappcompose.data.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): CurrentResponse

    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): ForecastResponse
}