package com.example.gladlaksapp.repositories

import com.example.gladlaksapp.models.Locality
import com.example.gladlaksapp.models.LocalityDao

class LocalityRepository (
    private val localityDao: LocalityDao
) {
    fun getAll() = localityDao.getAll()

    fun getByNo(input: Int) = localityDao.getByNo(input)

    fun getByName(input: String) = localityDao.getByName("%${input}%")

    fun insertLocality(locality: Locality) = localityDao.insertLocality(locality)

    fun insertAll(localities: List<Locality>) = localityDao.insertAll(localities)
}

