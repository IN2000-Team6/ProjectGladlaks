package com.example.gladlaksapp.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gladlaksapp.models.Locality
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
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLng), 6f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        if (localities != null) {
            for (loc in localities) {
                Marker(
                    onClick = { onMarkerClick(loc) },
                    position = LatLng(loc.lat, loc.lon),
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                )
            }
        }
    }
}