package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.BuildConfig
import com.example.gladlaksapp.models.barentswatch.BarentsWatchToken
import com.example.gladlaksapp.models.LocalitiesWrapper
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.LouseDataByYear
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

object BarentswatchNetworkDataSource {
    private const val tokenURL = "https://id.barentswatch.no/connect/token"
    private const val localitiesURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s"
    private const val localityDetailedURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s/%s"
    private const val louseDataByYearURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/liceTypeDistribution/%s"

    private const val bw_client = BuildConfig.BARENTSWATCH_CLIENT
    private const val bw_secret = BuildConfig.BARENTSWATCH_SECRET

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    // ------------- Network-requests ------------- //

    /**
     * Gets token to access the Barentswatch API. Lasts 3600 seconds
     */
    private suspend fun getToken() : String {
        val response: BarentsWatchToken = client.post(tokenURL) {
            headers.append("Content-Type","application/x-www-form-urlencoded")
            body = "client_id=$bw_client&scope=api&client_secret=$bw_secret&grant_type=client_credentials"
        }
        return response.access_token
    }

    /**
     * Returns a data class representing a list of all localities
     * @param year - The year
     * @param week - The calendar week
     */
    suspend fun getLocalities(year: Int, week: Int) : LocalitiesWrapper {
        val token: String = getToken()
        return client.get(localitiesURL.format(year,week)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }

    /**
     * Returns a data class representing detailed information about one locality
     * @param localityNo - The locality no, as stated by the [Locality] dataclass
     */
    suspend fun getDetailedLocalityInfo(localityNo: Int, year: Int, week: Int) : LocalityDetailsWrapper {
        val token: String = getToken()
        return client.get(localityDetailedURL.format(localityNo,year,week)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }

    /**
     * Returns a data class representing louse data from a year at a locality
     * @param localityNo - The locality no, as stated by the [Locality] dataclass
     * @param year - The year to fetch data from
     */
    suspend fun getLouseDataByYear(localityNo: Int, year: Int) : LouseDataByYear {
        val token: String = getToken()
        return client.get(louseDataByYearURL.format(localityNo,year)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }
}
