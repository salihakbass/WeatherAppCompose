package com.salihakbas.weatherappcompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salihakbas.weatherappcompose.domain.usecase.WeatherUseCase
import com.salihakbas.weatherappcompose.ui.home.HomeContract.UiAction
import com.salihakbas.weatherappcompose.ui.home.HomeContract.UiEffect
import com.salihakbas.weatherappcompose.ui.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.FetchWeather -> fetchWeather(uiAction.lat, uiAction.lon, uiAction.apiKey)
        }
    }

     fun fetchWeather(lat: Double, lon: Double, apiKey: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val weatherResult = weatherUseCase.getWeather(lat, lon, apiKey)
            val forecastResult = weatherUseCase.getForecast(lat, lon, apiKey)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    weatherData = weatherResult.getOrNull(),
                    forecastData = forecastResult.getOrNull()
                )
            }

            if (weatherResult.isFailure || forecastResult.isFailure) {
                emitUiEffect(UiEffect.ShowError("Error fetching weather data"))
            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}