package com.salihakbas.weatherappcompose.data.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("result") val result: List<WeatherResponse>
)

data class WeatherResponse(
    @SerializedName("date") val date: String = "",
    @SerializedName("day") val day: String = "",
    @SerializedName("icon") val icon: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("degree") val degree: String = "",
    @SerializedName("min") val min: String = "",
    @SerializedName("max") val max: String = "",
    @SerializedName("night") val night: String = "",
    @SerializedName("humidity") val humidity: String = ""
)

