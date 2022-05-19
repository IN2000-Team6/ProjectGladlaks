package com.example.gladlaksapp.composables.map

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
/**
 * Composable used for showing the map and loading markers
 * @param localities the list of localities you want to show on the map
 * @param onMarkerClick the onclick action for a marker
 * @param onMapClick the onclick action for them map
 * @param startLat the start latitude of the camera
 * @param startLng the start longitude of the camera
 * @param startZoom the initial camera zoom
 */
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
    var markerSize by remember { mutableStateOf(initMarkerSize) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLng), startZoom)
    }

    LaunchedEffect(
        cameraPositionState.position.zoom,
        markerSize,
    ) {
        markerSize = setMarkerSize(cameraPositionState.position.zoom, initMarkerSize, markerSize)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { onMapClick() }
        ) {
            if (localities != null) {
                val iconT = createMarkerIconTurquoise(LocalContext.current, markerSize.toInt())
                val iconG = createMarkerIconGray(LocalContext.current, markerSize.toInt())

                for (loc in localities) {
                    SmartMarker(
                        loc = loc,
                        iconT = iconT,
                        iconP = iconG,
                        onClick = {
                            onMarkerClick(loc)
                            true
                        }
                    )
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.TopEnd)) {
            MapMarkerColorInfoBox()
        }
    }
}

fun createMarkerIconTurquoise(context: Context, size: Int): BitmapDescriptor {
    val bitmapIcon = Bitmap.createScaledBitmap(
        getDrawable(context, R.drawable.ic_white_border_marker_t)!!.toBitmap(50, 50),
        size,
        size,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(bitmapIcon)
}

fun createMarkerIconGray(context: Context, size: Int): BitmapDescriptor {
    val bitmapIcon = Bitmap.createScaledBitmap(
        getDrawable(context, R.drawable.ic_white_border_marker_g)!!.toBitmap(50, 50),
        size,
        size,
        false
    )
    return BitmapDescriptorFactory.fromBitmap(bitmapIcon)
}

// creates a map marker, z index set to max float if locality has reported data
@Composable
fun SmartMarker(
    loc: Locality,
    onClick: (Marker) -> Boolean,
    iconT: BitmapDescriptor,
    iconP: BitmapDescriptor
) {
    Marker(
        icon = if (loc.hasReportedLice) iconT else iconP,
        position = LatLng(loc.lat, loc.lon),
        anchor = Offset(0.5f, 0.6f),
        onClick = onClick,
        zIndex = if (loc.hasReportedLice) Float.MAX_VALUE else 0f
    )
}

// takes current zoom level on map, and returns a marker size fitting for each zoom level
fun setMarkerSize(currentZoom: Float, initMarkerSize: Float, markerSize: Float): Float {
    if (currentZoom < 6f) {
        return initMarkerSize
    } else if (currentZoom > 6f && currentZoom < 7f) {
        return initMarkerSize * 2
    } else if (currentZoom <= 6f && markerSize != initMarkerSize) {
        return initMarkerSize
    } else if (currentZoom > 7f && currentZoom < 8f) {
        return initMarkerSize * 2.5f
    } else if (currentZoom <= 7f && currentZoom > 6f) {
        return initMarkerSize * 2
    } else if (currentZoom > 8f && currentZoom < 9f) {
        return initMarkerSize * 3
    } else if (currentZoom <= 8f && currentZoom > 7f) {
        return initMarkerSize * 2.5f
    } else if (currentZoom > 9f && currentZoom < 10f) {
        return initMarkerSize * 3.5f
    } else if (currentZoom <= 9f && currentZoom > 8f) {
        return initMarkerSize * 3
    } else if (currentZoom > 10f && currentZoom < 11f) {
        return initMarkerSize * 4
    } else if (currentZoom <= 10f && currentZoom > 9f) {
        return initMarkerSize * 3.5f
    } else if (currentZoom > 11f && currentZoom < 12f) {
        return initMarkerSize * 5.5f
    } else if (currentZoom <= 11f && currentZoom > 10f) {
        return initMarkerSize * 4
    } else if (currentZoom > 12f && markerSize == initMarkerSize * 5.5f) {
        return initMarkerSize * 7
    } else if (currentZoom <= 12f && currentZoom > 11f) {
        return initMarkerSize * 5.5f
    }
    return initMarkerSize * 7
}




