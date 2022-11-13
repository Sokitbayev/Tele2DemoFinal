package com.example.tele2demo.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequestApi(
    @SerializedName("msisdn")
    val login: String,
    val password: String
)