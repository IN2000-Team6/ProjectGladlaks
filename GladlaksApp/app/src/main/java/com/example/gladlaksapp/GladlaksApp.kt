package com.example.gladlaksapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is the entry point of the application.
 * Hilt uses this to initiate dependency injection
 */
@HiltAndroidApp
class GladlaksApp : Application()