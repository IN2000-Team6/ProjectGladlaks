package com.example.gladlaksapp.models.database

import com.example.gladlaksapp.models.Locality


class LocalityRepository constructor(private val localityDao: LocalityDao) {

    fun getAll() = localityDao.getAll()

    fun getByNo(input: Int) = localityDao.getByNo(input)

    fun getByName(input: String) = localityDao.getByName("%${input}%")

    fun insertLocality(locality: Locality) = localityDao.insertLocality(locality)

    fun insertAll(localities: List<Locality>) = localityDao.insertAll(localities)
}

