package com.salihakbas.weatherappcompose.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.salihakbas.weatherappcompose.R
import com.salihakbas.weatherappcompose.common.formatUnixTimestampToTime
import com.salihakbas.weatherappcompose.data.model.Weather
import com.salihakbas.weatherappcompose.ui.components.EmptyScreen
import com.salihakbas.weatherappcompose.ui.components.LoadingBar
import com.salihakbas.weatherappcompose.ui.home.HomeContract.UiAction
import com.salihakbas.weatherappcompose.ui.home.HomeContract.UiEffect
import com.salihakbas.weatherappcompose.ui.home.HomeContract.UiState
import com.salihakbas.weatherappcompose.utils.getBackgroundForIcon
import kotlinx.coroutines.flow.Flow
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    println("UiState: $uiState")
    when {
        uiState.isLoading -> LoadingBar()
        uiState.weatherData != null -> HomeContent(uiState.weatherData)
        else -> EmptyScreen()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeContent(weather: Weather) {
    val celciusTemp = (weather.main.temp - 273.15).roundToInt()
    val sunriseTime = formatUnixTimestampToTime(weather.sys.sunrise.toLong())
    val sunsetTime = formatUnixTimestampToTime(weather.sys.sunset.toLong())
    val backgroundColor =
        painterResource(id = getBackgroundForIcon(weather.weather.firstOrNull()?.icon ?: "01"))

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundColor,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = weather.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp),
                    )
                }
            }
            Text(
                text = weather.dt.toString(),
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$celciusTemp°",
                    color = Color.White,
                    fontSize = 120.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = weather.weather.firstOrNull()?.description ?: "N/A",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally), shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(36.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherDetailItem(
                        label = "Wind",
                        value = "${weather.wind.speed} km/h",
                        icon = painterResource(R.drawable.wind),
                        tint = Color.Blue
                    )
                    WeatherDetailItem(
                        label = "Wind",
                        value = "${weather.main.humidity}%",
                        icon = painterResource(R.drawable.humidity),
                        tint = Color.Blue
                    )
                    WeatherDetailItem(
                        label = "Wind",
                        value = sunriseTime,
                        icon = painterResource(R.drawable.sunrise),
                        tint = Color.Blue
                    )
                    WeatherDetailItem(
                        label = "Wind",
                        value = sunsetTime,
                        icon = painterResource(R.drawable.sunset),
                        tint = Color.Blue
                    )
                }

            }


        }
    }


}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherDetailItem(label: String, value: String, icon: Painter, tint: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(32.dp),
            tint = tint
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
}