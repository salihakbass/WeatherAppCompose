package com.salihakbas.weatherappcompose.ui.home

import com.salihakbas.weatherappcompose.data.model.Weather

object HomeContract {
    data class UiState(
        val isLoading: Boolean = false,
        val weatherData: Weather? = null
    )

    sealed class UiAction

    sealed class UiEffect
}