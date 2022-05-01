package com.example.gladlaksapp

import android.app.Application

/**
 * Sets up dependency [Graph] with a context. Consider renaming/moving to avoid confusion with main
 */
class GladlaksApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyGraph.provide(this)
    }
}