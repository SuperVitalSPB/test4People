package com.test.people.api

import com.test.people.model.LatestRate
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("latest?access_key=847e0d133fe1c0d320fd3ebe423a6f23")
    suspend fun getLatest(): Response<LatestRate>
}