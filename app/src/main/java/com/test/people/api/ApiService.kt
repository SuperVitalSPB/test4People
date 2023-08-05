package com.test.people.api

import com.test.people.model.LatestRateEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("latest")
    suspend fun getLatest(): Response<LatestRateEntity>
}