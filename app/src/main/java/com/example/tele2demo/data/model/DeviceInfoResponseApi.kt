package com.example.tele2demo.data.model

import com.example.tele2demo.domain.model.TextLang

data class DeviceInfoResponseApi(
    val title: String,
    val subTitle: String,
    val banner: BannerResponseApi,
    val price: PriceResponseApi,
    val installmentPlan: InstallmentPlanResponseApi,
    val characteristic: List<CharacteristicResponseApi>,
    val tariff: TariffResponseApi,
    val shopUrls: List<ShopResponseApi>
) {
    data class BannerResponseApi(
        val imageUrl: String,
        val videoUrl: String
    )

    data class PriceResponseApi(
        val old: String,
        val new: String
    )

    data class InstallmentPlanResponseApi(
        val price: String,
        val months: String
    )

    data class CharacteristicResponseApi(
        val iconUrl: String,
        val text: TextLang,
        val value: String
    )

    data class TariffResponseApi(
        val iconUrl: String,
        val name: TextLang,
        val resources: ResourcesResponseApi
    ) {
        data class ResourcesResponseApi(
            val sms: String,
            val minutes: String,
            val data: String,
        )
    }

    data class ShopResponseApi(
        val name: String,
        val iconUrl: String,
        val link: String
    )
}
