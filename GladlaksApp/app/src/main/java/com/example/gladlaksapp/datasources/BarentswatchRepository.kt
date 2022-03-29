package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.LocalitiesWrapper
import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDetailsWrapper

object BarentswatchRepository {

    private val datasource = BarentswatchNetworkDataSource

    /**
     * Get all localities from BarentsWatch
     * @param year the year
     * @param week the calendar week
     * @return LocalitiesWrapper
     */
    suspend fun getLocalities(year: Int, week: Int) : LocalitiesWrapper {
        return datasource.getLocalities(year,week)
    }

    /**
     * Get all localities that are in water
     * @param year the year
     * @param week the calendar week
     * @return a list containing all localities in sea
     */
    suspend fun getLocalitiesInWater(year: Int, week: Int) : List<Locality> {
        return datasource.getLocalities(year, week).localities.filter {
            !it.isOnLand
        }
    }

    /**
     * Get detailed locality information for one locality. Use this for showing data in sheet
     * @param localityNo the locality number provided by BarentsWatch
     * @param year the year
     * @param week the calendar week
     */
    suspend fun getDetailedLocalityInfo(localityNo: Int, year: Int, week: Int) : LocalityDetailsWrapper {
        return datasource.getDetailedLocalityInfo(localityNo, year, week)
    }
}
