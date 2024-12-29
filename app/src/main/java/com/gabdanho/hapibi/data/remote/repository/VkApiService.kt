package com.gabdanho.hapibi.data.remote.repository

import com.gabdanho.hapibi.data.remote.model.VkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApiService {
    @GET("method/friends.get")
    suspend fun getUserInfo(
        @Query("user_id") userId: Int? = null,
        @Query("fields") fields: String = "bdate,first_name,last_name,photo_400_orig",
        @Query("access_token") accessToken: String,
        @Query("v") apiVersion: String = "5.131"
    ): VkResponse
}