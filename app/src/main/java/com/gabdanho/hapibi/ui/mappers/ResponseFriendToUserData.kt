package com.gabdanho.hapibi.ui.mappers

import com.gabdanho.hapibi.data.remote.model.Friend
import com.gabdanho.hapibi.ui.model.UserData

fun Friend.toUserData(): UserData {
    return UserData(
        firstName = this.firstName,
        lastName = this.lastName,
        birthDay = this.bdate,
        imageUrl = this.imageUrl
    )
}