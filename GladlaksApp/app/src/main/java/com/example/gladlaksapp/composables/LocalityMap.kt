package com.example.gladlaksapp.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(startLat, startLng), 6f)
    }

    val context = LocalContext.current

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        if (localities != null) {

            val bitmapFactory = BitmapFactory.decodeResource(context.resources, R.drawable.circle_icon_low_res)
            val bitmap =  Bitmap.createScaledBitmap(bitmapFactory, 50, 50, false)

            for (loc in localities) {
                if (!loc.isOnLand) {
                    Marker(
                        onClick = { onMarkerClick(loc) },
                        position = LatLng(loc.lat, loc.lon),
                        icon = BitmapDescriptorFactory.fromBitmap((bitmap))
                    )
                }
            }
        }
    }
}