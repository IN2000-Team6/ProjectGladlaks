package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.BarentswatchNetworkDatasourceInterface
import com.example.gladlaksapp.BuildConfig
import com.example.gladlaksapp.models.LocalitiesWrapper
import com.example.gladlaksapp.models.LocalityDetailsWrapper
import com.example.gladlaksapp.models.LouseDataByYear
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class BarentswatchNetworkDataSource : BarentswatchNetworkDatasourceInterface{
    private val tokenURL = "https://id.barentswatch.no/connect/token"
    private val localitiesURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s"
    private val localityDetailedURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s/%s"
    private val louseDataByYearURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/liceTypeDistribution/%s"

    private val bwClient = BuildConfig.BARENTSWATCH_CLIENT
    private val bwSecret = BuildConfig.BARENTSWATCH_SECRET

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    // ------------- Network-requests ------------- //

    /**
     * Gets token to access the Barentswatch API. Lasts 3600 seconds
     */

    override suspend fun getToken() : String {
        val response: BarentsWatchToken = client.post(tokenURL) {
            headers.append("Content-Type","application/x-www-form-urlencoded")
            body = "client_id=$bwClient&scope=api&client_secret=$bwSecret&grant_type=client_credentials"
        }
        return response.access_token
    }

    /**
     * Returns a data class representing a list of all localities
     * @param year - The year
     * @param week - The calendar week
     */
    override suspend fun getLocalities(year: Int, week: Int) : LocalitiesWrapper {
        val token: String = getToken()
        return client.get(localitiesURL.format(year,week)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }

    /**
     * Returns a data class representing detailed information about one locality
     * @param localityNo - The locality no, as stated by the Locality dataclass
     */
    override suspend fun getDetailedLocalityInfo(localityNo: Int, year: Int, week: Int) : LocalityDetailsWrapper {
        val token: String = getToken()
        return client.get(localityDetailedURL.format(localityNo,year,week)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }

    /**
     * Returns a data class representing louse data from a year at a locality
     * @param localityNo The locality no, as stated by the [Locality] dataclass
     * @param year The year to fetch data from
     */
    override suspend fun getLouseDataByYear(localityNo: Int, year: Int) : LouseDataByYear {
        val token: String = getToken()
        return client.get(louseDataByYearURL.format(localityNo,year)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }

    data class BarentsWatchToken(val access_token: String, val expires_in: Number)
}
