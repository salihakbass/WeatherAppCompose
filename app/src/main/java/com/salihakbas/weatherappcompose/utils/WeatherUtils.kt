package com.salihakbas.weatherappcompose.utils

import com.salihakbas.weatherappcompose.R

fun getBackgroundForIcon(icon: String): Int {
    return when (icon.dropLast(1)) {
        "01" -> R.drawable.snow_bg
        "02", "03", "04" -> R.drawable.cloudy_bg
        "09", "10", "11" -> R.drawable.rainy_bg
        "13" -> R.drawable.snow_bg
        "50" -> R.drawable.haze_bg
        else -> R.drawable.sunny_bg
    }
}

fun getWeatherIcon(icon: String): Int {
    return when (icon) {
        "01d", "0n" -> R.drawable.sunny
        "02d", "02n" -> R.drawable.cloudy_sunny
        "03d", "03n" -> R.drawable.cloudy_sunny
        "04d", "04n" -> R.drawable.cloudy
        "09d", "09n" -> R.drawable.rainy
        "10d", "10n" -> R.drawable.rainy
        "11d", "11n" -> R.drawable.storm
        "13d", "13n" -> R.drawable.snowy
        "50d", "50n" -> R.drawable.windy
        else -> R.drawable.sunny
    }
}

