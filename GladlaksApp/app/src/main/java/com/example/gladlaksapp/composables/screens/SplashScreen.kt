package com.example.gladlaksapp.composables.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gladlaksapp.R
import com.example.gladlaksapp.composables.NetworkNotice
import com.example.gladlaksapp.models.ConnectionState
import com.example.gladlaksapp.models.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

//source for splash screen: https://www.youtube.com/watch?v=GhNwvGePTbY&ab_channel=PhilippLackner
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available



    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        delay(500L)
        if (isConnected) {
            navController.popBackStack()
            navController.navigate(Screen.Map.route)
        }

    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.ic_salmon_logo_draft),
                contentDescription = stringResource(R.string.text_logo_app_name),
                modifier = Modifier.scale(scale.value)
            )
            if (!isConnected) {
                NetworkNotice()
            }
        }
    }
}