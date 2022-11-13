package com.example.tele2demo.data

import com.example.tele2demo.data.model.DeviceInfoRequestApi
import com.example.tele2demo.data.model.LoginRequestApi
import com.example.tele2demo.domain.Repository
import com.example.tele2demo.domain.Response
import com.example.tele2demo.domain.model.Branch
import com.example.tele2demo.domain.model.City
import com.example.tele2demo.domain.model.DeviceInfo

class DefaultRepository(
    private val dataSource: NetworkDataSource,
    private val deviceInfoMapper: DeviceInfoMapper
) : Repository {

    override fun login(login: String, password: String): Response<String> = try {
        val result = dataSource.login(LoginRequestApi(login, password))
        result.map(
            mapSuccess = {
                Response.Success(it.token)
            },
            mapFailure = {
                Response.Failure(Exception())
            }
        )
    } catch (e: Exception) {
        Response.Failure(Exception())
    }

    override fun getCities(): Response<List<City>> = try {
        val result = dataSource.getCities()
        result.map(
            mapSuccess = {
                Response.Success(it.map { City(id = it.id, name = it.name) })
            },
            mapFailure = {
                Response.Failure(Exception())
            }
        )
    } catch (e: Exception) {
        Response.Failure(Exception())
    }

    override fun getBranches(cityId: String): Response<List<Branch>> = try {
        val result = dataSource.getBranches(cityId)
        result.map(
            mapSuccess = {
                Response.Success(it.map { Branch(id = it.id, name = it.name) })
            },
            mapFailure = {
                Response.Failure(Exception())
            }
        )
    } catch (e: Exception) {
        Response.Failure(Exception())
    }

    override fun getDeviceInfo(branchId: String, deviceId: String): Response<DeviceInfo> = try {
        val response =
            dataSource.getDeviceInfo(DeviceInfoRequestApi(branchId = branchId, deviceId = deviceId))
        response.map(
            mapSuccess = { Response.Success(deviceInfoMapper.map(it)) },
            mapFailure = { Response.Failure(Exception()) }
        )
    } catch (e: Exception) {
        Response.Failure(Exception())
    }
}