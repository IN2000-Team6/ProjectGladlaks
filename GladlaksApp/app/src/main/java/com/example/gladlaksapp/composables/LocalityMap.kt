package com.example.gladlaksapp.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.Locality
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocalityMap(
    localities: List<Locality>?,
    startLat: Double = 59.0,
    startLng: Double = 10.7,
    onMarkerClick: (Locality) -> Boolean,
) {
    val initMarkerSize = 25
    var markerSize by remember { mutableStateOf(initMarkerSize)}

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLng), 6f)
    }

    LaunchedEffect(
        cameraPositionState.position.zoom,
        markerSize
    ) {
        if (cameraPositionState.position.zoom > 6f && cameraPositionState.position.zoom < 8f && markerSize == initMarkerSize) {
            markerSize = initMarkerSize + 25
        } else if (cameraPositionState.position.zoom <= 6f  && markerSize != initMarkerSize) {
            markerSize = initMarkerSize
        } else if (cameraPositionState.position.zoom > 8f && markerSize == initMarkerSize + 25) {
            markerSize = initMarkerSize + 50
        } else if (cameraPositionState.position.zoom <= 8f && cameraPositionState.position.zoom > 6f &&  markerSize != initMarkerSize) {
            markerSize = initMarkerSize + 25
        } else if (cameraPositionState.position.zoom > 10f && markerSize == initMarkerSize + 50) {
        markerSize = initMarkerSize + 75
        } else if (cameraPositionState.position.zoom <= 10f && cameraPositionState.position.zoom > 8f &&  markerSize != initMarkerSize) {
            markerSize = initMarkerSize + 50
        } else if (cameraPositionState.position.zoom > 12f && markerSize == initMarkerSize + 75) {
            markerSize = initMarkerSize + 100
        } else if (cameraPositionState.position.zoom <= 12f && cameraPositionState.position.zoom > 10f &&  markerSize != initMarkerSize) {
            markerSize = initMarkerSize + 75
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        if (localities != null) {
            val icon = createMarkerIcon(LocalContext.current, markerSize)

            for (loc in localities) {
                if (!loc.isOnLand) {
                    Marker(
                        onClick = { onMarkerClick(loc) },
                        position = LatLng(loc.lat, loc.lon),
                        icon = icon
                    )
                }
            }
        }
    }
}

fun createMarkerIcon(context: Context, size: Int): BitmapDescriptor {
    val bitmapIcon = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(context.resources, R.drawable.white_border_icon),
        size,
        size,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(bitmapIcon)
}

