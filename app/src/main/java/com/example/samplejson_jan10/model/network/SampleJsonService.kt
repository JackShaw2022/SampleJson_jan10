package com.example.samplejson_jan10.model.network

import com.example.samplejson_jan10.model.network.models.user.User
import retrofit2.Response
import retrofit2.http.GET

interface SampleJsonService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}