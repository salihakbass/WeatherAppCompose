package com.salihakbas.weatherappcompose.ui.home

import com.salihakbas.weatherappcompose.data.model.CurrentResponse
import com.salihakbas.weatherappcompose.data.model.ForecastResponse

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val weatherData: CurrentResponse? = null,
        val forecastData: ForecastResponse? = null
    )

    sealed class UiAction {
        data class FetchWeather(val lat: Double, val lon: Double, val apiKey: String) : UiAction()
    }

    sealed class UiEffect {
        data class ShowError(val message: String) : UiEffect()

    }
}