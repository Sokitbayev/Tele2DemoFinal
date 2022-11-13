package com.example.tele2demo.data

import com.example.tele2demo.data.model.DeviceInfoResponseApi
import com.example.tele2demo.domain.Mapper
import com.example.tele2demo.domain.model.DeviceInfo

class DeviceInfoMapper : Mapper<DeviceInfoResponseApi, DeviceInfo>() {

    override fun map(from: DeviceInfoResponseApi): DeviceInfo {
        return DeviceInfo(
            title = from.title,
            subTitle = from.subTitle,
            banner = DeviceInfo.Banner(from.banner.imageUrl, from.banner.videoUrl),
            price = DeviceInfo.Price(from.price.old,from.price.new),
            installmentPlan = DeviceInfo.InstallmentPlan(from.installmentPlan.price, from.installmentPlan.months),
            characteristic = from.characteristic.map { DeviceInfo.Characteristic(it.iconUrl, it.text, it.value) },
            tariff = DeviceInfo.Tariff(from.tariff.iconUrl, from.tariff.name, DeviceInfo.Tariff.Resources(from.tariff.resources.sms,from.tariff.resources.minutes, from.tariff.resources.data)),
            shopUrls = from.shopUrls.map { DeviceInfo.Shop(it.name, it.iconUrl, it.link) }

        )
    }
}