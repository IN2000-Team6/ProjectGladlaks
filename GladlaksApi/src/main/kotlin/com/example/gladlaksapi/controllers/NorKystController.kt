package com.example.gladlaksapi.controllers

import com.example.gladlaksapi.models.Location
import com.example.gladlaksapi.repositories.openWithGridDataset
import com.example.gladlaksapi.utils.getDateNowByFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureController {
    @GetMapping("/temperature")
    fun getTemperature(
        @RequestParam lat: Double,
        @RequestParam lon: Double,
    ): Location {
        val now = getDateNowByFormat("yyyyMMdd00")
        val url = "https://thredds.met.no/thredds/dodsC/fou-hi/norkyst800m-1h/NorKyst-800m_ZDEPTHS_his.fc.${now}.nc"

        return openWithGridDataset(url, lat, lon)
    }
}