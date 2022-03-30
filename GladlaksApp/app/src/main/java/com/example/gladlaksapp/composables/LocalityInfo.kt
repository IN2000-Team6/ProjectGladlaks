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
import androidx.compose.ui.unit.sp
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.LocalityDetails

@Composable
fun LocalityInfo(localityInfo: LocalityDetails) {

    Column() {
        InfoComponent(
            image = painterResource(R.drawable.ic_temperature_icon),
            imageContentDescription = stringResource(R.string.temperature_icon_description),
            infoText = stringResource(R.string.ocean_temp),
            data = localityInfo.seaTemperature,
            //ascii value for degrees symbol
            textAddition = "${176.toChar()}C"
        )
        InfoComponent(
            image = painterResource(R.drawable.ic_lice_icon),
            imageContentDescription = stringResource(R.string.lice_icon_description),
            infoText = stringResource(R.string.female_lice),
            data = localityInfo.avgAdultFemaleLice,
        )
        InfoComponent(
            image = painterResource(R.drawable.ic_lice_icon),
            imageContentDescription = stringResource(R.string.lice_icon_description),
            infoText = stringResource(R.string.mobile_lice),
            data = localityInfo.avgMobileLice,
        )

        InfoComponent(
            image = painterResource(R.drawable.ic_lice_icon),
            imageContentDescription = stringResource(R.string.lice_icon_description),
            infoText = stringResource(R.string.stationary_lice),
            data = localityInfo.avgStationaryLice,
        )
    }
}

@Composable
fun InfoComponent(
    image: Painter,
    imageContentDescription: String,
    infoText: String,
    data: Number?,
    textAddition: String = "",
) {
    Box(
        modifier = Modifier.padding(15.dp)
    ) {
        Row() {
            Image(
                painter = image,
                contentDescription = imageContentDescription
            )
            if (data == null) {
                Text(
                    text = "${infoText}: Ikke rapportert",
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            } else {
                Text(
                    text = ("${infoText}: $data$textAddition"),
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
                //TODO: grå tekst med info om tall
            }
        }
    }

}