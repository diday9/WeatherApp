package com.tompee.model

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task

class LocationService(val context: Context) {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Task<Location>? {
        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        return fusedLocationClient.lastLocation

    }
}