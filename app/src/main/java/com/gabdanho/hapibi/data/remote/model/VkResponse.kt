package com.gabdanho.hapibi.data.remote.model

import com.google.gson.annotations.SerializedName

data class VkResponse(
    @SerializedName("response") val response: FriendsResponse
)
