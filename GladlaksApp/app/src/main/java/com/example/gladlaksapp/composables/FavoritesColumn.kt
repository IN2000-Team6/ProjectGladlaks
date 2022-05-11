package com.example.gladlaksapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gladlaksapp.R
import com.example.gladlaksapp.models.Locality

@Composable
fun FavoritesColumn(
    favoritesList: List<Locality>,
    //onExpandClick: () -> Unit,
    onButtonClick: (Locality) -> Unit,
    isCollapsed: Boolean,
    toggleFavorite: () -> Unit,
    favButtonTint: Color,
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        WeekDatesSnippet()
    }

    //Lazy column?
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        if (favoritesList.isNotEmpty()){
            for (loc in favoritesList) {
                Box(
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    InfoCard {
                        SnippetWrapper {
                            FavoriteLocalitySnippet(
                                locality = loc,
                                isCollapsed = isCollapsed,
                                //TODO state hoist!
                                onExpandClick = {
                                    onButtonClick(loc)
                                    true
                                },
                                toggleFavorite = toggleFavorite,
                                favButtonTint = favButtonTint,
                            )
                        }
                    }
                }
            }
        }else{
            Box(
                modifier = Modifier.padding(vertical = 2.dp).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text("Ingen favoritter",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                )
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
