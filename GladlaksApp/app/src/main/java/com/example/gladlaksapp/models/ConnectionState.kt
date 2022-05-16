package com.example.gladlaksapp.models

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}