package com.example.gladlaksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.gladlaksapp.composables.*
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val localities by model.localities.observeAsState()
            val loadedLocality by model.localityDetail.observeAsState()
            val localityTemps by model.localityTemps.observeAsState()

            AppContainer {
                BottomNavLayout(
                    content = {
                        
                            },
                        )
                      },
                    bottomNavContent = {
                        MainNavigation(
                            selectedItemIndex = 0,
                            onClick = { /*TODO implement state for navigation */ },
                        )
                    }
                )
            }
        }
    }
}
/*
// DUMMY DATA
val testLocalities = arrayListOf(
    Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 7, name="g", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 8, name="h", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
)

FavoritesBottomSheet(
localities = testLocalities,
loadedLocality = loadedLocality,
resetLoadedLocality = { model.resetLoadedLocality() },
localityTemps = localityTemps,
loadLocalityDetails = { loc ->
    model.loadLocalityDetails(loc)
*/
