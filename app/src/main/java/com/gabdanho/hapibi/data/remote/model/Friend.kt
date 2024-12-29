package com.gabdanho.hapibi.data.remote.model

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("bdate") val bdate: String = "",
    @SerializedName("track_code") val trackCode: String = "",
    @SerializedName("first_name") val firstName: String = "",
    @SerializedName("last_name") val lastName: String = "",
    @SerializedName("can_access_closed") val canAccessClosed: Boolean = false,
    @SerializedName("is_closed") val isClosed: Boolean = false,
    @SerializedName("photo_400_orig") val imageUrl: String = ""
)
