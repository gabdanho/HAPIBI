package com.gabdanho.hapibi.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class Friend(
    val id: Int = 0,
    val imageUrl: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val birthDayDate: String = ""
)
