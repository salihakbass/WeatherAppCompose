package com.salihakbas.weatherappcompose.ui.home

import com.salihakbas.weatherappcompose.data.model.Weather
import com.salihakbas.weatherappcompose.data.model.WeatherResponse

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val weatherData: WeatherResponse? = null,
        val forecastList: List<WeatherResponse> = emptyList()
    )

    sealed class UiAction {
        data class FetchWeather(val city: String, val apiKey: String) : UiAction()
        object NavigateToSearch : UiAction()
    }

    sealed class UiEffect {
        data object NavigateToSearch : UiEffect()
        data class ShowError(val message: String) : UiEffect()

    }
}