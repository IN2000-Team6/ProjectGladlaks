package com.example.gladlaksapi.controllers

import com.example.gladlaksapi.models.LocalityTemperature
import com.example.gladlaksapi.models.NorKystMaxDepth
import com.example.gladlaksapi.repositories.fetchFromNorKyst
import com.example.gladlaksapi.utils.getDateNowByFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Validated
@RestController
class TemperatureController {

    @GetMapping("/temperature")
    fun getTemperature(
        @RequestParam(required = true) lat: Double,
        @RequestParam(required = true) lon: Double,
        @RequestParam(defaultValue = "1") @Min(0) @Max(NorKystMaxDepth) depth: Int,
    ): LocalityTemperature {
        val now = getDateNowByFormat("yyyyMMdd00")
        val url = "https://thredds.met.no/thredds/dodsC/fou-hi/norkyst800m-1h/NorKyst-800m_ZDEPTHS_his.fc.${now}.nc"

        return fetchFromNorKyst(url, lat, lon, depth)
    }
}