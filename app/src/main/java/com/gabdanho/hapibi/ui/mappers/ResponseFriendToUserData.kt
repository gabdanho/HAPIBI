package com.gabdanho.hapibi.ui.mappers

import com.gabdanho.hapibi.data.remote.model.Friend
import com.gabdanho.hapibi.ui.model.UserData
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Friend.toUserData(): UserData {
    return UserData(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        birthDay = convertDate(this.bdate),
        imageUrl = this.imageUrl
    )
}

fun convertDate(date: String): String {
    var formattedDate = ""
    date.split(".").forEach {
        if (it.length == 1) formattedDate += "0"
        formattedDate += "$it."
    }
    formattedDate = formattedDate.dropLast(1)

    return formattedDate
}