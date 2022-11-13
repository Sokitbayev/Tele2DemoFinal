package com.example.tele2demo.data

import com.example.tele2demo.domain.Response
import retrofit2.Response as RetrofitResponse


fun <Api : Any, Domain : Any> RetrofitResponse<Api>.map(
    mapSuccess: (Api) -> Response<Domain>,
    mapFailure: ((RetrofitResponse<Api>) -> Response<Domain>)? = null
): Response<Domain> {
    val body = body()

    return if (body != null)
        mapSuccess(body)
    else
        mapFailure?.invoke(this) ?: Response.Failure(Exception(this.message()))
}