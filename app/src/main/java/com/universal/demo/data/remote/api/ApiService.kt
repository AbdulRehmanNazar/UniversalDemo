package com.universal.demo.data.remote.api

import com.universal.demo.data.model.User
import retrofit2.Response
import retrofit2.http.GET

/**
 * @Author: Abdul Rehman
 */

interface ApiService {
    @GET("contributors")
    suspend fun getContributors(): Response<List<User>>
}