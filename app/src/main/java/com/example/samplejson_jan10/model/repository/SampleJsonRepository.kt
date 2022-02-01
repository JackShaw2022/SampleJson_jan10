package com.example.samplejson_jan10.model.repository

import com.example.samplejson_jan10.model.network.ApiManager
import com.example.samplejson_jan10.model.network.models.user.User
import com.example.samplejson_jan10.utils.Resource

class SampleJsonRepository(
    private val apiManager: ApiManager
) {
    suspend fun getUsers(): Resource<List<User>> {
        return try {
            val response = apiManager.getUsers()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get Users.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }
}
