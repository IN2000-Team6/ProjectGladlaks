package com.example.gladlaksapp.datasources

import com.example.gladlaksapp.models.BarentsWatchToken
import com.example.gladlaksapp.models.Localities
import com.example.gladlaksapp.models.Locality
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

object BarentswatchNetworkDataSource {

    private const val tokenURL = "https://id.barentswatch.no/connect/token"
    private const val localitiesURL = "https://www.barentswatch.no/bwapi/v1/geodata/fishhealth/locality/%s/%s"

    private const val testClient = "jesperdn@uio.no:Jesperdn"
    private const val testSecret = "IN2000ErBest"


// ------------- Clients ------------- //

    private val postClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        defaultRequest {
            header("accept", "text/plain")
        }
    }



    private val getClient = HttpClient(CIO) {
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
        val response: BarentsWatchToken = postClient.post(tokenURL) {
            headers {
                append("Content-Type","application/x-www-form-urlencoded")
            }
            body = "client_id=jesperdn@uio.no:Jesperdn&scope=api&client_secret=IN2000ErBest&grant_type=client_credentials"
        }
        return response.access_token
    }

    /**
     * Returns a data class representing a list of all localities
     */
    suspend fun getLocalities(year: Int, week: Int) : Localities {
        //val token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjBCM0I1NEUyRkQ5OUZCQkY5NzVERDMxNDBDREQ4OEI1QzA5RkFDRjMiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJDenRVNHYyWi03LVhYZE1VRE4ySXRjQ2ZyUE0ifQ.eyJuYmYiOjE2NDc0MzA0NjQsImV4cCI6MTY0NzQzNDA2NCwiaXNzIjoiaHR0cHM6Ly9pZC5iYXJlbnRzd2F0Y2gubm8iLCJhdWQiOiJhcGkiLCJjbGllbnRfaWQiOiJqZXNwZXJkbkB1aW8ubm86SmVzcGVyZG4iLCJzY29wZSI6WyJhcGkiXX0.R4S9Bwh7oXpTPBIEMW_t3UDGvULpD61qTFFL6Ew0gFMnsFWnaQX8jegR_h3WK35iUavHYBCZ0UozBJt5Gt358929HiPNo5-ZMFCSkzdtLtRxP9QjzO9sdwNHrOuBg0AClSbNb6ZVW98Ovmyj0Y_Tvh36b3ufkeTQBKGUXWXWdNInD2tLLE46bGiAN7cm9AutA0CD1crXbdJSw20rbUtZBb4QkyuSv3Ddwjh-eSyMPKtNKOmUOlUvqtoRVq5ieu-oGklJqPD2msvvTfuPE5vRs7rWTqKRc_6zWiXfit9Hyv57GNY-5-7ojfMTtIN-IlIvM_0FeNbdMkYqD6CQxZVXzvDArlbMWExTdvBwIOgLyTFGMQv6uY5EqaEMTRQZ5fGD-gHr5vOunfoaM4KMZ-u4rqTTJwQvr8yXx4UP1T5NyT7Z85kwPRePKAKoEzoI5cc1Bfw__RlnjXxMTKgcUV4ggS5KSf-CjE2i6tPMi8IoVXaQ7L7v8MCZDwz2XsN5GfpNQ2PQErMIgju0KmtCXHE0ID9gD-WFH8noc-1b4kMKx7vMMweonICpaOqywmmziTVI70pGHWgMWGsVeV6mOUBLb4dqfw_IUWOuvBKow4c6_DqiqGbe9Je3B3qIDYNAT73SM_Lq6CKidyqRXLSn6yuMeRvMoAfYVjld_wKge5qR2SI"
        val token: String = getToken()
        return getClient.get(localitiesURL.format(year,week)) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
    }


}
