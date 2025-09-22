package com.gabdanho.hapibi.presentation.utils

fun convertDate(date: String): String {
    return if (date.isNotBlank()) {
        date
            .split(".")
            .joinToString(".") { it.padStart(2, '0') }
    } else ""
}