package com.example.gladlaksapi.repositories

import com.example.gladlaksapi.models.Location
import ucar.ma2.IndexIterator
import ucar.nc2.dt.GridCoordSystem
import ucar.nc2.dt.GridDataset
import ucar.nc2.dt.GridDatatype
import ucar.ma2.Array

fun openWithGridDataset(urlName: String, lat: Double, lon: Double): Location {
    val gds: GridDataset = ucar.nc2.dt.grid.GridDataset.open(urlName)
    val grid: GridDatatype = gds.findGridDatatype("temperature")
    val gcs: GridCoordSystem = grid.coordinateSystem

    // find the x,y index for a specific lat/lon position
    val xy: IntArray = gcs.findXYindexFromLatLon(lat, lon, null) // xy[0] = x, xy[1] = y

    // read the data at that lat, lon and the first z level and all timesteps produces a time series
    val timeSeriesData = grid.readDataSlice(-1, -1, xy[1], xy[0]) // note order is t, z, y, x
    return parseTimeSeries(timeSeriesData, 16, lat, lon)
}

fun parseTimeSeries(
    timeSeries: Array,
    columns: Int,
    lat: Double,
    lon: Double
): Location {
    val data = mutableListOf<List<Float?>>()
    val it: IndexIterator = timeSeries.indexIterator
    while(it.hasNext()) {
        val row = mutableListOf<Float?>()
        for (depth in 0 until columns) {
            val measurement: Float = it.floatNext
            row.add(if (measurement.isNaN()) null else measurement)
        }
        data.add(row)
    }

    return Location(
        rows = "time",
        columns = "depth",
        data = data,
        lat = lat,
        lon = lon,
        hours = data.size
    )
}