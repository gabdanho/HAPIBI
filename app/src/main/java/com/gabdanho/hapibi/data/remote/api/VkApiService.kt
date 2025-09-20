package com.gabdanho.hapibi.data.remote.api

import com.gabdanho.hapibi.data.remote.model.vk.VkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApiService {

    @GET("method/friends.get")
    suspend fun getUserInfo(
        @Query("access_token") accessToken: String,
        @Query("user_id") userId: Int? = null,
        @Query("fields") fields: String = "bdate,first_name,last_name,photo_400_orig",
        @Query("v") apiVersion: String = "5.199"
    ): VkResponse
}