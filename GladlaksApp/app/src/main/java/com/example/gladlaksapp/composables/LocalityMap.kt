package com.example.gladlaksapp.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
    onMarkerClick: (Locality) -> Unit,
    onMapClick: () -> Unit,
    startLat: Double = 61.9,
    startLng: Double = 8.7,
    startZoom: Float = 5.9f,
) {
    val initMarkerSize = 25
    var markerSize by remember { mutableStateOf(initMarkerSize)}

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLng), startZoom)
    }

    LaunchedEffect(
        cameraPositionState.position.zoom,
        markerSize,
    ) {
        if (cameraPositionState.position.zoom > 6f && cameraPositionState.position.zoom < 8f && markerSize == initMarkerSize) {
            markerSize = initMarkerSize * 2
        } else if (cameraPositionState.position.zoom <= 6f  && markerSize != initMarkerSize) {
            markerSize = initMarkerSize
        } else if (cameraPositionState.position.zoom > 8f && markerSize == initMarkerSize * 2) {
            markerSize = initMarkerSize * 3
        } else if (cameraPositionState.position.zoom <= 8f && cameraPositionState.position.zoom > 6f &&  markerSize != initMarkerSize) {
            markerSize = initMarkerSize * 2
        } else if (cameraPositionState.position.zoom > 10f && markerSize == initMarkerSize * 3) {
            markerSize = initMarkerSize * 3
        } else if (cameraPositionState.position.zoom <= 10f && cameraPositionState.position.zoom > 8f &&  markerSize != initMarkerSize) {
            markerSize = initMarkerSize * 3
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { onMapClick() }
    ) {
        if (localities != null) {
            val icon = createMarkerIcon(LocalContext.current, markerSize)

            for (loc in localities) {
                if (!loc.isOnLand) {
                    Marker(
                        icon = icon,
                        position = LatLng(loc.lat, loc.lon),
                        anchor = Offset(0.5f, 0.6f),
                        onClick = {
                            onMarkerClick(loc)
                            true
                        },
                    )
                }
            }
        }
    }
}

fun createMarkerIcon(context: Context, size: Int): BitmapDescriptor {
    val bitmapIcon = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(context.resources, R.drawable.white_border_turquoise_icon),
        size,
        size,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(bitmapIcon)
}

