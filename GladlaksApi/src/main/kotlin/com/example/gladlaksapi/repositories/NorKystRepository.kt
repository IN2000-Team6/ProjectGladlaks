package com.example.gladlaksapi.repositories

import com.example.gladlaksapi.models.LocationTemperature
import ucar.nc2.dt.grid.GridDataset
import ucar.ma2.Array

fun fetchFromNorKyst(
    urlName: String,
    lat: Double,
    lon: Double,
    depth: Int
): LocationTemperature {
    val grid = GridDataset.open(urlName).findGridDatatype("temperature")

    val xy = grid.coordinateSystem.findXYindexFromLatLon(lat, lon, null)

    // read the data at that lat, lon and the first z level and all timesteps produces a time series
    val depthTimeSlice = grid.readDataSlice(-1, depth, xy[1], xy[0])

    return LocationTemperature(
        lat = lat,
        lon = lon,
        depth = depth,
        data = parseArray(depthTimeSlice)
    )
}

fun parseArray(
    timeSeries: Array,
): List<Float?> {
    val data = mutableListOf<Float?>()
    val it = timeSeries.indexIterator

    while(it.hasNext()) {
        data.add(it.floatNext)
    }
    return data
}
