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
            is UiAction.FetchWeather -> fetchWeather(uiAction.city, uiAction.apiKey)
            is UiAction.NavigateToSearch -> navigateToSearch()
        }
    }

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = weatherUseCase.getWeather(city, apiKey)
            if (result.isSuccess) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        weatherData = result.getOrNull()?.result?.firstOrNull(),
                        forecastList = result.getOrNull()?.result ?: emptyList()
                    )
                }
            } else {
                emitUiEffect(UiEffect.ShowError("Error fetching weather data"))
            }
        }
    }

    private fun navigateToSearch() {
        viewModelScope.launch {
            _uiEffect.send(UiEffect.NavigateToSearch)
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}