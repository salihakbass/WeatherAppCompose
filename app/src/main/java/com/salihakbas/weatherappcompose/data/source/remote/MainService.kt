package com.salihakbas.weatherappcompose.data.source.remote

import com.salihakbas.weatherappcompose.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainService {
    @GET("getWeather")
    suspend fun getWeather(
        @Query("data.city") city: String,
        @Query("data.lang") lang: String = "tr",
        @Header("authorization") apiKey: String = "apikey 3mfVLGaUvnPiJNFrMVMkfL:4Aqw3u7s2FCfIC5XCPyj3D",
        @Header("content-type") contentType: String = "application/json"
    ): Weather

}