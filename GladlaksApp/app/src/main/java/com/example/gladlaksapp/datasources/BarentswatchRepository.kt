package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.LocalitiesWrapper
import com.example.gladlaksapp.models.LocalityDetailsWrapper

val datasource = BarentswatchNetworkDataSource



suspend fun getLocalities(year: Int, week: Int) : LocalitiesWrapper {
    return datasource.getLocalities(year,week)
}


suspend fun getDetailedLocalityInfo(localityNo: Int, year: Int, week: Int) : LocalityDetailsWrapper {
    return datasource.getDetailedLocalityInfo(localityNo, year, week)
}
