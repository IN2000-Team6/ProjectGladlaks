package com.example.gladlaksapp

import com.example.gladlaksapp.datasources.BarentswatchRepository
import com.example.gladlaksapp.models.*
import com.example.gladlaksapp.testUtils.testLocalities
import com.example.gladlaksapp.testUtils.testLousedataGen1
import com.example.gladlaksapp.testUtils.testLousedataGen2
import com.patrykandpatryk.vico.core.entry.FloatEntry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class BarentswatchRepoTest {
    private val repo = BarentswatchRepository(TestBarentswatchNetworkDatasource())

    @Test
    fun `should fetch and sort localities based on isOnLand`() {
        runBlocking {
            val localitiesInWater = repo.getLocalitiesInWater(2022,17)
            assertEquals(4, localitiesInWater.size)
        }
    }

    @Test
    fun `should fetch detailed locality info`() {
        runBlocking {
            val localityDetails = repo.getDetailedLocalityInfo(12345,2022,17)

            assertEquals("testLocality", localityDetails.localityName)
        }
    }

    @Test
    fun `should fetch nested list of FloatEntries for each year`() {
        runBlocking {
            val entries = repo.getTwoGenerations(12345, 2021,2022)

            assertEquals(2,entries.size)
            assertEquals(4,entries.first().size)
            assertTrue(entries is List<List<FloatEntry>>)
        }
    }
}


class TestBarentswatchNetworkDatasource : BarentswatchNetworkDatasourceInterface {
    override suspend fun getToken(): String {
        return "IN2000ERBEST"
    }

    override suspend fun getLocalities(
        year: Int,
        week: Int
    ): LocalitiesWrapper {
        return LocalitiesWrapper(
            localities = testLocalities,
            year = year,
            week = week
        )
    }

    override suspend fun getDetailedLocalityInfo(
        localityNo: Int,
        year: Int,
        week: Int
    ): LocalityDetailsWrapper {
        return LocalityDetailsWrapper(
            localityName = "testLocality",
            localityWeek = LocalityDetails(
                id = 123457,
                localityNo = 12345,
                year = year,
                week = week,
                hasReportedLice = true,
                hasBathTreatment = false,
                hasCleanerFishDeployed = false,
                hasInFeedTreatment = false,
                hasMechanicalRemoval = false,
                avgAdultFemaleLice = 0.3,
                avgMobileLice = 0.15,
                avgStationaryLice = 0.24,
                bathTreatments = null,
                inFeedTreatments = null,
                mechanicalRemoval = null,
                seaTemperature = null
            ),
            liceCountPreviousWeek = LiceCountPreviousWeek (
                year = year,
                week = week,
                hasReportedLice = true,
                avgAdultFemaleLice = 0.28,
                avgMobileLice = 0.14,
                avgStationaryLice = 0.22
            )
        )
    }

    override suspend fun getLouseDataByYear(localityNo: Int, year: Int): LouseDataByYear {
        return LouseDataByYear(
            localityNo = localityNo,
            year = year,
            maxCombinedNumberOfLice = "0",
            data = if (year != 2022) testLousedataGen1 else testLousedataGen2
        )
    }
}
