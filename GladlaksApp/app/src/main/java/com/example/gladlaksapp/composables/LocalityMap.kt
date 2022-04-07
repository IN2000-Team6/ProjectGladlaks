package com.example.gladlaksapp.composables

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.drawable.toBitmap
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.Locality
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocalityMap(
    localities: List<Locality>?,
    onMarkerClick: (Locality) -> Unit,
    onMapClick: () -> Unit,
    startLat: Double = 61.9,
    startLng: Double = 8.7,
    startZoom: Float = 5.9f,
) {
    val initMarkerSize = 20f
    var markerSize by remember { mutableStateOf(initMarkerSize)}

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLng), startZoom)
    }

    LaunchedEffect(
        cameraPositionState.position.zoom,
        markerSize,
    ) {
        if (cameraPositionState.position.zoom > 6f && cameraPositionState.position.zoom < 7f) {
            markerSize = initMarkerSize * 2
        } else if (cameraPositionState.position.zoom <= 6f  && markerSize != initMarkerSize) {
            markerSize = initMarkerSize
        } else if (cameraPositionState.position.zoom > 7f && cameraPositionState.position.zoom < 8f) {
            markerSize = initMarkerSize * 2.5f
        } else if (cameraPositionState.position.zoom <= 7f && cameraPositionState.position.zoom > 6f) {
            markerSize = initMarkerSize * 2
        } else if (cameraPositionState.position.zoom > 8f && cameraPositionState.position.zoom < 9f) {
            markerSize = initMarkerSize * 3
        } else if (cameraPositionState.position.zoom <= 8f && cameraPositionState.position.zoom > 7f) {
            markerSize = initMarkerSize * 2.5f
        } else if (cameraPositionState.position.zoom > 9f && cameraPositionState.position.zoom < 10f) {
            markerSize = initMarkerSize * 3.5f
        } else if (cameraPositionState.position.zoom <= 9f && cameraPositionState.position.zoom > 8f) {
            markerSize = initMarkerSize * 3
        }else if (cameraPositionState.position.zoom > 10f && cameraPositionState.position.zoom < 11f) {
            markerSize = initMarkerSize * 4
        } else if (cameraPositionState.position.zoom <= 10f && cameraPositionState.position.zoom > 9f) {
            markerSize = initMarkerSize * 3.5f
        } else if (cameraPositionState.position.zoom > 11f && cameraPositionState.position.zoom < 12f) {
            markerSize = initMarkerSize * 5.5f
        } else if (cameraPositionState.position.zoom <= 11f && cameraPositionState.position.zoom > 10f) {
            markerSize = initMarkerSize * 4
        }else if (cameraPositionState.position.zoom > 12f && markerSize == initMarkerSize * 5.5f) {
            markerSize = initMarkerSize * 7
        } else if (cameraPositionState.position.zoom <= 12f && cameraPositionState.position.zoom > 11f) {
            markerSize = initMarkerSize * 5.5f
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { onMapClick() }
    ) {
        if (localities != null) {
            val iconT = createMarkerIcon_T(LocalContext.current, markerSize.toInt())
            val iconP = createMarkerIcon_P(LocalContext.current, markerSize.toInt())

            for (loc in localities) {
                SmartMarker(
                    loc = loc,
                    onClick = {
                        onMarkerClick(loc)
                        true
                    },
                    iconT = iconT,
                    iconP = iconP
                )
            }
        }
    }
}

fun createMarkerIcon_T(context: Context, size: Int): BitmapDescriptor {
    val bitmapIcon = Bitmap.createScaledBitmap(
        getDrawable(context, R.drawable.ic_white_border_marker_t)!!.toBitmap(50, 50),
        size,
        size,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(bitmapIcon)
}

fun createMarkerIcon_P(context: Context, size: Int): BitmapDescriptor {
    val bitmapIcon = Bitmap.createScaledBitmap(
        getDrawable(context, R.drawable.ic_white_border_marker_p)!!.toBitmap(50, 50),
        size,
        size,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(bitmapIcon)
}

@Composable
fun SmartMarker(loc: Locality, onClick: (Marker) -> Boolean, iconT: BitmapDescriptor, iconP: BitmapDescriptor){
    Marker(
        icon = if (loc.hasReportedLice) iconT else iconP,
        position = LatLng(loc.lat, loc.lon),
        anchor = Offset(0.5f, 0.6f),
        onClick = onClick,
        zIndex = if (loc.hasReportedLice) Float.MAX_VALUE else 0f
    )
}




