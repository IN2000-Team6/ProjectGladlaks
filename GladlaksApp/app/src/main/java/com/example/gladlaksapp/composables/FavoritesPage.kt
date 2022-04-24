package com.example.gladlaksapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.Locality

@Composable
fun Favorites(
    favoritesList: List<Locality>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp, top = 20.dp)
            ,
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.ic_fish_icon),
            contentDescription = "Fish icon",
            modifier = Modifier
                .size(96.dp)
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.favoritter),
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
    //Lazy column?
    Column() {
        for (loc in favoritesList){
            Box(
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                InfoCard {
                    SnippetWrapper {
                        LocalitySnippet(locality = loc, isCollapsed = true) {
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SnippetWrapper(
    content: @Composable () -> Unit
){
    Row(
        modifier = Modifier
            .padding(vertical = 15.dp)
    ){
        content()
    }

}

// DUMMY DATA
val testLocalities = arrayListOf(
    Locality(localityNo = 1, name="a", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 2, name="b", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 3, name="c", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 4, name="d", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
    Locality(localityNo = 5, name="e", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = true),
    Locality(localityNo = 6, name="f", hasPd = false, hasIla = false, isOnLand = false, lat = 1.0, lon = 1.0, hasReportedLice = false),
)