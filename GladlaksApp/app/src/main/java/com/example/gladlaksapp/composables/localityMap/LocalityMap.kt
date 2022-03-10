package com.example.gladlaksapp.composables.localityMap

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.toLatLng
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.BitmapDescriptor
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.R
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

@Composable
fun LocalityMap(localities: List<Locality>?) {
    val mapView = rememberMapViewWithLifeCycle()

    AndroidView(
        factory = { mapView },
        update = {
            CoroutineScope(Dispatchers.Main).launch {
                val map = it.awaitMap()

                if (localities != null) {
                    for (locality in localities) {
                        val (localityNo, name, lat, long, isOnLand) = locality
                        val marker = MarkerOptions()
                            .position(toLatLng(lat, long))
                            .snippet(localityNo.toString())
                            .title(name)

                        marker.icon(if (isOnLand) redMarker else blueMarker)
                        map.addMarker(marker)
                    }
                }

                val startLocation = LatLng(59.913868, 10.752245)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 6.0f))
                map.uiSettings.isZoomControlsEnabled = true
            }
        }
    )
}

@Composable
fun rememberMapViewWithLifeCycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map_frame
        }
    }
    val lifeCycleObserver = rememberMapLifeCycleObserver(mapView)
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifeCycle) {
        lifeCycle.addObserver(lifeCycleObserver)
        onDispose {
            lifeCycle.removeObserver(lifeCycleObserver)
        }
    }
    return mapView
}

@Composable
fun rememberMapLifeCycleObserver(mapView: MapView) =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }


val redMarker: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(
    BitmapDescriptorFactory.HUE_ORANGE
)

val blueMarker: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(
    BitmapDescriptorFactory.HUE_BLUE
)