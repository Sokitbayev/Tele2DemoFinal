package com.example.tele2demo.data

import com.example.tele2demo.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkDataSource {

    @GET("digital-price/admin/auth")
    fun login(@Body loginData: LoginRequestApi): Response<LoginResponseApi>

    @GET("digital-price/cities")
    fun getCities(): Response<List<CityResponseApi>>

    @GET("digital-price/branches")
    fun getBranches(@Query("cityId") cityId: String): Response<List<BranchResponseApi>>

    @GET("digital-price/client/device-info")
    fun getDeviceInfo(@Body deviceInfo: DeviceInfoRequestApi): Response<DeviceInfoResponseApi>
}