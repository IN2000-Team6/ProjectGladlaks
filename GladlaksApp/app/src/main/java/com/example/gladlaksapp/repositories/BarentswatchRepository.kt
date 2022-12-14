package com.example.gladlaksapp.repositories

import com.example.gladlaksapp.datasources.BarentswatchNetworkDatasourceInterface
import com.example.gladlaksapp.models.*
import com.patrykandpatryk.vico.core.entry.FloatEntry
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class BarentswatchRepository(
   private val dataSource: BarentswatchNetworkDatasourceInterface
) {
    /**
     * Get all localities that are in water
     * @param year the year
     * @param week the calendar week
     * @return a list containing all localities in sea
     */
    suspend fun getLocalitiesInWater(year: Int, week: Int) : List<Locality> {
        return dataSource.getLocalities(year, week).localities.filter {
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
        return dataSource.getDetailedLocalityInfo(localityNo, year, week)
    }

    suspend fun getTwoGenerations(
        localityNo: Int,
        gen1: Int,
        gen2: Int
    ) = coroutineScope {
        val lousedata = awaitAll(
            async { dataSource.getLouseDataByYear(localityNo, gen1)},
            async { dataSource.getLouseDataByYear(localityNo, gen2)}
        )

        return@coroutineScope lousedata.map { year ->
            year.data.mapIndexed {
                x,y -> FloatEntry((x+1).toFloat(),y.avgAdultFemaleLice)
            }
        }
    }
}

