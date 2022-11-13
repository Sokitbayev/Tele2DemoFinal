package com.example.tele2demo.domain

import com.example.tele2demo.domain.model.Branch
import com.example.tele2demo.domain.model.City
import com.example.tele2demo.domain.model.DeviceInfo

interface Repository {

    fun login(login: String, password: String): Response<String>

    fun getCities(): Response<List<City>>

    fun getBranches(cityId: String): Response<List<Branch>>

    fun getDeviceInfo(branchId: String, deviceId: String): Response<DeviceInfo>
}