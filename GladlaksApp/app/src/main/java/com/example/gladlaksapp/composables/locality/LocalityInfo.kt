package com.example.gladlaksapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetails

@Composable
fun LocalityInfo(locality: Locality, localityInfo: LocalityDetails) {
    //TODO: Fikse font
    Column() {
        /*
        InfoComponent(
            image = painterResource(R.drawable.ic_temperature_icon),
            imageContentDescription = stringResource(R.string.temperature_icon_description),
            infoText = stringResource(R.string.ocean_temp),
            data = localityInfo.seaTemperature,
            //ascii value for degrees symbol
            textAddition = "${176.toChar()}C"
        )
        */
        InfoComponent(
            image = painterResource(R.drawable.ic_lice_icon),
            imageContentDescription = stringResource(R.string.lice_icon_description),
            infoText = stringResource(R.string.female_lice),
            data = localityInfo.avgAdultFemaleLice,
            dataDescription = stringResource(R.string.avg)
        )
        InfoComponent(
            image = painterResource(R.drawable.ic_lice_icon),
            imageContentDescription = stringResource(R.string.lice_icon_description),
            infoText = stringResource(R.string.mobile_lice),
            data = localityInfo.avgMobileLice,
            dataDescription = stringResource(R.string.avg)
        )
        InfoComponent(
            image = painterResource(R.drawable.ic_lice_icon),
            imageContentDescription = stringResource(R.string.lice_icon_description),
            infoText = stringResource(R.string.stationary_lice),
            data = localityInfo.avgStationaryLice,
            dataDescription = stringResource(R.string.avg)
        )
        InfoComponent(
            image = painterResource(R.drawable.ic_ila_pd_icon),
            imageContentDescription = stringResource(R.string.ila_pd_icon_description),
            infoText = stringResource(R.string.ila),
            data = locality.hasIla,
        )
        InfoComponent(
            image = painterResource(R.drawable.ic_ila_pd_icon),
            imageContentDescription = stringResource(R.string.ila_pd_icon_description),
            infoText = stringResource(R.string.pd),
            data = locality.hasPd,
        )
    }
}

@Composable
fun InfoComponent(
    image: Painter,
    imageContentDescription: String,
    infoText: String,
    data: Any?,
    textAddition: String = "",
    dataDescription: String = ""
) {
    Box(modifier = Modifier.padding(10.dp)) {
        Row {
            Image(
                painter = image,
                contentDescription = imageContentDescription
            )
            if (data == null) {
                Text(
                    text = "${infoText}${stringResource(R.string.colon)}${stringResource(R.string.no_data)}",
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }else if (data is Boolean && data) {
                Text(
                    text = "${infoText}${stringResource(R.string.colon)}${stringResource(R.string.proven)}",
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }else if (data is Boolean && !data) {
                Text(
                    text = "${infoText}${stringResource(R.string.colon)}${stringResource(R.string.not_proven)}",
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            } else {
                Text(
                    text = ("${infoText}${stringResource(R.string.colon)}$data$textAddition"),
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = ("$dataDescription"),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .offset(x = (-10).dp),
                    //TODO: Styling små bokstaver grå tekst
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}