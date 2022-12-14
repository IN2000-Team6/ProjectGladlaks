package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.LocalitiesWrapper
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.LouseDataByYear

/**
 * Interface providing method signatures to [BarentswatchNetworkDataSource] and
 * [TestBarentswatchNetworkDatasource]
 */
interface BarentswatchNetworkDatasourceInterface {
    suspend fun getToken(): String
    suspend fun getLocalities(year: Int, week: Int): LocalitiesWrapper
    suspend fun getDetailedLocalityInfo(
        localityNo: Int,
        year: Int,
        week: Int
    ): LocalityDetailsWrapper

    suspend fun getLouseDataByYear(localityNo: Int, year: Int): LouseDataByYear
}