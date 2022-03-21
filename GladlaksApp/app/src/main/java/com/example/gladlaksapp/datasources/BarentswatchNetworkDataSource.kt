package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.BarentsWatchToken
import com.example.gladlaksapp.models.Localities
import com.example.gladlaksapp.models.LocalityDetailed
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

object BarentswatchNetworkDataSource {
    private const val tokenURL = "https://id.barentswatch.no/connect/token"
    private const val localitiesURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s"                //
    private const val localityDetailedURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s/%s"  // Can these be combined? :thinking:


    private const val testClient = "jesperdn@uio.no:Jesperdn"
    private const val testSecret = "IN2000ErBest"

    // ------------- Clients ------------- //

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        defaultRequest {
            header("accept", "text/plain")
        }
    }

    // ------------- Network-requests ------------- //

    /**
     * Gets token to access the Barentswatch API. Lasts 3600 seconds
     */
    private suspend fun getToken() : String {
        val response: BarentsWatchToken = client.post(tokenURL) {
            headers {
                append("Content-Type","application/x-www-form-urlencoded")
            }
            body = "client_id=jesperdn@uio.no:Jesperdn&scope=api&client_secret=IN2000ErBest&grant_type=client_credentials"
        }
        return response.access_token
    }

    /**
     * Returns a data class representing a list of all localities
     * @param year - The year
     * @param week - The calendar week
     */
    suspend fun getLocalities(year: Int, week: Int) : Localities {
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
    suspend fun getDetailedLocalityInfo(localityNo: Int,year: Int, week: Int) : LocalityDetailed {
        val token: String = getToken()
        return client.get(localityDetailedURL.format(localityNo,year,week)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }
}